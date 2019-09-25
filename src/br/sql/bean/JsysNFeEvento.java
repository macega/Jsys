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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysNFeEvento")
@XmlRootElement
@NamedQueries({
    
    @NamedQuery(name = "JsysNFeEvento.findByDhEvento", query = "SELECT j FROM JsysNFeEvento j WHERE (j.dhEvento BETWEEN :dhEventoInicial AND :dhEventoFinal) and (j.emitida = :emitida)"),
    
    @NamedQuery(name = "JsysNFeEvento.findAll", query = "SELECT j FROM JsysNFeEvento j"),
    @NamedQuery(name = "JsysNFeEvento.findById", query = "SELECT j FROM JsysNFeEvento j WHERE j.id = :id"),
    @NamedQuery(name = "JsysNFeEvento.findByIdEvento", query = "SELECT j FROM JsysNFeEvento j WHERE j.idEvento = :idEvento"),
    @NamedQuery(name = "JsysNFeEvento.findByCOrgao", query = "SELECT j FROM JsysNFeEvento j WHERE j.cOrgao = :cOrgao"),
    @NamedQuery(name = "JsysNFeEvento.findByTpAmb", query = "SELECT j FROM JsysNFeEvento j WHERE j.tpAmb = :tpAmb"),
    @NamedQuery(name = "JsysNFeEvento.findByChNFe", query = "SELECT j FROM JsysNFeEvento j WHERE j.chNFe = :chNFe"),
    
    @NamedQuery(name = "JsysNFeEvento.findByTpEvento", query = "SELECT j FROM JsysNFeEvento j WHERE j.tpEvento = :tpEvento"),
    @NamedQuery(name = "JsysNFeEvento.findByNSeqEvento", query = "SELECT j FROM JsysNFeEvento j WHERE j.nSeqEvento = :nSeqEvento"),
    @NamedQuery(name = "JsysNFeEvento.findByDescEvento", query = "SELECT j FROM JsysNFeEvento j WHERE j.descEvento = :descEvento"),
    @NamedQuery(name = "JsysNFeEvento.findByNProt", query = "SELECT j FROM JsysNFeEvento j WHERE j.nProt = :nProt"),
    @NamedQuery(name = "JsysNFeEvento.findByXJust", query = "SELECT j FROM JsysNFeEvento j WHERE j.xJust = :xJust"),
    @NamedQuery(name = "JsysNFeEvento.findByEmitida", query = "SELECT j FROM JsysNFeEvento j WHERE j.emitida = :emitida"),
    @NamedQuery(name = "JsysNFeEvento.findByEnvEventoCancNFe", query = "SELECT j FROM JsysNFeEvento j WHERE j.envEventoCancNFe = :envEventoCancNFe"),
    @NamedQuery(name = "JsysNFeEvento.findByRetEnvEventoCancNFe", query = "SELECT j FROM JsysNFeEvento j WHERE j.retEnvEventoCancNFe = :retEnvEventoCancNFe")})
public class JsysNFeEvento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "idEvento")
    private String idEvento;
    @Basic(optional = false)
    @Column(name = "cOrgao")
    private String cOrgao;
    @Basic(optional = false)
    @Column(name = "tpAmb")
    private String tpAmb;
    @Basic(optional = false)
    @Column(name = "chNFe")
    private String chNFe;
    @Basic(optional = false)
    @Column(name = "dhEvento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dhEvento;
    @Basic(optional = false)
    @Column(name = "tpEvento")
    private String tpEvento;
    @Basic(optional = false)
    @Column(name = "nSeqEvento")
    private int nSeqEvento;
    @Basic(optional = false)
    @Column(name = "descEvento")
    private String descEvento;
    @Basic(optional = false)
    @Column(name = "nProt")
    private String nProt;
    @Basic(optional = false)
    @Column(name = "xJust")
    private String xJust;
    @Column(name = "emitida")
    private Boolean emitida;
    @Column(name = "envEventoCancNFe")
    private String envEventoCancNFe;
    @Column(name = "retEnvEventoCancNFe")
    private String retEnvEventoCancNFe;
    @Column(name = "procEventoNFe")
    private String procEventoNFe;

    public JsysNFeEvento() {
    }

    public JsysNFeEvento(Long id) {
        this.id = id;
    }

    public JsysNFeEvento(Long id, String idEvento, String cOrgao, String tpAmb, String chNFe, Date dhEvento, String tpEvento, int nSeqEvento, String descEvento, String nProt, String xJust) {
        this.id = id;
        this.idEvento = idEvento;
        this.cOrgao = cOrgao;
        this.tpAmb = tpAmb;
        this.chNFe = chNFe;
        this.dhEvento = dhEvento;
        this.tpEvento = tpEvento;
        this.nSeqEvento = nSeqEvento;
        this.descEvento = descEvento;
        this.nProt = nProt;
        this.xJust = xJust;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getCOrgao() {
        return cOrgao;
    }

    public void setCOrgao(String cOrgao) {
        this.cOrgao = cOrgao;
    }

    public String getTpAmb() {
        return tpAmb;
    }

    public void setTpAmb(String tpAmb) {
        this.tpAmb = tpAmb;
    }

    public String getChNFe() {
        return chNFe;
    }

    public void setChNFe(String chNFe) {
        this.chNFe = chNFe;
    }

    public Date getDhEvento() {
        return dhEvento;
    }

    public void setDhEvento(Date dhEvento) {
        this.dhEvento = dhEvento;
    }

    public String getTpEvento() {
        return tpEvento;
    }

    public void setTpEvento(String tpEvento) {
        this.tpEvento = tpEvento;
    }

    public int getNSeqEvento() {
        return nSeqEvento;
    }

    public void setNSeqEvento(int nSeqEvento) {
        this.nSeqEvento = nSeqEvento;
    }

    public String getDescEvento() {
        return descEvento;
    }

    public void setDescEvento(String descEvento) {
        this.descEvento = descEvento;
    }

    public String getNProt() {
        return nProt;
    }

    public void setNProt(String nProt) {
        this.nProt = nProt;
    }

    public String getXJust() {
        return xJust;
    }

    public void setXJust(String xJust) {
        this.xJust = xJust;
    }

    public Boolean getEmitida() {
        return emitida;
    }

    public void setEmitida(Boolean emitida) {
        this.emitida = emitida;
    }

    public String getEnvEventoCancNFe() {
        return envEventoCancNFe;
    }

    public void setEnvEventoCancNFe(String envEventoCancNFe) {
        this.envEventoCancNFe = envEventoCancNFe.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
    }

    public String getRetEnvEventoCancNFe() {
        return retEnvEventoCancNFe;
    }

    public void setRetEnvEventoCancNFe(String retEnvEventoCancNFe) {
        this.retEnvEventoCancNFe = retEnvEventoCancNFe;
    }

    public String getProcEventoNFe() {
        return procEventoNFe;
    }

    public void setProcEventoNFe(String procEventoNFe) {
        this.procEventoNFe = procEventoNFe;
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
        if (!(object instanceof JsysNFeEvento)) {
            return false;
        }
        JsysNFeEvento other = (JsysNFeEvento) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysNFeEvento[ id=" + id + " ]";
    }
    
}
