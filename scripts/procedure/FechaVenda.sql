CREATE PROCEDURE FechaVenda (
	@idfuncionario AS INT
	,@idcliente AS INT
	,@DataMaquina AS DATETIME
	,@idOrcamento AS INT
	,@valorBruto AS DECIMAL(16, 4)
	,@valorLiquido AS DECIMAL(16, 4)
	,@valorDesconto AS DECIMAL(16, 4)
	,@docEntrada AS VARCHAR(4)
	,@docRestante AS VARCHAR(4)
	,@valorEntrada AS DECIMAL(16, 4)
	,@valorRestante AS DECIMAL(16, 4)
	,@formaPagto AS VARCHAR(255)
	,@usuario AS VARCHAR(25)
	,@tipoVenda AS VARCHAR(60)
	,@ValorDevolvido AS DECIMAL(16, 4)
	,@ficha AS INT
	)
AS
BEGIN TRANSACTION

SET NOCOUNT ON

DECLARE @FLAG AS BIT
DECLARE @PARCELA AS VARCHAR(10)
DECLARE @Data AS DATETIME
DECLARE @Vip AS BIT
DECLARE @idLoja AS VARCHAR(30)

SET @Vip = (
		SELECT Vip
		FROM jsysClientes
		WHERE idCliente = @idcliente
		)
SET @Data = getdate()

SELECT @idLoja = idloja
FROM jsysLojas
WHERE ativo = 1

-- FECHA ORÇAMENTO
BEGIN
	UPDATE jsysOrcamento
	SET data = dbo.setTimeNull(@Data) --<data, datetime,>
		,idCliente = @idcliente --<idCliente, int,>
		,idFuncionario = @idfuncionario --<idFuncionario, int,>
		,valorBruto = CASE 
			WHEN @Vip = 0
				THEN @valorBruto
			ELSE 0
			END --<valorBruto, decimal(16,4),>
		,valorDesconto = CASE 
			WHEN @Vip = 0
				THEN @valorDesconto
			ELSE 0
			END --<valorDesconto, decimal(16,4),>
		,valorLiquido = CASE 
			WHEN @Vip = 0
				THEN @valorLiquido
			ELSE 0
			END --<valorLiquido, decimal(16,4),>
		,valorDevolvido = CASE 
			WHEN @Vip = 0
				THEN @ValorDevolvido
			ELSE 0
			END --<valorDevolvido, decimal(16,4),>
		,cancelado = 0 --<cancelado, bit,>
		,vendido = 1 --<vendido, bit,>
		,dataVendido = @Data --<dataVendido, datetime,>
		,idcaixa = NULL
		,obs = '' --<obs, text,>
		,idTituloEntrada = @docEntrada --<idTituloEntrada, varchar(4),>
		,idTituloRestante = @docRestante --<idTituloRestante, varchar(4),>
		,valorEntrada = CASE 
			WHEN @Vip = 0
				THEN @valorEntrada
			ELSE 0
			END --<valorEntrada, decimal(16,4),>
		,valorRestante = CASE 
			WHEN @Vip = 0
				THEN (@valorLiquido - @valorEntrada)
			ELSE 0
			END --<valorRestante, decimal(16,4),>
		,formaPagto = @formaPagto --<formaPagto, varchar(255),>
		,tipoVenda = @tipoVenda --<tipoVenda, varchar(60),>
		,dataAlteracao = @Data --<dataAlteracao, datetime,>
		,usuarioAlteracao = @usuario --<usuarioAlteracao, varchar(25),>
		,ficha = CASE 
			WHEN @Vip = 0
				THEN @ficha
			ELSE 0
			END
		,entregue = CASE 
			WHEN @Vip = 0
				THEN 0
			ELSE 1
			END
	WHERE idOrcamento = @idOrcamento --<Critérios de Pesquisa,,>
END

-- PARCELAS
DECLARE @int AS INT
DECLARE @loop AS INT
DECLARE @valor AS DECIMAL(16, 4)

SET @int = 1

