package br.sql.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "jsysReceberBaixa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysReceberBaixa.findAll", query = "SELECT j FROM JsysReceberBaixa j"),
    
    @NamedQuery(name = "JsysReceberBaixa.findByIdReceberAndSeqAndId", query = "SELECT j FROM JsysReceberBaixa j WHERE j.jsysReceberBaixaPK.idReceber = :idReceber AND j.jsysReceberBaixaPK.seqReceber = :seqReceber AND j.jsysReceberBaixaPK.id = :id"),
    
    
    @NamedQuery(name = "JsysReceberBaixa.findByIdReceber", query = "SELECT j FROM JsysReceberBaixa j WHERE j.jsysReceberBaixaPK.idReceber = :idReceber"),
    @NamedQuery(name = "JsysReceberBaixa.findBySeqReceber", query = "SELECT j FROM JsysReceberBaixa j WHERE j.jsysReceberBaixaPK.seqReceber = :seqReceber"),
    @NamedQuery(name = "JsysReceberBaixa.findById", query = "SELECT j FROM JsysReceberBaixa j WHERE j.jsysReceberBaixaPK.id = :id"),
    @NamedQuery(name = "JsysReceberBaixa.findByIdloja", query = "SELECT j FROM JsysReceberBaixa j WHERE j.idloja = :idloja"),
    @NamedQuery(name = "JsysReceberBaixa.findByIdBanco", query = "SELECT j FROM JsysReceberBaixa j WHERE j.idBanco = :idBanco"),
    @NamedQuery(name = "JsysReceberBaixa.findByData", query = "SELECT j FROM JsysReceberBaixa j WHERE j.data = :data"),
    @NamedQuery(name = "JsysReceberBaixa.findByValor", query = "SELECT j FROM JsysReceberBaixa j WHERE j.valor = :valor"),
    @NamedQuery(name = "JsysReceberBaixa.findByRestante", query = "SELECT j FROM JsysReceberBaixa j WHERE j.restante = :restante"),
    @NamedQuery(name = "JsysReceberBaixa.findByValorJuros", query = "SELECT j FROM JsysReceberBaixa j WHERE j.valorJuros = :valorJuros"),
    @NamedQuery(name = "JsysReceberBaixa.findByIdTitulo", query = "SELECT j FROM JsysReceberBaixa j WHERE j.idTitulo = :idTitulo"),
    @NamedQuery(name = "JsysReceberBaixa.findByQuitado", query = "SELECT j FROM JsysReceberBaixa j WHERE j.quitado = :quitado"),
    @NamedQuery(name = "JsysReceberBaixa.findByDataInclusao", query = "SELECT j FROM JsysReceberBaixa j WHERE j.dataInclusao = :dataInclusao"),
    @NamedQuery(name = "JsysReceberBaixa.findByUsuarioInclusao", query = "SELECT j FROM JsysReceberBaixa j WHERE j.usuarioInclusao = :usuarioInclusao"),
    @NamedQuery(name = "JsysReceberBaixa.findByDataAlteracao", query = "SELECT j FROM JsysReceberBaixa j WHERE j.dataAlteracao = :dataAlteracao"),
    @NamedQuery(name = "JsysReceberBaixa.findByUsuarioAlteracao", query = "SELECT j FROM JsysReceberBaixa j WHERE j.usuarioAlteracao = :usuarioAlteracao")})
public class JsysReceberBaixa implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JsysReceberBaixaPK jsysReceberBaixaPK;
    @Basic(optional = false)
    @Column(name = "idloja")
    private String idloja;
    @Column(name = "idBanco")
    private Integer idBanco;
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @Column(name = "restante")
    private BigDecimal restante;
    @Basic(optional = false)
    @Column(name = "valorJuros")
    private BigDecimal valorJuros;
    @Basic(optional = false)
    @Column(name = "idTitulo")
    private String idTitulo;
    @Basic(optional = false)
    @Column(name = "quitado")
    private boolean quitado;
    @Basic(optional = false)
    @Column(name = "dataInclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;
    @Basic(optional = false)
    @Column(name = "usuarioInclusao")
    private String usuarioInclusao;
    @Column(name = "dataAlteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @Column(name = "usuarioAlteracao")
    private String usuarioAlteracao;
    @JoinColumns({
        @JoinColumn(name = "idReceber", referencedColumnName = "idReceber", insertable = false, updatable = false),
        @JoinColumn(name = "seqReceber", referencedColumnName = "seqReceber", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private JsysReceber jsysReceber;

    public JsysReceberBaixa() {
    }

    public JsysReceberBaixa(JsysReceberBaixaPK jsysReceberBaixaPK) {
        this.jsysReceberBaixaPK = jsysReceberBaixaPK;
    }

    public JsysReceberBaixa(JsysReceberBaixaPK jsysReceberBaixaPK, String idloja, BigDecimal valor, BigDecimal restante, BigDecimal valorJuros, String idTitulo, boolean quitado, Date dataInclusao, String usuarioInclusao) {
        this.jsysReceberBaixaPK = jsysReceberBaixaPK;
        this.idloja = idloja;
        this.valor = valor;
        this.restante = restante;
        this.valorJuros = valorJuros;
        this.idTitulo = idTitulo;
        this.quitado = quitado;
        this.dataInclusao = dataInclusao;
        this.usuarioInclusao = usuarioInclusao;
    }

    public JsysReceberBaixa(Integer idReceber, int seqReceber, int id) {
        this.jsysReceberBaixaPK = new JsysReceberBaixaPK(idReceber, seqReceber, id);
    }

    public JsysReceberBaixaPK getJsysReceberBaixaPK() {
        return jsysReceberBaixaPK;
    }

    public void setJsysReceberBaixaPK(JsysReceberBaixaPK jsysReceberBaixaPK) {
        this.jsysReceberBaixaPK = jsysReceberBaixaPK;
    }

    public String getIdloja() {
        return idloja;
    }

    public void setIdloja(String idloja) {
        this.idloja = idloja;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getRestante() {
        return restante;
    }

    public void setRestante(BigDecimal restante) {
        this.restante = restante;
    }

    public BigDecimal getValorJuros() {
        return valorJuros;
    }

    public void setValorJuros(BigDecimal valorJuros) {
        this.valorJuros = valorJuros;
    }

    public String getIdTitulo() {
        return idTitulo;
    }

    public void setIdTitulo(String idTitulo) {
        this.idTitulo = idTitulo;
    }

    public boolean getQuitado() {
        return quitado;
    }

    public void setQuitado(boolean quitado) {
        this.quitado = quitado;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public String getUsuarioInclusao() {
        return usuarioInclusao;
    }

    public void setUsuarioInclusao(String usuarioInclusao) {
        this.usuarioInclusao = usuarioInclusao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(String usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
    }

    public JsysReceber getJsysReceber() {
        return jsysReceber;
    }

    public void setJsysReceber(JsysReceber jsysReceber) {
        this.jsysReceber = jsysReceber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jsysReceberBaixaPK != null ? jsysReceberBaixaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysReceberBaixa)) {
            return false;
        }
        JsysReceberBaixa other = (JsysReceberBaixa) object;
        return !((this.jsysReceberBaixaPK == null && other.jsysReceberBaixaPK != null) || (this.jsysReceberBaixaPK != null && !this.jsysReceberBaixaPK.equals(other.jsysReceberBaixaPK)));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysReceberBaixa[ jsysReceberBaixaPK=" + jsysReceberBaixaPK + " ]";
    }
    
}
