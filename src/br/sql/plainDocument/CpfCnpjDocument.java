/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.plainDocument;

import br.sql.util.ManagerString;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Juliano Alves Medina
 */
public class CpfCnpjDocument extends PlainDocument {

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        StringBuilder strB = new StringBuilder();
        strB.append(ManagerString.RemoveFormato(getText(0, offset)));
        strB.append(str.replaceAll("[^0-9]", ""));
        if (strB.length() >= 15) {
        } else {
            if (strB.toString().length() <= 11) {
                remove(0, getLength());
                super.insertString(0, ManagerString.format("###.###.###-##", strB.toString()), attr);
            } else if (strB.toString().length() <= 15) {
                remove(0, getLength());
                super.insertString(0, ManagerString.format("##.###.###/####-##", strB.toString()), attr);
            }
        }
    }
}
