package br.sql.bean;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysClientesIds")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysClientesIds.findAll", query = "SELECT j FROM JsysClientesIds j"),
    @NamedQuery(name = "JsysClientesIds.findById", query = "SELECT j FROM JsysClientesIds j WHERE j.jsysClientesIdsPK.id = :id"),
    @NamedQuery(name = "JsysClientesIds.findByIdCliente", query = "SELECT j FROM JsysClientesIds j WHERE j.jsysClientesIdsPK.idCliente = :idCliente")})
public class JsysClientesIds implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JsysClientesIdsPK jsysClientesIdsPK;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private JsysClientes jsysClientes;

    public JsysClientesIds() {
    }

    public JsysClientesIds(JsysClientesIdsPK jsysClientesIdsPK) {
        this.jsysClientesIdsPK = jsysClientesIdsPK;
    }

    public JsysClientesIds(String id, int idCliente) {
        this.jsysClientesIdsPK = new JsysClientesIdsPK(id, idCliente);
    }

    public JsysClientesIdsPK getJsysClientesIdsPK() {
        return jsysClientesIdsPK;
    }

    public void setJsysClientesIdsPK(JsysClientesIdsPK jsysClientesIdsPK) {
        this.jsysClientesIdsPK = jsysClientesIdsPK;
    }

    public JsysClientes getJsysClientes() {
        return jsysClientes;
    }

    public void setJsysClientes(JsysClientes jsysClientes) {
        this.jsysClientes = jsysClientes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jsysClientesIdsPK != null ? jsysClientesIdsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysClientesIds)) {
            return false;
        }
        JsysClientesIds other = (JsysClientesIds) object;
        if ((this.jsysClientesIdsPK == null && other.jsysClientesIdsPK != null) || (this.jsysClientesIdsPK != null && !this.jsysClientesIdsPK.equals(other.jsysClientesIdsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysClientesIds[ jsysClientesIdsPK=" + jsysClientesIdsPK + " ]";
    }
    
}
