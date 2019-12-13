package br.sql.nfe.xml;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.EventosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.util.ConstantesUtil;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TProcEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEvento;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNfeProc;
import br.sql.acesso.ConnectionFactory;
import br.sql.acesso.SQLDatabaseConnection;
import br.sql.bean.JsysNFe;
import br.sql.bean.JsysNFeEvento;
import br.sql.bean.JsysParametros;
import br.sql.log.Log;
import br.sql.nfe.links.ConstantesFiscal;
import br.sql.nfe.util.XmlUtil;
import br.sql.util.GravaNoArquivo;
import br.sql.util.ManagerData;
import br.sql.util.ManagerString;
import br.sql.util.Retorna;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Juliano Alves Medina
 */
public class GerandoNFeEventoJAXB {

    private JsysNFeEvento jsysNFeEvento;
    private ConfiguracoesNfe configuracoesNfe;
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
        try {
            jsysNFeEvento = new JsysNFeEvento();
            PAR = Retorna.JsysParametros();
            enviEvento = new TEnvEvento();
            mensagem = new String();
            dados = new SQLDatabaseConnection();
            configuracoesNfe = br.JavaApplicationJsys.iniciaConfigurações(PAR);
            if (!"".equals(chaveAcesso)) {
                Map<Object, Object> filtro = new HashMap<>();
                filtro.put("chaveAcesso", chaveAcesso);
                JsysNFe nfe = (JsysNFe) Retorna.findOneResult("JsysNFe.findByChaveAcesso", filtro);
                if (nfe != null) {
                    TNfeProc NFeProc = XmlUtil.xmlToObject(XmlUtil.criaNfeProc(nfe.getEnviNFe(), nfe.getRetConsReciNFe()), TNfeProc.class);
                    jsysNFeEvento.setCOrgao(NFeProc.getNFe().getInfNFe().getIde().getCUF());
                    jsysNFeEvento.setChNFe(NFeProc.getProtNFe().getInfProt().getChNFe());
                    jsysNFeEvento.setTpAmb(configuracoesNfe.getAmbiente().getCodigo());
                    jsysNFeEvento.setDhEvento(ManagerData.getDate());
                    jsysNFeEvento.setTpEvento(EventosEnum.CANCELAMENTO.getCodigo());
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
                }
            }
        } catch (FileNotFoundException | JAXBException | NfeException ex) {
            jsysNFeEvento = null;
            Log.registraErro(GerandoNFeEventoJAXB.class, "GerandoNFeEventoJAXB", ex);
        }
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
                infoEvento.setCOrgao(EstadosEnum.valueOf(PAR.getUf()).getCodigoUF());
                infoEvento.setTpAmb(AmbienteEnum.getByCodigo(ConstantesFiscal.AMBIENTE.TP_AMB).getCodigo());
                infoEvento.setCNPJ(ManagerString.RemoveFormatoCpfCnpj(PAR.getCnpj()));
                infoEvento.setDhEvento(XmlUtil.dataNfe());
                infoEvento.setTpEvento(EventosEnum.CANCELAMENTO.getCodigo());
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
        tRetEnvEvento = Nfe.cancelarNfe(configuracoesNfe, enviEvento, true, DocumentoEnum.NFE);
        TProcEvento procEvento = new TProcEvento();
        procEvento.setVersao("1.00");
        procEvento.setEvento(enviEvento.getEvento().get(0));
        for (TRetEvento tRetEvento : tRetEnvEvento.getRetEvento()) {
            procEvento.setRetEvento(tRetEvento);
        }
        GravaNoArquivo gravador = new GravaNoArquivo();
        String xmlProcEvento = XmlUtil.objectToXml(procEvento);
        gravador.salvarArquivo(xmlProcEvento, br.JavaApplicationJsys.PASTA_XML_RET_EVENTO, jsysNFeEvento.getIdEvento(), "xml");
        if ("128".equals(tRetEnvEvento.getCStat())) {
            for (br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEvento r : tRetEnvEvento.getRetEvento()) {
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
}
