package br.adp.util;

import br.adp.geral.Geral;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juliano Alves Medina
 */
public class ExecutaQuery {

    private static Connection conn;
    //private static CallableStatement cStmt;
    private static Statement stm;
    //private static Geral aThis;

    /**
     *
     * @param sql passar uma String com um Script sql
     * @param base
     * @throws java.sql.SQLException
     */
    public static void executeSqlScript(String sql, String base) throws SQLException {
        executa(sql, base);
    }

    /**
     *
     * @param sql passar uma StringBuilder com um Script sql
     * @param base
     * @throws SQLException
     */
    public static void executeSqlScript(StringBuilder sql, String base) throws SQLException {
        executa(sql.toString(), base);
    }

    public static ResultSet executesqlScriptRS(String sql, String base) throws SQLException, ClassNotFoundException {
        return executaRS(sql, base);
    }

    public static ResultSet executesqlScriptRS(StringBuilder sql, String base) throws SQLException, ClassNotFoundException {
        return executaRS(sql.toString(), base);
    }

    public static void executeSqlScriptList(List<String[]> list, String base, Geral aThis) throws SQLException {
        for (String[] sql : list) {
            executa(sql[0], base);
            aThis.setLog("Script Executado: " + sql[1], "script");
            //aThis.jTextAreaLog.append("script executado: " + sql[1] + System.lineSeparator());
        }
    }

    private static void executa(String sql, String base) throws SQLException {
        try {
            Class.forName(br.JavaApplicationJsys.DRIVER);
            conn = DriverManager.getConnection(br.sql.acesso.ConnectionFactory.getConfString(base));
            stm = conn.createStatement();
            stm.execute(sql);
            close();
        } catch (ClassNotFoundException ex) {
            close();
            ex.printStackTrace();
        }
    }

    public static void close() {
        try {
            stm.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ResultSet executaRS(String sql, String base) throws SQLException, ClassNotFoundException {
        try {
            Class.forName(br.JavaApplicationJsys.DRIVER);
            conn = DriverManager.getConnection(br.sql.acesso.ConnectionFactory.getConfString(base));
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            return rs;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static List<String> getTables(String base) {
        List<String> list = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(br.sql.acesso.ConnectionFactory.getConfString(base));
            String[] types = {"TABLE"};
            ResultSet rs = conn.getMetaData().getTables(null, "dbo", "%", types);
            while (rs.next()) {
                list.add(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> getColuns(String tabela, String base) {
        List<String> list = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(br.sql.acesso.ConnectionFactory.getConfString(base));
            ResultSet rs = conn.getMetaData().getColumns(null, "dbo", tabela, null);
            while (rs.next()) {
                list.add(rs.getString("COLUMN_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
