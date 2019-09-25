/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.defaultListCellRenderer;

import br.sql.bean.JsysProdutosTFamilias;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author Juliano Alves Medina
 */
public class FamiliaProdutos extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof JsysProdutosTFamilias) {
            JsysProdutosTFamilias f = (JsysProdutosTFamilias) value;
            setText(f.getNomeFamilia() + " - " + f.getIdFamilia());
        }
        return this;
    }
}
