/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.bean;

import br.JavaApplicationJsys;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "transferenciasProdutos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransferenciasProdutos.findAll", query = "SELECT t FROM TransferenciasProdutos t"),
    @NamedQuery(name = "TransferenciasProdutos.findByIdTransf", query = "SELECT t FROM TransferenciasProdutos t WHERE t.idTransf = :idTransf"),
    @NamedQuery(name = "TransferenciasProdutos.findByData", query = "SELECT t FROM TransferenciasProdutos t WHERE t.data = :data"),
    
    @NamedQuery(name = "TransferenciasProdutos.findByIdCliente", query = "SELECT t FROM TransferenciasProdutos t WHERE t.idCliente.idCliente = :idCliente AND t.confirmado = :confirmado AND t.data BETWEEN :dataInicial AND :datafinal"),
    
    @NamedQuery(name = "TransferenciasProdutos.findByIdloja", query = "SELECT t FROM TransferenciasProdutos t WHERE t.idloja = :idloja"),
    @NamedQuery(name = "TransferenciasProdutos.findByUsuario", query = "SELECT t FROM TransferenciasProdutos t WHERE t.usuario = :usuario"),
    
    @NamedQuery(name = "TransferenciasProdutos.findByConfirmado", query = "SELECT t FROM TransferenciasProdutos t WHERE t.confirmado = :confirmado AND t.data BETWEEN :dataInicial AND :datafinal"),
    
    @NamedQuery(name = "TransferenciasProdutos.findByCancelado", query = "SELECT t FROM TransferenciasProdutos t WHERE t.cancelado = :cancelado"),
    @NamedQuery(name = "TransferenciasProdutos.findByTotalCompra", query = "SELECT t FROM TransferenciasProdutos t WHERE t.totalCompra = :totalCompra"),
    @NamedQuery(name = "TransferenciasProdutos.findByTotalVenda", query = "SELECT t FROM TransferenciasProdutos t WHERE t.totalVenda = :totalVenda")})
public class TransferenciasProdutos implements Serializable {
    @Transient
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTransf")
    private Integer idTransf;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    //@Basic(optional = false)
    //@Column(name = "idCliente")
    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private JsysClientes idCliente;
    @Basic(optional = false)
    @Column(name = "idFuncionario")
    private int idFuncionario;
    @Basic(optional = false)
    @Column(name = "idloja")
    private String idloja;
    @Basic(optional = false)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "confirmado")
    private boolean confirmado;
    @Basic(optional = false)
    @Column(name = "cancelado")
    private boolean cancelado;
    // @Max(value=?) @Min(value=0)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalCompra")
    private BigDecimal totalCompra;
    @Column(name = "totalVenda")
    private BigDecimal totalVenda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transferenciasProdutos")
    private Collection<TransferenciasProdutosItens> transferenciasProdutosItensCollection;

    public TransferenciasProdutos() {
    }

    public TransferenciasProdutos(Integer idTransf) {
        this.idTransf = idTransf;
    }

    public TransferenciasProdutos(Integer idTransf, Date data, JsysClientes idCliente, String idloja, String usuario, boolean confirmado, boolean cancelado) {
        this.idTransf = idTransf;
        this.data = data;
        this.idCliente = idCliente;
        this.idloja = idloja;
        this.usuario = usuario;
        this.confirmado = confirmado;
        this.cancelado = cancelado;
    }

    public Integer getIdTransf() {
        return idTransf;
    }

    public void setIdTransf(Integer idTransf) {
        Integer oldIdTransf = this.idTransf;
        this.idTransf = idTransf;
        changeSupport.firePropertyChange("idTransf", oldIdTransf, idTransf);
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        Date oldData = this.data;
        this.data = data;
        changeSupport.firePropertyChange("data", oldData, data);
    }

    public JsysClientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(JsysClientes idCliente) {
        JsysClientes oldIdCliente = this.idCliente;
        this.idCliente = idCliente;
        changeSupport.firePropertyChange("idCliente", oldIdCliente, idCliente);
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        int oldIdFuncionario = this.idFuncionario;
        this.idFuncionario = idFuncionario;
        changeSupport.firePropertyChange("idFuncionario", oldIdFuncionario, idFuncionario);
    }

    public String getIdloja() {
        return idloja;
    }

    public void setIdloja(String idloja) {
        String oldIdloja = this.idloja;
        this.idloja = idloja;
        changeSupport.firePropertyChange("idloja", oldIdloja, idloja);
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

    public boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        boolean oldCancelado = this.cancelado;
        this.cancelado = cancelado;
        changeSupport.firePropertyChange("cancelado", oldCancelado, cancelado);
    }

    public BigDecimal getTotalCompra() {
        return totalCompra.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_COMPRA, RoundingMode.HALF_UP);
    }

    public void setTotalCompra(BigDecimal totalCompra) {
        BigDecimal oldTotalCompra = this.totalCompra;
        this.totalCompra = totalCompra;
        changeSupport.firePropertyChange("totalCompra", oldTotalCompra, totalCompra);
    }

    public BigDecimal getTotalVenda() {
        return totalVenda.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_VENDA, RoundingMode.HALF_UP);
    }

    public void setTotalVenda(BigDecimal totalVenda) {
        BigDecimal oldTotalVenda = this.totalVenda;
        this.totalVenda = totalVenda;
        changeSupport.firePropertyChange("totalVenda", oldTotalVenda, totalVenda);
    }

    @XmlTransient
    public Collection<TransferenciasProdutosItens> getTransferenciasProdutosItensCollection() {
        return transferenciasProdutosItensCollection;
    }

    public void setTransferenciasProdutosItensCollection(Collection<TransferenciasProdutosItens> transferenciasProdutosItensCollection) {
        this.transferenciasProdutosItensCollection = transferenciasProdutosItensCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransf != null ? idTransf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransferenciasProdutos)) {
            return false;
        }
        TransferenciasProdutos other = (TransferenciasProdutos) object;
        return (this.idTransf != null || other.idTransf == null) && (this.idTransf == null || this.idTransf.equals(other.idTransf));
    }

    @Override
    public String toString() {
        return "br.sql.bean.TransferenciasProdutos[ idTransf=" + idTransf + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
