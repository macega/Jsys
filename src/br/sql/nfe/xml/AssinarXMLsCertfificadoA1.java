package br.sql.nfe.xml;

import br.sql.acesso.SQLDatabaseConnection;
import br.sql.bean.JsysNFe;
import br.sql.bean.JsysNFeEvento;
import br.sql.bean.JsysNFeInut;
import br.sql.bean.JsysParametros;
import br.sql.log.Log;
import br.sql.util.GravaNoArquivo;
import br.sql.util.ManagerString;
import br.sql.util.Retorna;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Juliano Alves Medina
 */
public class AssinarXMLsCertfificadoA1 {

    private static final String INFINUT = "infInut";
    private static final String EVENTO = "evento";
    private static final String NFE = "NFe";
    private static final SQLDatabaseConnection DADOS = new SQLDatabaseConnection();

    private PrivateKey privateKey;
    private KeyInfo keyInfo;

    /**
     *
     * @param nomeArquivo
     * @param operacao "Lote", "Evento", "Inutilizacao"
     * @return verdadeiro se assinado com sucesso.
     */
    public Boolean assinar(String nomeArquivo, String operacao) {
        JsysParametros par = Retorna.JsysParametros();
        //ConfiguracoesIniciaisNfe config = br.JavaApplicationJsys.iniciaConfigurações(par);
        try {
            String caminhoDoCertificadoDoCliente = par.getCaminhoDoCertificadoDoCliente();
            String senhaDoCertificadoDoCliente = par.getSenhaDoCertificadoDoCliente();
            AssinarXMLsCertfificadoA1 assinarXMLsCertfificadoA1 = new AssinarXMLsCertfificadoA1();
            java.util.Map<Object, Object> filtro = new java.util.HashMap<>();
            switch (operacao) {
                case "Lote":
                    filtro.clear();
                    filtro.put("chaveAcesso", nomeArquivo);
                    JsysNFe nfe = (JsysNFe) Retorna.findOneResult("JsysNFe.findByChaveAcesso", filtro);
                    /**
                     * Assinando o XML de Lote da NF-e fileEnviNFe = Caminho do
                     * Arquivo XML (EnviNFe) gerado;
                     */
                    if (nfe != null) {
                        String xmlEnviNFe = ManagerString.lerXML(br.JavaApplicationJsys.PASTA_XML_ENVI_NFE + nomeArquivo + ".xml");
                        String xmlEnviNFeAssinado = assinarXMLsCertfificadoA1.assinaEnviNFe(
                                xmlEnviNFe, caminhoDoCertificadoDoCliente, senhaDoCertificadoDoCliente);
                        StringBuilder sql = new StringBuilder();
                        sql.append("UPDATE jsysNFe SET enviNFe = '").append(xmlEnviNFeAssinado)
                                .append("' WHERE chaveAcesso = '").append(nomeArquivo).append("'");
                        if (DADOS.execSQLUpdate(sql) == 0) {
                            return false;
                        }
                        GravaNoArquivo gravador = new GravaNoArquivo();
                        gravador.salvarArquivo(xmlEnviNFeAssinado, br.JavaApplicationJsys.PASTA_XML_ENVI_NFE, nomeArquivo, "xml");
                        return true;
                    } else {
                        return false;
                    }
                case "Evento":
                    filtro.clear();
                    filtro.put("idEvento", nomeArquivo);
                    JsysNFeEvento evento = (JsysNFeEvento) Retorna.findOneResult("JsysNFeEvento.findByIdEvento", filtro);
                    /**
                     * Assinando o XML de Evento
                     */
                    if (evento != null) {
                        String xmlEventoNFe = ManagerString.lerXML(br.JavaApplicationJsys.PASTA_XML_EVENTO + nomeArquivo + ".xml");

                        String xmlEventoNFeAssinado = assinarXMLsCertfificadoA1.assinaEvento(
                                xmlEventoNFe, caminhoDoCertificadoDoCliente, senhaDoCertificadoDoCliente);

                        StringBuilder sql = new StringBuilder();
                        sql.append("UPDATE jsysNFeEvento SET envEventoCancNFe = '");
                        sql.append(xmlEventoNFeAssinado);
                        sql.append("' WHERE idEvento = '").append(nomeArquivo).append("'");
                        if (DADOS.execSQLUpdate(sql) == 0) {
                            return false;
                        }
                        GravaNoArquivo gravador = new GravaNoArquivo();
                        gravador.salvarArquivo(xmlEventoNFeAssinado, br.JavaApplicationJsys.PASTA_XML_EVENTO, nomeArquivo, "xml");
                        return true;
                    } else {
                        return false;
                    }
                case "Inutilizacao":
                    /**
                     * Assinando o XML de Inutilizacao da NF-e fileInutNFe =
                     * Caminho do Arquivo XML (InutNFe) gerado;
                     */
                    filtro.clear();
                    filtro.put("idInut", nomeArquivo);
                    JsysNFeInut inut = (JsysNFeInut) Retorna.findOneResult("JsysNFeInut.findByIdInut", filtro);
                    if (inut != null) {
                        String xmlInutNFe = ManagerString.lerXML(br.JavaApplicationJsys.PASTA_XML_INUT_NFE + nomeArquivo + ".xml");

                        String xmlInutNFeAssinado = assinarXMLsCertfificadoA1.assinaInutilizacao(
                                xmlInutNFe, caminhoDoCertificadoDoCliente, senhaDoCertificadoDoCliente);

                        StringBuilder sql = new StringBuilder();
                        sql.append("UPDATE jsysNFeInut SET xmlInutNFe = '");
                        sql.append(xmlInutNFeAssinado);
                        sql.append("' WHERE idInut = '").append(nomeArquivo).append("'");
                        if (DADOS.execSQLUpdate(sql) == 0) {
                            return false;
                        }
                        GravaNoArquivo gravador = new GravaNoArquivo();
                        gravador.salvarArquivo(xmlInutNFeAssinado, br.JavaApplicationJsys.PASTA_XML_INUT_NFE, nomeArquivo, "xml");
                        return true;
                    } else {
                        return false;
                    }

                default:
                    return false;
            }
        } catch (Exception e) {
            Log.registraErro(this.getClass().getName(), "assinar", e);
            return false;
        }
    }

