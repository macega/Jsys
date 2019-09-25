CREATE PROCEDURE ListaCompencacao(@dataInicial datetime, @dataFinal datetime) AS
 --- Separando os valores: Passo 1
declare @values as nvarchar(max)

SELECT  @values = STUFF(
	(SELECT top 100 percent '],[' + CAST(day(DATA) AS VARCHAR) FROM jsysPontoCompensacao 
	WHERE data between @dataInicial and @dataFinal
	GROUP BY day(DATA)
	ORDER BY day(DATA) FOR XML PATH('')), 1, 2, '') + ']'

--- Esqueleto da query com os marcadores:
declare @query as nvarchar(max)
set @query = 'Select Codigo, Funcionarios, Horas, ' + @values + ' ' +
             'from (Select jsysPontoCompensacao.idFuncionario as Codigo ' +
							',jsysFuncionarios.nomeCliente as Funcionarios ' +
							',CASE WHEN totalMinutosExtra IS NULL THEN ''00:00'' ELSE cast(totalMinutosExtra / 60 as varchar)+'':''+cast(totalMinutosExtra % 60 as varchar) END AS Horas ' +
							',day(jsysPontoCompensacao.data) dia ' +
							',jsysPontoCompensacao.periodo ' +
					  'from jsysPontoCompensacao left join jsysFuncionariosBancoDeHorasGeral on jsysFuncionariosBancoDeHorasGeral.idFuncionario = jsysPontoCompensacao.idFuncionario  ' + 
					  'inner join jsysFuncionarios on jsysFuncionarios.idFuncionario = jsysPontoCompensacao.idFuncionario '+ 
					  'where data between '''+convert(varchar, @dataInicial, 120)+''' and '''+convert(varchar, @dataFinal, 120)+''') PT ' +
             'PIVOT (max(periodo) for dia in (' + @values + ')) pvt '
--- Executando a query
--- print @query
EXEC SP_EXECUTESQL @query
RETURN