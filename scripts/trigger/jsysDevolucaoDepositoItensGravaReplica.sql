CREATE TRIGGER jsysDevolucaoDepositoItensGravaReplica ON jsysDevolucaoDepositoItens
AFTER UPDATE
	,INSERT
	,DELETE
AS
BEGIN

	IF EXISTS (
			SELECT *
			FROM jsysParametros
			WHERE deposito = 0
			)
	BEGIN
		IF EXISTS (
				SELECT *
				FROM INSERTED
				)
		BEGIN
			--'É update/insert'
			INSERT INTO Replicacao (
				tabela
				,id
				,idAntigo
				,operacao
				,camposRemovidos
				,ExecSP
				,servidorDestino
				)
			SELECT 'jsysDevolucaoDepositoItens' --<tabela, varchar(255),>
				,CONCAT (
					inserted.idDevolucao
					,'|'
					,inserted.idloja
					,'|'
					,inserted.idProduto
					) --<id, varchar(100),>
				,isnull((
						SELECT TOP 1 CONCAT (
								deleted.idDevolucao
								,'|'
								,deleted.idloja
								,'|'
								,deleted.idProduto
								)
						FROM deleted
						), CONCAT (
						inserted.idDevolucao
						,'|'
						,inserted.idloja
						,'|'
						,inserted.idProduto
						)) --<idAntigo, varchar(100),>
				,'INSERT/UPDATE' --<operacao, varchar(30),>
				,'' --<camposRemovidos, varchar(5000),>
				,CONCAT (
					'recontaEstoque '
					,inserted.idProduto
					) --<ExecSP, varchar(5000),>
				,jsysLojas.idloja --<servidorDestino, varchar(30),>
			FROM inserted
			CROSS JOIN jsysLojas WITH (NOLOCK)
			WHERE jsysLojas.deposito = 1
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
			SELECT 'jsysDevolucaoDepositoItens' --tabela
				,CONCAT (
					deleted.idDevolucao
					,'|'
					,deleted.idloja
					,'|'
					,deleted.idProduto
					) --id
				,'' --idAntigo
				,'DELETE' --operacao
				,'' --camposRemovidos
				,CONCAT (
					'recontaEstoque '
					,deleted.idProduto
					) --ExecSP
				,jsysLojas.idloja --servidorDestino
			FROM deleted
			CROSS JOIN jsysLojas WITH (NOLOCK)
			WHERE jsysLojas.deposito = 1
		END
	END
END
