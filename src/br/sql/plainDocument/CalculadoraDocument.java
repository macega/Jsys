package br.sql.plainDocument;

import br.JavaApplicationJsys;
import br.sql.log.Log;
import br.sql.util.ManagerDecimal;
import br.sql.util.ManagerString;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Juliano Alves Medina
 */
public class CalculadoraDocument extends PlainDocument {

    private int maxDigts;
    private BigDecimal total;
    private JTextField jTextField;

    public CalculadoraDocument(int maxDigts, JTextField jTextField) {
        this.maxDigts = maxDigts;
        this.total = BigDecimal.ZERO;
        this.jTextField = jTextField;
        this.jTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    }

    public CalculadoraDocument(JTextField jTextField) {
        this.maxDigts = 12;
        this.total = BigDecimal.ZERO;
        this.jTextField = jTextField;
        this.jTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

        if (offs < getLength() - 1) {
            return; //não faz nada; 
        }

        StringBuilder value = new StringBuilder();
        BigDecimal val = ManagerDecimal.StringToBigDecimal(getText(0, offs));
        switch (str) {
            case "+":
                total = total.add(val).setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
                remove(0, getLength());
                super.insertString(0, total.toString(), a);
                jTextField.selectAll();
                break;
            case "-":
                total = total.subtract(val).setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
                remove(0, getLength());
                super.insertString(0, total.toString(), a);
                jTextField.selectAll();
                break;
            case "*":
                total = total.multiply(val).setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
                remove(0, getLength());
                super.insertString(0, total.toString(), a);
                jTextField.selectAll();
                break;
            case "/":
                total = total.divide(val).setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
                remove(0, getLength());
                super.insertString(0, total.toString(), a);
                jTextField.selectAll();
                break;
            default:
                if (str.matches("[0-9]{" + str.length() + "}") || ".".equals(str) || ",".equals(str)) { // str.matches("[0-9]{" + str.length() + "}")
                    value.append(ManagerString.RemoveFormato(getText(0, offs)));
                    value.append(str.replaceAll(",", "."));
                    if (value.length() <= maxDigts) {
                        remove(0, getLength());
                        super.insertString(0, value.toString(), a);
                    }
                }
                break;
        }
    }

    public void setValue(BigDecimal d) {
        try {
            if (d != null) {
                remove(0, getLength());
                insertString(0, String.valueOf(d.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP)), null);
            }
        } catch (BadLocationException e) {
            Log.registraErro(this, "setValue", e);
        }
    }

    public BigDecimal getText() {
        try {
            String t = getText(0, getLength());
            if (t != null && t.length() > 0) {
                return ManagerDecimal.StringToBigDecimal(t);
            } else {
                return BigDecimal.ZERO;
            }
        } catch (BadLocationException e) {
            Log.registraErro(this, "getValue", e);
            throw new Error(e.getMessage());
        }
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setZeroSum() {
        try {
            this.total = BigDecimal.ZERO;
            remove(0, getLength());
        } catch (BadLocationException e) {
            Log.registraErro(this, "setZeroSum", e);
        }
    }
}
