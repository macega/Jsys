/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.defaultTableCellRenderer;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * renomeado
 * @author Juliano Alves Medina
 */
public class Compencacao extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setForeground(Color.white);
        setBackground(new Color(255, 255, 255));
        setText("");
        
        if (value instanceof String[]) {
            String[] text = (String[]) value;
            setText(text[1]);
            if (text[0] != null) {
                switch (text[0]) {
                    case "NORMAL":
                        setBackground(new Color(0, 204, 51));
                        break;
                    case "MATUTINO":
                        setBackground(new Color(0, 0, 0));
                        break;
                    case "VESPERTINO":
                        setBackground(new Color(255, 153, 0));
                        break;
                    case "DIURNO":
                        setBackground(new Color(255, 0, 0));
                        break;
                    case "SÁBADO":
                        setBackground(new Color(51, 102, 255));
                        break;
                    case "DOMINGO":
                        setBackground(new Color(102, 102, 102));
                        break;
                    default:
                        setBackground(new Color(255, 255, 255));
                        break;
                }
            }
        }
        return this;
    }
}
