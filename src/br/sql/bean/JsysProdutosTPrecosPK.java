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
public class JsysProdutosTPrecosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idProduto")
    private int idProduto;
    @Basic(optional = false)
    @Column(name = "idloja")
    private String idloja;

    public JsysProdutosTPrecosPK() {
    }

    public JsysProdutosTPrecosPK(int idProduto, String idloja) {
        this.idProduto = idProduto;
        this.idloja = idloja;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getIdloja() {
        return idloja;
    }

    public void setIdloja(String idloja) {
        this.idloja = idloja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProduto;
        hash += (idloja != null ? idloja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysProdutosTPrecosPK)) {
            return false;
        }
        JsysProdutosTPrecosPK other = (JsysProdutosTPrecosPK) object;
        if (this.idProduto != other.idProduto) {
            return false;
        }
        return (this.idloja != null || other.idloja == null) && (this.idloja == null || this.idloja.equals(other.idloja));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysProdutosTPrecosPK[ idProduto=" + idProduto + ", idloja=" + idloja + " ]";
    }

}
