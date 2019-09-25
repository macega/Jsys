CREATE PROCEDURE ReplicaDados (
	@codigoLancamento AS INT
	,@tabela AS VARCHAR(255)
	,@id AS VARCHAR(50)
	,@idAnterior AS VARCHAR(50)
	,@op AS VARCHAR(20)
	,@sd AS VARCHAR(30)
	,@ExecSP NVARCHAR(max) = N''
	,@cr VARCHAR(max) = ''
	,@camposChave VARCHAR(5000) = '' -- passar os campos no formato <idProduto/><idGrupo/>
	)
AS
SET NOCOUNT ON

-- declaçao de varibles
DECLARE @Srv SYSNAME
	,@nomeBancoDados SYSNAME
	,@Comando NVARCHAR(max)
	,@OK AS INT = 0
	,@camposInsert AS NVARCHAR(max)
	,@camposUpdate AS NVARCHAR(max)
	,@camposKeys AS NVARCHAR(max)
	,@camposLKeys AS NVARCHAR(max)
	,@camposRKeys AS NVARCHAR(max)
	,@camposDeleteKeys AS NVARCHAR(max)

-- procura por servidores vinculados 
DECLARE cServer CURSOR
FOR
SELECT NAME
	,jsysLojas.nomeBancoDados
FROM SYS.SERVERS
INNER JOIN jsysLojas ON jsysLojas.idloja = SYS.SERVERS.NAME
WHERE (is_linked = 1)
	AND (
		NAME IN (
			SELECT itens
			FROM DBO.SPLIT(@sd, ',')
			)
		OR '' = @sd
		OR 'ALL' = @sd
		)

-- verifica se foi passado uma campos chaves se nao procura pelas chaves primarias da tabela a ser replicada 
IF (
		@camposChave = ''
		OR @camposChave IS NULL
		)
BEGIN
	SELECT @camposKeys = (
			SELECT DISTINCT TB.NAME
			FROM (
				SELECT c.NAME
				FROM sys.objects t
				INNER JOIN sys.columns c ON t.object_id = c.object_id
				INNER JOIN sys.index_columns ic ON t.object_id = ic.object_id
					AND ic.column_id = c.column_id
				INNER JOIN sys.indexes i ON t.object_id = i.object_id
					AND i.index_id = ic.index_id
				WHERE (t.NAME = @tabela)
					AND (
						CASE 
							WHEN t.type_desc = 'VIEW'
								THEN i.is_unique
							ELSE i.is_primary_key
							END = 1
						)
				
				UNION ALL
				
				SELECT NAME
				FROM sys.sysforeignkeys
				INNER JOIN sys.syscolumns parent_columns ON fkeyid = id
					AND fkey = colid
				WHERE OBJECT_NAME(fkeyid) = @tabela
				) AS TB
			ORDER BY 1
			FOR XML RAW('T')
			)

	SET @camposLKeys = substring(REPLACE(REPLACE(@camposKeys, '<T name="', '+''|''+cast(L.['), '"/>', '] as varchar)'), 6, 5000)
	SET @camposRKeys = substring(REPLACE(REPLACE(@camposKeys, '<T name="', '+''|''+cast(R.['), '"/>', '] as varchar)'), 6, 5000)
	SET @camposDeleteKeys = substring(REPLACE(REPLACE(@camposKeys, '<T name="', '+''|''+cast(['), '"/>', '] as varchar)'), 6, 5000)
END
ELSE
BEGIN
	SET @camposLKeys = substring(REPLACE(REPLACE(@camposChave, '<', '+''|''+cast(L.['), '/>', '] as varchar)'), 6, 5000)
	SET @camposRKeys = substring(REPLACE(REPLACE(@camposChave, '<', '+''|''+cast(R.['), '/>', '] as varchar)'), 6, 5000)
	SET @camposDeleteKeys = substring(REPLACE(REPLACE(@camposChave, '<', '+''|''+cast(['), '/>', '] as varchar)'), 6, 5000)
END

OPEN cServer

FETCH NEXT
FROM cServer
INTO @Srv, @nomeBancoDados

