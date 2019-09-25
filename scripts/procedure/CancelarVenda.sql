CREATE PROCEDURE CancelarVenda (
	@idOrcamento AS INT
	,@usuario AS VARCHAR(25)
	,@motivo AS VARCHAR(max)
	)
AS
SET NOCOUNT ON

DECLARE @FLAG AS BIT = 0

IF EXISTS (
		SELECT *
		FROM jsysOrcamento
		WHERE (idOrcamento = @idOrcamento)
			AND (
				(vendido = 0)
				OR (cancelado = 1)
				)
		)
	RETURN @flag

BEGIN TRY
	-- Cancela ORÇAMENTO
	UPDATE jsysOrcamento
	SET dataCancelado = GETDATE()
		,cancelado = 1
		,obs = @motivo
		,dataAlteracao = GETDATE()
		,usuarioAlteracao = @usuario
	WHERE idOrcamento = @idOrcamento

	-- registra o cancelamento
	UPDATE jsysReceber
	SET dataCancelar = GETDATE()
		,obsCancelamento = @motivo
		,restante = 0.0
		,quitado = 0
	WHERE idReceber = @idOrcamento

	-- APAGA os pagamentos
	DELETE jsysReceberBaixa
	WHERE idReceber = @idOrcamento

	-- ATUALIZA ESTOQUE
	BEGIN TRANSACTION

	DECLARE Cur CURSOR
	FOR
	SELECT idproduto
		,quantidade
	FROM jsysOrcamentoItens WITH (NOLOCK)
	WHERE idorcamento = @idOrcamento

	OPEN Cur

	DECLARE @idproduto INT
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

	COMMIT TRANSACTION

	-- REGISTRA CANELAMENTO
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
		,'Cancelamento'
		,@usuario
		)

	SET @FLAG = 0
END TRY

BEGIN CATCH
	SET @FLAG = 1
END CATCH

RETURN @FLAG
