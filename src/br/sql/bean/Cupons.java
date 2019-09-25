/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "cupons")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cupons.findAll", query = "SELECT c FROM Cupons c"),
    @NamedQuery(name = "Cupons.findById", query = "SELECT c FROM Cupons c WHERE c.id = :id"),
    @NamedQuery(name = "Cupons.findByVenda", query = "SELECT c FROM Cupons c WHERE c.venda = :venda"),
    @NamedQuery(name = "Cupons.findByCpfCnpj", query = "SELECT c FROM Cupons c WHERE c.cpfCnpj = :cpfCnpj"),
    @NamedQuery(name = "Cupons.findByOk", query = "SELECT c FROM Cupons c WHERE c.ok = :ok")})
public class Cupons implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "venda")
    private int venda;
    @Column(name = "CpfCnpj")
    private String cpfCnpj;
    @Basic(optional = false)
    @Column(name = "ok")
    private boolean ok;

    public Cupons() {
    }

    public Cupons(Integer id) {
        this.id = id;
    }

    public Cupons(Integer id, int venda, boolean ok) {
        this.id = id;
        this.venda = venda;
        this.ok = ok;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getVenda() {
        return venda;
    }

    public void setVenda(int venda) {
        this.venda = venda;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public boolean getOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cupons)) {
            return false;
        }
        Cupons other = (Cupons) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.sql.bean.Cupons[ id=" + id + " ]";
    }
    
}
