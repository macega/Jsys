/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.defaultListCellRenderer;

import br.sql.bean.JsysTitulos;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author Juliano Alves Medina
 */
public class Titulos extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof JsysTitulos) {
            JsysTitulos f = (JsysTitulos) value;
            setText(f.getIdTitulo());
        }
        return this;
    }
}
