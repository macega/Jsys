package br.sql.plainDocument;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Juliano Alves Medina
 */
public class NcmDocument extends PlainDocument {

    @Override
    public void insertString(int off, String string, AttributeSet attr) throws BadLocationException {
        if (off < getLength() - 1) {
            return; //não fazer nada; 
        }

        if (off < 10) {
            if (Character.isDigit(string.charAt(string.length() - 1))) {
                if (string.length() == 1) {
                    if (off == 3) {
                        string = string.concat(".");
                    }
                    if (off == 6) {
                        string = string.concat(".");
                    }
                    super.insertString(off, string, attr);
                } else {
                    super.insertString(off, string, attr);
                }
            }
        }
    }
}
