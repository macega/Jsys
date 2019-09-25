package br.sql.plainDocument;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Juliano Alves Medina
 */
public class QuantidadeVendaDocument extends PlainDocument {
    
    String valor;

    /**
     * vai iniciar a classe com o valor "[^0-9^-]"
     */
    public QuantidadeVendaDocument() {
        valor = "[^0-9^-]";
    }

    /**
     *
     * @param valor passar os campos permitidos EX:"[^0-9^-]" 
     */
    public QuantidadeVendaDocument(String valor) {
        this.valor = valor;
    }
    
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        super.insertString(offset, str.toUpperCase().replaceAll(valor, ""), attr);
    }

    public void replace(int offset, String str, AttributeSet attr) throws BadLocationException {
        super.insertString(offset, str.toUpperCase().replaceAll(valor, ""), attr);
    }
}
