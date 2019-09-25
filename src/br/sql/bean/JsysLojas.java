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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysLojas")
@NamedQueries({
    @NamedQuery(name = "JsysLojas.findAll", query = "SELECT j FROM JsysLojas j"),
    @NamedQuery(name = "JsysLojas.findByIdloja", query = "SELECT j FROM JsysLojas j WHERE j.idloja = :idloja"),
    @NamedQuery(name = "JsysLojas.findByNomeLoja", query = "SELECT j FROM JsysLojas j WHERE j.nomeLoja = :nomeLoja"),
    @NamedQuery(name = "JsysLojas.findByAtivo", query = "SELECT j FROM JsysLojas j WHERE j.ativo = :ativo"),
    @NamedQuery(name = "JsysLojas.findByCnpj", query = "SELECT j FROM JsysLojas j WHERE j.cnpj = :cnpj"),
    @NamedQuery(name = "JsysLojas.findByDeposito", query = "SELECT j FROM JsysLojas j WHERE j.deposito = :deposito")})
public class JsysLojas implements Serializable {

    @Transient
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idloja")
    private String idloja;
    @Basic(optional = false)
    @Column(name = "nomeLoja")
    private String nomeLoja;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @Basic(optional = false)
    @Column(name = "cnpj")
    private String cnpj;
    @Basic(optional = false)
    @Column(name = "deposito")
    private boolean deposito;
    @Basic(optional = false)
    @Column(name = "nomeBancoDados")
    private String nomeBancoDados;

    public JsysLojas() {
    }

    public JsysLojas(String idloja) {
        this.idloja = idloja;
    }

    public JsysLojas(String idloja, String nomeLoja) {
        this.idloja = idloja;
        this.nomeLoja = nomeLoja;
    }

    public String getIdloja() {
        return idloja;
    }

    public void setIdloja(String idloja) {
        String oldIdloja = this.idloja;
        this.idloja = idloja;
        changeSupport.firePropertyChange("idloja", oldIdloja, idloja);
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        String oldNomeLoja = this.nomeLoja;
        this.nomeLoja = nomeLoja;
        changeSupport.firePropertyChange("nomeLoja", oldNomeLoja, nomeLoja);
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        boolean oldAtivo = this.ativo;
        this.ativo = ativo;
        changeSupport.firePropertyChange("ativo", oldAtivo, ativo);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        String oldCnpj = this.cnpj;
        this.cnpj = cnpj;
        changeSupport.firePropertyChange("cnpj", oldCnpj, cnpj);
    }

    public boolean getDeposito() {
        return deposito;
    }

    public void setDeposito(boolean deposito) {
        boolean oldDeposito = this.deposito;
        this.deposito = deposito;
        changeSupport.firePropertyChange("deposito", oldDeposito, deposito);
    }

    public String getNomeBancoDados() {
        return nomeBancoDados;
    }

    public void setNomeBancoDados(String nomeBancoDados) {
        String oldNomeBancoDados = this.nomeBancoDados;
        this.nomeBancoDados = nomeBancoDados;
        changeSupport.firePropertyChange("nomeBancoDados", oldNomeBancoDados, nomeBancoDados);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idloja != null ? idloja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysLojas)) {
            return false;
        }
        JsysLojas other = (JsysLojas) object;
        return (this.idloja != null || other.idloja == null) && (this.idloja == null || this.idloja.equals(other.idloja));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysLojas[ idloja=" + idloja + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
