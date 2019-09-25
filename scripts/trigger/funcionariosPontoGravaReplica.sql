CREATE TRIGGER funcionariosPontoGravaReplica ON funcionariosPonto
AFTER UPDATE
	,INSERT
	,DELETE
AS
BEGIN
	IF EXISTS (
			SELECT *
			FROM INSERTED
			)
	BEGIN
		BEGIN
			INSERT INTO Replicacao (
				tabela
				,id
				,idAntigo
				,operacao
				,camposRemovidos
				,ExecSP
				,servidorDestino
				)
			SELECT 'funcionariosPonto' --<tabela, varchar(255),>
				,CONCAT (
					inserted.cnpjCpf
					,'|'
					,inserted.data
					) --<id, varchar(100),>
				,ISNULL((
						SELECT TOP 1 CONCAT (
								deleted.cnpjCpf
								,'|'
								,deleted.data
								)
						FROM deleted
						), CONCAT (
						inserted.cnpjCpf
						,'|'
						,inserted.data
						))
				,'INSERT/UPDATE' --<operacao, varchar(30),>
				,'id' --<camposRemovidos, varchar(5000),>
				,'PontoSetColaborador ' --<ExecSP, varchar(5000),>
				,jsysClientes.idLoja --<servidorDestino, varchar(30),>
			FROM inserted
			INNER JOIN jsysClientes WITH (NOLOCK) ON inserted.idFuncionario = jsysClientes.idCliente
			INNER JOIN jsysLojas WITH (NOLOCK) ON jsysClientes.idLoja = jsysLojas.idloja
			WHERE jsysLojas.ativo = 0
		END
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
		INSERT INTO Replicacao (
			tabela
			,id
			,idAntigo
			,operacao
			,camposRemovidos
			,ExecSP
			,servidorDestino
			)
		SELECT 'funcionariosPonto' --<tabela, varchar(255),>
			,CONCAT (
				deleted.cnpjCpf
				,'|'
				,deleted.data
				) --<id, varchar(100),>
			,'' --<idAntigo, varchar(100),>
			,'DELETE' --<operacao, varchar(30),>
			,'id' --<camposRemovidos, varchar(5000),>
			,'PontoSetColaborador ' --<ExecSP, varchar(5000),>
			,jsysClientes.idLoja --<servidorDestino, varchar(30),>
		FROM deleted
		INNER JOIN jsysClientes WITH (NOLOCK) ON deleted.idFuncionario = jsysClientes.idCliente
		INNER JOIN jsysLojas WITH (NOLOCK) ON jsysClientes.idLoja = jsysLojas.idloja
		WHERE jsysLojas.ativo = 0
	END
END
