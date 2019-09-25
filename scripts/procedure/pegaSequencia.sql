CREATE PROCEDURE pegaSequencia (
	@tb VARCHAR(255)
	,@cp VARCHAR(255)
	,@cpCh VARCHAR(255) = ''
	,@ch VARCHAR(255) = ''
	)
AS
DECLARE @ret AS INT
	,@sql AS NVARCHAR(MAX)

SET @sql = CONCAT (
		'SELECT @x = ISNULL(MAX(cast('
		,@cp
		,' AS INT)) + 1, 1) FROM '
		,@tb
		,' '
		,CASE 
			WHEN @cpCh <> ''
				THEN CONCAT (
						'WHERE '
						,@cpCh
						,' = '''
						,@ch
						,''' '
						)
			END
		)

EXEC SP_EXECUTESQL @sql
	,N' @x int OUT '
	,@ret OUTPUT

RETURN @ret