    /**
     * Assinatura do XML de Envio de Lote da NF-e utilizando Certificado Digital
     * A1.
     *
     * @param xml
     * @param certificado
     * @param senha
     * @return
     * @throws Exception
     */
    public String assinaEnviNFe(String xml, String certificado, String senha) throws Exception {
        Document document = documentFactory(xml);
        XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");
        ArrayList<Transform> transformList = signatureFactory(signatureFactory);
        loadCertificates(certificado, senha, signatureFactory);
        for (int i = 0; i < document.getDocumentElement().getElementsByTagName(NFE).getLength(); i++) {
            assinarNFe(signatureFactory, transformList, privateKey, keyInfo, document, i);
        }
        return outputXML(document);
    }

    /**
     * Assinatura do XML de Inutilizacao de sequenciais da NF-e utilizando
     * Certificado Digital A1.
     *
     * @param xml
     * @param certificado
     * @param senha
     * @return
     * @throws Exception
     */
    private void assinarNFe(XMLSignatureFactory fac, ArrayList<Transform> transformList, PrivateKey privateKey,
            KeyInfo ki, Document document, int indexNFe) throws Exception {

        NodeList elements = document.getElementsByTagName("infNFe");
        org.w3c.dom.Element el = (org.w3c.dom.Element) elements.item(indexNFe);
        String id = el.getAttribute("Id");
        // alterado
        el.setIdAttribute("Id", true);

        Reference ref = fac.newReference("#" + id,
                fac.newDigestMethod(DigestMethod.SHA1, null),
                transformList,
                null,
                null);

        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(
                CanonicalizationMethod.INCLUSIVE,
                (C14NMethodParameterSpec) null),
                fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                Collections.singletonList(ref));

        XMLSignature signature = fac.newXMLSignature(si, ki);

