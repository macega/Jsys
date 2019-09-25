/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sql.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Embeddable;

/**
 *
 * @author Juliano Alves Medina
 */
@Embeddable
public class JsysSubContaPK implements Serializable {
    @Basic(optional = false)
    private String idConta;
    @Basic(optional = false)
    private String idSubConta;

    public JsysSubContaPK() {
    }

    public JsysSubContaPK(String idConta, String idSubConta) {
        this.idConta = idConta;
        this.idSubConta = idSubConta;
    }

    public String getIdConta() {
        return idConta;
    }

    public void setIdConta(String idConta) {
        this.idConta = idConta;
    }

    public String getIdSubConta() {
        return idSubConta;
    }

    public void setIdSubConta(String idSubConta) {
        this.idSubConta = idSubConta;
    }
    
    public void setIdSubConta(int idSubConta) {
        this.idSubConta = String.valueOf(idSubConta);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConta != null ? idConta.hashCode() : 0);
        hash += (idSubConta != null ? idSubConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysSubContaPK)) {
            return false;
        }
        JsysSubContaPK other = (JsysSubContaPK) object;
        if ((this.idConta == null && other.idConta != null) || (this.idConta != null && !this.idConta.equals(other.idConta))) {
            return false;
        }
        if ((this.idSubConta == null && other.idSubConta != null) || (this.idSubConta != null && !this.idSubConta.equals(other.idSubConta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysSubContaPK[ idConta=" + idConta + ", idSubConta=" + idSubConta + " ]";
    }
    
}
