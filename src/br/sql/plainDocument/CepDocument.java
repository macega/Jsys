/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
public class CepDocument extends PlainDocument {

    @Override
    public void insertString(int off, String string, AttributeSet attr) throws BadLocationException {
        if (off < getLength() - 1) {
            return; //não faz nada; 
        }

        if (off < 9) {
            if (Character.isDigit(string.charAt(string.length() - 1))) {
                if (string.length() == 1) {
                    if (off == 5) {
                        string = "-".concat(string);
                    }
                    super.insertString(off, string, attr);
                } else {
                    super.insertString(off, string, attr);
                }
            }
        }
    }
}
