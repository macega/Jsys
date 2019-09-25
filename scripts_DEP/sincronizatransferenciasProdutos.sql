ALTER PROCEDURE sincronizatransferenciasProdutos
AS
BEGIN
	DECLARE @Srv SYSNAME
		,@BD SYSNAME
		,@Comando1 NVARCHAR(max)
		,@Comando2 NVARCHAR(max)
		,@OK AS INT

	DECLARE cServer CURSOR
	FOR
	SELECT NAME
	FROM SYS.SERVERS
	WHERE is_linked = 1
	ORDER BY NAME

	SET @BD = 'DADOS'
	SET @OK = 0

	OPEN cServer

	FETCH NEXT
	FROM cServer
	INTO @Srv

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
				FROM DADOS.DBO.transferenciasProdutos DEP WITH (NOLOCK)
					LEFT JOIN [' + @Srv + '].' + @BD + '.DBO.transferenciasProdutos LOJA WITH (NOLOCK) ON LOJA.idTransf = DEP.idTransf
				WHERE LOJA.idTransf is null and DEP.idLoja = ''' + @Srv + ''' '
			SET @Comando2 = N'' + 'INSERT INTO Replicacao (tabela, id, idAntigo, operacao, servidorDestino, ExecSP)
				SELECT ''transferenciasProdutosItens''
						,(cast(DEP.idProduto as varchar)+''|''+ cast(DEP.idTransf as varchar))
						,(cast(DEP.idProduto as varchar)+''|''+ cast(DEP.idTransf as varchar))
						,''INSERT/UPDATE''
						,''' + @Srv + '''
						,''BaixaTranferencia '' + cast(DEP.idTransf as varchar)
				FROM DADOS.DBO.transferenciasProdutosItens AS DEP WITH (NOLOCK)
					INNER JOIN DADOS.DBO.transferenciasProdutos WITH (NOLOCK) ON transferenciasProdutos.idTransf = DEP.idTransf
					LEFT JOIN [' + @Srv + '].' + @BD + '.DBO.transferenciasProdutosItens AS LOJA WITH (NOLOCK) ON LOJA.idTransf = DEP.idTransf
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
	END

	CLOSE cServer

	DEALLOCATE cServer
END