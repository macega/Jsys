package br.sql.nfe.transmisor;

//import br.inf.portalfiscal.nfe.schema_4.consSitNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.consSitNFe.ObjectFactory;
//import br.inf.portalfiscal.nfe.schema_4.consSitNFe.TConsSitNFe;
import br.com.swconsultoria.nfe.schema_4.consSitNFe.TConsSitNFe;
//import br.inf.portalfiscal.www.nfe_400.wsdl.NFeConsultaProtocolo.NFeConsultaProtocolo4Stub;
import br.com.swconsultoria.nfe.wsdl.NFeConsultaProtocolo.NFeConsultaProtocolo4Stub;
import br.sql.bean.JsysParametros;
import br.sql.nfe.links.LigacaoServicos;
import br.sql.nfe.links.Servicos;
import br.sql.log.Log;
import br.sql.util.Retorna;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamException;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.AxisFault;

/**
 *
 * @author Juliano Alves Medina
 */
public class consultaNFeTransmitir implements transmitirInterface {

    private String mensagem;
    private final JsysParametros par;
    private String xml;

    public consultaNFeTransmitir() {
        par = Retorna.JsysParametros();
        mensagem = "Consulta Iniciada, Aguarde o Processo Finalizar.";
        xml = new String();
    }

    @Override
    public void setMensagem(String mensagen) {
        this.mensagem += System.lineSeparator();
        this.mensagem += mensagen;
    }

    @Override
    public String getMensagem() {
        return mensagem;
    }

    public String getXml() {
        return xml;
    }

    @Override
    public Boolean transmitir(String chaveAcesso, String tpAmb, int mod) {
        try {
            //String codigoDoEstado = String.valueOf(par.getCodMunicipio()).substring(0, 2);
            Servicos ser = new LigacaoServicos().ligacaoServicos(par.getUf(), tpAmb, mod);
            URL url = new URL(ser.getNfeConsultaProtocolo());

            try {
                Certificates.loadCertificates();
            } catch (KeyStoreException | NoSuchAlgorithmException e) {
                setMensagem(e.getMessage());
                Log.registraErro("consultaNFeTransmitir", "transmitir.certificado", e);
            } catch (CertificateException e) {
                setMensagem(e.getMessage());
                Log.registraErro("consultaNFeTransmitir", "transmitir.certificado", e);
            } catch (Exception e) {
                setMensagem(e.getMessage());
                Log.registraErro("consultaNFeTransmitir", "transmitir.certificado", e);
            }

            /**
             * TConsSitNFe de Consulta.
             */
            TConsSitNFe tConsSitNfe = new TConsSitNFe();
            tConsSitNfe.setVersao("3.10");
            tConsSitNfe.setTpAmb(tpAmb);
            tConsSitNfe.setXServ("CONSULTAR");
            tConsSitNfe.setChNFe(chaveAcesso);

            OMElement ome = AXIOMUtil.stringToOM(strValueOf(tConsSitNfe));

            NFeConsultaProtocolo4Stub.NfeDadosMsg dadosMsg = new NFeConsultaProtocolo4Stub.NfeDadosMsg();
            dadosMsg.setExtraElement(ome);
            //NFeConsultaProtocolo4Stub.NfeCabecMsg nfeCabecMsg = new NFeConsultaProtocolo4Stub.NfeCabecMsg();

            /**
             * Código do Estado.
             */
            //nfeCabecMsg.setCUF(codigoDoEstado);
            /**
             * Versao do XML
             */
            //nfeCabecMsg.setVersaoDados("3.10");
            //NFeConsultaProtocolo4Stub.NfeCabecMsgE nfeCabecMsgE = new NFeConsultaProtocolo4Stub.NfeCabecMsgE();
            //nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);
            NFeConsultaProtocolo4Stub stub = new NFeConsultaProtocolo4Stub(url.toString());
            NFeConsultaProtocolo4Stub.NfeResultMsg result = stub.nfeConsultaNF(dadosMsg);

            xml = result.getExtraElement().toString();
            setMensagem("Consulta Finalizada com Sucesso.");
            return true;
        } catch (MalformedURLException e) {
            setMensagem(e.getMessage());
            Log.registraErro("consultaNFeTransmitir", "transmitir.1", e);
        } catch (JAXBException | XMLStreamException | AxisFault e) {
            setMensagem(e.getMessage());
            Log.registraErro("consultaNFeTransmitir", "transmitir.2", e);
        } catch (RemoteException e) {
            setMensagem(e.getMessage());
            Log.registraErro("consultaNFeTransmitir", "transmitir.3", e);
        }
        return false;
    }

    /**
     * Método que Converte o Objeto em String.
     *
     * @param obj
     * @return
     * @throws JAXBException
     */
    private static String strValueOf(TConsSitNFe obj) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TConsSitNFe.class);
        Marshaller marshaller = context.createMarshaller();
        JAXBElement<TConsSitNFe> element = new ObjectFactory().createConsSitNFe(obj);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        marshaller.marshal(element, sw);

        String xml = sw.toString();
        xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
        xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + xml;
        return xml;
    }
}
