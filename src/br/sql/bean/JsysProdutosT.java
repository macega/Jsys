/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.bean;

import br.JavaApplicationJsys;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysProdutosT")
@NamedQueries({
    @NamedQuery(name = "JsysProdutosT.findAll", query = "SELECT j FROM JsysProdutosT j"),
    @NamedQuery(name = "JsysProdutosT.findByIdProduto", query = "SELECT j FROM JsysProdutosT j WHERE j.idProduto = :idProduto"),
    @NamedQuery(name = "JsysProdutosT.findByIdProdutoAndInativo", query = "SELECT j FROM JsysProdutosT j WHERE j.idProduto = :idProduto AND j.inativo = FALSE"),
    @NamedQuery(name = "JsysProdutosT.findByNomeProduto", query = "SELECT j FROM JsysProdutosT j WHERE j.nomeProduto = :nomeProduto"),
    @NamedQuery(name = "JsysProdutosT.findByPrincipioAtivo", query = "SELECT j FROM JsysProdutosT j WHERE j.principioAtivo = :principioAtivo"),
    @NamedQuery(name = "JsysProdutosT.findByIdFamilia", query = "SELECT j FROM JsysProdutosT j WHERE j.idFamilia = :idFamilia"),
    @NamedQuery(name = "JsysProdutosT.findByIdGrupo", query = "SELECT j FROM JsysProdutosT j WHERE j.idGrupo = :idGrupo"),
    @NamedQuery(name = "JsysProdutosT.findByIdFabricante", query = "SELECT j FROM JsysProdutosT j WHERE j.idFabricante = :idFabricante"),
    @NamedQuery(name = "JsysProdutosT.findByMarca", query = "SELECT j FROM JsysProdutosT j WHERE j.marca = :marca"),
    @NamedQuery(name = "JsysProdutosT.findByUnidadeCompra", query = "SELECT j FROM JsysProdutosT j WHERE j.unidadeCompra = :unidadeCompra"),
    @NamedQuery(name = "JsysProdutosT.findByUnidadeVenda", query = "SELECT j FROM JsysProdutosT j WHERE j.unidadeVenda = :unidadeVenda"),
    @NamedQuery(name = "JsysProdutosT.findByReferencia", query = "SELECT j FROM JsysProdutosT j WHERE j.referencia = :referencia"),
    @NamedQuery(name = "JsysProdutosT.findByOrigemFiscal", query = "SELECT j FROM JsysProdutosT j WHERE j.origemFiscal = :origemFiscal"),
    @NamedQuery(name = "JsysProdutosT.findByTipoIcms", query = "SELECT j FROM JsysProdutosT j WHERE j.tipoIcms = :tipoIcms"),
    @NamedQuery(name = "JsysProdutosT.findByIcmsEstadual", query = "SELECT j FROM JsysProdutosT j WHERE j.icmsEstadual = :icmsEstadual"),
    @NamedQuery(name = "JsysProdutosT.findByIcmsInterestadual", query = "SELECT j FROM JsysProdutosT j WHERE j.icmsInterestadual = :icmsInterestadual"),
    @NamedQuery(name = "JsysProdutosT.findByCfopEstadual", query = "SELECT j FROM JsysProdutosT j WHERE j.cfopEstadual = :cfopEstadual"),
    @NamedQuery(name = "JsysProdutosT.findByCfopInterestadual", query = "SELECT j FROM JsysProdutosT j WHERE j.cfopInterestadual = :cfopInterestadual"),
    @NamedQuery(name = "JsysProdutosT.findByPrecoCompra", query = "SELECT j FROM JsysProdutosT j WHERE j.precoCompra = :precoCompra"),
    @NamedQuery(name = "JsysProdutosT.findByPrecoFrete", query = "SELECT j FROM JsysProdutosT j WHERE j.precoFrete = :precoFrete"),
    @NamedQuery(name = "JsysProdutosT.findByPrecoEncargos", query = "SELECT j FROM JsysProdutosT j WHERE j.precoEncargos = :precoEncargos"),
    @NamedQuery(name = "JsysProdutosT.findByPrecoCusto", query = "SELECT j FROM JsysProdutosT j WHERE j.precoCusto = :precoCusto"),
    @NamedQuery(name = "JsysProdutosT.findByPrecoIpi", query = "SELECT j FROM JsysProdutosT j WHERE j.precoIpi = :precoIpi"),
    @NamedQuery(name = "JsysProdutosT.findByPrecoCreditoIcms", query = "SELECT j FROM JsysProdutosT j WHERE j.precoCreditoIcms = :precoCreditoIcms"),
    @NamedQuery(name = "JsysProdutosT.findByPrecoDebitoIcms", query = "SELECT j FROM JsysProdutosT j WHERE j.precoDebitoIcms = :precoDebitoIcms"),
    @NamedQuery(name = "JsysProdutosT.findByPrecoOutrosImpostos", query = "SELECT j FROM JsysProdutosT j WHERE j.precoOutrosImpostos = :precoOutrosImpostos"),
    @NamedQuery(name = "JsysProdutosT.findByPrecoComissao", query = "SELECT j FROM JsysProdutosT j WHERE j.precoComissao = :precoComissao"),
    @NamedQuery(name = "JsysProdutosT.findByPrecoDespesasOperacional", query = "SELECT j FROM JsysProdutosT j WHERE j.precoDespesasOperacional = :precoDespesasOperacional"),
    @NamedQuery(name = "JsysProdutosT.findByPrecoAgregado", query = "SELECT j FROM JsysProdutosT j WHERE j.precoAgregado = :precoAgregado"),
    @NamedQuery(name = "JsysProdutosT.findByMargemLucro", query = "SELECT j FROM JsysProdutosT j WHERE j.margemLucro = :margemLucro"),
    @NamedQuery(name = "JsysProdutosT.findByEstMinimo", query = "SELECT j FROM JsysProdutosT j WHERE j.estMinimo = :estMinimo"),
    @NamedQuery(name = "JsysProdutosT.findByEstMaximo", query = "SELECT j FROM JsysProdutosT j WHERE j.estMaximo = :estMaximo"),
    @NamedQuery(name = "JsysProdutosT.findByEstoqueGeral", query = "SELECT j FROM JsysProdutosT j WHERE j.estoqueGeral = :estoqueGeral"),
    @NamedQuery(name = "JsysProdutosT.findByAliquotaCupom", query = "SELECT j FROM JsysProdutosT j WHERE j.aliquotaCupom = :aliquotaCupom"),
    @NamedQuery(name = "JsysProdutosT.findByAliquotaFora", query = "SELECT j FROM JsysProdutosT j WHERE j.aliquotaFora = :aliquotaFora"),
    @NamedQuery(name = "JsysProdutosT.findByInativo", query = "SELECT j FROM JsysProdutosT j WHERE j.inativo = :inativo"),
    @NamedQuery(name = "JsysProdutosT.findByDataInclusao", query = "SELECT j FROM JsysProdutosT j WHERE j.dataInclusao = :dataInclusao"),
    @NamedQuery(name = "JsysProdutosT.findByUsuarioInclusao", query = "SELECT j FROM JsysProdutosT j WHERE j.usuarioInclusao = :usuarioInclusao"),
    @NamedQuery(name = "JsysProdutosT.findByDataAlteracao", query = "SELECT j FROM JsysProdutosT j WHERE j.dataAlteracao = :dataAlteracao"),
    @NamedQuery(name = "JsysProdutosT.findByUsuarioAlteracao", query = "SELECT j FROM JsysProdutosT j WHERE j.usuarioAlteracao = :usuarioAlteracao"),
    @NamedQuery(name = "JsysProdutosT.findByPeso", query = "SELECT j FROM JsysProdutosT j WHERE j.peso = :peso"),
    @NamedQuery(name = "JsysProdutosT.findByProdLocaliza", query = "SELECT j FROM JsysProdutosT j WHERE j.prodLocaliza = :prodLocaliza"),
    @NamedQuery(name = "JsysProdutosT.findByComissaoVista", query = "SELECT j FROM JsysProdutosT j WHERE j.comissaoVista = :comissaoVista"),
    @NamedQuery(name = "JsysProdutosT.findByComissaoPrazo", query = "SELECT j FROM JsysProdutosT j WHERE j.comissaoPrazo = :comissaoPrazo"),
    @NamedQuery(name = "JsysProdutosT.findByNCM", query = "SELECT j FROM JsysProdutosT j WHERE j.NCM = :NCM"),
    @NamedQuery(name = "JsysProdutosT.findByModBC", query = "SELECT j FROM JsysProdutosT j WHERE j.modBC = :modBC"),
    @NamedQuery(name = "JsysProdutosT.findByModBCST", query = "SELECT j FROM JsysProdutosT j WHERE j.modBCST = :modBCST"),
    @NamedQuery(name = "JsysProdutosT.findByPisCST", query = "SELECT j FROM JsysProdutosT j WHERE j.pisCST = :pisCST"),
    @NamedQuery(name = "JsysProdutosT.findByCofinsCST", query = "SELECT j FROM JsysProdutosT j WHERE j.cofinsCST = :cofinsCST"),
    @NamedQuery(name = "JsysProdutosT.findByIpiCST", query = "SELECT j FROM JsysProdutosT j WHERE j.ipiCST = :ipiCST"),
    @NamedQuery(name = "JsysProdutosT.findByTipoItem", query = "SELECT j FROM JsysProdutosT j WHERE j.tipoItem = :tipoItem")})
