CREATE PROCEDURE LancaCupons (@venda int, @CpfCnpj as varchar(14)) AS
if (select count(*) from cupons WHERE venda = @venda) = 0
BEGIN
	INSERT INTO [cupons] (venda, CpfCnpj, ok)
		 VALUES (@venda ,@CpfCnpj ,0)
END ELSE 
BEGIN
	UPDATE [cupons]
	   SET CpfCnpj = @CpfCnpj, ok = 0
	WHERE venda = @venda
END