/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sql.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "NCM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ncm.findAll", query = "SELECT n FROM Ncm n"),
    @NamedQuery(name = "Ncm.findById", query = "SELECT n FROM Ncm n WHERE n.id = :id"),
    @NamedQuery(name = "Ncm.findByCodigo", query = "SELECT n FROM Ncm n WHERE n.codigo = :codigo"),
    @NamedQuery(name = "Ncm.findByEx", query = "SELECT n FROM Ncm n WHERE n.ex = :ex"),
    @NamedQuery(name = "Ncm.findByTipo", query = "SELECT n FROM Ncm n WHERE n.tipo = :tipo"),
    @NamedQuery(name = "Ncm.findByDescricao", query = "SELECT n FROM Ncm n WHERE n.descricao = :descricao"),
    @NamedQuery(name = "Ncm.findByNacionalfederal", query = "SELECT n FROM Ncm n WHERE n.nacionalfederal = :nacionalfederal"),
    @NamedQuery(name = "Ncm.findByImportadosfederal", query = "SELECT n FROM Ncm n WHERE n.importadosfederal = :importadosfederal"),
    @NamedQuery(name = "Ncm.findByEstadual", query = "SELECT n FROM Ncm n WHERE n.estadual = :estadual"),
    @NamedQuery(name = "Ncm.findByMunicipal", query = "SELECT n FROM Ncm n WHERE n.municipal = :municipal"),
    @NamedQuery(name = "Ncm.findByVigenciainicio", query = "SELECT n FROM Ncm n WHERE n.vigenciainicio = :vigenciainicio"),
    @NamedQuery(name = "Ncm.findByVigenciafim", query = "SELECT n FROM Ncm n WHERE n.vigenciafim = :vigenciafim"),
    @NamedQuery(name = "Ncm.findByChave", query = "SELECT n FROM Ncm n WHERE n.chave = :chave"),
    @NamedQuery(name = "Ncm.findByVersao", query = "SELECT n FROM Ncm n WHERE n.versao = :versao"),
    @NamedQuery(name = "Ncm.findByFonte", query = "SELECT n FROM Ncm n WHERE n.fonte = :fonte")})
public class Ncm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "ex")
    private String ex;
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "nacionalfederal")
    private BigDecimal nacionalfederal;
    @Column(name = "importadosfederal")
    private BigDecimal importadosfederal;
    @Column(name = "estadual")
    private BigDecimal estadual;
    @Column(name = "municipal")
    private BigDecimal municipal;
    @Column(name = "vigenciainicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vigenciainicio;
    @Column(name = "vigenciafim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vigenciafim;
    @Column(name = "chave")
    private String chave;
    @Column(name = "versao")
    private String versao;
    @Column(name = "fonte")
    private String fonte;

    public Ncm() {
    }

    public Ncm(Long id) {
        this.id = id;
    }

    public Ncm(Long id, String codigo, String descricao) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getNacionalfederal() {
        return nacionalfederal;
    }

    public void setNacionalfederal(BigDecimal nacionalfederal) {
        this.nacionalfederal = nacionalfederal;
    }

    public BigDecimal getImportadosfederal() {
        return importadosfederal;
    }

    public void setImportadosfederal(BigDecimal importadosfederal) {
        this.importadosfederal = importadosfederal;
    }

    public BigDecimal getEstadual() {
        return estadual;
    }

    public void setEstadual(BigDecimal estadual) {
        this.estadual = estadual;
    }

    public BigDecimal getMunicipal() {
        return municipal;
    }

    public void setMunicipal(BigDecimal municipal) {
        this.municipal = municipal;
    }

    public Date getVigenciainicio() {
        return vigenciainicio;
    }

    public void setVigenciainicio(Date vigenciainicio) {
        this.vigenciainicio = vigenciainicio;
    }

    public Date getVigenciafim() {
        return vigenciafim;
    }

    public void setVigenciafim(Date vigenciafim) {
        this.vigenciafim = vigenciafim;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
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
        if (!(object instanceof Ncm)) {
            return false;
        }
        Ncm other = (Ncm) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.sql.bean.Ncm[ id=" + id + " ]";
    }
    
}
