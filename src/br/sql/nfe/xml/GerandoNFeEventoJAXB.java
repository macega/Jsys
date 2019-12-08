package br.sql.nfe.xml;

import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.dom.Enum.StatusEnum;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TEvento;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TProcEvento;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TRetEvento;
//import br.inf.portalfiscal.nfe.schema.retEnvEventoCancNFe.TRetEnvEvento;
import br.inf.portalfiscal.nfe.schema_4.procNFe.TNfeProc;
import br.sql.acesso.ConnectionFactory;
import br.sql.acesso.SQLDatabaseConnection;
import br.sql.bean.JsysNFe;
import br.sql.bean.JsysNFeEvento;
import br.sql.bean.JsysParametros;
import br.sql.log.Log;
import br.sql.util.GravaNoArquivo;
import br.sql.util.ManagerData;
import br.sql.util.ManagerString;
import br.sql.util.Retorna;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Juliano Alves Medina
 */
public class GerandoNFeEventoJAXB {

    private JsysNFeEvento jsysNFeEvento;
    private ConfiguracoesIniciaisNfe config;
    private TRetEnvEvento tRetEnvEvento;
    private JsysParametros PAR;
    private TEnvEvento enviEvento;
    private SQLDatabaseConnection dados;
    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem += System.lineSeparator();
        this.mensagem += mensagem;
    }

    public GerandoNFeEventoJAXB(String chaveAcesso, String xJust) {
        jsysNFeEvento = new JsysNFeEvento();
        PAR = Retorna.JsysParametros();
        enviEvento = new TEnvEvento();
        mensagem = new String();
        dados = new SQLDatabaseConnection();
        try {
            config = br.JavaApplicationJsys.iniciaConfigurações(PAR);
            if (!"".equals(chaveAcesso)) {
                Map<Object, Object> filtro = new HashMap<>();
                filtro.put("chaveAcesso", chaveAcesso);
                JsysNFe nfe = (JsysNFe) Retorna.findOneResult("JsysNFe.findByChaveAcesso", filtro);
                if (nfe != null) {
                    TNfeProc NFeProc = XmlUtil.xmlToObject(XmlUtil.criaNfeProc(nfe.getEnviNFe(), nfe.getRetConsReciNFe()), TNfeProc.class);

                    jsysNFeEvento.setCOrgao(NFeProc.getNFe().getInfNFe().getIde().getCUF());
                    jsysNFeEvento.setChNFe(NFeProc.getProtNFe().getInfProt().getChNFe());
                    jsysNFeEvento.setTpAmb(config.getAmbiente());
                    jsysNFeEvento.setDhEvento(ManagerData.getDate());
                    jsysNFeEvento.setTpEvento(ConstantesUtil.EVENTO.CANCELAR);
                    jsysNFeEvento.setNSeqEvento(1);
                    jsysNFeEvento.setDescEvento("Cancelamento");
                    jsysNFeEvento.setNProt(NFeProc.getProtNFe().getInfProt().getNProt());
                    jsysNFeEvento.setXJust(xJust);
                    jsysNFeEvento.setEmitida(false);

                    StringBuilder id = new StringBuilder();
                    id.append("ID");
                    id.append(jsysNFeEvento.getTpEvento());
                    id.append(jsysNFeEvento.getChNFe());
                    id.append(ManagerString.zeros(String.valueOf(jsysNFeEvento.getNSeqEvento()), 2));
                    jsysNFeEvento.setIdEvento(id.toString());

                    //cnpj = NFeProc.getNFe().getInfNFe().getEmit().getCNPJ();
                    //motivo = xJust;
                    //nSeqEvento = "01";
                    //id = "ID" + ConstantesUtil.EVENTO.CANCELAR + chave + nSeqEvento;
                }
            }
        } catch (CertificadoException | NfeException | JAXBException ex) {
            jsysNFeEvento = null;
            Log.registraErro(GerandoNFeEventoJAXB.class, "GerandoNFeEventoJAXB", ex);
        }
//        if (!"".equals(chaveAcesso)) {
//            Map<Object, Object> filtro = new HashMap<>();
//            filtro.put("chaveAcesso", chaveAcesso);
//            JsysNFe nfe = (JsysNFe) Retorna.findOneResult("JsysNFe.findByChaveAcesso", filtro);
//            if (nfe != null) {
//                try {
//
//                    // TNfeProc NFeProc = GerandoNFeProc.gerar(nfe.getEnviNFe(), nfe.getRetConsReciNFe());
//                    TNfeProc NFeProc = XmlUtil.xmlToObject(XmlUtil.criaNfeProc(nfe.getEnviNFe(), nfe.getRetConsReciNFe()), TNfeProc.class);
//                    event.setTpAmb(NFeProc.getNFe().getInfNFe().getIde().getTpAmb());
//                    // Verificar se esta correto.
//                    event.setCOrgao(NFeProc.getNFe().getInfNFe().getIde().getCUF());
//                    event.setChNFe(NFeProc.getProtNFe().getInfProt().getChNFe());
//                    event.setDhEvento(ManagerData.getDate());
//                    event.setTpEvento("110111");
//                    event.setNSeqEvento(1);
//                    event.setDescEvento("Cancelamento");
//                    event.setNProt(NFeProc.getProtNFe().getInfProt().getNProt());
//                    event.setXJust(xJust);
//
//                    StringBuilder id = new StringBuilder();
//                    id.append("ID");
//                    id.append(event.getTpEvento());
//                    id.append(event.getChNFe());
//                    id.append(ManagerString.zeros(String.valueOf(event.getNSeqEvento()), 2));
//
//                    idEvent = id.toString();
//                    event.setIdEvento(idEvent);
//                    event.setEmitida(false);
//                } catch (JAXBException | NfeException ex) {
//                    Log.registraErro(GerandoNFeEventoJAXB.class, "GerandoNFeEventoJAXB", ex);
//                }
//            }
//        }
    }

    public boolean gerarEnviEvento() {
        try {
            if (jsysNFeEvento.getIdEvento() != null) {
                enviEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);
                enviEvento.setIdLote("1");

                TEvento eventoCancela = new TEvento();
                eventoCancela.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);

                TEvento.InfEvento infoEvento = new TEvento.InfEvento();
                infoEvento.setId(jsysNFeEvento.getIdEvento());
                infoEvento.setChNFe(jsysNFeEvento.getChNFe());
                infoEvento.setCOrgao(String.valueOf(config.getEstado().getCodigoIbge()));
                infoEvento.setTpAmb(config.getAmbiente());
                infoEvento.setCNPJ(ManagerString.RemoveFormatoCpfCnpj(PAR.getCnpj()));
                infoEvento.setDhEvento(XmlUtil.dataNfe());
                infoEvento.setTpEvento(ConstantesUtil.EVENTO.CANCELAR);
                infoEvento.setNSeqEvento(String.valueOf(jsysNFeEvento.getNSeqEvento()));
                infoEvento.setVerEvento(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);

                TEvento.InfEvento.DetEvento detEvento = new TEvento.InfEvento.DetEvento();
                detEvento.setVersao(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);
                
                detEvento.setDescEvento("Cancelamento");
                detEvento.setNProt(jsysNFeEvento.getNProt());
                detEvento.setXJust(jsysNFeEvento.getXJust());
                infoEvento.setDetEvento(detEvento);
                eventoCancela.setInfEvento(infoEvento);
                enviEvento.getEvento().add(eventoCancela);

                GravaNoArquivo gravador = new GravaNoArquivo();
                String XmlEvento = XmlUtil.objectToXml(enviEvento);
                gravador.salvarArquivo(XmlEvento, br.JavaApplicationJsys.PASTA_XML_EVENTO, jsysNFeEvento.getIdEvento(), "xml");
                jsysNFeEvento.setEnvEventoCancNFe(XmlEvento);
                jsysNFeEvento = (JsysNFeEvento) ConnectionFactory.insert(jsysNFeEvento);
                return jsysNFeEvento != null;
            }
        } catch (NfeException | JAXBException e) {
            Log.registraErro(GerandoNFeEventoJAXB.class, "gerar", e);
        }
        return false;
    }

    public boolean enviar() throws NfeException, JAXBException {
        //try {
        tRetEnvEvento = Nfe.cancelarNfe(enviEvento, true, ConstantesUtil.NFE);
        
        System.out.println(tRetEnvEvento.getClass().getSimpleName());
        System.out.println(tRetEnvEvento.getClass().getName());
        
        //String xmltRetEnvEvento = strValueOf(tRetEnvEvento);
        
        //System.out.println(xmltRetEnvEvento);

//        if (!StatusEnum.LOTE_EVENTO_PROCESSADO.getCodigo().equals(tRetEnvEvento.getCStat())) {
//            throw new NfeException("Status:" + tRetEnvEvento.getCStat() + " - Motivo:" + tRetEnvEvento.getXMotivo());
//        }
//
//        if (!StatusEnum.EVENTO_VINCULADO.getCodigo().equals(tRetEnvEvento.getRetEvento().get(0).getInfEvento().getCStat())) {
//            throw new NfeException("Status:" + tRetEnvEvento.getRetEvento().get(0).getInfEvento().getCStat() + " - Motivo:" + tRetEnvEvento.getRetEvento().get(0).getInfEvento().getXMotivo());
//        }

        // Cria TProcEventoNFe
        TProcEvento procEvento = new TProcEvento();
        procEvento.setVersao("1.00");
        procEvento.setEvento(enviEvento.getEvento().get(0));
        for (TRetEvento tRetEvento : tRetEnvEvento.getRetEvento()) {
            procEvento.setRetEvento(tRetEvento);
        }
        //procEvento.setRetEvento(tRetEnvEvento.getRetEvento().get(0));

        GravaNoArquivo gravador = new GravaNoArquivo();
        String xmlProcEvento = XmlUtil.objectToXml(procEvento);
        gravador.salvarArquivo(xmlProcEvento, br.JavaApplicationJsys.PASTA_XML_RET_EVENTO, jsysNFeEvento.getIdEvento(), "xml");

        System.out.println("Status:" + tRetEnvEvento.getRetEvento().get(0).getInfEvento().getCStat());
        System.out.println("Motivo:" + tRetEnvEvento.getRetEvento().get(0).getInfEvento().getXMotivo());
        System.out.println("Data:" + tRetEnvEvento.getRetEvento().get(0).getInfEvento().getDhRegEvento());

        if ("128".equals(tRetEnvEvento.getCStat())) {
            for (br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TRetEvento r : tRetEnvEvento.getRetEvento()) {
                if ("135".equals(r.getInfEvento().getCStat())) {
                    setMensagem(r.getInfEvento().getXMotivo());
                    java.util.Map<Object, Object> filtro = new java.util.HashMap<>();
                    filtro.put("chNFe", r.getInfEvento().getChNFe());
                    JsysNFeEvento evento = (JsysNFeEvento) Retorna.findOneResult("JsysNFeEvento.findByChNFe", filtro);
                    if (evento != null) {
                        StringBuilder sql = new StringBuilder();
                        sql.append("UPDATE jsysNFeEvento SET emitida = 1, retEnvEventoCancNFe = '");
                        sql.append(xmlProcEvento);
                        sql.append("' WHERE chNFe = '").append(r.getInfEvento().getChNFe()).append("'");

                        StringBuilder sql2 = new StringBuilder();
                        sql2.append("UPDATE jsysNFe SET cancelada = 1");
                        sql2.append("WHERE chaveAcesso = 'NFe");
                        sql2.append(r.getInfEvento().getChNFe());
                        sql2.append("'");

                        if (dados.execSQLUpdate(sql) == 0) {
                            Log.registraErro(this.getClass().getName(), "transmitir", new Exception("erro ao tentar executar o Update na tabela jsysNFeEvento"));
                            return false;
                        } else if (dados.execSQLUpdate(sql2) == 0) {
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
            setMensagem(tRetEnvEvento.getCStat() + " - " + tRetEnvEvento.getXMotivo());
        }
        return false;
    }

//    public void criaProcEvento() throws JAXBException, NfeException {
//        // Cria TProcEventoNFe
//        TProcEvento procEvento = new TProcEvento();
//        procEvento.setVersao("1.00");
//        procEvento.setEvento(enviEvento.getEvento().get(0));
//        procEvento.setRetEvento(tRetEnvEvento.getRetEvento().get(0));
//
//        System.out.println(XmlUtil.objectToXml(procEvento));
//    }
    
    
    
//    /**
//     * Método que Converte o Objeto em String.
//     *
//     * @param tInutNFe
//     * @return
//     * @throws JAXBException
//     */
//    private static String strValueOf(TRetEnvEvento tInutNFe) throws JAXBException {
//        JAXBContext context = JAXBContext.newInstance(br.inf.portalfiscal.nfe.schema.retEnvEventoCancNFe.TRetEnvEvento.class);
//        Marshaller marshaller = context.createMarshaller();
//        JAXBElement<br.inf.portalfiscal.nfe.schema.retEnvEventoCancNFe.TRetEnvEvento> element = 
//                new br.inf.portalfiscal.nfe.schema.retEnvEventoCancNFe.ObjectFactory().createRetEnvEvento(tInutNFe);
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
//        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
//
//        StringWriter sw = new StringWriter();
//        marshaller.marshal(element, sw);
//
//        String xml = sw.toString();
//        //xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
//        //xml = xml.replaceAll("<NFe>", "<NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\">");
//        return xml;
//    }
    
    
    
    
//    private TEvento getTEvento() {
//        TEvento tEvento = new TEvento();
//        tEvento.setVersao("1.00");
//
//        TEvento.InfEvento infEvento = new TEvento.InfEvento();
//        infEvento.setId(idEvent);
//        infEvento.setCOrgao(event.getCOrgao());
//        infEvento.setTpAmb(event.getTpAmb());
//        infEvento.setCNPJ(ManagerString.RemoveFormatoCpfCnpj(par.getCnpj()));
//        infEvento.setChNFe(event.getChNFe());
//        infEvento.setDhEvento(ManagerData.convertDate(event.getDhEvento(), "yyyy-MM-dd'T'HH:mm:ssXXX"));
//        infEvento.setTpEvento(event.getTpEvento());
//        infEvento.setNSeqEvento(String.valueOf(event.getNSeqEvento()));
//        infEvento.setVerEvento("1.00");
//        tEvento.setInfEvento(infEvento);
//
//        TEvento.InfEvento.DetEvento detEvento = new TEvento.InfEvento.DetEvento();
//        detEvento.setVersao("1.00");
//        detEvento.setDescEvento(event.getDescEvento());
//        detEvento.setNProt(event.getNProt());
//        detEvento.setXJust(event.getXJust());
//        tEvento.getInfEvento().setDetEvento(detEvento);
//
//        return tEvento;
//    }
//    public String getIdEvento() {
//        return idEvent;
//    }
}
