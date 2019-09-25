CREATE PROCEDURE JsysMetasFindByMes
AS
BEGIN TRANSACTION

SET NOCOUNT ON

DECLARE @RET AS INT
DECLARE @DataInicioFolhaPagamento AS INT
DECLARE @DataFimFolhaPagamento AS INT
DECLARE @DataCurrent AS DATETIME = dbo.setTimeNull(getdate())
DECLARE @dateInicial AS DATETIME = @DataCurrent
DECLARE @dateFinal AS DATETIME = @DataCurrent

SELECT @DataInicioFolhaPagamento = DataInicioFolhaPagamento
	,@DataFimFolhaPagamento = dataFimFolhaPagamento
FROM jsysParametros

IF @DataInicioFolhaPagamento <> 00
BEGIN
	IF day(@DataCurrent) < @DataInicioFolhaPagamento
	BEGIN
		SET @dateInicial = DATEFROMPARTS(year(dateadd(MONTH, - 1, @DataCurrent)), month(dateadd(MONTH, - 1, @DataCurrent)), @DataInicioFolhaPagamento)
	END
	ELSE
	BEGIN
		SET @dateFinal = DATEFROMPARTS(year(dateadd(MONTH, 1, @DataCurrent)), month(dateadd(MONTH, 1, @DataCurrent)), @DataFimFolhaPagamento)
	END
END
ELSE
BEGIN
	SET @dateInicial = dateadd(day, 1, EOMONTH(@DataCurrent, - 1))
	SET @dateFinal = EOMONTH(@DataCurrent)
END

SELECT @RET = count(*)
FROM JsysMetas
WHERE mes BETWEEN @dateInicial
		AND @dateFinal

COMMIT TRANSACTION

RETURN @RET
