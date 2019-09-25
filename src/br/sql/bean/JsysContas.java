/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sql.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysContas")
@NamedQueries({
    @NamedQuery(name = "JsysContas.findAll", query = "SELECT j FROM JsysContas j"),
    @NamedQuery(name = "JsysContas.findByIdConta", query = "SELECT j FROM JsysContas j WHERE j.idConta = :idConta"),
    @NamedQuery(name = "JsysContas.findByDescricao", query = "SELECT j FROM JsysContas j WHERE j.descricao = :descricao"),
    @NamedQuery(name = "JsysContas.findByIdGrupo", query = "SELECT j FROM JsysContas j WHERE j.idGrupo = :idGrupo")})
public class JsysContas implements Serializable {
    @Transient
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idConta")
    private String idConta;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "idGrupo")
    private String idGrupo;
    @Column(name = "dataInclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;
    @Basic(optional = false)
    @Column(name = "usuarioInclusao")
    private String usuarioInclusao;
    @Column(name = "dataAlteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @Basic(optional = true)
    @Column(name = "usuarioAlteracao")
    private String usuarioAlteracao;
    
    public JsysContas() {
    }

    public JsysContas(String idConta) {
        this.idConta = idConta;
    }

    public JsysContas(String idConta, String descricao, String idGrupo) {
        this.idConta = idConta;
        this.descricao = descricao;
        this.idGrupo = idGrupo;
    }

    public String getIdConta() {
        return idConta;
    }

    public void setIdConta(String idConta) {
        String oldIdConta = this.idConta;
        this.idConta = idConta;
        changeSupport.firePropertyChange("idConta", oldIdConta, idConta);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        String oldDescricao = this.descricao;
        this.descricao = descricao;
        changeSupport.firePropertyChange("descricao", oldDescricao, descricao);
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        String oldIdGrupo = this.idGrupo;
        this.idGrupo = idGrupo;
        changeSupport.firePropertyChange("idGrupo", oldIdGrupo, idGrupo);
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public String getUsuarioInclusao() {
        return usuarioInclusao;
    }

    public void setUsuarioInclusao(String usuarioInclusao) {
        this.usuarioInclusao = usuarioInclusao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(String usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConta != null ? idConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysContas)) {
            return false;
        }
        JsysContas other = (JsysContas) object;
        return (this.idConta != null || other.idConta == null) && (this.idConta == null || this.idConta.equals(other.idConta));
    }

    @Override
    public String toString() {
        return "br.sql.janelas.financeiro.JsysContas[ idConta=" + idConta + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
