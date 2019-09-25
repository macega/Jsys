CREATE PROCEDURE orcamentoIncluir (
	@idfuncionario AS INT
	,@cliente AS INT
	)
AS
BEGIN TRANSACTION

SET NOCOUNT ON

DECLARE @RET AS INT

INSERT INTO [jsysOrcamento] (
	[data]
	,[idCliente]
	,[idFuncionario]
	,[vendido]
	,[cancelado]
	,[tipoVenda]
	,[dataInclusao]
	,[usuarioInclusao]
	,[reaberta]
	)
VALUES (
	GETDATE() --<data, datetime,>
	,@cliente --<idCliente, int,>
	,@idfuncionario --<idFuncionario, int,>
	,0
	,0
	,'Balcão' --<tipoVenda, varchar(60),>
	,GETDATE() --<dataInclusao, datetime,>
	,'AUTO' --<usuarioInclusao, varchar(25),>
	,0 --<reaberta, int,>
	)

SET @RET = SCOPE_IDENTITY()

COMMIT TRANSACTION

RETURN @RET
