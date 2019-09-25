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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "agendaTelefonica")
@NamedQueries({
    @NamedQuery(name = "AgendaTelefonica.findAll", query = "SELECT a FROM AgendaTelefonica a"),
    @NamedQuery(name = "AgendaTelefonica.findByIdAgenda", query = "SELECT a FROM AgendaTelefonica a WHERE a.idAgenda = :idAgenda"),
    @NamedQuery(name = "AgendaTelefonica.findByNomeContato", query = "SELECT a FROM AgendaTelefonica a WHERE a.nomeContato = :nomeContato"),
    @NamedQuery(name = "AgendaTelefonica.findByFoneFixo", query = "SELECT a FROM AgendaTelefonica a WHERE a.foneFixo = :foneFixo"),
    @NamedQuery(name = "AgendaTelefonica.findByFoneCelular", query = "SELECT a FROM AgendaTelefonica a WHERE a.foneCelular = :foneCelular"),
    @NamedQuery(name = "AgendaTelefonica.findByGrupo", query = "SELECT a FROM AgendaTelefonica a WHERE a.grupo = :grupo")})
public class AgendaTelefonica implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idAgenda")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAgenda;
    @Basic(optional = false)
    @Column(name = "nomeContato")
    private String nomeContato;
    @Basic(optional = false)
    @Column(name = "foneFixo")
    private String foneFixo;
    @Basic(optional = false)
    @Column(name = "foneCelular")
    private String foneCelular;
    @Basic(optional = false)
    @Lob
    @Column(name = "obs")
    private String obs;
    @Basic(optional = false)
    @Column(name = "grupo")
    private String grupo;

    public AgendaTelefonica() {
    }

    public AgendaTelefonica(Integer idAgenda) {
        this.idAgenda = idAgenda;
    }

    public AgendaTelefonica(String nomeContato, String foneFixo, String foneCelular, String obs, String grupo) {
        this.nomeContato = nomeContato;
        this.foneFixo = foneFixo;
        this.foneCelular = foneCelular;
        this.obs = obs;
        this.grupo = grupo;
    }

    public Integer getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Integer idAgenda) {
        Integer oldIdAgenda = this.idAgenda;
        this.idAgenda = idAgenda;
        changeSupport.firePropertyChange("idAgenda", oldIdAgenda, idAgenda);
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        String oldNomeContato = this.nomeContato;
        this.nomeContato = nomeContato;
        changeSupport.firePropertyChange("nomeContato", oldNomeContato, nomeContato);
    }

    public String getFoneFixo() {
        return foneFixo;
    }

    public void setFoneFixo(String foneFixo) {
        String oldFoneFixo = this.foneFixo;
        this.foneFixo = foneFixo;
        changeSupport.firePropertyChange("foneFixo", oldFoneFixo, foneFixo);
    }

    public String getFoneCelular() {
        return foneCelular;
    }

    public void setFoneCelular(String foneCelular) {
        String oldFoneCelular = this.foneCelular;
        this.foneCelular = foneCelular;
        changeSupport.firePropertyChange("foneCelular", oldFoneCelular, foneCelular);
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        String oldObs = this.obs;
        this.obs = obs;
        changeSupport.firePropertyChange("obs", oldObs, obs);
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        String oldGrupo = this.grupo;
        this.grupo = grupo;
        changeSupport.firePropertyChange("grupo", oldGrupo, grupo);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgenda != null ? idAgenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AgendaTelefonica)) {
            return false;
        }
        AgendaTelefonica other = (AgendaTelefonica) object;
        if ((this.idAgenda == null && other.idAgenda != null) || (this.idAgenda != null && !this.idAgenda.equals(other.idAgenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.sql.bean.AgendaTelefonica[ idAgenda=" + idAgenda + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
