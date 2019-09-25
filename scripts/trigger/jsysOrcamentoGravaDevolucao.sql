CREATE TRIGGER jsysOrcamentoGravaDevolucao ON jsysOrcamento
AFTER UPDATE
	,INSERT
	,DELETE
AS
BEGIN
	DECLARE @idDeposito AS INT
		,@idInsert AS VARCHAR(30)
		,@idLoja AS VARCHAR(30)

	SELECT @idDeposito = idDeposito
	FROM jsysParametros

	SELECT @idLoja = idloja
	FROM jsysLojas
	WHERE [ativo] = 1

	IF EXISTS (
			SELECT *
			FROM INSERTED
			WHERE idCliente = @idDeposito
				AND vendido = 1
			)
	BEGIN
		IF TRIGGER_NESTLEVEL() > 1
			RETURN

		BEGIN
			IF EXISTS (
					SELECT *
					FROM jsysDevolucaoDeposito WITH (NOLOCK)
					INNER JOIN inserted ON inserted.idOrcamento = jsysDevolucaoDeposito.idOrcamento
					)
			BEGIN
				UPDATE jsysDevolucaoDeposito
				SET data = inserted.dataVendido
					,idFuncionario = inserted.idFuncionario
					,cancelado = inserted.cancelado
					,dataInclusao = inserted.dataInclusao
					,usuarioInclusao = inserted.usuarioInclusao
					,dataAlteracao = inserted.dataAlteracao
					,usuarioAlteracao = inserted.usuarioAlteracao
				FROM jsysDevolucaoDeposito WITH (NOLOCK)
				INNER JOIN inserted ON inserted.idOrcamento = jsysDevolucaoDeposito.idOrcamento
			END
			ELSE
			BEGIN
				EXEC @idInsert = pegaSequencia 'jsysDevolucaoDeposito'
					,'idDevolucao'

				INSERT INTO jsysDevolucaoDeposito (
					idDevolucao
					,idLoja
					,idOrcamento
					,data
					,idFuncionario
					,cancelado
					,dataInclusao
					,usuarioInclusao
					,dataAlteracao
					,usuarioAlteracao
					)
				SELECT @idInsert --<idDevolucao, bigint,>
					,@idLoja
					,idOrcamento --<idOrcamento, int,>
					,dataVendido --<data, datetime,>
					,idFuncionario --<idFuncionario, int,>
					,cancelado --<cancelado, bit,>
					,dataInclusao --<dataInclusao, datetime,>
					,usuarioInclusao --<usuarioInclusao, varchar(25),>
					,dataAlteracao --<dataAlteracao, datetime,>
					,usuarioAlteracao --<usuarioAlteracao, varchar(25),>
				FROM inserted
			END

			UPDATE jsysDevolucaoDepositoItens
			SET [quantidade] = jsysOrcamentoItens.quantidade
			FROM inserted
			INNER JOIN jsysOrcamentoItens WITH (NOLOCK) ON jsysOrcamentoItens.idOrcamento = inserted.idOrcamento
			WHERE jsysDevolucaoDepositoItens.idProduto = jsysOrcamentoItens.idProduto
				AND jsysDevolucaoDepositoItens.idOrcamento = jsysOrcamentoItens.idOrcamento

			INSERT INTO jsysDevolucaoDepositoItens (
				idDevolucao
				,idloja
				,idOrcamento
				,idProduto
				,quantidade
				)
			SELECT jsysDevolucaoDeposito.idDevolucao
				,@idLoja
				,inserted.idOrcamento
				,jsysOrcamentoItens.idProduto
				,jsysOrcamentoItens.quantidade
			FROM inserted
			INNER JOIN jsysOrcamentoItens WITH (NOLOCK) ON inserted.idOrcamento = jsysOrcamentoItens.idOrcamento
			INNER JOIN jsysDevolucaoDeposito WITH (NOLOCK) ON inserted.idOrcamento = jsysDevolucaoDeposito.idOrcamento
			WHERE jsysOrcamentoItens.idProduto NOT IN (
					SELECT jsysDevolucaoDepositoItens.idProduto
					FROM inserted
					INNER JOIN jsysDevolucaoDepositoItens WITH (NOLOCK) ON inserted.idOrcamento = jsysDevolucaoDepositoItens.idOrcamento
					)
		END
	END

	IF NOT EXISTS (
			SELECT *
			FROM INSERTED
			WHERE idCliente = @idDeposito
			)
		AND EXISTS (
			SELECT *
			FROM DELETED
			WHERE idCliente = @idDeposito
			)
	BEGIN
		--'É DELETE'
		DELETE
		FROM jsysDevolucaoDeposito
		WHERE idOrcamento IN (
				SELECT idOrcamento
				FROM deleted
				)
	END
END
