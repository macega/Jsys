package br.sql.util;

import br.sql.bean.JsysClientes;
import br.sql.bean.JsysLojas;
import br.sql.bean.JsysParametros;
import br.sql.bean.JsysProdutosTBarra;
import br.sql.bean.JsysSubConta;
import br.sql.log.Log;
import java.awt.HeadlessException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Juliano Alves Medina
 */
public class Retorna {

    //private static final javax.persistence.EntityManager ENTITY_MANAGER = javax.persistence.Persistence.createEntityManagerFactory(br.sql.acesso.BaseDados.NOME_PERSISTENCE, br.sql.acesso.BaseDados.getConf()).createEntityManager();
    /**
     *
     * @param namedQuery
     * @param parameter
     * @return um Object com o primerio resultado da consulta
     */
    @SuppressWarnings("unchecked")
    public static Object findOneResult(String namedQuery, Map parameter) {
        try {
            javax.persistence.Query q = br.sql.acesso.ConnectionFactory.getEntityManagerNew().createNamedQuery(namedQuery);
            parameter.keySet().forEach((key) -> {
                q.setParameter(key.toString(), parameter.get(key));
            });
            List<Object> data = q.getResultList();
            data.forEach((entity) -> {
                br.sql.acesso.ConnectionFactory.getEntityManagerNew().refresh(entity);
            });
            return data.isEmpty() ? null : data.get(0);
        } catch (Exception e) {
            Log.registraErro("Retorna", "findOneResult", e);
            finalizar(e.getMessage());
        }
        return null;
    }

