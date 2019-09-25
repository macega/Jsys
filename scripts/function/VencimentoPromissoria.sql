CREATE FUNCTION VencimentoPromissoria (
	@DATA DATETIME
	,@DIAS VARCHAR(4)
	,@PARCELA INT
	)
RETURNS DATETIME

BEGIN
	DECLARE @D AS DATETIME

	IF (@DIAS = '00')
	BEGIN
		SET @D = cast(substring(CONVERT(VARCHAR, @DATA, 120), 1, 11) + '00:00:00.000' AS DATETIME)
	END

	IF (@DIAS <> '00')
	BEGIN
		SET @D = DATEADD(month, CASE 
					WHEN (substring(CONVERT(VARCHAR, @DATA, 120), 9, 2) < 15)
						THEN @PARCELA
					ELSE (@PARCELA + 1)
					END, cast(substring(CONVERT(VARCHAR, @DATA, 120), 1, 7) + '-05 00:00:00.000' AS DATETIME))
	END

	RETURN @D
END
