/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.bean.views;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysResumoDeCaixa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysResumoDeCaixa.findAll", query = "SELECT j FROM JsysResumoDeCaixa j ORDER BY j.id"),
    @NamedQuery(name = "JsysResumoDeCaixa.findByDescricao", query = "SELECT j FROM JsysResumoDeCaixa j WHERE j.descricao = :descricao ORDER BY j.id"),
    @NamedQuery(name = "JsysResumoDeCaixa.findByData", query = "SELECT j FROM JsysResumoDeCaixa j WHERE j.data = :data ORDER BY j.id"),
    @NamedQuery(name = "JsysResumoDeCaixa.findByValor", query = "SELECT j FROM JsysResumoDeCaixa j WHERE j.valor = :valor ORDER BY j.id")
})
public class JsysResumoDeCaixa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "cor")
    private String cor;

    public JsysResumoDeCaixa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
