package br.sql.tableCellRenderer;

import br.sql.bean.geral.pagamentosMarcados;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Juliano Alves Medina
 */
public class MarcarGridPagamentos implements TableCellRenderer {

    public MarcarGridPagamentos(List<pagamentosMarcados> marcados) {
        this.marcados = marcados;
    }

    private List<pagamentosMarcados> marcados = new ArrayList<>();

    public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        ((JLabel) renderer).setOpaque(true);
        Color foreground, background;
        foreground = Color.black;
        background = Color.white;
        for (pagamentosMarcados p : marcados) {
            if (Objects.equals(p.getPag().getIdPagamentos(), value)) {
                renderer.setFont(renderer.getFont().deriveFont(Font.BOLD));
                foreground = Color.white;
                background = Color.blue;
            }
        }
        renderer.setForeground(foreground);
        renderer.setBackground(background);
        return renderer;
    }
}
