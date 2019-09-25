package br.sql.nfe.links;

import br.sql.bean.views.ufIBGE;
import static br.JavaApplicationJsys.ARQUIVO_INI;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juliano Alves Medina
 */
public class ConstantesFiscal {
 
    public static final int NFC_E = 65;
    public static final int NF_E = 55;
    public static final String NFE = "NFe";
    public static final String NFCE = "NFCe";

    public static interface VERSAO {

        public static final String NFE = "4.00";
        public static final String INUTILIZACAO = "4.00";
        public static final String DIST_DFE = "1.01";
        public static final String EVENTO_CANCELAMENTO = "1.00";
        public static final String EVENTO_CCE = "1.00";
        public static final String EVENTO_MANIFESTAR = "1.00";
        public static final String STATUS_SERVICO = "4.00";
    }

    public static interface AMBIENTE {

        public static final String HOMOLOGACAO = "2";
        public static final String PRODUCAO = "1";
        public static final String TP_AMB = new br.sql.acesso.ini.IniFiles(ARQUIVO_INI).getString("FISCAL", "TP_AMB").substring(0, 1);
    }

    public static interface EVENTO {

        public static final String CANCELAR = "110111";
        public static final String CCE = "110110";
        public static final String MANIFESTACAO = "MANIFESTACAO";
    }

    public static interface TIPOS {

        public static final String CNPJ = "CNPJ";
        public static final String CPF = "CPF";
        public static final String NSU = "NSU";
        public static final String CHAVE = "CHAVE";
    }

    public static interface SERVICOS {

        public static final String STATUS_SERVICO = "NfeStatusServico_4.00";
        public static final String ENVIO = "NfeAutorizacao_4.00";
        public static final String CONSULTA_RECIBO = "NfeRetAutorizacao_4.00";
        public static final String CONSULTA_CADASTRO = "NfeConsultaCadastro_4.00";
        public static final String URL_QRCODE = "URL-QRCode";
        public static final String URL_CONSULTANFCE = "URL-ConsultaNFCe";
        public static final String EVENTO = "RecepcaoEvento_4.00";
        public static final String INUTILIZACAO = "NfeInutilizacao_4.00";
        public static final String CONSULTA_XML = "NfeConsultaProtocolo_4.00";
        public static final String DISTRIBUICAO_DFE = "NfeDistribuicaoDFe_1.01";
        public static final String MANIFESTACAO = "MANIFESTACAO";
        public static final String CSC = "AdministrarCSCNFCe_1.00";
    }

    public static final String statusSefaz(String CStat) {
        switch (CStat) {
            case "107":
                return "107-Serviço em Operação";
            case "108":
                return "108-Serviço Paralisado Temporariamente”";
            case "109":
                return "109-Serviço Paralisado sem Previsão";
            default:
                return CStat + "-Erro Indeterminado (Sem acesso à Internet verifique 'rede local, modem ou cabos')";
        }
    }

    public static List<ufIBGE> UF_IBGE = getListUfIbge();

    //Região Norte
    public static final ufIBGE RO = new ufIBGE(11, "Rondonia", "RO", 237590.543);
    public static final ufIBGE AC = new ufIBGE(12, "Acre", "AC", 164123.739);
    public static final ufIBGE AM = new ufIBGE(13, "Amazonas", "AM", 1559148.89);
    public static final ufIBGE RR = new ufIBGE(14, "Roraima", "RR", 224303.187);
    public static final ufIBGE PA = new ufIBGE(15, "Para", "PA", 1247954.32);
    public static final ufIBGE AP = new ufIBGE(16, "Amapa", "AP", 142828.52);
    public static final ufIBGE TO = new ufIBGE(17, "Tocantins", "TO", 277720.569);
    //Região Nordeste
    public static final ufIBGE MA = new ufIBGE(21, "Maranhao", "MA", 331936.948);
    public static final ufIBGE PI = new ufIBGE(22, "Piaui", "PI", 251611.932);
    public static final ufIBGE CE = new ufIBGE(23, "Ceara", "CE", 148886.308);
    public static final ufIBGE RN = new ufIBGE(24, "Rio Grande do Norte", "RN", 52811.126);
    public static final ufIBGE PB = new ufIBGE(25, "Paraiba", "PB", 56469.744);
    public static final ufIBGE PE = new ufIBGE(26, "Pernambuco", "PE", 98149.119);
    public static final ufIBGE AL = new ufIBGE(27, "Alagoas", "AL", 27774.993);
    public static final ufIBGE SE = new ufIBGE(28, "Sergipe", "SE", 21918.493);
    public static final ufIBGE BA = new ufIBGE(29, "Bahia", "BA", 564733.081);
    //Região Sudeste
    public static final ufIBGE MG = new ufIBGE(31, "Minas Gerais", "MG", 586519.727);
    public static final ufIBGE ES = new ufIBGE(32, "Espirito Santo", "ES", 46096.925);
    public static final ufIBGE RJ = new ufIBGE(33, "Rio de Janeiro", "RJ", 43777.954);
    public static final ufIBGE SP = new ufIBGE(35, "Sao Paulo", "SP", 248222.362);
    //Região Sul
    public static final ufIBGE PR = new ufIBGE(41, "Parana", "PR", 199307.945);
    public static final ufIBGE SC = new ufIBGE(42, "Santa Catarina", "SC", 95733.978);
    public static final ufIBGE RS = new ufIBGE(43, "Rio Grande do Sul", "RS", 281731.445);
    //Região Centro-Oeste
    public static final ufIBGE MS = new ufIBGE(50, "Mato Grosso do Sul", "MS", 357145.534);
    public static final ufIBGE MT = new ufIBGE(51, "Mato Grosso", "MT", 903378.292);
    public static final ufIBGE GO = new ufIBGE(52, "Goias", "GO", 340111.376);
    public static final ufIBGE DF = new ufIBGE(53, "Distrito Federal", "DF", 5779.999);

    private static List<ufIBGE> getListUfIbge() {
        List<ufIBGE> l = new ArrayList<>();
        l.add(RO);
        l.add(AC);
        l.add(AM);
        l.add(RR);
        l.add(PA);
        l.add(AP);
        l.add(TO);
        l.add(MA);
        l.add(PI);
        l.add(CE);
        l.add(RN);
        l.add(PB);
        l.add(PE);
        l.add(AL);
        l.add(SE);
        l.add(BA);
        l.add(MG);
        l.add(ES);
        l.add(RJ);
        l.add(SP);
        l.add(PR);
        l.add(SC);
        l.add(RS);
        l.add(MS);
        l.add(MT);
        l.add(GO);
        l.add(DF);
        return l;
    }
}
