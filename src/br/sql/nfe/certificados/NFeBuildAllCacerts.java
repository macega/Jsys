package br.sql.nfe.certificados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class NFeBuildAllCacerts {

    public static void main(String[] args) {
        try {
            char[] passphrase = "changeit".toCharArray();

            File file = new File(br.JavaApplicationJsys.JSSECACERTS);
            if (file.isFile() == false) {
                char SEP = File.separatorChar;
                File dir = new File(System.getProperty("java.home") + SEP + "lib" + SEP + "security");
                file = new File(dir, br.JavaApplicationJsys.JSSECACERTS);
                if (file.isFile() == false) {
                    file = new File(dir, "cacerts");
                }
            }

            info("| Loading KeyStore " + file + "...");
            InputStream in = new FileInputStream(file);
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(in, passphrase);
            in.close();
            
            //HOMOLOGACAO
            //AM - https://homnfe.sefaz.am.gov.br
            get("homnfe.sefaz.am.gov.br", 443, ks);
            //BA - https://hnfe.sefaz.ba.gov.br
            get("hnfe.sefaz.ba.gov.br", 443, ks);
            //CE - https://nfeh.sefaz.ce.gov.br
            get("nfeh.sefaz.ce.gov.br", 443, ks);
            //GO - https://homolog.sefaz.go.gov.br
            get("homolog.sefaz.go.gov.br", 443, ks);
            //MG - https://hnfe.fazenda.mg.gov.br
            get("hnfe.fazenda.mg.gov.br", 443, ks);
            //MS - https://homologacao.nfe.ms.gov.br
            get("homologacao.nfe.ms.gov.br", 443, ks);
            //MT - https://homologacao.sefaz.mt.gov.br
            get("homologacao.sefaz.mt.gov.br", 443, ks);
            //PE - https://nfehomolog.sefaz.pe.gov.br
            get("nfehomolog.sefaz.pe.gov.br", 443, ks);
            //PR - https://homologacao.nfe2.fazenda.pr.gov.br
            //get("homologacao.nfe2.fazenda.pr.gov.br", 443, ks);
            //PR - https://homologacao.nfe.fazenda.pr.gov.br
            get("homologacao.nfe.fazenda.pr.gov.br", 443, ks);
            //RS NFC-e - https://nfce-homologacao.sefazrs.rs.gov.br
            get("nfce-homologacao.sefazrs.rs.gov.br", 443, ks);
            //RS - https://nfe-homologacao.sefazrs.rs.gov.br
            get("nfe-homologacao.sefazrs.rs.gov.br", 443, ks);
            get("nfce-homologacao.sefazrs.rs.gov.br", 443, ks);
            //SP - https://homologacao.nfe.fazenda.sp.gov.br
            get("homologacao.nfe.fazenda.sp.gov.br", 443, ks);
            //SVAN - https://hom.sefazvirtual.fazenda.gov.br
            get("hom.sefazvirtual.fazenda.gov.br", 443, ks);
            //SVRS - https://nfe-homologacao.svrs.rs.gov.br
            get("nfe-homologacao.svrs.rs.gov.br", 443, ks);
            get("nfce-homologacao.svrs.rs.gov.br", 443, ks);
            get("cte-homologacao.svrs.rs.gov.br", 443, ks);
            get("mdfe-homologacao.svrs.rs.gov.br", 443, ks);
            //SVRS - https://cad-homologacao.svrs.rs.gov.br
            get("cad-homologacao.svrs.rs.gov.br", 443, ks);
            //SVC-AN - https://hom.svc.fazenda.gov.br
            get("hom.svc.fazenda.gov.br", 443, ks);
            //SVC-RS - https://nfe-homologacao.svrs.rs.gov.br
            get("nfe-homologacao.svrs.rs.gov.br", 443, ks);
            //AN - https://hom.nfe.fazenda.gov.br
            get("hom.nfe.fazenda.gov.br", 443, ks);

            //PRODUCAO
            //AM - https://nfe.sefaz.am.gov.br
            get("nfe.sefaz.am.gov.br", 443, ks);
            //BA - https://nfe.sefaz.ba.gov.br
            get("nfe.sefaz.ba.gov.br", 443, ks);
            //CE - https://nfe.sefaz.ce.gov.br
            get("nfe.sefaz.ce.gov.br", 443, ks);
            //GO - https://nfe.sefaz.go.gov.br
            get("nfe.sefaz.go.gov.br", 443, ks);
            //MG - https://nfe.fazenda.mg.gov.br
            get("nfe.fazenda.mg.gov.br", 443, ks);
            //MA - https://sistemas.sefaz.ma.gov.br
            get("sistemas.sefaz.ma.gov.br", 443, ks);
            //MS - https://nfe.fazenda.ms.gov.br
            get("nfe.fazenda.ms.gov.br", 443, ks);
            //MT - https://nfe.sefaz.mt.gov.br
            get("nfe.sefaz.mt.gov.br", 443, ks);
            //PE - https://nfe.sefaz.pe.gov.br
            get("nfe.sefaz.pe.gov.br", 443, ks);
            //PR - https://nfe2.fazenda.pr.gov.br
            //get("nfe2.fazenda.pr.gov.br", 443, ks);
            //PR - https://nfe.fazenda.pr.gov.br
            get("nfe.fazenda.pr.gov.br", 443, ks);
            //RS - https://cad.sefazrs.rs.gov.br
            get("cad.sefazrs.rs.gov.br", 443, ks);
            //RS - https://nfe.sefazrs.rs.gov.br
            get("nfe.sefazrs.rs.gov.br", 443, ks);
            //RS NFC-e - https://nfce.sefazrs.rs.gov.br
            get("nfce.sefazrs.rs.gov.br", 443, ks);
            //SP - https://nfe.fazenda.sp.gov.br
            get("nfe.fazenda.sp.gov.br", 443, ks);
            //SVAN - https://www.sefazvirtual.fazenda.gov.br
            get("www.sefazvirtual.fazenda.gov.br", 443, ks);
            //SVRS - https://cad.svrs.rs.gov.br
            get("cad.svrs.rs.gov.br", 443, ks);
            //SVRS - https://nfe.svrs.rs.gov.br
            get("nfe.svrs.rs.gov.br", 443, ks);
            get("nfce.svrs.rs.gov.br", 443, ks);
            get("cte.svrs.rs.gov.br", 443, ks);
            get("mdfe.svrs.rs.gov.br", 443, ks);
            //SVC-AN - https://www.svc.fazenda.gov.br
            get("www.svc.fazenda.gov.br", 443, ks);
            //SVC-RS - https://nfe.svrs.rs.gov.br
            get("nfe.svrs.rs.gov.br", 443, ks);
            //AN - https://www.nfe.fazenda.gov.br
            get("www.nfe.fazenda.gov.br", 443, ks);
            //AN - https://www1.nfe.fazenda.gov.br
            get("www1.nfe.fazenda.gov.br", 443, ks);
            //https://ccd.serpro.gov.br
            get("ccd.serpro.gov.br", 443, ks);
            //https://acraiz.icpbrasil.gov.br
            get("acraiz.icpbrasil.gov.br", 443, ks);
            //https://certificadodigital.caixa.gov.br
            get("certificadodigital.caixa.gov.br", 443, ks);

//            get("nfe-homologacao.sefazrs.rs.gov.br", Constantes.SSL_PORT, ks);
//            get("cad.sefazrs.rs.gov.br", Constantes.SSL_PORT, ks);
//            get("nfe.sefazrs.rs.gov.br", Constantes.SSL_PORT, ks);
//            get("nfe-homologacao.svrs.rs.gov.br", Constantes.SSL_PORT, ks);
//            get("cad-homologacao.svrs.rs.gov.br", Constantes.SSL_PORT, ks);
//            get("nfe.svrs.rs.gov.br", Constantes.SSL_PORT, ks);
//            get("cad.svrs.rs.gov.br", Constantes.SSL_PORT, ks);
//            get("nfce-homologacao.sefazrs.rs.gov.br", Constantes.SSL_PORT, ks);
//            get("nfce.sefazrs.rs.gov.br", Constantes.SSL_PORT, ks);
//            get("nfce-homologacao.svrs.rs.gov.br", Constantes.SSL_PORT, ks);
//            get("nfce.svrs.rs.gov.br", Constantes.SSL_PORT, ks);
//            get("cte-homologacao.svrs.rs.gov.br", Constantes.SSL_PORT, ks);
//            get("cte.svrs.rs.gov.br", Constantes.SSL_PORT, ks);
//            get("mdfe-homologacao.svrs.rs.gov.br", Constantes.SSL_PORT, ks);
//            get("mdfe.svrs.rs.gov.br", Constantes.SSL_PORT, ks);
////		    get("homologacao.sefaz.mt.gov.br", 443, ks);
////		    get("nfehomolog.sefaz.pe.gov.br", 443, ks);
////		    get("homologacao.nfe2.fazenda.pr.gov.br", 443, ks);
////		    get("homologacao.nfe.sefaz.rs.gov.br", 443, ks);
////		    get("homologacao.nfe.fazenda.sp.gov.br", 443, ks);
////		    get("hom.nfe.fazenda.gov.br", 443, ks);
////		    get("hom.sefazvirtual.fazenda.gov.br", 443, ks);
////		    get("homologacao.nfe.sefazvirtual.rs.gov.br", 443, ks);

            File cafile = new File(br.JavaApplicationJsys.JSSECACERTS);
            OutputStream out = new FileOutputStream(cafile);
            ks.store(out, passphrase);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void get(String host, int port, KeyStore ks) throws Exception {

        SSLContext context = SSLContext.getInstance("TLSv1.2");
        //SSLContext context = SSLContext.getInstance("TLS");
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);
        X509TrustManager defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
        SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
        context.init(null, new TrustManager[]{tm}, null);
        SSLSocketFactory factory = context.getSocketFactory();

        info("| Opening connection to " + host + ":" + port + "...");
        SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
        socket.setSoTimeout(br.JavaApplicationJsys.TIMEOUT_WS * 1000);
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
             * tratado, pois sempre ocorre essa exceção quando o cacerts nao
             * esta gerado.
             */
        } catch (SSLException e) {
            error("| " + e.toString());
        }

        X509Certificate[] chain = tm.chain;
        if (chain == null) {
            info("| Could not obtain server certificate chain");
        }

        info("| Server sent " + chain.length + " certificate(s):");
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        for (int i = 0; i < chain.length; i++) {
            X509Certificate cert = chain[i];
            sha1.update(cert.getEncoded());
            md5.update(cert.getEncoded());

            String alias = host + "-" + (i);
            ks.setCertificateEntry(alias, cert);
            info("| Added certificate to keystore '" + br.JavaApplicationJsys.JSSECACERTS + "' using alias '" + alias + "'");
        }
    }

    private static class SavingTrustManager implements X509TrustManager {

        private final X509TrustManager tm;
        private X509Certificate[] chain;

        SavingTrustManager(X509TrustManager tm) {
            this.tm = tm;
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            throw new UnsupportedOperationException();
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            this.chain = chain;
            tm.checkServerTrusted(chain, authType);
        }
    }

    private static void info(String log) {
        //System.out.println("INFO: " + log);
    }

    private static void error(String log) {
        //System.out.println("ERROR: " + log);
    }

}
