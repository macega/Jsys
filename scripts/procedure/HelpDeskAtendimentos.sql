CREATE PROCEDURE HelpDeskAtendimentos AS
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
        SET @Comando = @Comando + N'UNION ALL SELECT id ' +
			',data as datahora ' +
			',dbo.setTimeNull(data) as data ' +
			',jsysLojas.nomeLoja as loja ' +
			',usuario ' +
			',cast(obs as varchar(max)) AS obs ' +
			',tipo ' +
			',fechado ' +
			',atendente ' +
			',cast(obsFechamento as varchar(max)) AS obsFechamento ' +
			',dataFechamento ' +
			',jsysParametros.fantasia ' +
			',case when DATEDIFF(day, dbo.setTimeNull(DATA), getDate()) = 0 then ''HOJE'' ELSE CAST(DATEDIFF(day, dbo.setTimeNull(DATA), getDate()) AS VARCHAR) + '' DIAS'' END AS dias ' + 
			'FROM [' + @Srv + '].DADOS.DBO.helpDesk with (nolock)'+
			'inner join jsysLojas with (nolock) on jsysLojas.idloja = helpDesk.idloja '+
			'cross join jsysParametros with (nolock) ' +
			'WHERE fechado = 0 '
    END TRY 
    BEGIN CATCH 
        SET @Comando = @Comando + N'UNION ALL SELECT 0 id ' +
			',GETDATE() AS datahora ' +
			',GETDATE() AS data ' +
			',''' + @Srv + ''' AS nomeLoja ' +
			',''ERRO OFF-LINE'' AS usuario ' +
			',''ERRO OFF-LINE'' AS obs ' +
			',''ERRO OFF-LINE'' AS tipo ' +
			',0 AS fechado ' +
			',''ERRO OFF-LINE'' AS atendente ' +
			',''ERRO OFF-LINE'' AS obsFechamento ' +
			',GETDATE() AS dataFechamento ' +
			',''ERRO OFF-LINE'' AS fantasia ' + 
			',''OFF'' AS dias '
    END CATCH
    FETCH NEXT FROM cServer INTO @Srv
END 
CLOSE cServer 
DEALLOCATE cServer
SET @Comando = @Comando + N'UNION ALL SELECT id ' +
			',data as datahora ' +
			',dbo.setTimeNull(data) as data ' +
			',jsysLojas.nomeLoja as loja ' +
			',usuario ' +
			',cast(obs as varchar(max)) AS obs' +
			',tipo ' +
			',fechado ' +
			',atendente ' +
			',cast(obsFechamento as varchar(max)) AS obsFechamento' +
			',dataFechamento ' +
			',jsysParametros.fantasia ' +
			',case when DATEDIFF(day, dbo.setTimeNull(DATA), getDate()) = 0 then ''HOJE'' ELSE CAST(DATEDIFF(day, dbo.setTimeNull(DATA), getDate()) AS VARCHAR) + '' DIAS'' END AS dias ' + 
			'FROM helpDesk with (nolock) '+
			'inner join jsysLojas with (nolock) on jsysLojas.idloja = helpDesk.idloja '+
			'cross join jsysParametros with (nolock) ' +
			'WHERE fechado = 0 ' +
			'GROUP BY id, data, jsysLojas.nomeLoja, usuario, cast(obs as varchar(max)), tipo, fechado, atendente, cast(obsFechamento as varchar(max)), dataFechamento, jsysParametros.fantasia ' +
			'ORDER BY datahora, nomeLoja, tipo '
set @Comando = substring(@Comando, 10, len(@Comando))
EXEC SP_EXECUTESQL @Comando
RETURN
