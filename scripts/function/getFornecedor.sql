CREATE FUNCTION dbo.getFornecedor ()
RETURNS @temptable TABLE (
	idProduto INT
	,idCorentista INT
	)
AS
BEGIN
	INSERT INTO @temptable (
		idProduto
		,idCorentista
		)
	SELECT jsysProdutosT.idProduto
		,jsysCompras.idCorentista
	FROM jsysProdutosT WITH (NOLOCK)
	INNER JOIN jsysComprasItens WITH (NOLOCK) ON jsysComprasItens.idProduto = jsysProdutosT.idProduto
	INNER JOIN jsysCompras WITH (NOLOCK) ON jsysCompras.idCompra = jsysComprasItens.idCompra
	INNER JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.idCliente = jsysCompras.idCorentista
	GROUP BY jsysProdutosT.idProduto
		,jsysCompras.idCorentista

	RETURN
END
