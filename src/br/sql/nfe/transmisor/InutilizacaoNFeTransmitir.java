package br.sql.nfe.transmisor;

import br.inf.portalfiscal.www.nfe_400.wsdl.NFeInutilizacao.NFeInutilizacao4Stub;
import br.sql.acesso.SQLDatabaseConnection;
import br.sql.bean.JsysNFeInut;
import br.sql.bean.JsysParametros;
import br.sql.nfe.links.LigacaoServicos;
import br.sql.nfe.links.Servicos;
import br.sql.log.Log;
import br.sql.util.GravaNoArquivo;
import br.sql.util.ManagerString;
import br.sql.util.Retorna;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.xml.stream.XMLStreamException;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

/**
 *
 * @author Juliano Alves Medina
 */
public class InutilizacaoNFeTransmitir implements transmitirInterface {

    private static JsysParametros par;
    private String mensagem;
    private static final SQLDatabaseConnection DADOS = new SQLDatabaseConnection();

    public InutilizacaoNFeTransmitir() {
        par = Retorna.JsysParametros();
        mensagem = new String();
    }

    @Override
    public String getMensagem() {
        return mensagem;
    }

    @Override
    public void setMensagem(String mensagem) {
        this.mensagem += System.lineSeparator();
        this.mensagem += mensagem;
    }

    /**
     *
     * @param chaveAcesso
     * @param tpAmb
     * @param mod
     * @return
     */
    @Override
    public Boolean transmitir(String chaveAcesso, String tpAmb, int mod) {
        try {
            //String codigoDoEstado = String.valueOf(parametros.getCodMunicipio()).substring(0, 2);
            Servicos ser = new LigacaoServicos().ligacaoServicos(par.getUf(), tpAmb, mod);
            URL url = new URL(ser.getNfeInutilizacao());

            try {
                Certificates.loadCertificates();
            } catch (KeyStoreException | NoSuchAlgorithmException e) {
                Log.registraErro("InutilizacaoNFeTranmitir", "transmitir", e);
            } catch (CertificateException e) {
                Log.registraErro("InutilizacaoNFeTranmitir", "transmitir", e);
            } catch (Exception e) {
                Log.registraErro("InutilizacaoNFeTranmitir", "transmitir", e);
            }

            /**
             * IMPORTANTE: O XML já deve estar assinado antes do envio. Lendo o
             * Xml de um arquivo Gerado.
             */
            OMElement ome = AXIOMUtil.stringToOM(ManagerString.lerXML(br.JavaApplicationJsys.PASTA_XML_INUT_NFE + chaveAcesso + ".xml"));
            NFeInutilizacao4Stub.NfeDadosMsg dadosMsg = new NFeInutilizacao4Stub.NfeDadosMsg();
            dadosMsg.setExtraElement(ome);

            //NFeInutilizacao4Stub.NfeCabecMsg nfeCabecMsg = new NFeInutilizacao4Stub.NfeCabecMsg();
            /**
             * Codigo do Estado.
             */
            //nfeCabecMsg.setCUF(codigoDoEstado);

            /**
             * Versao do XML
             */
            //nfeCabecMsg.setVersaoDados("3.10");
            //NFeInutilizacao4Stub.NfeCabecMsgE nfeCabecMsgE = new NFeInutilizacao4Stub.NfeCabecMsgE();
            //nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);

            NFeInutilizacao4Stub stub = new NFeInutilizacao4Stub(url.toString());
            NFeInutilizacao4Stub.NfeResultMsg result = stub.nfeInutilizacaoNF(dadosMsg);
            String retInutNFe = result.getExtraElement().toString();

            GravaNoArquivo gravador = new GravaNoArquivo();
            gravador.salvarArquivo(retInutNFe, br.JavaApplicationJsys.PASTA_XML_RET_INUT_NFE, chaveAcesso, "xml");

            java.util.Map<Object, Object> filtro = new java.util.HashMap<>();
            filtro.put("idInut", chaveAcesso);
            JsysNFeInut nfeInut = (JsysNFeInut) Retorna.findOneResult("JsysNFeInut.findByIdInut", filtro);
            if (nfeInut != null) {
                StringBuilder sql = new StringBuilder();
                sql.append("UPDATE jsysNFeInut SET emitida = 1, xmlRetInutNFe = '");
                sql.append(retInutNFe);
                sql.append("' WHERE idInut = '").append(chaveAcesso).append("'");
                if (DADOS.execSQLUpdate(sql) == 0) {
                    Log.registraErro(this.getClass().getName(), "transmitir", new Exception("erro ao tentar executar o Update na tabela jsysNFeInut"));
                    return false;
                }
            }
            return true;
        } catch (MalformedURLException | RemoteException | XMLStreamException e) {
            Log.registraErro(this.getClass().getName(), "transmitir", e);
            return false;
        }
    }

}
