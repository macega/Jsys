/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.defaultTableCellRenderer;

import br.sql.util.ManagerData;
import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Administrador
 */
public class ColorirPonto15 extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);       
        Date entrada = ManagerData.formataData(table.getModel().getValueAt(row, 1).toString(), "HH:mm:ss");
        Date saida = ManagerData.formataData(table.getModel().getValueAt(row, 2).toString(), "HH:mm:ss");
        if (ManagerData.dateDiff(ManagerData.SECONDS, entrada, saida) > 900) {
            setBackground(Color.RED);
        } else {
            setBackground(Color.GREEN);
        }
        return this;
    }
}
