/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.defaultTableCellRenderer;

import br.sql.util.ManagerDecimal;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Juliano Alves Medina
 */
public class ZebradoLocation extends DefaultTableCellRenderer {

    private JCheckBox jCheckBox;
    //private static final Border focusBorder = new EmptyBorder(1, 1, 1, 1);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        // cria o zebrado 
        if (row % 2 == 0) {
            setBackground(Color.LIGHT_GRAY);
        } else {
            setBackground(Color.WHITE);
        }
        if (hasFocus) {
            setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
        } else {
            setBorder(new EmptyBorder(1, 1, 1, 1));
        }
        // mostra o registro selecinado
        if (isSelected) {
            setBackground(Color.BLUE);
        }
        if (value == null) {
            return this;
        }
        // verida o tipo de dados que esta no campo para fazer algo
        if (value instanceof Double
                | value instanceof Integer
                | value instanceof Long) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        } else if (value instanceof java.util.Date) {
            setText(new SimpleDateFormat("dd/MM/yyyy").format((Date) value));
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        } else if (value instanceof BigDecimal) {
            setText(ManagerDecimal.converter((BigDecimal) value, "#,##0.00"));
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        } else if (value instanceof Boolean) {
            jCheckBox = new JCheckBox();
            jCheckBox.setHorizontalAlignment(CENTER);
            if (row % 2 == 0) {
                jCheckBox.setBackground(Color.LIGHT_GRAY);
            } else {
                jCheckBox.setBackground(Color.WHITE);
            }
            if (isSelected) {
                jCheckBox.setBackground(Color.BLUE);
            }
            if (hasFocus) {
                jCheckBox.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
            } else {
                jCheckBox.setBorder(new EmptyBorder(1, 1, 1, 1));
            }
            jCheckBox.setSelected((Boolean) value);
            return jCheckBox;
        } else {
            setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        }
        return this;
    }
}
