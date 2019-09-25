CREATE PROCEDURE ReabrirVenda (
	@idOrcamento AS INT
	,@usuario AS VARCHAR(25)
	,@motivo AS VARCHAR(max)
	)
AS
SET NOCOUNT ON

IF EXISTS (
		SELECT *
		FROM jsysOrcamento
		WHERE (idOrcamento = @idOrcamento)
			AND (vendido = 0)
			AND (cancelado = 0)
		)
	RETURN 0

-- ABRE ORÇAMENTO
UPDATE jsysOrcamento
SET dataVendido = NULL
	,vendido = 0
	,cancelado = 0
	,valorDesconto = 0
	,dataCancelado = NULL
	,idcaixa = NULL
	,reaberta += 1
	,usuarioAlteracao = @usuario
	,dataAlteracao = GETDATE()
WHERE idOrcamento = @idOrcamento

-- APAGA DESCONTO DOS ITENS
UPDATE jsysOrcamentoItens
set desconto = 0
WHERE idOrcamento = @idOrcamento

-- APAGA PARCELAS
DELETE jsysReceber
WHERE idReceber = @idOrcamento

-- ATUALIZA ESTOQUE
DECLARE Cur CURSOR
FOR
SELECT idproduto
	,quantidade
FROM jsysOrcamentoItens
WHERE idorcamento = @idOrcamento

OPEN Cur

DECLARE @idproduto AS INT
	,@quantidade AS DECIMAL(16, 4)

FETCH NEXT
FROM Cur
INTO @idproduto
	,@quantidade

WHILE @@FETCH_STATUS = 0
BEGIN
	UPDATE jsysProdutosT
	SET estoqueGeral += @quantidade
	WHERE idProduto = @idproduto

	FETCH NEXT
	FROM Cur
	INTO @idproduto
		,@quantidade
END

CLOSE Cur

DEALLOCATE Cur

-- REGISTRA REABERTURA
INSERT INTO Liberacao (
	tabelaOrigem
	,idOrigem
	,motivo
	,usuario
	,data
	,hora
	,tipo
	,usuarioLogado
	)
VALUES (
	'Vendas'
	,@idOrcamento
	,@motivo
	,@usuario
	,dbo.setTimeNull(GETDATE())
	,GETDATE()
	,'Reabertura'
	,@usuario
	)

RETURN 1
