package br.sql.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysReceber")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysReceber.findAll", query = "SELECT j FROM JsysReceber j"),
    @NamedQuery(name = "JsysReceber.findByIdReceber", query = "SELECT j FROM JsysReceber j WHERE j.jsysReceberPK.idReceber = :idReceber"),
    @NamedQuery(name = "JsysReceber.findBySeqReceber", query = "SELECT j FROM JsysReceber j WHERE j.jsysReceberPK.seqReceber = :seqReceber"),
    @NamedQuery(name = "JsysReceber.findByIdloja", query = "SELECT j FROM JsysReceber j WHERE j.idloja = :idloja"),
    @NamedQuery(name = "JsysReceber.findByIdTitulo", query = "SELECT j FROM JsysReceber j WHERE j.idTitulo = :idTitulo"),
    @NamedQuery(name = "JsysReceber.findByDataEmissao", query = "SELECT j FROM JsysReceber j WHERE j.dataEmissao = :dataEmissao"),
    @NamedQuery(name = "JsysReceber.findByDataVencimento", query = "SELECT j FROM JsysReceber j WHERE j.dataVencimento = :dataVencimento"),
    @NamedQuery(name = "JsysReceber.findByValorTitulo", query = "SELECT j FROM JsysReceber j WHERE j.valorTitulo = :valorTitulo"),
    @NamedQuery(name = "JsysReceber.findByRestante", query = "SELECT j FROM JsysReceber j WHERE j.restante = :restante"),
    @NamedQuery(name = "JsysReceber.findByDescontos", query = "SELECT j FROM JsysReceber j WHERE j.descontos = :descontos"),
    @NamedQuery(name = "JsysReceber.findByDataCancelar", query = "SELECT j FROM JsysReceber j WHERE j.dataCancelar = :dataCancelar"),
    @NamedQuery(name = "JsysReceber.findByIdBanco", query = "SELECT j FROM JsysReceber j WHERE j.idBanco = :idBanco"),
    @NamedQuery(name = "JsysReceber.findByQuitado", query = "SELECT j FROM JsysReceber j WHERE j.quitado = :quitado"),
    @NamedQuery(name = "JsysReceber.findByIdContabil", query = "SELECT j FROM JsysReceber j WHERE j.idContabil = :idContabil"),
    @NamedQuery(name = "JsysReceber.findByFicha", query = "SELECT j FROM JsysReceber j WHERE j.ficha = :ficha"),
    @NamedQuery(name = "JsysReceber.findByDataInclusao", query = "SELECT j FROM JsysReceber j WHERE j.dataInclusao = :dataInclusao"),
    @NamedQuery(name = "JsysReceber.findByUsuarioInclusao", query = "SELECT j FROM JsysReceber j WHERE j.usuarioInclusao = :usuarioInclusao"),
    @NamedQuery(name = "JsysReceber.findByDataAlteracao", query = "SELECT j FROM JsysReceber j WHERE j.dataAlteracao = :dataAlteracao"),
    @NamedQuery(name = "JsysReceber.findByUsuarioAlteracao", query = "SELECT j FROM JsysReceber j WHERE j.usuarioAlteracao = :usuarioAlteracao")})
