CREATE VIEW funcionariosPontoMesAtual
AS
SELECT [id]
	,[entrada]
	,[intervaloInicio]
	,[intervaloFim]
	,[intervalo15Inicio]
	,[intervalo15Fim]
	,[Saida]
	,[funcionariosPonto].[idFuncionario]
	,jsysClientes.nomeCorentista AS Funcionario
	,[funcionariosPonto].[obs]
FROM [funcionariosPonto] WITH (NOLOCK)
INNER JOIN jsysClientes WITH (NOLOCK) ON funcionariosPonto.idFuncionario = jsysClientes.idCliente
WHERE Month(entrada) = Month(getdate())
	AND year(entrada) = year(getdate())
