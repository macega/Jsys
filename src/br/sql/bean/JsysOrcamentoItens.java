package br.sql.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysOrcamentoItens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysOrcamentoItens.findAll", query = "SELECT j FROM JsysOrcamentoItens j")
    ,@NamedQuery(name = "JsysOrcamentoItens.findByIdOrcamento", query = "SELECT j FROM JsysOrcamentoItens j WHERE j.jsysOrcamentoItensPK.idOrcamento = :idOrcamento")
    ,@NamedQuery(name = "JsysOrcamentoItens.findByIdProduto", query = "SELECT j FROM JsysOrcamentoItens j WHERE j.jsysOrcamentoItensPK.idProduto = :idProduto")
    ,@NamedQuery(name = "JsysOrcamentoItens.findByQuantidade", query = "SELECT j FROM JsysOrcamentoItens j WHERE j.quantidade = :quantidade")
    ,@NamedQuery(name = "JsysOrcamentoItens.findByPrecoVenda", query = "SELECT j FROM JsysOrcamentoItens j WHERE j.precoVenda = :precoVenda")
    ,@NamedQuery(name = "JsysOrcamentoItens.findByTotalProduto", query = "SELECT j FROM JsysOrcamentoItens j WHERE j.totalProduto = :totalProduto")
    ,@NamedQuery(name = "JsysOrcamentoItens.findByDesconto", query = "SELECT j FROM JsysOrcamentoItens j WHERE j.desconto = :desconto")
    ,@NamedQuery(name = "JsysOrcamentoItens.findByPrecoCompra", query = "SELECT j FROM JsysOrcamentoItens j WHERE j.precoCompra = :precoCompra")
    ,@NamedQuery(name = "JsysOrcamentoItens.findByUnidadeVenda", query = "SELECT j FROM JsysOrcamentoItens j WHERE j.unidadeVenda = :unidadeVenda")
    ,@NamedQuery(name = "JsysOrcamentoItens.findByComissaoVista", query = "SELECT j FROM JsysOrcamentoItens j WHERE j.comissaoVista = :comissaoVista")
    ,@NamedQuery(name = "JsysOrcamentoItens.findByComissaoPrazo", query = "SELECT j FROM JsysOrcamentoItens j WHERE j.comissaoPrazo = :comissaoPrazo")
    ,@NamedQuery(name = "JsysOrcamentoItens.findByIdbarra", query = "SELECT j FROM JsysOrcamentoItens j WHERE j.idbarra = :idbarra")})
