package br.sql.nfe.xml;

import br.inf.portalfiscal.nfe.schema_4.inutNFe.ObjectFactory;
import br.inf.portalfiscal.nfe.schema_4.inutNFe.TInutNFe;
import br.sql.bean.JsysNFeInut;
import br.sql.bean.JsysParametros;
import br.sql.nfe.links.ConstantesFiscal;
import br.sql.log.Log;
import br.sql.util.GravaNoArquivo;
import br.sql.util.ManagerData;
import br.sql.util.ManagerString;
import br.sql.util.Retorna;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Juliano Alves Medina
 */
public class GerandoNFeInutilizacaoJAXB {

    private final JsysNFeInut jsysNFeInut = new JsysNFeInut();
    private final JsysParametros par = Retorna.JsysParametros();
    private final String idInut;
    private String XmlNfeInutulizacao;

    public GerandoNFeInutilizacaoJAXB(String mod, String serie, String NNFIni, String NNFFin, String xJust) {
        jsysNFeInut.setTpAmb(ConstantesFiscal.AMBIENTE.TP_AMB);
        jsysNFeInut.setXServ("INUTILIZAR");
        jsysNFeInut.setCUF(String.valueOf(par.getCodMunicipio()).substring(0, 2));
        jsysNFeInut.setAno(ManagerData.getDataAtualTypeString("yy"));
        jsysNFeInut.setCnpj(ManagerString.RemoveFormatoCpfCnpj(par.getCnpj()));
        jsysNFeInut.setMod(mod);
        jsysNFeInut.setSerie(serie);
        jsysNFeInut.setNNFIni(NNFIni);
        jsysNFeInut.setNNFFin(NNFFin);
        jsysNFeInut.setXJust(xJust);
        idInut = getid();
        jsysNFeInut.setIdInut(idInut);
        jsysNFeInut.setEmitida(false);
    }

    public boolean gerar() {
        try {
            XmlNfeInutulizacao = getXmlNfeInutulizacao();
            GravaNoArquivo gravador = new GravaNoArquivo();
            gravador.salvarArquivo(XmlNfeInutulizacao, br.JavaApplicationJsys.PASTA_XML_INUT_NFE, jsysNFeInut.getIdInut(), "xml");
            jsysNFeInut.setXmlInutNFe(XmlNfeInutulizacao);
            return br.sql.acesso.ConnectionFactory.insert(jsysNFeInut) instanceof JsysNFeInut;
        } catch (Exception e) {
            Log.registraErro(this.getClass().getName(), "gerar", e);
            return false;
        }
    }

    private String getXmlNfeInutulizacao() {
        try {
            TInutNFe tInutNFe = new TInutNFe();
            tInutNFe.setVersao("3.10");
            tInutNFe.setInfInut(getInfInut());
            return strValueOf(tInutNFe);
        } catch (JAXBException e) {
            Log.registraErro(this.getClass().getName(), "getXmlNfeInutulizacao", e);
            return null;
        }
    }

    private TInutNFe.InfInut getInfInut() {
        TInutNFe.InfInut infInut = new TInutNFe.InfInut();
        infInut.setId(jsysNFeInut.getIdInut());
        infInut.setTpAmb(jsysNFeInut.getTpAmb());
        infInut.setXServ(jsysNFeInut.getXServ());
        infInut.setCUF(jsysNFeInut.getCUF());
        infInut.setAno(jsysNFeInut.getAno());
        infInut.setCNPJ(jsysNFeInut.getCnpj());
        infInut.setMod(jsysNFeInut.getMod());
        infInut.setSerie(jsysNFeInut.getSerie());
        infInut.setNNFIni(jsysNFeInut.getNNFIni());
        infInut.setNNFFin(jsysNFeInut.getNNFFin());
        infInut.setXJust(jsysNFeInut.getXJust());
        return infInut;
    }

    /**
     * Método que Converte o Objeto em String.
     *
     * @param tInutNFe
     * @return
     * @throws JAXBException
     */
    private static String strValueOf(TInutNFe tInutNFe) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TInutNFe.class);
        Marshaller marshaller = context.createMarshaller();
        JAXBElement<TInutNFe> element = new ObjectFactory().createInutNFe(tInutNFe);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        marshaller.marshal(element, sw);

        String xml = sw.toString();
        xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
        xml = xml.replaceAll("<NFe>", "<NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\">");

        return xml;
    }

    private String getid() {
        StringBuilder id = new StringBuilder();
        id.append("ID")
                .append(jsysNFeInut.getCUF())
                .append(jsysNFeInut.getAno())
                .append(jsysNFeInut.getCnpj())
                .append(jsysNFeInut.getMod())
                .append(ManagerString.zeros(jsysNFeInut.getSerie(), 3))
                .append(ManagerString.zeros(jsysNFeInut.getNNFIni(), 9))
                .append(ManagerString.zeros(jsysNFeInut.getNNFFin(), 9));
        return id.toString();
    }

    public String getIdInut() {
        return idInut;
    }
}
