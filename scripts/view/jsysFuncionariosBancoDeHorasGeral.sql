CREATE VIEW jsysFuncionariosBancoDeHorasGeral
AS
SELECT idFuncionario
	,Funcionario
	,SUM(minutosExtras) AS totalMinutosExtra
FROM jsysFuncionariosBancoDeHoras WITH (NOLOCK)
WHERE (data < dbo.setTimeNull(getdate()) - 1)
GROUP BY idFuncionario
	,Funcionario
