/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sql.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Juliano Alves Medina
 */
@Embeddable
public class JsysProdutosTBarraPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idbarra")
    private String idbarra;
    @Basic(optional = false)
    @Column(name = "idProduto")
    private int idProduto;

    public JsysProdutosTBarraPK() {
    }

    public JsysProdutosTBarraPK(String idbarra, int idProduto) {
        this.idbarra = idbarra;
        this.idProduto = idProduto;
    }

    public String getIdbarra() {
        return idbarra;
    }

    public void setIdbarra(String idbarra) {
        this.idbarra = idbarra;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbarra != null ? idbarra.hashCode() : 0);
        hash += (int) idProduto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysProdutosTBarraPK)) {
            return false;
        }
        JsysProdutosTBarraPK other = (JsysProdutosTBarraPK) object;
        if ((this.idbarra == null && other.idbarra != null) || (this.idbarra != null && !this.idbarra.equals(other.idbarra))) {
            return false;
        }
        if (this.idProduto != other.idProduto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysProdutosTBarraPK[ idbarra=" + idbarra + ", idProduto=" + idProduto + " ]";
    }
    
}
