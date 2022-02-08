CREATE PROCEDURE removePendencia (@funcionario INT)
AS
SET NOCOUNT ON

BEGIN TRANSACTION

UPDATE funcionariosponto
SET intervalo15Fim = intervalo15Inicio
WHERE idFuncionario = @funcionario
	AND DATEDIFF(MINUTE, intervalo15Inicio, intervalo15Fim) > 15

UPDATE funcionariosPonto
SET intervalo15Inicio = GETDATE()
	,intervalo15Fim = GETDATE()
WHERE idFuncionario = @funcionario
	AND (convert(VARCHAR, entrada, 102) = convert(VARCHAR, getdate(), 102))

COMMIT TRANSACTION

RETURN 0
