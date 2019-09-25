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
public class JsysOrcamentoItensPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idOrcamento")
    private int idOrcamento;
    @Basic(optional = false)
    @Column(name = "idProduto")
    private int idProduto;

    public JsysOrcamentoItensPK() {
    }

    public JsysOrcamentoItensPK(int idOrcamento, int idProduto) {
        this.idOrcamento = idOrcamento;
        this.idProduto = idProduto;
    }

    public int getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(int idOrcamento) {
        this.idOrcamento = idOrcamento;
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
        hash +=  idOrcamento;
        hash +=  idProduto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysOrcamentoItensPK)) {
            return false;
        }
        JsysOrcamentoItensPK other = (JsysOrcamentoItensPK) object;
        if (this.idOrcamento != other.idOrcamento) {
            return false;
        }
        if (this.idProduto != other.idProduto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysOrcamentoItensPK[ idOrcamento=" + idOrcamento + ", idProduto=" + idProduto + " ]";
    }
    
}
