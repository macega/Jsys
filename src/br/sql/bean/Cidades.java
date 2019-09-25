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
@Table(name = "cidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cidades.findAll", query = "SELECT c FROM Cidades c"),
    @NamedQuery(name = "Cidades.findByIdCidade", query = "SELECT c FROM Cidades c WHERE c.idCidade = :idCidade"),
    @NamedQuery(name = "Cidades.findByPais", query = "SELECT c FROM Cidades c WHERE c.pais = :pais"),
    @NamedQuery(name = "Cidades.findByCodPais", query = "SELECT c FROM Cidades c WHERE c.codPais = :codPais"),
    @NamedQuery(name = "Cidades.findByMunicipio", query = "SELECT c FROM Cidades c WHERE c.municipio = :municipio"),
    @NamedQuery(name = "Cidades.findByCodMunicipio", query = "SELECT c FROM Cidades c WHERE c.codMunicipio = :codMunicipio"),
    @NamedQuery(name = "Cidades.findByUf", query = "SELECT c FROM Cidades c WHERE c.uf = :uf"),
    @NamedQuery(name = "Cidades.findByDistrito", query = "SELECT c FROM Cidades c WHERE c.distrito = :distrito")})
public class Cidades implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idCidade")
    private Integer idCidade;
    @Basic(optional = false)
    @Column(name = "pais")
    private String pais;
    @Basic(optional = false)
    @Column(name = "codPais")
    private int codPais;
    @Basic(optional = false)
    @Column(name = "municipio")
    private String municipio;
    @Basic(optional = false)
    @Column(name = "codMunicipio")
    private int codMunicipio;
    @Basic(optional = false)
    @Column(name = "uf")
    private String uf;
    @Basic(optional = false)
    @Column(name = "distrito")
    private boolean distrito;

    public Cidades() {
    }

    public Cidades(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public Cidades(Integer idCidade, String pais, int codPais, String municipio, int codMunicipio, String uf, boolean distrito) {
        this.idCidade = idCidade;
        this.pais = pais;
        this.codPais = codPais;
        this.municipio = municipio;
        this.codMunicipio = codMunicipio;
        this.uf = uf;
        this.distrito = distrito;
    }

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getCodPais() {
        return codPais;
    }

    public void setCodPais(int codPais) {
        this.codPais = codPais;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public int getCodMunicipio() {
        return codMunicipio;
    }

    public void setCodMunicipio(int codMunicipio) {
        this.codMunicipio = codMunicipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public boolean getDistrito() {
        return distrito;
    }

    public void setDistrito(boolean distrito) {
        this.distrito = distrito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCidade != null ? idCidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cidades)) {
            return false;
        }
        Cidades other = (Cidades) object;
        if ((this.idCidade == null && other.idCidade != null) || (this.idCidade != null && !this.idCidade.equals(other.idCidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.sql.bean.Cidades[ idCidade=" + idCidade + " ]";
    }
    
}
