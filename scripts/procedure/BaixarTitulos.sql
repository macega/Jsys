CREATE PROCEDURE BaixarTitulos (
	@idReceber AS INT -- codigo da venda
	,@seqReceber AS INT -- order de parcelas
	,@ValorPago AS DECIMAL(16, 4) -- valor pago 
	,@idTitulo AS VARCHAR(4) -- titulo de pagamento tipo dinhe, cheq, cart, deb
	,@idCaixa AS INT -- codigo do caixa que esta recebendo a parcela
	,@Usuario AS VARCHAR(30) -- usuario do sistema
	,@data AS DATETIME -- data
	,@Quitar AS BIT -- e para quitar a parcela mesmo com o valor pago menor que que valor a receber
	,@receber AS BIT -- e um recebimento ou confimar um venda a prazo = 1 vai baixar o titulo, = 0 vai salvar o caixa que esta confimando o bonus 
	)
AS
BEGIN TRANSACTION

SET NOCOUNT ON

DECLARE @idLoja AS VARCHAR(30)
	,@idReceberBaixa AS INT
	,@idSeqReceber AS VARCHAR(max)

IF EXISTS (
		SELECT count(*)
		FROM jsysReceber
		WHERE (
				idReceber = @idReceber
				AND seqReceber = @seqReceber
				)
			AND (dataCancelar IS NULL)
			AND (restante > 0)
			AND (quitado = 0)
		)
	AND (@ValorPago > 0)
	AND (@receber = 1)
BEGIN
	DECLARE @ValorTitulo AS DECIMAL(16, 4)
	DECLARE @Restante AS DECIMAL(16, 2)
	DECLARE @Quitado AS BIT

	SELECT TOP 1 @idLoja = idLoja
	FROM jsysLojas
	WHERE ativo = 1

	SET @idSeqReceber = CONCAT (
			@idReceber
			,@seqReceber
			)

	-- recupera a sequencia para inserir o registro na tabela jsysReceberBaixa
	EXECUTE @idReceberBaixa = pegaSequencia 'jsysReceberBaixa'
		,'id'
		,'CONCAT(idReceber, seqReceber)'
		,@idSeqReceber

	SELECT @ValorTitulo = jsysReceber.restante
		,@Restante = CASE 
				WHEN @Quitar = 1
					THEN 0
				ELSE (
						jsysReceber.valorTitulo - CASE 
							WHEN @ValorPago <= jsysReceber.restante
								THEN (isnull(sum(jsysReceberBaixa.valor), 0.0) + @ValorPago)
							ELSE (isnull(sum(jsysReceberBaixa.valor), 0.0) + (jsysReceber.restante - @ValorPago) + @ValorPago)
							END
						)
				END
	FROM jsysReceber
	LEFT JOIN jsysReceberBaixa ON jsysReceberBaixa.idReceber = jsysReceber.idReceber
		AND jsysReceberBaixa.seqReceber = jsysReceber.seqReceber
	WHERE jsysReceber.idReceber = @idReceber
		AND jsysReceber.seqReceber = @seqReceber
	GROUP BY jsysReceber.restante
		,jsysReceber.valorTitulo

	SET @Quitado = CASE 
			WHEN @Quitar = 1
				THEN 1
			WHEN @Restante = 0
				THEN 1
			ELSE 0
			END

	UPDATE jsysReceber
	SET idBanco = @idCaixa
		,restante = @Restante
		,quitado = @Quitado
		,dataAlteracao = GETDATE()
		,usuarioAlteracao = @Usuario
	WHERE idReceber = @idReceber
		AND seqReceber = @seqReceber

	INSERT INTO jsysReceberBaixa (
		idReceber
		,seqReceber
		,id
		,idloja
		,idBanco
		,data
		,valor
		,restante
		,valorJuros
		,idTitulo
		,quitado
		,dataInclusao
		,usuarioInclusao
		)
	VALUES (
		@idReceber -- <idReceber, bigint,>
		,@seqReceber -- <seqReceber, int,>
		,@idReceberBaixa --<id, int,>
		,@idLoja --<idloja, varchar(30),>
		,@idCaixa --<idBanco, int,>
		,@data -- <data, datetime,>
		,CASE 
			WHEN @ValorPago <= @ValorTitulo
				THEN @ValorPago
			ELSE ((@ValorTitulo - @ValorPago) + @ValorPago)
			END --<valor, decimal(16,4),>
		,@Restante -- <restante, decimal(16,4),>
		,0 --<valorJuros, decimal(16,4),>
		,@idTitulo --<idTitulo, varchar(4),>
		,@Quitado --<quitado, bit,>
		,getdate() --<dataInclusao, datetime,>
		,@Usuario --<usuarioInclusao, varchar(25),>
		)
END

IF @receber = 0
BEGIN
	UPDATE jsysOrcamento
	SET idcaixa = @idCaixa
	WHERE jsysOrcamento.idOrcamento = @idReceber

	UPDATE jsysReceber
	SET idBanco = @idCaixa
	WHERE idReceber = @idReceber
END

COMMIT TRANSACTION

RETURN 1
