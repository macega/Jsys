CREATE PROCEDURE CancelarRecebimento (
	@idReceber AS BIGINT
	,@seqReceber AS INT
	,@usuario AS VARCHAR(25)
	,@motivo AS VARCHAR(max)
	)
AS
SET NOCOUNT ON

IF NOT EXISTS (
		SELECT *
		FROM jsysReceber
		WHERE (
				idReceber = @idReceber
				AND seqReceber = @seqReceber
				)
			AND (dataCancelar IS NULL)
		)
	RETURN 0

BEGIN TRY
	UPDATE jsysOrcamento
	SET idcaixa = NULL
	WHERE idOrcamento = @idReceber

	UPDATE jsysReceber
	SET idBanco = 1
		,restante = valorTitulo
		,Descontos = 0.0
		,quitado = 0
		,dataAlteracao = GETDATE()
		,usuarioAlteracao = @Usuario
	WHERE idReceber = @idReceber
		AND seqReceber = @seqReceber

	-- deleta os pagamentos
	DELETE jsysReceberBaixa
	WHERE idReceber = @idReceber
		AND seqReceber = @seqReceber

	-- REGISTRA CANELAMENTO NA TABELA LIBERAÇAO
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
		'jsysReceber'
		,CONCAT (
			@idReceber
			,@seqReceber
			)
		,@motivo
		,@usuario
		,dbo.setTimeNull(GETDATE())
		,GETDATE()
		,'Cancelamento'
		,@usuario
		)

	RETURN 0
END TRY

BEGIN CATCH
	RETURN 1
END CATCH
