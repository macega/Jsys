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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysFeriados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysFeriados.findAll", query = "SELECT j FROM JsysFeriados j"),
    @NamedQuery(name = "JsysFeriados.findByIdFeriado", query = "SELECT j FROM JsysFeriados j WHERE j.idFeriado = :idFeriado"),
    @NamedQuery(name = "JsysFeriados.findByDataFeriado", query = "SELECT j FROM JsysFeriados j WHERE j.dataFeriado = :dataFeriado"),
    @NamedQuery(name = "JsysFeriados.findByObs", query = "SELECT j FROM JsysFeriados j WHERE j.obs = :obs")})
public class JsysFeriados implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idFeriado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFeriado;
    @Basic(optional = false)
    @Column(name = "dataFeriado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFeriado;
    @Basic(optional = false)
    @Column(name = "obs")
    private String obs;

    public JsysFeriados() {
    }

    public JsysFeriados(Integer idFeriado) {
        this.idFeriado = idFeriado;
    }

    public JsysFeriados(Integer idFeriado, Date dataFeriado, String obs) {
        this.idFeriado = idFeriado;
        this.dataFeriado = dataFeriado;
        this.obs = obs;
    }

    public Integer getIdFeriado() {
        return idFeriado;
    }

    public void setIdFeriado(Integer idFeriado) {
        Integer oldIdFeriado = this.idFeriado;
        this.idFeriado = idFeriado;
        changeSupport.firePropertyChange("idFeriado", oldIdFeriado, idFeriado);
    }

    public Date getDataFeriado() {
        return dataFeriado;
    }

    public void setDataFeriado(Date dataFeriado) {
        Date oldDataFeriado = this.dataFeriado;
        this.dataFeriado = dataFeriado;
        changeSupport.firePropertyChange("dataFeriado", oldDataFeriado, dataFeriado);
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        String oldObs = this.obs;
        this.obs = obs;
        changeSupport.firePropertyChange("obs", oldObs, obs);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFeriado != null ? idFeriado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysFeriados)) {
            return false;
        }
        JsysFeriados other = (JsysFeriados) object;
        return (this.idFeriado != null || other.idFeriado == null) && (this.idFeriado == null || this.idFeriado.equals(other.idFeriado));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysFeriados[ idFeriado=" + idFeriado + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
