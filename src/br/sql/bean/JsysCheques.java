/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysCheques")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysCheques.findAll", query = "SELECT j FROM JsysCheques j ORDER BY j.dataVencimento"),
    @NamedQuery(name = "JsysCheques.findByDataVencimentoAndQuitado", query = "SELECT j FROM JsysCheques j WHERE j.dataVencimento BETWEEN :dataVencimentoInicial AND :dataVencimentoFinal AND j.quitado = :quitado ORDER BY j.dataVencimento"),
    @NamedQuery(name = "JsysCheques.findByQuitado", query = "SELECT j FROM JsysCheques j WHERE j.quitado = :quitado ORDER BY j.dataVencimento"),
    
    @NamedQuery(name = "JsysCheques.findByDevolvido", query = "SELECT j FROM JsysCheques j WHERE j.devolvido = :devolvido ORDER BY j.dataVencimento"),
    @NamedQuery(name = "JsysCheques.findByIdClienteDevolvido", query = "SELECT j FROM JsysCheques j WHERE j.idCliente = :idCliente AND j.devolvido = :devolvido ORDER BY j.dataVencimento"),
    
    @NamedQuery(name = "JsysCheques.findByIdClienteDataVencimentoAndQuitado", query = "SELECT j FROM JsysCheques j WHERE j.idCliente = :idCliente AND j.dataVencimento BETWEEN :dataVencimentoInicial AND :dataVencimentoFinal AND j.quitado = :quitado ORDER BY j.dataVencimento"),
    @NamedQuery(name = "JsysCheques.findByIdClienteQuitado", query = "SELECT j FROM JsysCheques j WHERE j.idCliente = :idCliente AND j.quitado = :quitado ORDER BY j.dataVencimento"),
    @NamedQuery(name = "JsysCheques.findByIdcheque", query = "SELECT j FROM JsysCheques j WHERE j.idcheque = :idcheque"),
    @NamedQuery(name = "JsysCheques.findByEmitidos", query = "SELECT j FROM JsysCheques j WHERE j.emitidos = :emitidos"),
    @NamedQuery(name = "JsysCheques.findByDataEmissao", query = "SELECT j FROM JsysCheques j WHERE j.dataEmissao = :dataEmissao"),
    @NamedQuery(name = "JsysCheques.findByDataVencimento", query = "SELECT j FROM JsysCheques j WHERE j.dataVencimento = :dataVencimento"),
    @NamedQuery(name = "JsysCheques.findBySemCadastrado", query = "SELECT j FROM JsysCheques j WHERE j.semCadastrado = :semCadastrado"),
    @NamedQuery(name = "JsysCheques.findByNomeCorentista", query = "SELECT j FROM JsysCheques j WHERE j.nomeCorentista = :nomeCorentista"),
    @NamedQuery(name = "JsysCheques.findByIdCliente", query = "SELECT j FROM JsysCheques j WHERE j.idCliente = :idCliente"),
    @NamedQuery(name = "JsysCheques.findByIdBanco", query = "SELECT j FROM JsysCheques j WHERE j.idBanco = :idBanco"),
    @NamedQuery(name = "JsysCheques.findByIdCaixa", query = "SELECT j FROM JsysCheques j WHERE j.idCaixa = :idCaixa"),
    @NamedQuery(name = "JsysCheques.findByNumeroCheque", query = "SELECT j FROM JsysCheques j WHERE j.numeroCheque = :numeroCheque"),
    @NamedQuery(name = "JsysCheques.findByOutrosNumeros", query = "SELECT j FROM JsysCheques j WHERE j.outrosNumeros = :outrosNumeros"),
    @NamedQuery(name = "JsysCheques.findByValorCheque", query = "SELECT j FROM JsysCheques j WHERE j.valorCheque = :valorCheque"),
    @NamedQuery(name = "JsysCheques.findByValorPago", query = "SELECT j FROM JsysCheques j WHERE j.valorPago = :valorPago"),
    @NamedQuery(name = "JsysCheques.findByNumeroCompensacao", query = "SELECT j FROM JsysCheques j WHERE j.numeroCompensacao = :numeroCompensacao"),
    @NamedQuery(name = "JsysCheques.findByCancelado", query = "SELECT j FROM JsysCheques j WHERE j.cancelado = :cancelado"),
    @NamedQuery(name = "JsysCheques.findByDataCancelar", query = "SELECT j FROM JsysCheques j WHERE j.dataCancelar = :dataCancelar"),
    @NamedQuery(name = "JsysCheques.findByDataInclusao", query = "SELECT j FROM JsysCheques j WHERE j.dataInclusao = :dataInclusao"),
    @NamedQuery(name = "JsysCheques.findByUsuarioInclusao", query = "SELECT j FROM JsysCheques j WHERE j.usuarioInclusao = :usuarioInclusao"),
    @NamedQuery(name = "JsysCheques.findByDataAlteracao", query = "SELECT j FROM JsysCheques j WHERE j.dataAlteracao = :dataAlteracao"),
    @NamedQuery(name = "JsysCheques.findByUsuarioAlteracao", query = "SELECT j FROM JsysCheques j WHERE j.usuarioAlteracao = :usuarioAlteracao"),
    @NamedQuery(name = "JsysCheques.findByIdLoja", query = "SELECT j FROM JsysCheques j WHERE j.idLoja = :idLoja")})
