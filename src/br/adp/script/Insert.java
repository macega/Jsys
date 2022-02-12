package br.adp.script;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juliano Alves Medina
 */
public class Insert {

    public static List<String[]> insert() {
        List<String[]> script = new ArrayList<>();
        // insere dados do sistema 

        script.add(new String[]{"INSERT INTO UsuariosGrupo ( "
            + "	[Grupo] "
            + "	,[Caixa] "
            + "	,[vendas] "
            + "	,[Gerencia] "
            + "	,[Financeiro] "
            + "	,[Relatorios] "
            + "	,[Lojas] "
            + "	,[ponto] "
            + "	,[MenuCadastros] "
            + "	,[MenuCaixa] "
            + "	,[MenuMovimento] "
            + "	,[MenuPagar] "
            + "	,[MenuPonto] "
            + "	,[MenuRelatorios] "
            + "	,[MenuUtilitarios] "
            + "	,[MenuFiscal] "
            + "	,[MenuDeposito] "
            + "	,[MenuFinanceiro] "
            + "	,[MenuCRM] "
            + "	,[MenuAjuda] "
            + "	,[RelVendas] "
            + "	,[RelCaixa] "
            + "	,[RelGerencial] "
            + "	,[fechaAuto] "
            + "	,[cancelarDia] "
            + "	,[administracao] "
            + "	) VALUES ('ADMINISTRACAO' "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,0 "
            + "	,1 "
            + "	)", "INSERT INTO UsuariosGrupo"});
        script.add(new String[]{"INSERT INTO jsysLojas(idloja, nomeLoja, ativo, cnpj, deposito, nomeBancoDados) "
            + "VALUES ('127.0.0.1', 'Loja', 1, '00.000.000/0000-00', 1, 'dados')", "INSERT INTO jsysLojas"});

        script.add(new String[]{"INSERT INTO jsysClientes ( "
            + "	nomeCorentista "
            + "	,fantasia "
            + "	,cidade "
            + "	,estado "
            + "	,pais "
            + "	,dataInclusao "
            + "	,usuarioInclusao "
            + "	,cliente "
            + "	,fornecedor "
            + "	,colaborador "
            + "	,filial "
            + "	,idLoja "
            + "	,tipo "
            + "	,endereco "
            + "	,complemento "
            + "	,bairro "
            + "	,codMunicipio "
            + "	,cep "
            + "	,numero "
            + "	,codPais "
            + "	,ieRg "
            + "	,cnpjCpf "
            + "	,filhos "
            + "	,inativo "
            + "	,malaDireta "
            + "	,vip "
            + "	) "
            + "SELECT TOP 1 'Consumidor Final' "
            + "	,'Consumidor Final' "
            + "	,'PORTO VELHO' "
            + "	,'RO' "
            + "	,'BRASIL' "
            + "	,CONVERT(DATETIME, '2016-01-01 00:00:00', 102) "
            + "	,'AUTO' "
            + "	,1 "
            + "	,0 "
            + "	,0 "
            + "	,0 "
            + "	,idloja "
            + "	,0 "
            + "	,'' "
            + "	,'' "
            + "	,'' "
            + "	,'1100205' "
            + "	,'' "
            + "	,'0' "
            + "	,'1058' "
            + "	,'' "
            + "	,'' "
            + "	,0 "
            + "	,0 "
            + "	,0 "
            + "	,0 "
            + "FROM jsysLojas "
            + "UNION ALL "
            + "SELECT TOP 1 'Deposito Central' "
            + "	,'Deposito Central' "
            + "	,'PORTO VELHO' "
            + "	,'RO' "
            + "	,'BRASIL' "
            + "	,CONVERT(DATETIME, '2016-01-01 00:00:00', 102) "
            + "	,'AUTO' "
            + "	,0 "
            + "	,1 "
            + "	,1 "
            + "	,1 "
            + "	,idloja "
            + "	,0 "
            + "	,'' "
            + "	,'' "
            + "	,'' "
            + "	,'1100205' "
            + "	,'' "
            + "	,'0' "
            + "	,'1058' "
            + "	,'' "
            + "	,'' "
            + "	,0 "
            + "	,0 "
            + "	,0 "
            + "	,0 "
            + "FROM jsysLojas "
            + "UNION ALL "
            + "SELECT TOP 1 'Supervisor' "
            + "	,'Supervisor' "
            + "	,'PORTO VELHO' "
            + "	,'RO' "
            + "	,'BRASIL' "
            + "	,CONVERT(DATETIME, '2016-01-01 00:00:00', 102) "
            + "	,'AUTO' "
            + "	,1 "
            + "	,0 "
            + "	,1 "
            + "	,0 "
            + "	,idloja "
            + "	,0 "
            + "	,'' "
            + "	,'' "
            + "	,'' "
            + "	,'1100205' "
            + "	,'' "
            + "	,'0' "
            + "	,'1058' "
            + "	,'' "
            + "	,'' "
            + "	,0 "
            + "	,0 "
            + "	,0 "
            + "	,0 "
            + "FROM jsysLojas "
            + "UNION ALL "
            + "SELECT TOP 1 'Fornecedor Padrao' "
            + "	,'Fornecedor Padrao' "
            + "	,'PORTO VELHO' "
            + "	,'RO' "
            + "	,'BRASIL' "
            + "	,CONVERT(DATETIME, '2016-01-01 00:00:00', 102) "
            + "	,'AUTO' "
            + "	,0 "
            + "	,1 "
            + "	,0 "
            + "	,0 "
            + "	,idloja "
            + "	,0 "
            + "	,'' "
            + "	,'' "
            + "	,'' "
            + "	,'1100205' "
            + "	,'' "
            + "	,'0' "
            + "	,'1058' "
            + "	,'' "
            + "	,'' "
            + "	,0 "
            + "	,0 "
            + "	,0 "
            + "	,0 "
            + "FROM jsysLojas "
            + "UNION ALL "
            + "SELECT TOP 1 'Conta Cofre' "
            + "	,'Conta Cofre' "
            + "	,'PORTO VELHO' "
            + "	,'RO' "
            + "	,'BRASIL' "
            + "	,CONVERT(DATETIME, '2016-01-01 00:00:00', 102) "
            + "	,'AUTO' "
            + "	,1 "
            + "	,0 "
            + "	,1 "
            + "	,0 "
            + "	,idloja "
            + "	,0 "
            + "	,'' "
            + "	,'' "
            + "	,'' "
            + "	,'1100205' "
            + "	,'' "
            + "	,'0' "
            + "	,'1058' "
            + "	,'' "
            + "	,'' "
            + "	,0 "
            + "	,0 "
            + "	,0 "
            + "	,0 "
            + "FROM jsysLojas", "INSERT INTO jsysClientes"});
        script.add(new String[]{"INSERT INTO Usuarios ( "
            + "	[idGrupo] "
            + "	,[usuario] "
            + "	,[bloqueado] "
            + "	,[idCliente] "
            + "	,[password] "
            + "	) "
            + "SELECT TOP 1 idGrupo "
            + "	,'SUPERVISOR' "
            + "	,0 "
            + "	,JSYSCLIENTES.idcliente "
            + "	,'71F4BBB3BDF3722BCB12ACCA51C9F0E3ACBE8C26318A83EFCA391E6972F59EA4' "
            + "FROM UsuariosGrupo "
            + "CROSS JOIN JSYSCLIENTES "
            + "WHERE grupo = 'ADMINISTRACAO' "
            + "	AND JSYSCLIENTES.nomeCorentista = 'Supervisor'", "INSERT INTO Usuarios"});

        script.add(new String[]{"INSERT INTO jsysTitulos ([idTitulo],[tipoFatura],[idcupom],[ativo],[tipoPagamento],[card],[cnpjCredenciadora],[tipoBandeira],[tipoIntegracao],[baixaReceber]) "
            + "     VALUES ('PROM','PROMISSORIA','05',1,'05 Crédito Loja',0,'','',NULL,1)", "INSERT INTO jsysTitulos"});
        script.add(new String[]{"INSERT INTO jsysTitulos ([idTitulo],[tipoFatura],[idcupom],[ativo],[tipoPagamento],[card],[cnpjCredenciadora],[tipoBandeira],[tipoIntegracao],[baixaReceber]) "
            + "     VALUES ('PREM','PREMIO','99',1	,'99 Outros',0,'','',NULL,0)", "INSERT INTO jsysTitulos"});
        script.add(new String[]{"INSERT INTO jsysTitulos ([idTitulo],[tipoFatura],[idcupom],[ativo],[tipoPagamento],[card],[cnpjCredenciadora],[tipoBandeira],[tipoIntegracao],[baixaReceber]) "
            + "     VALUES ('DINH','DINHEIRO','01',1,'01 Dinheiro',0,'','',NULL,1)", "INSERT INTO jsysTitulos"});
        script.add(new String[]{"INSERT INTO jsysTitulos ([idTitulo],[tipoFatura],[idcupom],[ativo],[tipoPagamento],[card],[cnpjCredenciadora],[tipoBandeira],[tipoIntegracao],[baixaReceber]) "
            + "     VALUES ('DEBI','CARTAO DE DEBITO','04',1,'04 Cartão de Débito',1,'','99 Outros','2 - POS Pagamento não integrado com o sistema de automaticoo da empresa',1)", "INSERT INTO jsysTitulos"});
        script.add(new String[]{"INSERT INTO jsysTitulos ([idTitulo],[tipoFatura],[idcupom],[ativo],[tipoPagamento],[card],[cnpjCredenciadora],[tipoBandeira],[tipoIntegracao],[baixaReceber]) "
            + "     VALUES ('CRED','CARTAO DE CREDITO','03',1,'03 Cartão de Crédito',1,'','99 Outros','2 - POS Pagamento não integrado com o sistema de automatico da empresa',0)", "INSERT INTO jsysTitulos"});

        script.add(new String[]{"INSERT INTO jsysParametros "
            + "                         (fantasia, razaoSocial, cnpj, inscricao, suframa, endereco, numero, complemento, bairro, cidade, codMunicipio, uf, cep, pais, codPais, fone, fax, email, mensagem, juros, selecionarEstoque, idForncedor, "
            + "                         sqlProduto, sqlCliente, cartaoVendas, cartaoVendedor, cfop, cfopInterestadual, cfopSub, cfopSubcfopInterestadual, simplesNacinal, CNAE, IM, nNFeserie, vercaoSystema, vercaoDB, deposito, idDeposito, "
            + "                         idGeralCheque, idGeralAberturaCaixa, idGeralRetiradas, idGeralSobraCaixa, idGeralFaltaCaixa, idGeralPagamento, idTituloDinhero, crt, infCpl, caminhoDoCertificadoDoCliente, senhaDoCertificadoDoCliente, "
            + "                         cIdToken, CSC, utilizarNfce, idConsumidorFinal, logo, tempoIntervalo, quantIntervalo, bloquearRankVendedor, contingencia, tpEmis, dhCont, xJust, vias, CStat65, CStat65Hora, CStat55, CStat55Hora, "
            + "                         limiteDesconto) "
            + "VALUES ('Fantasia', 'Razao social', '00.000.000/0000-00', 'ISENTO', '', 'Logradouro', 0, 'perto do ', 'bairro', 'PORTO VELHO', 1100205, 'RO', '78910-491', 'BRASIL', 1058, '(00)0000-0000', '(00)0000-0000', "
            + "                         'email@email.com', 'OBRIGADO PELA PREFERENCIA', 0.00, 0, 4, '', '', 0, 1, '5.102', '6.102', '5.102', '6.102', 1, '0000000', '14224638', 1, 1, '48', 1, 2, '2.22', '24.1', '24.2', '24.5', '24.4', '15.6', 'DINH', 1, "
            + "                         'DOCUMENTO EMITIDO POR ME OPTANTE PELO SIMPLES NACIONAL. CONFORME LEI COMPLEMENTAR 123, NAO GERA DIREITO A CREDITO FISCAL DE ICMS, ISS E IPI.', 'certificados/certEmpresa.pfx', '000', "
            + "                         000001, '000000000000000000', 1, 1, 'C:\\Jsys\\logo.jpg', '1970-01-01 00:15:00.000', 4, 0, 0, 1, '2016-05-06 11:07:44.777', '', 1, 107, '2016-11-04 19:06:28.737', 107, '2016-11-04 19:06:26.673', 5.00)", "INSERT INTO jsysParametros"});
        script.add(new String[]{"INSERT INTO [Plano_pagto] ([Cod_plano],[Desc_plano],[Forma_pagto],[Doc_entrada],[Doc_restante],[q_parcela],[tem_entrada],[intervalo],[controle_juros],[par_mensal_juros],[Plano_desconto],[plano_desconto_inicial]) VALUES ("
            + "	'001'"
            + "	,'00'"
            + "	,'00'"
            + "	,'DINH'"
            + "	,'DINH'"
            + "	,'01'"
            + "	,1"
            + "	,1"
            + "	,0"
            + "	,0.00"
            + "	,0.00"
            + "	,0.00"
            + "	)", "INSERT INTO [Plano_pagto]"});
        script.add(new String[]{"INSERT INTO [Plano_pagto] ([Cod_plano],[Desc_plano],[Forma_pagto],[Doc_entrada],[Doc_restante],[q_parcela],[tem_entrada],[intervalo],[controle_juros],[par_mensal_juros],[Plano_desconto],[plano_desconto_inicial]) VALUES ("
            + "	'002'"
            + "	,'00/30'"
            + "	,'00/30'"
            + "	,'DINH'"
            + "	,'PROM'"
            + "	,'02'"
            + "	,1"
            + "	,30"
            + "	,0"
            + "	,0.00"
            + "	,0.00"
            + "	,0.00"
            + "	)", "INSERT INTO [Plano_pagto]"});
        script.add(new String[]{"INSERT INTO [Plano_pagto] ([Cod_plano],[Desc_plano],[Forma_pagto],[Doc_entrada],[Doc_restante],[q_parcela],[tem_entrada],[intervalo],[controle_juros],[par_mensal_juros],[Plano_desconto],[plano_desconto_inicial]) VALUES ("
            + "	'003'"
            + "	,'30'"
            + "	,'30'"
            + "	,'PROM'"
            + "	,'PROM'"
            + "	,'01'"
            + "	,0"
            + "	,30"
            + "	,0"
            + "	,0.00"
            + "	,0.00"
            + "	,0.00"
            + "	)", "INSERT INTO [Plano_pagto]"});
        script.add(new String[]{"INSERT INTO [Plano_pagto] ([Cod_plano],[Desc_plano],[Forma_pagto],[Doc_entrada],[Doc_restante],[q_parcela],[tem_entrada],[intervalo],[controle_juros],[par_mensal_juros],[Plano_desconto],[plano_desconto_inicial]) VALUES ("
            + "	'004'"
            + "	,'30/60/90'"
            + "	,'30/60/90'"
            + "	,'PROM'"
            + "	,'PROM'"
            + "	,'03'"
            + "	,0"
            + "	,30"
            + "	,0"
            + "	,0.00"
            + "	,0.00"
            + "	,0.00"
            + "	)", "INSERT INTO [Plano_pagto]"});
        script.add(new String[]{"INSERT INTO [Plano_pagto] ([Cod_plano],[Desc_plano],[Forma_pagto],[Doc_entrada],[Doc_restante],[q_parcela],[tem_entrada],[intervalo],[controle_juros],[par_mensal_juros],[Plano_desconto],[plano_desconto_inicial]) VALUES ("
            + "	'005'"
            + "	,'30/60'"
            + "	,'30/60'"
            + "	,'PROM'"
            + "	,'PROM'"
            + "	,'02'"
            + "	,0"
            + "	,30"
            + "	,0"
            + "	,0.00"
            + "	,0.00"
            + "	,0.00"
            + "	)", "INSERT INTO [Plano_pagto]"});
        script.add(new String[]{"INSERT INTO [Plano_pagto] ([Cod_plano],[Desc_plano],[Forma_pagto],[Doc_entrada],[Doc_restante],[q_parcela],[tem_entrada],[intervalo],[controle_juros],[par_mensal_juros],[Plano_desconto],[plano_desconto_inicial]) VALUES ("
            + "	'006'"
            + "	,'30/60/90/120'"
            + "	,'30/60/90/120'"
            + "	,'PROM'"
            + "	,'PROM'"
            + "	,'04'"
            + "	,0"
            + "	,30"
            + "	,0"
            + "	,0.00"
            + "	,0.00"
            + "	,0.00"
            + "	)", "INSERT INTO [Plano_pagto]"});
        script.add(new String[]{"INSERT INTO [Plano_pagto] ([Cod_plano],[Desc_plano],[Forma_pagto],[Doc_entrada],[Doc_restante],[q_parcela],[tem_entrada],[intervalo],[controle_juros],[par_mensal_juros],[Plano_desconto],[plano_desconto_inicial]) VALUES ("
            + "	'007'"
            + "	,'00/30/60/90/120/150'"
            + "	,'00/30/60/90/120/150'"
            + "	,'DINH'"
            + "	,'PROM'"
            + "	,'06'"
            + "	,1"
            + "	,30"
            + "	,0"
            + "	,0.00"
            + "	,0.00"
            + "	,0.00"
            + "	)", "INSERT INTO [Plano_pagto]"});
        script.add(new String[]{"INSERT INTO [Plano_pagto] ([Cod_plano],[Desc_plano],[Forma_pagto],[Doc_entrada],[Doc_restante],[q_parcela],[tem_entrada],[intervalo],[controle_juros],[par_mensal_juros],[Plano_desconto],[plano_desconto_inicial]) VALUES ("
            + "	'008'"
            + "	,'30/60/90/120/150'"
            + "	,'30/60/90/120/150'"
            + "	,'PROM'"
            + "	,'PROM'"
            + "	,'05'"
            + "	,0"
            + "	,30"
            + "	,0"
            + "	,0.00"
            + "	,0.00"
            + "	,0.00"
            + "	)", "INSERT INTO [Plano_pagto]"});
        script.add(new String[]{"INSERT INTO [Plano_pagto] ([Cod_plano],[Desc_plano],[Forma_pagto],[Doc_entrada],[Doc_restante],[q_parcela],[tem_entrada],[intervalo],[controle_juros],[par_mensal_juros],[Plano_desconto],[plano_desconto_inicial]) VALUES ("
            + "	'009'"
            + "	,'00/30/60'"
            + "	,'00/30/60'"
            + "	,'DINH'"
            + "	,'PROM'"
            + "	,'03'"
            + "	,1"
            + "	,30"
            + "	,0"
            + "	,0.00"
            + "	,0.00"
            + "	,0.00"
            + "	)", "INSERT INTO [Plano_pagto]"});
        script.add(new String[]{"INSERT INTO [Plano_pagto] ([Cod_plano],[Desc_plano],[Forma_pagto],[Doc_entrada],[Doc_restante],[q_parcela],[tem_entrada],[intervalo],[controle_juros],[par_mensal_juros],[Plano_desconto],[plano_desconto_inicial]) VALUES ("
            + "	'010'"
            + "	,'00/30/60/90'"
            + "	,'00/30/60/90'"
            + "	,'DINH'"
            + "	,'PROM'"
            + "	,'04'"
            + "	,1"
            + "	,30"
            + "	,0"
            + "	,0.00"
            + "	,0.00"
            + "	,0.00"
            + "	)", "INSERT INTO [Plano_pagto]"});
        script.add(new String[]{"INSERT INTO [Plano_pagto] ([Cod_plano],[Desc_plano],[Forma_pagto],[Doc_entrada],[Doc_restante],[q_parcela],[tem_entrada],[intervalo],[controle_juros],[par_mensal_juros],[Plano_desconto],[plano_desconto_inicial]) VALUES ("
            + "	'011'"
            + "	,'00/30/60/90/120'"
            + "	,'00/30/60/90/120'"
            + "	,'DINH'"
            + "	,'PROM'"
            + "	,'05'"
            + "	,1"
            + "	,30"
            + "	,0"
            + "	,0.00"
            + "	,0.00"
            + "	,0.00"
            + "	)", "INSERT INTO [Plano_pagto]"});

        //script.add(new String[]{"", ""});
        return script;
    }

}
