CREATE PROCEDURE LancaFalta (
	@idFuncionario AS INT
	,@Data AS DATETIME
	)
AS
SET NOCOUNT ON

DECLARE @d AS DATETIME = dbo.setTimeNull(@Data)
	,@cnpjCpf AS VARCHAR(18)
	,@feriado AS BIT
	,@falta AS BIT
	,@vespertino AS BIT
	,@matutino AS BIT
	,@diurno AS BIT
	,@avisoDataInicial AS DATETIME
	,@avisoDataFinal AS DATETIME

IF EXISTS (
		SELECT *
		FROM jsysClientes
		WHERE (idCliente = @idFuncionario)
			AND (
				dbo.setTimeNull(@Data) BETWEEN dbo.setTimeNull(admissao)
					AND dbo.setTimeNull(isnull(avisoDataFinal, dbo.setTimeNull(getdate())))
				)
			AND (
				idLoja = (
					SELECT idloja
					FROM jsysLojas
					WHERE ativo = 1
					)
				)
		)
BEGIN
	SELECT @cnpjCpf = cnpjCpf
		,@avisoDataInicial = CASE 
			WHEN dbo.setTimeNull(@Data) BETWEEN avisoDataInicial
					AND avisoDataFinal
				THEN avisoDataInicial
			END
		,@avisoDataFinal = CASE 
			WHEN dbo.setTimeNull(@Data) BETWEEN avisoDataInicial
					AND avisoDataFinal
				THEN avisoDataFinal
			END
	FROM jsysClientes WITH (NOLOCK)
	WHERE idCliente = @idFuncionario

	SELECT @feriado = COUNT(*)
	FROM jsysFeriados WITH (NOLOCK)
	WHERE dbo.setTimeNull(jsysFeriados.dataFeriado) = @d

	SELECT @vespertino = CASE 
			WHEN periodo = 'VESPERTINO'
				THEN 1
			ELSE 0
			END
		,@matutino = CASE 
			WHEN periodo = 'MATUTINO'
				THEN 1
			ELSE 0
			END
		,@diurno = CASE 
			WHEN periodo = 'DIURNO'
				THEN 1
			ELSE 0
			END
		,@falta = CASE 
			WHEN DATEPART(DW, @D) = 1
				OR periodo = 'DIURNO'
				THEN 0
			ELSE 1
			END
	FROM jsysPontoCompensacao WITH (NOLOCK)
	WHERE data = @d
		AND idFuncionario = @idFuncionario

	IF (
			SELECT count(*)
			FROM funcionariosPonto WITH (NOLOCK)
			WHERE idFuncionario = @idFuncionario
				AND convert(VARCHAR, entrada, 102) = convert(VARCHAR, @D, 102)
			) = 0
	BEGIN
		INSERT INTO funcionariosPonto (
			data
			,cnpjCpf
			,idFuncionario
			,entrada
			,intervaloInicio
			,intervaloFim
			,Saida
			,feriado
			,atestado
			,ferias
			,outros
			,falta
			,licencaMaternidade
			,avisoPrevio
			,lactante
			,compensacao
			,verificar
			)
		VALUES (
			@D
			,@cnpjCpf
			,@idFuncionario
			,@D
			,@D
			,@D
			,@D
			,@feriado
			,0
			,0
			,0
			,CASE 
				WHEN (@feriado = 1)
					OR (@falta IS NULL)
					OR (
						@avisoDataInicial IS NOT NULL
						AND (
							(@matutino = 1)
							OR (@vespertino = 1)
							OR (@diurno = 1)
							)
						)
					THEN 0
				ELSE @falta
				END
			,0
			,CASE 
				WHEN @avisoDataInicial IS NULL
					THEN 0
				ELSE 1
				END
			,0
			,CASE 
				WHEN (@feriado = 1)
					OR (@matutino IS NULL)
					OR (@vespertino IS NULL)
					OR (@diurno IS NULL)
					THEN 0
				ELSE CASE 
						WHEN @matutino = 1
							OR @vespertino = 1
							OR @diurno = 1
							THEN 1
						ELSE 0
						END
				END
			,CASE 
				WHEN (@feriado = 1)
					AND (
						@matutino = 1
						OR @vespertino = 1
						OR @diurno = 1
						)
					THEN 1
				ELSE 0
				END
			)
	END
	ELSE
	BEGIN
		SELECT @falta = CASE 
				WHEN (
						(
							@matutino = 1
							OR @vespertino = 1
							OR DATEPART(DW, data) = 7
							OR avisoPrevio = 1
							)
						AND entrada <> intervaloInicio
						)
					OR (
						(
							@matutino = 0
							OR @vespertino = 0
							OR @diurno = 0
							)
						AND entrada <> Saida
						)
					OR (@diurno = 1)
					OR (feriado = 1)
					OR (atestado = 1)
					OR (ferias = 1)
					OR (outros = 1)
					OR (licencaMaternidade = 1)
					OR (DATEPART(DW, data) = 1)
					THEN 0
				ELSE 1
				END
			,@vespertino = CASE 
				WHEN (feriado = 1)
					OR (atestado = 1)
					OR (ferias = 1)
					OR (outros = 1)
					OR (licencaMaternidade = 1)
					OR DATEPART(DW, data) = 1
					THEN 0
				ELSE @vespertino
				END
			,@matutino = CASE 
				WHEN (feriado = 1)
					OR (atestado = 1)
					OR (ferias = 1)
					OR (outros = 1)
					OR (licencaMaternidade = 1)
					OR DATEPART(DW, data) = 1
					THEN 0
				ELSE @matutino
				END
			,@diurno = CASE 
				WHEN (feriado = 1)
					OR (atestado = 1)
					OR (ferias = 1)
					OR (outros = 1)
					OR (licencaMaternidade = 1)
					OR DATEPART(DW, data) = 1
					THEN 0
				ELSE @diurno
				END
		FROM funcionariosPonto WITH (NOLOCK)
		WHERE data = @d
			AND idFuncionario = @idFuncionario

		UPDATE funcionariosPonto
		SET compensacao = CASE 
				WHEN @diurno = 1
					OR @matutino = 1
					OR @vespertino = 1
					THEN 1
				ELSE 0
				END
			,falta = @falta
		WHERE idFuncionario = @idFuncionario
			AND data = @D
	END

	EXEC pontoAuto

	UPDATE funcionariosPonto
	SET meiaFalta = CASE 
			WHEN (
					falta = 1
					AND [entrada] IS NOT NULL
					AND intervaloInicio IS NOT NULL
					AND [intervaloFim] IS NULL
					AND [Saida] IS NULL
					)
				THEN 1
			ELSE 0
			END
	WHERE idFuncionario = @idFuncionario
		AND data = @D
END
