package br.sql.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Juliano Alves Medina
 */
public class ManagerSQL {

    private String tabela = new String();
    private String with = new String();
    private String[] campos = new String[0];
    private String[] order = new String[0];
    private String[] agrupar = new String[0];
    private Map<Object, Object> onde = new HashMap<>();

    public ManagerSQL() {
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public void setWith(String with) {
        this.with = with;
    }

    public void setCampos(String[] campos) {
        this.campos = campos;
    }

    public void setOrder(String[] order) {
        this.order = order;
    }

    public void setAgrupar(String[] agrupar) {
        this.agrupar = agrupar;
    }

    /**
     *
     * @param onde ex: Map<Object, Object> onde = new HashMap<>();
     * onde.put("jsysClientes.inativo", "= 0"); 
     * onde.put("jsysClientes.cliente", "= 1");
     */
    public void setOnde(Map<Object, Object> onde) {
        if (Validar.isNotNull(onde)) {
            this.onde = onde;
        }
    }

    private String montaSQL(String consulta) {
        StringBuilder sql = new StringBuilder();
        // adiciona os campos a SQL
        StringBuilder camp = new StringBuilder();
        for (String campo : campos) {
            camp.append(", ");
            camp.append(campo);
        }
        sql.append(with);
        sql.append(" SELECT ");
        sql.append(camp.substring(1));
        sql.append(" FROM ");
        sql.append(tabela);
        sql.append(" ");

        StringBuilder o = new StringBuilder();
        // consulta fixa na sql
        for (Object key : onde.keySet()) {
            if (onde.get(key) instanceof Date[]) {
                Date[] d = (Date[]) onde.get(key);
                if (d.length == 2) {
                    o.append(" and (");
                    o.append(key);
                    o.append(" between '");
                    o.append(ManagerData.convertUsDate(d[0]));
                    o.append("' and '");
                    o.append(ManagerData.convertUsDate(d[1]));
                    o.append("')");
                }
            } else {
                o.append(" and (");
                o.append(key);
                o.append(" ");
                o.append(onde.get(key));
                o.append(") ");
            }
        }

        // monta a consulta a realizar
        if (!consulta.equals("")) {
            String[] palavras = consulta.trim().split(" ");
            for (String palavra : palavras) {
                StringBuilder cam = new StringBuilder();
                for (int i = 0; i < campos.length; i++) {
                    String campo = campos[i];
                    if (verificaCampo(campo)) {
                        if (i == 0) {
                            cam.append("CONCAT(ISNULL(CAST(");
                        } else {
                            cam.append(", ISNULL(CAST(");
                        }
                        cam.append(campo);
                        cam.append(" AS VARCHAR), '')");
                    }
                }
                cam.append(" )");
                o.append(" and (");
                o.append(cam);
                o.append(" LIKE '%");
                o.append(palavra);
                o.append("%')");
            }
        }
        if (o.length() > 5) {
            sql.append(" WHERE ");
            sql.append(o.substring(4));
        }

        // veficca se tem agrupamento 
        for (int i = 0; i < agrupar.length; i++) {
            String campo = agrupar[i];
            if (i == 0) {
                sql.append(" GROUP BY ").append(campo);
            } else {
                sql.append(", ").append(campo);
            }
        }

        // adiciona a ordenação da consulta
        for (int i = 0; i < order.length; i++) {
            String orden = order[i];
            if (i == 0) {
                sql.append(" ORDER BY ").append(orden);
            } else {
                sql.append(", ").append(orden);
            }
        }
        return sql.toString();
    }

    /**
     *
     * @param consulta Palavras a serem consultadas sepradas por espaço " "
     * @return retorna a sql Contruida com Filtro
     */
    public String getSQL(String consulta) {
        return montaSQL(consulta);
    }

    /**
     *
     * @return retorna a sql Contruida sem Filtro
     */
    public String getSQL() {
        return montaSQL("");
    }

    private boolean verificaCampo(String campo) {
        return !campo.toLowerCase().contains("avg(")
                && !campo.toLowerCase().contains("convert(")
                && !campo.toLowerCase().contains("datediff(")
                && !campo.toLowerCase().contains("max(")
                && !campo.toLowerCase().contains("concat(");
    }
}
