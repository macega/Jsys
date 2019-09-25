CREATE VIEW jsysFuncionariosBancoDeHoras
AS
SELECT id
	,CASE 
		WHEN funcionariosPonto.feriado = 1
			OR (DATEPART(DW, entrada)) = 1
			THEN isnull(DATEDIFF(mi, entrada, intervaloInicio), 0) + isnull(DATEDIFF(mi, intervaloFim, Saida), 0)
		WHEN (funcionariosPonto.atestado = 1)
			OR (funcionariosPonto.ferias = 1)
			OR (funcionariosPonto.outros = 1)
			OR (
				funcionariosPonto.falta = 1
				AND funcionariosPonto.compensacao = 0
				AND isnull(DATEDIFF(mi, entrada, intervaloInicio), 0) + isnull(DATEDIFF(mi, intervaloFim, Saida), 0) = 0
				)
			OR (funcionariosPonto.licencaMaternidade = 1)
			THEN 0
		WHEN (funcionariosPonto.avisoPrevio = 1)
			OR (DATEPART(DW, entrada)) = 7
			OR (
				funcionariosPonto.falta = 1
				AND funcionariosPonto.compensacao = 1
				)
			THEN isnull(DATEDIFF(mi, entrada, intervaloInicio), 0) + isnull(DATEDIFF(mi, intervaloFim, Saida), 0) - CASE 
					WHEN (funcionariosPonto.lactante = 1)
						THEN 180
					ELSE 240
					END
		ELSE isnull(DATEDIFF(mi, entrada, intervaloInicio), 0) + isnull(DATEDIFF(mi, intervaloFim, Saida), 0) - CASE 
				WHEN (funcionariosPonto.lactante = 1)
					THEN 420
				ELSE 480
				END
		END AS minutosExtras
	,dbo.setTimeNull(entrada) AS data
	,entrada
	,intervaloInicio
	,intervaloFim
	,intervalo15Inicio
	,intervalo15Fim
	,Saida
	,dbo.castMintosToHoras(isnull(DATEDIFF(mi, entrada, intervaloInicio), 0) + isnull(DATEDIFF(mi, intervaloFim, Saida), 0)) AS horasTrabalhadas
	,isnull(DATEDIFF(mi, entrada, intervaloInicio), 0) + isnull(DATEDIFF(mi, intervaloFim, Saida), 0) AS minutosTrabalhadas
	,funcionariosPonto.idFuncionario
	,jsysFuncionarios.nomeCliente AS Funcionario
	,funcionariosPonto.OBS
	,funcionariosPonto.feriado
	,funcionariosPonto.atestado
	,funcionariosPonto.ferias
	,funcionariosPonto.outros
	,funcionariosPonto.falta
	,funcionariosPonto.licencaMaternidade
	,funcionariosPonto.avisoPrevio
	,funcionariosPonto.lactante
	,funcionariosPonto.compensacao
	,funcionariosPonto.verificar
	,funcionariosPonto.meiaFalta
FROM funcionariosPonto WITH (NOLOCK)
INNER JOIN jsysFuncionarios WITH (NOLOCK) ON funcionariosPonto.idFuncionario = jsysFuncionarios.idFuncionario
