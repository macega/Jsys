package br.adp.script;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juliano Alves Medina
 */
public class Update {

    public static List<String[]> update(Integer vercaoAtulizacao) {
        List<String[]> script = new ArrayList<>();
        if (vercaoAtulizacao == 2) {
            script.add(new String[]{"CREATE TABLE jsysNFeLote "
                + "	( "
                + "	id bigint NOT NULL IDENTITY (1, 1), "
                + "	idLote varchar(15) NOT NULL "
                + "	)  ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE jsysNFeLote ADD CONSTRAINT "
                + "	PK_jsysNFeLote PRIMARY KEY CLUSTERED  "
                + "	( "
                + "	id "
                + "	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE jsysNFeLote SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"CREATE TABLE jsysNFe ( "
                + "	nfe_id bigint IDENTITY(1,1) NOT NULL,  "
                + "	venda int NOT NULL, "
                + "	chaveAcesso varchar(50) NOT NULL,     	    "
                + "	indSinc bit not null,  "
                + "    cUF varchar(2) not null, "
                + "    cNF varchar(8) not null, "
                + "    natOp varchar(60) not null, "
                + "    indPag varchar(1) not null, "
                + "    serie varchar(3) not null, "
                + "    nNF varchar(9) not null, "
                + "    dhEmi datetime not null,      "
                + "    dhSaiEnt datetime not null,     "
                + "    tpNF bit not null, "
                + "	idDest varchar(1) not null, "
                + "    cMunFG varchar(7) not null, "
                + "    tpImp varchar(1) not null, "
                + "    tpEmis varchar(1) not null, "
                + "    cDV varchar(1) not null, "
                + "    tpAmb varchar(1) not null, "
                + "    finNFe varchar(1) not null, "
                + "	indFinal varchar(1) not null, "
                + "	indPres varchar(1) not null, "
                + "	mod int not null, "
                + "	emitida bit NOT NULL, "
                + "	enviNFe xml NULL, "
                + "	retConsReciNFe xml NULL "
                + " CONSTRAINT [PK_jsysNFe] PRIMARY KEY CLUSTERED  "
                + "( "
                + "	nfe_id ASC "
                + ")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY] "
                + ") ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE jsysParametros  "
                + " ADD nNFCeserie VARCHAR(3) NULL "
                + "	,crt VARCHAR(1) NULL "
                + "	,infCpl VARCHAR(5000) NULL "
                + "	,caminhoDoCertificadoDoCliente VARCHAR(5000) NULL "
                + "	,senhaDoCertificadoDoCliente VARCHAR(60)", ""});
            script.add(new String[]{"ALTER TABLE jsysParametros SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"UPDATE jsysParametros "
                + "SET nNFCeserie = 1 "
                + "	,crt = 1 "
                + "	,infCpl = 'DOCUMENTO EMITIDO POR ME OPTANTE PELO SIMPLES NACIONAL. CONFORME LEI COMPLEMENTAR 123, NAO GERA DIREITO A CREDITO FISCAL DE ICMS, ISS E IPI.' "
                + "	,caminhoDoCertificadoDoCliente = 'certificados/certEmpresa.pfx' "
                + "	,senhaDoCertificadoDoCliente = '12345'", ""});
            script.add(new String[]{"UPDATE jsysClientes "
                + "SET cnpjcpf = '' "
                + "WHERE cnpjcpf IN ( "
                + "		'000.000.000-00' "
                + "		,'111.111.111-11' "
                + "		,'222.222.222-22' "
                + "		,'333.333.333-33' "
                + "		,'444.444.444-44' "
                + "		,'555.555.555-55' "
                + "		,'666.666.666-66' "
                + "		,'777.777.777-77' "
                + "		,'888.888.888-88' "
                + "		,'999.999.999-99' "
                + "		,'00.000.000/0000-00' "
                + "		,'00,.000.0' "
                + "		,'000.000.0' "
                + "		)", ""});
            script.add(new String[]{"CREATE TABLE jsysNFeInut "
                + "	( "
                + "	id bigint NOT NULL IDENTITY (1, 1), "
                + "	idInut varchar(43) NOT NULL, "
                + "	tpAmb varchar(1) NOT NULL, "
                + "	xServ varchar(10) NOT NULL, "
                + "	cUF varchar(2) NOT NULL, "
                + "	ano varchar(2) NOT NULL, "
                + "	cnpj varchar(14) NOT NULL, "
                + "	mod varchar(2) NOT NULL, "
                + "	serie varchar(3) NOT NULL, "
                + "	nNFIni varchar(9) NOT NULL, "
                + "	nNFFin varchar(9) NOT NULL, "
                + "	xJust varchar(255) NOT NULL, "
                + "	emitida bit NOT NULL, "
                + "	xmlInutNFe xml NULL, "
                + "	xmlRetInutNFe xml NULL) ON [PRIMARY] "
                + "	 TEXTIMAGE_ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE jsysNFeInut ADD CONSTRAINT "
                + "	PK_jsysNFeInut PRIMARY KEY CLUSTERED  "
                + "	(id) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE jsysNFeInut SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 3) {
            script.add(new String[]{"DROP VIEW jsysTitulos", ""});
            script.add(new String[]{"CREATE TABLE jsysTitulos ( "
                + "	idTitulo VARCHAR(4) NOT NULL "
                + "	,tipoFatura VARCHAR(25) NOT NULL "
                + "	,idcupom VARCHAR(2) NOT NULL "
                + "	,ativo BIT NOT NULL "
                + "	,controlarCompensacao BIT NOT NULL "
                + "	,tefChequeEletronico SMALLINT NOT NULL "
                + "	,tipoTitulo VARCHAR(14) NOT NULL "
                + "	,idBanco VARCHAR(2) NOT NULL "
                + "	,tipoSped VARCHAR(2) NOT NULL "
                + "	,usaPdv BIT NOT NULL "
                + "	,aPrazo BIT NOT NULL "
                + "	,dscCupom VARCHAR(16) NOT NULL) ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE jsysTitulos SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"EXECUTE sp_bindefault N'defNada',N'jsysTitulos.idTitulo'", ""});
            script.add(new String[]{"EXECUTE sp_bindefault N'defNada',N'jsysTitulos.tipoFatura'", ""});
            script.add(new String[]{"EXECUTE sp_bindefault N'defNada',N'jsysTitulos.idcupom'", ""});
            script.add(new String[]{"EXECUTE sp_bindefault N'defZero',N'jsysTitulos.ativo'", ""});
            script.add(new String[]{"EXECUTE sp_bindefault N'defZero',N'jsysTitulos.controlarCompensacao'", ""});
            script.add(new String[]{"EXECUTE sp_bindefault N'defZero',N'jsysTitulos.tefChequeEletronico'", ""});
            script.add(new String[]{"EXECUTE sp_bindefault N'defNada',N'jsysTitulos.tipoTitulo'", ""});
            script.add(new String[]{"EXECUTE sp_bindefault N'defNada',N'jsysTitulos.idBanco'", ""});
            script.add(new String[]{"EXECUTE sp_bindefault N'defNada',N'jsysTitulos.tipoSped'", ""});
            script.add(new String[]{"EXECUTE sp_bindefault N'defZero',N'jsysTitulos.usaPdv'", ""});
            script.add(new String[]{"EXECUTE sp_bindefault N'defZero',N'jsysTitulos.aPrazo'", ""});
            script.add(new String[]{"EXECUTE sp_bindefault N'defNada',N'jsysTitulos.dscCupom'", ""});
            script.add(new String[]{"IF EXISTS (SELECT * FROM [T�tulos]) "
                + "	EXEC ('INSERT INTO jsysTitulos ( "
                + "	idTitulo, "
                + "	tipoFatura, "
                + "	idcupom, "
                + "	ativo, "
                + "	controlarCompensacao, "
                + "	tefChequeEletronico, "
                + "	tipoTitulo, "
                + "	idBanco, "
                + "	tipoSped, "
                + "	usaPdv, "
                + "	aPrazo, "
                + "	dscCupom) "
                + "SELECT [C�digo t�tulo] "
                + "	,Tipo_fatura "
                + "	,Cod_cupom "
                + "	,Ativo "
                + "	,[Controlar compensacao] "
                + "	,Tef_cheque_eletronico "
                + "	,Tipo_titulo "
                + "	,Cod_banco "
                + "	,Tit_sped "
                + "	,Usa_no_pdv "
                + "	,Aprazo "
                + "	,dsc_cupom "
                + "FROM T�tulos WITH (HOLDLOCK TABLOCKX)')", ""});
            script.add(new String[]{"DROP TABLE [T�tulos]", ""});
            script.add(new String[]{"ALTER TABLE jsysTitulos ADD CONSTRAINT pkIdTitulo PRIMARY KEY CLUSTERED (idTitulo) WITH ( "
                + " PAD_INDEX = OFF ,FILLFACTOR = 90 ,STATISTICS_NORECOMPUTE = OFF ,IGNORE_DUP_KEY = OFF "
                + " ,ALLOW_ROW_LOCKS = ON ,ALLOW_PAGE_LOCKS = ON ) ON [PRIMARY]", ""});
            script.add(new String[]{"CREATE NONCLUSTERED INDEX [pkTipoFatura] ON jsysTitulos (tipoFatura) "
                + "	WITH (PAD_INDEX = OFF "
                + "	,FILLFACTOR = 90 "
                + "	,STATISTICS_NORECOMPUTE = OFF "
                + "	,IGNORE_DUP_KEY = OFF "
                + "	,ALLOW_ROW_LOCKS = ON "
                + "	,ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]", ""});

            script.add(new String[]{"ALTER TABLE jsysTitulos ADD "
                + "	tipoPagamento varchar(40) NULL, "
                + "	card bit NULL, "
                + "	cnpjCredenciadora varchar(14) NULL, "
                + "	tipoBandeira varchar(40) NULL", ""});
            script.add(new String[]{"EXECUTE sp_unbindefault N'jsysTitulos.controlarCompensacao'", ""});
            script.add(new String[]{"EXECUTE sp_unbindefault N'jsysTitulos.tefChequeEletronico'", ""});
            script.add(new String[]{"EXECUTE sp_unbindefault N'jsysTitulos.tipoTitulo'", ""});
            script.add(new String[]{"EXECUTE sp_unbindefault N'jsysTitulos.idBanco'", ""});
            script.add(new String[]{"EXECUTE sp_unbindefault N'jsysTitulos.tipoSped'", ""});
            script.add(new String[]{"EXECUTE sp_unbindefault N'jsysTitulos.usaPdv'", ""});
            script.add(new String[]{"EXECUTE sp_unbindefault N'jsysTitulos.aPrazo'", ""});
            script.add(new String[]{"EXECUTE sp_unbindefault N'jsysTitulos.dscCupom'", ""});
            script.add(new String[]{"ALTER TABLE jsysTitulos "
                + "	DROP COLUMN controlarCompensacao, tefChequeEletronico, tipoTitulo, idBanco, tipoSped, usaPdv, aPrazo, dscCupom", ""});
            script.add(new String[]{"ALTER TABLE jsysTitulos SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"update jsysTitulos set tipoPagamento = '', card = 0, cnpjCredenciadora = '', tipoBandeira = ''", ""});
        }
        if (vercaoAtulizacao == 4) {
            script.add(new String[]{"CREATE TABLE NCM (id bigint NOT NULL IDENTITY (1, 1), codigo varchar(15) NOT NULL, "
                + "ex varchar(5) NULL, tipo varchar(5) NULL, descricao varchar(max) NOT NULL, nacionalfederal decimal(18, 4) NULL, "
                + "importadosfederal decimal(18, 4) NULL, estadual decimal(18, 4) NULL, "
                + "	municipal decimal(18, 4) NULL, vigenciainicio datetime NULL, vigenciafim datetime NULL, "
                + "	chave varchar(30) NULL, versao varchar(30) NULL, fonte varchar(15) NULL )  ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE NCM ADD CONSTRAINT PK_NCM PRIMARY KEY CLUSTERED ( id "
                + "	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE NCM SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"DROP TABLE jsysRegistrosNCM", ""});
        }
        if (vercaoAtulizacao == 5) {
            script.add(new String[]{"ALTER TABLE jsysParametros ADD "
                + "	cIdToken varchar(6) NULL, "
                + "	CSC varchar(36) NULL", ""});
            script.add(new String[]{"ALTER TABLE jsysParametros SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"DROP TABLE [dbo].[Par�metros]", ""});
            script.add(new String[]{"update JsysPlanoPagto set docEntrada = 'PROM' where docEntrada <> 'DINH'", ""});
            script.add(new String[]{"update JsysPlanoPagto set docRestante = 'PROM' where docRestante <> 'DINH'", ""});
        }
        if (vercaoAtulizacao == 6) {
            script.add(new String[]{"ALTER TABLE jsysParametros ADD utilizarNfce bit NULL", ""});
            script.add(new String[]{"ALTER TABLE jsysParametros SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"update jsysParametros set utilizarNfce = 0", ""});
        }
        if (vercaoAtulizacao == 7) {
            script.add(new String[]{"CREATE TABLE jsysNFeEvento "
                + "	(id bigint NOT NULL IDENTITY (1, 1), "
                + "	idEvento varchar(54) NOT NULL, "
                + "	cOrgao varchar(2) NOT NULL, "
                + "	tpAmb varchar(1) NOT NULL, "
                + "	chNFe varchar(44) NOT NULL, "
                + "	dhEvento datetime NOT NULL, "
                + "	tpEvento varchar(6) NOT NULL, "
                + "	nSeqEvento int NOT NULL, "
                + "	descEvento varchar(60) NOT NULL, "
                + "	nProt varchar(15) NOT NULL, "
                + "	xJust varchar(255) NOT NULL, "
                + "	emitida bit NULL, "
                + "	envEventoCancNFe xml NULL, "
                + "	retEnvEventoCancNFe xml NULL) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE jsysNFeEvento ADD CONSTRAINT "
                + "	PK_jsysNFeEvento PRIMARY KEY CLUSTERED "
                + "	(id) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE jsysNFeEvento SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 8) {
            script.add(new String[]{"ALTER TABLE jsysNFe ADD cancelada bit NULL", ""});
            script.add(new String[]{"ALTER TABLE jsysNFe SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"UPDATE jsysNFe SET cancelada = 0", ""});
        }
        if (vercaoAtulizacao == 10) {
            script.add(new String[]{"UPDATE jsysNFe "
                + "SET [cancelada] = 0 "
                + "WHERE [cancelada] IS NULL", ""});
        }
        if (vercaoAtulizacao == 11) {
            script.add(new String[]{"ALTER TABLE jsysParametros ADD idConsumidorFinal int NULL", ""});
            script.add(new String[]{"ALTER TABLE jsysParametros SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"UPDATE jsysParametros SET idConsumidorFinal = 1", ""});
        }
        if (vercaoAtulizacao == 12) {
            script.add(new String[]{"update jsysParametros set nNFeserie = cast(nNFeserie as int)", ""});
        }
        if (vercaoAtulizacao == 13) {
            script.add(new String[]{"ALTER TABLE jsysNFe ADD procNFe xml NULL", ""});
            script.add(new String[]{"ALTER TABLE jsysNFe SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 14) {
            script.add(new String[]{"ALTER TABLE jsysParametros DROP COLUMN NfeTipoAmbiente", ""});
            script.add(new String[]{"ALTER TABLE jsysParametros SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 15) {
            script.add(new String[]{"ALTER TABLE jsysNFeEvento ADD procEventoNFe xml NULL", ""});
            script.add(new String[]{"ALTER TABLE jsysNFeEvento SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 16) {
            script.add(new String[]{"ALTER TABLE jsysParametros ADD logo varchar(5000) NULL", ""});
            script.add(new String[]{"ALTER TABLE jsysParametros SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 17) {
            script.add(new String[]{"if exists (select * from sysobjects where id = object_id('[jsysDevolucaoDepositoGravaReplica]')) "
                + "  DROP TRIGGER [dbo].[jsysDevolucaoDepositoGravaReplica]", ""});
            script.add(new String[]{"if exists (select * from sysobjects where id = object_id('[jsysDevolucaoDepositoItensGravaReplica]')) "
                + "  DROP TRIGGER [dbo].[jsysDevolucaoDepositoItensGravaReplica]", ""});
            script.add(new String[]{"CREATE TABLE Tmp_jsysDevolucaoDeposito "
                + "	(idDevolucao bigint NOT NULL, "
                + " idLoja VARCHAR(30) NOT NULL, "
                + "	idOrcamento int NOT NULL, "
                + "	data datetime NOT NULL, "
                + "	idFuncionario int NOT NULL, "
                + "	cancelado bit NOT NULL, "
                + "	dataInclusao datetime NOT NULL, "
                + "	usuarioInclusao varchar(25) NOT NULL, "
                + "	dataAlteracao datetime NULL, "
                + "	usuarioAlteracao varchar(25) NULL) ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE Tmp_jsysDevolucaoDeposito SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"EXECUTE sp_bindefault N'defaultFalse', N'Tmp_jsysDevolucaoDeposito.cancelado'", ""});
            script.add(new String[]{"IF EXISTS (SELECT * FROM jsysDevolucaoDeposito) "
                + "	EXEC ('INSERT INTO Tmp_jsysDevolucaoDeposito (idDevolucao, idLoja, idOrcamento, data, idFuncionario, cancelado, dataInclusao, usuarioInclusao, dataAlteracao, usuarioAlteracao) "
                + "	SELECT CONVERT(BIGINT, RIGHT(idDevolucao, len(idDevolucao) - charindex(''|'', idDevolucao))), CASE WHEN charindex(''|'', idDevolucao) = 0 THEN jsyslojas.idloja ELSE LEFT(idDevolucao, charindex(''|'', idDevolucao) - 1) END,idOrcamento,data,idFuncionario,cancelado,dataInclusao,usuarioInclusao,dataAlteracao,usuarioAlteracao FROM jsysDevolucaoDeposito WITH (HOLDLOCK TABLOCKX) CROSS JOIN jsyslojas WHERE (jsyslojas.ativo = 1)')", ""});
            script.add(new String[]{"ALTER TABLE jsysDevolucaoDepositoItens "
                + "	DROP CONSTRAINT FK_jsysDevolucaoDepositoItens", ""});
            script.add(new String[]{"DROP TABLE jsysDevolucaoDeposito", ""});
            script.add(new String[]{"EXECUTE sp_rename N'Tmp_jsysDevolucaoDeposito', N'jsysDevolucaoDeposito', 'OBJECT' ", ""});
            script.add(new String[]{"ALTER TABLE jsysDevolucaoDeposito ADD CONSTRAINT "
                + "	PK_jsysDevolucaoDeposito PRIMARY KEY CLUSTERED  "
                + "	(idDevolucao, idLoja "
                + "	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]", ""});
            script.add(new String[]{"CREATE TABLE Tmp_jsysDevolucaoDepositoItens "
                + "	(idDevolucao bigint NOT NULL, "
                + " idLoja varchar(30) NOT NULL, "
                + "	idOrcamento int NOT NULL, "
                + "	idProduto int NOT NULL, "
                + "	quantidade decimal(16, 4) NOT NULL "
                + "	)  ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE Tmp_jsysDevolucaoDepositoItens SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"IF EXISTS (SELECT * FROM jsysDevolucaoDepositoItens) "
                + "	EXEC ('INSERT INTO Tmp_jsysDevolucaoDepositoItens (idDevolucao, idLoja, idOrcamento, idProduto, quantidade) "
                + "		SELECT CONVERT(BIGINT, RIGHT(idDevolucao, len(idDevolucao) - charindex(''|'', idDevolucao))), CASE WHEN charindex(''|'', idDevolucao) = 0 THEN jsyslojas.idloja ELSE LEFT(idDevolucao, charindex(''|'', idDevolucao) - 1) END,idOrcamento, idProduto, quantidade  FROM jsysDevolucaoDepositoItens WITH (HOLDLOCK TABLOCKX) CROSS JOIN jsyslojas WHERE (jsyslojas.ativo = 1)')", ""});
            script.add(new String[]{"DROP TABLE jsysDevolucaoDepositoItens", ""});
            script.add(new String[]{"EXECUTE sp_rename N'Tmp_jsysDevolucaoDepositoItens', N'jsysDevolucaoDepositoItens', 'OBJECT' ", ""});
            script.add(new String[]{"ALTER TABLE jsysProdutosT SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"ALTER TABLE jsysDevolucaoDeposito SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"ALTER TABLE jsysDevolucaoDepositoItens ADD CONSTRAINT "
                + "	FK_jsysDevolucaoDepositoItens_jsysDevolucaoDeposito FOREIGN KEY "
                + "	(idDevolucao, idLoja) REFERENCES jsysDevolucaoDeposito "
                + "	(idDevolucao, idLoja) ON UPDATE  NO ACTION ON DELETE  NO ACTION ", ""});
            script.add(new String[]{"ALTER TABLE jsysDevolucaoDepositoItens ADD CONSTRAINT FK_jsysDevolucaoDepositoItens_jsysProdutosT FOREIGN KEY "
                + "	(idProduto) REFERENCES jsysProdutosT ( idProduto ) ON UPDATE  NO ACTION ON DELETE  NO ACTION ", ""});
            script.add(new String[]{"ALTER TABLE jsysDevolucaoDepositoItens SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 18) {
            script.add(new String[]{"ALTER TABLE jsysParametros ADD "
                + "	tempoIntervalo datetime NULL, "
                + "	quantIntervalo int NULL", ""});
            script.add(new String[]{"ALTER TABLE jsysParametros SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 19) {
            script.add(new String[]{"IF EXISTS ( "
                + "		SELECT * "
                + "		FROM sysobjects "
                + "		WHERE id = OBJECT_ID('jsysTranferenciaEntreContas') "
                + "		) "
                + "	DROP VIEW jsysTranferenciaEntreContas", ""});
            script.add(new String[]{"CREATE TABLE jsysTranferenciaEntreContas "
                + "	( "
                + "	id bigint NOT NULL IDENTITY (1, 1), "
                + "	idGeral varchar(5) NOT NULL, "
                + "	valor decimal(16, 4) NOT NULL, "
                + "	data datetime NOT NULL, "
                + "	descricao varchar(100) NULL, "
                + "	idBancoOrigem int NOT NULL, "
                + "	idBancoDestino int NOT NULL, "
                + "	cancelada bit NOT NULL, "
                + "	idTitulo varchar(4) NOT NULL, "
                + "	dataInclusao datetime NOT NULL, "
                + "	usuarioInclusao varchar(25) NOT NULL, "
                + "	dataAlteracao datetime NULL, "
                + "	usuarioAlteracao varchar(25) NULL, "
                + "	retirado bit NOT NULL, "
                + "	descRetirado varchar(60) NULL, "
                + "	dataRetirado datetime NULL "
                + "	)  ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE jsysTranferenciaEntreContas ADD CONSTRAINT "
                + "	PK_jsysTranferenciaEntreContas PRIMARY KEY CLUSTERED  "
                + "	(id) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE jsysTranferenciaEntreContas SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"INSERT INTO [dbo].[jsysTranferenciaEntreContas] ( "
                + "	[idGeral] "
                + "	,[valor] "
                + "	,[data] "
                + "	,[descricao] "
                + "	,[idBancoOrigem] "
                + "	,[idBancoDestino] "
                + "	,[cancelada] "
                + "	,[idTitulo] "
                + "	,[dataInclusao] "
                + "	,[usuarioInclusao] "
                + "	,[retirado] "
                + "	,[descRetirado] "
                + "	,[dataRetirado] "
                + "	) "
                + "SELECT [Cod_geral] "
                + "	,[Vlr_transferencia] "
                + "	,[Dat_transferencia] "
                + "	,'' "
                + "	,[Cod_banco_origem] "
                + "	,[Cod_banco_destino] "
                + "	,0 "
                + "	,[C�digo t�tulo] "
                + "	,dateadd(MILLISECOND, DATEPART(MILLISECOND, [Hor_transferencia]), dateadd(SECOND, DATEPART(SECOND, [Hor_transferencia]), dateadd(MINUTE, DATEPART(MINUTE, [Hor_transferencia]), dateadd(HOUR, DATEPART(HOUR, [Hor_transferencia]), [Dat_transferencia])))) "
                + "	,[Usuario_incluiu] "
                + "	,[CONFIRMA] "
                + "	,[NomeRet] "
                + "	,[NomeRetData] "
                + "FROM Transferencia_entre_contas "
                + "WHERE Processada = 1", ""});
            script.add(new String[]{"IF EXISTS (SELECT * FROM sysobjects "
                + " WHERE id = OBJECT_ID('Transferencia_entre_contas')) "
                + "	DROP TABLE Transferencia_entre_contas", ""});
        }
        if (vercaoAtulizacao == 20) {
            script.add(new String[]{"ALTER TABLE jsysClientes ADD "
                + "	avisoDataInicial datetime NULL, "
                + "	avisoDataFinal datetime NULL", ""});
            script.add(new String[]{"ALTER TABLE jsysClientes SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 21) {
            script.add(new String[]{"ALTER TABLE jsysTitulos ADD tipoIntegracao varchar(80) NULL", ""});
            script.add(new String[]{"ALTER TABLE jsysTitulos SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 22) {
            script.add(new String[]{"ALTER TABLE Replicacao ADD camposChave varchar(5000) NULL", ""});
            script.add(new String[]{"ALTER TABLE Replicacao SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 23) {
            script.add(new String[]{"EXEC sp_changedbowner @loginame = N'jsys', @map = false", ""});
            script.add(new String[]{"UPDATE jsysOrcamento SET idCliente = jsysParametros.idConsumidorFinal "
                + "FROM jsysParametros WHERE idCliente NOT IN (SELECT idCliente FROM jsysClientes)", ""});
            script.add(new String[]{"CREATE TABLE Tmp_jsysClientes ( "
                + "	idCliente INT NOT NULL IDENTITY(1, 1) "
                + "	,nomeCorentista VARCHAR(60) NOT NULL "
                + "	,fantasia VARCHAR(60) NOT NULL "
                + "	,tipo BIT NOT NULL "
                + "	,sexo VARCHAR(15) NULL "
                + "	,endereco VARCHAR(60) NOT NULL "
                + "	,complemento VARCHAR(30) NULL "
                + "	,bairro VARCHAR(60) NOT NULL "
                + "	,cidade VARCHAR(40) NOT NULL "
                + "	,codMunicipio VARCHAR(7) NOT NULL "
                + "	,estado VARCHAR(2) NOT NULL "
                + "	,cep VARCHAR(9) NOT NULL "
                + "	,numero VARCHAR(9) NOT NULL "
                + "	,pais VARCHAR(40) NOT NULL "
                + "	,codPais VARCHAR(5) NOT NULL "
                + "	,fone VARCHAR(25) NULL "
                + "	,celular VARCHAR(25) NULL "
                + "	,fax VARCHAR(25) NULL "
                + "	,email VARCHAR(60) NULL "
                + "	,[natural] VARCHAR(40) NULL "
                + "	,cnpjCpf VARCHAR(18) NULL "
                + "	,ieRg VARCHAR(25) NOT NULL "
                + "	,ieRgData DATETIME NULL "
                + "	,ctps VARCHAR(30) NULL "
                + "	,ctpsSerie VARCHAR(30) NULL "
                + "	,ctpsdata DATETIME NULL "
                + "	,reservista VARCHAR(30) NULL "
                + "	,reservistaCategoria VARCHAR(30) NULL "
                + "	,cnh VARCHAR(30) NULL "
                + "	,cnhTipo VARCHAR(5) NULL "
                + "	,nacionalidade VARCHAR(40) NULL "
                + "	,pis VARCHAR(30) NULL "
                + "	,pisdata DATETIME NULL "
                + "	,estadoCivil VARCHAR(40) NULL "
                + "	,conjuge VARCHAR(60) NULL "
                + "	,filhos BIT NOT NULL "
                + "	,nomesDatas VARCHAR(255) NULL "
                + "	,suframa VARCHAR(12) NULL "
                + "	,dataNacimento DATETIME NULL "
                + "	,pai VARCHAR(60) NULL "
                + "	,mae VARCHAR(60) NULL "
                + "	,cargo VARCHAR(40) NULL "
                + "	,salario DECIMAL(16, 4) NULL "
                + "	,foneconjugue VARCHAR(25) NULL "
                + "	,contato VARCHAR(40) NULL "
                + "	,dataInclusao DATETIME NOT NULL "
                + "	,usuarioInclusao VARCHAR(50) NOT NULL "
                + "	,dataalteracao DATETIME NULL "
                + "	,usuarioalteracao VARCHAR(50) NULL "
                + "	,obs TEXT NULL "
                + "	,inativo BIT NOT NULL "
                + "	,comissaoVista DECIMAL(5, 2) NULL "
                + "	,comissaoPrazo DECIMAL(5, 2) NULL "
                + "	,admissao DATETIME NULL "
                + "	,demissao DATETIME NULL "
                + "	,cliente BIT NOT NULL "
                + "	,fornecedor BIT NOT NULL "
                + "	,colaborador BIT NOT NULL "
                + "	,filial BIT NOT NULL "
                + "	,malaDireta BIT NOT NULL "
                + "	,vip BIT NOT NULL "
                + "	,idLoja VARCHAR(30) NULL "
                + "	,tituloEleitor VARCHAR(50) NULL "
                + "	,zona VARCHAR(10) NULL "
                + "	,secao VARCHAR(10) NULL "
                + "	,escolaridade VARCHAR(60) NULL "
                + "	,cutis VARCHAR(25) NULL "
                + "	,cargaHoraria DATETIME NULL "
                + "	,reposoSemanal VARCHAR(25) NULL "
                + "	,horasExtras BIT NULL "
                + "	,recebimentoValeTrasnporte BIT NULL "
                + "	,descontosOutros BIT NULL "
                + "	,exameAdmissional DATETIME NULL "
                + "	,avisoDataInicial DATETIME NULL "
                + "	,avisoDataFinal DATETIME NULL "
                + "	) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE Tmp_jsysClientes SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"EXECUTE sp_bindefault 'defaultFalse', 'Tmp_jsysClientes.cliente'", ""});
            script.add(new String[]{"EXECUTE sp_bindefault 'defaultFalse', 'Tmp_jsysClientes.fornecedor'", ""});
            script.add(new String[]{"EXECUTE sp_bindefault 'defaultFalse', 'Tmp_jsysClientes.colaborador'", ""});
            script.add(new String[]{"EXECUTE sp_bindefault 'defaultFalse', 'Tmp_jsysClientes.filial'", ""});
            script.add(new String[]{" IF EXISTS (SELECT * FROM jsysClientes) "
                + "	EXEC ('SET IDENTITY_INSERT Tmp_jsysClientes ON "
                + " INSERT INTO Tmp_jsysClientes (idCliente, nomeCorentista, fantasia, tipo, sexo, endereco, complemento, bairro, cidade, codMunicipio, estado, cep, numero, pais, codPais, fone, celular, fax, email, [natural], cnpjCpf, ieRg, ieRgData, ctps, ctpsSerie, ctpsdata, reservista, reservistaCategoria, cnh, cnhTipo, nacionalidade, pis, pisdata, estadoCivil, conjuge, filhos, nomesDatas, suframa, dataNacimento, pai, mae, cargo, salario, foneconjugue, contato, dataInclusao, usuarioInclusao, dataalteracao, usuarioalteracao, obs, inativo, comissaoVista, comissaoPrazo, admissao, demissao, cliente, fornecedor, colaborador, malaDireta, vip, idLoja, tituloEleitor, zona, secao, escolaridade, cutis, cargaHoraria, reposoSemanal, horasExtras, recebimentoValeTrasnporte, descontosOutros, exameAdmissional, avisoDataInicial, avisoDataFinal) "
                + "	SELECT idCliente, nomeCorentista, fantasia, tipo, sexo, endereco, complemento, bairro, cidade, codMunicipio, estado, cep, numero, pais, codPais, fone, celular, fax, email, [natural], cnpjCpf, ieRg, ieRgData, ctps, ctpsSerie, ctpsdata, reservista, reservistaCategoria, cnh, cnhTipo, nacionalidade, pis, pisdata, estadoCivil, conjuge, filhos, nomesDatas, suframa, dataNacimento, pai, mae, cargo, salario, foneconjugue, contato, dataInclusao, usuarioInclusao, dataalteracao, usuarioalteracao, obs, inativo, comissaoVista, comissaoPrazo, admissao, demissao, cliente, fornecedor, colaborador, malaDireta, vip, idLoja, tituloEleitor, zona, secao, escolaridade, cutis, cargaHoraria, reposoSemanal, horasExtras, recebimentoValeTrasnporte, descontosOutros, exameAdmissional, avisoDataInicial, avisoDataFinal FROM jsysClientes WITH (HOLDLOCK TABLOCKX) "
                + " SET IDENTITY_INSERT Tmp_jsysClientes OFF ') ", ""});
            script.add(new String[]{"IF EXISTS (SELECT * FROM sysobjects WHERE id = object_id('[FK_Usuarios_idCliente]')) "
                + "	ALTER TABLE Usuarios DROP CONSTRAINT FK_Usuarios_idCliente", ""});
            script.add(new String[]{"IF EXISTS (SELECT * FROM sysobjects WHERE id = object_id('[jsysOrcamentoidCliente]'))"
                + "ALTER TABLE jsysOrcamento DROP CONSTRAINT jsysOrcamentoidCliente", ""});
            script.add(new String[]{"IF EXISTS (SELECT * FROM sysobjects WHERE id = object_id('[jsysClientesIdsdClente]'))"
                + "ALTER TABLE jsysClientesIds DROP CONSTRAINT jsysClientesIdsdClente", ""});
            script.add(new String[]{"IF EXISTS (SELECT * FROM sysobjects WHERE id = object_id('[FKjsysClientesIds]'))"
                + "ALTER TABLE jsysClientesIds DROP CONSTRAINT FKjsysClientesIds", ""});
            script.add(new String[]{"DROP TABLE jsysClientes", ""});
            script.add(new String[]{"EXECUTE sp_rename N'Tmp_jsysClientes', N'jsysClientes', 'OBJECT'", ""});
            script.add(new String[]{"ALTER TABLE jsysClientes ADD CONSTRAINT PKidClientes PRIMARY KEY CLUSTERED (idCliente) "
                + "	WITH ( STATISTICS_NORECOMPUTE = OFF,IGNORE_DUP_KEY = OFF,ALLOW_ROW_LOCKS = ON,ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]", ""});
            script.add(new String[]{"CREATE NONCLUSTERED INDEX idClientesIndex ON jsysClientes (idCliente) "
                + "	WITH (PAD_INDEX = OFF,FILLFACTOR = 90,STATISTICS_NORECOMPUTE = OFF,IGNORE_DUP_KEY = OFF,ALLOW_ROW_LOCKS = ON,ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE jsysClientesIds ADD CONSTRAINT jsysClientesIdsdClente FOREIGN KEY (idCliente) REFERENCES jsysClientes (idCliente) ON "
                + "UPDATE NO ACTION ON "
                + "DELETE NO ACTION", ""});
            script.add(new String[]{"ALTER TABLE jsysClientesIds "
                + "	WITH CHECK ADD CONSTRAINT [FKjsysClientesIds] FOREIGN KEY ([idCliente]) REFERENCES [dbo].[jsysClientes]([idCliente]) ON "
                + "UPDATE CASCADE ON "
                + "DELETE CASCADE", ""});
            script.add(new String[]{"ALTER TABLE [dbo].[jsysClientesIds] CHECK CONSTRAINT [FKjsysClientesIds]", ""});
            script.add(new String[]{"ALTER TABLE jsysClientesIds SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"ALTER TABLE jsysOrcamento ADD CONSTRAINT jsysOrcamentoidCliente FOREIGN KEY (idCliente) REFERENCES jsysClientes (idCliente) ON "
                + "UPDATE NO ACTION ON "
                + "DELETE NO ACTION", ""});
            script.add(new String[]{"ALTER TABLE jsysOrcamento SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"ALTER TABLE Usuarios ADD CONSTRAINT FK_Usuarios_idCliente FOREIGN KEY (idCliente) REFERENCES jsysClientes (idCliente) ON "
                + "UPDATE NO ACTION ON "
                + "DELETE NO ACTION", ""});
            script.add(new String[]{"ALTER TABLE Usuarios SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 24) {
            script.add(new String[]{"EXECUTE sp_unbindefault N'funcionariosPonto.falta'", ""});
        }
        if (vercaoAtulizacao == 25) {
            script.add(new String[]{"ALTER TABLE jsysParametros ADD bloquearRankVendedor bit NULL", ""});
            script.add(new String[]{"EXECUTE sp_bindefault N'defaultFalse', N'jsysParametros.bloquearRankVendedor'", ""});
            script.add(new String[]{"ALTER TABLE jsysParametros SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"UPDATE jsysParametros SET bloquearRankVendedor = 0", ""});
        }
        if (vercaoAtulizacao == 26) {
            script.add(new String[]{"ALTER TABLE jsysLojas ADD nomeBancoDados varchar(60) NULL", ""});
            script.add(new String[]{"ALTER TABLE jsysLojas SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"update jsysLojas set nomeBancoDados = 'DADOS'", ""});
        }
        if (vercaoAtulizacao == 27) {
            script.add(new String[]{"ALTER TABLE jsysCompras ADD chaveAcesso varchar(50) NULL, xml xml NULL", ""});
            script.add(new String[]{"ALTER TABLE jsysCompras SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 28) {
            script.add(new String[]{"EXECUTE sp_unbindefault N'jsysParametros.vercaoNFe'", ""});
            script.add(new String[]{"ALTER TABLE jsysParametros DROP COLUMN modeloNfe, nNFCeserie, vercaoNFe", ""});
        }
        if (vercaoAtulizacao == 29) {
            script.add(new String[]{"ALTER TABLE funcionariosPonto ADD meiaFalta bit NULL", ""});
            script.add(new String[]{"EXECUTE sp_bindefault N'defaultFalse', N'funcionariosPonto.meiaFalta'", ""});
            script.add(new String[]{"ALTER TABLE funcionariosPonto SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"UPDATE funcionariosPonto SET meiaFalta = CASE WHEN (falta = 1 AND [entrada] IS NOT NULL AND intervaloInicio IS NOT NULL "
                + " AND [intervaloFim] IS NULL AND [Saida] IS NULL) THEN 1 ELSE 0 END", ""});
        }
        if (vercaoAtulizacao == 30) {
            script.add(new String[]{"ALTER TABLE jsysParametros ADD contingencia bit NULL, tpEmis smallint NULL, dhCont datetime NULL, xJust varchar(256) NULL, vias bit NULL", ""});
            script.add(new String[]{"ALTER TABLE jsysParametros SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"UPDATE jsysParametros SET tpEmis = 1, contingencia = 0, dhCont = GETDATE(), xJust = '', vias = 1", ""});
        }
        if (vercaoAtulizacao == 31) {
            script.add(new String[]{"UPDATE jsysOrcamentoItens SET unidadeVenda = 'UN' WHERE unidadeVenda = ''", ""});
        }
        if (vercaoAtulizacao == 32) {
            script.add(new String[]{"ALTER TABLE jsysParametros ADD "
                + "	CStat65 varchar(3) NULL, "
                + "	CStat65Hora datetime NULL, "
                + "	CStat55 varchar(3) NULL, "
                + "	CStat55Hora datetime NULL", ""});
            script.add(new String[]{"ALTER TABLE jsysParametros SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 33) {
            script.add(new String[]{"CREATE TABLE JsysNFeTransportadoras ( "
                + " id int NOT NULL IDENTITY (1, 1), "
                + "	CnpjCpf varchar(18) NOT NULL, "
                + "	xNome varchar(60) NOT NULL, "
                + "	ieRg varchar(25) NOT NULL, "
                + "	xEnder varchar(60) NULL, "
                + "	xMun varchar(60) NULL, "
                + "	UF varchar(2) NULL, "
                + "	obs text NULL, "
                + "	fone varchar(25) NULL, "
                + "	email varchar(60) NULL)  ON [PRIMARY] "
                + "	 TEXTIMAGE_ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE JsysNFeTransportadoras ADD CONSTRAINT "
                + "	PK_JsysNFeTransportadoras PRIMARY KEY CLUSTERED (id "
                + "	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE JsysNFeTransportadoras SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 34) {
            script.add(new String[]{"DROP TABLE Transportadoras", ""});
            script.add(new String[]{"DROP TABLE NFe", ""});
        }
        if (vercaoAtulizacao == 35) {
            script.add(new String[]{"ALTER TABLE jsysNFe ADD modFrete varchar(1) NULL, idTrasportadora int NULL", ""});
            script.add(new String[]{"ALTER TABLE jsysNFe SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 36) {
            script.add(new String[]{"ALTER TABLE jsysNFe SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"CREATE TABLE jsysNFeVolumes (id bigint IDENTITY(1,1) NOT NULL, "
                + "nfe_id bigint NOT NULL, "
                + "qVol decimal(15, 0) NULL, "
                + "esp varchar(60) NULL, "
                + "marca varchar(60) NULL, "
                + "nVol varchar(60) NULL, "
                + "pesoL decimal(13, 3) NULL, "
                + "pesoB decimal(13, 3) NULL) ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE jsysNFeVolumes ADD CONSTRAINT "
                + "PK_jsysNFeVolumes PRIMARY KEY CLUSTERED (id) "
                + "WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE jsysNFeVolumes ADD CONSTRAINT FK_jsysNFeVolumes_jsysNFe FOREIGN KEY "
                + "(nfe_id) REFERENCES jsysNFe "
                + "(nfe_id) ON UPDATE  NO ACTION  "
                + "ON DELETE  NO ACTION ", ""});
            script.add(new String[]{"ALTER TABLE jsysNFeVolumes SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 37) {
            script.add(new String[]{"ALTER TABLE jsysNFe ADD infCpl varchar(5000) null", ""});
            script.add(new String[]{"ALTER TABLE jsysNFe SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 38) {
            script.add(new String[]{"ALTER TABLE jsysOrcamentoItens ADD cfop varchar(5) NULL", ""});
            script.add(new String[]{"ALTER TABLE jsysOrcamentoItens SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 39) {
            script.add(new String[]{"CREATE TABLE Tmp_jsysParametros "
                + "	( "
                + "	fantasia varchar(60) NOT NULL, "
                + "	razaoSocial varchar(60) NOT NULL, "
                + "	cnpj varchar(18) NOT NULL, "
                + "	inscricao varchar(15) NOT NULL, "
                + "	suframa varchar(25) NOT NULL, "
                + "	endereco varchar(40) NOT NULL, "
                + "	numero varchar(10) NOT NULL, "
                + "	complemento varchar(22) NOT NULL, "
                + "	bairro varchar(20) NOT NULL, "
                + "	cidade varchar(50) NOT NULL, "
                + "	codMunicipio int NOT NULL, "
                + "	uf varchar(2) NOT NULL, "
                + "	cep varchar(9) NOT NULL, "
                + "	pais varchar(40) NOT NULL, "
                + "	codPais int NOT NULL, "
                + "	fone varchar(14) NULL, "
                + "	fax varchar(14) NULL, "
                + "	email varchar(90) NULL, "
                + "	mensagem varchar(40) NULL, "
                + "	juros decimal(3, 2) NOT NULL, "
                + "	selecionarEstoque bit NOT NULL, "
                + "	idForncedor int NOT NULL, "
                + "	sqlProduto varchar(255) NULL, "
                + "	sqlCliente varchar(255) NULL, "
                + "	cartaoVendas bit NOT NULL, "
                + "	cartaoVendedor bit NOT NULL, "
                + "	cfop varchar(5) NOT NULL, "
                + "	cfopInterestadual varchar(5) NOT NULL, "
                + "	cfopSub varchar(5) NOT NULL, "
                + "	cfopSubcfopInterestadual varchar(5) NOT NULL, "
                + "	simplesNacinal bit NOT NULL, "
                + "	CNAE varchar(7) NOT NULL, "
                + "	IM varchar(15) NOT NULL, "
                + "	nNFeserie varchar(3) NOT NULL, "
                + "	vercaoSystema bigint NOT NULL, "
                + "	vercaoDB bigint NOT NULL, "
                + "	deposito bit NOT NULL, "
                + "	idDeposito int NOT NULL, "
                + "	idGeralCheque varchar(5) NULL, "
                + "	idGeralAberturaCaixa varchar(5) NULL, "
                + "	idGeralRetiradas varchar(5) NULL, "
                + "	idGeralSobraCaixa varchar(5) NULL, "
                + "	idGeralFaltaCaixa varchar(5) NULL, "
                + "	idGeralPagamento varchar(5) NULL, "
                + "	idTituloDinhero varchar(4) NULL, "
                + "	crt varchar(1) NULL, "
                + "	infCpl varchar(5000) NULL, "
                + "	caminhoDoCertificadoDoCliente varchar(5000) NULL, "
                + "	senhaDoCertificadoDoCliente varchar(60) NULL, "
                + "	cIdToken varchar(6) NULL, "
                + "	CSC varchar(36) NULL, "
                + "	utilizarNfce bit NULL, "
                + "	idConsumidorFinal int NULL, "
                + "	logo varchar(5000) NULL, "
                + "	tempoIntervalo datetime NULL, "
                + "	quantIntervalo int NULL, "
                + "	bloquearRankVendedor bit NULL, "
                + "	contingencia bit NULL, "
                + "	tpEmis smallint NULL, "
                + "	dhCont datetime NULL, "
                + "	xJust varchar(256) NULL, "
                + "	vias bit NULL, "
                + "	CStat65 varchar(3) NULL, "
                + "	CStat65Hora datetime NULL, "
                + "	CStat55 varchar(3) NULL, "
                + "	CStat55Hora datetime NULL "
                + "	)  ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE Tmp_jsysParametros SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"EXECUTE sp_bindefault N'defZero', N'Tmp_jsysParametros.vercaoSystema'", ""});
            script.add(new String[]{"EXECUTE sp_bindefault N'defaultFalse', N'Tmp_jsysParametros.deposito'", ""});
            script.add(new String[]{"EXECUTE sp_bindefault N'defaultFalse', N'Tmp_jsysParametros.bloquearRankVendedor'", ""});
            script.add(new String[]{"IF EXISTS(SELECT * FROM jsysParametros) "
                + "	EXEC('INSERT INTO Tmp_jsysParametros (fantasia, razaoSocial, cnpj, inscricao, suframa, endereco, numero, complemento, bairro, cidade, codMunicipio, uf, cep, pais, codPais, fone, fax, email, mensagem, juros, selecionarEstoque, idForncedor, sqlProduto, sqlCliente, cartaoVendas, cartaoVendedor, cfop, cfopInterestadual, cfopSub, cfopSubcfopInterestadual, simplesNacinal, CNAE, IM, nNFeserie, vercaoSystema, vercaoDB, deposito, idDeposito, idGeralCheque, idGeralAberturaCaixa, idGeralRetiradas, idGeralSobraCaixa, idGeralFaltaCaixa, idGeralPagamento, idTituloDinhero, crt, infCpl, caminhoDoCertificadoDoCliente, senhaDoCertificadoDoCliente, cIdToken, CSC, utilizarNfce, idConsumidorFinal, logo, tempoIntervalo, quantIntervalo, bloquearRankVendedor, contingencia, tpEmis, dhCont, xJust, vias, CStat65, CStat65Hora, CStat55, CStat55Hora) "
                + "	SELECT fantasia, razaoSocial, cnpj, inscricao, suframa, endereco, numero, complemento, bairro, cidade, codMunicipio, uf, cep, pais, codPais, fone, fax, email, mensagem, juros, selecionarEstoque, idForncedor, sqlProduto, sqlCliente, cartaoVendas, cartaoVendedor, cfop, cfop, cfopSub, cfopSub, simplesNacinal, CNAE, IM, nNFeserie, vercaoSystema, vercaoDB, deposito, idDeposito, idGeralCheque, idGeralAberturaCaixa, idGeralRetiradas, idGeralSobraCaixa, idGeralFaltaCaixa, idGeralPagamento, idTituloDinhero, crt, infCpl, caminhoDoCertificadoDoCliente, senhaDoCertificadoDoCliente, cIdToken, CSC, utilizarNfce, idConsumidorFinal, logo, tempoIntervalo, quantIntervalo, bloquearRankVendedor, contingencia, tpEmis, dhCont, xJust, vias, CStat65, CStat65Hora, CStat55, CStat55Hora FROM jsysParametros WITH (HOLDLOCK TABLOCKX)')", ""});
            script.add(new String[]{"DROP TABLE jsysParametros", ""});
            script.add(new String[]{"EXECUTE sp_rename N'Tmp_jsysParametros', N'jsysParametros', 'OBJECT' ", ""});
            script.add(new String[]{"ALTER TABLE jsysParametros ADD CONSTRAINT "
                + "	PK_jsysParametros PRIMARY KEY CLUSTERED (cnpj) "
                + "	 WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]", ""});
        }
        if (vercaoAtulizacao == 40) {
            script.add(new String[]{"update jsysParametros set CStat55 = '107', CStat65 = '107'", ""});
        }
        if (vercaoAtulizacao == 41) {
            script.add(new String[]{"ALTER TABLE jsysNFe SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"CREATE TABLE jsysNFeReferencias ( "
                + "	id BIGINT NOT NULL IDENTITY(1, 1) "
                + "	,nfe_id BIGINT NOT NULL "
                + "	,NFeRef BIT NOT NULL "
                + "	,refNFe VARCHAR(44) NULL "
                + "	,CTeRef BIT NOT NULL "
                + "	,refCTe VARCHAR(44) NULL "
                + "	,NfRef BIT NOT NULL "
                + "	,cUF VARCHAR(2) NULL "
                + "	,AAMM VARCHAR(4) NULL "
                + "	,CNPJ VARCHAR(14) NULL "
                + "	,modelo VARCHAR(2) NULL "
                + "	,serie VARCHAR(3) NULL "
                + "	,nNF VARCHAR(9) NULL "
                + "	,NfpRef BIT NOT NULL "
                + "	,cpf VARCHAR(11) NULL "
                + "	,ie VARCHAR(14) NULL "
                + "	,EcfRef BIT NOT NULL "
                + "	,nECF VARCHAR(3) NULL "
                + "	,nCOO VARCHAR(6) NULL "
                + "	) ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE jsysNFeReferencias ADD CONSTRAINT PK_jsysNFeReferencias PRIMARY KEY CLUSTERED (id) "
                + "	WITH (STATISTICS_NORECOMPUTE = OFF "
                + "			,IGNORE_DUP_KEY = OFF "
                + "			,ALLOW_ROW_LOCKS = ON "
                + "			,ALLOW_PAGE_LOCKS = ON "
                + "			) ON [PRIMARY]", ""});
            script.add(new String[]{"ALTER TABLE jsysNFeReferencias ADD CONSTRAINT FK_jsysNFeReferencias_jsysNFe FOREIGN KEY (nfe_id) REFERENCES jsysNFe (nfe_id) ON "
                + "UPDATE NO ACTION ON "
                + "DELETE NO ACTION", ""});
            script.add(new String[]{"ALTER TABLE jsysNFeReferencias SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 42) {
            script.add(new String[]{"EXEC dbo.sp_changedbowner @loginame = N'jsys', @map = false", ""});
            script.add(new String[]{"exec sp_rename 'JsysNFeTransportadoras', 'jsysNFeTransportadoras'", ""});
        }

        if (vercaoAtulizacao == 43) {
            script.add(new String[]{"DROP VIEW jsysReceber", ""});
            script.add(new String[]{"CREATE TABLE jsysReceber ( "
                + "	idReceber BIGINT NOT NULL "
                + "	,seqReceber INT NOT NULL "
                + "	,idloja VARCHAR(30) NOT NULL "
                + "	,idTitulo VARCHAR(4) NOT NULL "
                + "	,idCliente INT NOT NULL "
                + "	,dataEmissao DATETIME NULL "
                + "	,dataVencimento DATETIME NULL "
                + "	,valorTitulo DECIMAL(16, 4) NOT NULL "
                + "	,restante DECIMAL(16, 4) NOT NULL "
                + "	,Descontos DECIMAL(16, 4) NOT NULL "
                + "	,dataCancelar DATETIME NULL "
                + "	,obsCancelamento TEXT NULL "
                + "	,idBanco INT NULL "
                + "	,quitado BIT NOT NULL "
                + "	,idContabil VARCHAR(5) NOT NULL "
                + "	,ficha INT NULL "
                + "	,dataInclusao DATETIME NOT NULL "
                + "	,usuarioInclusao VARCHAR(25) NOT NULL "
                + "	,dataAlteracao DATETIME NULL "
                + "	,usuarioAlteracao VARCHAR(25) NULL "
                + "	,CONSTRAINT idReceberSeqReceber PRIMARY KEY CLUSTERED ( "
                + "		idReceber ASC "
                + "		,seqReceber ASC "
                + "		) WITH ( "
                + "		PAD_INDEX = OFF "
                + "		,STATISTICS_NORECOMPUTE = OFF "
                + "		,IGNORE_DUP_KEY = OFF "
                + "		,ALLOW_ROW_LOCKS = ON "
                + "		,ALLOW_PAGE_LOCKS = ON "
                + "		,FILLFACTOR = 90 "
                + "		) ON [PRIMARY] "
                + "	) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]", ""});
            script.add(new String[]{"IF EXISTS ( "
                + "		SELECT * "
                + "		FROM Receber "
                + "		) "
                + "BEGIN "
                + "	INSERT INTO jsysReceber ( "
                + "		idReceber "
                + "		,seqReceber "
                + "		,idloja "
                + "		,idTitulo "
                + "		,idCliente "
                + "		,dataEmissao "
                + "		,dataVencimento "
                + "		,valorTitulo "
                + "		,restante "
                + "		,Descontos "
                + "		,dataCancelar "
                + "		,obsCancelamento "
                + "		,idBanco "
                + "		,quitado "
                + "		,idContabil "
                + "		,ficha "
                + "		,dataInclusao "
                + "		,usuarioInclusao "
                + "		,dataAlteracao "
                + "		,usuarioAlteracao "
                + "		) "
                + "	SELECT CAST(Documento AS INT) "
                + "		,cast(CASE  "
                + "				WHEN [Sequencia_titulos] = 'A' "
                + "					OR [Sequencia_titulos] = 'A1' "
                + "					THEN 1 "
                + "				WHEN [Sequencia_titulos] = 'B' "
                + "					OR [Sequencia_titulos] = 'A2' "
                + "					THEN 2 "
                + "				WHEN [Sequencia_titulos] = 'C' "
                + "					OR [Sequencia_titulos] = 'A3' "
                + "					THEN 3 "
                + "				WHEN [Sequencia_titulos] = 'D' "
                + "					THEN 4 "
                + "				WHEN [Sequencia_titulos] = 'E' "
                + "					THEN 5 "
                + "				WHEN [Sequencia_titulos] = 'F' "
                + "					THEN 6 "
                + "				WHEN [Sequencia_titulos] = 'G' "
                + "					THEN 7 "
                + "				ELSE [Sequencia_titulos] "
                + "				END AS INT) "
                + "		,jsysLojas.idloja "
                + "		,[C�digo t�tulo] "
                + "		,cast([Codigo do cliente] AS INT) "
                + "		,Data_emiss�o "
                + "		,Data_vencimento "
                + "		,Valor_titulo "
                + "		,restante "
                + "		,Descontos "
                + "		,Data_cancelar "
                + "		,Obs_cancelamento "
                + "		,Cod_banco "
                + "		,Quitado "
                + "		,Cod_contabil "
                + "		,ficha "
                + "		,cast(cast(isnull(Data_inclusao, getdate()) AS DATE) AS DATETIME) + cast(cast(isnull(Hora_inclusao, getdate()) AS TIME) AS DATETIME) "
                + "		,Usuario_inclusao "
                + "		,Data_alteracao "
                + "		,Usuario_alteracao "
                + "	FROM Receber WITH (HOLDLOCK TABLOCKX) "
                + "	CROSS JOIN jsysLojas WITH (HOLDLOCK TABLOCKX) "
                + "	WHERE jsysLojas.ativo = 1 "
                + "END", ""});
            script.add(new String[]{"ALTER TABLE jsysReceber "
                + "	WITH CHECK ADD CONSTRAINT [FK_jsysReceber_jsysClientes] FOREIGN KEY (idCliente) REFERENCES jsysClientes(idCliente) ON "
                + "UPDATE CASCADE ON "
                + "DELETE CASCADE", ""});
            script.add(new String[]{"ALTER TABLE jsysReceber CHECK CONSTRAINT [FK_jsysReceber_jsysClientes]", ""});
            script.add(new String[]{"CREATE TABLE jsysReceberBaixa ( "
                + "	idReceber BIGINT NOT NULL "
                + "	,seqReceber INT NOT NULL "
                + "	,id INT NOT NULL "
                + "	,idloja VARCHAR(30) NOT NULL "
                + "	,idBanco INT NULL "
                + "	,data DATETIME NULL "
                + "	,valor DECIMAL(16, 4) NOT NULL "
                + "	,restante DECIMAL(16, 4) NOT NULL "
                + "	,valorJuros DECIMAL(16, 4) NOT NULL "
                + "	,idTitulo VARCHAR(4) NOT NULL "
                + "	,quitado bit NOT NULL "
                + "	,dataInclusao DATETIME NOT NULL "
                + "	,usuarioInclusao VARCHAR(25) NOT NULL "
                + "	,dataAlteracao DATETIME NULL "
                + "	,usuarioAlteracao VARCHAR(25) NULL "
                + "	,CONSTRAINT idReceberSeqReceberId PRIMARY KEY CLUSTERED ( "
                + "		idReceber ASC "
                + "		,seqReceber ASC "
                + "		,id ASC "
                + "		) WITH ( "
                + "		PAD_INDEX = OFF "
                + "		,STATISTICS_NORECOMPUTE = OFF "
                + "		,IGNORE_DUP_KEY = OFF "
                + "		,ALLOW_ROW_LOCKS = ON "
                + "		,ALLOW_PAGE_LOCKS = ON "
                + "		,FILLFACTOR = 90 "
                + "		) ON [PRIMARY] "
                + "	) ON [PRIMARY]", ""});
            script.add(new String[]{"IF EXISTS ( "
                + "		SELECT * "
                + "		FROM Quita_receber "
                + "		) "
                + "BEGIN "
                + "	INSERT INTO jsysReceberBaixa ( "
                + "		idReceber "
                + "		,seqReceber "
                + "		,id "
                + "		,idloja "
                + "		,idBanco "
                + "		,data "
                + "		,valor "
                + "		,restante "
                + "		,valorJuros "
                + "		,idTitulo "
                + "		,quitado "
                + "		,dataInclusao "
                + "		,usuarioInclusao "
                + "		,dataAlteracao "
                + "		,usuarioAlteracao "
                + "		) "
                + "	SELECT CAST(Documento AS INT) "
                + "		,cast(CASE  "
                + "				WHEN [Sequencia_titulos] = 'A' "
                + "					OR [Sequencia_titulos] = 'A1' "
                + "					THEN 1 "
                + "				WHEN [Sequencia_titulos] = 'B' "
                + "					OR [Sequencia_titulos] = 'A2' "
                + "					THEN 2 "
                + "				WHEN [Sequencia_titulos] = 'C' "
                + "					OR [Sequencia_titulos] = 'A3' "
                + "					THEN 3 "
                + "				WHEN [Sequencia_titulos] = 'D' "
                + "					THEN 4 "
                + "				WHEN [Sequencia_titulos] = 'E' "
                + "					THEN 5 "
                + "				WHEN [Sequencia_titulos] = 'F' "
                + "					THEN 6 "
                + "				WHEN [Sequencia_titulos] = 'G' "
                + "					THEN 7 "
                + "				ELSE [Sequencia_titulos] "
                + "				END AS INT) "
                + "		,Sq "
                + "		,jsysLojas.idloja "
                + "		,Cod_banco "
                + "		,Data_pagamento "
                + "		,[Valor_Pago] "
                + "         ,Quita_receber.[valor_saldo] "
                + "		,Valor_corrigido "
                + "		,[C�digo t�tulo] "
                + "         ,Quita_receber.QUITADO "
                + "		,Data_inclusao "
                + "		,Usuario_inclusao "
                + "		,Data_alteracao "
                + "		,Usuario_alteracao "
                + "	FROM Quita_receber WITH (HOLDLOCK TABLOCKX) "
                + "	CROSS JOIN jsysLojas WITH (HOLDLOCK TABLOCKX) "
                + "	WHERE jsysLojas.ativo = 1 "
                + "END", ""});
            script.add(new String[]{"ALTER TABLE jsysReceberBaixa "
                + "	WITH CHECK ADD CONSTRAINT [FK_jsysReceber_jsysReceberBaixa] FOREIGN KEY ( "
                + "	idReceber, seqReceber) REFERENCES jsysReceber(idReceber, seqReceber) ON "
                + "UPDATE CASCADE ON "
                + "DELETE CASCADE", ""});
            script.add(new String[]{"DROP TABLE Quita_receber", ""});
            script.add(new String[]{"DROP TABLE Receber", ""});
            script.add(new String[]{"UPDATE jsysclientes SET recebimentoValeTrasnporte = 0 WHERE recebimentoValeTrasnporte IS NULL", ""});
            script.add(new String[]{"UPDATE jsysclientes SET horasExtras = 0 WHERE horasExtras IS NULL", ""});
            script.add(new String[]{"UPDATE jsysclientes SET descontosOutros = 0 WHERE descontosOutros IS NULL", ""});
        }
        if (vercaoAtulizacao == 44) {
            script.add(new String[]{"ALTER TABLE UsuariosGrupo ADD cancelarDia bit NULL", ""});
            script.add(new String[]{"ALTER TABLE UsuariosGrupo SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"UPDATE UsuariosGrupo SET cancelarDia = CASE WHEN Grupo = 'ADMINISTRACAO' THEN 0 ELSE 1 END ", ""});
        }
        if (vercaoAtulizacao == 45) {
            script.add(new String[]{"ALTER TABLE UsuariosGrupo ADD administracao bit NULL", ""});
            script.add(new String[]{"ALTER TABLE UsuariosGrupo SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"UPDATE UsuariosGrupo SET administracao = CASE WHEN Grupo = 'ADMINISTRACAO' THEN 1 ELSE 0 END ", ""});
        }
        if (vercaoAtulizacao == 46) {
            script.add(new String[]{"ALTER TABLE Usuarios ADD password varchar(128) NULL", ""});
            script.add(new String[]{"UPDATE usuarios SET [password] = upper(SUBSTRING(master.dbo.fn_varbintohexstr(HashBytes('SHA2_256', senha)), 3, 64))", ""});
            script.add(new String[]{"EXECUTE sp_bindefault N'defNada', N'Usuarios.password'", ""});
            script.add(new String[]{"EXECUTE sp_unbindefault N'Usuarios.senha'", ""});
            script.add(new String[]{"ALTER TABLE Usuarios DROP COLUMN senha", ""});
            script.add(new String[]{"ALTER TABLE Usuarios SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 47) {
            script.add(new String[]{"ALTER TABLE jsysTitulos ADD baixaReceber bit NULL", ""});
            script.add(new String[]{"EXECUTE sp_bindefault N' defaultTrue', N'jsysTitulos.baixaReceber'", ""});
            script.add(new String[]{"ALTER TABLE jsysTitulos SET (LOCK_ESCALATION = TABLE)", ""});
            script.add(new String[]{"UPDATE jsysTitulos SET baixaReceber = 1", ""});
            script.add(new String[]{"UPDATE jsysTitulos SET baixaReceber = 0 WHERE idTitulo = 'CRED' OR idTitulo = 'PREM'", ""});
        }
        if (vercaoAtulizacao == 48) {
            script.add(new String[]{"ALTER TABLE  jsysParametros ADD limiteDesconto decimal(5, 2) NULL", ""});
            script.add(new String[]{"ALTER TABLE  jsysParametros SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 49) {
            script.add(new String[]{"UPDATE Liberacao SET tipo = tipo COLLATE SQL_Latin1_General_CP1251_CS_AS", ""});
        }
        if (vercaoAtulizacao == 50) {
            script.add(new String[]{"ALTER TABLE  jsysOrcamentoItens ADD idProdutoNfe int NULL", ""});
            script.add(new String[]{"ALTER TABLE  jsysOrcamentoItens SET (LOCK_ESCALATION = TABLE)", ""});
        }
        if (vercaoAtulizacao == 51) {
            script.add(new String[]{"DROP TABLE Grupo_conta", "Drop Grupo_conta"});
        }
        if (vercaoAtulizacao == 52) {
            script.add(new String[]{"ALTER TABLE jsysParametros ADD idBancoCofre int NULL, idBancoAjuste int NULL", "jsysParametros"});
            script.add(new String[]{"ALTER TABLE jsysParametros SET (LOCK_ESCALATION = TABLE)", "jsysParametros"});
        }
        if (vercaoAtulizacao == 53) {
            script.add(new String[]{"ALTER TABLE jsysProdutosT ADD "
                + "	CstPis int NULL, "
                + "	CstPisEstadual int NULL, "
                + "	CstCofins int NULL, "
                + "	CstCofEstadual int NULL, "
                + "	CstIpi int NULL, "
                + "	CstIpiEstadual int NULL, "
                + "	CstIss int NULL, "
                + "	CSOSNEstadual int NULL, "
                + "	CSOSN int NULL", "jsysProdutosT"});
            script.add(new String[]{"ALTER TABLE  jsysProdutosT SET (LOCK_ESCALATION = TABLE)", "jsysProdutosT"});
            script.add(new String[]{"EXECUTE sp_bindefault 'defNada', 'jsysProdutosT.obs'", "EXECUTE sp_bindefault"});
        }
        if (vercaoAtulizacao == 54) {
            script.add(new String[]{"ALTER TABLE jsysParametros ADD validarNFCe bit NULL", "jsysParametros"});
            script.add(new String[]{"ALTER TABLE jsysParametros SET (LOCK_ESCALATION = TABLE)", "jsysParametros"});
            script.add(new String[]{"ALTER TABLE UsuariosGrupo ADD movAjusteEstoque bit NULL", "UsuariosGrupo"});
            script.add(new String[]{"ALTER TABLE  UsuariosGrupo SET (LOCK_ESCALATION = TABLE)", "UsuariosGrupo"});
            script.add(new String[]{"UPDATE UsuariosGrupo SET movAjusteEstoque = 0", "UsuariosGrupo"});
        }
        if (vercaoAtulizacao == 55) {
            script.add(new String[]{"ALTER TABLE jsysCheques ADD devolvido bit NULL", "jsysCheques"});
            script.add(new String[]{"EXECUTE sp_bindefault 'defaultFalse', 'jsysCheques.devolvido'", "jsysCheques"});
            script.add(new String[]{"ALTER TABLE jsysCheques SET (LOCK_ESCALATION = TABLE)", "jsysCheques"});
            script.add(new String[]{"UPDATE jsysCheques SET devolvido = 0", "jsysCheques"});
            script.add(new String[]{"UPDATE jsysReceber "
                + "SET restante = 0 "
                + "	,quitado = 1 "
                + "WHERE restante <> valorTitulo "
                + "	AND restante > 0.00 "
                + "	AND restante < 0.01 "
                + "	AND quitado = 0 "
                + "	AND dataCancelar IS NULL", "jsysReceber"});
        }
        if (vercaoAtulizacao == 56) {
            script.add(new String[]{"ALTER TABLE jsysClientes ADD IM varchar(15) NULL, limiteCredito decimal(16, 4)", "jsysClientes"});
            script.add(new String[]{"ALTER TABLE jsysClientes SET (LOCK_ESCALATION = TABLE)", "jsysClientes"});
        }
        if (vercaoAtulizacao == 57) {
            script.add(new String[]{"ALTER TABLE jsysParametros ADD forcarCodigoBarrasVenda bit NULL", "jsysParametros"});
            script.add(new String[]{"ALTER TABLE jsysParametros SET (LOCK_ESCALATION = TABLE)", "jsysParametros"});
        }
        if (vercaoAtulizacao == 58) {
            script.add(new String[]{"CREATE TABLE jsysProdutosTTributacao "
                + "	( "
                + "	idTributacao int NOT NULL, "
                + "	nome varchar(100) NOT NULL, "
                + "	cst varchar(2) NULL, "
                + "	cson varchar(3) NULL, "
                + "	cfop int NULL, "
                + "	pCredSNDentro decimal(4, 2) NULL, "
                + "	pCredSNFora decimal(4, 2) NULL, "
                + "	modBC varchar(2) NULL, "
                + "	pRedBC decimal(4, 2) NULL, "
                + "	pICMS decimal(4, 2) NULL, "
                + "	modBCST varchar(2) NULL, "
                + "	pRedBCST decimal(4, 2) NULL, "
                + "	pMVAST decimal(4, 2) NULL, "
                + "	pICMSST decimal(4, 2) NULL, "
                + "	decreto varchar(1000) NULL "
                + "	)  ON [PRIMARY]", "jsysProdutosTTributacao"});
            script.add(new String[]{"ALTER TABLE  jsysProdutosTTributacao ADD CONSTRAINT "
                + "	PK_jsysProdutosTTributacao PRIMARY KEY CLUSTERED  "
                + "	( "
                + "	idTributacao "
                + "	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]", "jsysProdutosTTributacao"});
            script.add(new String[]{"ALTER TABLE jsysProdutosTTributacao SET (LOCK_ESCALATION = TABLE)", "jsysProdutosTTributacao"});
            script.add(new String[]{"ALTER TABLE jsysProdutosT ADD idTributacao int NULL", "jsysProdutosT"});
            script.add(new String[]{"ALTER TABLE jsysProdutosT SET (LOCK_ESCALATION = TABLE)", "jsysProdutosT"});
            script.add(new String[]{"ALTER TABLE jsysParametros ADD idTributacao int NULL", "jsysParametros"});
            script.add(new String[]{"ALTER TABLE jsysParametros SET (LOCK_ESCALATION = TABLE)", "jsysParametros"});
        }
        if (vercaoAtulizacao == 59) {
            script.add(new String[]{"ALTER TABLE jsysOrcamentoItens ADD idTributacao int NULL", "jsysOrcamentoItens"});
            script.add(new String[]{"ALTER TABLE jsysOrcamentoItens SET (LOCK_ESCALATION = TABLE)", "jsysOrcamentoItens"});
        }
        if (vercaoAtulizacao == 60) {
            script.add(new String[]{"ALTER TABLE jsysProdutosTTributacao ADD orig varchar(1) NULL", "jsysProdutosTTributacao"});
            script.add(new String[]{"ALTER TABLE jsysProdutosTTributacao SET (LOCK_ESCALATION = TABLE)", "jsysProdutosTTributacao"});
        }
        if (vercaoAtulizacao == 61) {
            script.add(new String[]{"ALTER TABLE jsysParametros ADD localInstalador varchar(5000) NULL", "jsysParametros"});
            script.add(new String[]{"ALTER TABLE jsysParametros SET (LOCK_ESCALATION = TABLE)", "jsysParametros"});
        }
        if (vercaoAtulizacao == 62) {
            script.add(new String[]{"ALTER TABLE jsysParametros ADD dataInicioFolhaPagamento varchar(2) NULL", "jsysParametros"});
            script.add(new String[]{"ALTER TABLE jsysParametros ADD dataFimFolhaPagamento varchar(2) NULL", "jsysParametros"});
            script.add(new String[]{"ALTER TABLE jsysParametros SET (LOCK_ESCALATION = TABLE)", "jsysParametros"});
        }
//        if (vercaoAtulizacao == 0) {
//            script.add(new String[]{"", ""});
//        }
        return script;
    }

}
