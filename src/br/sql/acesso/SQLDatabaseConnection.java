package br.sql.acesso;

import br.JavaApplicationJsys;
import br.sql.log.Log;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class SQLDatabaseConnection {

    private Connection connection;

    public SQLDatabaseConnection() {
        connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(ConnectionFactory.getConfString(JavaApplicationJsys.base.getNome()));
        } catch (SQLException | ClassNotFoundException ex) {
            Log.registraErro("SQLDatabaseConnection", "conn", ex);
        }
    }

    /**
     *
     * @param tabela
     * @param base
     * @return
     */
    public List<String> getColuns(String tabela, String base) {
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = connection.getMetaData().getColumns(null, "dbo", tabela, null);
            while (rs.next()) {
                list.add(rs.getString("COLUMN_NAME"));
            }
        } catch (SQLException ex) {
            Log.registraErro("SQLDatabaseConnection", "getColuns", ex);
        }
        return list;
    }

    /**
     *
     * @param base
     * @return
     */
    public List<String> getTables(String base) {
        List<String> list = new ArrayList<>();
        try {
            String[] types = {"TABLE"};
            ResultSet rs = connection.getMetaData().getTables(null, "dbo", "%", types);
            while (rs.next()) {
                list.add(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException ex) {
            Log.registraErro("SQLDatabaseConnection", "getTables", ex);
        }
        return list;
    }

    private ResultSet executaRS(String sql) {
        try {
            Statement stm = connection.createStatement();
            return stm.executeQuery(sql);
        } catch (SQLException ex) {
            Log.registraErro("SQLDatabaseConnection", "executaRS", ex);
        }
        return null;
    }

    /**
     *
     * @param sql
     * @return
     */
    public ResultSet executesqlScriptRS(String sql) {
        return executaRS(sql);
    }

    /**
     *
     * @param sql
     * @return
     */
    public ResultSet executesqlScriptRS(StringBuilder sql) { 
        return executaRS(sql.toString());
    }

    private void executa(String sql) { 
        try {
            Statement stm = connection.createStatement();
            stm.execute(sql);
        } catch (SQLException ex) {
            Log.registraErro("SQLDatabaseConnection", "executa", ex);
        }
    }

    /**
     *
     * @param sql passar uma String com um Script sql
     */
    public void executeSqlScript(String sql) { 
        executa(sql);
    }

    /**
     *
     * @param sql passar uma StringBuilder com um Script sql
     */
    public void executeSqlScript(StringBuilder sql) { 
        executa(sql.toString());
    }

    /**
     * @return retrona um int
     * @param procedure enviar a query da procedure ex: "procedure(?, ?, ?)"
     */
    public int execProcudureRetInt(String procedure) {
        try {
            CallableStatement cStmt = connection.prepareCall("{ ? = call " + procedure + " }");
            cStmt.registerOutParameter(1, Types.INTEGER);
            cStmt.execute();
            return cStmt.getInt(1);
        } catch (SQLException ex) {
            Log.registraErro("SQLDatabaseConnection", "execProcudureRetInt", ex);
        }
        return -1;
    }

    /**
     * @return retrona um int
     * @param procedure enviar a query da procedure ex: "procedure(?, ?, ?)"
     */
    public int execProcudureRetInt(StringBuilder procedure) {
        return execProcudureRetInt(procedure.toString());
    }

    /**
     *
     * @param tabela nome da tabela no bando de dados
     * @param campo campo que vai ser o sequencial
     * @param campoChave composiçao do campo
     * @param valorCampoChave valor do campo a ser verificado
     * @return
     */
    public int sequenciaTabela(String tabela, String campo, String campoChave, String valorCampoChave) {
        StringBuilder script = new StringBuilder();
        script.append("pegaSequencia('")
                .append(tabela).append("', '")
                .append(campo).append("', '")
                .append(campoChave).append("', '")
                .append(valorCampoChave).append("')");
        return execProcudureRetInt(script);
    }

    /**
     *
     * @param tabela
     * @param campo
     * @return
     */
    public int sequenciaTabela(String tabela, String campo) {
        return sequenciaTabela(tabela, campo, "", "");
    }

    /**
     * @return either (1) the row count for SQL Data Manipulation Language (DML)
     * statements or (2) 0 for SQL statements that return nothing
     * @param script enviar a query sql para ser executada";
     */
    public int execSQLUpdate(String script) {
        try {
            Statement stm = connection.createStatement();
            return stm.executeUpdate(script);
        } catch (SQLException ex) {
            Log.registraErro("SQLDatabaseConnection", "execSQLUpdate", ex);
        }
        return 0;
    }

    /**
     * @return retrona um int
     * @param script enviar a query sql para ser executada";
     */
    public int execSQLUpdate(StringBuilder script) {
        return execSQLUpdate(script.toString());
    }

    /**
     * @return retrona um ResultSet
     * @param script enviar a query sql para ser executada";
     */
    public ResultSet execSQL(String script) {
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(script);
            return rs;
        } catch (SQLException ex) {
            Log.registraErro("SQLDatabaseConnection", "execSQL", ex);
        }
        return null;
    }

    /**
     * @return retrona um ResultSet
     * @param script enviar a query sql para ser executada";
     */
    public ResultSet execSQL(StringBuilder script) {
        return execSQL(script.toString());
    }

    /**
     *
     * @param procedure enviar a query da procedure ex: "procedure(?, ?, ?)"
     */
    public void execProcedure(String procedure) {
        try {
            CallableStatement cStmt = connection.prepareCall("{call " + procedure + " }");
            cStmt.execute();
        } catch (SQLException ex) {
            Log.registraErro("ExecutaQuery", "execProcedure", ex);
        }
    }

    /**
     *
     * @param procedure enviar a query da procedure ex: "procedure(?, ?, ?)"
     */
    public void execProcedure(StringBuilder procedure) {
        execProcedure(procedure.toString());
    }

//    public void persist(Object object) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dados");
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        try {
//            em.persist(object);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            em.getTransaction().rollback();
//        } finally {
//            em.close();
//        }
//    }
}
