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
public class UppercaseDocument extends PlainDocument {

    /**
     * usado para mudar para maiusculos todo texto colocado no Jtextfild deve
     * ser colocalodo no campo Document do componente,
     * <code>new UppercaseDocument()</code>
     */
    public UppercaseDocument() {
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        super.insertString(offset, str.toUpperCase(), attr);
    }
}
