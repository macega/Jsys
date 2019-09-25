package br.adp.editor;

import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Juliano Alves Medina
 */
public class JComboBoxTableCellEditor extends AbstractCellEditor implements TableCellEditor {

    private JComboBox field;
    private final String[] values;

    public JComboBoxTableCellEditor(String[] values) {
        this.values = values;
    }

    @Override
    public Object getCellEditorValue() {
        return field.getSelectedItem().toString();
    }

    @Override 
    @SuppressWarnings("unchecked")
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        field = new JComboBox(values);
        field.setEditable(true);
        //field.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        field.setSelectedItem(value); //Deixa o editor pré-selecionado com o valor da célula
        return field;
    }

}
