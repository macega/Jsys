package br.sql.bean;

import br.sql.util.ManagerString;
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
@Table(name = "jsysProdutosTFabricantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysProdutosTFabricantes.findAll", query = "SELECT j FROM JsysProdutosTFabricantes j"),
    @NamedQuery(name = "JsysProdutosTFabricantes.findByIdFabricante", query = "SELECT j FROM JsysProdutosTFabricantes j WHERE j.idFabricante = :idFabricante"),
    @NamedQuery(name = "JsysProdutosTFabricantes.findByNomefabricante", query = "SELECT j FROM JsysProdutosTFabricantes j WHERE j.nomefabricante = :nomefabricante")})
public class JsysProdutosTFabricantes implements Serializable {
    @Transient
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idFabricante")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFabricante;
    
    @Basic(optional = false)
    @Column(name = "nomefabricante")
    private String nomefabricante;

    public JsysProdutosTFabricantes() {
    }

    public JsysProdutosTFabricantes(Integer idFabricante) {
        this.idFabricante = idFabricante;
    }

    public JsysProdutosTFabricantes(Integer idFabricante, String nomefabricante) {
        this.idFabricante = idFabricante;
        setNomefabricante(nomefabricante);
    }

    public Integer getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(Integer idFabricante) {
        Integer oldIdFabricante = this.idFabricante;
        this.idFabricante = idFabricante;
        changeSupport.firePropertyChange("idFabricante", oldIdFabricante, idFabricante);
    }

    public String getNomefabricante() {
        return nomefabricante;
    }

    public void setNomefabricante(String nomefabricante) {
        nomefabricante = ManagerString.Left(nomefabricante, 50);
        String oldNomefabricante = this.nomefabricante;
        this.nomefabricante = nomefabricante;
        changeSupport.firePropertyChange("nomefabricante", oldNomefabricante, nomefabricante);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFabricante != null ? idFabricante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysProdutosTFabricantes)) {
            return false;
        }
        JsysProdutosTFabricantes other = (JsysProdutosTFabricantes) object;
        return (this.idFabricante != null || other.idFabricante == null) && (this.idFabricante == null || this.idFabricante.equals(other.idFabricante));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysProdutosTFabricantes[ idFabricante=" + idFabricante + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
