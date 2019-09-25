/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.bean.views;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
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
@Table(name = "jsysResumoDeRetiradas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysResumoDeRetiradas.findAll", query = "SELECT j FROM JsysResumoDeRetiradas j"),
    @NamedQuery(name = "JsysResumoDeRetiradas.findByDescricao", query = "SELECT j FROM JsysResumoDeRetiradas j WHERE j.descricao = :descricao"),
    @NamedQuery(name = "JsysResumoDeRetiradas.findByData", query = "SELECT j FROM JsysResumoDeRetiradas j WHERE j.data = :data ORDER BY j.dataRetirada, j.valor"),
    @NamedQuery(name = "JsysResumoDeRetiradas.findByValor", query = "SELECT j FROM JsysResumoDeRetiradas j WHERE j.valor = :valor"),
    @NamedQuery(name = "JsysResumoDeRetiradas.findByDataRetirada", query = "SELECT j FROM JsysResumoDeRetiradas j WHERE j.dataRetirada = :dataRetirada"),
    @NamedQuery(name = "JsysResumoDeRetiradas.findByCor", query = "SELECT j FROM JsysResumoDeRetiradas j WHERE j.cor = :cor")})
public class JsysResumoDeRetiradas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "dataRetirada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRetirada;
    @Column(name = "NomeRetData")
    @Temporal(TemporalType.TIMESTAMP)
    private Date NomeRetData;
    @Basic(optional = false)
    @Column(name = "cor")
    private String cor;

    public JsysResumoDeRetiradas() {
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

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public Date getNomeRetData() {
        return NomeRetData;
    }

    public void setNomeRetData(Date NomeRetData) {
        this.NomeRetData = NomeRetData;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
