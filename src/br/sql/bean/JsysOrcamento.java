/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sql.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "jsysOrcamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysOrcamento.findAll", query = "SELECT j FROM JsysOrcamento j"),
    @NamedQuery(name = "JsysOrcamento.findByVendasAbertasVendedor", query = "SELECT j FROM JsysOrcamento j WHERE j.idFuncionario = :idFuncionario and j.vendido = :vendido"),
    @NamedQuery(name = "JsysOrcamento.findByFechado", query = "SELECT j FROM JsysOrcamento j WHERE j.idOrcamento = :idOrcamento and j.vendido = TRUE and j.cancelado = FALSE"),
    @NamedQuery(name = "JsysOrcamento.findByIdOrcamento", query = "SELECT j FROM JsysOrcamento j WHERE j.idOrcamento = :idOrcamento"),
    
    @NamedQuery(name = "JsysOrcamento.findByData", query = "SELECT j FROM JsysOrcamento j WHERE j.data = :data"),
    @NamedQuery(name = "JsysOrcamento.findByIdCliente", query = "SELECT j FROM JsysOrcamento j WHERE j.idCliente = :idCliente"),
    @NamedQuery(name = "JsysOrcamento.findByIdFuncionario", query = "SELECT j FROM JsysOrcamento j WHERE j.idFuncionario = :idFuncionario"),
    @NamedQuery(name = "JsysOrcamento.findByValorBruto", query = "SELECT j FROM JsysOrcamento j WHERE j.valorBruto = :valorBruto"),
    @NamedQuery(name = "JsysOrcamento.findByValorAcrecimo", query = "SELECT j FROM JsysOrcamento j WHERE j.valorAcrecimo = :valorAcrecimo"),
    @NamedQuery(name = "JsysOrcamento.findByValorDesconto", query = "SELECT j FROM JsysOrcamento j WHERE j.valorDesconto = :valorDesconto"),
    @NamedQuery(name = "JsysOrcamento.findByValorLiquido", query = "SELECT j FROM JsysOrcamento j WHERE j.valorLiquido = :valorLiquido"),
    @NamedQuery(name = "JsysOrcamento.findByValorDevolvido", query = "SELECT j FROM JsysOrcamento j WHERE j.valorDevolvido = :valorDevolvido"),
    @NamedQuery(name = "JsysOrcamento.findByCancelado", query = "SELECT j FROM JsysOrcamento j WHERE j.cancelado = :cancelado"),
    @NamedQuery(name = "JsysOrcamento.findByDataCancelado", query = "SELECT j FROM JsysOrcamento j WHERE j.dataCancelado = :dataCancelado"),
    @NamedQuery(name = "JsysOrcamento.findByVendido", query = "SELECT j FROM JsysOrcamento j WHERE j.vendido = :vendido"),
    @NamedQuery(name = "JsysOrcamento.findByDataVendido", query = "SELECT j FROM JsysOrcamento j WHERE j.dataVendido = :dataVendido"),
    @NamedQuery(name = "JsysOrcamento.findByIdcaixa", query = "SELECT j FROM JsysOrcamento j WHERE j.idcaixa = :idcaixa"),
    @NamedQuery(name = "JsysOrcamento.findByIdTituloEntrada", query = "SELECT j FROM JsysOrcamento j WHERE j.idTituloEntrada = :idTituloEntrada"),
    @NamedQuery(name = "JsysOrcamento.findByIdTituloRestante", query = "SELECT j FROM JsysOrcamento j WHERE j.idTituloRestante = :idTituloRestante"),
    @NamedQuery(name = "JsysOrcamento.findByValorEntrada", query = "SELECT j FROM JsysOrcamento j WHERE j.valorEntrada = :valorEntrada"),
    @NamedQuery(name = "JsysOrcamento.findByValorRestante", query = "SELECT j FROM JsysOrcamento j WHERE j.valorRestante = :valorRestante"),
    @NamedQuery(name = "JsysOrcamento.findByFormaPagto", query = "SELECT j FROM JsysOrcamento j WHERE j.formaPagto = :formaPagto"),
    @NamedQuery(name = "JsysOrcamento.findByTipoVenda", query = "SELECT j FROM JsysOrcamento j WHERE j.tipoVenda = :tipoVenda"),
    @NamedQuery(name = "JsysOrcamento.findByDataInclusao", query = "SELECT j FROM JsysOrcamento j WHERE j.dataInclusao = :dataInclusao"),
    @NamedQuery(name = "JsysOrcamento.findByUsuarioInclusao", query = "SELECT j FROM JsysOrcamento j WHERE j.usuarioInclusao = :usuarioInclusao"),
    @NamedQuery(name = "JsysOrcamento.findByDataAlteracao", query = "SELECT j FROM JsysOrcamento j WHERE j.dataAlteracao = :dataAlteracao"),
    @NamedQuery(name = "JsysOrcamento.findByUsuarioAlteracao", query = "SELECT j FROM JsysOrcamento j WHERE j.usuarioAlteracao = :usuarioAlteracao"),
    @NamedQuery(name = "JsysOrcamento.findByReaberta", query = "SELECT j FROM JsysOrcamento j WHERE j.reaberta = :reaberta"),
    @NamedQuery(name = "JsysOrcamento.findByCup", query = "SELECT j FROM JsysOrcamento j WHERE j.cup = :cup")})