SELECT @loop = cast(q_parcela AS INT)
FROM Plano_pagto
WHERE desc_Plano = @formaPagto

WHILE (@int <= @loop)
BEGIN
	IF (@Vip = 0)
	BEGIN
		SELECT @PARCELA = ITENS
		FROM Split(@formaPagto, '/')
		WHERE ID = @int

		SET @valor = CASE 
				WHEN @int = 1
					THEN @valorEntrada
				ELSE @valorRestante
				END

		INSERT INTO [dbo].[jsysReceber] (
			[idReceber]
			,[seqReceber]
			,[idloja]
			,[idTitulo]
			,[idCliente]
			,[dataEmissao]
			,[dataVencimento]
			,[valorTitulo]
			,[restante]
			,[Descontos]
			--,[dataCancelar]
			--,[obsCancelamento]
			,[idBanco]
			,[quitado]
			,[idContabil]
			,[ficha]
			,[dataInclusao]
			,[usuarioInclusao]
			)
		--,[dataAlteracao]
		--,[usuarioAlteracao]
		VALUES (
			@idOrcamento --<idReceber, bigint,>
			,@int --<seqReceber, int,>
			,@idLoja --<idloja, varchar(30),>
			,CASE 
				WHEN @int = 1
					THEN @docEntrada
				ELSE @docRestante
				END --<idTitulo, varchar(4),>
			,@idcliente --<idCliente, int,>
			,dbo.setTimeNull(@Data) --<dataEmissao, datetime,>
			,dbo.VencimentoPromissoria(@Data, @PARCELA, @int) --<dataVencimento, datetime,>
			,@valor --<valorTitulo, decimal(16,4),>
			,@valor --<restante, decimal(16,4),>
			,@valorDesconto / @loop -- <Descontos, decimal(16,4),>
			--,<dataCancelar, datetime,>
			--,<obsCancelamento, text,>
			,'01' --<idBanco, int,>
			,0 --<quitado, bit,>
			,CASE 
				WHEN @int = 1
					THEN '1.1'
				ELSE '1.2'
				END --<idContabil, varchar(5),>
			,@ficha --<ficha, int,>
			,getdate() --<dataInclusao, datetime,>
			,@usuario -- <usuarioInclusao, varchar(25),>
			)
	END

	SET @int = @int + 1
END

COMMIT TRANSACTION

-- ATUALIZA ESTOQUE
DECLARE Cur CURSOR
FOR
SELECT idproduto
FROM jsysOrcamentoItens
WHERE idorcamento = @idOrcamento

OPEN Cur

DECLARE @idproduto AS INT

FETCH NEXT
FROM Cur
INTO @idproduto

WHILE @@FETCH_STATUS = 0
BEGIN
	UPDATE jsysProdutosT
	SET estoqueGeral = (jsysProdutosT.estoqueGeral - jsysOrcamentoItens.quantidade)
	FROM jsysProdutosT
	INNER JOIN jsysOrcamentoItens ON jsysProdutosT.idProduto = jsysOrcamentoItens.idProduto
	WHERE (jsysProdutosT.idProduto = @idproduto)
		AND (jsysOrcamentoItens.idOrcamento = @idOrcamento)

	-- FIM
	FETCH NEXT
	FROM Cur
	INTO @idproduto
END

CLOSE Cur

DEALLOCATE Cur

-- lança desconto dos produtos
EXEC LancaDescontoProdutosVenda @idOrcamento

--UPDATE jsysOrcamentoItens
--SET desconto = CASE 
--		WHEN jsysOrcamento.valorDesconto <> 0
--			THEN round((jsysOrcamento.valorDesconto / jsysOrcamento.valorBruto) * jsysOrcamentoItens.totalProduto, 2)
--		ELSE 0
--		END
--FROM jsysOrcamentoItens
--INNER JOIN jsysOrcamento ON jsysOrcamento.idOrcamento = jsysOrcamentoItens.idOrcamento
--WHERE (jsysOrcamentoItens.idOrcamento = @idOrcamento)
SET @FLAG = 1

RETURN @FLAG
