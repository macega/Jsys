CREATE FUNCTION produtosEstoqueData (
	@produto INT
	,@data AS DATETIME
	)
RETURNS DECIMAL(16, 4)
AS
BEGIN
	DECLARE @q DECIMAL(16, 4)

	SELECT @q = tb.saldo
	FROM (
		SELECT idProduto
			,SUM(entrou - saiu) AS saldo
		FROM kardexAtual WITH (NOLOCK)
		WHERE (idProduto = @produto)
			AND (data <= @data)
		GROUP BY idProduto
		) AS tb

	RETURN (ISNULL(@q, 0))
END
