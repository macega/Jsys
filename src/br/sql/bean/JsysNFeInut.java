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
@Table(name = "jsysNFeInut")
@NamedQueries({
    @NamedQuery(name = "JsysNFeInut.findAll", query = "SELECT j FROM JsysNFeInut j"),
    @NamedQuery(name = "JsysNFeInut.findById", query = "SELECT j FROM JsysNFeInut j WHERE j.id = :id"),
    @NamedQuery(name = "JsysNFeInut.findByIdInut", query = "SELECT j FROM JsysNFeInut j WHERE j.idInut = :idInut"),
    @NamedQuery(name = "JsysNFeInut.findByTpAmb", query = "SELECT j FROM JsysNFeInut j WHERE j.tpAmb = :tpAmb"),
    @NamedQuery(name = "JsysNFeInut.findByXServ", query = "SELECT j FROM JsysNFeInut j WHERE j.xServ = :xServ"),
    @NamedQuery(name = "JsysNFeInut.findByCUF", query = "SELECT j FROM JsysNFeInut j WHERE j.cUF = :cUF"),
    @NamedQuery(name = "JsysNFeInut.findByAno", query = "SELECT j FROM JsysNFeInut j WHERE j.ano = :ano"),
    @NamedQuery(name = "JsysNFeInut.findByCnpj", query = "SELECT j FROM JsysNFeInut j WHERE j.cnpj = :cnpj"),
    @NamedQuery(name = "JsysNFeInut.findByMod", query = "SELECT j FROM JsysNFeInut j WHERE j.mod = :mod"),
    @NamedQuery(name = "JsysNFeInut.findBySerie", query = "SELECT j FROM JsysNFeInut j WHERE j.serie = :serie"),
    @NamedQuery(name = "JsysNFeInut.findByNNFIni", query = "SELECT j FROM JsysNFeInut j WHERE j.nNFIni = :nNFIni"),
    @NamedQuery(name = "JsysNFeInut.findByNNFFin", query = "SELECT j FROM JsysNFeInut j WHERE j.nNFFin = :nNFFin"),
    @NamedQuery(name = "JsysNFeInut.findByXJust", query = "SELECT j FROM JsysNFeInut j WHERE j.xJust = :xJust"),
    @NamedQuery(name = "JsysNFeInut.findByEmitida", query = "SELECT j FROM JsysNFeInut j WHERE j.emitida = :emitida"),
    @NamedQuery(name = "JsysNFeInut.findByXmlInutNFe", query = "SELECT j FROM JsysNFeInut j WHERE j.xmlInutNFe = :xmlInutNFe"),
    @NamedQuery(name = "JsysNFeInut.findByXmlRetInutNFe", query = "SELECT j FROM JsysNFeInut j WHERE j.xmlRetInutNFe = :xmlRetInutNFe")})
public class JsysNFeInut implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic(optional = false)
    @Column(name = "idInut")
    private String idInut;
    @Basic(optional = false)
    @Column(name = "tpAmb")
    private String tpAmb;
    @Basic(optional = false)
    @Column(name = "xServ")
    private String xServ;
    @Basic(optional = false)
    @Column(name = "cUF")
    private String cUF;
    @Basic(optional = false)
    @Column(name = "ano")
    private String ano;
    @Basic(optional = false)
    @Column(name = "cnpj")
    private String cnpj;
    @Basic(optional = false)
    @Column(name = "mod")
    private String mod;
    @Basic(optional = false)
    @Column(name = "serie")
    private String serie;
    @Basic(optional = false)
    @Column(name = "nNFIni")
    private String nNFIni;
    @Basic(optional = false)
    @Column(name = "nNFFin")
    private String nNFFin;
    @Basic(optional = false)
    @Column(name = "xJust")
    private String xJust;
    @Basic(optional = false)
    @Column(name = "emitida")
    private boolean emitida;
    @Column(name = "xmlInutNFe")
    private String xmlInutNFe;
    @Column(name = "xmlRetInutNFe")
    private String xmlRetInutNFe;

    public JsysNFeInut() {
    }

    public JsysNFeInut(Long id) {
        this.id = id;
    }

    public JsysNFeInut(Long id, String idInut, String tpAmb, String xServ, String cUF, String ano, String cnpj, String mod, String serie, String nNFIni, String nNFFin, String xJust, boolean emitida) {
        this.id = id;
        this.idInut = idInut;
        this.tpAmb = tpAmb;
        this.xServ = xServ;
        this.cUF = cUF;
        this.ano = ano;
        this.cnpj = cnpj;
        this.mod = mod;
        this.serie = serie;
        this.nNFIni = nNFIni;
        this.nNFFin = nNFFin;
        this.xJust = xJust;
        this.emitida = emitida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdInut() {
        return idInut;
    }

    public void setIdInut(String idInut) {
        this.idInut = idInut;
    }

    public String getTpAmb() {
        return tpAmb;
    }

    public void setTpAmb(String tpAmb) {
        this.tpAmb = tpAmb;
    }

    public String getXServ() {
        return xServ;
    }

    public void setXServ(String xServ) {
        this.xServ = xServ;
    }

    public String getCUF() {
        return cUF;
    }

    public void setCUF(String cUF) {
        this.cUF = cUF;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNNFIni() {
        return nNFIni;
    }

    public void setNNFIni(String nNFIni) {
        this.nNFIni = nNFIni;
    }

    public String getNNFFin() {
        return nNFFin;
    }

    public void setNNFFin(String nNFFin) {
        this.nNFFin = nNFFin;
    }

    public String getXJust() {
        return xJust;
    }

    public void setXJust(String xJust) {
        this.xJust = xJust;
    }

    public boolean getEmitida() {
        return emitida;
    }

    public void setEmitida(boolean emitida) {
        this.emitida = emitida;
    }

    public String getXmlInutNFe() {
        return xmlInutNFe;
    }

    public void setXmlInutNFe(String xmlInutNFe) {
        this.xmlInutNFe = xmlInutNFe;
    }

    public String getXmlRetInutNFe() {
        return xmlRetInutNFe;
    }

    public void setXmlRetInutNFe(String xmlRetInutNFe) {
        this.xmlRetInutNFe = xmlRetInutNFe;
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
        if (!(object instanceof JsysNFeInut)) {
            return false;
        }
        JsysNFeInut other = (JsysNFeInut) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysNFeInut[ id=" + id + " ]";
    }

}
