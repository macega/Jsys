CREATE FUNCTION jsysSequencia (
	@tb VARCHAR(40)
	,@cp VARCHAR(40)
	)
RETURNS INT

BEGIN
	DECLARE @id INT
		,@ExecSP NVARCHAR(max) = N''

	SET @ExecSP = CONCAT (
			'select @id = isnull(max('
			,@cp
			,') + 1, 1) from '
			,@tb
			)

	EXEC SP_EXECUTESQL @ExecSP

	RETURN @id
END
