CREATE TRIGGER transferenciasProdutosGravaReplica ON transferenciasProdutos
AFTER UPDATE
	,INSERT
	,DELETE
AS
BEGIN
	IF (
			SELECT deposito
			FROM jsysLojas
			WHERE ativo = 1
			) = 0
		RETURN

	DECLARE @cod AS VARCHAR(50)
		,@codAnt AS VARCHAR(50)
		,@sd AS VARCHAR(30)
		,@idTranf AS INT
		,@ExecSP AS VARCHAR(5000)
		,@idproduto AS INT

	-- AQUI ENTRA PARA INSERIR OU ATULIZAR UM TRANFERENCIA FECHADA
	IF EXISTS (
			SELECT *
			FROM INSERTED
			WHERE [confirmado] = 1
			)
		AND EXISTS (
			SELECT *
			FROM DELETED
			)
		OR EXISTS (
			SELECT *
			FROM INSERTED
			WHERE [confirmado] = 0
			)
		AND EXISTS (
			SELECT *
			FROM DELETED
			WHERE [confirmado] = 1
			)
		OR EXISTS (
			SELECT *
			FROM INSERTED
			WHERE [confirmado] = 1
			)
		AND EXISTS (
			SELECT *
			FROM DELETED
			WHERE [confirmado] = 1
			)
	BEGIN
		--'É update'
		SELECT @codAnt = cast([idTransf] AS VARCHAR)
		FROM deleted

		SELECT @cod = cast([idTransf] AS VARCHAR)
			,@sd = [idloja]
			,@idTranf = [idTransf]
		FROM INSERTED

		IF (
				SELECT TRIGGER_NESTLEVEL()
				) > 1
			RETURN

		BEGIN
			EXEC RegistraReplicacao 'transferenciasProdutos'
				,@cod
				,@codAnt
				,'INSERT/UPDATE'
				,@sd

			-- gera produtos a serem Replicados
			DECLARE Cur CURSOR
			FOR
			SELECT idproduto
			FROM INSERTED
			INNER JOIN transferenciasProdutosItens ON (transferenciasProdutosItens.idTransf = INSERTED.idTransf)

			OPEN Cur

			FETCH NEXT
			FROM Cur
			INTO @idproduto

			WHILE @@FETCH_STATUS = 0
			BEGIN
				SET @ExecSP = 'BaixaTranferencia ' + cast(@idTranf AS VARCHAR)
				SET @codAnt = (cast(@idproduto AS VARCHAR) + '|' + cast(@idTranf AS VARCHAR))
				SET @cod = (cast(@idproduto AS VARCHAR) + '|' + cast(@idTranf AS VARCHAR))

				EXEC RegistraReplicacao 'transferenciasProdutosItens'
					,@cod
					,@codAnt
					,'INSERT/UPDATE'
					,@sd
					,@ExecSP

				FETCH NEXT
				FROM Cur
				INTO @idproduto
			END

			CLOSE Cur

			DEALLOCATE Cur
		END
	END

	-- AQUI ENTRA PARA INSERIR UM REGISTRO DE TRANFERENCIA SEM TRATAMENTO
	IF EXISTS (
			SELECT *
			FROM INSERTED
			WHERE [confirmado] = 1
			)
		AND NOT EXISTS (
			SELECT *
			FROM DELETED
			)
	BEGIN
		--'É INSERT'
		SELECT @cod = cast([idTransf] AS VARCHAR)
			,@sd = [idloja]
		FROM inserted

		EXEC RegistraReplicacao 'transferenciasProdutos'
			,@cod
			,@cod
			,'INSERT/UPDATE'
			,@sd
	END

	-- AQUI ENTRA PARA DELETEAR UM REGISTRO REMOVIDO DO SISTEMA
	IF NOT EXISTS (
			SELECT *
			FROM INSERTED
			)
		AND EXISTS (
			SELECT *
			FROM DELETED
			)
	BEGIN
		--'É DELETE'
		SELECT @cod = cast([idTransf] AS VARCHAR)
			,@sd = [idloja]
		FROM deleted

		EXEC RegistraReplicacao 'transferenciasProdutos'
			,@cod
			,''
			,'DELETE'
			,@sd
	END
END