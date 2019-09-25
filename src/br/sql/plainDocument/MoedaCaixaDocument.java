/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *//*
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
public class MoedaCaixaDocument extends PlainDocument {

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        super.insertString(offset, str.toUpperCase().replaceAll("[^0-9^,]", ""), attr);
    }

    public void replace(int offset, String str, AttributeSet attr) throws BadLocationException {
        super.insertString(offset, str.toUpperCase().replaceAll("[^0-9^,]", ""), attr);
    }
}