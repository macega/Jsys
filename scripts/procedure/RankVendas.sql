CREATE PROCEDURE RankVendas(@dataInicial datetime, @dataFinal datetime) AS
DECLARE @Srv sysname ,@Comando NVARCHAR(MAX)

DECLARE cServer CURSOR FOR 
SELECT name 
  FROM SYS.SERVERS 
 WHERE is_linked = 1 
  ORDER BY Name
  SET @Comando = N''

OPEN cServer 
FETCH NEXT FROM cServer INTO @Srv 
WHILE @@FETCH_STATUS = 0 
BEGIN 
    BEGIN TRY 
        EXEC sp_testlinkedserver @Srv 
        SET @Comando = @Comando + N'UNION ALL SELECT ROW_NUMBER() OVER(ORDER BY idFuncionario) as posicao, idFuncionario, nomeCorentista, SUM(valorLiquido) AS valorLiquido, nomeLoja ' +
						'FROM [' + @Srv + '].DADOS.DBO.jsysListaVendasVendedor ' +
						'WHERE (Data between '''+ convert(varchar, @dataInicial, 120) + ''' AND ''' + convert(varchar, @dataFinal, 120) + ''') ' +
						'GROUP BY idFuncionario, nomeCorentista, nomeLoja '
    END TRY 
    BEGIN CATCH 
        SET @Comando = @Comando + N'UNION ALL SELECT 1 as posicao, 0 AS idFuncionario ' +
							',jsysLojas.nomeLoja + '' OFFLINE'' as nomeCorentista ' +
							',0 AS valorLiquido ' + 
							',jsysLojas.nomeLoja ' +
							'FROM jsysLojas where idloja = ''' + @Srv + ''' '  
    END CATCH
    FETCH NEXT FROM cServer INTO @Srv
END 
CLOSE cServer 
DEALLOCATE cServer
SET @Comando = @Comando + N'UNION ALL SELECT ROW_NUMBER() OVER(ORDER BY idFuncionario) as posicao, idFuncionario, nomeCorentista, SUM(valorLiquido) AS valorLiquido, nomeLoja ' +
						'FROM DADOS.DBO.jsysListaVendasVendedor ' +
						'WHERE (Data between '''+ convert(varchar, @dataInicial, 120) + ''' AND ''' + convert(varchar, @dataFinal, 120) + ''') ' +
						'GROUP BY idFuncionario, nomeCorentista, nomeLoja  ' +
						'ORDER BY nomeLoja, valorLiquido DESC '
set @Comando = substring(@Comando, 10, len(@Comando))
PRINT @Comando
EXEC SP_EXECUTESQL @Comando
RETURN