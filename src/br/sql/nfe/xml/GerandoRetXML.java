package br.sql.nfe.xml;

import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Juliano Alves Medina
 */
public class GerandoRetXML {

    @SuppressWarnings("unchecked")
    public static br.com.swconsultoria.nfe.schema_4.retEnviNFe.TRetEnviNFe getTRetEnviNFe(String xmlRetConsReciNFe) {
        try {
            JAXBContext context = JAXBContext.newInstance("br.com.swconsultoria.nfe.schema_4.retEnviNFe");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement<br.com.swconsultoria.nfe.schema_4.retEnviNFe.TRetEnviNFe> element = (JAXBElement<br.com.swconsultoria.nfe.schema_4.retEnviNFe.TRetEnviNFe>) unmarshaller.unmarshal(new StringReader(xmlRetConsReciNFe));
            return element.getValue();
        } catch (JAXBException e) {
            br.sql.log.Log.registraErro("GerandoRetEnviNFe", "gerar", e);
            br.com.swconsultoria.nfe.schema_4.retEnviNFe.TRetEnviNFe r = new br.com.swconsultoria.nfe.schema_4.retEnviNFe.TRetEnviNFe();
            r.setCStat("999");
            r.setXMotivo("Rejeição: Erro não catalogado (informar a mensagem de erro capturado no tratamento da exceção)");
            r.getProtNFe().getInfProt().setCStat("999");
            r.getProtNFe().getInfProt().setXMotivo("Rejeição: Erro não catalogado (informar a mensagem de erro capturado no tratamento da exceção)");
            return r;
        }
    }

    @SuppressWarnings("unchecked")
    public static br.com.swconsultoria.nfe.schema.retEnvEventoCancNFe.TRetEnvEvento getTRetEnvEvento(String xmlTRetEnvEvento) {
        try {
            JAXBContext context = JAXBContext.newInstance("br.com.swconsultoria.nfe.schema.retEnvEventoCancNFe");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement<br.com.swconsultoria.nfe.schema.retEnvEventoCancNFe.TRetEnvEvento> element = (JAXBElement<br.com.swconsultoria.nfe.schema.retEnvEventoCancNFe.TRetEnvEvento>) unmarshaller.unmarshal(new StringReader(xmlTRetEnvEvento));
            return element.getValue();
        } catch (JAXBException e) {
            br.sql.log.Log.registraErro("GerandoRetEnviNFe", "gerar", e);
            //br.inf.portalfiscal.nfe.schema.retEnvEventoCancNFe.TRetEnvEvento r = new br.inf.portalfiscal.nfe.schema.retEnvEventoCancNFe.TRetEnvEvento();
            br.com.swconsultoria.nfe.schema.retEnvEventoCancNFe.TRetEnvEvento r = new br.com.swconsultoria.nfe.schema.retEnvEventoCancNFe.TRetEnvEvento();
            r.setCStat("999");
            r.setXMotivo("Rejeição: Erro não catalogado (informar a mensagem de erro capturado no tratamento da exceção)");
            br.com.swconsultoria.nfe.schema.retEnvEventoCancNFe.TRetEvento tr = new br.com.swconsultoria.nfe.schema.retEnvEventoCancNFe.TRetEvento();
            tr.getInfEvento().setCStat("999");
            tr.getInfEvento().setXMotivo("Rejeição: Erro não catalogado (informar a mensagem de erro capturado no tratamento da exceção)");
            r.getRetEvento().add(tr);
            return r;
        }
    }
}
