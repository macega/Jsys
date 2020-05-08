package br.sql.bean;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;

/**
 * // CODIGO PARA TESTES DanfeNFCeQRCode code = new DanfeNFCeQRCode();
 * code.setUrlConsulta("http://www.nfce.sefin.ro.gov.br/consultanfce/consulta.jsp");
 * code.setChNFe("11150704509537000132650010000000811510632717");
 * code.setnVersao("100"); code.setTpAmb("2"); code.setcDest(null);
 * code.setDhEmi("2015-07-27T10:30:54-04:00"); code.setvNF("110.00");
 * code.setvICMS("0.00"); code.setDigVal("HZpBOagpUCDjZDZYmBWUEiMela0=");
 * code.setcIdToken("000001");
 * code.setCSC("000001C1774291-A86A-4ADA-B247-791207C6CF50");
 *
 * System.out.println(code.getCodeQRCode());
 *
 * @author Juliano Alves Medina
 */
public class DanfeNFCeQRCode {

    private String urlConsulta;
    private String chNFe;
    private String nVersao;
    private String tpAmb;
    private String cDest;
    private String dhEmi;
    private String vNF;
    private String vICMS;
    private String digVal;
    private String cIdToken;
    private String CSC;
    //private String cHashQRCode;
    //private final Security s = new Security();

    public String getCodeQRCode() {
        StringBuilder value = new StringBuilder();
        value.append("chNFe=").append(chNFe);
        value.append("&nVersao=").append(nVersao);
        value.append("&tpAmb=").append(tpAmb);
        value.append((cDest == null | "".equals(cDest)) ? "" : "&cDest=" + cDest);
        value.append("&dhEmi=").append(getHexa(dhEmi));
        value.append("&vNF=").append(vNF);
        value.append("&vICMS=").append(vICMS);
        value.append("&digVal=").append(getHexa(digVal));
        value.append("&cIdToken=").append(cIdToken);
        String cHashQRCode = getHexa(getHash(value.toString() + CSC, "SHA-1")).toUpperCase();
        StringBuilder ret = new StringBuilder();
        ret.append(urlConsulta).append("?");
        ret.append(value);
        ret.append("&cHashQRCode=").append(cHashQRCode);
        return ret.toString();

//            StringBuilder value = new StringBuilder();
//            value.append("chNFe=").append(chNFe);
//            value.append("&nVersao=").append(nVersao);
//            value.append("&tpAmb=").append(tpAmb);
//            value.append((cDest == null | "".equals(cDest)) ? "" : "&cDest=" + cDest);
//            value.append("&dhEmi=").append(getHexa(dhEmi));
//            value.append("&vNF=").append(vNF);
//            value.append("&vICMS=").append(vICMS);
//            value.append("&digVal=").append(getHexa(digVal));
//            value.append("&cIdToken=").append(cIdToken);
//            cHashQRCode = getHexa(s.getHash(value.toString() + CSC, "SHA-1")).toUpperCase();
//            StringBuilder ret = new StringBuilder();
//            //ret.append("<![CDATA[");
//            ret.append(urlConsulta).append("?");
//            ret.append(value);
//            ret.append("&cHashQRCode=").append(cHashQRCode);
//            //ret.append("]]>");
//            return ret.toString();
    }

    public void setUrlConsulta(String urlConsulta) {
        this.urlConsulta = urlConsulta;
    }

    public void setChNFe(String chNFe) {
        this.chNFe = chNFe.replaceAll("NFe", "");
    }

    public void setnVersao(String nVersao) {
        this.nVersao = nVersao;
    }

    public void setTpAmb(String tpAmb) {
        this.tpAmb = tpAmb;
    }

    public void setcDest(String cDest) {
        this.cDest = cDest;
    }

    public void setDhEmi(String dhEmi) {
        this.dhEmi = dhEmi;
    }

    public void setvNF(String vNF) {
        this.vNF = vNF;
    }

    public void setvICMS(String vICMS) {
        this.vICMS = vICMS;
    }

    public void setDigVal(byte[] value) throws UnsupportedEncodingException {
        byte[] base64 = Base64.encodeBase64(value);
        String DV = new String(base64, "ISO-8859-1");
        this.digVal = DV;
    }

    public void setDigVal(String digVal) {
        this.digVal = digVal;
    }

    public void setcIdToken(String cIdToken) {
        this.cIdToken = cIdToken;
    }

    public void setCSC(String CSC) {
        this.CSC = CSC.replaceAll("-", "");
    }

//    private static String getHexa(String valor) {
//        return getHexa(valor.getBytes());
//    }
//    private static String getHexa(byte[] bytes) {
//        StringBuilder s = new StringBuilder();
//        for (int i = 0; i < bytes.length; i++) {
//            int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
//            int parteBaixa = bytes[i] & 0xf;
//            if (parteAlta == 0) {
//                s.append('0');
//            }
//            s.append(Integer.toHexString(parteAlta | parteBaixa));
//        }
//        return s.toString();
//    }
    /**
     * @param valor
     * @param algoritmo "SHA-256", "SHA-1", "MD5"
     * @return
     */
    private static byte[] getHash(String valor, String algoritmo) {
        try {
            MessageDigest md = MessageDigest.getInstance(algoritmo);
            md.update(valor.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private static String getHexa(String valor) {
        return getHexa(valor.getBytes());
    }

    private static String getHexa(byte[] bytes) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
            int parteBaixa = bytes[i] & 0xf;
            if (parteAlta == 0) {
                s.append('0');
            }
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        return s.toString();
    }

}
