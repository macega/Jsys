/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.plainDocument;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Juliano Alves Medina
 */
public class MoedaDocument extends PlainDocument {

    public static int NUMERO_DIGITOS_MAXIMO;

    public MoedaDocument() {
        super();
        NUMERO_DIGITOS_MAXIMO = 12;
    }

    public MoedaDocument(int campoMax) {
        super();
        NUMERO_DIGITOS_MAXIMO = campoMax;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

        String texto = getText(0, getLength());
        str = str.replace(".", "").replace(",", "");
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
                return;
            }
        }

        if (texto.length() <= MoedaDocument.NUMERO_DIGITOS_MAXIMO) {
            super.remove(0, getLength());
            texto = texto.replace(".", "").replace(",", "");
            Long x = Long.parseLong("0" + texto + str);
            StringBuilder s = new StringBuilder(Long.toString(x));

            if (s.length() > 0 && s.charAt(0) == '0') {
                s.deleteCharAt(0);
            }
            
            if (s.length() < 3) {
                if (s.length() < 1) {
                    s.insert(0, "000");
                } else if (s.length() < 2) {
                    s.insert(0, "00");
                } else {
                    s.insert(0, "0");
                }
            }

            s.insert(s.length() - 2, ",");

            if (s.length() > 6) {
                s.insert(s.length() - 6, ".");
            }

            if (s.length() > 10) {
                s.insert(s.length() - 10, ".");
            }
            
            if (s.length() > 14) {
                s.insert(s.length() - 14, ".");
            }
            super.insertString(0, s.toString(), a);
        }
    }

    @Override
    public void remove(int offset, int length) throws BadLocationException {
        super.remove(offset, length);
        String texto = getText(0, getLength());
        texto = texto.replace(",", "");
        texto = texto.replace(".", "");
        super.remove(0, getLength());
        insertString(0, texto, null);
    }
}
