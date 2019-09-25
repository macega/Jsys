package br.sql.nfe.certificados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.MessageDigest;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author Samuel Oliveira
 *
 */
public class Cacert {

    private static final String CACERTS_NAME = "Cacert";
    private static final String CACERTS_PATH = "d:\\java\\util\\Cacert\\";
    private static final char SEPARATOR = File.separatorChar;
    private static final int TIMEOUT_WS = 30;

    public static void main(String[] args) {
        try {

            char[] passphrase = "changeit".toCharArray();
            File file = new File(CACERTS_PATH + SEPARATOR + CACERTS_NAME);

            if (file.isFile()) {
                file.delete();
            }

            if (file.isFile() == false) {

                File dir = new File(System.getProperty("java.home") + SEPARATOR + "lib" + SEPARATOR + "security");
                file = new File(dir, CACERTS_NAME);
                if (file.isFile() == false) {
                    file = new File(dir, "cacerts");
                }
            }

            info("| Loading KeyStore " + file + "...");
            InputStream in = new FileInputStream(file);
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(in, passphrase);
            in.close();

//            //NFE HOMOLOGACAO
//            listaWebServices.add("homnfe.sefaz.am.gov.br");
//            listaWebServices.add("hnfe.sefaz.ba.gov.br");
//            listaWebServices.add("nfeh.sefaz.ce.gov.br");
//            listaWebServices.add("app.sefaz.es.gov.br");
//            listaWebServices.add("homolog.sefaz.go.gov.br");
//            listaWebServices.add("hnfe.fazenda.mg.gov.br");
//            listaWebServices.add("homologacao.nfe.ms.gov.br");
//            listaWebServices.add("homologacao.sefaz.mt.gov.br");
//            listaWebServices.add("nfehomolog.sefaz.pe.gov.br");
//            listaWebServices.add("homologacao.nfe.fazenda.pr.gov.br");
//            listaWebServices.add("nfe-homologacao.sefazrs.rs.gov.br");
//            listaWebServices.add("homologacao.esocial.fazenda.pr.gov.br");
//            listaWebServices.add("cad.sefazrs.rs.gov.br");
//            listaWebServices.add("homologacao.nfe.fazenda.sp.gov.br");
//            listaWebServices.add("hom.sefazvirtual.fazenda.gov.br");
//            listaWebServices.add("nfe-homologacao.svrs.rs.gov.br");
//            listaWebServices.add("cad.svrs.rs.gov.br");
//            listaWebServices.add("hom.svc.fazenda.gov.br");
//            listaWebServices.add("hom.nfe.fazenda.gov.br");
//
//            // NFE PRODUCAO
//            listaWebServices.add("nfe.sefaz.am.gov.br");
//            listaWebServices.add("nfe.sefaz.ba.gov.br");
//            listaWebServices.add("nfe.sefaz.ce.gov.br");
//            listaWebServices.add("nfe.sefaz.go.gov.br");
//            listaWebServices.add("nfe.fazenda.mg.gov.br");
//            listaWebServices.add("nfe.fazenda.ms.gov.br");
//            listaWebServices.add("nfe.sefaz.mt.gov.br");
//            listaWebServices.add("nfe.sefaz.pe.gov.br");
//            listaWebServices.add("nfe.fazenda.pr.gov.br");
//            listaWebServices.add("nfe.sefazrs.rs.gov.br");
//            listaWebServices.add("nfe.fazenda.sp.gov.br");
//            listaWebServices.add("www.sefazvirtual.fazenda.gov.br");
//            listaWebServices.add("nfe.svrs.rs.gov.br");
//            listaWebServices.add("www.svc.fazenda.gov.br");
//            listaWebServices.add("www.nfe.fazenda.gov.br");
//            listaWebServices.add("sefazvirtual.fazenda.gov.br");
//            listaWebServices.add("svc.fazenda.gov.br");
//
//            // NFCE HOMOLOGACAO
//            listaWebServices.add("homnfce.sefaz.am.gov.br");
//            listaWebServices.add("nfceh.sefaz.ce.gov.br");
//            listaWebServices.add("homologacao.nfce.fazenda.ms.gov.br");
//            listaWebServices.add("nfcehomolog.sefaz.pe.gov.br");
//            listaWebServices.add("homologacao.nfce.fazenda.pr.gov.br");
//            listaWebServices.add("nfce-homologacao.sefazrs.rs.gov.br");
//            listaWebServices.add("homologacao.nfce.fazenda.sp.gov.br");
//            listaWebServices.add("nfce-homologacao.svrs.rs.gov.br");
//
//            // NFCE PRODUCAO
//            listaWebServices.add("nfce.sefaz.am.gov.br");
//            listaWebServices.add("nfce.fazenda.ms.gov.br");
//            listaWebServices.add("nfce.sefaz.pe.gov.br");
//            listaWebServices.add("nfce.fazenda.pr.gov.br");
//            listaWebServices.add("nfce.sefazrs.rs.gov.br");
//            listaWebServices.add("nfce.fazenda.sp.gov.br");
//            listaWebServices.add("nfce.svrs.rs.gov.br");
//
//            // CTE HOMOLOGACAO
//            listaWebServices.add("hcte.fazenda.mg.gov.br");
//            listaWebServices.add("homologacao.cte.ms.gov.br");
//            listaWebServices.add("homologacao.cte.fazenda.pr.gov.br");
//            listaWebServices.add("cte-homologacao.svrs.rs.gov.br");
//            listaWebServices.add("hom1.cte.fazenda.gov.br");
//
//            // CTE PRODUCAO
//            listaWebServices.add("cte.fazenda.mg.gov.br");
//            listaWebServices.add("producao.cte.ms.gov.br");
//            listaWebServices.add("cte.sefaz.mt.gov.br");
//            listaWebServices.add("cte.fazenda.pr.gov.br");
//            listaWebServices.add("cte.svrs.rs.gov.br");
//            listaWebServices.add("www1.cte.fazenda.gov.br");
//
//            //MDFE HOMOLOGACAO
//            listaWebServices.add("mdfe-homologacao.svrs.rs.gov.br");
//
//            //MDFE PRODUCAO
//            listaWebServices.add("mdfe.svrs.rs.gov.br");
//
//            //eSOCIAL Homologação
//            listaWebServices.add("webservices.producaorestrita.esocial.gov.br");
//
//            //eSOCIAL Produção
//            listaWebServices.add("webservices.esocial.gov.br");

            System.out.println(CACERTS_PATH + SEPARATOR + CACERTS_NAME);
            File cafile = new File(CACERTS_PATH + SEPARATOR + CACERTS_NAME);
            OutputStream out = new FileOutputStream(cafile);
            ks.store(out, passphrase);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void get(String host, int port, KeyStore ks) throws Exception {
        SSLContext context = SSLContext.getInstance("TLS");
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);
        X509TrustManager defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
        SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
        context.init(null, new TrustManager[]{tm}, null);
        SSLSocketFactory factory = context.getSocketFactory();

        info("| Opening connection to " + host + ":" + port + "...");
        SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
        socket.setSoTimeout(TIMEOUT_WS * 1000);
        try {
            info("| Starting SSL handshake...");
            socket.startHandshake();
            socket.close();
            info("| No errors, certificate is already trusted");
        } catch (SSLHandshakeException e) {
            /**
             * PKIX path building failed:
             * sun.security.provider.certpath.SunCertPathBuilderException:
             * unable to find valid certification path to requested target Não
             * tratado, pois sempre ocorre essa exceo quando o cacerts nao esta
             * gerado.
             */
        } catch (SSLException e) {
            error("| " + e.toString());
        }

        X509Certificate[] chain = tm.chain;
        if (chain == null) {
            info("| Could not obtain server certificate chain");
        } else {
            info("| Server sent " + chain.length + " certificate(s):");
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            for (int i = 0; i < chain.length; i++) {
                X509Certificate cert = chain[i];
                sha1.update(cert.getEncoded());
                md5.update(cert.getEncoded());

                String alias = host + "-" + (i);
                ks.setCertificateEntry(alias, cert);
                info("| Added certificate to keystore '" + CACERTS_PATH + SEPARATOR + CACERTS_NAME + "' using alias '" + alias + "'");
            }
        }
    }

    private static class SavingTrustManager implements X509TrustManager {

        private final X509TrustManager tm;
        private X509Certificate[] chain;

        SavingTrustManager(X509TrustManager tm) {
            this.tm = tm;
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
            // throw new UnsupportedOperationException();  
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            throw new UnsupportedOperationException();
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            this.chain = chain;
            this.tm.checkServerTrusted(chain, authType);
        }
    }

    private static void info(String log) {
        System.out.println("INFO: " + log);
    }

    private static void error(String log) {
        System.out.println("ERROR: " + log);
    }
}
