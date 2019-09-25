CREATE PROCEDURE BaixaTranferencia (@idTransf int) AS
SET NOCOUNT ON
DECLARE @RET AS INT
BEGIN TRY
	-- BAIXA ESTOQUE
	DECLARE Cur CURSOR FOR
	select idproduto from transferenciasProdutosItens where (idTransf = @idTransf) OPEN Cur
	DECLARE @idproduto as int
	FETCH NEXT FROM Cur INTO @idproduto
	WHILE @@FETCH_STATUS = 0
	begin 
		-- ATUALIZA O ESTOQUE GERAL
		UPDATE jsysProdutosT SET estoqueGeral = 0 WHERE (idProduto = @idproduto)
		UPDATE jsysProdutosT 
		SET estoqueGeral = TB.SALDO
		FROM (SELECT sum(entrou - saiu) AS SALDO, idProduto
				FROM  kardexAtual 
				WHERE (idProduto = @idProduto)
			GROUP BY idProduto) AS TB 
		WHERE TB.idProduto = jsysProdutosT.idProduto
	FETCH NEXT FROM Cur INTO @idproduto
	end
	CLOSE Cur
	DEALLOCATE Cur
	SET @RET = 0
END TRY 
BEGIN CATCH
	SET @RET = 1
END CATCH
RETURN @RET