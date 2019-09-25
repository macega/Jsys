/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.componentes;

import java.text.Normalizer;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Juliano Alves Medina
 */
@SuppressWarnings("serial")
public class JXTextField extends JTextField {

    private boolean acentos;

    public JXTextField(int maxlenght) {
        this(null, maxlenght, false);
    }

    public JXTextField(int maxlenght, boolean uperCase) {
        this(null, maxlenght, uperCase, true);
    }

    public JXTextField(boolean uperCase, boolean acentos) {
        this(null, 0, uperCase, acentos);
    }

    public JXTextField(int maxlenght, boolean uperCase, boolean acentos) {
        this(null, maxlenght, uperCase, acentos);
    }

    public JXTextField() {
        this(null, 0, false, true);
    }

    public JXTextField(String text, int maxlenght, boolean uperCase) {
        this(null, 0, uperCase, true);
    }

    public JXTextField(String text, int maxlenght, boolean uperCase, boolean acentos) {
        super();
        this.acentos = acentos;
        setDocument(new TextPlainDocument(maxlenght, uperCase));
        this.setText(text);

    }

    @Override
    public String getText() {
        try {
            Document model = this.getDocument();
            String text = model.getText(0, model.getLength()).trim();
            if (acentos) {
                return text;
            } else {
                return Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            }
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}


class TextPlainDocument extends PlainDocument {

    private final int maxSize;
    private final boolean uperCase;

    public TextPlainDocument(int limit, boolean uperCase) {
        this.maxSize = limit;
        this.uperCase = uperCase;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException, java.lang.NumberFormatException {
        if (((getLength() + str.length()) <= this.maxSize) || this.maxSize == 0) {
            super.insertString(offs, this.uperCase ? str.toUpperCase() : str, a);
        }
    }
}

