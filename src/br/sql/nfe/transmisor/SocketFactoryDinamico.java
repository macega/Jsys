package br.sql.nfe.transmisor;

import br.sql.log.Log;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.SocketFactory;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509KeyManager;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;

public class SocketFactoryDinamico implements ProtocolSocketFactory {

    private SSLContext ssl = null;
    private final X509Certificate certificate;
    private final PrivateKey privateKey;
    private String fileCacerts;

    public SocketFactoryDinamico(X509Certificate certificate,
            PrivateKey privateKey) {
        this.certificate = certificate;
        this.privateKey = privateKey;
    }

    private SSLContext createSSLContext() {
        try {
            KeyManager[] keyManagers = createKeyManagers();
            TrustManager[] trustManagers = createTrustManagers();

            /**
             * Os Protocolos possíveis são:
             *
             * TLS padrão. TLSv1 Suporta TLS versão 1 TLSv1.1 Suporta TLS versão
             * 1.1 (JDK 7 ou posterior) TLSv1.2 Suporta TLS versão 1.2 (JDK 7 ou
             * posterior) SSLv2 Hello Não usar! (Vulnerabilidade de segurança
             * POODLE) SSLv3 Hello Não usar! (Vulnerabilidade de segurança
             * POODLE) "TLS", "TLSv1", "TLSv1.1", "TLSv1.2"
             */
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagers, trustManagers, null);
            return sslContext;
        } catch (KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
            Log.registraErro(this.getClass().getName(), "createSSLContext", e);
        }
        return null;
    }

    private SSLContext getSSLContext() {
        if (ssl == null) {
            ssl = createSSLContext();
        }
        return ssl;
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress localAddress,
            int localPort, HttpConnectionParams params) throws IOException,
            UnknownHostException, ConnectTimeoutException {
        if (params == null) {
            throw new IllegalArgumentException("Parameters may not be null");
        }
        int timeout = params.getConnectionTimeout();
        SocketFactory socketfactory = getSSLContext().getSocketFactory();
        if (timeout == 0) {
            return socketfactory.createSocket(host, port, localAddress,
                    localPort);
        }

        /**
         * Os Protocolos possíveis são:
         *
         * TLS padrão. TLSv1 Suporta TLS versão 1 TLSv1.1 Suporta TLS versão 1.1
         * (JDK 7 ou posterior) TLSv1.2 Suporta TLS versão 1.2 (JDK 7 ou
         * posterior) SSLv2 Hello Não usar! (Vulnerabilidade de segurança
         * POODLE) SSLv3 Hello Não usar! (Vulnerabilidade de segurança POODLE)
         * "TLS", "TLSv1", "TLSv1.1", "TLSv1.2"
         */
        Socket socket = socketfactory.createSocket();
        //((SSLSocket) socket).setEnabledProtocols(new String[]{"TLSv1", "TLSv1.2"});
        ((SSLSocket) socket).setEnabledProtocols(new String[] {"SSLv3", "TLSv1"});
        // posivel alteraçao ((SSLSocket) socket).setEnabledProtocols(new String[] {"TLSv1.2"});  
        SocketAddress localaddr = new InetSocketAddress(localAddress, localPort);
        SocketAddress remoteaddr = new InetSocketAddress(host, port);
        socket.bind(localaddr);
        try {
            socket.connect(remoteaddr, timeout);
        } catch (IOException e) {
            Log.registraErro(this.getClass().getName(), "createSocket", e);
        }

        return socket;
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress clientHost,
            int clientPort) throws IOException, UnknownHostException {
        return getSSLContext().getSocketFactory().createSocket(host, port,
                clientHost, clientPort);
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException,
            UnknownHostException {
        return getSSLContext().getSocketFactory().createSocket(host, port);
    }

    public Socket createSocket(Socket socket, String host, int port,
            boolean autoClose) throws IOException, UnknownHostException {
        return getSSLContext().getSocketFactory().createSocket(socket, host,
                port, autoClose);
    }

    public KeyManager[] createKeyManagers() {
        HSKeyManager keyManager = new HSKeyManager(certificate, privateKey);

        return new KeyManager[]{keyManager};
    }

    public TrustManager[] createTrustManagers() throws KeyStoreException,
            NoSuchAlgorithmException, CertificateException, IOException {
        KeyStore trustStore = KeyStore.getInstance("JKS");

        trustStore.load(new FileInputStream(fileCacerts), "changeit".toCharArray());
        TrustManagerFactory trustManagerFactory = TrustManagerFactory
                .getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(trustStore);
        return trustManagerFactory.getTrustManagers();
    }

    class HSKeyManager implements X509KeyManager {

        private final X509Certificate certificate;
        private final PrivateKey privateKey;

        public HSKeyManager(X509Certificate certificate, PrivateKey privateKey) {
            this.certificate = certificate;
            this.privateKey = privateKey;
        }

        @Override
        public String chooseClientAlias(String[] arg0, Principal[] arg1,
                Socket arg2) {
            return certificate.getIssuerDN().getName();
        }

        @Override
        public String chooseServerAlias(String arg0, Principal[] arg1,
                Socket arg2) {
            return null;
        }

        @Override
        public X509Certificate[] getCertificateChain(String arg0) {
            return new X509Certificate[]{certificate};
        }

        @Override
        public String[] getClientAliases(String arg0, Principal[] arg1) {
            return new String[]{certificate.getIssuerDN().getName()};
        }

        @Override
        public PrivateKey getPrivateKey(String arg0) {
            return privateKey;
        }

        @Override
        public String[] getServerAliases(String arg0, Principal[] arg1) {
            return null;
        }
    }

    public void setFileCacerts(String fileCacerts) {
        this.fileCacerts = fileCacerts;
    }

}
