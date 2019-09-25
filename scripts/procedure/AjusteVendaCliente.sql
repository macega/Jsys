CREATE PROCEDURE AjusteVendaCliente (
	@idcliente AS INT
	,@idOrcamento AS INT
	,@usuario AS VARCHAR(50)
	)
AS
BEGIN TRANSACTION

SET NOCOUNT ON

BEGIN
	UPDATE jsysOrcamento
	SET idCliente = @idcliente
		,dataAlteracao = getdate()
		,usuarioAlteracao = left(@usuario, 25)
	WHERE idOrcamento = @idOrcamento

	UPDATE jsysReceber
	SET idCliente = @idcliente
		,dataAlteracao = getdate()
		,usuarioAlteracao = left(@usuario, 25)
	WHERE idReceber = @idOrcamento
END

COMMIT TRANSACTION

RETURN 1
