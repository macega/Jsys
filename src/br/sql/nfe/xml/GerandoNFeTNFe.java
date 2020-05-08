package br.sql.nfe.xml;

//import br.inf.portalfiscal.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
//import br.inf.portalfiscal.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.sql.log.Log;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Juliano Alves Medina
 */
public class GerandoNFeTNFe {

    @SuppressWarnings("unchecked")
    public static TEnviNFe ofValueStr(String conteudoXml) {
        try {
            JAXBContext context = JAXBContext.newInstance("br.inf.portalfiscal.nfe.schema_4.enviNFe");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement<TEnviNFe> element = (JAXBElement<TEnviNFe>) unmarshaller.unmarshal(new StringReader(conteudoXml));
            return element.getValue();
        } catch (JAXBException e) {
            Log.registraErro("GerandoNFeTNFe", "gerar", e);
        }
        return null;
    }

    /**
     *
     * @param enviNFe
     * @return Método que Converte o Objeto em String.
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public static String strValueOf(TEnviNFe enviNFe) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TEnviNFe.class);
        Marshaller marshaller = context.createMarshaller();
        JAXBElement<TEnviNFe> element = new ObjectFactory().createEnviNFe((enviNFe));
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        marshaller.marshal(element, sw);
        String xml = sw.toString();
        xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
        xml = xml.replaceAll("<NFe>", "<NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\">");
        return xml;
    }
}
