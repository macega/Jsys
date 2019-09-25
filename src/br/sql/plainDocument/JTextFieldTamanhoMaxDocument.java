/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.plainDocument;

import java.text.Normalizer;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Juliano Alves Medina
 */
public class JTextFieldTamanhoMaxDocument extends PlainDocument {

    private int maxLength;
    private boolean maiusculas;

    public JTextFieldTamanhoMaxDocument() {
        super();
        maiusculas = false;
    }

    public JTextFieldTamanhoMaxDocument(boolean setMaiusculas) {
        super();
        maiusculas = setMaiusculas;
    }

    public JTextFieldTamanhoMaxDocument(int maxlen) {
        super();
        if (maxlen <= 0) {
            throw new IllegalArgumentException("Você deve especificar um comprimento máximo!");
        }
        maxLength = maxlen;
        maiusculas = true;
    }

    public JTextFieldTamanhoMaxDocument(int maxlen, boolean setMaiusculas) {
        super();
        if (maxlen <= 0) {
            throw new IllegalArgumentException("Você deve especificar um comprimento máximo!");
        }
        maxLength = maxlen;
        maiusculas = setMaiusculas;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {

        // vefica se e para mudar para letras maiusculas 
        // e remove caracteres indesejados 
        str = maiusculas
                ? Normalizer.normalize(str, Normalizer.Form.NFD).toUpperCase().replaceAll("[^\\p{ASCII}]", "")
                : Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

        if (maxLength <= 0) {
            super.insertString(offset, str, attr);
        } else {
            if (str == null || getLength() == maxLength) {
                return;
            }
            int totalLen = (getLength() + str.length());
            if (totalLen <= maxLength) {
                super.insertString(offset, str, attr);
                return;
            }
            String newStr = str.substring(0, (maxLength - getLength()));
            super.insertString(offset, newStr, attr);
        }
    }
}
