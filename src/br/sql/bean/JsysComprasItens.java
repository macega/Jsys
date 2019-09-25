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

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysComprasItens")
@NamedQueries({
    @NamedQuery(name = "JsysComprasItens.findAll", query = "SELECT j FROM JsysComprasItens j"),
    @NamedQuery(name = "JsysComprasItens.findByIdCompra", query = "SELECT j FROM JsysComprasItens j WHERE j.jsysComprasItensPK.idCompra = :idCompra"),
    @NamedQuery(name = "JsysComprasItens.findByIdProduto", query = "SELECT j FROM JsysComprasItens j WHERE j.jsysComprasItensPK.idProduto = :idProduto"),
    @NamedQuery(name = "JsysComprasItens.findByUnidadeCompra", query = "SELECT j FROM JsysComprasItens j WHERE j.unidadeCompra = :unidadeCompra"),
    @NamedQuery(name = "JsysComprasItens.findByUnidadeVenda", query = "SELECT j FROM JsysComprasItens j WHERE j.unidadeVenda = :unidadeVenda"),
    @NamedQuery(name = "JsysComprasItens.findByQuantidade", query = "SELECT j FROM JsysComprasItens j WHERE j.quantidade = :quantidade"),
    @NamedQuery(name = "JsysComprasItens.findByPrecoCompra", query = "SELECT j FROM JsysComprasItens j WHERE j.precoCompra = :precoCompra"),
    @NamedQuery(name = "JsysComprasItens.findByPrecoVenda", query = "SELECT j FROM JsysComprasItens j WHERE j.precoVenda = :precoVenda"),
    @NamedQuery(name = "JsysComprasItens.findByTotalCompra", query = "SELECT j FROM JsysComprasItens j WHERE j.totalCompra = :totalCompra"),
    @NamedQuery(name = "JsysComprasItens.findByPrecoFrete", query = "SELECT j FROM JsysComprasItens j WHERE j.precoFrete = :precoFrete"),
    @NamedQuery(name = "JsysComprasItens.findByNcm", query = "SELECT j FROM JsysComprasItens j WHERE j.ncm = :ncm"),
    @NamedQuery(name = "JsysComprasItens.findByCfop", query = "SELECT j FROM JsysComprasItens j WHERE j.cfop = :cfop")})
public class JsysComprasItens implements Serializable {
    @Transient
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JsysComprasItensPK jsysComprasItensPK;
    @Basic(optional = false)
    @Column(name = "unidadeCompra")
    private String unidadeCompra;
    @Basic(optional = false)
    @Column(name = "unidadeVenda")
    private String unidadeVenda;
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
    @Basic(optional = false)
    @Column(name = "totalCompra")
    private BigDecimal totalCompra;
    @Basic(optional = false)
    @Column(name = "precoFrete")
    private BigDecimal precoFrete;
    @Basic(optional = false)
    @Column(name = "NCM")
    private String ncm;
    @Basic(optional = false)
    @Column(name = "CFOP")
    private String cfop;
    @JoinColumn(name = "idCompra", referencedColumnName = "idCompra", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private JsysCompras jsysCompras;
    @JoinColumn(name = "idProduto", referencedColumnName = "idProduto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private JsysProdutosT jsysProdutosT;

    public JsysComprasItens() {
    }

    public JsysComprasItens(JsysComprasItensPK jsysComprasItensPK) {
        this.jsysComprasItensPK = jsysComprasItensPK;
    }

    public JsysComprasItens(JsysComprasItensPK jsysComprasItensPK, String unidadeCompra, String unidadeVenda, BigDecimal quantidade, BigDecimal precoCompra, BigDecimal precoVenda, BigDecimal totalCompra, BigDecimal precoFrete, String ncm, String cfop) {
        this.jsysComprasItensPK = jsysComprasItensPK;
        this.unidadeCompra = unidadeCompra;
        this.unidadeVenda = unidadeVenda;
        this.quantidade = quantidade;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.totalCompra = totalCompra;
        this.precoFrete = precoFrete;
        this.ncm = ncm;
        this.cfop = cfop;
    }

    public JsysComprasItens(int idCompra, int idProduto) {
        this.jsysComprasItensPK = new JsysComprasItensPK(idCompra, idProduto);
    }

    public JsysComprasItensPK getJsysComprasItensPK() {
        return jsysComprasItensPK;
    }

    public void setJsysComprasItensPK(JsysComprasItensPK jsysComprasItensPK) {
        this.jsysComprasItensPK = jsysComprasItensPK;
    }

    public String getUnidadeCompra() {
        return unidadeCompra;
    }

    public void setUnidadeCompra(String unidadeCompra) {
        String oldUnidadeCompra = this.unidadeCompra;
        this.unidadeCompra = unidadeCompra;
        changeSupport.firePropertyChange("unidadeCompra", oldUnidadeCompra, unidadeCompra);
    }

    public String getUnidadeVenda() {
        return unidadeVenda;
    }

    public void setUnidadeVenda(String unidadeVenda) {
        String oldUnidadeVenda = this.unidadeVenda;
        this.unidadeVenda = unidadeVenda;
        changeSupport.firePropertyChange("unidadeVenda", oldUnidadeVenda, unidadeVenda);
    }

    public BigDecimal getQuantidade() {
        return quantidade.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_COMPRA, RoundingMode.HALF_UP);
    }

    public void setQuantidade(BigDecimal quantidade) {
        BigDecimal oldQuantidade = this.quantidade;
        this.quantidade = quantidade;
        changeSupport.firePropertyChange("quantidade", oldQuantidade, quantidade);
    }

    public BigDecimal getPrecoCompra() {
        return precoCompra.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_COMPRA, RoundingMode.HALF_UP);
    }

