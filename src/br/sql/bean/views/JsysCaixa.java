package br.sql.bean.views;

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
@Table(name = "jsysCaixa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysCaixa.findAll", query = "SELECT j FROM JsysCaixa j"),
    @NamedQuery(name = "JsysCaixa.findByTipoAndDataAndBanco", query = "SELECT j FROM JsysCaixa j WHERE j.tipo in ('1', '2', '3', '5', '6') and j.data = :data and j.idBanco = :idBanco order by j.hora desc"),
    
    //@NamedQuery(name = "JsysCaixa.findByTipoAndDataAndBancoResumo", query = "SELECT m.titulo, CASE WHEN (m.vlPago) FROM JsysCaixa m WHERE m.tipo in ('1', '2', '3', '5', '6') and m.data = :data and m.banco = :banco group by m.tipo"),
    
    @NamedQuery(name = "JsysCaixa.findByTipoAndData", query = "SELECT m FROM JsysCaixa m WHERE m.tipo in ('1', '2', '3', '5', '6') and m.data = :data order by m.hora desc"),
    @NamedQuery(name = "JsysCaixa.findByTipoAndDataUltimosRecebimentos", query = "SELECT m.venda, m.idTitulo, m.vlPago FROM JsysCaixa m WHERE m.tipo in ('1', '2', '3', '5', '6') and m.data = :data order by m.hora desc"),
    @NamedQuery(name = "JsysCaixa.findByVenda", query = "SELECT m FROM JsysCaixa m WHERE m.venda = :venda"),
    @NamedQuery(name = "JsysCaixa.findByEmissao", query = "SELECT m FROM JsysCaixa m WHERE m.emissao = :emissao"),
    @NamedQuery(name = "JsysCaixa.findByVencimento", query = "SELECT m FROM JsysCaixa m WHERE m.vencimento = :vencimento"),
    @NamedQuery(name = "JsysCaixa.findByPagamento", query = "SELECT m FROM JsysCaixa m WHERE m.pagamento = :pagamento"),
    @NamedQuery(name = "JsysCaixa.findByFatura", query = "SELECT m FROM JsysCaixa m WHERE m.fatura = :fatura"),
    @NamedQuery(name = "JsysCaixa.findByValor", query = "SELECT m FROM JsysCaixa m WHERE m.valor = :valor"),
    @NamedQuery(name = "JsysCaixa.findByRestante", query = "SELECT m FROM JsysCaixa m WHERE m.restante = :restante"),
    @NamedQuery(name = "JsysCaixa.findByVlPago", query = "SELECT m FROM JsysCaixa m WHERE m.vlPago = :vlPago"),
    @NamedQuery(name = "JsysCaixa.findByEmpresa", query = "SELECT m FROM JsysCaixa m WHERE m.empresa = :empresa"),
    @NamedQuery(name = "JsysCaixa.findByJuros", query = "SELECT m FROM JsysCaixa m WHERE m.juros = :juros"),
    @NamedQuery(name = "JsysCaixa.findByTipo", query = "SELECT m FROM JsysCaixa m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "JsysCaixa.findByData", query = "SELECT m FROM JsysCaixa m WHERE m.data = :data"),
    @NamedQuery(name = "JsysCaixa.findByQuitado", query = "SELECT m FROM JsysCaixa m WHERE m.quitado = :quitado"),
    @NamedQuery(name = "JsysCaixa.findByAtz", query = "SELECT m FROM JsysCaixa m WHERE m.atz = :atz"),
    @NamedQuery(name = "JsysCaixa.findByVlbruto", query = "SELECT m FROM JsysCaixa m WHERE m.vlbruto = :vlbruto"),
    @NamedQuery(name = "JsysCaixa.findByDescontopercet", query = "SELECT m FROM JsysCaixa m WHERE m.descontopercet = :descontopercet"),
    @NamedQuery(name = "JsysCaixa.findByHora", query = "SELECT m FROM JsysCaixa m WHERE m.hora = :hora"),
    @NamedQuery(name = "JsysCaixa.findByHistorico", query = "SELECT m FROM JsysCaixa m WHERE m.historico = :historico"),
    @NamedQuery(name = "JsysCaixa.findByBancoOrigem", query = "SELECT m FROM JsysCaixa m WHERE m.bancoOrigem = :bancoOrigem"),
    @NamedQuery(name = "JsysCaixa.findByBancoDestino", query = "SELECT m FROM JsysCaixa m WHERE m.bancoDestino = :bancoDestino")})
