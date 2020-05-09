CREATE PROCEDURE sincronizatransferenciasProdutos
AS
BEGIN
	DECLARE @Srv SYSNAME
		,@nomeBancoDadosLocal SYSNAME
		,@nomeBancoDadosRemoto SYSNAME
		,@Comando1 NVARCHAR(max)
		,@Comando2 NVARCHAR(max)
		,@OK AS INT

	SELECT @nomebancoDadosLocal = db_name()

	DECLARE cServer CURSOR
	FOR
	SELECT NAME
		,nomeBancoDados
	FROM SERVERS
	INNER JOIN jsysLojas ON jsysLojas.idloja = SERVERS.NAME
	WHERE is_linked = 1
	ORDER BY NAME

	SET @OK = 0

	OPEN cServer

	FETCH NEXT
	FROM cServer
	INTO @Srv
		,@nomeBancoDadosRemoto

	WHILE @@FETCH_STATUS = 0
	BEGIN
		BEGIN TRY
			EXEC sp_testlinkedserver @Srv

			-- CRIAR UMA SCRIPT PARA INSERIR OU ATULIZAR O REGISTRO
			SET @Comando1 = N'' + 'INSERT INTO Replicacao (tabela, id, idAntigo, operacao, servidorDestino)
				SELECT ''transferenciasProdutos''
						,cast(DEP.idTransf as varchar)
						,cast(DEP.idTransf as varchar)
						,''INSERT/UPDATE''
						,''' + @Srv + '''
				FROM [' + @nomebancoDadosLocal + '].DBO.transferenciasProdutos DEP WITH (NOLOCK)
					LEFT JOIN [' + @Srv + '].[' + @nomeBancoDadosRemoto + '].DBO.transferenciasProdutos LOJA WITH (NOLOCK) ON LOJA.idTransf = DEP.idTransf
				WHERE LOJA.idTransf is null and DEP.idLoja = ''' + @Srv + ''' '
			SET @Comando2 = N'' + 'INSERT INTO Replicacao (tabela, id, idAntigo, operacao, servidorDestino, ExecSP)
				SELECT ''transferenciasProdutosItens''
						,(cast(DEP.idProduto as varchar)+''|''+ cast(DEP.idTransf as varchar))
						,(cast(DEP.idProduto as varchar)+''|''+ cast(DEP.idTransf as varchar))
						,''INSERT/UPDATE''
						,''' + @Srv + '''
						,''BaixaTranferencia '' + cast(DEP.idTransf as varchar)
				FROM [' + @nomebancoDadosLocal + '].DBO.transferenciasProdutosItens AS DEP WITH (NOLOCK)
					INNER JOIN [' + @nomebancoDadosLocal + '].DBO.transferenciasProdutos WITH (NOLOCK) ON transferenciasProdutos.idTransf = DEP.idTransf
					LEFT JOIN [' + @Srv + '].[' + @nomeBancoDadosRemoto + '].DBO.transferenciasProdutosItens AS LOJA WITH (NOLOCK) ON LOJA.idTransf = DEP.idTransf
																				AND LOJA.idProduto = DEP.idProduto
				WHERE (LOJA.idproduto is null OR LOJA.quantidade <> DEP.quantidade) and (transferenciasProdutos.idLoja = ''' + @Srv + ''') '
		END TRY

		BEGIN CATCH
			SET @OK = @OK + 1
		END CATCH

		--PRINT @Comando1
		--PRINT @Comando2
		EXEC SP_EXECUTESQL @Comando1

		EXEC SP_EXECUTESQL @Comando2

		SET @Comando1 = N''
		SET @Comando2 = N''

		FETCH NEXT
		FROM cServer
		INTO @Srv
			,@nomeBancoDadosRemoto
	END

	CLOSE cServer

	DEALLOCATE cServer
END
