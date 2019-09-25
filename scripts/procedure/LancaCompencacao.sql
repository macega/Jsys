CREATE PROCEDURE LancaCompencacao (@Data AS DATETIME = NULL)
AS
SET NOCOUNT ON

BEGIN TRANSACTION

SET @data = isnull(@Data, getdate())

DECLARE @idFuncionario INTEGER
	,@D AS DATETIME --, @CNPJCPF AS VARCHAR(18)

SET @D = dbo.setTimeNull(@Data)

DECLARE Cur CURSOR
FOR
SELECT idFuncionario
FROM jsysFuncionarios
WHERE dbo.setTimeNull(dataAdmissaoServico) <= @D

OPEN Cur

FETCH NEXT
FROM Cur
INTO @idFuncionario

WHILE @@FETCH_STATUS = 0
BEGIN
	IF (
			SELECT count(*)
			FROM jsysPontoCompensacao
			WHERE idFuncionario = @idFuncionario
				AND convert(VARCHAR, data, 102) = convert(VARCHAR, @D, 102)
			) = 0
	BEGIN
		--SELECT @CNPJCPF = cnpjCpf from jsysClientes where idCliente = @idFuncionario
		INSERT INTO jsysPontoCompensacao (
			idFuncionario
			,data
			,periodo
			)
		VALUES (
			@idFuncionario
			,@D
			,CASE 
				WHEN (DATEPART(DW, @D)) = 1
					THEN 'DOMINGO'
				WHEN (DATEPART(DW, @D)) = 7
					THEN 'SÁBADO'
				ELSE 'NORMAL'
				END
			)
	END

	FETCH NEXT
	FROM Cur
	INTO @idFuncionario
END

CLOSE Cur

DEALLOCATE Cur

COMMIT TRANSACTION
