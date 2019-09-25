package br.sql.defaultTableCellRenderer;

import br.sql.util.ManagerDecimal;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * a clase vai colorir o grid e formatatar os campos automaticamente
 *
 * @author Juliano Alves Medina
 */
public class CaixaResumo extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        // se o campo e nulo ele cai fora
        if (value == null) {
            return null;
        }
        if (isSelected) {
            setBackground(Color.BLUE); // mostra o registro selecinado
        } else if (!(Boolean) table.getValueAt(row, 5)) {
            // se valor for um boolean vai verificar se e verdadeiro e colocar 
            // como a cor de fundo vermelho, para indicar que o cupom ainda nao foi impresso 
            setBackground(new Color(255, 165, 0));
        } else if (row % 2 == 0) { // cria o zebrado 
            setBackground(Color.LIGHT_GRAY);
        } else {
            setBackground(Color.WHITE);
        }
        if (hasFocus) {
            setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
        } else {
            setBorder(new EmptyBorder(1, 1, 1, 1));
        }
        // verifica o tipo de dados que esta no campo a faz a formataçao do mesmo
        if (value instanceof Double
                | value instanceof Integer
                | value instanceof Long) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        } else if (value instanceof java.util.Date) {
            setText(new SimpleDateFormat("dd/MM/yyyy").format((Date) value));
        } else if (value instanceof BigDecimal) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            setText(ManagerDecimal.converter((BigDecimal) value, "#,##0.00"));
        } else {
            setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        }
        return this;
    }
}
