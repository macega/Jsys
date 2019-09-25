/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sql.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysNFeLote")
@NamedQueries({
    @NamedQuery(name = "JsysNFeLote.findAll", query = "SELECT j FROM JsysNFeLote j"),
    @NamedQuery(name = "JsysNFeLote.findById", query = "SELECT j FROM JsysNFeLote j WHERE j.id = :id"),
    @NamedQuery(name = "JsysNFeLote.findByIdLote", query = "SELECT j FROM JsysNFeLote j WHERE j.idLote = :idLote")})
public class JsysNFeLote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic(optional = false)
    @Column(name = "idLote")
    private String idLote;

    public JsysNFeLote() {
    }

    public JsysNFeLote(Long id) {
        this.id = id;
    }

    public JsysNFeLote(Long id, String idLote) {
        this.id = id;
        this.idLote = idLote;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdLote() {
        return idLote;
    }

    public void setIdLote(String idLote) {
        this.idLote = idLote;
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
        if (!(object instanceof JsysNFeLote)) {
            return false;
        }
        JsysNFeLote other = (JsysNFeLote) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysNFeLote[ id=" + id + " ]";
    }
    
}
