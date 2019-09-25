CREATE PROCEDURE RegistraReplicacao (
	@tabela VARCHAR(255)
	,@idRegistro VARCHAR(50)
	,@idRegistroAntigo VARCHAR(50)
	,@operacao VARCHAR(30)
	,@servidorDestino VARCHAR(30)
	,@ExecSP VARCHAR(5000) = ''
	,@camposRemovidos VARCHAR(5000) = ''
	)
AS
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
	VALUES (
		@tabela --<tabela, varchar(255),>
		,@idRegistro --<id, varchar(100),>
		,@idRegistroAntigo --<idAntigo, varchar(100),>
		,@operacao --<operacao, varchar(30),>
		,@camposRemovidos --<camposRemovidos, varchar(5000),>
		,@ExecSP --<ExecSP, varchar(5000),>
		,@servidorDestino --<servidorDestino, varchar(30),>
		)
END
