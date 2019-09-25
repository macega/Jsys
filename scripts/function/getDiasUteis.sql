CREATE FUNCTION dbo.getDiasUteis (
	@dataInicio DATETIME
	,@DateFinal DATETIME
	)
RETURNS INT
AS
BEGIN
	DECLARE @Count INT

	SELECT @Count = 0

	WHILE @dataInicio <= @DateFinal
	BEGIN
		IF DatePart(WeekDay, @dataInicio) NOT IN (1)
			AND @dataInicio NOT IN (
				SELECT DBO.setTimeNull(dataFeriado)
				FROM jsysFeriados WITH (NOLOCK)
				)
			--(7,1) --And @DateStart Not In ( Select Holiday_Date from Holiday )  -- para os feriados tem que ter uma tabela
			SELECT @Count += 1

		SELECT @dataInicio = Dateadd(day, 1, @dataInicio)
	END

	RETURN @Count
END
