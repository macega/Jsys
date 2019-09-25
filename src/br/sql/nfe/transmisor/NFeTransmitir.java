package br.sql.nfe.transmisor;

import br.com.samuelweb.nfe.dom.ConfiguracoesNfe;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.ObjetoUtil;
import br.com.samuelweb.nfe.util.WebServiceUtil;
import br.inf.portalfiscal.www.nfe_400.wsdl.NFeAutorizacao.NFeAutorizacao4Stub;
import br.sql.acesso.SQLDatabaseConnection;
import br.sql.bean.JsysNFe;
import br.sql.bean.JsysParametros;
import br.sql.nfe.links.ConstantesFiscal;
import br.sql.nfe.xml.GerandoRetXML;
import br.sql.log.Log;
import br.sql.util.GravaNoArquivo;
import br.sql.util.ManagerString;
import br.sql.util.Retorna;
import java.io.IOException;
import java.io.StringReader;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Iterator;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.util.StAXParserConfiguration;
import org.apache.axis2.transport.http.HTTPConstants;
import org.xml.sax.InputSource;

/**
 *
 * @author Juliano Alves Medina
 */
public class NFeTransmitir implements transmitirInterface {

    private final JsysParametros par;
    private String mensagem;
    private static final SQLDatabaseConnection DADOS = new SQLDatabaseConnection();

