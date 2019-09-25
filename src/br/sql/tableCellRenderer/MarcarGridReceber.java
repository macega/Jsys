/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.tableCellRenderer;

import br.sql.bean.geral.titulosMarcados;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Juliano Alves Medina
 */
public class MarcarGridReceber implements TableCellRenderer {

    public MarcarGridReceber(ArrayList<titulosMarcados> marcados) {
        this.marcados = marcados;
    }

    private ArrayList<titulosMarcados> marcados = new ArrayList<>();

    public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Integer seqReceber = Integer.valueOf(table.getValueAt(row, 1).toString());
        ((JLabel) renderer).setOpaque(true);
        Color foreground, background;
        foreground = Color.black;
        background = Color.white;
        for (titulosMarcados m : marcados) {
            if (Objects.equals(m.getIdReceber(), Integer.valueOf(value.toString())) && Objects.equals(m.getSeqReceber(), seqReceber)) {
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
