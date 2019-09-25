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
@Table(name = "jsysProdutosTPrecos")
@NamedQueries({
    @NamedQuery(name = "JsysProdutosTPrecos.findAll", query = "SELECT j FROM JsysProdutosTPrecos j"),
    @NamedQuery(name = "JsysProdutosTPrecos.findByIdProduto", query = "SELECT j FROM JsysProdutosTPrecos j WHERE j.jsysProdutosTPrecosPK.idProduto = :idProduto"),
    @NamedQuery(name = "JsysProdutosTPrecos.findByIdProdutoAndIdloja", query = "SELECT j FROM JsysProdutosTPrecos j WHERE j.jsysProdutosTPrecosPK.idProduto = :idProduto AND j.jsysProdutosTPrecosPK.idloja = :idloja"),
    @NamedQuery(name = "JsysProdutosTPrecos.findByIdloja", query = "SELECT j FROM JsysProdutosTPrecos j WHERE j.jsysProdutosTPrecosPK.idloja = :idloja"),
    @NamedQuery(name = "JsysProdutosTPrecos.findByIdlojaAndNCM", query = "SELECT j FROM JsysProdutosTPrecos j WHERE j.jsysProdutosTPrecosPK.idloja = :idloja AND j.jsysProdutosT.NCM <> ''"),
    @NamedQuery(name = "JsysProdutosTPrecos.findByPrecoVenda", query = "SELECT j FROM JsysProdutosTPrecos j WHERE j.precoVenda = :precoVenda")})
public class JsysProdutosTPrecos implements Serializable {
    @Transient
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JsysProdutosTPrecosPK jsysProdutosTPrecosPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "precoVenda")
    private BigDecimal precoVenda;
    @JoinColumn(name = "idProduto", referencedColumnName = "idProduto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private JsysProdutosT jsysProdutosT;

    public JsysProdutosTPrecos() {
    }

    public JsysProdutosTPrecos(JsysProdutosTPrecosPK jsysProdutosTPrecosPK) {
        this.jsysProdutosTPrecosPK = jsysProdutosTPrecosPK;
    }

    public JsysProdutosTPrecos(JsysProdutosTPrecosPK jsysProdutosTPrecosPK, BigDecimal precoVenda) {
        this.jsysProdutosTPrecosPK = jsysProdutosTPrecosPK;
        this.precoVenda = precoVenda;
    }

    public JsysProdutosTPrecos(int idProduto, String idloja) {
        this.jsysProdutosTPrecosPK = new JsysProdutosTPrecosPK(idProduto, idloja);
    }
    
    public JsysProdutosTPrecos(int idProduto, String idloja, BigDecimal precoVenda) {
        this.jsysProdutosTPrecosPK = new JsysProdutosTPrecosPK(idProduto, idloja);
        this.precoVenda = precoVenda;
    }

    public JsysProdutosTPrecosPK getJsysProdutosTPrecosPK() {
        return jsysProdutosTPrecosPK;
    }

    public void setJsysProdutosTPrecosPK(JsysProdutosTPrecosPK jsysProdutosTPrecosPK) {
        this.jsysProdutosTPrecosPK = jsysProdutosTPrecosPK;
    }

    public BigDecimal getPrecoVenda() {
        // retorna o campo com 2 casas decimais
        return precoVenda.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_VENDA, RoundingMode.HALF_UP);
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        BigDecimal oldPrecoVenda = this.precoVenda;
        this.precoVenda = precoVenda;
        changeSupport.firePropertyChange("precoVenda", oldPrecoVenda, precoVenda);
    }

    public JsysProdutosT getJsysProdutosT() {
        return jsysProdutosT;
    }

    public void setJsysProdutosT(JsysProdutosT jsysProdutosT) {
        JsysProdutosT oldJsysProdutosT = this.jsysProdutosT;
        this.jsysProdutosT = jsysProdutosT;
        changeSupport.firePropertyChange("jsysProdutosT", oldJsysProdutosT, jsysProdutosT);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jsysProdutosTPrecosPK != null ? jsysProdutosTPrecosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysProdutosTPrecos)) {
            return false;
        }
        JsysProdutosTPrecos other = (JsysProdutosTPrecos) object;
        return (this.jsysProdutosTPrecosPK != null || other.jsysProdutosTPrecosPK == null) && (this.jsysProdutosTPrecosPK == null || this.jsysProdutosTPrecosPK.equals(other.jsysProdutosTPrecosPK));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysProdutosTPrecos[ jsysProdutosTPrecosPK=" + jsysProdutosTPrecosPK + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