public class JsysProdutosT implements Serializable {
    
    @OneToOne(optional = false)
    @JoinColumn(name = "idTributacao", referencedColumnName = "idTributacao")
    private JsysProdutosTTributacao idTributacao;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jsysProdutosT")
    private Collection<TransferenciasProdutosItens> transferenciasProdutosItensCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jsysProdutosT")
    private Collection<JsysOrcamentoItens> jsysOrcamentoItensCollection;

    @Transient
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idProduto")
    private Integer idProduto;

    @Basic(optional = false)
    @Column(name = "nomeProduto")
    private String nomeProduto;

    @Basic(optional = false)
    @Column(name = "principioAtivo")
    private String principioAtivo;

    @ManyToOne
    @JoinColumn(name = "idFamilia", referencedColumnName = "idFamilia")
    private JsysProdutosTFamilias idFamilia;

    @ManyToOne
    @JoinColumn(name = "idGrupo", referencedColumnName = "idGrupo")
    private JsysProdutosTGrupos idGrupo;

    @ManyToOne
    @JoinColumn(name = "idFabricante", referencedColumnName = "idFabricante")
    private JsysProdutosTFabricantes idFabricante;

    @Basic(optional = false)
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @Column(name = "unidadeCompra")
    private String unidadeCompra;
    @Basic(optional = false)
    @Column(name = "unidadeVenda")
    private String unidadeVenda;
    @Basic(optional = false)
    @Column(name = "referencia")
    private String referencia;
    @Basic(optional = false)
    @Column(name = "origemFiscal")
    private String origemFiscal;
    @Basic(optional = false)
    @Column(name = "tipoIcms")
    private String tipoIcms;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "icmsEstadual")
    private BigDecimal icmsEstadual;
    @Basic(optional = false)
    @Column(name = "icmsInterestadual")
    private BigDecimal icmsInterestadual;

    @Basic(optional = false)
    @Column(name = "cfopEstadual")
    private String cfopEstadual;
    @Basic(optional = false)
    @Column(name = "cfopInterestadual")
    private String cfopInterestadual;

    @Basic(optional = false)
    @Column(name = "cfopEstadualEntrada")
    private String cfopEstadualEntrada;
    @Basic(optional = false)
    @Column(name = "cfopInterestadualEntrada")
    private String cfopInterestadualEntrada;

    @Basic(optional = false)
    @Column(name = "precoCompra")
    private BigDecimal precoCompra;
    @Basic(optional = false)
    @Column(name = "precoFrete")
    private BigDecimal precoFrete;
    @Basic(optional = false)
    @Column(name = "precoEncargos")
    private BigDecimal precoEncargos;
    @Basic(optional = false)
    @Column(name = "precoCusto")
    private BigDecimal precoCusto;
    @Basic(optional = false)
    @Column(name = "precoIpi")
    private BigDecimal precoIpi;
    @Basic(optional = false)
    @Column(name = "precoCreditoIcms")
    private BigDecimal precoCreditoIcms;
    @Basic(optional = false)
    @Column(name = "precoDebitoIcms")
    private BigDecimal precoDebitoIcms;
    @Basic(optional = false)
    @Column(name = "precoOutrosImpostos")
    private BigDecimal precoOutrosImpostos;
    @Basic(optional = false)
    @Column(name = "precoComissao")
    private BigDecimal precoComissao;
    @Basic(optional = false)
    @Column(name = "precoDespesasOperacional")
    private BigDecimal precoDespesasOperacional;
    @Basic(optional = false)
    @Column(name = "precoAgregado")
    private BigDecimal precoAgregado;
    @Basic(optional = false)
    @Column(name = "margemLucro")
    private BigDecimal margemLucro;
    @Basic(optional = false)
    @Column(name = "estMinimo")
    private BigDecimal estMinimo;
    @Basic(optional = false)
    @Column(name = "estMaximo")
    private BigDecimal estMaximo;
    @Basic(optional = false)
    @Column(name = "estoqueGeral")
    private BigDecimal estoqueGeral;
    @Basic(optional = false)
    @Column(name = "aliquotaCupom")
    private String aliquotaCupom;
    @Basic(optional = false)
    @Column(name = "aliquotaFora")
    private String aliquotaFora;
    @Basic(optional = false)
    @Lob
    @Column(name = "obs")
    private String obs;
    @Basic(optional = false)
    @Column(name = "inativo")
    private boolean inativo;
    @Column(name = "dataInclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;
    @Basic(optional = false)
    @Column(name = "usuarioInclusao")
    private String usuarioInclusao;
    @Column(name = "dataAlteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @Basic(optional = true)
    @Column(name = "usuarioAlteracao")
    private String usuarioAlteracao;
    @Basic(optional = false)
    @Column(name = "peso")
    private BigDecimal peso;
    @Basic(optional = false)
    @Column(name = "prodLocaliza")
    private String prodLocaliza;
    @Basic(optional = false)
    @Column(name = "comissaoVista")
    private BigDecimal comissaoVista;
    @Basic(optional = false)
    @Column(name = "comissaoPrazo")
    private BigDecimal comissaoPrazo;
    @Basic(optional = false)
    @Column(name = "NCM")
    private String NCM;
    @Basic(optional = false)
    @Column(name = "modBC")
    private String modBC;
    @Basic(optional = false)
    @Column(name = "modBCST")
    private String modBCST;
    @Basic(optional = false)
    @Column(name = "pisCST")
    private String pisCST;
    @Basic(optional = false)
    @Column(name = "cofinsCST")
    private String cofinsCST;
    @Basic(optional = false)
    @Column(name = "ipiCST")
    private String ipiCST;
    @Basic(optional = false)
    @Column(name = "tipoItem")
    private String tipoItem;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jsysProdutosT")
    private List<JsysProdutosTPrecos> jsysProdutosTPrecosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jsysProdutosT")
    private Collection<AjusteEstoqueItens> ajusteEstoqueItensCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jsysProdutosT")
    private Collection<JsysProdutosTBarra> jsysProdutosTBarraCollection;

    public JsysProdutosT() {
    }

    public JsysProdutosT(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public JsysProdutosT(Integer idProduto, String nomeProduto, String principioAtivo, JsysProdutosTFamilias idFamilia, JsysProdutosTGrupos idGrupo, JsysProdutosTFabricantes idFabricante, String marca, String unidadeCompra, String unidadeVenda, String referencia, String origemFiscal, String tipoIcms, BigDecimal icmsEstadual, BigDecimal icmsInterestadual, String cfopEstadual, String cfopInterestadual, BigDecimal precoCompra, BigDecimal precoFrete, BigDecimal precoEncargos, BigDecimal precoCusto, BigDecimal precoIpi, BigDecimal precoCreditoIcms, BigDecimal precoDebitoIcms, BigDecimal precoOutrosImpostos, BigDecimal precoComissao, BigDecimal precoDespesasOperacional, BigDecimal precoAgregado, BigDecimal margemLucro, BigDecimal estMinimo, BigDecimal estMaximo, BigDecimal estoqueGeral, String aliquotaCupom, String aliquotaFora, String obs, boolean inativo, String usuarioInclusao, String usuarioAlteracao, BigDecimal peso, String prodLocaliza, BigDecimal comissaoVista, BigDecimal comissaoPrazo, String NCM, String modBC, String modBCST, String pisCST, String cofinsCST, String ipiCST, String tipoItem) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.principioAtivo = principioAtivo;
        this.idFamilia = idFamilia;
        this.idGrupo = idGrupo;
        this.idFabricante = idFabricante;
        this.marca = marca;
        this.unidadeCompra = unidadeCompra;
        this.unidadeVenda = unidadeVenda;
        this.referencia = referencia;
        this.origemFiscal = origemFiscal;
        this.tipoIcms = tipoIcms;
        this.icmsEstadual = icmsEstadual;
        this.icmsInterestadual = icmsInterestadual;
        this.cfopEstadual = cfopEstadual;
        this.cfopInterestadual = cfopInterestadual;
        this.precoCompra = precoCompra;
        this.precoFrete = precoFrete;
        this.precoEncargos = precoEncargos;
        this.precoCusto = precoCusto;
        this.precoIpi = precoIpi;
        this.precoCreditoIcms = precoCreditoIcms;
        this.precoDebitoIcms = precoDebitoIcms;
        this.precoOutrosImpostos = precoOutrosImpostos;
        this.precoComissao = precoComissao;
        this.precoDespesasOperacional = precoDespesasOperacional;
        this.precoAgregado = precoAgregado;
        this.margemLucro = margemLucro;
        this.estMinimo = estMinimo;
        this.estMaximo = estMaximo;
        this.estoqueGeral = estoqueGeral;
        this.aliquotaCupom = aliquotaCupom;
        this.aliquotaFora = aliquotaFora;
        this.obs = obs;
        this.inativo = inativo;
        this.usuarioInclusao = usuarioInclusao;
        this.usuarioAlteracao = usuarioAlteracao;
        this.peso = peso;
        this.prodLocaliza = prodLocaliza;
        this.comissaoVista = comissaoVista;
        this.comissaoPrazo = comissaoPrazo;
        this.NCM = NCM;
        this.modBC = modBC;
        this.modBCST = modBCST;
        this.pisCST = pisCST;
        this.cofinsCST = cofinsCST;
        this.ipiCST = ipiCST;
        this.tipoItem = tipoItem;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        Integer oldIdProduto = this.idProduto;
        this.idProduto = idProduto;
        changeSupport.firePropertyChange("idProduto", oldIdProduto, idProduto);
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        String oldNomeProduto = this.nomeProduto;
        this.nomeProduto = nomeProduto;
        changeSupport.firePropertyChange("nomeProduto", oldNomeProduto, nomeProduto);
    }

    public String getPrincipioAtivo() {
        return principioAtivo;
    }

    public void setPrincipioAtivo(String principioAtivo) {
        String oldPrincipioAtivo = this.principioAtivo;
        this.principioAtivo = principioAtivo;
        changeSupport.firePropertyChange("principioAtivo", oldPrincipioAtivo, principioAtivo);
    }

    public JsysProdutosTFamilias getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(JsysProdutosTFamilias idFamilia) {
        JsysProdutosTFamilias oldIdFamilia = this.idFamilia;
        this.idFamilia = idFamilia;
        changeSupport.firePropertyChange("idFamilia", oldIdFamilia, idFamilia);
    }

    public JsysProdutosTGrupos getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(JsysProdutosTGrupos idGrupo) {
        JsysProdutosTGrupos oldIdGrupo = this.idGrupo;
        this.idGrupo = idGrupo;
        changeSupport.firePropertyChange("idGrupo", oldIdGrupo, idGrupo);
    }

    public JsysProdutosTFabricantes getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(JsysProdutosTFabricantes idFabricante) {
        JsysProdutosTFabricantes oldIdFabricante = this.idFabricante;
        this.idFabricante = idFabricante;
        changeSupport.firePropertyChange("idFabricante", oldIdFabricante, idFabricante);
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        String oldMarca = this.marca;
        this.marca = marca;
        changeSupport.firePropertyChange("marca", oldMarca, marca);
    }

    public String getUnidadeCompra() {
        return unidadeCompra;
    }

    public void setUnidadeCompra(String unidadeCompra) {
        String oldUnidadeCompra = this.unidadeCompra;
        this.unidadeCompra = unidadeCompra;
        changeSupport.firePropertyChange("unidadeCompra", oldUnidadeCompra, unidadeCompra);
    }

    public String getUnidadeVenda() {
        return unidadeVenda;
    }

    public void setUnidadeVenda(String unidadeVenda) {
        String oldUnidadeVenda = this.unidadeVenda;
        this.unidadeVenda = unidadeVenda;
        changeSupport.firePropertyChange("unidadeVenda", oldUnidadeVenda, unidadeVenda);
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        String oldReferencia = this.referencia;
        this.referencia = referencia;
        changeSupport.firePropertyChange("referencia", oldReferencia, referencia);
    }

    public String getOrigemFiscal() {
        return origemFiscal;
    }

    public void setOrigemFiscal(String origemFiscal) {
        String oldOrigemFiscal = this.origemFiscal;
        this.origemFiscal = origemFiscal;
        changeSupport.firePropertyChange("origemFiscal", oldOrigemFiscal, origemFiscal);
    }

    public String getTipoIcms() {
        return tipoIcms;
    }

    public void setTipoIcms(String tipoIcms) {
        String oldTipoIcms = this.tipoIcms;
        this.tipoIcms = tipoIcms;
        changeSupport.firePropertyChange("tipoIcms", oldTipoIcms, tipoIcms);
    }

    public BigDecimal getIcmsEstadual() {
        return icmsEstadual;
    }

    public void setIcmsEstadual(BigDecimal icmsEstadual) {
        BigDecimal oldIcmsEstadual = this.icmsEstadual;
        this.icmsEstadual = icmsEstadual;
        changeSupport.firePropertyChange("icmsEstadual", oldIcmsEstadual, icmsEstadual);
    }

    public BigDecimal getIcmsInterestadual() {
        return icmsInterestadual;
    }

    public void setIcmsInterestadual(BigDecimal icmsInterestadual) {
        BigDecimal oldIcmsInterestadual = this.icmsInterestadual;
        this.icmsInterestadual = icmsInterestadual;
        changeSupport.firePropertyChange("icmsInterestadual", oldIcmsInterestadual, icmsInterestadual);
    }

    public String getCfopEstadual() {
        return cfopEstadual;
    }

    public void setCfopEstadual(String cfopEstadual) {
        String oldCfopEstadual = this.cfopEstadual;
        this.cfopEstadual = cfopEstadual;
        changeSupport.firePropertyChange("cfopEstadual", oldCfopEstadual, cfopEstadual);
    }

    public String getCfopInterestadual() {
        return cfopInterestadual;
    }

    public void setCfopInterestadual(String cfopInterestadual) {
        String oldCfopInterestadual = this.cfopInterestadual;
        this.cfopInterestadual = cfopInterestadual;
        changeSupport.firePropertyChange("cfopInterestadual", oldCfopInterestadual, cfopInterestadual);
    }

    public String getCfopEstadualEntrada() {
        return cfopEstadualEntrada;
    }

    public void setCfopEstadualEntrada(String cfopEstadualEntrada) {
        String oldCfopEstadualEntrada = this.cfopEstadualEntrada;
        this.cfopEstadualEntrada = cfopEstadualEntrada;
        changeSupport.firePropertyChange("cfopEstadualEntrada", oldCfopEstadualEntrada, cfopEstadualEntrada);
    }

    public String getCfopInterestadualEntrada() {
        return cfopInterestadualEntrada;
    }

    public void setCfopInterestadualEntrada(String cfopInterestadualEntrada) {
        String oldCfopInterestadualEntrada = this.cfopInterestadualEntrada;
        this.cfopInterestadualEntrada = cfopInterestadualEntrada;
        changeSupport.firePropertyChange("cfopInterestadualEntrada", oldCfopInterestadualEntrada, cfopInterestadualEntrada);
    }

    public BigDecimal getPrecoCompra() {
        return precoCompra.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_COMPRA, RoundingMode.HALF_UP);
    }

    public void setPrecoCompra(BigDecimal precoCompra) {
        BigDecimal oldPrecoCompra = this.precoCompra;
        this.precoCompra = precoCompra;
        changeSupport.firePropertyChange("precoCompra", oldPrecoCompra, precoCompra);
    }

    public BigDecimal getPrecoFrete() {
        return precoFrete.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setPrecoFrete(BigDecimal precoFrete) {
        BigDecimal oldPrecoFrete = this.precoFrete;
        this.precoFrete = precoFrete;
        changeSupport.firePropertyChange("precoFrete", oldPrecoFrete, precoFrete);
    }

    public BigDecimal getPrecoEncargos() {
        return precoEncargos.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setPrecoEncargos(BigDecimal precoEncargos) {
        BigDecimal oldPrecoEncargos = this.precoEncargos;
        this.precoEncargos = precoEncargos;
        changeSupport.firePropertyChange("precoEncargos", oldPrecoEncargos, precoEncargos);
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        BigDecimal oldPrecoCusto = this.precoCusto;
        this.precoCusto = precoCusto;
        changeSupport.firePropertyChange("precoCusto", oldPrecoCusto, precoCusto);
    }

    public BigDecimal getPrecoIpi() {
        return precoIpi.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setPrecoIpi(BigDecimal precoIpi) {
        BigDecimal oldPrecoIpi = this.precoIpi;
        this.precoIpi = precoIpi;
        changeSupport.firePropertyChange("precoIpi", oldPrecoIpi, precoIpi);
    }

    public BigDecimal getPrecoCreditoIcms() {
        return precoCreditoIcms.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setPrecoCreditoIcms(BigDecimal precoCreditoIcms) {
        BigDecimal oldPrecoCreditoIcms = this.precoCreditoIcms;
        this.precoCreditoIcms = precoCreditoIcms;
        changeSupport.firePropertyChange("precoCreditoIcms", oldPrecoCreditoIcms, precoCreditoIcms);
    }

    public BigDecimal getPrecoDebitoIcms() {
        return precoDebitoIcms.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setPrecoDebitoIcms(BigDecimal precoDebitoIcms) {
        BigDecimal oldPrecoDebitoIcms = this.precoDebitoIcms;
        this.precoDebitoIcms = precoDebitoIcms;
        changeSupport.firePropertyChange("precoDebitoIcms", oldPrecoDebitoIcms, precoDebitoIcms);
    }

    public BigDecimal getPrecoOutrosImpostos() {
        return precoOutrosImpostos.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setPrecoOutrosImpostos(BigDecimal precoOutrosImpostos) {
        BigDecimal oldPrecoOutrosImpostos = this.precoOutrosImpostos;
        this.precoOutrosImpostos = precoOutrosImpostos;
        changeSupport.firePropertyChange("precoOutrosImpostos", oldPrecoOutrosImpostos, precoOutrosImpostos);
    }

    public BigDecimal getPrecoComissao() {
        return precoComissao.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setPrecoComissao(BigDecimal precoComissao) {
        BigDecimal oldPrecoComissao = this.precoComissao;
        this.precoComissao = precoComissao;
        changeSupport.firePropertyChange("precoComissao", oldPrecoComissao, precoComissao);
    }

    public BigDecimal getPrecoDespesasOperacional() {
        return precoDespesasOperacional.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setPrecoDespesasOperacional(BigDecimal precoDespesasOperacional) {
        BigDecimal oldPrecoDespesasOperacional = this.precoDespesasOperacional;
        this.precoDespesasOperacional = precoDespesasOperacional;
        changeSupport.firePropertyChange("precoDespesasOperacional", oldPrecoDespesasOperacional, precoDespesasOperacional);
    }

    public BigDecimal getPrecoAgregado() {
        return precoAgregado.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setPrecoAgregado(BigDecimal precoAgregado) {
        BigDecimal oldPrecoAgregado = this.precoAgregado;
        this.precoAgregado = precoAgregado;
        changeSupport.firePropertyChange("precoAgregado", oldPrecoAgregado, precoAgregado);
    }

    public BigDecimal getMargemLucro() {
        return margemLucro.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setMargemLucro(BigDecimal margemLucro) {
        BigDecimal oldMargemLucro = this.margemLucro;
        this.margemLucro = margemLucro;
        changeSupport.firePropertyChange("margemLucro", oldMargemLucro, margemLucro);
    }

    public BigDecimal getEstMinimo() {
        return estMinimo.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setEstMinimo(BigDecimal estMinimo) {
        BigDecimal oldEstMinimo = this.estMinimo;
        this.estMinimo = estMinimo;
        changeSupport.firePropertyChange("estMinimo", oldEstMinimo, estMinimo);
    }

    public BigDecimal getEstMaximo() {
        return estMaximo.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setEstMaximo(BigDecimal estMaximo) {
        BigDecimal oldEstMaximo = this.estMaximo;
        this.estMaximo = estMaximo;
        changeSupport.firePropertyChange("estMaximo", oldEstMaximo, estMaximo);
    }

    public BigDecimal getEstoqueGeral() {
        return estoqueGeral.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setEstoqueGeral(BigDecimal estoqueGeral) {
        BigDecimal oldEstoqueGeral = this.estoqueGeral;
        this.estoqueGeral = estoqueGeral;
        changeSupport.firePropertyChange("estoqueGeral", oldEstoqueGeral, estoqueGeral);
    }

    public String getAliquotaCupom() {
        return aliquotaCupom;
    }

    public void setAliquotaCupom(String aliquotaCupom) {
        String oldAliquotaCupom = this.aliquotaCupom;
        this.aliquotaCupom = aliquotaCupom;
        changeSupport.firePropertyChange("aliquotaCupom", oldAliquotaCupom, aliquotaCupom);
    }

    public String getAliquotaFora() {
        return aliquotaFora;
    }

    public void setAliquotaFora(String aliquotaFora) {
        String oldAliquotaFora = this.aliquotaFora;
        this.aliquotaFora = aliquotaFora;
        changeSupport.firePropertyChange("aliquotaFora", oldAliquotaFora, aliquotaFora);
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        String oldObs = this.obs;
        this.obs = obs;
        changeSupport.firePropertyChange("obs", oldObs, obs);
    }

    public boolean getInativo() {
        return inativo;
    }

    public void setInativo(boolean inativo) {
        boolean oldInativo = this.inativo;
        this.inativo = inativo;
        changeSupport.firePropertyChange("inativo", oldInativo, inativo);
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        Date oldDataInclusao = this.dataInclusao;
        this.dataInclusao = dataInclusao;
        changeSupport.firePropertyChange("dataInclusao", oldDataInclusao, dataInclusao);
    }

    public String getUsuarioInclusao() {
        return usuarioInclusao;
    }

    public void setUsuarioInclusao(String usuarioInclusao) {
        String oldUsuarioInclusao = this.usuarioInclusao;
        this.usuarioInclusao = usuarioInclusao;
        changeSupport.firePropertyChange("usuarioInclusao", oldUsuarioInclusao, usuarioInclusao);
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        Date oldDataAlteracao = this.dataAlteracao;
        this.dataAlteracao = dataAlteracao;
        changeSupport.firePropertyChange("dataAlteracao", oldDataAlteracao, dataAlteracao);
    }

    public String getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(String usuarioAlteracao) {
        String oldUsuarioAlteracao = this.usuarioAlteracao;
        this.usuarioAlteracao = usuarioAlteracao;
        changeSupport.firePropertyChange("usuarioAlteracao", oldUsuarioAlteracao, usuarioAlteracao);
    }

    public BigDecimal getPeso() {
        return peso.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setPeso(BigDecimal peso) {
        BigDecimal oldPeso = this.peso;
        this.peso = peso;
        changeSupport.firePropertyChange("peso", oldPeso, peso);
    }

    public String getProdLocaliza() {
        return prodLocaliza;
    }

    public void setProdLocaliza(String prodLocaliza) {
        String oldProdLocaliza = this.prodLocaliza;
        this.prodLocaliza = prodLocaliza;
        changeSupport.firePropertyChange("prodLocaliza", oldProdLocaliza, prodLocaliza);
    }

    public BigDecimal getComissaoVista() {
        return comissaoVista.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setComissaoVista(BigDecimal comissaoVista) {
        BigDecimal oldComissaoVista = this.comissaoVista;
        this.comissaoVista = comissaoVista;
        changeSupport.firePropertyChange("comissaoVista", oldComissaoVista, comissaoVista);
    }

    public BigDecimal getComissaoPrazo() {
        return comissaoPrazo.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setComissaoPrazo(BigDecimal comissaoPrazo) {
        BigDecimal oldComissaoPrazo = this.comissaoPrazo;
        this.comissaoPrazo = comissaoPrazo;
        changeSupport.firePropertyChange("comissaoPrazo", oldComissaoPrazo, comissaoPrazo);
    }

    public String getNCM() {
        return NCM;
    }

    public void setIdNCM(String NCM) {
        String oldNCM = this.NCM;
        this.NCM = NCM;
        changeSupport.firePropertyChange("idNCM", oldNCM, NCM);
    }

    public String getModBC() {
        return modBC;
    }

    public void setModBC(String modBC) {
        String oldModBC = this.modBC;
        this.modBC = modBC;
        changeSupport.firePropertyChange("modBC", oldModBC, modBC);
    }

    public String getModBCST() {
        return modBCST;
    }

    public void setModBCST(String modBCST) {
        String oldModBCST = this.modBCST;
        this.modBCST = modBCST;
        changeSupport.firePropertyChange("modBCST", oldModBCST, modBCST);
    }

    public String getPisCST() {
        return pisCST;
    }

    public void setPisCST(String pisCST) {
        String oldPisCST = this.pisCST;
        this.pisCST = pisCST;
        changeSupport.firePropertyChange("pisCST", oldPisCST, pisCST);
    }

    public String getCofinsCST() {
        return cofinsCST;
    }

    public void setCofinsCST(String cofinsCST) {
        String oldCofinsCST = this.cofinsCST;
        this.cofinsCST = cofinsCST;
        changeSupport.firePropertyChange("cofinsCST", oldCofinsCST, cofinsCST);
    }

    public String getIpiCST() {
        return ipiCST;
    }

    public void setIpiCST(String ipiCST) {
        String oldIpiCST = this.ipiCST;
        this.ipiCST = ipiCST;
        changeSupport.firePropertyChange("ipiCST", oldIpiCST, ipiCST);
    }

    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        String oldTipoItem = this.tipoItem;
        this.tipoItem = tipoItem;
        changeSupport.firePropertyChange("tipoItem", oldTipoItem, tipoItem);
    }

    public List<JsysProdutosTPrecos> getJsysProdutosTPrecosList() {
        return jsysProdutosTPrecosList;
    }

    public void setJsysProdutosTPrecosList(List<JsysProdutosTPrecos> jsysProdutosTPrecosList) {
        this.jsysProdutosTPrecosList = jsysProdutosTPrecosList;
    }

    public JsysProdutosTTributacao getIdTributacao() {
        return idTributacao;
    }

    public void setIdTributacao(JsysProdutosTTributacao idTributacao) {
        this.idTributacao = idTributacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduto != null ? idProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysProdutosT)) {
            return false;
        }
        JsysProdutosT other = (JsysProdutosT) object;
        return (this.idProduto != null || other.idProduto == null) && (this.idProduto == null || this.idProduto.equals(other.idProduto));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysProdutosT[ idProduto=" + idProduto + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    @XmlTransient
    public Collection<JsysProdutosTBarra> getJsysProdutosTBarraCollection() {
        return jsysProdutosTBarraCollection;
    }

    public void setJsysProdutosTBarraCollection(Collection<JsysProdutosTBarra> jsysProdutosTBarraCollection) {
        this.jsysProdutosTBarraCollection = jsysProdutosTBarraCollection;
    }

    @XmlTransient
    public Collection<AjusteEstoqueItens> getAjusteEstoqueItensCollection() {
        return ajusteEstoqueItensCollection;
    }

    public void setAjusteEstoqueItensCollection(Collection<AjusteEstoqueItens> ajusteEstoqueItensCollection) {
        this.ajusteEstoqueItensCollection = ajusteEstoqueItensCollection;
    }

    @XmlTransient
    public Collection<JsysOrcamentoItens> getJsysOrcamentoItensCollection() {
        return jsysOrcamentoItensCollection;
    }

    public void setJsysOrcamentoItensCollection(Collection<JsysOrcamentoItens> jsysOrcamentoItensCollection) {
        this.jsysOrcamentoItensCollection = jsysOrcamentoItensCollection;
    }

    @XmlTransient
    public Collection<TransferenciasProdutosItens> getTransferenciasProdutosItensCollection() {
        return transferenciasProdutosItensCollection;
    }

    public void setTransferenciasProdutosItensCollection(Collection<TransferenciasProdutosItens> transferenciasProdutosItensCollection) {
        this.transferenciasProdutosItensCollection = transferenciasProdutosItensCollection;
    }

}
