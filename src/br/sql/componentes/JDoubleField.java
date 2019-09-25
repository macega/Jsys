package br.sql.componentes;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Juliano Alves Medina dfdfd
 */
@SuppressWarnings("serial")
public class JDoubleField extends JTextField {

    public JDoubleField(int maxlenght) {
        this(null, maxlenght);
    }

    public JDoubleField() {
        this(null, 0);
    }

    public JDoubleField(Double value, int maxlenght) {
        super();
        setDocument(new DoublePlainDocument(maxlenght));
        if (value != null) {
            this.setText(String.valueOf(value.doubleValue()));
        }
    }

    public void setDouble(double value) {
        this.setText(String.valueOf(value));
    }

    public double getDouble() {
        if (this.getText().length() == 1 && this.getText().indexOf("-") >= 0) {
            return 0;
        }
        final char decimalSeparator = (((DecimalFormat) NumberFormat.getCurrencyInstance(Locale.getDefault())).getDecimalFormatSymbols()).getDecimalSeparator();
        if (decimalSeparator == ',') {
            return this.getText().isEmpty() ? 0 : Double.parseDouble(this.getText().replace(".", "").replace(",", "."));
        } else {
            return this.getText().isEmpty() ? 0 : Double.parseDouble(this.getText().replace(",", ""));
        }
    }
}

class DoublePlainDocument extends PlainDocument {

    int maxSize;

    public DoublePlainDocument(int limit) {
        maxSize = limit;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException, java.lang.NumberFormatException {
        /**
         * Verificando se ja nao passou do tamanho se enquadra
         */
        if (((getLength() + str.length()) <= maxSize) || maxSize == 0) {
            try {
                /**
                 * Verificando se o componente JA CONTEM dados
                 */
                if (getLength() > 0) {
                    /**
                     * Verificando se esta tentando inserir um separador decimal
                     */
                    if (str.replace(",", ".").equals(".")) {
                        /**
                         * Verificando se ja existe um separador decimal
                         */
                        if ((getText(0, getLength()).replace(",", ".").indexOf('.') == -1)) {
                            /**
                             * Se tiver o sinal de subtracao, so insere o
                             * separador decimal se houver mais de um caracter
                             */
                            if (getText(0, getLength()).indexOf('-') >= 0) {
                                if (getLength() > 1) {
                                    super.insertString(offs, str.replace(".", ","), a);
                                }
                            } else {
                                super.insertString(offs, str.replace(".", ","), a);
                            }
                        }
                    } else {
                        if (offs != 0 && str.equals("-")) {
                            return;
                        }
                        if (!(getText(0, getLength()).indexOf('-') < 0 && str.equals("-"))) {
                            Integer.parseInt(str);
                        }
                        super.insertString(offs, str.replace(".", ","), a);
                    }
                } /**
                 * Nao, o componente AINDA esta vazio
                 */
                else {
                    /**
                     * Verificando se esta tentando inserir o sinal de subtracao
                     */
                    if (str.equals("-")) {
                        super.insertString(offs, str, a);
                    } else {
                        Integer.parseInt(str.replace(",", "").replace(".", ""));
                        super.insertString(offs, str.replace(".", ","), a);
                    }
                }

            } catch (Exception ex) {
            }

        }
    }
}
