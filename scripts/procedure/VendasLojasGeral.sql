CREATE PROCEDURE [dbo].[VendasLojasGeral] (
	@dataInicial DATETIME
	,@dataFinal DATETIME
	)
AS
DECLARE @Srv SYSNAME
	,@query NVARCHAR(MAX)
	,@nomeBancoDadosRemoto SYSNAME

DECLARE cServer CURSOR
FOR
SELECT sys.servers.name, jsysLojas.nomeBancoDados
FROM SYS.SERVERS INNER JOIN jsysLojas ON jsysLojas.idloja = sys.servers.name 
WHERE is_linked = 1
ORDER BY NAME

SET @query = N''

OPEN cServer

FETCH NEXT
FROM cServer
INTO @Srv, @nomeBancoDadosRemoto

WHILE @@FETCH_STATUS = 0
BEGIN
	BEGIN TRY
		EXEC sp_testlinkedserver @Srv

		SET @query = @query + N'UNION ALL SELECT ROW_NUMBER() OVER(ORDER BY idTitulo) as posicao, sum(totalVendas) AS totalVendas, sum(totalBruto) AS totalBruto, sum(totalLiqudo) AS totalLiqudo, sum(totalDesconto) AS totalDesconto, idTitulo, AVG(mediaDesconto) AS mediaDesconto, fantasia, TB.totalGeral ' + 
		'FROM [' + @Srv + '].['+@nomeBancoDadosRemoto+'].DBO.jsysVendasLojas  WITH (NOLOCK)' + 
		'cross join (SELECT sum(totalLiqudo) AS totalGeral FROM [' + @Srv + '].['+@nomeBancoDadosRemoto+'].DBO.jsysVendasLojas  WITH (NOLOCK) WHERE (data between ''' + convert(VARCHAR, @dataInicial, 120) + ''' AND ''' + convert(VARCHAR, @dataFinal, 120) + ''')) as TB ' + 
		'WHERE (data between ''' + convert(VARCHAR, @dataInicial, 120) + ''' AND ''' + convert(VARCHAR, @dataFinal, 120) + ''') ' + 
		'GROUP BY idTitulo, fantasia, TB.totalGeral '
	END TRY

	BEGIN CATCH
		SET @query = @query + N'UNION ALL SELECT ROW_NUMBER() OVER(ORDER BY idloja) as posicao, 0 AS totalVendas ' + ',0 AS totalBruto ' + ',0 as totalLiqudo ' + ',0 as totalDesconto ' + ',''ERRO OFF-LINE'' as idTitulo ' + ',0 as mediaDesconto ' + ',jsysLojas.nomeLoja + '' OFFLINE'' as fantasia ' + ',0 as totalGeral ' + 'FROM jsysLojas where idloja = ''' + @Srv + ''' '
	END CATCH

	FETCH NEXT
	FROM cServer
	INTO @Srv, @nomeBancoDadosRemoto
END

CLOSE cServer

DEALLOCATE cServer

SET @query = @query + N'UNION ALL SELECT ROW_NUMBER() OVER(ORDER BY idTitulo) as posicao, sum(totalVendas) AS totalVendas, sum(totalBruto) AS totalBruto, sum(totalLiqudo) AS totalLiqudo, sum(totalDesconto) AS totalDesconto, idTitulo, AVG(mediaDesconto) AS mediaDesconto, fantasia, TB.totalGeral ' + 'FROM jsysVendasLojas ' + 'cross join (SELECT sum(totalLiqudo) AS totalGeral FROM jsysVendasLojas WHERE (data between ''' + convert(VARCHAR, @dataInicial, 120) + ''' AND ''' + convert(VARCHAR, @dataFinal, 120) + ''')) as TB ' + 'WHERE (data between ''' + convert(VARCHAR, @dataInicial, 120) + ''' AND ''' + convert(VARCHAR, @dataFinal, 120) + ''') ' + 'GROUP BY idTitulo, fantasia, TB.totalGeral ' + 'ORDER BY 8, 6 '
SET @query = substring(@query, 10, len(@query))

EXEC SP_EXECUTESQL @query

RETURN
GO