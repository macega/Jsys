CREATE FUNCTION castMintosToHoras (@MINUTOS INT)
RETURNS DATETIME

BEGIN
	DECLARE @iHoras INTEGER
		,@iMinutos INTEGER
		,@valor DATETIME

	SET @iHoras = CAST(ROUND(@MINUTOS / 60, 0) AS INT)
	SET @iMinutos = @MINUTOS % 60
	SET @valor = '1999-12-31 00:00:00.000'
	SET @valor = DATEADD(hour, @iHoras, @valor)
	SET @valor = DATEADD(minute, @iMinutos, @valor)

	RETURN @valor
END
