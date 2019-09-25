CREATE VIEW kardexAtual
AS
SELECT TOP 100 PERCENT tb.tabelaOrigem
	,tb.idLancamento
	,tb.data
	,tb.idProduto
	,tb.entrou
	,tb.saiu
	,tb.idfornecedor
FROM (
	--COMPRAS
	SELECT 'COMPRAS' AS tabelaOrigem
		,cast(jsysCompras.idCompra AS VARCHAR(30)) AS idLancamento
		,data
		,jsysComprasItens.idProduto
		,jsysComprasItens.quantidade AS entrou
		,0 AS saiu
		,jsysCompras.idCorentista AS idfornecedor
	FROM jsysComprasItens WITH (NOLOCK)
	INNER JOIN jsysCompras WITH (NOLOCK) ON jsysComprasItens.idCompra = jsysCompras.idCompra
	WHERE (jsysCompras.confirmado = 1)
	
	UNION ALL
	
	--COMPRAS - CANCELADAS
	SELECT 'COMPRA CANCELADA' AS tabelaOrigem
		,cast(jsysCompras.idCompra AS VARCHAR(30)) AS idLancamento
		,data
		,jsysComprasItens.idProduto
		,0 AS entrou
		,jsysComprasItens.quantidade AS saiu
		,jsysCompras.idCorentista AS idfornecedor
	FROM jsysComprasItens WITH (NOLOCK)
	INNER JOIN jsysCompras WITH (NOLOCK) ON jsysComprasItens.idCompra = jsysCompras.idCompra
	WHERE (jsysCompras.confirmado = 1)
		AND (jsysCompras.cancelado = 1)
	
	UNION ALL
	
	-- AJUSTE
	SELECT 'AJUSTE' AS tabelaOrigem
		,cast(AjusteEstoqueItens.idAjusteEstoque AS VARCHAR(30)) AS idLancamento
		,AjusteEstoque.data
		,AjusteEstoqueItens.idProduto
		,CASE 
			WHEN AjusteEstoqueItens.quantidade > 0
				THEN AjusteEstoqueItens.quantidade
			ELSE 0
			END AS entrou
		,CASE 
			WHEN AjusteEstoqueItens.quantidade < 0
				THEN AjusteEstoqueItens.quantidade * (- 1)
			ELSE 0
			END AS Saiu
		,0 AS idfornecedor
	FROM AjusteEstoque WITH (NOLOCK)
	INNER JOIN AjusteEstoqueItens WITH (NOLOCK) ON AjusteEstoque.idAjusteEstoque = AjusteEstoqueItens.idAjusteEstoque
	WHERE (AjusteEstoque.fechado = 1)
	
	UNION ALL
	
	-- VENDAS E DEVOLUÇÃO 
	SELECT CASE 
			WHEN jsysOrcamento.tipoVenda = 'Devolução'
				AND jsysOrcamentoItens.quantidade < 0
				THEN 'DEVOLUCAO ENTRADA'
			WHEN jsysOrcamento.tipoVenda = 'Devolução'
				AND jsysOrcamentoItens.quantidade > 0
				THEN 'VENDA TROCA'
			ELSE 'VENDA'
			END AS tabelaOrigem
		,cast(jsysOrcamento.idOrcamento AS VARCHAR(30)) AS idLancamento
		,jsysOrcamento.dataVendido AS data
		,jsysOrcamentoItens.idProduto
		,CASE 
			WHEN jsysOrcamentoItens.quantidade < 0
				THEN jsysOrcamentoItens.quantidade * (- 1)
			ELSE 0
			END AS entrou
		,CASE 
			WHEN jsysOrcamentoItens.quantidade > 0
				THEN jsysOrcamentoItens.quantidade
			ELSE 0
			END AS saiu
		,0 AS idfornecedor
	FROM jsysOrcamento WITH (NOLOCK)
	INNER JOIN jsysOrcamentoItens WITH (NOLOCK) ON jsysOrcamentoItens.idOrcamento = jsysOrcamento.idOrcamento
	WHERE (jsysOrcamento.vendido = 1)
	
	UNION ALL
	
	-- VENDAS E DEVOLUÇÃO - CANCELADAS
	SELECT CASE 
			WHEN jsysOrcamento.tipoVenda = 'Devolução'
				AND jsysOrcamentoItens.quantidade < 0
				THEN 'DEVOLUCAO CANCELADA'
			WHEN jsysOrcamento.tipoVenda = 'Devolução'
				AND jsysOrcamentoItens.quantidade > 0
				THEN 'VENDA TROCA CANCELA'
			ELSE 'VENDA CANCELADA'
			END AS tabelaOrigem
		,cast(jsysOrcamento.idOrcamento AS VARCHAR(30)) AS idLancamento
		,jsysOrcamento.dataVendido AS data
		,jsysOrcamentoItens.idProduto
		,CASE 
			WHEN jsysOrcamentoItens.quantidade > 0
				THEN jsysOrcamentoItens.quantidade
			ELSE 0
			END AS entrou
		,CASE 
			WHEN jsysOrcamentoItens.quantidade < 0
				THEN jsysOrcamentoItens.quantidade * (- 1)
			ELSE 0
			END AS saiu
		,0 AS idfornecedor
	FROM jsysOrcamento WITH (NOLOCK)
	INNER JOIN jsysOrcamentoItens WITH (NOLOCK) ON jsysOrcamentoItens.idOrcamento = jsysOrcamento.idOrcamento
	WHERE (jsysOrcamento.vendido = 1)
		AND (jsysOrcamento.cancelado = 1)
	
	UNION ALL
	
	-- TRANFERENCIAS DE ESTOQUE
	SELECT 'TRANSFERÊNCIAS' AS tabelaOrigem
		,cast(transferenciasProdutosItens.idTransf AS VARCHAR(30)) AS idLancamento
		,transferenciasProdutos.data
		,idProduto
		,CASE 
			WHEN jsysLojas.idloja = transferenciasProdutos.idloja
				THEN transferenciasProdutosItens.quantidade
			ELSE 0
			END AS [Entrou]
		,CASE 
			WHEN jsysLojas.idloja = transferenciasProdutos.idloja
				THEN 0
			ELSE transferenciasProdutosItens.quantidade
			END AS [Saiu]
		,0 AS idfornecedor
	FROM transferenciasProdutosItens WITH (NOLOCK)
	INNER JOIN transferenciasProdutos WITH (NOLOCK) ON (transferenciasProdutos.idTransf = transferenciasProdutosItens.idTransf)
	CROSS JOIN jsysLojas WITH (NOLOCK)
	WHERE (jsysLojas.ativo = 1)
		AND (transferenciasProdutos.confirmado = 1)
	
	UNION ALL
	
	-- TRANFERENCIAS DE ESTOQUE Cancelada
	SELECT 'TRANSFERÊNCIAS CANCE' AS tabelaOrigem
		,cast(transferenciasProdutosItens.idTransf AS VARCHAR(30)) AS idLancamento
		,transferenciasProdutos.data
		,idProduto
		,CASE 
			WHEN jsysLojas.idloja = transferenciasProdutos.idloja
				THEN 0
			ELSE transferenciasProdutosItens.quantidade
			END AS [Entrou]
		,CASE 
			WHEN jsysLojas.idloja = transferenciasProdutos.idloja
				THEN transferenciasProdutosItens.quantidade
			ELSE 0
			END AS [Saiu]
		,0 AS idfornecedor
	FROM transferenciasProdutosItens WITH (NOLOCK)
	INNER JOIN transferenciasProdutos WITH (NOLOCK) ON (transferenciasProdutos.idTransf = transferenciasProdutosItens.idTransf)
	CROSS JOIN jsysLojas WITH (NOLOCK)
	WHERE (jsysLojas.ativo = 1)
		AND (transferenciasProdutos.confirmado = 1)
		AND (transferenciasProdutos.cancelado = 1)
	
	UNION ALL
	
	SELECT 'DEVOLUÇÃO LOJA' AS tabelaOrigem
		,jsysDevolucaoDepositoItens.idDevolucao AS idLancamento
		,jsysDevolucaoDeposito.data
		,idProduto
		,quantidade AS [Entrou]
		,0 AS [Saiu]
		,0 AS idfornecedor
	FROM jsysDevolucaoDepositoItens WITH (NOLOCK)
	INNER JOIN jsysDevolucaoDeposito WITH (NOLOCK) ON jsysDevolucaoDeposito.idDevolucao = jsysDevolucaoDepositoItens.idDevolucao
		AND jsysDevolucaoDeposito.idLoja = jsysDevolucaoDepositoItens.idLoja
	CROSS JOIN jsysParametros WITH (NOLOCK)
	WHERE (jsysParametros.deposito = 1) AND (jsysDevolucaoDeposito.cancelado <> 1)
	) AS TB
