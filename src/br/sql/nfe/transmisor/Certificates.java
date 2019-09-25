/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.nfe.transmisor;

import br.sql.bean.JsysParametros;
import br.sql.bean.geral.KeysNFe;
import br.sql.util.Retorna;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import org.apache.commons.httpclient.protocol.Protocol;

/**
 *
 * @author Juliano Alves Medina
 */
public class Certificates {

    private static final JsysParametros par = Retorna.JsysParametros();

    /**
     * Prepara o ambiente com os Certificados necessários.
     *
     * @param caminhoDoCertificadoDoCliente
     * @param senhaDoCertificadoDoCliente
     * @param arquivoCacertsGeradoParaCadaEstado
     * @throws java.io.FileNotFoundException
     * @throws java.security.KeyStoreException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.cert.CertificateException
     */
    public static void loadCertificates(String caminhoDoCertificadoDoCliente,
            String senhaDoCertificadoDoCliente,
            String arquivoCacertsGeradoParaCadaEstado) throws FileNotFoundException, KeyStoreException, NoSuchAlgorithmException, CertificateException, Exception {
        /**
         * Informacoes do Certificado Digital.
         */

        InputStream entrada = new FileInputStream(caminhoDoCertificadoDoCliente);
        KeyStore ks = KeyStore.getInstance("pkcs12");
        try {
            ks.load(entrada, senhaDoCertificadoDoCliente.toCharArray());
        } catch (IOException e) {
            throw new Exception("Senha do Certificado Digital esta incorreta ou Certificado inválido.");
        }

        String alias = "";
        Enumeration<String> aliasesEnum = ks.aliases();
        while (aliasesEnum.hasMoreElements()) {
            alias = (String) aliasesEnum.nextElement();
            if (ks.isKeyEntry(alias)) {
                break;
            }
        }
        X509Certificate certificate = (X509Certificate) ks.getCertificate(alias);
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, senhaDoCertificadoDoCliente.toCharArray());
        SocketFactoryDinamico socketFactoryDinamico = new SocketFactoryDinamico(certificate, privateKey);
        socketFactoryDinamico.setFileCacerts(arquivoCacertsGeradoParaCadaEstado);

        Protocol protocol = new Protocol("https", socketFactoryDinamico, br.JavaApplicationJsys.SSL_PORT);
        Protocol.registerProtocol("https", protocol);
    }

    public static void loadCertificates() throws FileNotFoundException, KeyStoreException, NoSuchAlgorithmException, CertificateException, Exception {
        String caminhoDoCertificadoDoCliente = par.getCaminhoDoCertificadoDoCliente();
        String senhaDoCertificadoDoCliente = par.getSenhaDoCertificadoDoCliente();
        String arquivoCacertsGeradoParaCadaEstado = br.JavaApplicationJsys.JSSECACERTS;
        /**
         * Certificados.
         */
        loadCertificates(caminhoDoCertificadoDoCliente,
                senhaDoCertificadoDoCliente,
                arquivoCacertsGeradoParaCadaEstado);

    }

    public static KeysNFe loadCertificates(String certificado, String senha) throws Exception {
        KeysNFe keysNFe = new KeysNFe();
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
                keysNFe.setPrivateKey(pkEntry.getPrivateKey());
                //privateKey = pkEntry.getPrivateKey();
                break;
            }
        }

        X509Certificate cert = (X509Certificate) pkEntry.getCertificate();
        //info("SubjectDN: " + cert.getSubjectDN().toString());

        KeyInfoFactory keyInfoFactory = XMLSignatureFactory.getInstance("DOM").getKeyInfoFactory();
        List<X509Certificate> x509Content = new ArrayList<>();

        x509Content.add(cert);
        X509Data x509Data = keyInfoFactory.newX509Data(x509Content);
        keysNFe.setKeyInfo(keyInfoFactory.newKeyInfo(Collections.singletonList(x509Data)));
        //keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(x509Data));
        return keysNFe;
    }
}
