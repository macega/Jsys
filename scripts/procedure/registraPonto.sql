CREATE PROCEDURE registraPonto (@cartao AS INT)
AS
SET NOCOUNT ON

DECLARE @CNPJCPF AS VARCHAR(18)
	,@idFuncionario AS INT
	,@periodo AS VARCHAR(10)
	,@avisoPrevio AS BIT

SELECT @idFuncionario = jsysClientesIds.idCliente
	,@CNPJCPF = cnpjCpf
	,@avisoPrevio = CASE 
		WHEN dbo.setTimeNull(getdate()) BETWEEN avisoDataInicial
				AND avisoDataFinal
			THEN 1
		ELSE 0
		END
FROM jsysClientesIds WITH (NOLOCK)
INNER JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.idCliente = jsysClientesIds.idCliente
WHERE jsysClientesIds.id = @cartao

EXEC lancaCompencacao

SELECT @periodo = periodo
FROM jsysPontoCompensacao WITH (NOLOCK)
WHERE (idFuncionario = @idFuncionario)
	AND (data = dbo.setTimeNull(getdate()))

IF (
		(@idFuncionario <> 0)
		AND (@cartao <> 0)
		AND (@CNPJCPF <> '')
		AND (@periodo <> 'DIURNO')
		)
BEGIN
	EXEC atulizarFaltas @idFuncionario

	IF (
			SELECT count(*)
			FROM funcionariosPonto WITH (NOLOCK)
			WHERE idFuncionario = @idFuncionario
				AND data = dbo.setTimeNull(getdate())
			) = 0
	BEGIN
		INSERT INTO funcionariosPonto (
			data
			,entrada
			,idFuncionario
			,cnpjCpf
			,avisoPrevio
			)
		VALUES (
			dbo.setTimeNull(getdate())
			,getdate()
			,@idFuncionario
			,@CNPJCPF
			,@avisoPrevio
			)
	END
	ELSE IF (
			SELECT COUNT(*)
			FROM funcionariosPonto WITH (NOLOCK)
			WHERE (idFuncionario = @idFuncionario)
				AND (data = dbo.setTimeNull(getdate()))
				AND (intervaloInicio IS NULL)
			) = 1
		OR (
			@periodo NOT IN (
				'MATUTINO'
				,'VESPERTINO'
				)
			)
	BEGIN
		UPDATE funcionariosPonto
		SET intervaloInicio = CASE 
				WHEN intervaloInicio IS NULL
					THEN GETDATE()
				ELSE intervaloInicio
				END
			,intervaloFim = CASE 
				WHEN (intervaloInicio <> '')
					AND (intervaloFim IS NULL)
					THEN GETDATE()
				ELSE intervaloFim
				END
			,Saida = CASE 
				WHEN (intervaloInicio <> '')
					AND (intervaloFim <> '')
					AND (Saida IS NULL)
					THEN GETDATE()
				ELSE Saida
				END
		WHERE idFuncionario = @idFuncionario
			AND data = dbo.setTimeNull(getdate())
	END
END

RETURN @@ROWCOUNT
