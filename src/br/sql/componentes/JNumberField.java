/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.componentes;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Juliano Alves Medina
 */
@SuppressWarnings("serial")
public class JNumberField extends JTextField {

    public JNumberField(int maxlenght) {
        this(null, maxlenght);
    }

    public JNumberField() {
        this(null, 0);
    }

    public JNumberField(Integer value, int maxlenght) {
        super();
        setDocument(new NumberPlainDocument(maxlenght));
        if (value != null) {
            this.setText(String.valueOf(value.intValue()));
        }
    }

    public void setInt(int value) {
        this.setText(String.valueOf(value));
    }

    public void setLong(long value) {
        this.setText(String.valueOf(value));
    }

    public int getInt() {
        return this.getText().isEmpty() ? 0 : Integer.parseInt(this.getText());
    }

    public long getLong() {
        return this.getText().isEmpty() ? 0 : Long.parseLong(this.getText());
    }
}

class NumberPlainDocument extends PlainDocument {

    int maxSize;

    public NumberPlainDocument(int limit) {
        maxSize = limit;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException, java.lang.NumberFormatException {
        if (((getLength() + str.length()) <= maxSize) || maxSize == 0) {
            try {
                Long.parseLong(str);
                super.insertString(offs, str, a);
            } catch (Exception ex) {
            }

        }
    }
}
