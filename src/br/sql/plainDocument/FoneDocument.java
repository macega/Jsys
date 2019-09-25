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
public class FoneDocument extends PlainDocument {

    @Override
    public void insertString(int off, String string, AttributeSet attr) throws BadLocationException {
        if (off < getLength() - 1) {
            return; //não fazer nada; 
        }

        if (off < 13) {
            if (Character.isDigit(string.charAt(string.length() - 1))) {
                if (string.length() == 1) {
                    if (off == 0) {
                        string = "(".concat(string);
                    }
                    if (off == 2) {
                        string = string.concat(")");
                    }
                    if (off == 7) {
                        string = string.concat("-");
                    }
                    super.insertString(off, string, attr);
                } else {
                    super.insertString(off, string, attr);
                }
            } else {
                java.awt.Toolkit.getDefaultToolkit().beep();
            }
        }
    }
}
