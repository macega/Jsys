package br.sql.util.Criptografia;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Juliano Alves Medina
 */
public class Security {

    public Security() {
    }

    /**
     *
     * @param valor
     * @param algoritmo "SHA-256", "SHA-1", "MD5"
     * @return
     * @throws java.security.NoSuchAlgorithmException
     */
    public byte[] getHash(String valor, String algoritmo) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algoritmo);
        md.update(valor.getBytes());
        return md.digest();
    }

    /**
     *
     * @param valor
     * @param algoritmo "SHA-256", "SHA-1", "MD5"
     * @param charsetName "ISO-8859-1", "UTF-8" e por ai vai
     * @return 
     */
    private byte[] getHash(String valor, String algoritmo, String charsetName) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance(algoritmo);
        md.update(valor.getBytes(charsetName));
        return md.digest();
    }

    /**
     *
     * @param valor
     * @param algoritmo "SHA-256", "SHA-1", "MD5"
     * @param charsetName "ISO-8859-1", "UTF-8" e por ai vai
     * @return
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     */
    public String getHexadecimal(String valor, String algoritmo, String charsetName) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // guardar os hexadecimais
        StringBuilder hexString = new StringBuilder();
        for (byte b : getHash(valor, algoritmo, charsetName)) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString();
    }
}
