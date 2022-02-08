CREATE TRIGGER jsysProdutosTGravaReplica ON jsysProdutosT
AFTER UPDATE
	,INSERT
	,DELETE
AS
BEGIN
	DECLARE @cod AS VARCHAR(50)
		,@codAnt AS VARCHAR(50)
		,@sd AS VARCHAR(30)
		,@data1 AS DATETIME
		,@data2 AS DATETIME

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
		SELECT @codAnt = CAST([idProduto] AS VARCHAR)
		FROM deleted

		SELECT @cod = CAST([idProduto] AS VARCHAR)
		FROM inserted

		SELECT @data1 = dataAlteracao
		FROM deleted

		SELECT @data2 = dataAlteracao
		FROM inserted

		IF @data1 <> @data2
		BEGIN
			IF TRIGGER_NESTLEVEL() > 1
				RETURN

			EXEC RegistraReplicacao 'jsysProdutosT'
				,@cod
				,@codAnt
				,'INSERT/UPDATE'
				,'ALL'
				,''
				,'estoqueGeral'
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
		SET @cod = (
				SELECT CAST([idProduto] AS VARCHAR)
				FROM inserted
				)

		EXEC RegistraReplicacao 'jsysProdutosT'
			,@cod
			,@cod
			,'INSERT/UPDATE'
			,'ALL'
			,''
			,'estoqueGeral'
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
				SELECT CAST([idProduto] AS VARCHAR)
				FROM deleted
				)

		EXEC RegistraReplicacao 'jsysProdutosT'
			,@cod
			,''
			,'DELETE'
			,'All'
			,''
			,'estoqueGeral'
	END
END