-- vai fazer um loop para cada link server configurado no servidor ou que foi selecina por parametro 
WHILE @@FETCH_STATUS = 0
BEGIN
	-- limpa o script 
	SET @Comando = N''

	BEGIN TRY
		-- verifica se o servidor esta online 
		EXEC sp_testlinkedserver @Srv

		-- se e um insert ou upadate ele vai montar o script
		IF @op = 'INSERT/UPDATE'
		BEGIN
			-- SELECIONA TODOS OS CAMPOS DA TABELA PARA MONTAR FAZER O UPDATE 
			SELECT @camposUpdate = REPLACE(REPLACE(REPLACE((
								SELECT COLUNAS.NAME AS C
									,COLUNAS.NAME AS R
								FROM SYSOBJECTS AS T
								INNER JOIN SYSCOLUMNS AS COLUNAS ON T.ID = COLUNAS.ID
								WHERE T.NAME = @tabela
									AND COLUNAS.NAME NOT IN (
										SELECT itens
										FROM dbo.Split(@cr, '|')
										)
								FOR XML RAW('T')
								), '<T C="', ',['), '" R="', ']=L.['), '"/>', ']')

			SELECT @camposUpdate = substring(@camposUpdate, 2, len(@camposUpdate) - 1)

			-- SELECIONA TODOS OS CAMPOS DA TABELA PARA FAZER O INSERT
			SELECT @camposInsert = REPLACE(REPLACE((
							SELECT COLUNAS.NAME AS I
							FROM SYSOBJECTS AS T
							INNER JOIN SYSCOLUMNS AS COLUNAS ON T.ID = COLUNAS.ID
							WHERE T.NAME = @tabela
								AND COLUNAS.NAME NOT IN (
									SELECT itens
									FROM dbo.Split(@cr, '|')
									)
							FOR XML RAW('T')
							), '<T I="', ',['), '"/>', ']')

			SELECT @camposInsert = substring(@camposInsert, 2, len(@camposInsert) - 1)

			-- aqui pega todas as informaçoes campos, chaves, e monta um insert / update 
			-- ele cria um script para verificar se ja existe o registro, assim ele pode tanto incluir como atulizar
			SET @Comando = CONCAT (
					'IF (SELECT COUNT(*) FROM ['
					,@Srv
					,'].'
					,@nomeBancoDados
					,'.DBO.['
					,@tabela
					,'] AS L WITH (NOLOCK) WHERE '
					,@camposLKeys
					,' = '''
					,@id
					,''') >= 1 BEGIN '
					,'UPDATE ['
					,@Srv
					,'].'
					,@nomeBancoDados
					,'.DBO.['
					,@tabela
					,'] '
					,'SET '
					,@camposUpdate
					,' '
					,'FROM ['
					,@Srv
					,'].'
					,@nomeBancoDados
					,'.DBO.['
					,@tabela
					,'] AS R WITH (NOLOCK) CROSS JOIN '
					,@nomeBancoDados
					,'.DBO.['
					,@tabela
					,']'
					,' AS L WITH (NOLOCK) '
					,'WHERE '
					,@camposLKeys
					,' = '''
					,@id
					,''' '
					,'AND '
					,@camposRKeys
					,' = '''
					,@idAnterior
					,''' END '
					,'ELSE BEGIN '
					,'INSERT INTO ['
					,@Srv
					,'].'
					,@nomeBancoDados
					,'.DBO.['
					,@tabela
					,'] ('
					,@camposInsert
					,') '
					,'SELECT '
					,@camposInsert
					,' FROM '
					,@nomeBancoDados
					,'.DBO.['
					,@tabela
					,'] AS L WITH (NOLOCK) '
					,'WHERE '
					,@camposLKeys
					,' = '''
					,@id
					,''' END'
					)
		END
				-- se e um delete ele vai montar o script
		ELSE IF @op = 'DELETE'
		BEGIN
			-- CRIAR UMA SCRIPT PARA DELETAR O REGISTRO
			SET @Comando = CONCAT (
					'DELETE FROM ['
					,@Srv
					,'].'
					,@nomeBancoDados
					,'.DBO.['
					,@tabela
					,'] WHERE '
					,@camposDeleteKeys
					,' = '''
					,@id
					,''' '
					)
		END

		BEGIN TRY
			-- pega o script montado e executa 
			EXEC SP_EXECUTESQL @Comando

			-- vefifica se tem alguma coisa para fazer apos o insert ex: atulizar o estoque
			IF (@ExecSP <> '')
			BEGIN
				SET @ExecSP = CONCAT (
						N'EXEC ['
						,@Srv
						,'].'
						,@nomeBancoDados
						,'.DBO.'
						,@ExecSP
						)

				-- executa o 2 script
				EXEC SP_EXECUTESQL @ExecSP
			END
		END TRY

		-- se seu erro entra aqui e resitra o erro na tabela Replicacao
		BEGIN CATCH
			SET @OK += 1

			UPDATE Replicacao
			SET obs = CONCAT (
					obs
					,@Srv
					,'; '
					,CAST(@@ERROR AS VARCHAR)
					,'; '
					,ERROR_MESSAGE()
					,'. '
					)
			WHERE idReplicacao = @codigoLancamento
		END CATCH
	END TRY

	-- se seu erro entra aqui e resitra o erro na tabela Replicacao
	BEGIN CATCH
		SET @OK += 1

		UPDATE Replicacao
		SET obs = CONCAT (
				obs
				,@Srv
				,'; '
				,CAST(@@ERROR AS VARCHAR)
				,'; '
				,ERROR_MESSAGE()
				,'. '
				)
		WHERE idReplicacao = @codigoLancamento
	END CATCH

	--  vai para o proximo registro 
	FETCH NEXT
	FROM cServer
	INTO @Srv, @nomeBancoDados
END

-- fecha o loop
CLOSE cServer

DEALLOCATE cServer

-- se nao tiver erro durante a execuçao ele registra na tabela replicaçao a data e hora que foi executado 
IF @OK = 0
BEGIN
	UPDATE Replicacao
	SET dataProcessado = GETDATE()
	WHERE idReplicacao = @codigoLancamento
END
