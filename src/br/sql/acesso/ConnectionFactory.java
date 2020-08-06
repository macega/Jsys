package br.sql.acesso;

import br.sql.log.Log;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

/**
 *
 * @author julia
 */
public class ConnectionFactory implements Serializable {

    private static final long serialVersionUID = 1368751264732747141L;
    private static EntityManagerFactory entityManagerFactory = null;
    private static EntityManager entityManager = null;

    public static EntityManager getEntityManagerNew() {
        if (br.JavaApplicationJsys.base.getNome() == null) {
            return null;
        }
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(br.JavaApplicationJsys.NOME_PERSISTENCE, getProperties());
        }
        // se ainda não houver uma instância do EntityManager... cria-se uma e atribui ao atributo estático
        if (entityManager == null) {
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    public static void iniciaBean() {
        if (getEntityManagerNew().getTransaction().isActive()) {
            getEntityManagerNew().getTransaction().rollback();
            getEntityManagerNew().getTransaction().begin();
        } else {
            getEntityManagerNew().getTransaction().begin();
        }
    }

    /**
     * para achar isso tem que usar isso
     * System.out.println(entityManager.getProperties());
     * javax.persistence.jdbc.url=jdbc:sqlserver://MEDINA:1433;databaseName=DADOS,
     * javax.persistence.jdbc.password=jsys8203,
     * javax.persistence.jdbc.driver=com.microsoft.sqlserver.jdbc.SQLServerDriver,
     * javax.persistence.jdbc.user=jsys
     *
     * @return vai retornar um MAP com a a conexão do banco de dados 2005
     */
    public static Map getProperties() {
        Map<Object, Object> prop = new HashMap<>();
        prop.put("javax.persistence.jdbc.url", br.JavaApplicationJsys.INI.getString("BASE " + br.JavaApplicationJsys.base.getNome(), "NOMEDRIVERSQLSERVER")
                + "//" + br.JavaApplicationJsys.INI.getString("BASE " + br.JavaApplicationJsys.base.getNome(), "HOSTSQLSERVER")
                + ":" + br.JavaApplicationJsys.INI.getString("BASE " + br.JavaApplicationJsys.base.getNome(), "PORTASQLSERVER")
                + ";databaseName=" + br.JavaApplicationJsys.INI.getString("BASE " + br.JavaApplicationJsys.base.getNome(), "BANCO DE DADOSSQLSERVER"));
        prop.put("javax.persistence.jdbc.password", br.JavaApplicationJsys.INI.getString("BASE " + br.JavaApplicationJsys.base.getNome(), "SENHASQLSERVER"));
        prop.put("javax.persistence.jdbc.driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        prop.put("javax.persistence.jdbc.user", br.JavaApplicationJsys.INI.getString("BASE " + br.JavaApplicationJsys.base.getNome(), "USUARIOSQLSERVER"));
        return prop;
    }

    /**
     *
     * @return retorona a string de conexao para o bando de dados
     */
    public static String getConfString() {
        return getConfString(br.JavaApplicationJsys.base.getNome());
    }

    public static String getConfString(String base) {
        String valor = br.JavaApplicationJsys.INI.getString("BASE " + base, "NOMEDRIVERSQLSERVER")
                + "//" + br.JavaApplicationJsys.INI.getString("BASE " + base, "HOSTSQLSERVER")
                + ":" + br.JavaApplicationJsys.INI.getString("BASE " + base, "PORTASQLSERVER")
                + ";databaseName=" + br.JavaApplicationJsys.INI.getString("BASE " + base, "BANCO DE DADOSSQLSERVER")
                + ";user=" + br.JavaApplicationJsys.INI.getString("BASE " + base, "USUARIOSQLSERVER")
                + ";password=" + br.JavaApplicationJsys.INI.getString("BASE " + base, "SENHASQLSERVER");
        return valor;
    }

    /**
     * O método getConnection retorna uma conexão com o banco de dados baseado
     * nos parâmetros fornecidos.
     *
     * retorna a conexão a partir do método getConnection de DriverManager
     *
     * @param url O endereço da base de dados.
     * @return Uma conexão com o banco de dados especificado na url.
     * @throws SQLException Caso ocorra algum problema durante a conexão.
     */
    public static Connection getConnection(String url) throws SQLException {
        return DriverManager.getConnection(url);
    }

    /**
     * Obtém uma conexão para a base de dados sakila.
     *
     * @return Uma conexão para a base de dados sakila.
     * @throws SQLException Caso ocorra algum problema durante a conexão.
     */
    public static Connection getSakilaConnection() throws SQLException {
        return getConnection(getConfString());
    }

    /**
     *
     * @return retorna o nome da base de dados
     */
    public static String getNomeSQLServer() {
        return br.JavaApplicationJsys.INI.getString("BASE " + br.JavaApplicationJsys.base.getNome(), "NOMESQLSERVER");
    }

    /**
     * Este metodo serve para inserir um retistro no banco de dados
     *
     * @param o
     * @return verdadero se nao ouve erro ao salvar o object no banco de dados
     */
    public static Object insert(Object o) {
        try {
            iniciaBean();
            getEntityManagerNew().persist(o);
            commit();
            return o;
        } catch (RollbackException ex) {
            Log.registraErro("GerenteEntidade", "insert(" + o.getClass().toString() + ")", ex);
            getEntityManagerNew().getTransaction().rollback();
            return null;
        }
    }

    public static Object insertOrUpdate(Object o) {
        try {
            iniciaBean();
            if (getEntityManagerNew().contains(o)) {
                o = getEntityManagerNew().merge(o);
            } else {
                getEntityManagerNew().persist(o);
            }
            commit();
            return o;
        } catch (RollbackException ex) {
            Log.registraErro("GerenteEntidade", "insertOrUpdate(" + o.getClass().toString() + ")", ex);
            //getEntityManagerNew().getTransaction().rollback();
            return null;
        } 
    }

    /**
     * Este metodo serve para apagar um objeto no banco de dados
     *
     * @param o
     */
    public static void delete(Object o) {
        try {
            iniciaBean();
            getEntityManagerNew().remove(getEntityManagerNew().merge(o));
            commit();
        } catch (RollbackException ex) {
            Log.registraErro("GerenteEntidade", "delete(" + o.getClass().toString() + ")", ex);
            getEntityManagerNew().getTransaction().rollback();
        }
    }

    /**
     * Este Metodo vai atulizar o registro no banco de dados
     *
     * @param o
     * @return verdadeiro se o retistro for salvo com sucesso
     */
    public static Object update(Object o) {
        try {
            iniciaBean();
            o = getEntityManagerNew().merge(o);
            commit();
            return o;
        } catch (RollbackException ex) {
            Log.registraErro("GerenteEntidade", "update(" + o.getClass().toString() + ")", ex);
            getEntityManagerNew().getTransaction().rollback();
            return null;
        }
    }

    private static void commit() throws RollbackException {
        getEntityManagerNew().getTransaction().commit();
    }

    public static void salvar() throws RollbackException {
        iniciaBean();
        commit();
    }

    public static void cancelar() {
        iniciaBean();
        getEntityManagerNew().getTransaction().rollback();
    }
}