public class JsysCaixa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "idTitulo")
    private String idTitulo;
    @Basic(optional = false)
    @Column(name = "venda")
    private long venda;
    @Column(name = "emissao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date emissao;
    @Column(name = "vencimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vencimento;
    @Column(name = "pagamento")
    private String pagamento;
    @Column(name = "nomeCorentista")
    private String nomeCorentista;
    @Column(name = "fatura")
    private String fatura;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "restante")
    private BigDecimal restante;
    @Column(name = "vlPago")
    private BigDecimal vlPago;
    @Column(name = "idVendedor")
    private Integer idVendedor;
    @Basic(optional = false)
    @Column(name = "idCliente")
    private int idCliente;
    @Basic(optional = false)
    @Column(name = "Empresa")
    private String empresa;
    @Basic(optional = false)
    @Column(name = "juros")
    private BigDecimal juros;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "Data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private long id;
    @Column(name = "desconto")
    private BigDecimal desconto;
    @Column(name = "Quitado")
    private Boolean quitado;
    @Column(name = "ATZ")
    private Integer atz;
    @Column(name = "vlbruto")
    private BigDecimal vlbruto;
    @Column(name = "Descontopercet")
    private BigDecimal descontopercet;
    @Column(name = "usuarioInclusao")
    private String usuarioInclusao;
    @Column(name = "idBanco")
    private Integer idBanco;
    @Column(name = "hora")
    private String hora;
    @Column(name = "historico")
    private String historico;
    @Basic(optional = false)
    @Column(name = "idContabil")
    private String idContabil;
    @Basic(optional = false)
    @Column(name = "tabela")
    private String tabela;
    @Column(name = "bancoOrigem")
    private String bancoOrigem;
    @Column(name = "bancoDestino")
    private String bancoDestino;
    @Basic(optional = false)
    @Column(name = "retirado")
    private boolean retirado;
    @Column(name = "descRetirado")
    private String descRetirado;
    @Column(name = "dataRetirado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRetirado;
    @Basic(optional = false)
    @Column(name = "ficha")
    private int ficha;

    public JsysCaixa() {
    }

    public String getIdTitulo() {
        return idTitulo;
    }

    public void setIdTitulo(String idTitulo) {
        this.idTitulo = idTitulo;
    }

    public long getVenda() {
        return venda;
    }

    public void setVenda(long venda) {
        this.venda = venda;
    }

    public Date getEmissao() {
        return emissao;
    }

    public void setEmissao(Date emissao) {
        this.emissao = emissao;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getNomeCorentista() {
        return nomeCorentista;
    }

    public void setNomeCorentista(String nomeCorentista) {
        this.nomeCorentista = nomeCorentista;
    }

    public String getFatura() {
        return fatura;
    }

    public void setFatura(String fatura) {
        this.fatura = fatura;
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

    public BigDecimal getVlPago() {
        return vlPago;
    }

    public void setVlPago(BigDecimal vlPago) {
        this.vlPago = vlPago;
    }

    public Integer getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Integer idVendedor) {
        this.idVendedor = idVendedor;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public BigDecimal getJuros() {
        return juros;
    }

    public void setJuros(BigDecimal juros) {
        this.juros = juros;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public Boolean getQuitado() {
        return quitado;
    }

    public void setQuitado(Boolean quitado) {
        this.quitado = quitado;
    }

    public Integer getAtz() {
        return atz;
    }

    public void setAtz(Integer atz) {
        this.atz = atz;
    }

    public BigDecimal getVlbruto() {
        return vlbruto;
    }

    public void setVlbruto(BigDecimal vlbruto) {
        this.vlbruto = vlbruto;
    }

    public BigDecimal getDescontopercet() {
        return descontopercet;
    }

    public void setDescontopercet(BigDecimal descontopercet) {
        this.descontopercet = descontopercet;
    }

    public String getUsuarioInclusao() {
        return usuarioInclusao;
    }

    public void setUsuarioInclusao(String usuarioInclusao) {
        this.usuarioInclusao = usuarioInclusao;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public String getIdContabil() {
        return idContabil;
    }

    public void setIdContabil(String idContabil) {
        this.idContabil = idContabil;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String getBancoOrigem() {
        return bancoOrigem;
    }

    public void setBancoOrigem(String bancoOrigem) {
        this.bancoOrigem = bancoOrigem;
    }

    public String getBancoDestino() {
        return bancoDestino;
    }

    public void setBancoDestino(String bancoDestino) {
        this.bancoDestino = bancoDestino;
    }

    public boolean getRetirado() {
        return retirado;
    }

    public void setRetirado(boolean retirado) {
        this.retirado = retirado;
    }

    public String getDescRetirado() {
        return descRetirado;
    }

    public void setDescRetirado(String descRetirado) {
        this.descRetirado = descRetirado;
    }

    public Date getDataRetirado() {
        return dataRetirado;
    }

    public void setDataRetirado(Date dataRetirado) {
        this.dataRetirado = dataRetirado;
    }

    public int getFicha() {
        return ficha;
    }

    public void setFicha(int ficha) {
        this.ficha = ficha;
    }
    
}
