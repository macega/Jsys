package br.sql.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysNFeReferencias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysNFeReferencias.findAll", query = "SELECT j FROM JsysNFeReferencias j"),
    @NamedQuery(name = "JsysNFeReferencias.findById", query = "SELECT j FROM JsysNFeReferencias j WHERE j.id = :id"),
    @NamedQuery(name = "JsysNFeReferencias.findByNFeRef", query = "SELECT j FROM JsysNFeReferencias j WHERE j.nFeRef = :nFeRef"),
    @NamedQuery(name = "JsysNFeReferencias.findByRefNFe", query = "SELECT j FROM JsysNFeReferencias j WHERE j.refNFe = :refNFe"),
    @NamedQuery(name = "JsysNFeReferencias.findByCTeRef", query = "SELECT j FROM JsysNFeReferencias j WHERE j.cTeRef = :cTeRef"),
    @NamedQuery(name = "JsysNFeReferencias.findByRefCTe", query = "SELECT j FROM JsysNFeReferencias j WHERE j.refCTe = :refCTe"),
    @NamedQuery(name = "JsysNFeReferencias.findByNfRef", query = "SELECT j FROM JsysNFeReferencias j WHERE j.nfRef = :nfRef"),
    @NamedQuery(name = "JsysNFeReferencias.findByCUF", query = "SELECT j FROM JsysNFeReferencias j WHERE j.cUF = :cUF"),
    @NamedQuery(name = "JsysNFeReferencias.findByAamm", query = "SELECT j FROM JsysNFeReferencias j WHERE j.aamm = :aamm"),
    @NamedQuery(name = "JsysNFeReferencias.findByCnpj", query = "SELECT j FROM JsysNFeReferencias j WHERE j.cnpj = :cnpj"),
    @NamedQuery(name = "JsysNFeReferencias.findByModelo", query = "SELECT j FROM JsysNFeReferencias j WHERE j.modelo = :modelo"),
    @NamedQuery(name = "JsysNFeReferencias.findBySerie", query = "SELECT j FROM JsysNFeReferencias j WHERE j.serie = :serie"),
    @NamedQuery(name = "JsysNFeReferencias.findByNNF", query = "SELECT j FROM JsysNFeReferencias j WHERE j.nNF = :nNF"),
    @NamedQuery(name = "JsysNFeReferencias.findByNfpRef", query = "SELECT j FROM JsysNFeReferencias j WHERE j.nfpRef = :nfpRef"),
    @NamedQuery(name = "JsysNFeReferencias.findByCpf", query = "SELECT j FROM JsysNFeReferencias j WHERE j.cpf = :cpf"),
    @NamedQuery(name = "JsysNFeReferencias.findByIe", query = "SELECT j FROM JsysNFeReferencias j WHERE j.ie = :ie"),
    @NamedQuery(name = "JsysNFeReferencias.findByEcfRef", query = "SELECT j FROM JsysNFeReferencias j WHERE j.ecfRef = :ecfRef"),
    @NamedQuery(name = "JsysNFeReferencias.findByNECF", query = "SELECT j FROM JsysNFeReferencias j WHERE j.nECF = :nECF"),
    @NamedQuery(name = "JsysNFeReferencias.findByNCOO", query = "SELECT j FROM JsysNFeReferencias j WHERE j.nCOO = :nCOO")})
public class JsysNFeReferencias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic(optional = false)
    @Column(name = "NFeRef")
    private boolean nFeRef;
    @Column(name = "refNFe")
    private String refNFe;
    @Basic(optional = false)
    @Column(name = "CTeRef")
    private boolean cTeRef;
    @Column(name = "refCTe")
    private String refCTe;
    @Basic(optional = false)
    @Column(name = "NfRef")
    private boolean nfRef;
    @Column(name = "cUF")
    private String cUF;
    @Column(name = "AAMM")
    private String aamm;
    @Column(name = "CNPJ")
    private String cnpj;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "serie")
    private String serie;
    @Column(name = "nNF")
    private String nNF;
    @Basic(optional = false)
    @Column(name = "NfpRef")
    private boolean nfpRef;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "ie")
    private String ie;
    @Basic(optional = false)
    @Column(name = "EcfRef")
    private boolean ecfRef;
    @Column(name = "nECF")
    private String nECF;
    @Column(name = "nCOO")
    private String nCOO;
    @JoinColumn(name = "nfe_id", referencedColumnName = "nfe_id")
    @ManyToOne(optional = false)
    private JsysNFe nfeId;

    public JsysNFeReferencias() {
        this.nFeRef = false;
        this.cTeRef = false;
        this.nfRef = false;
        this.nfpRef = false;
        this.ecfRef = false;
    }

    public JsysNFeReferencias(Long id) {
        this.id = id;
    }

    public JsysNFeReferencias(Long id, boolean nFeRef, boolean cTeRef, boolean nfRef, boolean nfpRef, boolean ecfRef) {
        this.id = id;
        this.nFeRef = nFeRef;
        this.cTeRef = cTeRef;
        this.nfRef = nfRef;
        this.nfpRef = nfpRef;
        this.ecfRef = ecfRef;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getNFeRef() {
        return nFeRef;
    }

    public void setNFeRef(boolean nFeRef) {
        this.nFeRef = nFeRef;
    }

    public String getRefNFe() {
        return refNFe;
    }

    public void setRefNFe(String refNFe) {
        this.refNFe = refNFe;
    }

    public boolean getCTeRef() {
        return cTeRef;
    }

    public void setCTeRef(boolean cTeRef) {
        this.cTeRef = cTeRef;
    }

    public String getRefCTe() {
        return refCTe;
    }

    public void setRefCTe(String refCTe) {
        this.refCTe = refCTe;
    }

    public boolean getNfRef() {
        return nfRef;
    }

    public void setNfRef(boolean nfRef) {
        this.nfRef = nfRef;
    }

    public String getCUF() {
        return cUF;
    }

    public void setCUF(String cUF) {
        this.cUF = cUF;
    }

    public String getAamm() {
        return aamm;
    }

    public void setAamm(String aamm) {
        this.aamm = aamm;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNNF() {
        return nNF;
    }

    public void setNNF(String nNF) {
        this.nNF = nNF;
    }

    public boolean getNfpRef() {
        return nfpRef;
    }

    public void setNfpRef(boolean nfpRef) {
        this.nfpRef = nfpRef;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public boolean getEcfRef() {
        return ecfRef;
    }

    public void setEcfRef(boolean ecfRef) {
        this.ecfRef = ecfRef;
    }

    public String getNECF() {
        return nECF;
    }

    public void setNECF(String nECF) {
        this.nECF = nECF;
    }

    public String getNCOO() {
        return nCOO;
    }

    public void setNCOO(String nCOO) {
        this.nCOO = nCOO;
    }

    public JsysNFe getNfeId() {
        return nfeId;
    }

    public void setNfeId(JsysNFe nfeId) {
        this.nfeId = nfeId;
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
        if (!(object instanceof JsysNFeReferencias)) {
            return false;
        }
        JsysNFeReferencias other = (JsysNFeReferencias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysNFeReferencias[ id=" + id + " ]";
    }

}
