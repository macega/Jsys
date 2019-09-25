CREATE PROCEDURE LancaDescontoProdutosVenda (@idOrcamento AS INT)
AS
DECLARE @dif AS DECIMAL(13, 2)
	,@saldo AS DECIMAL(13, 2)
	,@rowns AS INT
	,@id AS INT = 1

SET NOCOUNT ON

-- lança desconto dos produtos
UPDATE jsysOrcamentoItens
SET desconto = CASE 
		WHEN jsysOrcamento.valorDesconto <> 0
			THEN round((jsysOrcamento.valorDesconto / jsysOrcamento.valorBruto) * jsysOrcamentoItens.totalProduto, 2)
		ELSE 0
		END
FROM jsysOrcamentoItens
INNER JOIN jsysOrcamento ON jsysOrcamento.idOrcamento = jsysOrcamentoItens.idOrcamento
WHERE (jsysOrcamentoItens.idOrcamento = @idOrcamento)

-- verifica se existe diferenca entre a soma do desconto dos produtos e o desconto da venda
IF EXISTS (
		SELECT count(*)
		FROM jsysOrcamento
		INNER JOIN jsysOrcamentoItens ON jsysOrcamento.idOrcamento = jsysOrcamentoItens.idOrcamento
		WHERE jsysOrcamento.idOrcamento = @idOrcamento
		GROUP BY jsysOrcamento.valorDesconto
			,jsysOrcamento.valorBruto
		HAVING sum(jsysOrcamentoItens.desconto) <> jsysOrcamento.valorDesconto
			AND sum(jsysOrcamentoItens.totalProduto) = jsysOrcamento.valorBruto
		)
BEGIN
	SELECT @dif = sum(jsysOrcamentoItens.desconto) - jsysOrcamento.valorDesconto
		,@rowns = count(jsysOrcamentoItens.idproduto)
	FROM jsysOrcamento
	INNER JOIN jsysOrcamentoItens ON jsysOrcamento.idOrcamento = jsysOrcamentoItens.idOrcamento
	WHERE jsysOrcamento.idOrcamento = @idOrcamento
	GROUP BY jsysOrcamento.valorDesconto

	SET @saldo = @dif

	WHILE @saldo <> 0
	BEGIN
		UPDATE jsysOrcamentoItens
		SET desconto = (
				jsysOrcamentoItens.desconto - CASE 
					WHEN @dif > 0
						THEN 0.01
					ELSE - 0.01
					END
				)
			,@saldo -= CASE 
				WHEN @dif > 0
					THEN 0.01
				ELSE - 0.01
				END
		FROM (
			SELECT ROW_NUMBER() OVER (
					ORDER BY idorcamento
					) AS id
				,jsysOrcamentoItens.idProduto
				,jsysOrcamentoItens.desconto
			FROM jsysOrcamentoItens
			WHERE idOrcamento = @idOrcamento
			) AS tb
		WHERE tb.id = @id
			AND tb.idProduto = jsysOrcamentoItens.idProduto
			AND jsysOrcamentoItens.idOrcamento = @idOrcamento
			AND jsysOrcamentoItens.totalProduto > jsysOrcamentoItens.desconto

		IF (@id < @rowns)
			SET @id += 1
		ELSE
			SET @id = 1
	END
END
