CREATE PROCEDURE pontoAuto
AS
SET NOCOUNT ON

UPDATE funcionariosPonto
SET entrada = CASE 
		WHEN DATEPART(DW, entrada) <> 1
			THEN dateadd(minute, Cast(10 * RAND(CAST(NEWID() AS VARBINARY)) + 0 AS INT), Dateadd(hour, 8, dbo.setTimeNull(entrada)))
		ELSE dbo.setTimeNull(entrada)
		END
	,intervaloInicio = CASE 
		WHEN DATEPART(DW, entrada) <> 1
			THEN dateadd(minute, Cast(10 * RAND(CAST(NEWID() AS VARBINARY)) + 0 AS INT), Dateadd(hour, 12, dbo.setTimeNull(entrada)))
		ELSE dbo.setTimeNull(entrada)
		END
	,intervaloFim = CASE 
		WHEN DATEPART(DW, entrada) NOT IN (
				1
				,7
				)
			THEN dateadd(minute, Cast(10 * RAND(CAST(NEWID() AS VARBINARY)) + 0 AS INT), Dateadd(hour, 14, dbo.setTimeNull(entrada)))
		ELSE dbo.setTimeNull(entrada)
		END
	,Saida = CASE 
		WHEN DATEPART(DW, entrada) NOT IN (
				1
				,7
				)
			THEN dateadd(minute, Cast(10 * RAND(CAST(NEWID() AS VARBINARY)) + 0 AS INT), Dateadd(hour, 18, dbo.setTimeNull(entrada)))
		ELSE dbo.setTimeNull(entrada)
		END
	,falta = 0
	,verificar = 0
FROM jsysFuncionarios WITH (NOLOCK)
INNER JOIN funcionariosPonto WITH (NOLOCK) ON funcionariosPonto.idFuncionario = jsysFuncionarios.idFuncionario
INNER JOIN jsysClientes WITH (NOLOCK) ON jsysClientes.idCliente = funcionariosPonto.idFuncionario
WHERE (jsysFuncionarios.dataAdmissaoServico IS NOT NULL)
	AND (cast(jsysClientes.obs AS VARCHAR) like '%AUTO%')
	AND (funcionariosPonto.falta = 1 or verificar = 1)
	AND (funcionariosPonto.feriado = 0)
	AND (funcionariosPonto.entrada = funcionariosPonto.Saida)
