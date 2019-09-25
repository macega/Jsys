/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sql.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "jsysPontoCompensacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysPontoCompensacao.findAll", query = "SELECT j FROM JsysPontoCompensacao j"),
    @NamedQuery(name = "JsysPontoCompensacao.findById", query = "SELECT j FROM JsysPontoCompensacao j WHERE j.id = :id"),
    @NamedQuery(name = "JsysPontoCompensacao.findByIdFuncionario", query = "SELECT j FROM JsysPontoCompensacao j WHERE j.idFuncionario = :idFuncionario"),
    @NamedQuery(name = "JsysPontoCompensacao.findByData", query = "SELECT j FROM JsysPontoCompensacao j WHERE j.data = :data"),
    @NamedQuery(name = "JsysPontoCompensacao.findByDataPeriodo", query = "SELECT j FROM JsysPontoCompensacao j WHERE j.data BETWEEN :dataInicio AND :dataFim"),
    @NamedQuery(name = "JsysPontoCompensacao.findByPeriodo", query = "SELECT j FROM JsysPontoCompensacao j WHERE j.periodo = :periodo")})
public class JsysPontoCompensacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "idFuncionario")
    private int idFuncionario;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Column(name = "periodo")
    private String periodo;

    public JsysPontoCompensacao() {
    }

    public JsysPontoCompensacao(Long id) {
        this.id = id;
    }

    public JsysPontoCompensacao(Long id, int idFuncionario, Date data) {
        this.id = id;
        this.idFuncionario = idFuncionario;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysPontoCompensacao)) {
            return false;
        }
        JsysPontoCompensacao other = (JsysPontoCompensacao) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysPontoCompensacao[ id=" + id + " ]";
    }
    
}
