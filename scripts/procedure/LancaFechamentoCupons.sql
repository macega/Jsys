CREATE PROCEDURE LancaFechamentoCupons (@cod int) AS
UPDATE cupons
   SET  ok = 1
WHERE venda = @cod