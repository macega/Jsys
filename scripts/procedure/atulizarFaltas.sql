CREATE PROCEDURE atulizarFaltas (@idFuncionario AS INT = 0)
AS
DECLARE @dataAdmissaoServico AS DATETIME

DECLARE lancadorFaltas CURSOR
FOR
SELECT dataAdmissaoServico
	,idFuncionario
FROM jsysFuncionarios WITH (NOLOCK)
WHERE (dataAdmissaoServico IS NOT NULL)
	AND (dbo.setTimeNull(dataAdmissaoServico) < dbo.setTimeNull(GETDATE()))
	AND (
		0 = @idFuncionario
		OR jsysFuncionarios.idFuncionario = @idFuncionario
		)

OPEN lancadorFaltas

FETCH NEXT
FROM lancadorFaltas
INTO @dataAdmissaoServico
	,@idFuncionario

WHILE @@FETCH_STATUS = 0
BEGIN
	DECLARE @diaFalta AS DATETIME

	DECLARE faltas CURSOR
	FOR
	SELECT dateTable.data
	FROM dbo.dateTable(@dataAdmissaoServico, dateadd(DAY, - 1, getdate())) AS dateTable
	LEFT JOIN funcionariosPonto WITH (NOLOCK) ON funcionariosPonto.data = dateTable.data
		AND @idFuncionario = funcionariosPonto.idFuncionario
	WHERE funcionariosPonto.data IS NULL
		OR funcionariosPonto.falta IS NULL

	OPEN faltas

	FETCH NEXT
	FROM faltas
	INTO @diaFalta

	WHILE @@FETCH_STATUS = 0
	BEGIN
		EXEC lancaCompencacao @diaFalta

		EXEC LancaFalta @idFuncionario
			,@diaFalta

		FETCH NEXT
		FROM faltas
		INTO @diaFalta
	END

	CLOSE faltas

	DEALLOCATE faltas

	FETCH NEXT
	FROM lancadorFaltas
	INTO @dataAdmissaoServico
		,@idFuncionario
END

CLOSE lancadorFaltas

DEALLOCATE lancadorFaltas
