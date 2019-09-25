package br.sql.bean.geral;

import java.security.PrivateKey;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;

/**
 *
 * @author Juliano Alves Medina
 */
public class KeysNFe {

    private PrivateKey privateKey;
    private KeyInfo keyInfo;

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public KeyInfo getKeyInfo() {
        return keyInfo;
    }

    public void setKeyInfo(KeyInfo keyInfo) {
        this.keyInfo = keyInfo;
    }

}