    public void setPrecoCompra(BigDecimal precoCompra) {
        BigDecimal oldPrecoCompra = this.precoCompra;
        this.precoCompra = precoCompra;
        changeSupport.firePropertyChange("precoCompra", oldPrecoCompra, precoCompra);
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        BigDecimal oldPrecoVenda = this.precoVenda;
        this.precoVenda = precoVenda;
        changeSupport.firePropertyChange("precoVenda", oldPrecoVenda, precoVenda);
    }

    public BigDecimal getTotalCompra() {
        return totalCompra.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_COMPRA, RoundingMode.HALF_UP);
    }

    public void setTotalCompra(BigDecimal totalCompra) {
        BigDecimal oldTotalCompra = this.totalCompra;
        this.totalCompra = totalCompra;
        changeSupport.firePropertyChange("totalCompra", oldTotalCompra, totalCompra);
    }

    public BigDecimal getPrecoFrete() {
        return precoFrete.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_COMPRA, RoundingMode.HALF_UP);
    }

    public void setPrecoFrete(BigDecimal precoFrete) {
        BigDecimal oldPrecoFrete = this.precoFrete;
        this.precoFrete = precoFrete;
        changeSupport.firePropertyChange("precoFrete", oldPrecoFrete, precoFrete);
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        String oldNcm = this.ncm;
        this.ncm = ncm;
        changeSupport.firePropertyChange("ncm", oldNcm, ncm);
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        String oldCfop = this.cfop;
        this.cfop = cfop;
        changeSupport.firePropertyChange("cfop", oldCfop, cfop);
    }

    public JsysCompras getJsysCompras() {
        return jsysCompras;
    }

    public void setJsysCompras(JsysCompras jsysCompras) {
        JsysCompras oldJsysCompras = this.jsysCompras;
        this.jsysCompras = jsysCompras;
        changeSupport.firePropertyChange("jsysCompras", oldJsysCompras, jsysCompras);
    }

    public JsysProdutosT getJsysProdutosT() {
        return jsysProdutosT;
    }

    public void setJsysProdutosT(JsysProdutosT jsysProdutosT) {
        this.jsysProdutosT = jsysProdutosT;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jsysComprasItensPK != null ? jsysComprasItensPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysComprasItens)) {
            return false;
        }
        JsysComprasItens other = (JsysComprasItens) object;
        return (this.jsysComprasItensPK != null || other.jsysComprasItensPK == null) && (this.jsysComprasItensPK == null || this.jsysComprasItensPK.equals(other.jsysComprasItensPK));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysComprasItens[ jsysComprasItensPK=" + jsysComprasItensPK + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
