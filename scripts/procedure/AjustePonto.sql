CREATE PROCEDURE AjustePonto (@corentista int
							 ,@data datetime 
							 ,@obs VARCHAR(MAX)) AS
BEGIN
DECLARE @D as datetime, @CNPJCPF AS VARCHAR(18)
SET @D = dbo.setTimeNull(@data)
	IF (@obs IN ('FERIAS', 'LICENÇA-MATERNIDADE')) 
		BEGIN
			IF (SELECT COUNT(*) FROM funcionariosPonto WHERE data = @D and idFuncionario = @corentista) = 1
				UPDATE funcionariosPonto
					SET entrada = @D
						,intervaloInicio = @D
						,intervaloFim = @D
						,Saida = @D
						,ferias = case when @obs = 'FERIAS' then 1 else 0 end
						,licencaMaternidade = case when @obs = 'LICENÇA-MATERNIDADE' then 1 else 0 end
					WHERE data = @D and idFuncionario = @corentista
			ELSE 
				SELECT @CNPJCPF = cnpjCpf from jsysClientes WHERE idCliente = @corentista
				INSERT INTO funcionariosPonto
					(data
					,entrada
					,intervaloInicio
					,intervaloFim
					,Saida
					,idFuncionario
					,cnpjCpf
					,ferias
					,licencaMaternidade)
				VALUES
					(@D
					,@D
					,@D
					,@D
					,@D
					,@corentista
					,@CNPJCPF
					,case when @obs = 'FERIAS' then 1 else 0 end
					,case when @obs = 'LICENÇA-MATERNIDADE' then 1 else 0 end)
		END 
	ELSE IF (@obs IN ('AVISO-PRÉVIO', 'LACTANTE')) 
		BEGIN
			UPDATE funcionariosPonto
			   SET avisoPrevio = case when @obs = 'AVISO-PRÉVIO' then 1 else 0 end
			      ,lactante = case when @obs = 'LACTANTE' then 1 else 0 end
			 WHERE data = @D and idFuncionario = @corentista
		END
END