public class JsysReceber implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jsysReceber")
    private Collection<JsysReceberBaixa> jsysReceberBaixaCollection;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JsysReceberPK jsysReceberPK;
    @Basic(optional = false)
    @Column(name = "idloja")
    private String idloja;
    @Basic(optional = false)
    @Column(name = "idTitulo")
    private String idTitulo;
    @Column(name = "dataEmissao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEmissao;
    @Column(name = "dataVencimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVencimento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valorTitulo")
    private BigDecimal valorTitulo;
    @Basic(optional = false)
    @Column(name = "restante")
    private BigDecimal restante;
    @Basic(optional = false)
    @Column(name = "Descontos")
    private BigDecimal descontos;
    @Column(name = "dataCancelar")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCancelar;
    @Lob
    @Column(name = "obsCancelamento")
    private String obsCancelamento;
    @Column(name = "idBanco")
    private Integer idBanco;
    @Basic(optional = false)
    @Column(name = "quitado")
    private boolean quitado;
    @Basic(optional = false)
    @Column(name = "idContabil")
    private String idContabil;
    @Column(name = "ficha")
    private Integer ficha;
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
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false)
    private JsysClientes idCliente;

    public JsysReceber() {
    }

    public JsysReceber(JsysReceberPK jsysReceberPK) {
        this.jsysReceberPK = jsysReceberPK;
    }

    public JsysReceber(JsysReceberPK jsysReceberPK, String idloja, String idTitulo, BigDecimal valorTitulo, BigDecimal restante, BigDecimal descontos, boolean quitado, String idContabil, Date dataInclusao, String usuarioInclusao) {
        this.jsysReceberPK = jsysReceberPK;
        this.idloja = idloja;
        this.idTitulo = idTitulo;
        this.valorTitulo = valorTitulo;
        this.restante = restante;
        this.descontos = descontos;
        this.quitado = quitado;
        this.idContabil = idContabil;
        this.dataInclusao = dataInclusao;
        this.usuarioInclusao = usuarioInclusao;
    }

    public JsysReceber(Integer idReceber, int seqReceber) {
        this.jsysReceberPK = new JsysReceberPK(idReceber, seqReceber);
    }

    public JsysReceberPK getJsysReceberPK() {
        return jsysReceberPK;
    }

    public void setJsysReceberPK(JsysReceberPK jsysReceberPK) {
        this.jsysReceberPK = jsysReceberPK;
    }

    public String getIdloja() {
        return idloja;
    }

    public void setIdloja(String idloja) {
        this.idloja = idloja;
    }

    public String getIdTitulo() {
        return idTitulo;
    }

    public void setIdTitulo(String idTitulo) {
        this.idTitulo = idTitulo;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigDecimal getValorTitulo() {
        return valorTitulo;
    }

    public void setValorTitulo(BigDecimal valorTitulo) {
        this.valorTitulo = valorTitulo;
    }

    public BigDecimal getRestante() {
        return restante;
    }

    public void setRestante(BigDecimal restante) {
        this.restante = restante;
    }

    public BigDecimal getDescontos() {
        return descontos;
    }

    public void setDescontos(BigDecimal descontos) {
        this.descontos = descontos;
    }

    public Date getDataCancelar() {
        return dataCancelar;
    }

    public void setDataCancelar(Date dataCancelar) {
        this.dataCancelar = dataCancelar;
    }

    public String getObsCancelamento() {
        return obsCancelamento;
    }

    public void setObsCancelamento(String obsCancelamento) {
        this.obsCancelamento = obsCancelamento;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public boolean getQuitado() {
        return quitado;
    }

    public void setQuitado(boolean quitado) {
        this.quitado = quitado;
    }

    public String getIdContabil() {
        return idContabil;
    }

    public void setIdContabil(String idContabil) {
        this.idContabil = idContabil;
    }

    public Integer getFicha() {
        return ficha;
    }

    public void setFicha(Integer ficha) {
        this.ficha = ficha;
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

    public JsysClientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(JsysClientes idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jsysReceberPK != null ? jsysReceberPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysReceber)) {
            return false;
        }
        JsysReceber other = (JsysReceber) object;
        if ((this.jsysReceberPK == null && other.jsysReceberPK != null) || (this.jsysReceberPK != null && !this.jsysReceberPK.equals(other.jsysReceberPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysReceber[ jsysReceberPK=" + jsysReceberPK + " ]";
    }

    @XmlTransient
    public Collection<JsysReceberBaixa> getJsysReceberBaixaCollection() {
        return jsysReceberBaixaCollection;
    }

    public void setJsysReceberBaixaCollection(Collection<JsysReceberBaixa> jsysReceberBaixaCollection) {
        this.jsysReceberBaixaCollection = jsysReceberBaixaCollection;
    }
    
}
