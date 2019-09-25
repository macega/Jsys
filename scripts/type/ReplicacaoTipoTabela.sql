CREATE TYPE ReplicacaoTipoTabela AS TABLE (
	idReplicacao int,
	tabela varchar(255),
	id varchar(100),
	idAntigo varchar(100),
	operacao varchar(30),
	camposRemovidos varchar(5000),
	ExecSP varchar(5000),
	servidorDestino varchar(30),
	camposChave varchar(5000))