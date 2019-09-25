/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sql.bean;

import br.sql.util.ManagerString;
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
@Table(name = "jsysProdutosTGrupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysProdutosTGrupos.findAll", query = "SELECT j FROM JsysProdutosTGrupos j"),
    @NamedQuery(name = "JsysProdutosTGrupos.findByIdGrupo", query = "SELECT j FROM JsysProdutosTGrupos j WHERE j.idGrupo = :idGrupo"),
    @NamedQuery(name = "JsysProdutosTGrupos.findByNomeGrupo", query = "SELECT j FROM JsysProdutosTGrupos j WHERE j.nomeGrupo = :nomeGrupo")})
public class JsysProdutosTGrupos implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "idGrupo")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGrupo;
    
    @Basic(optional = false)
    @Column(name = "nomeGrupo")
    private String nomeGrupo;

    public JsysProdutosTGrupos() {
    }

    public JsysProdutosTGrupos(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public JsysProdutosTGrupos(Integer idGrupo, String nomeGrupo) {
        this.idGrupo = idGrupo;
        setNomeGrupo(nomeGrupo);
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = ManagerString.Left(nomeGrupo, 30);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupo != null ? idGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysProdutosTGrupos)) {
            return false;
        }
        JsysProdutosTGrupos other = (JsysProdutosTGrupos) object;
        if ((this.idGrupo == null && other.idGrupo != null) || (this.idGrupo != null && !this.idGrupo.equals(other.idGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysProdutosTGrupos[ idGrupo=" + idGrupo + " ]";
    }
    
}
