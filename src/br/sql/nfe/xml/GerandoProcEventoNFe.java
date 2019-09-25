package br.sql.nfe.xml;

import br.inf.portalfiscal.nfe.schema.procEventoCancNFe.TProcEvento;
import br.sql.log.Log;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Juliano Alves Medina
 */
public class GerandoProcEventoNFe {
    
    @SuppressWarnings("unchecked")
    public static TProcEvento gerar(String conteudoXml) {
        try {
            JAXBContext context = JAXBContext.newInstance("br.inf.portalfiscal.nfe.schema.procEventoCancNFe");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement<TProcEvento> element = (JAXBElement<TProcEvento>) unmarshaller.unmarshal(new StringReader(conteudoXml));
            return element.getValue();
        } catch (JAXBException e) {
            Log.registraErro("GerandoNFeProc", "gerar", e);
        }
        return null;
    }
    
    public static String getProcEventoNFe(String infEvento, String retEvento) {
        StringBuilder str = new StringBuilder();        
        str.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        str.append("<procEventoNFe versao=\"1.00\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">");
        str.append(infEvento.substring(
                infEvento.indexOf("<evento versao=\"1.00\">"),
                infEvento.indexOf("</envEvento>")));
        str.append(retEvento.substring(retEvento.indexOf("<retEvento versao=\"1.00\">"),
                retEvento.indexOf("</retEnvEvento>")));
        str.append("</procEventoNFe>");
        return str.toString();
    }
}
