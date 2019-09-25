CREATE TRIGGER jsysClientesGravaReplica ON jsysClientes
AFTER UPDATE
	,INSERT
	,DELETE
AS
BEGIN
	-- verifica se esta executando pela 2 ves e para 
	IF (
			SELECT TRIGGER_NESTLEVEL()
			) > 1
		RETURN

	-- declara as variables
	DECLARE @table AS VARCHAR(50) = 'jsysClientes'
		,@TRT AS ReplicacaoTipoTabela
		,@comando AS NVARCHAR(max)
		,@camposRemovidos AS VARCHAR(5000) = 'idCliente'
		,@ExecSP AS VARCHAR(5000) = ''
		,@servidorDestino AS VARCHAR(30)
		,@ipServidor AS VARCHAR(30)
		,@camposChave AS VARCHAR(5000) = '<cnpjCpf/>'

	-- verifica se e o servidor executando 
	SELECT @ipServidor = idloja
	FROM jsysLojas
	WHERE deposito = 1

	-- verifica na tabela se o servidor que esta executando e o servidor central 
	-- se sim vai gravar que o registro e para todos 
	SELECT @servidorDestino = CASE 
			WHEN deposito = 1
				THEN 'ALL'
			ELSE @ipServidor
			END
	FROM jsysLojas
	WHERE ativo = 1

	-- verifica se e um insert
	IF EXISTS (
			SELECT *
			FROM inserted
			)
		AND NOT EXISTS (
			SELECT *
			FROM deleted
			)
	BEGIN
		-- limpo a tabela variavel 
		DELETE @TRT

		-- insere os registros na tabela variavel 
		INSERT INTO @TRT (
			idReplicacao
			,tabela
			,id
			,idAntigo
			,operacao
			,camposRemovidos
			,ExecSP
			,servidorDestino
			,camposChave
			)
		SELECT 0
			,@table
			,inserted.cnpjCpf
			,inserted.cnpjCpf
			,'INSERT/UPDATE'
			,@camposRemovidos
			,@ExecSP
			,@servidorDestino
			,@camposChave
		FROM inserted
		WHERE inserted.cnpjCpf <> ''

		-- aqui simplesmente da um insert nos registros que vai para a tabela replicaçao
		EXECUTE RegistraReplicacaoTable @TRT
	END

	--  SE E UM UPDATE 
	IF EXISTS (
			SELECT *
			FROM inserted
			)
		AND EXISTS (
			SELECT *
			FROM deleted
			)
	BEGIN
		-- limpo a tabela variavel 
		DELETE @TRT

		-- insere os registros na tabela variavel 
		INSERT INTO @TRT (
			idReplicacao
			,tabela
			,id
			,idAntigo
			,operacao
			,camposRemovidos
			,ExecSP
			,servidorDestino
			,camposChave
			)
		SELECT 0
			,@table
			,inserted.cnpjCpf
			,case when ISNULL(deleted.cnpjCpf, '') = '' then inserted.cnpjCpf else ISNULL(deleted.cnpjCpf, inserted.cnpjCpf) end 
			,'INSERT/UPDATE'
			,@camposRemovidos
			,@ExecSP
			,@servidorDestino
			,@camposChave
		FROM inserted
		INNER JOIN deleted ON inserted.idcliente = deleted.idCliente
		WHERE (inserted.cnpjCpf <> '')
			AND (ISNULL(inserted.dataAlteracao, inserted.dataInclusao) <> ISNULL(deleted.dataAlteracao, deleted.dataInclusao))

		-- aqui simplesmente da um insert nos registros que vai para a tabela replicaçao
		EXECUTE RegistraReplicacaoTable @TRT
	END

	-- SE E UM DELETE
	IF NOT EXISTS (
			SELECT *
			FROM inserted
			)
		AND EXISTS (
			SELECT *
			FROM deleted
			)
	BEGIN
		-- limpo a tabela variavel 
		DELETE @TRT

		-- insere os registros na tabela variavel 
		INSERT INTO @TRT (
			idReplicacao
			,tabela
			,id
			,idAntigo
			,operacao
			,camposRemovidos
			,ExecSP
			,servidorDestino
			,camposChave
			)
		SELECT 0
			,@table
			,deleted.cnpjCpf
			,''
			,'DELETE'
			,@camposRemovidos
			,@ExecSP
			,@servidorDestino
			,@camposChave
		FROM deleted
		WHERE deleted.cnpjCpf <> ''

		-- aqui simplesmente da um insert nos registros que vai para a tabela replicaçao
		EXECUTE RegistraReplicacaoTable @TRT
	END
END
