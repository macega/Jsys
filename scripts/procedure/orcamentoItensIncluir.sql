CREATE PROCEDURE orcamentoItensIncluir (@idOrcamento AS int, @idProduto AS int, @idbarra as varchar(30), @quantidade as decimal(16, 4)) AS
BEGIN TRANSACTION
SET NOCOUNT ON
DECLARE @ERRO AS INT
SET @ERRO = 0
BEGIN TRY
IF (SELECT count(*) FROM jsysOrcamentoItens inner join jsysOrcamento on jsysOrcamento.idOrcamento = jsysOrcamentoItens.idOrcamento
    WHERE (jsysOrcamento.vendido = 0) AND (jsysOrcamento.cancelado = 0) AND (jsysOrcamentoItens.idOrcamento = @idOrcamento) AND (idProduto = @idProduto)) = 0 
	INSERT INTO [jsysOrcamentoItens]
           ([idOrcamento]
           ,[idProduto]
           ,[quantidade]
           ,[precoVenda]
           ,[totalProduto]
           ,[desconto]
           ,[precoCompra]
           ,[unidadeVenda]
           ,[comissaoVista]
           ,[comissaoPrazo]
           ,[idbarra])
     SELECT @idOrcamento
           ,@idProduto  --<idProduto, int,>
           ,@quantidade --<quantidade, decimal(16,4),>
           ,[jsysProdutosTPrecos].precoVenda  --<precoVenda, decimal(16,4),>
           ,(@quantidade * [jsysProdutosTPrecos].precoVenda) --<totalProduto, decimal(16,4),>
           , 0 --<desconto, decimal(16,4),>
           ,[jsysProdutosT].[precoCompra] --,<precoCompra, decimal(16,4),>
           ,[jsysProdutosT].[unidadeVenda] --<unidadeVenda, varchar(3),>
           ,[jsysProdutosT].[comissaoVista] --<comissaoVista, decimal(5,2),>
           ,[jsysProdutosT].[comissaoPrazo] --<comissaoPrazo, decimal(5,2),>
           ,@idbarra --<idbarra, varchar(30),> 
		   FROM [jsysProdutosT] 
		   INNER JOIN [jsysProdutosTPrecos] ON [jsysProdutosT].[idProduto] = [jsysProdutosTPrecos].[idProduto]
		   INNER JOIN [jsysLojas] ON [jsysLojas].[idloja] = [jsysProdutosTPrecos].[idloja]
		   WHERE [jsysProdutosT].idProduto = @idProduto AND [jsysLojas].[ativo] = 1
	ELSE 
		IF (SELECT count(*) FROM jsysOrcamento WHERE (jsysOrcamento.vendido = 0) AND (jsysOrcamento.cancelado = 0) AND (jsysOrcamento.idOrcamento = @idOrcamento)) = 1
		BEGIN
		UPDATE [jsysOrcamentoItens]
		   SET [quantidade] = ([jsysOrcamentoItens].[quantidade] + @quantidade)
			  ,[precoVenda] = [jsysProdutosTPrecos].precoVenda --<precoVenda, decimal(16,4),>
			  ,[totalProduto] = (([jsysOrcamentoItens].[quantidade] + @quantidade) * [jsysProdutosTPrecos].precoVenda)--<totalProduto, decimal(16,4),>
			  ,[desconto] = 0 --<desconto, decimal(16,4),>
			  ,[precoCompra] = [jsysProdutosT].[precoCompra] --<precoCompra, decimal(16,4),>
			  ,[unidadeVenda] = [jsysProdutosT].[unidadeVenda] --<unidadeVenda, varchar(3),>
			  ,[comissaoVista] = [jsysProdutosT].[comissaoVista] --<comissaoVista, decimal(5,2),>
			  ,[comissaoPrazo] = [jsysProdutosT].[comissaoPrazo] --<comissaoPrazo, decimal(5,2),>
			  ,[idbarra] = @idbarra
		FROM [jsysProdutosT] 
		   INNER JOIN [jsysOrcamentoItens] ON [jsysOrcamentoItens].[idProduto] = [jsysProdutosT].[idProduto]
		   INNER JOIN [jsysProdutosTPrecos] ON [jsysProdutosT].[idProduto] = [jsysProdutosTPrecos].[idProduto]
		   INNER JOIN [jsysLojas] ON [jsysLojas].[idloja] = [jsysProdutosTPrecos].[idloja]
		WHERE ([jsysProdutosT].idProduto = @idProduto) AND ([jsysOrcamentoItens].[idOrcamento] = @idOrcamento) AND ([jsysLojas].[ativo] = 1)
		DELETE [jsysOrcamentoItens] WHERE [quantidade] = 0
		END
	END TRY
	BEGIN CATCH
		SET @ERRO = 1
	END CATCH
	BEGIN TRY
		UPDATE jsysOrcamento SET valorBruto = TB.TOTAL, valorLiquido = TB.TOTAL
		FROM jsysOrcamento inner join (SELECT idorcamento, SUM(totalProduto) as TOTAL 
										FROM jsysOrcamentoItens group by idorcamento) AS TB on TB.idorcamento = jsysOrcamento.idorcamento
		WHERE jsysOrcamento.idorcamento = @idOrcamento
	END TRY
	BEGIN CATCH
		SET @ERRO = 1
	END CATCH
COMMIT TRANSACTION
RETURN @ERRO