public class JsysOrcamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idOrcamento")
    private Integer idOrcamento;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    //@Basic(optional = false)
    //@Column(name = "idCliente")
    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private JsysClientes idCliente;
    //@Basic(optional = false)
    //@Column(name = "idFuncionario")
    @ManyToOne
    @JoinColumn(name = "idFuncionario", referencedColumnName = "idCliente")
    private JsysClientes idFuncionario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valorBruto")
    private BigDecimal valorBruto;
    @Basic(optional = false)
    @Column(name = "valorAcrecimo")
    private BigDecimal valorAcrecimo;
    @Basic(optional = false)
    @Column(name = "valorDesconto")
    private BigDecimal valorDesconto;
    @Basic(optional = false)
    @Column(name = "valorLiquido")
    private BigDecimal valorLiquido;
    @Basic(optional = false)
    @Column(name = "valorDevolvido")
    private BigDecimal valorDevolvido;
    @Basic(optional = false)
    @Column(name = "cancelado")
    private boolean cancelado;
    @Column(name = "dataCancelado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCancelado;
    @Basic(optional = false)
    @Column(name = "vendido")
    private boolean vendido;
    @Column(name = "dataVendido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVendido;
    @Column(name = "idcaixa")
    private Integer idcaixa;
    @Basic(optional = false)
    @Lob
    @Column(name = "obs")
    private String obs;
    @Basic(optional = false)
    @Column(name = "idTituloEntrada")
    private String idTituloEntrada;
    @Basic(optional = false)
    @Column(name = "idTituloRestante")
    private String idTituloRestante;
    @Basic(optional = false)
    @Column(name = "valorEntrada")
    private BigDecimal valorEntrada;
    @Basic(optional = false)
    @Column(name = "valorRestante")
    private BigDecimal valorRestante;
    @Basic(optional = false)
    @Column(name = "formaPagto")
    private String formaPagto;
    @Basic(optional = false)
    @Column(name = "tipoVenda")
    private String tipoVenda;
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
    @Basic(optional = false)
    @Column(name = "reaberta")
    private int reaberta;
    @Column(name = "cup")
    private String cup;
    @Basic(optional = false)
    @Column(name = "ficha")
    private int ficha;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jsysOrcamento")
    private Collection<JsysOrcamentoItens> jsysOrcamentoItensCollection;

    public JsysOrcamento() {
    }

    public JsysOrcamento(Integer idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public JsysOrcamento(Integer idOrcamento, Date data, JsysClientes idCliente, JsysClientes idFuncionario, BigDecimal valorBruto, BigDecimal valorAcrecimo, BigDecimal valorDesconto, BigDecimal valorLiquido, BigDecimal valorDevolvido, boolean cancelado, boolean vendido, String obs, String idTituloEntrada, String idTituloRestante, BigDecimal valorEntrada, BigDecimal valorRestante, String formaPagto, String tipoVenda, Date dataInclusao, String usuarioInclusao, int reaberta) {
        this.idOrcamento = idOrcamento;
        this.data = data;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
        this.valorBruto = valorBruto;
        this.valorAcrecimo = valorAcrecimo;
        this.valorDesconto = valorDesconto;
        this.valorLiquido = valorLiquido;
        this.valorDevolvido = valorDevolvido;
        this.cancelado = cancelado;
        this.vendido = vendido;
        this.obs = obs;
        this.idTituloEntrada = idTituloEntrada;
        this.idTituloRestante = idTituloRestante;
        this.valorEntrada = valorEntrada;
        this.valorRestante = valorRestante;
        this.formaPagto = formaPagto;
        this.tipoVenda = tipoVenda;
        this.dataInclusao = dataInclusao;
        this.usuarioInclusao = usuarioInclusao;
        this.reaberta = reaberta;
    }

    public Integer getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(Integer idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public JsysClientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(JsysClientes idCliente) {
        this.idCliente = idCliente;
    }

    public JsysClientes getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(JsysClientes idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public BigDecimal getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(BigDecimal valorBruto) {
        this.valorBruto = valorBruto;
    }

    public BigDecimal getValorAcrecimo() {
        return valorAcrecimo;
    }

    public void setValorAcrecimo(BigDecimal valorAcrecimo) {
        this.valorAcrecimo = valorAcrecimo;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public BigDecimal getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(BigDecimal valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public BigDecimal getValorDevolvido() {
        return valorDevolvido;
    }

    public void setValorDevolvido(BigDecimal valorDevolvido) {
        this.valorDevolvido = valorDevolvido;
    }

    public boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public Date getDataCancelado() {
        return dataCancelado;
    }

    public void setDataCancelado(Date dataCancelado) {
        this.dataCancelado = dataCancelado;
    }

    public boolean getVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    public Date getDataVendido() {
        return dataVendido;
    }

    public void setDataVendido(Date dataVendido) {
        this.dataVendido = dataVendido;
    }

    public Integer getIdcaixa() {
        return idcaixa;
    }

    public void setIdcaixa(Integer idcaixa) {
        this.idcaixa = idcaixa;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getIdTituloEntrada() {
        return idTituloEntrada;
    }

    public void setIdTituloEntrada(String idTituloEntrada) {
        this.idTituloEntrada = idTituloEntrada;
    }

    public String getIdTituloRestante() {
        return idTituloRestante;
    }

    public void setIdTituloRestante(String idTituloRestante) {
        this.idTituloRestante = idTituloRestante;
    }

    public BigDecimal getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(BigDecimal valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public BigDecimal getValorRestante() {
        return valorRestante;
    }

    public void setValorRestante(BigDecimal valorRestante) {
        this.valorRestante = valorRestante;
    }

    public String getFormaPagto() {
        return formaPagto;
    }

    public void setFormaPagto(String formaPagto) {
        this.formaPagto = formaPagto;
    }

    public String getTipoVenda() {
        return tipoVenda;
    }

    public void setTipoVenda(String tipoVenda) {
        this.tipoVenda = tipoVenda;
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

    public int getReaberta() {
        return reaberta;
    }

    public void setReaberta(int reaberta) {
        this.reaberta = reaberta;
    }

    public String getCup() {
        return cup;
    }

    public void setCup(String cup) {
        this.cup = cup;
    }

    public int getFicha() {
        return ficha;
    }

    public void setFicha(int ficha) {
        this.ficha = ficha;
    }

    @XmlTransient
    public Collection<JsysOrcamentoItens> getJsysOrcamentoItensCollection() {
        return jsysOrcamentoItensCollection;
    }

    public void setJsysOrcamentoItensCollection(Collection<JsysOrcamentoItens> jsysOrcamentoItensCollection) {
        this.jsysOrcamentoItensCollection = jsysOrcamentoItensCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrcamento != null ? idOrcamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysOrcamento)) {
            return false;
        }
        JsysOrcamento other = (JsysOrcamento) object;
        return (this.idOrcamento != null || other.idOrcamento == null) && (this.idOrcamento == null || this.idOrcamento.equals(other.idOrcamento));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysOrcamento[ idOrcamento=" + idOrcamento + " ]";
    }
    
}
