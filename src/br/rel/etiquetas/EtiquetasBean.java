/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rel.etiquetas;

import java.math.BigDecimal;

/**
 *
 * @author Juliano Alves Medina
 */
public class EtiquetasBean {

    private java.lang.Integer id;
    private String codigo;
    private String descricao;
    private BigDecimal precoVenda;
    private String marca;

    public EtiquetasBean(int id, String codigo, String descricao, BigDecimal precoVenda, String marca) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.precoVenda = precoVenda;
        this.marca = marca;
    }

    public java.lang.Integer getId() {
        return id;
    }

    public void setId(java.lang.Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
