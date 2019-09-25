CREATE PROCEDURE RegistraReplicacaoTable @TRT
AS
ReplicacaoTipoTabela READONLY AS

SET NOCOUNT ON

	INSERT INTO Replicacao (
		tabela
		,id
		,idAntigo
		,operacao
		,camposRemovidos
		,ExecSP
		,servidorDestino
		,camposChave
		)
	SELECT tabela
		,id
		,idAntigo
		,operacao
		,camposRemovidos
		,ExecSP
		,servidorDestino
		,camposChave
	FROM @TRT