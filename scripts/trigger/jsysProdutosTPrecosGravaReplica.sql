CREATE TRIGGER jsysProdutosTPrecosGravaReplica ON jsysProdutosTPrecos
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
		--'É update'
		SELECT @codAnt = ([idloja] + '|' + cast([idProduto] AS VARCHAR))
		FROM deleted

		SELECT @cod = ([idloja] + '|' + cast([idProduto] AS VARCHAR))
			,@sd = [idloja]
		FROM inserted

		IF (
				SELECT TRIGGER_NESTLEVEL()
				) > 1
			RETURN

		BEGIN
			EXEC RegistraReplicacao 'jsysProdutosTPrecos'
				,@cod
				,@codAnt
				,'INSERT/UPDATE'
				,@sd
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
		--'É INSERT'
		SELECT @cod = ([idloja] + '|' + cast([idProduto] AS VARCHAR))
			,@sd = [idloja]
		FROM inserted

		EXEC RegistraReplicacao 'jsysProdutosTPrecos'
			,@cod
			,@cod
			,'INSERT/UPDATE'
			,@sd
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
		--'É DELETE'
		SELECT @cod = ([idloja] + '|' + cast([idProduto] AS VARCHAR))
			,@sd = [idloja]
		FROM deleted

		EXEC RegistraReplicacao 'jsysProdutosTPrecos'
			,@cod
			,''
			,'DELETE'
			,@sd
	END
END
