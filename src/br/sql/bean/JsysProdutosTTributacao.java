package br.sql.bean;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "jsysProdutosTTributacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysProdutosTTributacao.findAll", query = "SELECT j FROM JsysProdutosTTributacao j")
    , @NamedQuery(name = "JsysProdutosTTributacao.findByIdTributacao", query = "SELECT j FROM JsysProdutosTTributacao j WHERE j.idTributacao = :idTributacao")
    , @NamedQuery(name = "JsysProdutosTTributacao.findByNome", query = "SELECT j FROM JsysProdutosTTributacao j WHERE j.nome = :nome")
    , @NamedQuery(name = "JsysProdutosTTributacao.findByCst", query = "SELECT j FROM JsysProdutosTTributacao j WHERE j.cst = :cst")
    , @NamedQuery(name = "JsysProdutosTTributacao.findByCson", query = "SELECT j FROM JsysProdutosTTributacao j WHERE j.cson = :cson")
    , @NamedQuery(name = "JsysProdutosTTributacao.findByCfop", query = "SELECT j FROM JsysProdutosTTributacao j WHERE j.cfop = :cfop")
    , @NamedQuery(name = "JsysProdutosTTributacao.findByPCredSNDentro", query = "SELECT j FROM JsysProdutosTTributacao j WHERE j.pCredSNDentro = :pCredSNDentro")
    , @NamedQuery(name = "JsysProdutosTTributacao.findByPCredSNFora", query = "SELECT j FROM JsysProdutosTTributacao j WHERE j.pCredSNFora = :pCredSNFora")
    , @NamedQuery(name = "JsysProdutosTTributacao.findByModBC", query = "SELECT j FROM JsysProdutosTTributacao j WHERE j.modBC = :modBC")
    , @NamedQuery(name = "JsysProdutosTTributacao.findByPRedBC", query = "SELECT j FROM JsysProdutosTTributacao j WHERE j.pRedBC = :pRedBC")
    , @NamedQuery(name = "JsysProdutosTTributacao.findByPICMS", query = "SELECT j FROM JsysProdutosTTributacao j WHERE j.pICMS = :pICMS")
    , @NamedQuery(name = "JsysProdutosTTributacao.findByPRedBCST", query = "SELECT j FROM JsysProdutosTTributacao j WHERE j.pRedBCST = :pRedBCST")
    , @NamedQuery(name = "JsysProdutosTTributacao.findByPMVAST", query = "SELECT j FROM JsysProdutosTTributacao j WHERE j.pMVAST = :pMVAST")
    , @NamedQuery(name = "JsysProdutosTTributacao.findByPICMSST", query = "SELECT j FROM JsysProdutosTTributacao j WHERE j.pICMSST = :pICMSST")
    , @NamedQuery(name = "JsysProdutosTTributacao.findByDecreto", query = "SELECT j FROM JsysProdutosTTributacao j WHERE j.decreto = :decreto")
    , @NamedQuery(name = "JsysProdutosTTributacao.findByModBCST", query = "SELECT j FROM JsysProdutosTTributacao j WHERE j.modBCST = :modBCST")})
public class JsysProdutosTTributacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idTributacao")
    private Integer idTributacao;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Column(name = "cst")
    private String cst;
    @Column(name = "cson")
    private String cson;
    @Column(name = "cfop")
    private Integer cfop;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pCredSNDentro")
    private BigDecimal pCredSNDentro;
    @Column(name = "pCredSNFora")
    private BigDecimal pCredSNFora;
    @Column(name = "modBC")
    private String modBC;
    @Column(name = "pRedBC")
    private BigDecimal pRedBC;
    @Column(name = "pICMS")
    private BigDecimal pICMS;
    @Column(name = "pRedBCST")
    private BigDecimal pRedBCST;
    @Column(name = "pMVAST")
    private BigDecimal pMVAST;
    @Column(name = "pICMSST")
    private BigDecimal pICMSST;
    @Column(name = "decreto")
    private String decreto;
    @Column(name = "modBCST")
    private String modBCST;

    public JsysProdutosTTributacao() {
    }

    public JsysProdutosTTributacao(Integer idTributacao) {
        this.idTributacao = idTributacao;
    }

    public JsysProdutosTTributacao(Integer idTributacao, String nome) {
        this.idTributacao = idTributacao;
        this.nome = nome;
    }

    public Integer getIdTributacao() {
        return idTributacao;
    }

    public void setIdTributacao(Integer idTributacao) {
        this.idTributacao = idTributacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCst() {
        return cst;
    }

    public void setCst(String cst) {
        this.cst = cst;
    }

    public String getCson() {
        return cson;
    }

    public void setCson(String cson) {
        this.cson = cson;
    }

    public Integer getCfop() {
        return cfop;
    }

    public void setCfop(Integer cfop) {
        this.cfop = cfop;
    }

    public BigDecimal getPCredSNDentro() {
        return pCredSNDentro;
    }

    public void setPCredSNDentro(BigDecimal pCredSNDentro) {
        this.pCredSNDentro = pCredSNDentro;
    }

    public BigDecimal getPCredSNFora() {
        return pCredSNFora;
    }

    public void setPCredSNFora(BigDecimal pCredSNFora) {
        this.pCredSNFora = pCredSNFora;
    }

    public String getModBC() {
        return modBC;
    }

    public void setModBC(String modBC) {
        this.modBC = modBC;
    }

    public BigDecimal getPRedBC() {
        return pRedBC;
    }

    public void setPRedBC(BigDecimal pRedBC) {
        this.pRedBC = pRedBC;
    }

    public BigDecimal getPICMS() {
        return pICMS;
    }

    public void setPICMS(BigDecimal pICMS) {
        this.pICMS = pICMS;
    }

    public BigDecimal getPRedBCST() {
        return pRedBCST;
    }

    public void setPRedBCST(BigDecimal pRedBCST) {
        this.pRedBCST = pRedBCST;
    }

    public BigDecimal getPMVAST() {
        return pMVAST;
    }

    public void setPMVAST(BigDecimal pMVAST) {
        this.pMVAST = pMVAST;
    }

    public BigDecimal getPICMSST() {
        return pICMSST;
    }

    public void setPICMSST(BigDecimal pICMSST) {
        this.pICMSST = pICMSST;
    }

    public String getDecreto() {
        return decreto;
    }

    public void setDecreto(String decreto) {
        this.decreto = decreto;
    }

    public String getModBCST() {
        return modBCST;
    }

    public void setModBCST(String modBCST) {
        this.modBCST = modBCST;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTributacao != null ? idTributacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysProdutosTTributacao)) {
            return false;
        }
        JsysProdutosTTributacao other = (JsysProdutosTTributacao) object;
        return !((this.idTributacao == null && other.idTributacao != null) || (this.idTributacao != null && !this.idTributacao.equals(other.idTributacao)));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysProdutosTTributacao[ idTributacao=" + idTributacao + " ]";
    }
    
}
