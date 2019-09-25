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
public class IntegerDocument extends PlainDocument {

    private int maxLength = 0;

    public IntegerDocument() {
        super();
        this.maxLength = 9;
    }

    public IntegerDocument(int maxLen) {
        super();
        if (maxLen <= 0) {
            throw new IllegalArgumentException("Você deve especificar um comprimento máximo!");
        }
        this.maxLength = maxLen;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (maxLength <= 0) {
            super.insertString(offset, str.replaceAll("[^0-9^-]", ""), attr);
        } else {
            if (str == null || getLength() == maxLength) {
                return;
            }
            int totalLen = (getLength() + str.length());
            if (totalLen <= maxLength) {
                super.insertString(offset, str.replaceAll("[^0-9^-]", ""), attr);
                return;
            }
            String newStr = str.substring(0, (maxLength - getLength()));
            super.insertString(offset, newStr.replaceAll("[^0-9^-]", ""), attr);
        }
    }

    public void replace(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (maxLength <= 0) {
            super.insertString(offset, str.replaceAll("[^0-9]^-", ""), attr);
        } else {
            if (str == null || getLength() == maxLength) {
                return;
            }
            int totalLen = (getLength() + str.length());
            if (totalLen <= maxLength) {
                super.insertString(offset, str.replaceAll("[^0-9^-]", ""), attr);
                return;
            }
            String newStr = str.substring(0, (maxLength - getLength()));
            super.insertString(offset, newStr.replaceAll("[^0-9]^-", ""), attr);
        }
    }
}
