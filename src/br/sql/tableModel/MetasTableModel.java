/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.tableModel;

import br.JavaApplicationJsys;
import br.sql.bean.JsysMetas;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 * A interface {@link TableModel} define 9 métodos, sendo que 2 deles cuidam da
 * parte de listeners. Esses dois métodos (addTableModelListener e
 * removeTableModelListener) nós delegaremos para a classe
 * {@link AbstractTableModel}. Os outros 7 nós implementaremos. Uma pequena
 * explicação de cada um deles (para mais detalhes, consulte a
 * documentação):<br>
 * <ul>
 * <li>getColumnClass: retorna a classe de determinada coluna. Útil para os
 * renderers saberem como exibir determinada informação.</li>
 * <li>getColumnCount: retorna o número de colunas que nossa tabela terá
 * (geralmente é o número de atributos do objeto que será exibido).</li>
 * <li>getColumnName: retorna o nome da coluna. Será usado no cabeçalho da
 * tabela.</li>
 * <li>getRowCount: retorna o número de registros. Geralmente é a quantidade de
 * elementos que há em nossa lista.</li>
 * <li>getValueAt: retorna o valor que está contido em determinada célula. Para
 * resgatarmos esse valor, primeiro pegamos o objeto da linha e depois, a partir
 * do número da coluna, retornamos uma de suas propriedades.</li>
 * <li>isCellEditable: informa se determinada célula pode ser editada ou
 * não.</li>
 * <li>setValueAt: é chamado quanto o valor de determinada célula é alterada.
 * Devemos modificar nosso objeto para refletir essa mudança.</li>
 * </ul>
 */
public class MetasTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private List<JsysMetas> metas;

    public MetasTableModel() {
        metas = new ArrayList<>();
    }

    /**
     *
     * @param lista a lista de clientes a ser adicionada.
     */
    public MetasTableModel(List<JsysMetas> lista) {
        this();
        metas.addAll(lista);
    }

    @Override
    public Class<?> getColumnClass(int coluna) {
        if (coluna == 0) {
            return Date.class;
        }
        return BigDecimal.class;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Mes"; // o nome da primeira coluna
            case 1:
                return "Loja"; // o nome da segunda
            case 2:
                return "Vendedor"; // e o da terceira
            default:
                return ""; // isso nunca deve ocorrer, pois temos só 3 colunas
        }
    }

    @Override
    public int getRowCount() {
        return metas.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        JsysMetas m = metas.get(linha);
        // façamos um switch
        switch (coluna) {
            case 0:
                return m.getMes(); // retornamos o mes
            case 1:
                return m.getLoja().setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP); // retornamos o loja
            case 2:
                return m.getVendedor().setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP); // e o vendedor
            default:
                return null; // isso nunca deve ocorrer, pois temos só 3 colunas
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        // aqui devemos atualizar o valor de nossos Clientes
        // vemos em qual linha ele está
        JsysMetas m = metas.get(linha);
        // e vemos o que será atualizado
        switch (coluna) {
            case 0:
                m.setMes((Date) valor); // mudamos o Mes
                break;
            case 1:
                m.setLoja((BigDecimal) valor); // mudamos a meta Loja
                break;
            case 2:
                m.setVendedor((BigDecimal) valor); // mudamos o valor da meta vendedor
                break;
        }
        // é importante notificar os listeners a cada alteração
        fireTableDataChanged();
    }

    /**
     * Adiciona um novo {link Cliente} e notifica os listeners que um novo
     * registro foi adicionado.
     *
     * @param m
     */
    public void adiciona(JsysMetas m) {
        metas.add(m);
        // informamos os listeners que a linha (size - 1) foi adicionada
        fireTableRowsInserted(metas.size() - 1, metas.size() - 1);
    }

    /**
     * Similar ao {link #adiciona(Cliente)}, porém para remover. Recebe o índice
     * do cliente a ser removido. Se não souber o índice, use o método {link
     * #getIndice(Cliente)} antes.
     *
     * @param indice
     */
    public void remove(int indice) {
        metas.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    /**
     * Adiciona todos os clientes na lista à este modelo.
     *
     * @param lista
     */
    public void adicionaLista(List<JsysMetas> lista) {
        int i = metas.size();
        metas.addAll(lista);
        fireTableRowsInserted(i, i + lista.size());
    }

    public void limpaLista() {
        int i = metas.size();
        metas.clear();
        fireTableRowsDeleted(0, i - 1);
    }

    public JsysMetas get(int row) {
        return metas.get(row);
    }

    public JsysMetas getMeta(int pos) {
        if (pos < 0 || pos >= metas.size()) {
            return null;
        }
        return metas.get(pos);
    }
}
