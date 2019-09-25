CREATE FUNCTION dateTable (
	@dataInicial AS DATETIME
	,@dataFinal AS DATETIME
	)
RETURNS @dataTable TABLE (
	id INT
	,data DATETIME
	)
AS
BEGIN
	IF @dataInicial IS NULL
		OR @dataFinal IS NULL
		OR dbo.setTimeNull(@dataInicial) > dbo.setTimeNull(@dataFinal)
		RETURN

	DECLARE @id INT = 1

	SET @dataInicial = dbo.setTimeNull(@dataInicial)
	SET @dataFinal = dbo.setTimeNull(@dataFinal)

	WHILE (1 = 1)
	BEGIN
		INSERT INTO @dataTable (
			id
			,data
			)
		VALUES (
			@id
			,@dataInicial
			)

		IF @dataInicial = @dataFinal
			BREAK

		SET @id += 1
		SET @dataInicial = dateadd(DAY, 1, @dataInicial)
	END

	RETURN
END
