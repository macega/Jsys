/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.defaultTableCellRenderer;

import br.sql.util.ManagerDecimal;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.math.BigDecimal;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Administrador
 */
public class FormataBigDecimal extends DefaultTableCellRenderer {
    
    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof BigDecimal) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            setText(ManagerDecimal.converter((BigDecimal) value, "#,##0.00"));
        }
        return this;
    }
}