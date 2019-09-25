CREATE TRIGGER jsysSubContaGravaReplica ON jsysSubConta
AFTER UPDATE
	,INSERT
	,DELETE
AS
BEGIN
	IF (
			SELECT TRIGGER_NESTLEVEL()
			) > 1
		RETURN

	DECLARE @table AS VARCHAR(50) = 'jsysSubConta'
		,@TRT AS ReplicacaoTipoTabela
		,@comando AS NVARCHAR(max)
		,@operacao AS VARCHAR(15)
		,@camposRemovidos AS VARCHAR(5000) = ''
		,@ExecSP AS VARCHAR(5000) = ''
		,@servidorDestino AS VARCHAR(30)
		,@ipServidor AS VARCHAR(30)

	SELECT @ipServidor = idloja
	FROM jsysLojas
	WHERE deposito = 1

	SELECT @servidorDestino = CASE 
			WHEN deposito = 1
				THEN 'ALL'
			ELSE @ipServidor
			END
	FROM jsysLojas
	WHERE ativo = 1

	IF EXISTS (
			SELECT *
			FROM INSERTED
			)
	BEGIN
		IF (
				SELECT ISNULL(dataAlteracao, dataInclusao)
				FROM inserted
				) = (
				SELECT ISNULL(dataAlteracao, dataInclusao)
				FROM deleted
				)
			RETURN

		SET @operacao = 'INSERT/UPDATE'

		DELETE
		FROM @TRT

		INSERT INTO @TRT (
			idReplicacao
			,tabela
			,id
			,idAntigo
			,operacao
			,camposRemovidos
			,ExecSP
			,servidorDestino
			)
		SELECT 0
			,@table
			,CONCAT (
				[idConta]
				,'|'
				,[idSubConta]
				,''
				)
			,CONCAT (
				[idConta]
				,'|'
				,[idSubConta]
				,''
				)
			,@operacao
			,@camposRemovidos
			,@ExecSP
			,@servidorDestino
		FROM inserted

		EXECUTE RegistraReplicacaoTable @TRT
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
		SET @operacao = 'DELETE'

		DELETE
		FROM @TRT

		INSERT INTO @TRT (
			idReplicacao
			,tabela
			,id
			,idAntigo
			,operacao
			,camposRemovidos
			,ExecSP
			,servidorDestino
			)
		SELECT 0
			,@table
			,CONCAT (
				[idConta]
				,'|'
				,[idSubConta]
				,''
				)
			,''
			,@operacao
			,@camposRemovidos
			,@ExecSP
			,@servidorDestino
		FROM deleted

		EXECUTE RegistraReplicacaoTable @TRT
	END
END
