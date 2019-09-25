/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.editor;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Juliano Alves Medina
 */
public class ObsPontoTableCellEditor extends AbstractCellEditor implements TableCellEditor {

//    private JComboBox field;
//    private final String[] values = {"",
//        "FALTA",
//        "ATESTADO",
//        "FERIADO",
//        "OUTROS",
//        "COMPENSAÇÃO",
//        "DOMINGO",
//        "FERIAS",
//        "LICENÇA-MATERNIDADE",
//        "AVISO-PRÉVIO",
//        "LACTANTE"};

    @Override @SuppressWarnings("unchecked")
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//        field = new JComboBox(values);
//        field.setSelectedItem(value); //Deixa o editor pré-selecionado com o valor da célula
//        return field;
        return null;
    }

    @Override
    public Object getCellEditorValue() {
        //return field.getSelectedItem().toString();
        return null;
    }
}
