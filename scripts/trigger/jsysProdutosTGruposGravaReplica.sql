CREATE TRIGGER jsysProdutosTGruposGravaReplica ON jsysProdutosTGrupos
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
		SET @codAnt = (
				SELECT CAST([idGrupo] AS VARCHAR)
				FROM deleted
				)
		SET @cod = (
				SELECT CAST([idGrupo] AS VARCHAR)
				FROM inserted
				)

		IF (
				SELECT TRIGGER_NESTLEVEL()
				) > 1
			RETURN

		BEGIN
			EXEC RegistraReplicacao 'jsysProdutosTGrupos'
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
		--'É INSERT'
		SELECT @cod = CAST([idGrupo] AS VARCHAR)
		FROM inserted

		EXEC RegistraReplicacao 'jsysProdutosTGrupos'
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
		--'É DELETE'
		SET @cod = (
				SELECT CAST([idGrupo] AS VARCHAR)
				FROM deleted
				)

		EXEC RegistraReplicacao 'jsysProdutosTGrupos'
			,@cod
			,''
			,'DELETE'
			,'ALL'
	END
END