/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.defaultListCellRenderer;

import br.sql.bean.views.JsysFuncionarios;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

 /**
 *
 * @author Juliano Alves Medina  
 */
public class Funcionarios extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof JsysFuncionarios) {
            JsysFuncionarios f = (JsysFuncionarios) value;
            StringBuilder text = new StringBuilder();
            text.append(f.getNomeCliente()).append(" ").append(f.getIdFuncionario());
            setText(text.toString());
        }
        return this;
    }
}
