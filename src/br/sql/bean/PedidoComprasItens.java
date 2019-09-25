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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "PedidoComprasItens")
@NamedQueries({
    @NamedQuery(name = "PedidoComprasItens.findAll", query = "SELECT p FROM PedidoComprasItens p"),
    @NamedQuery(name = "PedidoComprasItens.findByIdPedidoComprasItens", query = "SELECT p FROM PedidoComprasItens p WHERE p.idPedidoComprasItens = :idPedidoComprasItens"),
    @NamedQuery(name = "PedidoComprasItens.findByIdProduto", query = "SELECT p FROM PedidoComprasItens p WHERE p.idProduto = :idProduto"),
    @NamedQuery(name = "PedidoComprasItens.findByDescricao", query = "SELECT p FROM PedidoComprasItens p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "PedidoComprasItens.findByQuantidade", query = "SELECT p FROM PedidoComprasItens p WHERE p.quantidade = :quantidade")})
public class PedidoComprasItens implements Serializable {

    @Transient
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPedidoComprasItens")
    private Integer idPedidoComprasItens;
    @Basic(optional = false)
    @Column(name = "idProduto")
    private int idProduto;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "Quantidade")
    private BigDecimal quantidade;
    @JoinColumn(name = "idPedido", referencedColumnName = "idPedido")
    @ManyToOne(optional = false)
    private PedidoCompras idPedido;

    public PedidoComprasItens() {
    }

    public PedidoComprasItens(Integer idPedidoComprasItens) {
        this.idPedidoComprasItens = idPedidoComprasItens;
    }

    public PedidoComprasItens(Integer idPedidoComprasItens, int idProduto, String descricao, BigDecimal quantidade) {
        this.idPedidoComprasItens = idPedidoComprasItens;
        this.idProduto = idProduto;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public Integer getIdPedidoComprasItens() {
        return idPedidoComprasItens;
    }

    public void setIdPedidoComprasItens(Integer idPedidoComprasItens) {
        Integer oldIdPedidoComprasItens = this.idPedidoComprasItens;
        this.idPedidoComprasItens = idPedidoComprasItens;
        changeSupport.firePropertyChange("idPedidoComprasItens", oldIdPedidoComprasItens, idPedidoComprasItens);
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        int oldIdProduto = this.idProduto;
        this.idProduto = idProduto;
        changeSupport.firePropertyChange("idProduto", oldIdProduto, idProduto);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        String oldDescricao = this.descricao;
        this.descricao = descricao;
        changeSupport.firePropertyChange("descricao", oldDescricao, descricao);
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        BigDecimal oldQuantidade = this.quantidade;
        this.quantidade = quantidade;
        changeSupport.firePropertyChange("quantidade", oldQuantidade, quantidade);
    }

    public PedidoCompras getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(PedidoCompras idPedido) {
        PedidoCompras oldIdPedido = this.idPedido;
        this.idPedido = idPedido;
        changeSupport.firePropertyChange("idPedido", oldIdPedido, idPedido);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedidoComprasItens != null ? idPedidoComprasItens.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoComprasItens)) {
            return false;
        }
        PedidoComprasItens other = (PedidoComprasItens) object;
        return (this.idPedidoComprasItens != null || other.idPedidoComprasItens == null) && (this.idPedidoComprasItens == null || this.idPedidoComprasItens.equals(other.idPedidoComprasItens));
    }

    @Override
    public String toString() {
        return "br.sql.bean.PedidoComprasItens[ idPedidoComprasItens=" + idPedidoComprasItens + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
