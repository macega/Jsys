package br.sql.editor;

import br.sql.log.Log;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Juliano Alves Medina
 */
public class HoraTableCellEditor extends AbstractCellEditor implements TableCellEditor {

    /**
     * classe para editar a celula com formato de Hora
     */
    private static final long serialVersionUID = 1L;
    private Calendar calenNew = Calendar.getInstance();
    private Calendar calenOld = Calendar.getInstance();
    private JSpinner jSpinner1 = new javax.swing.JSpinner();
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value == null) {
            value = table.getValueAt(row, 2);
        }
        calenOld.setTime((Date) value);
        SpinnerModel model = new javax.swing.SpinnerDateModel((Date) value, null, null, java.util.Calendar.HOUR);
        jSpinner1.setModel(model);
        jSpinner1.setBorder(null);
        JComponent editor = new JSpinner.DateEditor(jSpinner1, "HH:mm");
        jSpinner1.setEditor(editor);
        JTextField tf = ((JSpinner.DefaultEditor) jSpinner1.getEditor()).getTextField();
        tf.setFont(new Font("", Font.BOLD, 11));
        tf.setForeground(Color.black);
        tf.setBackground(Color.yellow);
        return jSpinner1;
    }
    
    @Override
    public Object getCellEditorValue() {
        try {
            jSpinner1.commitEdit();
            calenNew.setTime((Date) jSpinner1.getValue());
            calenOld.set(Calendar.HOUR_OF_DAY, calenNew.get(Calendar.HOUR_OF_DAY));
            calenOld.set(Calendar.MINUTE, calenNew.get(Calendar.MINUTE));
            Date oldDate = calenOld.getTime();
            jSpinner1.setValue(oldDate);
        } catch (ParseException e) {
            Log.registraErro(this.getClass().getName(), "getCellEditorValue", e);
        }
        return jSpinner1.getValue();
    }
}
