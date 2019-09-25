CREATE FUNCTION Split (
	@String VARCHAR(max)
	,@Delimiter CHAR(1)
	)
RETURNS @temptable TABLE (
	itens VARCHAR(max)
	,id INT
	)
AS
BEGIN
	DECLARE @idx INT
	DECLARE @slice VARCHAR(max)
	DECLARE @id INT

	SET @id = 1

	SELECT @idx = 1

	IF len(@String) < 1
		OR @String IS NULL
		RETURN

	WHILE @idx != 0
	BEGIN
		SET @idx = charindex(@Delimiter, @String)

		IF @idx != 0
			SET @slice = left(@String, @idx - 1)
		ELSE
			SET @slice = @String

		IF (len(@slice) > 0)
			INSERT INTO @temptable (
				itens
				,id
				)
			VALUES (
				@slice
				,@id
				)

		SET @id = @id + 1
		SET @String = right(@String, len(@String) - @idx)

		IF len(@String) = 0
			BREAK
	END

	RETURN
END
