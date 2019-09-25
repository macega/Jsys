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
import javax.persistence.Lob;
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
@Table(name = "helpDesk")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HelpDesk.findByTipoAndLojaAndFechado", query = "SELECT h FROM HelpDesk h WHERE (h.tipo = :tipo OR 'Todos' = :tipo) AND (h.idLoja = :idLoja or 'Todas' = :idLoja) AND (h.fechado = :fechado)"),
    @NamedQuery(name = "HelpDesk.findAll", query = "SELECT h FROM HelpDesk h"),
    @NamedQuery(name = "HelpDesk.findById", query = "SELECT h FROM HelpDesk h WHERE h.id = :id"),
    @NamedQuery(name = "HelpDesk.findByData", query = "SELECT h FROM HelpDesk h WHERE h.data = :data"),
    @NamedQuery(name = "HelpDesk.findByIdLoja", query = "SELECT h FROM HelpDesk h WHERE h.idLoja = :idLoja"),
    @NamedQuery(name = "HelpDesk.findByUsuario", query = "SELECT h FROM HelpDesk h WHERE h.usuario = :usuario"),
    @NamedQuery(name = "HelpDesk.findByTipo", query = "SELECT h FROM HelpDesk h WHERE h.tipo = :tipo"),
    @NamedQuery(name = "HelpDesk.findByFechado", query = "SELECT h FROM HelpDesk h WHERE h.fechado = :fechado"),
    @NamedQuery(name = "HelpDesk.findByAtendente", query = "SELECT h FROM HelpDesk h WHERE h.atendente = :atendente"),
    @NamedQuery(name = "HelpDesk.findByDataFechamento", query = "SELECT h FROM HelpDesk h WHERE h.dataFechamento = :dataFechamento")})
public class HelpDesk implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @Column(name = "idLoja")
    private String idLoja;
    @Basic(optional = false)
    @Column(name = "usuario")
    private String usuario;
    @Lob
    @Column(name = "obs")
    private String obs;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "fechado")
    private boolean fechado;
    @Column(name = "atendente")
    private String atendente;
    @Lob
    @Column(name = "obsFechamento")
    private String obsFechamento;
    @Column(name = "dataFechamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFechamento;

    public HelpDesk() {
    }

    public HelpDesk(Long id) {
        this.id = id;
    }

    public HelpDesk(Long id, Date data, String idLoja, String usuario, String tipo, boolean fechado) {
        this.id = id;
        this.data = data;
        this.idLoja = idLoja;
        this.usuario = usuario;
        this.tipo = tipo;
        this.fechado = fechado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        Long oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        Date oldData = this.data;
        this.data = data;
        changeSupport.firePropertyChange("data", oldData, data);
    }

    public String getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(String idLoja) {
        String oldIdLoja = this.idLoja;
        this.idLoja = idLoja;
        changeSupport.firePropertyChange("idLoja", oldIdLoja, idLoja);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        String oldUsuario = this.usuario;
        this.usuario = usuario;
        changeSupport.firePropertyChange("usuario", oldUsuario, usuario);
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        String oldObs = this.obs;
        this.obs = obs;
        changeSupport.firePropertyChange("obs", oldObs, obs);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        String oldTipo = this.tipo;
        this.tipo = tipo;
        changeSupport.firePropertyChange("tipo", oldTipo, tipo);
    }

    public boolean getFechado() {
        return fechado;
    }

    public void setFechado(boolean fechado) {
        boolean oldFechado = this.fechado;
        this.fechado = fechado;
        changeSupport.firePropertyChange("fechado", oldFechado, fechado);
    }

    public String getAtendente() {
        return atendente;
    }

    public void setAtendente(String atendente) {
        String oldAtendente = this.atendente;
        this.atendente = atendente;
        changeSupport.firePropertyChange("atendente", oldAtendente, atendente);
    }

    public String getObsFechamento() {
        return obsFechamento;
    }

    public void setObsFechamento(String obsFechamento) {
        String oldObsFechamento = this.obsFechamento;
        this.obsFechamento = obsFechamento;
        changeSupport.firePropertyChange("obsFechamento", oldObsFechamento, obsFechamento);
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        Date oldDataFechamento = this.dataFechamento;
        this.dataFechamento = dataFechamento;
        changeSupport.firePropertyChange("dataFechamento", oldDataFechamento, dataFechamento);
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
        if (!(object instanceof HelpDesk)) {
            return false;
        }
        HelpDesk other = (HelpDesk) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "br.sql.bean.HelpDesk[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
