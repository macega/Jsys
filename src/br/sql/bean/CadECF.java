/*
 * To change this template, choose Tools | Templates
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

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "CadECF")
@NamedQueries({
    @NamedQuery(name = "CadECF.findAll", query = "SELECT c FROM CadECF c"),
    @NamedQuery(name = "CadECF.findAllAtivos", query = "SELECT c FROM CadECF c WHERE c.ativo = true"),
    @NamedQuery(name = "CadECF.findById", query = "SELECT c FROM CadECF c WHERE c.id = :id"),
    @NamedQuery(name = "CadECF.findByTipoECF", query = "SELECT c FROM CadECF c WHERE c.tipoECF = :tipoECF"),
    @NamedQuery(name = "CadECF.findByNumeroSerie", query = "SELECT c FROM CadECF c WHERE c.numeroSerie = :numeroSerie"),
    @NamedQuery(name = "CadECF.findBySecECF", query = "SELECT c FROM CadECF c WHERE c.secECF = :secECF")})
public class CadECF implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "TipoECF")
    private String tipoECF;
    @Basic(optional = false)
    @Column(name = "numeroSerie")
    private String numeroSerie;
    @Basic(optional = false)
    @Column(name = "SecECF")
    private String secECF;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;

    public CadECF() {
    }

    public CadECF(Integer id) {
        this.id = id;
    }

    public CadECF(Integer id, String tipoECF, String numeroSerie, String secECF) {
        this.id = id;
        this.tipoECF = tipoECF;
        this.numeroSerie = numeroSerie;
        this.secECF = secECF;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getTipoECF() {
        return tipoECF;
    }

    public void setTipoECF(String tipoECF) {
        String oldTipoECF = this.tipoECF;
        this.tipoECF = tipoECF;
        changeSupport.firePropertyChange("tipoECF", oldTipoECF, tipoECF);
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        String oldNumeroSerie = this.numeroSerie;
        this.numeroSerie = numeroSerie;
        changeSupport.firePropertyChange("numeroSerie", oldNumeroSerie, numeroSerie);
    }

    public String getSecECF() {
        return secECF;
    }

    public void setSecECF(String secECF) {
        String oldSecECF = this.secECF;
        this.secECF = secECF;
        changeSupport.firePropertyChange("secECF", oldSecECF, secECF);
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        boolean oldAtivo = this.ativo;
        this.ativo = ativo;
        changeSupport.firePropertyChange("ativo", oldAtivo, ativo);
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
        if (!(object instanceof CadECF)) {
            return false;
        }
        CadECF other = (CadECF) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "br.sql.cadastros.CadECF[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