    private static void finalizar(String erro) throws HeadlessException {
        String mensagen = "teste 123";
        for (int i = 0; i < erro.length(); i++) {
            if (i <= 50) {
                mensagen += erro.substring(i, erro.length());
            }
        }

        JOptionPane.showMessageDialog(null, mensagen, "ERRO", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    @SuppressWarnings("unchecked")
    public static Object findOneResult(String namedQuery) {
        try {
            javax.persistence.Query q = br.sql.acesso.ConnectionFactory.getEntityManagerNew().createNamedQuery(namedQuery);
            List<Object> data = q.getResultList();
            data.forEach((entity) -> {
                br.sql.acesso.ConnectionFactory.getEntityManagerNew().refresh(entity);
            });
            return data.isEmpty() ? null : data.get(0);
        } catch (Exception e) {
            Log.registraErro("Retorna", "findOneResult", e);
            finalizar(e.getMessage());
        }
        return null;
    }

    /**
     *
     * @param namedQuery
     * @param parameter
     * @return uma List
     */
    @SuppressWarnings("unchecked")
    public static List<Object> findList(String namedQuery, Map parameter) {
        try {
            javax.persistence.Query q = br.sql.acesso.ConnectionFactory.getEntityManagerNew().createNamedQuery(namedQuery);
            for (Object key : parameter.keySet()) {
                q.setParameter(key.toString(), parameter.get(key));
            }
            List<Object> data = q.getResultList();
            for (Object entity : data) {
                br.sql.acesso.ConnectionFactory.getEntityManagerNew().refresh(entity);
            }
            return data;
        } catch (Exception e) {
            Log.registraErro("Retorna", "findList", e);
            finalizar(e.getMessage());
        }
        return null;
    }

    /**
     *
     * @param namedQuery
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Object> findList(String namedQuery) {
        try {
            javax.persistence.Query q = br.sql.acesso.ConnectionFactory.getEntityManagerNew().createNamedQuery(namedQuery);
            List<Object> data = q.getResultList();
            for (Object entity : data) {
                br.sql.acesso.ConnectionFactory.getEntityManagerNew().refresh(entity);
            }
            return data;
        } catch (Exception e) {
            Log.registraErro("Retorna", "findList", e);
            finalizar(e.getMessage());
        }
        return null;
    }

    /**
     *
     * @param namedQuery
     * @return
     */
    @SuppressWarnings("unchecked")
    public static java.util.Collection findCollection(String namedQuery) {
        try {
            javax.persistence.Query q = br.sql.acesso.ConnectionFactory.getEntityManagerNew().createNamedQuery(namedQuery);
            java.util.Collection data = q.getResultList();
            for (Object entity : data) {
                br.sql.acesso.ConnectionFactory.getEntityManagerNew().refresh(entity);
            }
            return data;
        } catch (Exception e) {
            Log.registraErro("Retorna", "findCollection", e);
            finalizar(e.getMessage());
        }
        return null;
    }

    /**
     *
     * @param namedQuery
     * @param parameter
     * @return
     */
    @SuppressWarnings("unchecked")
    public static java.util.Collection findCollection(String namedQuery, Map parameter) {
        try {
            javax.persistence.Query q = br.sql.acesso.ConnectionFactory.getEntityManagerNew().createNamedQuery(namedQuery);
            for (Object key : parameter.keySet()) {
                q.setParameter(key.toString(), parameter.get(key));
            }
            java.util.Collection data = q.getResultList();
            for (Object entity : data) {
                br.sql.acesso.ConnectionFactory.getEntityManagerNew().refresh(entity);
            }
            return data;
        } catch (Exception e) {
            Log.registraErro("Retorna", "findCollection", e);
            finalizar(e.getMessage());
        }
        return null;
    }

    /**
     *
     * @param valor, pasar o codigo do produto
     * @return um registro de JsysProdutosT
     */
    @SuppressWarnings("unchecked")
    public static br.sql.bean.JsysProdutosT jsysProdutoT(Integer valor) {
        try {
            javax.persistence.Query q = br.sql.acesso.ConnectionFactory.getEntityManagerNew().createNamedQuery("JsysProdutosT.findByIdProduto");
            q.setParameter("idProduto", valor);
            List<br.sql.bean.JsysProdutosT> data = q.getResultList();
            for (Object entity : data) {
                br.sql.acesso.ConnectionFactory.getEntityManagerNew().refresh(entity);
            }
            return data.get(0);
        } catch (Exception e) {
            Log.registraErro("Retorna", "jsysProdutoT", e);
        }
        return null;
    }

    /**
     *
     * @param valor, pasar o codigo do produto
     * @return uma List de JsysProdutosTPrecos
     */
    @SuppressWarnings("unchecked")
    public static List<br.sql.bean.JsysProdutosTPrecos> jsysProdutoTPrecos(Integer valor) {
        try {
            javax.persistence.Query q = br.sql.acesso.ConnectionFactory.getEntityManagerNew().createNamedQuery("JsysProdutosTPrecos.findByIdProduto");
            q.setParameter("idProduto", valor);
            List<br.sql.bean.JsysProdutosTPrecos> data = q.getResultList();
            for (Object entity : data) {
                br.sql.acesso.ConnectionFactory.getEntityManagerNew().refresh(entity);
            }
            return data;
        } catch (Exception e) {
            Log.registraErro("Retorna", "jsysProdutoTPrecos", e);
        }
        return null;
    }

    /**
     *
     * @param valor, pasar o codigo do produto
     * @return um registro de JsysLojas
     */
    @SuppressWarnings("unchecked")
    public static br.sql.bean.JsysLojas jsysLojas(String valor) {
        try {
            javax.persistence.Query q = br.sql.acesso.ConnectionFactory.getEntityManagerNew().createNamedQuery("JsysLojas.findByIdloja");
            q.setParameter("idloja", valor);
            List<br.sql.bean.JsysLojas> data = q.getResultList();
            for (Object entity : data) {
                br.sql.acesso.ConnectionFactory.getEntityManagerNew().refresh(entity);
            }
            return data.get(0);
        } catch (Exception e) {
            Log.registraErro("Retorna", "jsysLojas", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static List<JsysLojas> jsysLojas() {
        try {
            javax.persistence.Query q = br.sql.acesso.ConnectionFactory.getEntityManagerNew().createNamedQuery("JsysLojas.findAll");
            List<JsysLojas> data = q.getResultList();
            for (Object entity : data) {
                br.sql.acesso.ConnectionFactory.getEntityManagerNew().refresh(entity);
            }
            return data;
        } catch (Exception e) {
            Log.registraErro("Retorna", "jsysLojas", e);
        }
        return null;
    }

    /**
     *
     * @param valor, pasar o codigo do produto
     * @return uma List de JsysProdutosTPrecos
     */
    @SuppressWarnings("unchecked")
    public static List<JsysProdutosTBarra> jsysProdutosTBarra(Integer valor) {
        try {
            javax.persistence.Query q = br.sql.acesso.ConnectionFactory.getEntityManagerNew().createNamedQuery("JsysProdutosTBarra.findByIdProduto");
            q.setParameter("idProduto", valor);
            List<JsysProdutosTBarra> data = q.getResultList();
            for (Object entity : data) {
                br.sql.acesso.ConnectionFactory.getEntityManagerNew().refresh(entity);
            }
            return data;
        } catch (Exception e) {
            Log.registraErro("Retorna", "jsysProdutosTBarra", e);
        }
        return null;
    }

    public static JsysParametros JsysParametros() {
        try {
            return (JsysParametros) findOneResult("JsysParametros.findAll");
        } catch (Exception e) {
            return new JsysParametros();
        }
    }

    public static JsysClientes jsysClientes(Integer idCliente) {
        Map<Object, Object> filtro = new HashMap<>();
        filtro.put("idCliente", idCliente);
        JsysClientes cliente = (JsysClientes) findOneResult("JsysClientes.findByIdCliente", filtro);
        return cliente;
    }

    public static JsysSubConta pagamentos(String idGeral) {
        Map<Object, Object> filtro = new HashMap<>();
        filtro.put("idGeral", idGeral);
        JsysSubConta subConta = (JsysSubConta) findOneResult("JsysSubConta.findByIdGeral", filtro);
        return subConta;
    }

    public static String getLoja() {
        java.util.Map<Object, Object> filtro = new java.util.HashMap<>();
        filtro.put("ativo", true);
        String l = ((JsysLojas) findOneResult("JsysLojas.findByAtivo", filtro)).getIdloja();
        return l;
    }
}
