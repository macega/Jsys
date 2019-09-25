package br.sql.nfe.xml;

import br.sql.log.Log;
import br.sql.util.ManagerString;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author Juliano Alves Medina
 */
public class ValidacaoNFeXML implements ErrorHandler {

    private final List<String> listaComErrosDeValidacao;
    private String erro;

    public ValidacaoNFeXML() {
        this.listaComErrosDeValidacao = new ArrayList<>();
        this.erro = "";
    }

    public String getMensagenErro() {
        return erro;
    }

    /**
     *
     * @param nomeArquivo
     * @param operacao "Lote", "Cancelamento", "Inutilizacao"
     * @return verdadeiro se assinado com sucesso.
     */
    public Boolean Validar(String nomeArquivo, String operacao) {
        try {
            /**
             * XSD usado para Validar o XML. Use o XSD adequado para cada XML a
             * ser validado: enviNFe_v2.00.xsd (Envio do Lote) inutNFe_v3.10.xsd
             * (Inutilização de Número da NF-e) cancNFe_v2.00.xsd (Cancelamento
             * da NF-e) ...
             */
            String xsd = null;
            String xml = null;
            switch (operacao) {
                case "Lote":
                    xsd = "xsd/enviNFe_v4.00.xsd";
                    xml = ManagerString.lerXML(br.JavaApplicationJsys.PASTA_XML_ENVI_NFE + nomeArquivo + ".xml");
                    break;
//                case "Cancelamento":
//                    xsd = "xsd/PL_008h1/cancNFe_v2.00.xsd";
//                    break;
                case "Inutilizacao":
                    xsd = "xsd/inutNFe_v4.00.xsd";
                    xml = ManagerString.lerXML(br.JavaApplicationJsys.PASTA_XML_INUT_NFE + nomeArquivo + ".xml");
                    break;
                default:
                    return false;
            }

            ValidacaoNFeXML validacaoXML = new ValidacaoNFeXML();
            List<String> errosValidacao = validacaoXML.validateXml(ManagerString.normalizeXML(xml), xsd);
            if ((errosValidacao != null) && (errosValidacao.size() > 0)) {
                for (String msgError : errosValidacao) {
                    this.erro = this.erro + "Erro XML: " + msgError + System.lineSeparator();
                }
                return false;
            } else {
                this.erro = "XML Validado com Sucesso!" + System.lineSeparator();
                return true;
            }
        } catch (Exception e) {
            Log.registraErro("NFeValidacaoXML", "Validar", e);
            return false;
        }
    }

    private List<String> validateXml(String xml, String xsd) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setValidating(true);
        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", xsd);
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            builder.setErrorHandler(this);
        } catch (ParserConfigurationException e) {
            Log.registraErro(this.getClass().getName(), "validateXml", e);
        }

        org.w3c.dom.Document document;
        try {
            document = builder.parse(new ByteArrayInputStream(xml.getBytes()));
            //org.w3c.dom.Node rootNode = document.getFirstChild();
            //info("| Validando Node: " + rootNode.getNodeName());
            //Mensagens = "| Validando Node: " + rootNode.getNodeName();
            return this.getListaComErrosDeValidacao();
        } catch (SAXException | IOException e) {
            Log.registraErro(this.getClass().getName(), "validateXml", e);
            throw new Exception(e.toString());
        }
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        if (isError(exception)) {
            listaComErrosDeValidacao.add(tratamentoRetorno(exception.getMessage()));
        }
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        listaComErrosDeValidacao.add(tratamentoRetorno(exception.getMessage()));
    }

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        listaComErrosDeValidacao.add(tratamentoRetorno(exception.getMessage()));
    }

    private String tratamentoRetorno(String message) {
        message = message.replaceAll("cvc-type.3.1.3:", "");
        message = message.replaceAll("cvc-complex-type.2.4.a:", "");
        message = message.replaceAll("cvc-complex-type.2.4.b:", "");
        message = message.replaceAll("The value", "O valor");
        message = message.replaceAll("of element", "do campo");
        message = message.replaceAll("is not valid", "não é valido");
        message = message.replaceAll("Invalid content was found starting with element", "Encontrado o campo");
        message = message.replaceAll("One of", "Campo(s)");
        message = message.replaceAll("is expected", "é obrigatorio");
        message = message.replaceAll("\\{", "");
        message = message.replaceAll("\\}", "");
        message = message.replaceAll("\"", "");
        message = message.replaceAll("http://www.portalfiscal.inf.br/nfe:", "");
        return message.trim();
    }

    private List<String> getListaComErrosDeValidacao() {
        return listaComErrosDeValidacao;
    }

    private boolean isError(SAXParseException exception) {
        if (exception.getMessage().startsWith("cvc-pattern-valid")
                || exception.getMessage().startsWith("cvc-maxLength-valid")
                || exception.getMessage().startsWith("cvc-datatype")) {
            return false;
        }
        return true;
    }
}
