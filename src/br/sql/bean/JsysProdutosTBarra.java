/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sql.bean;

import java.io.Serializable;
import javax.persistence.Column;
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
@Table(name = "jsysProdutosTBarra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysProdutosTBarra.findAll", query = "SELECT j FROM JsysProdutosTBarra j"),
    @NamedQuery(name = "JsysProdutosTBarra.findByIdbarra", query = "SELECT j FROM JsysProdutosTBarra j WHERE j.jsysProdutosTBarraPK.idbarra = :idbarra"),
    @NamedQuery(name = "JsysProdutosTBarra.findByIdProduto", query = "SELECT j FROM JsysProdutosTBarra j WHERE j.jsysProdutosTBarraPK.idProduto = :idProduto"),
    @NamedQuery(name = "JsysProdutosTBarra.findByObs", query = "SELECT j FROM JsysProdutosTBarra j WHERE j.obs = :obs")})
public class JsysProdutosTBarra implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JsysProdutosTBarraPK jsysProdutosTBarraPK;
    @Column(name = "obs")
    private String obs;
    @JoinColumn(name = "idProduto", referencedColumnName = "idProduto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private JsysProdutosT jsysProdutosT;

    public JsysProdutosTBarra() {
    }

    public JsysProdutosTBarra(JsysProdutosTBarraPK jsysProdutosTBarraPK) {
        this.jsysProdutosTBarraPK = jsysProdutosTBarraPK;
    }

    public JsysProdutosTBarra(String idbarra, int idProduto) {
        this.jsysProdutosTBarraPK = new JsysProdutosTBarraPK(idbarra, idProduto);
    }

    public JsysProdutosTBarraPK getJsysProdutosTBarraPK() {
        return jsysProdutosTBarraPK;
    }

    public void setJsysProdutosTBarraPK(JsysProdutosTBarraPK jsysProdutosTBarraPK) {
        this.jsysProdutosTBarraPK = jsysProdutosTBarraPK;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
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
        hash += (jsysProdutosTBarraPK != null ? jsysProdutosTBarraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysProdutosTBarra)) {
            return false;
        }
        JsysProdutosTBarra other = (JsysProdutosTBarra) object;
        if ((this.jsysProdutosTBarraPK == null && other.jsysProdutosTBarraPK != null) || (this.jsysProdutosTBarraPK != null && !this.jsysProdutosTBarraPK.equals(other.jsysProdutosTBarraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysProdutosTBarra[ jsysProdutosTBarraPK=" + jsysProdutosTBarraPK + " ]";
    }
    
}
