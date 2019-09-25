CREATE PROCEDURE registraPonto15 (@cartao AS INT)
AS
SET NOCOUNT ON

DECLARE @idFuncionario AS INT
	,@bloqueado AS INT
	,@d AS DATETIME
	,@return AS INT
	,@ROWCOUNT AS INT
	,@tempoIntervalo AS DATETIME
	,@tempo AS DATETIME = '1900-01-01 00:00:00.000'
	,@quantIntervalo AS INT

SELECT @tempoIntervalo = tempoIntervalo
	,@quantIntervalo = quantIntervalo
FROM jsysParametros WITH (NOLOCK)

SELECT @idFuncionario = jsysClientesIds.idCliente
FROM jsysClientesIds WITH (NOLOCK)
INNER JOIN jsysFuncionarios WITH (NOLOCK) ON jsysFuncionarios.idFuncionario = jsysClientesIds.idCliente
WHERE jsysClientesIds.id = @cartao

IF (@idFuncionario is null)
	RETURN 4

IF (
		SELECT count(*)
		FROM funcionariosPonto
		INNER JOIN jsysClientes ON jsysClientes.idCliente = funcionariosPonto.idFuncionario
		WHERE (
				intervalo15Inicio IS NOT NULL
				AND intervalo15Fim IS NULL
				)
			AND funcionariosPonto.idFuncionario <> @idFuncionario
		) >= @quantIntervalo
	RETURN 2

IF (
		SELECT count(*)
		FROM funcionariosPonto
		INNER JOIN jsysClientes ON jsysClientes.idCliente = funcionariosPonto.idFuncionario
		WHERE (
				intervalo15Inicio IS NOT NULL
				AND intervalo15Fim IS NOT NULL
				)
			AND (data = dbo.setTimeNull(GETDATE()))
			AND (funcionariosPonto.idFuncionario = @idFuncionario)
		) = 1
	RETURN 3

SET @tempo = DATEADD(HOUR, DATEPART(HOUR, @tempoIntervalo), @tempo)
SET @tempo = DATEADD(MINUTE, DATEPART(MINUTE, @tempoIntervalo), @tempo)

EXEC atulizarFaltas @idFuncionario

SELECT @bloqueado = CASE 
		WHEN isnull((intervalo15Fim - intervalo15Inicio), cast('1900-01-01 00:00:00.000' AS DATETIME)) > @tempo
			THEN 1
		ELSE 0
		END
FROM funcionariosPonto WITH (NOLOCK)
INNER JOIN (
	SELECT max(funcionariosPonto.data) AS data
	FROM funcionariosPonto WITH (NOLOCK)
	INNER JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.idCliente = funcionariosPonto.idFuncionario
	INNER JOIN (
		SELECT max(data) AS data
		FROM funcionariosPonto WITH (NOLOCK)
		INNER JOIN jsysFuncionarios WITH (NOLOCK) ON jsysFuncionarios.idFuncionario = funcionariosPonto.idFuncionario
		WHERE dataAdmissaoServico IS NOT NULL
			AND funcionariosPonto.idFuncionario = @idFuncionario
		) AS tb ON funcionariosPonto.data < tb.data
	WHERE (
			funcionariosPonto.idFuncionario = @idFuncionario
			AND DATEPART(DW, funcionariosPonto.data) <> 1
			)
		AND (
			(
				funcionariosPonto.compensacao = 1
				AND funcionariosPonto.intervalo15Inicio IS NOT NULL
				AND funcionariosPonto.intervalo15Fim IS NOT NULL
				)
			OR (
				funcionariosPonto.falta <> 1
				AND funcionariosPonto.feriado <> 1
				AND funcionariosPonto.ferias <> 1
				AND funcionariosPonto.atestado <> 1
				AND funcionariosPonto.compensacao <> 1
				)
			)
	GROUP BY funcionariosPonto.idFuncionario
		,jsysClientes.nomeCorentista
	) AS tab ON funcionariosPonto.data = tab.data
WHERE (idFuncionario = @idFuncionario)

SELECT @d = data
FROM funcionariosPonto WITH (NOLOCK)
WHERE idFuncionario = @idFuncionario
	AND intervalo15Inicio IS NOT NULL
	AND intervalo15Fim IS NULL

UPDATE funcionariosPonto
SET intervalo15Inicio = CASE 
		WHEN intervalo15Inicio IS NULL
			THEN GETDATE()
		ELSE intervalo15Inicio
		END
	,intervalo15Fim = CASE 
		WHEN (intervalo15Inicio IS NOT NULL)
			AND (intervalo15Fim IS NULL)
			THEN GETDATE()
		ELSE intervalo15Fim
		END
WHERE (idFuncionario = @idFuncionario)
	AND (data = dbo.setTimeNull(isnull(@d, getdate())))
	AND (
		intervalo15Inicio IS NULL
		OR intervalo15Fim IS NULL
		)
	AND 0 = CASE 
		WHEN @bloqueado IS NULL
			THEN 0
		ELSE @bloqueado
		END

SET @ROWCOUNT = @@ROWCOUNT

/**
* -1 bloqueado
*  0 entrou
*  1 saiu
*  2 limite de funcionarios atingido
*  3 já foi para intervalo
*  4 colaborador nao encontrador
**/
SELECT @return = CASE 
		WHEN @ROWCOUNT = 0
			THEN - 1
		WHEN intervalo15Inicio IS NOT NULL
			AND intervalo15Fim IS NULL
			THEN 0
		ELSE 1
		END
FROM funcionariosPonto
WHERE (idFuncionario = @idFuncionario)
	AND (data = dbo.setTimeNull(isnull(@d, getdate())))

RETURN @return