    public NFeTransmitir() {
        par = Retorna.JsysParametros();
        this.mensagem = new String();
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

    @Override
    public Boolean transmitir(String chaveAcesso, String tpAmb, int mod) throws Exception {
        return enviar(chaveAcesso, tpAmb, mod, ManagerString.lerXML(br.JavaApplicationJsys.PASTA_XML_ENVI_NFE + chaveAcesso + ".xml"));
    }

    public Boolean transmitir(String chaveAcesso, String tpAmb, int mod, String xml) throws Exception {
        return enviar(chaveAcesso, tpAmb, mod, xml);
    }

    /**
     *
     * @param chaveAcesso
     * @param tpAmb
     * @param mod
     * @return retonar verdadeiro se operaçao realizada com sucesso
     */
    private Boolean enviar(String chaveAcesso, String tpAmb, int mod, String XML) throws Exception {
        Boolean ret = false;
        boolean nfce = ConstantesFiscal.NFC_E == mod;
        ConfiguracoesNfe config = br.JavaApplicationJsys.iniciaConfigurações(par);
        try {
            //String codigoDoEstado = String.valueOf(par.getCodMunicipio()).substring(0, 2);
            //Servicos ser = new LigacaoServicos().ligacaoServicos(par.getUf(), tpAmb, mod);
            //URL url = new URL(ser.getNFeAutorizacao());
            try {
                Certificates.loadCertificates();
            } catch (KeyStoreException | NoSuchAlgorithmException e) {
                setMensagem(e.getMessage());
                Log.registraErro("NFeTranmitir", "transmitir", e);
            } catch (CertificateException e) {
                setMensagem(e.getMessage());
                Log.registraErro("NFeTranmitir", "transmitir", e);
            } catch (Exception e) {
                setMensagem(e.getMessage());
                Log.registraErro("NFeTranmitir", "transmitir", e);
            }
            /**
             * IMPORTANTE: O XML já deve ser assinado antes do envio. Lendo o
             * Xml de um arquivo Gerado.
             */
            //OMElement ome = AXIOMUtil.stringToOM(XML);

            OMFactory factory = OMAbstractFactory.getOMFactory();
            OMElement ome = factory.getMetaFactory().createOMBuilder(factory,
                    StAXParserConfiguration.NON_COALESCING,
                    new InputSource(new StringReader(XML))).getDocumentElement();

            Iterator<?> children = ome.getChildrenWithLocalName("NFe");
            while (children.hasNext()) {
                OMElement omElement = (OMElement) children.next();
                if ((omElement != null) && ("NFe".equals(omElement.getLocalName()))) {
                    omElement.addAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe", null);
                }
            }
            
//            if (nfce) {
//                GerandoNFeTNFe cast = new GerandoNFeTNFe();
//                TEnviNFe nfe = cast.CastTEnviNFe(XML);
//                for (int i = 0; i < nfe.getNFe().size(); i++) {
//                    DanfeNFCeQRCode code = new DanfeNFCeQRCode();
//                    code.setUrlConsulta(ser.getNFCeConsultaQRCode());
//                    code.setChNFe(nfe.getNFe().get(i).getInfNFe().getId());
//                    code.setnVersao("100");
//                    code.setTpAmb(nfe.getNFe().get(i).getInfNFe().getIde().getTpAmb());
//                    if (nfe.getNFe().get(i).getInfNFe().getDest() != null) {
//                        String cDest = new String();
//                        if (nfe.getNFe().get(i).getInfNFe().getDest().getCNPJ() != null) {
//                            cDest = nfe.getNFe().get(i).getInfNFe().getDest().getCNPJ();
//                        }
//                        if (nfe.getNFe().get(i).getInfNFe().getDest().getCPF() != null) {
//                            cDest = nfe.getNFe().get(i).getInfNFe().getDest().getCPF();
//                        }
//                        if (nfe.getNFe().get(i).getInfNFe().getDest().getIdEstrangeiro() != null) {
//                            cDest = nfe.getNFe().get(i).getInfNFe().getDest().getIdEstrangeiro();
//                        }
//                        code.setcDest(cDest);
//                    }
//                    code.setDhEmi(nfe.getNFe().get(i).getInfNFe().getIde().getDhEmi());
//                    code.setvNF(nfe.getNFe().get(i).getInfNFe().getTotal().getICMSTot().getVNF());
//                    code.setvICMS(nfe.getNFe().get(i).getInfNFe().getTotal().getICMSTot().getVICMS());
//                    try {
//                        code.setDigVal(nfe.getNFe().get(i).getSignature().getSignedInfo().getReference().getDigestValue());
//                    } catch (UnsupportedEncodingException e) {
//                        Log.registraErro("ImprimirFiscal", "nfce", e);
//                    }
//                    code.setcIdToken(par.getcIdToken());
//                    code.setCSC(par.getCSC());
//                    //document.getDocumentElement().getElementsByTagName("qrCode").item(i).setTextContent(code.getCodeQRCode());
//
//                    OMFactory f = OMAbstractFactory.getOMFactory();
//                    OMText omt = f.createOMText(code.getCodeQRCode(), OMElement.CDATA_SECTION_NODE);
//
//                    Iterator<?> itNfe = ome.getChildrenWithLocalName("NFe");
//                    while (itNfe.hasNext()) {
//                        Object elementNfe = itNfe.next();
//                        if (elementNfe instanceof OMElement) {
//                            OMElement omElementNfe = (OMElement) elementNfe;
//                            Iterator<?> itInfSupl = omElementNfe.getChildrenWithLocalName("infNFeSupl");
//                            while (itInfSupl.hasNext()) {
//                                Object elementInfSupl = itInfSupl.next();
//                                if (elementInfSupl instanceof OMElement) {
//                                    OMElement omElementInfSupl = (OMElement) elementInfSupl;
//                                    Iterator<?> itqrCode = omElementInfSupl.getChildrenWithLocalName("qrCode");
//                                    while (itqrCode.hasNext()) {
//                                        Object elementQrCode = itqrCode.next();
//                                        if (elementQrCode instanceof OMElement) {
//                                            OMElement omElementQrCode = (OMElement) elementQrCode;
//                                            omElementQrCode.addChild(omt);
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//
//                StringBuilder sql = new StringBuilder();
//                sql.append("UPDATE jsysNFe SET enviNFe = '");
//                sql.append(ome.toString());
//                sql.append("' WHERE chaveAcesso = '").append(chaveAcesso).append("'");
//                if (DADOS.execSQLUpdate(sql) == 0) {
//                    return false;
//                }
//                GravaNoArquivo gravador = new GravaNoArquivo();
//                gravador.salvarArquivo(ome.toString(), br.JavaApplicationJsys.PASTA_XML_ENVI_NFE, chaveAcesso, "xml");
//            }

//            NFeAutorizacao4Stub.NfeDadosMsg dadosMsg = new NFeAutorizacao4Stub.NfeDadosMsg();
//            dadosMsg.setExtraElement(ome);
//            //NFeAutorizacao4Stub.NfeCabecMsg nfeCabecMsg = new NFeAutorizacao4Stub.NfeCabecMsg();
//            /**
//             * Código do Estado.
//             */
//            //nfeCabecMsg.setCUF(codigoDoEstado);
//            /**
//             * Versao do XML.
//             */
//            //nfeCabecMsg.setVersaoDados("3.10");
//            //NFeAutorizacao4Stub.NfeCabecMsgE nfeCabecMsgE = new NFeAutorizacao4Stub.NfeCabecMsgE();
//            //nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);
//            
//            NFeAutorizacao4Stub stub = new NFeAutorizacao4Stub(url.toString());
//            NFeAutorizacao4Stub.NfeResultMsg result = stub.nfeAutorizacaoLote(dadosMsg);
            ///////////////////////////////////////
            //ConfiguracoesNfe config = JavaApplicationJsys.iniciaConfigurações(par);
            NFeAutorizacao4Stub.NfeDadosMsg dadosMsg = new NFeAutorizacao4Stub.NfeDadosMsg();
            dadosMsg.setExtraElement(ome);

            NFeAutorizacao4Stub stub = new NFeAutorizacao4Stub(
                    nfce ? WebServiceUtil.getUrl(config, ConstantesUtil.NFCE, ConstantesUtil.SERVICOS.ENVIO)
                            : WebServiceUtil.getUrl(config, ConstantesUtil.NFE, ConstantesUtil.SERVICOS.ENVIO));
            // Timeout
            if (!ObjetoUtil.isEmpty(config.getTimeout())) {
                stub._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeout());
                stub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT,
                        config.getTimeout());
            }
            NFeAutorizacao4Stub.NfeResultMsg result = stub.nfeAutorizacaoLote(dadosMsg);

            //return XmlUtil.xmlToObject(result.getExtraElement().toString(), TRetEnviNFe.class);
            ////////////////////////////////
            GravaNoArquivo gravador = new GravaNoArquivo();
            gravador.salvarArquivo(result.getExtraElement().toString(), 
                    br.JavaApplicationJsys.PASTA_XML_RET_CONS_RECI_NFE, 
                    chaveAcesso, 
                    "xml");

            br.inf.portalfiscal.nfe.schema_4.retEnviNFe.TRetEnviNFe retEnviNFe = GerandoRetXML.getTRetEnviNFe(result.getExtraElement().toString());
            java.util.Map<Object, Object> filtro = new java.util.HashMap<>();
            filtro.put("chaveAcesso", chaveAcesso);
            JsysNFe nfe = (JsysNFe) Retorna.findOneResult("JsysNFe.findByChaveAcesso", filtro);
            //String emitida = "0";
            if (nfe != null) {
                String emitida = nfe.getEmitida() ? "1" : "0";
                if ("104".equals(retEnviNFe.getCStat())) {
                    String csStat = retEnviNFe.getProtNFe().getInfProt().getCStat();
                    if ("100".equals(csStat)
                            | "301".equals(csStat)
                            | "302".equals(csStat)
                            | "303".equals(csStat)
                            | "999".equals(csStat)) {
                        setMensagem(retEnviNFe.getProtNFe().getInfProt().getXMotivo());
                        setMensagem(retEnviNFe.getProtNFe().getInfProt().getChNFe());
                        emitida = "1";
                        ret = true;
                    } else {
                        setMensagem(retEnviNFe.getProtNFe().getInfProt().getCStat() + " - " + retEnviNFe.getProtNFe().getInfProt().getXMotivo());
                        setMensagem(retEnviNFe.getProtNFe().getInfProt().getChNFe());
                        ret = false;
                    }
                } else {
                    setMensagem(retEnviNFe.getCStat() + " - " + retEnviNFe.getXMotivo());
                    ret = false;
                }
                StringBuilder sql = new StringBuilder();
                sql.append("UPDATE jsysNFe SET emitida = ").append(emitida);
                sql.append(", retConsReciNFe = '").append(result.getExtraElement().toString());
                sql.append("' WHERE chaveAcesso = '").append(chaveAcesso).append("'");
                if (DADOS.execSQLUpdate(sql) == 0) {
                    Log.registraErro(this.getClass().getName(), "transmitir", new Exception("erro ao tentar executar o Update na tabela jsysNFe"));
                }
            } else {
                ret = false;
            }
        } catch (IOException e) {
            setMensagem(e.getMessage());
            Log.registraErro(this.getClass().getName(), "transmitir", e);
        }
        return ret;
    }
}
