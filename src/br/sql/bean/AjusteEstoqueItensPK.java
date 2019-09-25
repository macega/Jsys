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
public class AjusteEstoqueItensPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idAjusteEstoque")
    private int idAjusteEstoque;
    @Basic(optional = false)
    @Column(name = "idProduto")
    private int idProduto;

    public AjusteEstoqueItensPK() {
    }

    public AjusteEstoqueItensPK(int idAjusteEstoque, int idProduto) {
        this.idAjusteEstoque = idAjusteEstoque;
        this.idProduto = idProduto;
    }

    public int getIdAjusteEstoque() {
        return idAjusteEstoque;
    }

    public void setIdAjusteEstoque(int idAjusteEstoque) {
        this.idAjusteEstoque = idAjusteEstoque;
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
        hash += idAjusteEstoque;
        hash += idProduto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AjusteEstoqueItensPK)) {
            return false;
        }
        AjusteEstoqueItensPK other = (AjusteEstoqueItensPK) object;
        if (this.idAjusteEstoque != other.idAjusteEstoque) {
            return false;
        }
        return this.idProduto == other.idProduto;
    }

    @Override
    public String toString() {
        return "br.sql.bean.AjusteEstoqueItensPK[ idAjusteEstoque=" + idAjusteEstoque + ", idProduto=" + idProduto + " ]";
    }
    
}
