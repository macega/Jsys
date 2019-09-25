/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sql.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Juliano Alves Medina
 */
@Embeddable
public class FuncionariosPontoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @Column(name = "cnpjCpf")
    private String cnpjCpf;

    public FuncionariosPontoPK() {
    }

    public FuncionariosPontoPK(Date data, String cnpjCpf) {
        this.data = data;
        this.cnpjCpf = cnpjCpf;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (data != null ? data.hashCode() : 0);
        hash += (cnpjCpf != null ? cnpjCpf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FuncionariosPontoPK)) {
            return false;
        }
        FuncionariosPontoPK other = (FuncionariosPontoPK) object;
        if ((this.data == null && other.data != null) || (this.data != null && !this.data.equals(other.data))) {
            return false;
        }
        return (this.cnpjCpf != null || other.cnpjCpf == null) && (this.cnpjCpf == null || this.cnpjCpf.equals(other.cnpjCpf));
    }

    @Override
    public String toString() {
        return "br.sql.bean.FuncionariosPontoPK[ data=" + data + ", cnpjCpf=" + cnpjCpf + " ]";
    }
    
}