public class JsysOrcamentoItens implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JsysOrcamentoItensPK jsysOrcamentoItensPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "quantidade")
    private BigDecimal quantidade;
    @Basic(optional = false)
    @Column(name = "precoVenda")
    private BigDecimal precoVenda;
    @Basic(optional = false)
    @Column(name = "totalProduto")
    private BigDecimal totalProduto;
    @Basic(optional = false)
    @Column(name = "desconto")
    private BigDecimal desconto;
    @Basic(optional = false)
    @Column(name = "precoCompra")
    private BigDecimal precoCompra;
    @Basic(optional = false)
    @Column(name = "unidadeVenda")
    private String unidadeVenda;
    @Basic(optional = false)
    @Column(name = "comissaoVista")
    private BigDecimal comissaoVista;
    @Basic(optional = false)
    @Column(name = "comissaoPrazo")
    private BigDecimal comissaoPrazo;
    @Basic(optional = false)
    @Column(name = "idbarra")
    private String idbarra;
    @JoinColumn(name = "idProduto", referencedColumnName = "idProduto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private JsysProdutosT jsysProdutosT;
    @JoinColumn(name = "idOrcamento", referencedColumnName = "idOrcamento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private JsysOrcamento jsysOrcamento;
    @Basic(optional = false)
    @Column(name = "cfop")
    private String cfop;
    @Column(name = "idProdutoNfe")
    private Integer idProdutoNfe;
    @OneToOne(optional = false)
    @JoinColumn(name = "idTributacao", referencedColumnName = "idTributacao")
    private JsysProdutosTTributacao idTributacao;

    public JsysOrcamentoItens() {
    }

    public JsysOrcamentoItens(JsysOrcamentoItensPK jsysOrcamentoItensPK) {
        this.jsysOrcamentoItensPK = jsysOrcamentoItensPK;
    }

    public JsysOrcamentoItens(JsysOrcamentoItensPK jsysOrcamentoItensPK, BigDecimal quantidade, BigDecimal precoVenda, BigDecimal totalProduto, BigDecimal desconto, BigDecimal precoCompra, String unidadeVenda, BigDecimal comissaoVista, BigDecimal comissaoPrazo, String idbarra) {
        this.jsysOrcamentoItensPK = jsysOrcamentoItensPK;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
        this.totalProduto = totalProduto;
        this.desconto = desconto;
        this.precoCompra = precoCompra;
        this.unidadeVenda = unidadeVenda;
        this.comissaoVista = comissaoVista;
        this.comissaoPrazo = comissaoPrazo;
        this.idbarra = idbarra;
    }

    public JsysOrcamentoItens(int idOrcamento, int idProduto) {
        this.jsysOrcamentoItensPK = new JsysOrcamentoItensPK(idOrcamento, idProduto);
    }

    public JsysOrcamentoItensPK getJsysOrcamentoItensPK() {
        return jsysOrcamentoItensPK;
    }

    public void setJsysOrcamentoItensPK(JsysOrcamentoItensPK jsysOrcamentoItensPK) {
        this.jsysOrcamentoItensPK = jsysOrcamentoItensPK;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public BigDecimal getTotalProduto() {
        return totalProduto;
    }

    public void setTotalProduto(BigDecimal totalProduto) {
        this.totalProduto = totalProduto;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(BigDecimal precoCompra) {
        this.precoCompra = precoCompra;
    }

    public String getUnidadeVenda() {
        return unidadeVenda;
    }

    public void setUnidadeVenda(String unidadeVenda) {
        this.unidadeVenda = unidadeVenda;
    }

    public BigDecimal getComissaoVista() {
        return comissaoVista;
    }

    public void setComissaoVista(BigDecimal comissaoVista) {
        this.comissaoVista = comissaoVista;
    }

    public BigDecimal getComissaoPrazo() {
        return comissaoPrazo;
    }

    public void setComissaoPrazo(BigDecimal comissaoPrazo) {
        this.comissaoPrazo = comissaoPrazo;
    }

    public String getIdbarra() {
        return idbarra;
    }

    public void setIdbarra(String idbarra) {
        this.idbarra = idbarra;
    }

    public JsysProdutosT getJsysProdutosT() {
        return jsysProdutosT;
    }

    public void setJsysProdutosT(JsysProdutosT jsysProdutosT) {
        this.jsysProdutosT = jsysProdutosT;
    }

    public JsysOrcamento getJsysOrcamento() {
        return jsysOrcamento;
    }

    public void setJsysOrcamento(JsysOrcamento jsysOrcamento) {
        this.jsysOrcamento = jsysOrcamento;
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public Integer getIdProdutoNfe() {
        return idProdutoNfe;
    }

    public void setIdProdutoNfe(Integer idProdutoNfe) {
        this.idProdutoNfe = idProdutoNfe;
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
        hash += (jsysOrcamentoItensPK != null ? jsysOrcamentoItensPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysOrcamentoItens)) {
            return false;
        }
        JsysOrcamentoItens other = (JsysOrcamentoItens) object;
        return (this.jsysOrcamentoItensPK != null || other.jsysOrcamentoItensPK == null) && (this.jsysOrcamentoItensPK == null || this.jsysOrcamentoItensPK.equals(other.jsysOrcamentoItensPK));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysOrcamentoItens[ jsysOrcamentoItensPK=" + jsysOrcamentoItensPK + " ]";
    }
}
