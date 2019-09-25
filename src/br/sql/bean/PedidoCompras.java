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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "PedidoCompras")
@NamedQueries({
    @NamedQuery(name = "PedidoCompras.findAll", query = "SELECT p FROM PedidoCompras p"),
    @NamedQuery(name = "PedidoCompras.findByPeriodo", query = "SELECT p FROM PedidoCompras p WHERE p.dataPedido BETWEEN :dataInicial AND :dataFinal ORDER BY p.dataPedido"),
    @NamedQuery(name = "PedidoCompras.findByIdPedido", query = "SELECT p FROM PedidoCompras p WHERE p.idPedido = :idPedido"),
    @NamedQuery(name = "PedidoCompras.findByDataPedido", query = "SELECT p FROM PedidoCompras p WHERE p.dataPedido = :dataPedido"),
    @NamedQuery(name = "PedidoCompras.findByUsuario", query = "SELECT p FROM PedidoCompras p WHERE p.usuario = :usuario"),
    @NamedQuery(name = "PedidoCompras.findByConfirmado", query = "SELECT p FROM PedidoCompras p WHERE p.confirmado = :confirmado"),
    @NamedQuery(name = "PedidoCompras.findByImpresso", query = "SELECT p FROM PedidoCompras p WHERE p.impresso = :impresso")})
public class PedidoCompras implements Serializable {

    @Transient
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPedido")
    private Integer idPedido;
    @Basic(optional = false)
    @Column(name = "dataPedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPedido;
    @Basic(optional = false)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "confirmado")
    private boolean confirmado;
    @Basic(optional = false)
    @Column(name = "impresso")
    private boolean impresso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPedido")
    private List<PedidoComprasItens> pedidoComprasItensList;

    public PedidoCompras() {
    }

    public PedidoCompras(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public PedidoCompras(Integer idPedido, Date dataPedido, String usuario, boolean confirmado, boolean impresso) {
        this.idPedido = idPedido;
        this.dataPedido = dataPedido;
        this.usuario = usuario;
        this.confirmado = confirmado;
        this.impresso = impresso;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        Integer oldIdPedido = this.idPedido;
        this.idPedido = idPedido;
        changeSupport.firePropertyChange("idPedido", oldIdPedido, idPedido);
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        Date oldDataPedido = this.dataPedido;
        this.dataPedido = dataPedido;
        changeSupport.firePropertyChange("dataPedido", oldDataPedido, dataPedido);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        String oldUsuario = this.usuario;
        this.usuario = usuario;
        changeSupport.firePropertyChange("usuario", oldUsuario, usuario);
    }

    public boolean getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        boolean oldConfirmado = this.confirmado;
        this.confirmado = confirmado;
        changeSupport.firePropertyChange("confirmado", oldConfirmado, confirmado);
    }

    public boolean getImpresso() {
        return impresso;
    }

    public void setImpresso(boolean impresso) {
        boolean oldImpresso = this.impresso;
        this.impresso = impresso;
        changeSupport.firePropertyChange("impresso", oldImpresso, impresso);
    }

    public List<PedidoComprasItens> getPedidoComprasItensList() {
        return pedidoComprasItensList;
    }

    public void setPedidoComprasItensList(List<PedidoComprasItens> pedidoComprasItensList) {
        this.pedidoComprasItensList = pedidoComprasItensList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoCompras)) {
            return false;
        }
        PedidoCompras other = (PedidoCompras) object;
        return (this.idPedido != null || other.idPedido == null) && (this.idPedido == null || this.idPedido.equals(other.idPedido));
    }

    @Override
    public String toString() {
        return "br.sql.bean.PedidoCompras[ idPedido=" + idPedido + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
