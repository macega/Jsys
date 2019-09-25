package br.sql.plainDocument;

import br.sql.util.ManagerString;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Juliano Alves Medina
 */
public class CnpjDocument extends PlainDocument {

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        StringBuilder strB = new StringBuilder();
        strB.append(ManagerString.RemoveFormato(getText(0, offset)));
        strB.append(ManagerString.RemoveFormato(str));
        if (strB.length() <= 15) {
            remove(0, getLength());
            super.insertString(0, ManagerString.format("##.###.###/####-##", strB.toString()), attr);
        }
    }
}
