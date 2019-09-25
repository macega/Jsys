CREATE VIEW vendasMes
AS
SELECT TB.idproduto
	,TB.nomeProduto
	,TB.mes
	,TB.ano
	,SUM(TB.saida) AS totalSaida
	,SUM(TB.entrada) AS totalEntrada
	,TB.estoqueGeral
FROM (
	SELECT jsysProdutosT.idProduto
		,jsysProdutosT.[nomeProduto]
		,month(jsysOrcamento.Data) AS mes
		,year(jsysOrcamento.Data) AS ano
		,jsysOrcamentoItens.Quantidade AS saida
		,0 AS entrada
		,jsysProdutosT.estoqueGeral
	FROM jsysProdutosT WITH (NOLOCK)
	LEFT JOIN jsysOrcamentoItens WITH (NOLOCK) ON jsysOrcamentoItens.idProduto = jsysProdutosT.idProduto
	INNER JOIN jsysOrcamento WITH (NOLOCK) ON jsysOrcamento.idOrcamento = jsysOrcamentoItens.idOrcamento
	WHERE jsysOrcamento.vendido = 1
		AND jsysOrcamento.cancelado = 0
	
	UNION ALL
	
	SELECT jsysProdutosT.idProduto
		,jsysProdutosT.[nomeProduto]
		,month(jsysCompras.data) AS mes
		,year(jsysCompras.data) AS ano
		,0 AS saida
		,jsysComprasItens.quantidade AS entrada
		,jsysProdutosT.estoqueGeral
	FROM jsysProdutosT WITH (NOLOCK)
	LEFT JOIN jsysComprasItens WITH (NOLOCK) ON jsysComprasItens.idProduto = jsysProdutosT.idProduto
	INNER JOIN jsysCompras WITH (NOLOCK) ON jsysCompras.idCompra = jsysComprasItens.idCompra
	
	UNION ALL
	
	SELECT jsysProdutosT.idProduto
		,jsysProdutosT.[nomeProduto]
		,month(transferenciasProdutos.data) AS mes
		,year(transferenciasProdutos.data) AS ano
		,0 AS saida
		,transferenciasProdutosItens.quantidade AS entrada
		,jsysProdutosT.estoqueGeral
	FROM jsysProdutosT WITH (NOLOCK)
	LEFT JOIN transferenciasProdutosItens WITH (NOLOCK) ON (transferenciasProdutosItens.idProduto = jsysProdutosT.idProduto)
	INNER JOIN transferenciasProdutos WITH (NOLOCK) ON (transferenciasProdutos.idTransf = transferenciasProdutosItens.idTransf)
	WHERE transferenciasProdutos.confirmado = 1
		AND transferenciasProdutos.cancelado = 0
	) AS TB
GROUP BY TB.idProduto
	,TB.nomeProduto
	,TB.mes
	,TB.ano
	,estoqueGeral
