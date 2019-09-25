/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sql.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysBancos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysBancos.findAll", query = "SELECT j FROM JsysBancos j"),
    @NamedQuery(name = "JsysBancos.findByIdBanco", query = "SELECT j FROM JsysBancos j WHERE j.idBanco = :idBanco"),
    @NamedQuery(name = "JsysBancos.findByNomeBanco", query = "SELECT j FROM JsysBancos j WHERE j.nomeBanco = :nomeBanco"),
    @NamedQuery(name = "JsysBancos.findByNumeroBanco", query = "SELECT j FROM JsysBancos j WHERE j.numeroBanco = :numeroBanco"),
    @NamedQuery(name = "JsysBancos.findByNumeroBancoDv", query = "SELECT j FROM JsysBancos j WHERE j.numeroBancoDv = :numeroBancoDv"),
    @NamedQuery(name = "JsysBancos.findByNumeroConta", query = "SELECT j FROM JsysBancos j WHERE j.numeroConta = :numeroConta"),
    @NamedQuery(name = "JsysBancos.findByFone", query = "SELECT j FROM JsysBancos j WHERE j.fone = :fone"),
    @NamedQuery(name = "JsysBancos.findByCidade", query = "SELECT j FROM JsysBancos j WHERE j.cidade = :cidade"),
    @NamedQuery(name = "JsysBancos.findByEstado", query = "SELECT j FROM JsysBancos j WHERE j.estado = :estado")})
public class JsysBancos implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBanco")
    private Integer idBanco;
    @Basic(optional = false)
    @Column(name = "nomeBanco")
    private String nomeBanco;
    @Basic(optional = false)
    @Column(name = "numeroBanco")
    private String numeroBanco;
    @Basic(optional = false)
    @Column(name = "numeroBancoDv")
    private String numeroBancoDv;
    @Basic(optional = false)
    @Column(name = "numeroConta")
    private String numeroConta;
    @Column(name = "fone")
    private String fone;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "estado")
    private String estado;

    public JsysBancos() {
    }

    public JsysBancos(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public JsysBancos(Integer idBanco, String nomeBanco, String numeroBanco, String numeroBancoDv, String numeroConta) {
        this.idBanco = idBanco;
        this.nomeBanco = nomeBanco;
        this.numeroBanco = numeroBanco;
        this.numeroBancoDv = numeroBancoDv;
        this.numeroConta = numeroConta;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        Integer oldIdBanco = this.idBanco;
        this.idBanco = idBanco;
        changeSupport.firePropertyChange("idBanco", oldIdBanco, idBanco);
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        String oldNomeBanco = this.nomeBanco;
        this.nomeBanco = nomeBanco;
        changeSupport.firePropertyChange("nomeBanco", oldNomeBanco, nomeBanco);
    }

    public String getNumeroBanco() {
        return numeroBanco;
    }

    public void setNumeroBanco(String numeroBanco) {
        String oldNumeroBanco = this.numeroBanco;
        this.numeroBanco = numeroBanco;
        changeSupport.firePropertyChange("numeroBanco", oldNumeroBanco, numeroBanco);
    }

    public String getNumeroBancoDv() {
        return numeroBancoDv;
    }

    public void setNumeroBancoDv(String numeroBancoDv) {
        String oldNumeroBancoDv = this.numeroBancoDv;
        this.numeroBancoDv = numeroBancoDv;
        changeSupport.firePropertyChange("numeroBancoDv", oldNumeroBancoDv, numeroBancoDv);
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        String oldNumeroConta = this.numeroConta;
        this.numeroConta = numeroConta;
        changeSupport.firePropertyChange("numeroConta", oldNumeroConta, numeroConta);
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        String oldFone = this.fone;
        this.fone = fone;
        changeSupport.firePropertyChange("fone", oldFone, fone);
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        String oldCidade = this.cidade;
        this.cidade = cidade;
        changeSupport.firePropertyChange("cidade", oldCidade, cidade);
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        String oldEstado = this.estado;
        this.estado = estado;
        changeSupport.firePropertyChange("estado", oldEstado, estado);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBanco != null ? idBanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysBancos)) {
            return false;
        }
        JsysBancos other = (JsysBancos) object;
        return (this.idBanco != null || other.idBanco == null) && (this.idBanco == null || this.idBanco.equals(other.idBanco));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysBancos[ idBanco=" + idBanco + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
