/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.bean;

import br.sql.acesso.ConnectionFactory;
import br.sql.util.Retorna;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityTransaction;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "transferenciasProdutosItens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransferenciasProdutosItens.findAll", query = "SELECT t FROM TransferenciasProdutosItens t")
    ,
    @NamedQuery(name = "TransferenciasProdutosItens.findByIdTransf", query = "SELECT t FROM TransferenciasProdutosItens t WHERE t.transferenciasProdutosItensPK.idTransf = :idTransf")
    ,
    @NamedQuery(name = "TransferenciasProdutosItens.findByIdProduto", query = "SELECT t FROM TransferenciasProdutosItens t WHERE t.transferenciasProdutosItensPK.idProduto = :idProduto")
    ,
    @NamedQuery(name = "TransferenciasProdutosItens.findByQuantidade", query = "SELECT t FROM TransferenciasProdutosItens t WHERE t.quantidade = :quantidade")
    ,
    @NamedQuery(name = "TransferenciasProdutosItens.findByPrecoCompra", query = "SELECT t FROM TransferenciasProdutosItens t WHERE t.precoCompra = :precoCompra")
    ,
    @NamedQuery(name = "TransferenciasProdutosItens.findByPrecoVenda", query = "SELECT t FROM TransferenciasProdutosItens t WHERE t.precoVenda = :precoVenda")})
public class TransferenciasProdutosItens implements Serializable {

    @JoinColumn(name = "idProduto", referencedColumnName = "idProduto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private JsysProdutosT jsysProdutosT;
    @Transient
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TransferenciasProdutosItensPK transferenciasProdutosItensPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "quantidade")
    private BigDecimal quantidade;
    @Basic(optional = false)
    @Column(name = "precoCompra")
    private BigDecimal precoCompra;
    @Basic(optional = false)
    @Column(name = "precoVenda")
    private BigDecimal precoVenda;
    @JoinColumn(name = "idTransf", referencedColumnName = "idTransf", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TransferenciasProdutos transferenciasProdutos;

    public TransferenciasProdutosItens() {
    }

    public TransferenciasProdutosItens(TransferenciasProdutosItensPK transferenciasProdutosItensPK) {
        this.transferenciasProdutosItensPK = transferenciasProdutosItensPK;
    }

    public TransferenciasProdutosItens(TransferenciasProdutosItensPK transferenciasProdutosItensPK, BigDecimal quantidade, BigDecimal precoCompra, BigDecimal precoVenda) {
        this.transferenciasProdutosItensPK = transferenciasProdutosItensPK;
        this.quantidade = quantidade;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
    }

    public TransferenciasProdutosItens(int idTransf, int idProduto) {
        this.transferenciasProdutosItensPK = new TransferenciasProdutosItensPK(idTransf, idProduto);
    }

    public TransferenciasProdutosItensPK getTransferenciasProdutosItensPK() {
        return transferenciasProdutosItensPK;
    }

    public void setTransferenciasProdutosItensPK(TransferenciasProdutosItensPK transferenciasProdutosItensPK) {
        this.transferenciasProdutosItensPK = transferenciasProdutosItensPK;
    }

    public BigDecimal getQuantidade() {
        return quantidade.setScale(br.JavaApplicationJsys.CASA_DECIMAL_QUANTIDADE, RoundingMode.HALF_UP);
    }

    public void setQuantidade(BigDecimal quantidade) {
        BigDecimal oldQuantidade = this.quantidade;
        this.quantidade = quantidade;
        changeSupport.firePropertyChange("quantidade", oldQuantidade, quantidade);
    }

    public BigDecimal getPrecoCompra() {
        return precoCompra.setScale(br.JavaApplicationJsys.CASA_DECIMAL_VALOR_COMPRA, RoundingMode.HALF_UP);
    }

    public void setPrecoCompra(BigDecimal precoCompra) {
        BigDecimal oldPrecoCompra = this.precoCompra;
        this.precoCompra = precoCompra;
        changeSupport.firePropertyChange("precoCompra", oldPrecoCompra, precoCompra);
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda.setScale(br.JavaApplicationJsys.CASA_DECIMAL_VALOR_VENDA, RoundingMode.HALF_UP);
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        BigDecimal oldPrecoVenda = this.precoVenda;
        this.precoVenda = precoVenda;
        changeSupport.firePropertyChange("precoVenda", oldPrecoVenda, precoVenda);
    }

    public TransferenciasProdutos getTransferenciasProdutos() {
        return transferenciasProdutos;
    }

    public void setTransferenciasProdutos(TransferenciasProdutos transferenciasProdutos) {
        TransferenciasProdutos oldTransferenciasProdutos = this.transferenciasProdutos;
        this.transferenciasProdutos = transferenciasProdutos;
        changeSupport.firePropertyChange("transferenciasProdutos", oldTransferenciasProdutos, transferenciasProdutos);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transferenciasProdutosItensPK != null ? transferenciasProdutosItensPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransferenciasProdutosItens)) {
            return false;
        }
        TransferenciasProdutosItens other = (TransferenciasProdutosItens) object;
        return (this.transferenciasProdutosItensPK != null || other.transferenciasProdutosItensPK == null) && (this.transferenciasProdutosItensPK == null || this.transferenciasProdutosItensPK.equals(other.transferenciasProdutosItensPK));
    }

    @Override
    public String toString() {
        return "br.sql.bean.TransferenciasProdutosItens[ transferenciasProdutosItensPK=" + transferenciasProdutosItensPK + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public JsysProdutosT getJsysProdutosT() {
        return jsysProdutosT;
    }

    public void setJsysProdutosT(JsysProdutosT jsysProdutosT) {
        this.jsysProdutosT = jsysProdutosT;
    }

}