        DOMSignContext dsc = new DOMSignContext(privateKey,
                document.getDocumentElement().getElementsByTagName(NFE).item(indexNFe));
        signature.sign(dsc);
    }

    public String assinaEvento(String xml, String certificado, String senha)
            throws Exception {
        Document document = documentFactory(xml);
        XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");
        ArrayList<Transform> transformList = signatureFactory(signatureFactory);
        loadCertificates(certificado, senha, signatureFactory);
        for (int i = 0; i < document.getDocumentElement().getElementsByTagName(EVENTO).getLength(); i++) {
            assinarEvento(signatureFactory, transformList, privateKey, keyInfo, document, i);
        }
        return outputXML(document);
    }

    private void assinarEvento(XMLSignatureFactory fac,
            ArrayList<Transform> transformList,
            PrivateKey privateKey,
            KeyInfo ki,
            Document document,
            int indexEvento)
            throws Exception {
        NodeList elements = document.getElementsByTagName("infEvento");
        org.w3c.dom.Element el = (org.w3c.dom.Element) elements.item(indexEvento);
        String id = el.getAttribute("Id");
        // alterado
        el.setIdAttribute("Id", true);
        Reference ref = fac.newReference("#" + id,
                fac.newDigestMethod(DigestMethod.SHA1, null),
                transformList,
                null,
                null);
        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(
                CanonicalizationMethod.INCLUSIVE,
                (C14NMethodParameterSpec) null),
                fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                Collections.singletonList(ref));
        XMLSignature signature = fac.newXMLSignature(si, ki);
        DOMSignContext dsc = new DOMSignContext(privateKey,
                document.getDocumentElement().getElementsByTagName(EVENTO).item(indexEvento));
        signature.sign(dsc);
    }

    private String assinaInutilizacao(String xml,
            String certificado, String senha)
            throws Exception {
        Document document = documentFactory(xml);
        XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");
        ArrayList<Transform> transformList = signatureFactory(signatureFactory);
        loadCertificates(certificado, senha, signatureFactory);
        NodeList elements = document.getElementsByTagName(INFINUT);
        org.w3c.dom.Element el = (org.w3c.dom.Element) elements.item(0);
        String id = el.getAttribute("Id");
        el.setIdAttribute("Id", true);
        Reference ref = signatureFactory.newReference("#" + id,
                signatureFactory.newDigestMethod(DigestMethod.SHA1, null),
                transformList, null, null);
        SignedInfo si = signatureFactory.newSignedInfo(signatureFactory
                .newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE,
                        (C14NMethodParameterSpec) null), signatureFactory
                        .newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                Collections.singletonList(ref));
        XMLSignature signature = signatureFactory.newXMLSignature(si, keyInfo);
        DOMSignContext dsc = new DOMSignContext(privateKey, document.getFirstChild());
        signature.sign(dsc);
        return outputXML(document);
    }

    private ArrayList<Transform> signatureFactory(XMLSignatureFactory signatureFactory)
            throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        ArrayList<Transform> transformList = new ArrayList<>();
        Transform envelopedTransform = signatureFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null);
        Transform c14NTransform = signatureFactory.newTransform("http://www.w3.org/TR/2001/REC-xml-c14n-20010315", (TransformParameterSpec) null);
        transformList.add(envelopedTransform);
        transformList.add(c14NTransform);
        return transformList;
    }

    private Document documentFactory(String xml) throws SAXException,
            IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document document = factory.newDocumentBuilder().parse(
                new ByteArrayInputStream(xml.getBytes()));
        return document;
    }

    private void loadCertificates(String certificado, String senha,
            XMLSignatureFactory signatureFactory) throws Exception {
        InputStream entrada = new FileInputStream(certificado);
        KeyStore ks = KeyStore.getInstance("pkcs12");
        try {
            ks.load(entrada, senha.toCharArray());
        } catch (IOException e) {
            throw new Exception("Senha do Certificado Digital incorreta ou Certificado inválido.");
        }
        KeyStore.PrivateKeyEntry pkEntry = null;
        Enumeration<String> aliasesEnum = ks.aliases();
        while (aliasesEnum.hasMoreElements()) {
            String alias = (String) aliasesEnum.nextElement();
            if (ks.isKeyEntry(alias)) {
                pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias,
                        new KeyStore.PasswordProtection(senha.toCharArray()));
                privateKey = pkEntry.getPrivateKey();
                break;
            }
        }
        X509Certificate cert = (X509Certificate) pkEntry.getCertificate();
        KeyInfoFactory keyInfoFactory = signatureFactory.getKeyInfoFactory();
        List<X509Certificate> x509Content = new ArrayList<>();
        x509Content.add(cert);
        X509Data x509Data = keyInfoFactory.newX509Data(x509Content);
        keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(x509Data));
    }

    private String outputXML(Document doc) throws TransformerException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.transform(new DOMSource(doc), new StreamResult(os));
        String xml = os.toString();
        if ((xml != null) && (!"".equals(xml))) {
            xml = xml.replaceAll("\\r\\n", "");
            xml = xml.replaceAll(" standalone=\"no\"", "");
        }
        return xml;
    }
}
