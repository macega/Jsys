package br.sql.nfe.transmisor;

import br.inf.portalfiscal.www.nfe_400.wsdl.NFeRecepcaoEvento.NFeRecepcaoEvento4Stub;
import br.sql.acesso.SQLDatabaseConnection;
import br.sql.bean.JsysNFeEvento;
import br.sql.bean.JsysParametros;
import br.sql.nfe.links.LigacaoServicos;
import br.sql.nfe.links.Servicos;
import br.sql.nfe.xml.GerandoRetXML;
import br.sql.log.Log;
import br.sql.util.GravaNoArquivo;
import br.sql.util.ManagerString;
import br.sql.util.Retorna;
import java.io.IOException;
import java.net.URL;
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
public class EventoTransmitir implements transmitirInterface {

    private final JsysParametros par;
    private String mensagem;
    private static final SQLDatabaseConnection DADOS = new SQLDatabaseConnection();

    public EventoTransmitir() {
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
     * @return retonar verdadeiro se operaçao realizada com sucesso
     */
    @Override
    public Boolean transmitir(String chaveAcesso, String tpAmb, int mod) {
        try {
            String codigoDoEstado = String.valueOf(par.getCodMunicipio()).substring(0, 2);
            Servicos ser = new LigacaoServicos().ligacaoServicos(par.getUf(), tpAmb, mod);
            URL url = new URL(ser.getRecepcaoEvento());
            //String nomeArquivo = Constantes.PASTA_XML_ENVI_NFE + chaveAcesso + ".xml";
            try {
                Certificates.loadCertificates();
            } catch (KeyStoreException | NoSuchAlgorithmException e) {
                Log.registraErro("EventoTransmitir", "transmitir", e);
            } catch (CertificateException e) {
                Log.registraErro("EventoTransmitir", "transmitir", e);
            } catch (Exception e) {
                Log.registraErro("EventoTransmitir", "transmitir", e);
            }

            /**
             * IMPORTANTE: O XML já deve ser assinado antes do envio. Lendo o
             * Xml de um arquivo Gerado.
             */
            OMElement om = AXIOMUtil.stringToOM(ManagerString.lerXML(br.JavaApplicationJsys.PASTA_XML_EVENTO + chaveAcesso + ".xml"));
            NFeRecepcaoEvento4Stub.NfeDadosMsg dadosMsg = new NFeRecepcaoEvento4Stub.NfeDadosMsg();
            dadosMsg.setExtraElement(om);
            //NFeRecepcaoEvento4Stub.NfeCabecMsg CabecMsg = new NFeRecepcaoEvento4Stub.NfeCabecMsg();
            /**
             * Código do Estado.
             */
            //CabecMsg.setCUF(codigoDoEstado);
            /**
             * Versao do XML.
             */
            //CabecMsg.setVersaoDados("1.00");

            //NFeRecepcaoEvento4Stub.NfeCabecMsgE nfeCabecMsgE = new NFeRecepcaoEvento4Stub.NfeCabecMsgE();
            //nfeCabecMsgE.setNfeCabecMsg(CabecMsg);
            NFeRecepcaoEvento4Stub stub = new NFeRecepcaoEvento4Stub(url.toString());
            NFeRecepcaoEvento4Stub.NfeResultMsg result = stub.nfeRecepcaoEvento(dadosMsg);

            GravaNoArquivo gravador = new GravaNoArquivo();
            gravador.salvarArquivo(result.getExtraElement().toString(), br.JavaApplicationJsys.PASTA_XML_RET_EVENTO, chaveAcesso, "xml");

            br.inf.portalfiscal.nfe.schema.retEnvEventoCancNFe.TRetEnvEvento retEnvEvento = GerandoRetXML.getTRetEnvEvento(result.getExtraElement().toString());

            if ("128".equals(retEnvEvento.getCStat())) {
                for (br.inf.portalfiscal.nfe.schema.retEnvEventoCancNFe.TRetEvento r : retEnvEvento.getRetEvento()) {
                    if ("135".equals(r.getInfEvento().getCStat())) {
                        setMensagem(r.getInfEvento().getXMotivo());
                        java.util.Map<Object, Object> filtro = new java.util.HashMap<>();
                        filtro.put("chNFe", r.getInfEvento().getChNFe());
                        JsysNFeEvento evento = (JsysNFeEvento) Retorna.findOneResult("JsysNFeEvento.findByChNFe", filtro);
                        if (evento != null) {
                            StringBuilder sql = new StringBuilder();
                            sql.append("UPDATE jsysNFeEvento SET emitida = 1, retEnvEventoCancNFe = '");
                            sql.append(result.getExtraElement().toString());
                            sql.append("' WHERE chNFe = '").append(r.getInfEvento().getChNFe()).append("'");

                            StringBuilder sql2 = new StringBuilder();
                            sql2.append("UPDATE jsysNFe SET cancelada = 1");
                            sql2.append("WHERE chaveAcesso = 'NFe");
                            sql2.append(r.getInfEvento().getChNFe());
                            sql2.append("'");

                            if (DADOS.execSQLUpdate(sql) == 0) {
                                Log.registraErro(this.getClass().getName(), "transmitir", new Exception("erro ao tentar executar o Update na tabela jsysNFeEvento"));
                                return false;
                            } else if (DADOS.execSQLUpdate(sql2) == 0) {
                                Log.registraErro(this.getClass().getName(), "transmitir", new Exception("erro ao tentar executar o Update na tabela jsysNFe"));
                                return false;
                            } else {
                                return true;
                            }
                        }
                    } else {
                        setMensagem(r.getInfEvento().getCStat() + " - " + r.getInfEvento().getXMotivo());
                    }
                }
            } else {
                setMensagem(retEnvEvento.getCStat() + " - " + retEnvEvento.getXMotivo());
            }
        } catch (IOException | XMLStreamException e) {
            Log.registraErro(this.getClass().getName(), "transmitir", e);
        }
        return false;
    }

}
