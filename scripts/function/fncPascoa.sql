CREATE FUNCTION fncPascoa (@ANO INT)
RETURNS DATETIME
AS
BEGIN
	DECLARE @DATA AS DATETIME
		,@MES AS INT
		,@DIA AS INT
		,@A AS INT
		,@B AS INT
		,@C AS INT
		,@D AS INT
		,@E AS INT
		,@F AS INT
		,@G AS INT
		,@H AS INT
		,@I AS INT
		,@K AS INT
		,@L AS INT
		,@M AS INT

	SET @A = @ANO % 19
	SET @B = @ANO / 100
	SET @C = @ANO % 100
	SET @D = @B / 4
	SET @E = @B % 4
	SET @F = (@B + 8) / 25
	SET @G = (@B - @F + 1) / 3
	SET @H = (19 * @A + @B - @D - @G + 15) % 30
	SET @I = @C / 4
	SET @K = @C % 4
	SET @L = (32 + 2 * @E + 2 * @I - @H - @K) % 7
	SET @M = (@A + 11 * @H + 22 * @L) / 451
	SET @MES = (@H + @L - 7 * @M + 114) / 31
	SET @DIA = ((@H + @L - 7 * @M + 114) % 31) + 1
	SET @DATA = CAST((LTRIM(RTRIM(CAST(@ANO AS CHAR)))) + '-' + (LTRIM(RTRIM(CAST(@MES AS CHAR)))) + '-' + (LTRIM(RTRIM(CAST(@DIA AS CHAR)))) AS DATETIME)

	RETURN (@DATA)
END
