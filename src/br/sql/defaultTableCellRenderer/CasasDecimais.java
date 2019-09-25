/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.defaultTableCellRenderer;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Juliano Alves Medina
 */
public class CasasDecimais extends DefaultTableCellRenderer {

    int casasDecimais;

    /**
     *
     */
    public CasasDecimais() {
        casasDecimais = 2;
    }

    /**
     * formata o campo da tabela com os campos decimas 
     * @param casasDecimais
     */
    public CasasDecimais(int casasDecimais) {
        this.casasDecimais = casasDecimais;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value != null) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            setValue(((BigDecimal) value).setScale(casasDecimais, RoundingMode.HALF_UP));
            return this;
        }
        return null;
    }
}
