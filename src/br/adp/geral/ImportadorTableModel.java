package br.adp.geral;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Juliano Alves Medina
 */
public class ImportadorTableModel extends AbstractTableModel {

    private List<Importador> dados;
    private final String[] colunas = {"Campo Origem", "Campo Destino", "Script"};

    public ImportadorTableModel(List<Importador> list) {
        this.dados = list;
        
//        this.addTableModelListener((TableModelEvent tme) -> {
//            int linha = tme.getFirstRow();
//            Importador i = dados.get(linha);
//            // aqui voc� atualiza no banco ou em outro lugar qualquer
//        });
    }
    
    

    public List<Importador> getDados() {
        return this.dados;
    }

    public void setDados(List<Importador> list) {
        this.dados = list;
        this.fireTableDataChanged();
    }

    public void addRow(Importador i) {
        this.dados.add(i);
        this.fireTableDataChanged();
    }

    public void removeRow(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    public Importador get(int linha) {
        return this.dados.get(linha);
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        return true;
    }

    @Override
    public String getColumnName(int num) {
        return this.colunas[num];
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return this.dados.get(rowIndex).getOrigem();
            case 1:
                return this.dados.get(rowIndex).getDestino();
            case 2:
                return this.dados.get(rowIndex).getScript();
        }
        return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        if (valor == null) {
            return;
        }

        switch (coluna) {
            case 0:
                this.dados.get(linha).setOrigem((String) valor);
                break;
            case 1:
                this.dados.get(linha).setDestino((String) valor);
                break;
            case 2:
                this.dados.get(linha).setScript((String) valor);
        }
        this.fireTableRowsUpdated(linha, linha);
    }

    public void onRemoveAll() {
        this.dados.clear();
        fireTableDataChanged();
    }

    public void addRow() {
        addRow(new Importador("", "", ""));
    }
    
    
}
