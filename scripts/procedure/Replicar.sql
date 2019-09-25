CREATE PROCEDURE Replicar
AS
-- declare variaves
DECLARE @idReplicacao AS INT
	,@tabela AS VARCHAR(max)
	,@id AS VARCHAR(50)
	,@idAntigo AS VARCHAR(50)
	,@operacao AS VARCHAR(20)
	,@servidorDestino AS VARCHAR(30)
	,@ExecSP AS VARCHAR(max)
	,@camposRemovidos AS VARCHAR(max)
	,@camposChave AS VARCHAR(max)

-- cria um loop para cada registro a ser replicado 
DECLARE Replicar CURSOR
FOR
SELECT idReplicacao
	,tabela
	,id
	,idAntigo
	,operacao
	,servidorDestino
	,ExecSP
	,camposRemovidos
	,camposChave
FROM Replicacao
WHERE (dataProcessado IS NULL)
ORDER BY 1

-- abre loop
OPEN Replicar

-- coloca o ponteiro no primeiro registro
FETCH NEXT
FROM Replicar
INTO @idReplicacao
	,@tabela
	,@id
	,@idAntigo
	,@operacao
	,@servidorDestino
	,@ExecSP
	,@camposRemovidos
	,@camposChave

-- verifica se ainda tem registros
WHILE @@FETCH_STATUS = 0
BEGIN
	-- pega o registro da e replica
	EXECUTE ReplicaDados @idReplicacao
		,@tabela
		,@id
		,@idAntigo
		,@operacao
		,@servidorDestino
		,@ExecSP
		,@camposRemovidos
		,@camposChave

	-- vai para o proximo registro
	FETCH NEXT
	FROM Replicar
	INTO @idReplicacao
		,@tabela
		,@id
		,@idAntigo
		,@operacao
		,@servidorDestino
		,@ExecSP
		,@camposRemovidos
		,@camposChave
END

-- fecha loop
CLOSE Replicar

DEALLOCATE Replicar
