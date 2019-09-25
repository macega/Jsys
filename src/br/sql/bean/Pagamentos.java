/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "Pagamentos")
@NamedQueries({
    @NamedQuery(name = "Pagamentos.findByVencidos", query = "SELECT p FROM Pagamentos p WHERE (p.dataVencimento <= :dataVencimento) AND (p.quitado = :quitado) AND (p.idFornecedor = :idFornecedor OR 0 = :todos) AND (p.dataCancelar = NULL) ORDER BY P.dataVencimento"),
    @NamedQuery(name = "Pagamentos.findByDataVencimento", query = "SELECT p FROM Pagamentos p WHERE (p.dataVencimento BETWEEN :dataVencimentoInicial AND :dataVencimentoFinal) AND (p.idFornecedor = :idFornecedor OR 0 = :todos) ORDER BY P.dataVencimento"),
    @NamedQuery(name = "Pagamentos.findByDataPagamentoQuitado", query = "SELECT p FROM Pagamentos p WHERE (p.dataPagamento BETWEEN :dataPagamentoInicial AND :dataPagamentoFinal) AND (p.quitado = :quitado) AND (p.idFornecedor = :idFornecedor OR 0 = :todos) AND (p.dataCancelar = NULL) ORDER BY P.dataVencimento"),
    @NamedQuery(name = "Pagamentos.findByDataVencimentoQuitado", query = "SELECT p FROM Pagamentos p WHERE (p.dataVencimento BETWEEN :dataVencimentoInicial AND :dataVencimentoFinal) AND (p.quitado = :quitado) AND (p.idFornecedor = :idFornecedor OR 0 = :todos) AND (p.dataCancelar = NULL) ORDER BY P.dataVencimento"),
    @NamedQuery(name = "Pagamentos.findByDataVencimentoCancelados", query = "SELECT p FROM Pagamentos p WHERE (p.dataVencimento BETWEEN :dataVencimentoInicial AND :dataVencimentoFinal) AND (p.idFornecedor = :idFornecedor OR 0 = :todos) AND (p.dataCancelar <> NULL) ORDER BY P.dataVencimento"),
    @NamedQuery(name = "Pagamentos.findAll", query = "SELECT p FROM Pagamentos p"),
    @NamedQuery(name = "Pagamentos.findByIdPagamentos", query = "SELECT p FROM Pagamentos p WHERE p.idPagamentos = :idPagamentos"),
    @NamedQuery(name = "Pagamentos.findByIdPagamentosAtivos", query = "SELECT p FROM Pagamentos p WHERE p.idPagamentos = :idPagamentos AND p.quitado = 1 AND p.dataCancelar IS NULL"),
    @NamedQuery(name = "Pagamentos.findByOutrosNumeros", query = "SELECT p FROM Pagamentos p WHERE p.outrosNumeros = :outrosNumeros"),
    @NamedQuery(name = "Pagamentos.findByDocOrdem", query = "SELECT p FROM Pagamentos p WHERE p.docOrdem = :docOrdem"),
    @NamedQuery(name = "Pagamentos.findByCodEmpresa", query = "SELECT p FROM Pagamentos p WHERE p.codEmpresa = :codEmpresa"),
    @NamedQuery(name = "Pagamentos.findByIdTitulo", query = "SELECT p FROM Pagamentos p WHERE p.idTitulo = :idTitulo"),
    @NamedQuery(name = "Pagamentos.findByNomeFornecedor", query = "SELECT p FROM Pagamentos p WHERE p.nomeFornecedor = :nomeFornecedor"),
    @NamedQuery(name = "Pagamentos.findBySemCadastrado", query = "SELECT p FROM Pagamentos p WHERE p.semCadastrado = :semCadastrado"),
    @NamedQuery(name = "Pagamentos.findByDataEmissao", query = "SELECT p FROM Pagamentos p WHERE p.dataEmissao = :dataEmissao"),
    @NamedQuery(name = "Pagamentos.findByValorTitulo", query = "SELECT p FROM Pagamentos p WHERE p.valorTitulo = :valorTitulo"),
    @NamedQuery(name = "Pagamentos.findByValorPago", query = "SELECT p FROM Pagamentos p WHERE p.valorPago = :valorPago"),
    @NamedQuery(name = "Pagamentos.findByRestante", query = "SELECT p FROM Pagamentos p WHERE p.restante = :restante"),
    @NamedQuery(name = "Pagamentos.findByValorEstorno", query = "SELECT p FROM Pagamentos p WHERE p.valorEstorno = :valorEstorno"),
    @NamedQuery(name = "Pagamentos.findByIdBanco", query = "SELECT p FROM Pagamentos p WHERE p.idBanco = :idBanco"),
    @NamedQuery(name = "Pagamentos.findByObs", query = "SELECT p FROM Pagamentos p WHERE p.obs = :obs"),
    @NamedQuery(name = "Pagamentos.findByDataPagamento", query = "SELECT p FROM Pagamentos p WHERE p.dataPagamento = :dataPagamento"),
    @NamedQuery(name = "Pagamentos.findByIdContabil", query = "SELECT p FROM Pagamentos p WHERE p.idContabil = :idContabil"),
    @NamedQuery(name = "Pagamentos.findByMulta", query = "SELECT p FROM Pagamentos p WHERE p.multa = :multa"),
    @NamedQuery(name = "Pagamentos.findByJuros", query = "SELECT p FROM Pagamentos p WHERE p.juros = :juros"),
    @NamedQuery(name = "Pagamentos.findByCorrecao", query = "SELECT p FROM Pagamentos p WHERE p.correcao = :correcao"),
    @NamedQuery(name = "Pagamentos.findByDespesas", query = "SELECT p FROM Pagamentos p WHERE p.despesas = :despesas"),
    @NamedQuery(name = "Pagamentos.findByDescontos", query = "SELECT p FROM Pagamentos p WHERE p.descontos = :descontos"),
    @NamedQuery(name = "Pagamentos.findByDataUltimoPgto", query = "SELECT p FROM Pagamentos p WHERE p.dataUltimoPgto = :dataUltimoPgto"),
    @NamedQuery(name = "Pagamentos.findByDataCancelar", query = "SELECT p FROM Pagamentos p WHERE p.dataCancelar = :dataCancelar"),
    @NamedQuery(name = "Pagamentos.findByDataInclusao", query = "SELECT p FROM Pagamentos p WHERE p.dataInclusao = :dataInclusao"),
    @NamedQuery(name = "Pagamentos.findByUsuarioInclusao", query = "SELECT p FROM Pagamentos p WHERE p.usuarioInclusao = :usuarioInclusao"),
    @NamedQuery(name = "Pagamentos.findByDataAlteracao", query = "SELECT p FROM Pagamentos p WHERE p.dataAlteracao = :dataAlteracao"),
    @NamedQuery(name = "Pagamentos.findByUsuarioAlteracao", query = "SELECT p FROM Pagamentos p WHERE p.usuarioAlteracao = :usuarioAlteracao"),
    @NamedQuery(name = "Pagamentos.findByNumeroCompensacao", query = "SELECT p FROM Pagamentos p WHERE p.numeroCompensacao = :numeroCompensacao"),
    @NamedQuery(name = "Pagamentos.findByIdTituloBaixa", query = "SELECT p FROM Pagamentos p WHERE p.idTituloBaixa = :idTituloBaixa"),
    @NamedQuery(name = "Pagamentos.findByQuitado", query = "SELECT p FROM Pagamentos p WHERE p.quitado = :quitado")})
