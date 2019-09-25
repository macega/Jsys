package br.sql.plainDocument;

import br.JavaApplicationJsys;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;

/**
 *
 * @author Juliano Alves Medina
 */
public class DoubleDocument extends PlainDocument {

    private static final long serialVersionUID = -3802846632709128803L;
    private static final SimpleAttributeSet NULL_ATTRIBUTE = new SimpleAttributeSet();
    private static int maximoDigitos;

    /**
     * Permite apenas digitar ate 16 caracteres (9.999.999.999,99)
     */
    public DoubleDocument() {
        maximoDigitos = 12;
    }

    /**
     *
     * @param maximoDigitos permite definir um valor Máximo de digitos
     */
    public DoubleDocument(int maximoDigitos) {
        DoubleDocument.maximoDigitos = maximoDigitos;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        String original = getText(0, getLength());
        if (original.length() < maximoDigitos) {
            StringBuilder mascarado = new StringBuilder();
            if (a != NULL_ATTRIBUTE) {
                //limpa o campo   
                remove(-1, getLength());

                mascarado.append((original + str).replaceAll("[^0-9]", ""));
                for (int i = 0; i < mascarado.length(); i++) {
                    if (!Character.isDigit(mascarado.charAt(i))) {
                        mascarado.deleteCharAt(i);
                    }
                }
                Long number = new Long(mascarado.toString());

                mascarado.replace(0, mascarado.length(), number.toString());

                if (mascarado.length() < 3) {
                    if (mascarado.length() == 1) {
                        mascarado.insert(0, "0");
                        mascarado.insert(0, ",");
                        mascarado.insert(0, "0");
                    } else if (mascarado.length() == 2) {
                        mascarado.insert(0, ",");
                        mascarado.insert(0, "0");
                    }
                } else {
                    mascarado.insert(mascarado.length() - 2, ",");
                }

                if (mascarado.length() > 6) {
                    mascarado.insert(mascarado.length() - 6, '.');
                    if (mascarado.length() > 10) {
                        mascarado.insert(mascarado.length() - 10, '.');
                        if (mascarado.length() > 14) {
                            mascarado.insert(mascarado.length() - 14, '.');
                        }
                    }
                }
                super.insertString(0, mascarado.toString(), a);
            } else {
                if (original.length() == 0) {
                    super.insertString(0, "0,00", a);
                }
            }
        }
    }

    @Override
    public void remove(int offs, int len) throws BadLocationException {
        if (len == getLength()) {
            super.remove(0, len);
            if (offs != -1) {
                insertString(0, "", NULL_ATTRIBUTE);
            }
        } else {
            String original = getText(0, getLength()).substring(0, offs) + getText(0, getLength()).substring(offs + len);
            super.remove(0, getLength());
            insertString(0, original, null);
        }
    }

    public void setValue(BigDecimal d) {
        try {
            if (d != null) {
                remove(0, getLength());
                insertString(0, String.valueOf(d.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP)), null);
            }
        } catch (BadLocationException e) {
            // Will not happen because we are sure
            // we use the proper range
        }
    }

    public BigDecimal getValue() {
        try {
            String t = getText(0, getLength());
            if (t != null && t.length() > 0) {
                return new BigDecimal(t.replaceAll("\\.", "").replaceAll(",", "."));
            } else {
                return BigDecimal.ZERO;
            }
        } catch (BadLocationException e) {
            // Will not happen because we are sure
            // we use the proper range
            throw new Error(e.getMessage());
        }
    }
}
