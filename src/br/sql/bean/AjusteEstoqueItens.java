/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "AjusteEstoqueItens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AjusteEstoqueItens.findAll", query = "SELECT a FROM AjusteEstoqueItens a"),
    @NamedQuery(name = "AjusteEstoqueItens.findByIdAjusteEstoque", query = "SELECT a FROM AjusteEstoqueItens a WHERE a.ajusteEstoqueItensPK.idAjusteEstoque = :idAjusteEstoque"),
    @NamedQuery(name = "AjusteEstoqueItens.findByIdProduto", query = "SELECT a FROM AjusteEstoqueItens a WHERE a.ajusteEstoqueItensPK.idProduto = :idProduto"),
    @NamedQuery(name = "AjusteEstoqueItens.findByQuantidade", query = "SELECT a FROM AjusteEstoqueItens a WHERE a.quantidade = :quantidade"),
    @NamedQuery(name = "AjusteEstoqueItens.findByQuantidadeAnterior", query = "SELECT a FROM AjusteEstoqueItens a WHERE a.quantidadeAnterior = :quantidadeAnterior"),
    @NamedQuery(name = "AjusteEstoqueItens.findByTotalCompra", query = "SELECT a FROM AjusteEstoqueItens a WHERE a.totalCompra = :totalCompra"),
    @NamedQuery(name = "AjusteEstoqueItens.findByTotalVenda", query = "SELECT a FROM AjusteEstoqueItens a WHERE a.totalVenda = :totalVenda")})
public class AjusteEstoqueItens implements Serializable {

    @Transient
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AjusteEstoqueItensPK ajusteEstoqueItensPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "quantidade")
    private BigDecimal quantidade;
    @Basic(optional = false)
    @Column(name = "quantidadeAnterior")
    private BigDecimal quantidadeAnterior;
    @Basic(optional = false)
    @Column(name = "totalCompra")
    private BigDecimal totalCompra;
    @Basic(optional = false)
    @Column(name = "totalVenda")
    private BigDecimal totalVenda;
    @JoinColumn(name = "idProduto", referencedColumnName = "idProduto", insertable = false, updatable = false)
    @ManyToOne()
    private JsysProdutosT jsysProdutosT;
    @JoinColumn(name = "idAjusteEstoque", referencedColumnName = "idAjusteEstoque", insertable = false, updatable = false)
    @ManyToOne()
    private AjusteEstoque ajusteEstoque;

    public AjusteEstoqueItens() {
    }

    public AjusteEstoqueItens(AjusteEstoqueItensPK ajusteEstoqueItensPK) {
        this.ajusteEstoqueItensPK = ajusteEstoqueItensPK;
    }

    public AjusteEstoqueItens(AjusteEstoqueItensPK ajusteEstoqueItensPK, BigDecimal quantidade, BigDecimal quantidadeAnterior, BigDecimal totalCompra, BigDecimal totalVenda) {
        this.ajusteEstoqueItensPK = ajusteEstoqueItensPK;
        this.quantidade = quantidade;
        this.quantidadeAnterior = quantidadeAnterior;
        this.totalCompra = totalCompra;
        this.totalVenda = totalVenda;
    }

    public AjusteEstoqueItens(int idAjusteEstoque, int idProduto) {
        this.ajusteEstoqueItensPK = new AjusteEstoqueItensPK(idAjusteEstoque, idProduto);
    }

    public AjusteEstoqueItensPK getAjusteEstoqueItensPK() {
        return ajusteEstoqueItensPK;
    }

    public void setAjusteEstoqueItensPK(AjusteEstoqueItensPK ajusteEstoqueItensPK) {
        this.ajusteEstoqueItensPK = ajusteEstoqueItensPK;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        BigDecimal oldQuantidade = this.quantidade;
        this.quantidade = quantidade;
        changeSupport.firePropertyChange("quantidade", oldQuantidade, quantidade);
    }

    public BigDecimal getQuantidadeAnterior() {
        return quantidadeAnterior;
    }

    public void setQuantidadeAnterior(BigDecimal quantidadeAnterior) {
        BigDecimal oldQuantidadeAnterior = this.quantidadeAnterior;
        this.quantidadeAnterior = quantidadeAnterior;
        changeSupport.firePropertyChange("quantidadeAnterior", oldQuantidadeAnterior, quantidadeAnterior);
    }

    public BigDecimal getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(BigDecimal totalCompra) {
        BigDecimal oldTotalCompra = this.totalCompra;
        this.totalCompra = totalCompra;
        changeSupport.firePropertyChange("totalCompra", oldTotalCompra, totalCompra);
    }

    public BigDecimal getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(BigDecimal totalVenda) {
        BigDecimal oldTotalVenda = this.totalVenda;
        this.totalVenda = totalVenda;
        changeSupport.firePropertyChange("totalVenda", oldTotalVenda, totalVenda);
    }

    public JsysProdutosT getJsysProdutosT() {
        return jsysProdutosT;
    }

    public void setJsysProdutosT(JsysProdutosT jsysProdutosT) {
        JsysProdutosT oldJsysProdutosT = this.jsysProdutosT;
        this.jsysProdutosT = jsysProdutosT;
        changeSupport.firePropertyChange("jsysProdutosT", oldJsysProdutosT, jsysProdutosT);
    }

    public AjusteEstoque getAjusteEstoque() {
        return ajusteEstoque;
    }

    public void setAjusteEstoque(AjusteEstoque ajusteEstoque) {
        AjusteEstoque oldAjusteEstoque = this.ajusteEstoque;
        this.ajusteEstoque = ajusteEstoque;
        changeSupport.firePropertyChange("ajusteEstoque", oldAjusteEstoque, ajusteEstoque);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ajusteEstoqueItensPK != null ? ajusteEstoqueItensPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AjusteEstoqueItens)) {
            return false;
        }
        AjusteEstoqueItens other = (AjusteEstoqueItens) object;
        return (this.ajusteEstoqueItensPK != null || other.ajusteEstoqueItensPK == null) && (this.ajusteEstoqueItensPK == null || this.ajusteEstoqueItensPK.equals(other.ajusteEstoqueItensPK));
    }

    @Override
    public String toString() {
        return "br.sql.bean.AjusteEstoqueItens[ ajusteEstoqueItensPK=" + ajusteEstoqueItensPK + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
