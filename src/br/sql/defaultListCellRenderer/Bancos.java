/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.defaultListCellRenderer;


import br.sql.bean.JsysClientes;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author Juliano Alves Medina
 */
public class Bancos extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof JsysClientes) {
            JsysClientes cc = (JsysClientes) value;
            setText(cc.getIdCliente() + " - " + cc.getNomeCorentista());
        }
        return this;
    }
}