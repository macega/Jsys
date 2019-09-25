/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.defaultListCellRenderer;

import br.sql.bean.views.JsysPlanoPagto;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author Administrador
 */
public class PlanoPagamento extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof JsysPlanoPagto) {
            JsysPlanoPagto f = (JsysPlanoPagto) value;
            setText(f.getDescPlano());
        }
        return this;
    }
}
