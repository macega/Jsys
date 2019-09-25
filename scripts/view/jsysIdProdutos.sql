CREATE VIEW jsysIdProdutos
AS
SELECT TOP (100) PERCENT TB.codigoBarra
	,TB.idProduto
FROM (
	SELECT cast(idbarra AS VARCHAR) AS codigoBarra
		,jsysProdutosTBarra.idProduto
	FROM jsysProdutosTBarra WITH (NOLOCK)
	INNER JOIN jsysProdutosT WITH (NOLOCK) ON jsysProdutosTBarra.idProduto = jsysProdutosT.idProduto
	WHERE jsysProdutosT.Inativo = 0
	
	UNION ALL
	
	SELECT cast(idProduto AS VARCHAR) AS codigoBarra
		,idProduto
	FROM jsysProdutosT WITH (NOLOCK)
	WHERE jsysProdutosT.Inativo = 0
	) AS TB
GROUP BY TB.codigoBarra
	,TB.idProduto
