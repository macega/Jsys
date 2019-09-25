/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.editor;

import java.awt.Component;
import java.text.SimpleDateFormat;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
//import net.sf.nachocalendar.components.DateField;

/**
 *
 * @author Juliano Alves Medina  
 */
public class DateTableCellEditor extends AbstractCellEditor implements TableCellEditor {

    /**
     * classe para editar a celula com formato de data
     */
    private static final long serialVersionUID = 1L;
    //private DateField cell = null;

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy");
//        cell = new DateField(formatarDate);
//        cell.setValue(value); 
        //return cell;
        return null;
    }

    @Override
    public Object getCellEditorValue() {
        
        //return cell.getValue();
        return null;
    }
}