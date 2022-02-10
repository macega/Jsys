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

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysProdutosTFamilias")
@NamedQueries({
    @NamedQuery(name = "JsysProdutosTFamilias.findAll", query = "SELECT j FROM JsysProdutosTFamilias j")
    ,@NamedQuery(name = "JsysProdutosTFamilias.findByIdFamilia", query = "SELECT j FROM JsysProdutosTFamilias j WHERE j.idFamilia = :idFamilia")
    ,@NamedQuery(name = "JsysProdutosTFamilias.findByNomeFamilia", query = "SELECT j FROM JsysProdutosTFamilias j WHERE j.nomeFamilia = :nomeFamilia")})
public class JsysProdutosTFamilias implements Serializable {

    @Transient
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idFamilia")
    private Integer idFamilia;

    @Basic(optional = false)
    @Column(name = "nomeFamilia")
    private String nomeFamilia;

    public JsysProdutosTFamilias() {
    }

    public JsysProdutosTFamilias(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public JsysProdutosTFamilias(Integer idFamilia, String nomeFamilia) {
        this.idFamilia = idFamilia;
        setNomeFamilia(nomeFamilia);
    }

    public Integer getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Integer idFamilia) {
        Integer oldIdFamilia = this.idFamilia;
        this.idFamilia = idFamilia;
        changeSupport.firePropertyChange("idFamilia", oldIdFamilia, idFamilia);
    }

    public String getNomeFamilia() {
        return nomeFamilia;
    }

    public void setNomeFamilia(String nomeFamilia) {
        nomeFamilia = ManagerString.Left(nomeFamilia, 25);
        String oldNomeFamilia = this.nomeFamilia;
        this.nomeFamilia = nomeFamilia;
        changeSupport.firePropertyChange("nomeFamilia", oldNomeFamilia, nomeFamilia);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFamilia != null ? idFamilia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysProdutosTFamilias)) {
            return false;
        }
        JsysProdutosTFamilias other = (JsysProdutosTFamilias) object;
        return (this.idFamilia != null || other.idFamilia == null) && (this.idFamilia == null || this.idFamilia.equals(other.idFamilia));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysProdutosTFamilias[ idFamilia=" + idFamilia + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
