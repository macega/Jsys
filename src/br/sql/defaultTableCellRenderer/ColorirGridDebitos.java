package br.sql.defaultTableCellRenderer;

import br.sql.bean.Pagamentos;
import br.sql.util.ManagerData;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Juliano Alves Medina
 */
public class ColorirGridDebitos extends DefaultTableCellRenderer {

    private List<Pagamentos> listPagamentos;

    public ColorirGridDebitos() {
    }

    public ColorirGridDebitos(List<Pagamentos> list) {
        this.listPagamentos = list;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Boolean quitado = false;
        if (this.listPagamentos != null
                && this.listPagamentos.get(table.convertRowIndexToModel(row)) instanceof Pagamentos
                && this.listPagamentos.get(table.convertRowIndexToModel(row)) != null) {
            quitado = ((Pagamentos) this.listPagamentos.get(table.convertRowIndexToModel(row))).getQuitado();
        }
        if (value != null) {
            setText(ManagerData.convertBrDate((Date) value));
            setFont(getFont().deriveFont(Font.BOLD));
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            if (quitado) {
                setForeground(Color.white);
                setBackground(Color.blue);
            } else if (ManagerData.dateDiffDay(ManagerData.setHoraInicioDia((Date) value), ManagerData.setHoraInicioDia(ManagerData.getDate())) >= 0) {
                setForeground(Color.white);
                setBackground(Color.red);
            } else {
                setForeground(Color.black);
                setBackground(Color.green);
            }
            return this;
        }
        return null;
    }
}
