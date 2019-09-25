CREATE FUNCTION GETKEY (@tabela VARCHAR(max))
RETURNS NVARCHAR(max)

BEGIN
	DECLARE @camposKeys AS NVARCHAR(max)

	-- SELECIONA AS CHAVES PRIMARIA DA TABELA
	SELECT @camposKeys = (
			SELECT DISTINCT TB.NAME
			FROM (
				SELECT c.NAME
				FROM sys.objects t
				INNER JOIN sys.columns c ON t.object_id = c.object_id
				INNER JOIN sys.index_columns ic ON t.object_id = ic.object_id
					AND ic.column_id = c.column_id
				INNER JOIN sys.indexes i ON t.object_id = i.object_id
					AND i.index_id = ic.index_id
				WHERE (t.NAME = @tabela)
					AND (
						CASE 
							WHEN t.type_desc = 'VIEW'
								THEN i.is_unique
							ELSE i.is_primary_key
							END = 1
						)
				
				UNION ALL
				
				SELECT NAME
				FROM sys.sysforeignkeys
				INNER JOIN sys.syscolumns parent_columns ON fkeyid = id
					AND fkey = colid
				WHERE OBJECT_NAME(fkeyid) = @tabela
				) AS TB
			ORDER BY 1
			FOR XML RAW('T')
			)

	RETURN CONCAT (
			'CONCAT('
			,REPLACE(REPLACE(REPLACE(@camposKeys, '<T name="', '['), '"/>', ']'), '][', '], ''|'', [')
			,', '''')'
			)
END
