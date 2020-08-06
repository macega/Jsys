CREATE TRIGGER jsysProdutosTFamiliasGravaReplica ON jsysProdutosTFamilias
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
				SELECT CAST(idFamilia AS VARCHAR)
				FROM deleted
				)
		SET @cod = (
				SELECT CAST(idFamilia AS VARCHAR)
				FROM inserted
				)

		IF (
				SELECT TRIGGER_NESTLEVEL()
				) > 1
			RETURN

		BEGIN
			EXEC RegistraReplicacao 'jsysProdutosTFamilias'
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
		SELECT @cod = CAST(idFamilia AS VARCHAR)
		FROM inserted

		EXEC RegistraReplicacao 'jsysProdutosTFamilias'
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
				SELECT CAST(idFamilia AS VARCHAR)
				FROM deleted
				)

		EXEC RegistraReplicacao 'jsysProdutosTFamilias'
			,@cod
			,''
			,'DELETE'
			,'ALL'
	END
END
