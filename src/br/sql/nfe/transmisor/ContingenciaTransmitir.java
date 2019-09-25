package br.sql.nfe.transmisor;

import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TEnviNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.sql.bean.JsysNFe;
import br.sql.nfe.util.XmlUtil;
import br.sql.util.GravaNoArquivo;
import br.sql.util.Retorna;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Juliano Alves Medina
 */
public class ContingenciaTransmitir {

    private final Collection<JsysNFe> nfces;

    @SuppressWarnings("unchecked")
    public ContingenciaTransmitir(Collection<String> chavesAcesso) {
        Map<Object, Object> filtro = new HashMap<>();
        filtro.put("chavesAcesso", chavesAcesso);
        nfces = Retorna.findCollection("JsysNFe.findByinChaveAcesso", filtro);
    }

    public void enviar() throws NfeException, CertificadoException {
        br.JavaApplicationJsys.iniciaConfigurações(Retorna.JsysParametros());
        for (JsysNFe jsysNFe : nfces) {
            try {
                TEnviNFe enviNFe = XmlUtil.xmlToObject(jsysNFe.getEnviNFe(), TEnviNFe.class);
                TRetEnviNFe retorno = Nfe.enviarNfe(enviNFe, "55".equals(jsysNFe.getMod()) ? ConstantesUtil.NFE : ConstantesUtil.NFCE);
                String xml = XmlUtil.objectToXml(retorno);
                GravaNoArquivo gravador = new GravaNoArquivo();
                gravador.salvarArquivo(xml, br.JavaApplicationJsys.PASTA_XML_RET_CONS_RECI_NFE, jsysNFe.getChaveAcesso(), "xml");
                jsysNFe.setRetConsReciNFe(xml);
                if ("104".equals(retorno.getCStat())) {
                    if (XmlUtil.verificaCsStat(retorno.getProtNFe().getInfProt().getCStat())) {
                        jsysNFe.setEmitida(true);
                    } else {
                        jsysNFe.setEmitida(false);
                    }
                } else {
                    jsysNFe.setEmitida(false);
                }
                br.sql.acesso.ConnectionFactory.update(jsysNFe);
                if (!(retorno instanceof TRetEnviNFe)) {
                    throw new NfeException("Retorno da Sefaz Invalido!");
                }
            } catch (JAXBException | NfeException ex) {
                throw new NfeException("Erro ao Enviar para Sefaz: " + ex.getMessage());
            }
        }
    }
}