public class Pagamentos implements Serializable {
    @ManyToOne
    @JoinColumn(name = "idFornecedor", referencedColumnName = "idCliente")
    private JsysClientes idFornecedor;
    @Basic(optional = false)
    @Column(name = "idBanco")
    private int idBanco;
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPagamentos")
    private Integer idPagamentos;
    @Basic(optional = false)
    @Column(name = "outrosNumeros")
    private String outrosNumeros;
    @Basic(optional = false)
    @Column(name = "docOrdem")
    private String docOrdem;
    @Basic(optional = false)
    @Column(name = "codEmpresa")
    private String codEmpresa;
    @Basic(optional = false)
    @Column(name = "idTitulo")
    private String idTitulo;
    @Basic(optional = false)
    @Column(name = "nomeFornecedor")
    private String nomeFornecedor;
    @Basic(optional = false)
    @Column(name = "semCadastrado")
    private boolean semCadastrado;
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
    @Column(name = "valorPago")
    private BigDecimal valorPago;
    @Basic(optional = false)
    @Column(name = "restante")
    private BigDecimal restante;
    @Basic(optional = false)
    @Column(name = "valorEstorno")
    private BigDecimal valorEstorno;
    @Basic(optional = false)
    @Column(name = "obs")
    private String obs;
    @Column(name = "dataPagamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPagamento;
    @Basic(optional = false)
    @Column(name = "idContabil")
    private String idContabil;
    @Basic(optional = false)
    @Column(name = "multa")
    private BigDecimal multa;
    @Basic(optional = false)
    @Column(name = "juros")
    private BigDecimal juros;
    @Basic(optional = false)
    @Column(name = "correcao")
    private BigDecimal correcao;
    @Basic(optional = false)
    @Column(name = "despesas")
    private BigDecimal despesas;
    @Basic(optional = false)
    @Column(name = "descontos")
    private BigDecimal descontos;
    @Column(name = "dataUltimoPgto")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUltimoPgto;
    @Column(name = "dataCancelar")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCancelar;
    @Basic(optional = false)
    @Lob
    @Column(name = "obsCancelamento")
    private String obsCancelamento;
    @Column(name = "dataInclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;
    @Basic(optional = false)
    @Column(name = "usuarioInclusao")
    private String usuarioInclusao;
    @Column(name = "dataAlteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @Basic(optional = false)
    @Column(name = "usuarioAlteracao")
    private String usuarioAlteracao;
    @Basic(optional = false)
    @Column(name = "numeroCompensacao")
    private String numeroCompensacao;
    @Basic(optional = false)
    @Column(name = "idTituloBaixa")
    private String idTituloBaixa;
    @Basic(optional = false)
    @Column(name = "quitado")
    private boolean quitado;
    @Basic(optional = false)
    @Lob
    @Column(name = "informacoesFiscais")
    private String informacoesFiscais;
    @Basic(optional = false)
    @Column(name = "idLoja")
    private String idLoja;

    public Pagamentos() {
        this.docOrdem = "";
        this.codEmpresa = "";
        this.valorPago = BigDecimal.ZERO;
        this.valorEstorno = BigDecimal.ZERO;
        this.idBanco = 0;
        this.despesas = BigDecimal.ZERO;
        this.descontos = BigDecimal.ZERO;
        this.numeroCompensacao = "";
        this.idTituloBaixa = "";
        this.quitado = false;
        this.informacoesFiscais = "";
    }

    public Pagamentos(Integer idPagamentos) {
        this.idPagamentos = idPagamentos;
    }

    public Pagamentos(Integer idPagamentos, String outrosNumeros, String docOrdem, String codEmpresa, String idTitulo, JsysClientes idFornecedor, String nomeFornecedor, boolean semCadastrado, BigDecimal valorTitulo, BigDecimal valorPago, BigDecimal restante, BigDecimal valorEstorno, Integer idBanco, String obs, String idContabil, BigDecimal multa, BigDecimal juros, BigDecimal correcao, BigDecimal despesas, BigDecimal descontos, String obsCancelamento, String usuarioInclusao, String usuarioAlteracao, String numeroCompensacao, String idTituloBaixa, boolean quitado, String informacoesFiscais) {
        this.idPagamentos = idPagamentos;
        this.outrosNumeros = outrosNumeros;
        this.docOrdem = docOrdem;
        this.codEmpresa = codEmpresa;
        this.idTitulo = idTitulo;
        this.idFornecedor = idFornecedor;
        this.nomeFornecedor = nomeFornecedor;
        this.semCadastrado = semCadastrado;
        this.valorTitulo = valorTitulo;
        this.valorPago = valorPago;
        this.restante = restante;
        this.valorEstorno = valorEstorno;
        this.idBanco = idBanco;
        this.obs = obs;
        this.idContabil = idContabil;
        this.multa = multa;
        this.juros = juros;
        this.correcao = correcao;
        this.despesas = despesas;
        this.descontos = descontos;
        this.obsCancelamento = obsCancelamento;
        this.usuarioInclusao = usuarioInclusao;
        this.usuarioAlteracao = usuarioAlteracao;
        this.numeroCompensacao = numeroCompensacao;
        this.idTituloBaixa = idTituloBaixa;
        this.quitado = quitado;
        this.informacoesFiscais = informacoesFiscais;
    }

    public Integer getIdPagamentos() {
        return idPagamentos;
    }

    public void setIdPagamentos(Integer idPagamentos) {
        Integer oldIdPagamentos = this.idPagamentos;
        this.idPagamentos = idPagamentos;
        changeSupport.firePropertyChange("idPagamentos", oldIdPagamentos, idPagamentos);
    }

    public String getOutrosNumeros() {
        return outrosNumeros;
    }

    public void setOutrosNumeros(String outrosNumeros) {
        String oldOutrosNumeros = this.outrosNumeros;
        this.outrosNumeros = outrosNumeros;
        changeSupport.firePropertyChange("outrosNumeros", oldOutrosNumeros, outrosNumeros);
    }

    public String getDocOrdem() {
        return docOrdem;
    }

    public void setDocOrdem(String docOrdem) {
        String oldDocOrdem = this.docOrdem;
        this.docOrdem = docOrdem;
        changeSupport.firePropertyChange("docOrdem", oldDocOrdem, docOrdem);
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        String oldCodEmpresa = this.codEmpresa;
        this.codEmpresa = codEmpresa;
        changeSupport.firePropertyChange("codEmpresa", oldCodEmpresa, codEmpresa);
    }

    public String getIdTitulo() {
        return idTitulo;
    }

    public void setIdTitulo(String idTitulo) {
        String oldIdTitulo = this.idTitulo;
        this.idTitulo = idTitulo;
        changeSupport.firePropertyChange("idTitulo", oldIdTitulo, idTitulo);
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        String oldNomeFornecedor = this.nomeFornecedor;
        this.nomeFornecedor = nomeFornecedor;
        changeSupport.firePropertyChange("nomeFornecedor", oldNomeFornecedor, nomeFornecedor);
    }

    public boolean getSemCadastrado() {
        return semCadastrado;
    }

    public void setSemCadastrado(boolean semCadastrado) {
        boolean oldSemCadastrado = this.semCadastrado;
        this.semCadastrado = semCadastrado;
        changeSupport.firePropertyChange("semCadastrado", oldSemCadastrado, semCadastrado);
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        Date oldDataEmissao = this.dataEmissao;
        this.dataEmissao = dataEmissao;
        changeSupport.firePropertyChange("dataEmissao", oldDataEmissao, dataEmissao);
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        Date oldDataVencimento = this.dataVencimento;
        this.dataVencimento = dataVencimento;
        changeSupport.firePropertyChange("dataVencimento", oldDataVencimento, dataVencimento);
    }

    public BigDecimal getValorTitulo() {
        return valorTitulo;
    }

    public void setValorTitulo(BigDecimal valorTitulo) {
        BigDecimal oldValorTitulo = this.valorTitulo;
        this.valorTitulo = valorTitulo;
        changeSupport.firePropertyChange("valorTitulo", oldValorTitulo, valorTitulo);
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        BigDecimal oldValorPago = this.valorPago;
        this.valorPago = valorPago;
        changeSupport.firePropertyChange("valorPago", oldValorPago, valorPago);
    }

    public BigDecimal getRestante() {
        return restante;
    }

    public void setRestante(BigDecimal restante) {
        BigDecimal oldRestante = this.restante;
        this.restante = restante;
        changeSupport.firePropertyChange("restante", oldRestante, restante);
    }

    public BigDecimal getValorEstorno() {
        return valorEstorno;
    }

    public void setValorEstorno(BigDecimal valorEstorno) {
        BigDecimal oldValorEstorno = this.valorEstorno;
        this.valorEstorno = valorEstorno;
        changeSupport.firePropertyChange("valorEstorno", oldValorEstorno, valorEstorno);
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        String oldObs = this.obs;
        this.obs = obs;
        changeSupport.firePropertyChange("obs", oldObs, obs);
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        Date oldDataPagamento = this.dataPagamento;
        this.dataPagamento = dataPagamento;
        changeSupport.firePropertyChange("dataPagamento", oldDataPagamento, dataPagamento);
    }

    public String getIdContabil() {
        return idContabil;
    }

    public void setIdContabil(String idContabil) {
        String oldIdContabil = this.idContabil;
        this.idContabil = idContabil;
        changeSupport.firePropertyChange("idContabil", oldIdContabil, idContabil);
    }

    public BigDecimal getMulta() {
        return multa;
    }

    public void setMulta(BigDecimal multa) {
        BigDecimal oldMulta = this.multa;
        this.multa = multa;
        changeSupport.firePropertyChange("multa", oldMulta, multa);
    }

    public BigDecimal getJuros() {
        return juros;
    }

    public void setJuros(BigDecimal juros) {
        BigDecimal oldJuros = this.juros;
        this.juros = juros;
        changeSupport.firePropertyChange("juros", oldJuros, juros);
    }

    public BigDecimal getCorrecao() {
        return correcao;
    }

    public void setCorrecao(BigDecimal correcao) {
        BigDecimal oldCorrecao = this.correcao;
        this.correcao = correcao;
        changeSupport.firePropertyChange("correcao", oldCorrecao, correcao);
    }

    public BigDecimal getDespesas() {
        return despesas;
    }

    public void setDespesas(BigDecimal despesas) {
        BigDecimal oldDespesas = this.despesas;
        this.despesas = despesas;
        changeSupport.firePropertyChange("despesas", oldDespesas, despesas);
    }

    public BigDecimal getDescontos() {
        return descontos;
    }

    public void setDescontos(BigDecimal descontos) {
        BigDecimal oldDescontos = this.descontos;
        this.descontos = descontos;
        changeSupport.firePropertyChange("descontos", oldDescontos, descontos);
    }

    public Date getDataUltimoPgto() {
        return dataUltimoPgto;
    }

    public void setDataUltimoPgto(Date dataUltimoPgto) {
        Date oldDataUltimoPgto = this.dataUltimoPgto;
        this.dataUltimoPgto = dataUltimoPgto;
        changeSupport.firePropertyChange("dataUltimoPgto", oldDataUltimoPgto, dataUltimoPgto);
    }

    public Date getDataCancelar() {
        return dataCancelar;
    }

    public void setDataCancelar(Date dataCancelar) {
        Date oldDataCancelar = this.dataCancelar;
        this.dataCancelar = dataCancelar;
        changeSupport.firePropertyChange("dataCancelar", oldDataCancelar, dataCancelar);
    }

    public String getObsCancelamento() {
        return obsCancelamento;
    }

    public void setObsCancelamento(String obsCancelamento) {
        String oldObsCancelamento = this.obsCancelamento;
        this.obsCancelamento = obsCancelamento;
        changeSupport.firePropertyChange("obsCancelamento", oldObsCancelamento, obsCancelamento);
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

    public String getNumeroCompensacao() {
        return numeroCompensacao;
    }

    public void setNumeroCompensacao(String numeroCompensacao) {
        String oldNumeroCompensacao = this.numeroCompensacao;
        this.numeroCompensacao = numeroCompensacao;
        changeSupport.firePropertyChange("numeroCompensacao", oldNumeroCompensacao, numeroCompensacao);
    }

    public String getIdTituloBaixa() {
        return idTituloBaixa;
    }

    public void setIdTituloBaixa(String idTituloBaixa) {
        String oldIdTituloBaixa = this.idTituloBaixa;
        this.idTituloBaixa = idTituloBaixa;
        changeSupport.firePropertyChange("idTituloBaixa", oldIdTituloBaixa, idTituloBaixa);
    }

    public boolean getQuitado() {
        return quitado;
    }

    public void setQuitado(boolean quitado) {
        boolean oldQuitado = this.quitado;
        this.quitado = quitado;
        changeSupport.firePropertyChange("quitado", oldQuitado, quitado);
    }

    public String getInformacoesFiscais() {
        return informacoesFiscais;
    }

    public void setInformacoesFiscais(String informacoesFiscais) {
        String oldInformacoesFiscais = this.informacoesFiscais;
        this.informacoesFiscais = informacoesFiscais;
        changeSupport.firePropertyChange("informacoesFiscais", oldInformacoesFiscais, informacoesFiscais);
    }

    public String getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(String idLoja) {
        String oldIdLoja = this.idLoja;
        this.idLoja = idLoja;
        changeSupport.firePropertyChange("idLoja", oldIdLoja, idLoja);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPagamentos != null ? idPagamentos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagamentos)) {
            return false;
        }
        Pagamentos other = (Pagamentos) object;
        if ((this.idPagamentos == null && other.idPagamentos != null) || (this.idPagamentos != null && !this.idPagamentos.equals(other.idPagamentos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.sql.bean.Pagamentos[ idPagamentos=" + idPagamentos + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public JsysClientes getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(JsysClientes idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }
}
