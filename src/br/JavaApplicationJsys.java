package br;

import br.adp.geral.Geral;
import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Proxy;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.sql.acesso.LoginTela;
import br.sql.bean.JsysParametros;
import br.sql.nfe.links.ConstantesFiscal;
import java.io.FileNotFoundException;
import java.util.Arrays;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author julia
 */
public class JavaApplicationJsys {

    public static final int MAJOR = 63; // versao do db
    public static final int MINOR = 4; // versao do app
    public static final int BUILD = 40012; // NotaFiscal java-nfe-4.00.12.jar
    public static final int REVISION = 0; // revisoes bugs
    public static int[] VERSAO = new int[]{MAJOR, MINOR, BUILD, REVISION};
    public static String VERSAO_STRING = Arrays.toString(new int[]{MAJOR, MINOR, BUILD, REVISION}).replace("[", "").replace("]", "").replace(",", ".").replace(" ", "");
    public static Integer VERCAO_DB = MAJOR;
    public static final String ARQUIVO_INI = "Jsys.ini";
    //public static final String TP_AMB = new br.sql.ini.IniFiles(ARQUIVO_INI).getString("FISCAL", "TP_AMB").substring(0, 1);
    public static final Object[] OPTIONS = {"Sim", "Não"};
    public static final br.sql.acesso.ini.IniFiles INI = new br.sql.acesso.ini.IniFiles(ARQUIVO_INI);
    public static final String NOME_PERSISTENCE = "dados";
    public static final String PASTA_RELATORIOS_FISCAIS = "dadosFiscais" + System.getProperty("file.separator");
    public static final String PASTA_FISCAIS_COMPACTADOS = PASTA_RELATORIOS_FISCAIS + "compactados" + System.getProperty("file.separator");
    public static final String PASTA_XML = "xml" + System.getProperty("file.separator");
    public static final String PASTA_XML_PROC_NFE = PASTA_XML + "procNfe" + System.getProperty("file.separator");
    public static final String PASTA_XML_ENVI_NFE = PASTA_XML + "enviNFe" + System.getProperty("file.separator");
    public static final String PASTA_XML_RET_CONS_RECI_NFE = PASTA_XML + "retConsReciNFe" + System.getProperty("file.separator");
    public static final String PASTA_XML_INUT_NFE = PASTA_XML + "inutNFe" + System.getProperty("file.separator");
    public static final String PASTA_XML_RET_INUT_NFE = PASTA_XML + "retInutNFe" + System.getProperty("file.separator");
    public static final String PASTA_XML_PROC_INUT_NFE = PASTA_XML + "procInutNFe" + System.getProperty("file.separator");
    public static final String PASTA_XML_EVENTO = PASTA_XML + "envEvento" + System.getProperty("file.separator");
    public static final String PASTA_XML_CONS_NFE_DEST = PASTA_XML + "consNFeDest" + System.getProperty("file.separator");
    public static final String PASTA_XML_RET_EVENTO = PASTA_XML + "retEnvEvento" + System.getProperty("file.separator");
    public static final Integer CASA_DECIMAL_QUANTIDADE = 0;
    public static final Integer CASA_DECIMAL_VALOR_VENDA = 2;
    public static final Integer CASA_DECIMAL_VALOR_COMPRA = 2;
    public static final Integer CASA_DECIMAL_VALOR_DEFALT = 2;
    public static final Double TEMPO_FECHA_AUTO = 0.03336027777777778; // total de 2 minutos
    public static final String TEXT_BUTTON_NOVO = "Novo";
    public static final String TEXT_BUTTON_APAGAR = "Apagar";
    public static final String TEXT_BUTTON_CANCELAR = "Cancelar";
    public static final String TEXT_BUTTON_SALVAR = "Salvar";
    public static final String JSSECACERTS = "certificados" + System.getProperty("file.separator") + "NFeCacerts";
    public static final int TIMEOUT_WS = 30;
    public static final int SSL_PORT = 443;
    //public static final int DIA_INICIO_FOLHA = 26;
    public static final String LINK_INSTALADOR = "https://drive.google.com/open?id=0B1_nXJkF5YiBTnVQVUR5UmNKRWs";
    public static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final Boolean LOG = true;
    public static final String PASTAS_SCHEMAS = "Schemas 4.00";
    public static Base base = new Base();

    public static String getBaseDefault() {
        String bases[] = INI.getString("LISTA BASE", "LISTA").split(",");
        return bases[0];
    }

    private static boolean fechaSystemaVendas;

    public static boolean isFechaSystemaVendas() {
        return fechaSystemaVendas;
    }

    public static void setFechaSystemaVendas(boolean fechaSystemaVendas) {
        JavaApplicationJsys.fechaSystemaVendas = fechaSystemaVendas;
    }

    /**
     * ConfiguracoesNfe config =
     * br.JavaApplicationJsys.iniciaConfigurações(par);
     *
     * @param jsysParametros
     * @return
     * @throws CertificadoException
     * @throws java.io.FileNotFoundException
     */
    public static ConfiguracoesNfe iniciaConfigurações(JsysParametros jsysParametros) throws FileNotFoundException, CertificadoException {
        Certificado certificado = CertificadoService.certificadoPfx(
                jsysParametros.getCaminhoDoCertificadoDoCliente(),
                jsysParametros.getSenhaDoCertificadoDoCliente());
        ConfiguracoesNfe config = ConfiguracoesNfe.criarConfiguracoes(
                EstadosEnum.getByCodigoIbge(EstadosEnum.valueOf(jsysParametros.getUf()).getCodigoUF()),
                AmbienteEnum.getByCodigo(ConstantesFiscal.AMBIENTE.TP_AMB),
                certificado,
                PASTAS_SCHEMAS);
        String ip = "192.168.0.1";
        int porta = 3128;
        String usuario = "";
        String senha = "";
        config.setProxy(new Proxy(ip, porta, usuario, senha));
        return config;
    }

    public static void main(String[] args) {
        boolean adapta = false;
        PropertyConfigurator.configure("log4j.properties");
        for (String s : args) {
            if ("adapta".equals(s)) {
                adapta = true;
                break;
            }
        }
        if (adapta) {
            Geral geral = new Geral();
            geral.setVisible(true);
        } else {
            LoginTela loginTela = new LoginTela();
            loginTela.setVisible(true);
            loginTela.autoLogin(args);
        }
    }
}
