/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.plainDocument;

import br.sql.util.ManagerString;
import java.util.Date;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;

/**
 *
 * @author Juliano Alves Medina
 */
public class HoraDocument extends PlainDocument {

    private static final int MAXIMO_DIGITOS = 7;
    private static final String FORMATO = "HH:mm:ss";
    private static final SimpleAttributeSet NULL_ATTRIBUTE = new SimpleAttributeSet();

    public HoraDocument() {
    }

    public void setDate(Date date) {
        String strr = new java.text.SimpleDateFormat(FORMATO).format(date);
        try {
            remove(0, getLength());
            super.insertString(0, strr, null);
        } catch (BadLocationException e) {
            br.sql.log.Log.registraErro("SetFormatoHora", "setDate", e);
        }
    }

    @Override
    public void insertString(int offset, String s, AttributeSet a) throws BadLocationException {
        String original = getText(0, getLength());

        StringBuilder mascarado = new StringBuilder();
        mascarado.append((original + s).replaceAll("[^0-9]", ""));
        for (int i = 0; i < mascarado.length(); i++) {
            if (!Character.isDigit(mascarado.charAt(i))) {
                mascarado.deleteCharAt(i);
            }
        }
        if (mascarado.length() <= 0) {
            return;
        }
        Long number = new Long(mascarado.toString());
        mascarado.replace(0, mascarado.length(), number.toString());
        if (mascarado.length() < MAXIMO_DIGITOS) {
            if (a != NULL_ATTRIBUTE) {
                //limpa o campo   
                remove(-1, getLength());
                mascarado.insert(0, ManagerString.zeros("", 6 - mascarado.length()));
                if (mascarado.length() > 2) {
                    mascarado.insert(mascarado.length() - 2, ':');
                    if (mascarado.length() > 5) {
                        mascarado.insert(mascarado.length() - 5, ':');
                    }
                }
                super.insertString(0, mascarado.toString(), a);
            } else {
                if (original.length() == 0) {
                    super.insertString(0, "00:00:00", a);
                }
            }
        }
    }

    @Override
    public void remove(int offs, int len) throws BadLocationException {
        if (len == getLength()) {
            super.remove(0, len);
            if (offs != -1) {
                insertString(0, "", NULL_ATTRIBUTE);
            }
        } else {
            String original = getText(0, getLength()).substring(0, offs) + getText(0, getLength()).substring(offs + len);
            super.remove(0, getLength());
            insertString(0, original, null);
        }
    }
}
