CREATE TRIGGER jsysProdutosTBarraGravaReplica ON jsysProdutosTBarra
AFTER UPDATE
	,INSERT
	,DELETE
AS
BEGIN
	DECLARE @cod AS VARCHAR(50)
		,@codAnt AS VARCHAR(50)
		,@sd AS VARCHAR(30)

	IF (
			SELECT deposito
			FROM jsysLojas
			WHERE ativo = 1
			) = 0
		RETURN

	IF EXISTS (
			SELECT *
			FROM INSERTED
			)
		AND EXISTS (
			SELECT *
			FROM DELETED
			)
	BEGIN
		IF TRIGGER_NESTLEVEL() > 0
			RETURN

		--'Update'
		SELECT @codAnt = cast([idbarra] AS VARCHAR) + '|' + cast([idProduto] AS VARCHAR)
		FROM deleted

		SELECT @cod = cast([idbarra] AS VARCHAR) + '|' + cast([idProduto] AS VARCHAR)
		FROM inserted

		BEGIN
			EXEC RegistraReplicacao 'jsysProdutosTBarra'
				,@cod
				,@codAnt
				,'INSERT/UPDATE'
				,'ALL'
		END
	END

	IF EXISTS (
			SELECT *
			FROM INSERTED
			)
		AND NOT EXISTS (
			SELECT *
			FROM DELETED
			)
	BEGIN
		--'Insert'
		SELECT @cod = cast([idbarra] AS VARCHAR) + '|' + cast([idProduto] AS VARCHAR)
		FROM inserted

		EXEC RegistraReplicacao 'jsysProdutosTBarra'
			,@cod
			,@cod
			,'INSERT/UPDATE'
			,'ALL'
	END

	IF NOT EXISTS (
			SELECT *
			FROM INSERTED
			)
		AND EXISTS (
			SELECT *
			FROM DELETED
			)
	BEGIN
		--'Delete'
		SELECT @cod = cast([idbarra] AS VARCHAR) + '|' + cast([idProduto] AS VARCHAR)
		FROM deleted

		EXEC RegistraReplicacao 'jsysProdutosTBarra'
			,@cod
			,''
			,'DELETE'
			,'ALL'
	END
END
