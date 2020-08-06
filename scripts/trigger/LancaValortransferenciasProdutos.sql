CREATE TRIGGER LancaValortransferenciasProdutos ON transferenciasProdutos
AFTER UPDATE
AS
BEGIN
	IF TRIGGER_NESTLEVEL() > 2
		RETURN

	BEGIN
		DECLARE @idTransf AS INT
			,@totalVenda AS DECIMAL(16, 4)
			,@totalCompra AS DECIMAL(16, 4)

		SELECT @idTransf = idTransf
		FROM inserted

		SELECT @totalCompra = SUM(transferenciasProdutosItens.quantidade * transferenciasProdutosItens.precoCompra)
			,@totalVenda = SUM(transferenciasProdutosItens.quantidade * transferenciasProdutosItens.precoVenda)
		FROM transferenciasProdutosItens
		WHERE transferenciasProdutosItens.idTransf = @idTransf
		GROUP BY transferenciasProdutosItens.idTransf

		UPDATE transferenciasProdutos
		SET totalVenda = CASE 
				WHEN @totalVenda IS NULL
					THEN 0
				ELSE @totalVenda
				END
			,totalCompra = CASE 
				WHEN @totalCompra IS NULL
					THEN 0
				ELSE @totalCompra
				END
		WHERE transferenciasProdutos.idTransf = @idTransf
	END
END
