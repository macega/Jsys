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
public class TransferenciasProdutosItensPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idTransf")
    private int idTransf;
    @Basic(optional = false)
    @Column(name = "idProduto")
    private int idProduto;
    

    public TransferenciasProdutosItensPK() {
    }

    public TransferenciasProdutosItensPK(int idTransf, int idProduto) {
        this.idTransf = idTransf;
        this.idProduto = idProduto;
    }

    public int getIdTransf() {
        return idTransf;
    }

    public void setIdTransf(int idTransf) {
        this.idTransf = idTransf;
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
        hash += idTransf;
        hash += idProduto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransferenciasProdutosItensPK)) {
            return false;
        }
        TransferenciasProdutosItensPK other = (TransferenciasProdutosItensPK) object;
        if (this.idTransf != other.idTransf) {
            return false;
        }
        return this.idProduto == other.idProduto;
    }

    @Override
    public String toString() {
        return "br.sql.bean.TransferenciasProdutosItensPK[ idTransf=" + idTransf + ", idProduto=" + idProduto + " ]";
    }
    
}
