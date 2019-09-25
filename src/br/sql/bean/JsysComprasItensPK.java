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
public class JsysComprasItensPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idCompra")
    private int idCompra;
    @Basic(optional = false)
    @Column(name = "idProduto")
    private int idProduto;

    public JsysComprasItensPK() {
    }

    public JsysComprasItensPK(int idCompra, int idProduto) {
        this.idCompra = idCompra;
        this.idProduto = idProduto;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
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
        hash += (int) idCompra;
        hash += (int) idProduto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysComprasItensPK)) {
            return false;
        }
        JsysComprasItensPK other = (JsysComprasItensPK) object;
        if (this.idCompra != other.idCompra) {
            return false;
        }
        return this.idProduto == other.idProduto;
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysComprasItensPK[ idCompra=" + idCompra + ", idProduto=" + idProduto + " ]";
    }
    
}
