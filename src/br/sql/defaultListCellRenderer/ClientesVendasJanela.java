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
 * @author Administrador
 */
public class ClientesVendasJanela extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof JsysClientes) {
            JsysClientes f = (JsysClientes) value;
            setText(f.getNomeCorentista() + " - " + f.getIdCliente());
        }
        return this;
    }
}