public class JsysCheques implements Serializable {
    @Transient
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcheque")
    private Integer idcheque;
    @Basic(optional = false)
    @Column(name = "emitidos")
    private boolean emitidos;
    @Basic(optional = false)
    @Column(name = "dataEmissao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEmissao;
    @Basic(optional = false)
    @Column(name = "dataVencimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVencimento;
    @Basic(optional = false)
    @Column(name = "semCadastrado")
    private boolean semCadastrado;
    @Column(name = "nomeCorentista")
    private String nomeCorentista;
    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private JsysClientes idCliente;
    @ManyToOne
    @JoinColumn(name = "idBanco", referencedColumnName = "idBanco")
    private JsysBancos idBanco;
    @Column(name = "idCaixa")
    private Integer idCaixa;
    @Basic(optional = false)
    @Column(name = "numeroCheque")
    private String numeroCheque;
    @Basic(optional = false)
    @Column(name = "outrosNumeros")
    private String outrosNumeros;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valorCheque")
    private BigDecimal valorCheque;
    @Basic(optional = false)
    @Column(name = "valorPago")
    private BigDecimal valorPago;
    //@Basic(optional = true)
    @Column(name = "numeroCompensacao")
    private String numeroCompensacao;
    @Basic(optional = false)
    @Column(name = "quitado")
    private boolean quitado;
    @Basic(optional = false)
    @Column(name = "cancelado")
    private boolean cancelado;
    @Column(name = "dataCancelar")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCancelar;
    @Lob
    @Column(name = "obsCancelamento")
    private String obsCancelamento;
    @Lob
    @Column(name = "obs")
    private String obs;
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
    @ManyToOne
    @JoinColumn(name = "idLoja", referencedColumnName = "idLoja")
    private JsysLojas idLoja;
    @Column(name = "dataPagamento")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataPagamento;
    @Basic(optional = false)
    @Column(name = "devolvido")
    private boolean devolvido;

    public JsysCheques() {
        idCaixa = 0;
        emitidos = true;
        cancelado = false;
        quitado = false;
        semCadastrado = false;
        valorCheque = BigDecimal.ZERO;
        valorPago = BigDecimal.ZERO;
        numeroCompensacao = "";
    }

    public JsysCheques(Integer idcheque) {
        this.idcheque = idcheque;
    }

    public JsysCheques(Integer idcheque, boolean emitidos, Date dataEmissao, Date dataVencimento, boolean semCadastrado, JsysClientes idCliente, JsysBancos idBanco, String numeroCheque, String outrosNumeros, BigDecimal valorCheque, BigDecimal valorPago, boolean quitado, boolean cancelado, Date dataInclusao, String usuarioInclusao, JsysLojas idLoja) {
        this.idcheque = idcheque;
        this.emitidos = emitidos;
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.semCadastrado = semCadastrado;
        this.idCliente = idCliente;
        this.idBanco = idBanco;
        this.numeroCheque = numeroCheque;
        this.outrosNumeros = outrosNumeros;
        this.valorCheque = valorCheque;
        this.valorPago = valorPago;
        this.quitado = quitado;
        this.cancelado = cancelado;
        this.dataInclusao = dataInclusao;
        this.usuarioInclusao = usuarioInclusao;
        this.idLoja = idLoja;
    }

    public Integer getIdcheque() {
        return idcheque;
    }

    public void setIdcheque(Integer idcheque) {
        Integer oldIdcheque = this.idcheque;
        this.idcheque = idcheque;
        changeSupport.firePropertyChange("idcheque", oldIdcheque, idcheque);
    }

    public boolean getEmitidos() {
        return emitidos;
    }

    public void setEmitidos(boolean emitidos) {
        boolean oldEmitidos = this.emitidos;
        this.emitidos = emitidos;
        changeSupport.firePropertyChange("emitidos", oldEmitidos, emitidos);
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

    public boolean getSemCadastrado() {
        return semCadastrado;
    }

    public void setSemCadastrado(boolean semCadastrado) {
        boolean oldSemCadastrado = this.semCadastrado;
        this.semCadastrado = semCadastrado;
        changeSupport.firePropertyChange("semCadastrado", oldSemCadastrado, semCadastrado);
    }

    public String getNomeCorentista() {
        return nomeCorentista;
    }

    public void setNomeCorentista(String nomeCorentista) {
        String oldNomeCorentista = this.nomeCorentista;
        this.nomeCorentista = nomeCorentista;
        changeSupport.firePropertyChange("nomeCorentista", oldNomeCorentista, nomeCorentista);
    }

    public JsysClientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(JsysClientes idCliente) {
        JsysClientes oldIdCliente = this.idCliente;
        this.idCliente = idCliente;
        changeSupport.firePropertyChange("idCliente", oldIdCliente, idCliente);
    }

    public JsysBancos getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(JsysBancos idBanco) {
        JsysBancos oldIdBanco = this.idBanco;
        this.idBanco = idBanco;
        changeSupport.firePropertyChange("idBanco", oldIdBanco, idBanco);
    }

    public Integer getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(Integer idCaixa) {
        Integer oldIdCaixa = this.idCaixa;
        this.idCaixa = idCaixa;
        changeSupport.firePropertyChange("idCaixa", oldIdCaixa, idCaixa);
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(String numeroCheque) {
        String oldNumeroCheque = this.numeroCheque;
        this.numeroCheque = numeroCheque;
        changeSupport.firePropertyChange("numeroCheque", oldNumeroCheque, numeroCheque);
    }

    public String getOutrosNumeros() {
        return outrosNumeros;
    }

    public void setOutrosNumeros(String outrosNumeros) {
        String oldOutrosNumeros = this.outrosNumeros;
        this.outrosNumeros = outrosNumeros;
        changeSupport.firePropertyChange("outrosNumeros", oldOutrosNumeros, outrosNumeros);
    }

    public BigDecimal getValorCheque() {
        return valorCheque;
    }

    public void setValorCheque(BigDecimal valorCheque) {
        BigDecimal oldValorCheque = this.valorCheque;
        this.valorCheque = valorCheque;
        changeSupport.firePropertyChange("valorCheque", oldValorCheque, valorCheque);
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        BigDecimal oldValorPago = this.valorPago;
        this.valorPago = valorPago;
        changeSupport.firePropertyChange("valorPago", oldValorPago, valorPago);
    }

    public String getNumeroCompensacao() {
        return numeroCompensacao;
    }

    public void setNumeroCompensacao(String numeroCompensacao) {
        String oldNumeroCompensacao = this.numeroCompensacao;
        this.numeroCompensacao = numeroCompensacao;
        changeSupport.firePropertyChange("numeroCompensacao", oldNumeroCompensacao, numeroCompensacao);
    }

    public boolean getQuitado() {
        return quitado;
    }

    public void setQuitado(boolean quitado) {
        boolean oldQuitado = this.quitado;
        this.quitado = quitado;
        changeSupport.firePropertyChange("quitado", oldQuitado, quitado);
    }

    public boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        boolean oldCancelado = this.cancelado;
        this.cancelado = cancelado;
        changeSupport.firePropertyChange("cancelado", oldCancelado, cancelado);
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

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        String oldObs = this.obs;
        this.obs = obs;
        changeSupport.firePropertyChange("obs", oldObs, obs);
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

    public JsysLojas getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(JsysLojas idLoja) {
        JsysLojas oldIdLoja = this.idLoja;
        this.idLoja = idLoja;
        changeSupport.firePropertyChange("idLoja", oldIdLoja, idLoja);
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        Date oldDataPagamento = this.dataPagamento;
        this.dataPagamento = dataPagamento;
        changeSupport.firePropertyChange("dataPagamento", oldDataPagamento, dataPagamento);
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        boolean oldDevolvido = this.devolvido;
        this.devolvido = devolvido;
        changeSupport.firePropertyChange("devolvido", oldDevolvido, devolvido);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcheque != null ? idcheque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysCheques)) {
            return false;
        }
        JsysCheques other = (JsysCheques) object;
        return (this.idcheque != null || other.idcheque == null) && (this.idcheque == null || this.idcheque.equals(other.idcheque));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysCheques[ idcheque=" + idcheque + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
