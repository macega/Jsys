CREATE PROCEDURE PacoteEntrega (@idOrcamento int) AS
BEGIN
UPDATE jsysOrcamento
   SET [entregue] = 1
 WHERE idOrcamento = @idOrcamento
END