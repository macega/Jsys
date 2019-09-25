package br.sql.tableModel;

import br.sql.acesso.SQLDatabaseConnection;
import br.sql.util.ManagerData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Juliano Alves Medina
 */
public class CompencacaoTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private final List<String[]> list;
    private final String[] tableColumnsName;
    private final Date dataInicio;
    private final Date dataFim;
    private final GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
    private static final SQLDatabaseConnection DADOS = new SQLDatabaseConnection();
    //private final List<funComp> funComp = new ArrayList<>();

    /**
     *
     * @param dataInicio
     * @param dataFim
     * @throws java.sql.SQLException
     */
    @SuppressWarnings("unchecked")
    public CompencacaoTableModel(Date dataInicio, Date dataFim) throws SQLException {
        super();
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.gc.setTime(dataInicio);
        this.list = new ArrayList();
        List<Date> periodo = ManagerData.getList(this.dataInicio, this.dataFim);
        for (Date date : periodo) {
            DADOS.execProcedure("lancaCompencacao('" + ManagerData.convertUsDate(date) + "')");
        }
        StringBuilder sql = new StringBuilder();
        sql.append("EXECUTE ListaCompencacao '")
                .append(ManagerData.convertUsDate(this.dataInicio)).append("', '")
                .append(ManagerData.convertUsDate(this.dataFim)).append("'");
        ResultSet x = DADOS.execSQL(sql);
        java.sql.ResultSetMetaData rsmd = x.getMetaData();
        int colNo = rsmd.getColumnCount();

        this.tableColumnsName = new String[colNo];
        for (int i = 0; i < colNo; i++) {
            tableColumnsName[i] = rsmd.getColumnLabel(i + 1);
        }

        while (x.next()) {
            String[] s = new String[colNo];
            for (int i = 0; i < colNo; i++) {
                s[i] = x.getString(i + 1);
            }
            this.list.add(s);
        }
    }

    @Override
    public Class<?> getColumnClass(int coluna) {
        switch (coluna) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            default:
                return String.class;
        }
    }

    @Override
    public int getColumnCount() {
        return tableColumnsName.length;
    }

    @Override
    public String getColumnName(int coluna) {
        return tableColumnsName[coluna];
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        if (coluna > 2) {
            this.gc.set(Calendar.DAY_OF_MONTH, Integer.valueOf(this.getColumnName(coluna)));
            String[] ret = {list.get(linha)[coluna], ManagerData.diaSemanaPequeno(this.gc)};
            return ret;
        } else {
            return list.get(linha)[coluna];
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch (coluna) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                String[] s = list.get(linha);
                s[coluna] = getProximo(list.get(linha)[coluna]);
                if (setDataBase(s, coluna) == 1) {
                    list.set(linha, s);
                } else {
                    JOptionPane.showMessageDialog(null, "Verificar se o Funcionário Trabalha neste Período!");
                }
                break;
        }
        fireTableDataChanged();
    }

    private String getProximo(String valor) {
        if (valor != null) {
            switch (valor) {
                case "NORMAL":
                    return "MATUTINO";
                case "MATUTINO":
                    return "VESPERTINO";
                case "VESPERTINO":
                    return "DIURNO";
                case "DIURNO":
                    return "SÁBADO";
                case "SÁBADO":
                    return "DOMINGO";
                default:
                    return "NORMAL";
            }
        } else {
            return "";
        }
    }

    private int setDataBase(String[] s, int coluna) {
        GregorianCalendar calendario = ManagerData.getGregorianCalendar();
        calendario.setTime(dataInicio);
        calendario.set(Calendar.DAY_OF_MONTH, Integer.parseInt(getColumnName(coluna)));
        StringBuilder script = new StringBuilder();
        script.append("UPDATE jsysPontoCompensacao ")
                .append("SET periodo = '").append(s[coluna]).append("' ")
                .append("WHERE data = '").append(ManagerData.convertUsDate(calendario.getTime())).append("' and ")
                .append("idFuncionario = ").append(s[0]);
        return DADOS.execSQLUpdate(script);
    }
}
