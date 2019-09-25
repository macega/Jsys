package br.sql.bean.views;

import br.JavaApplicationJsys;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
@Table(name = "jsysAvistaAberto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysAvistaAberto.findAll", query = "SELECT j FROM JsysAvistaAberto j"),
    @NamedQuery(name = "JsysAvistaAberto.findByIdReceber", query = "SELECT j FROM JsysAvistaAberto j WHERE j.idReceber = :idReceber"),
    @NamedQuery(name = "JsysAvistaAberto.findBySeqReceber", query = "SELECT j FROM JsysAvistaAberto j WHERE j.seqReceber = :seqReceber"),
    @NamedQuery(name = "JsysAvistaAberto.findByIdTitulo", query = "SELECT j FROM JsysAvistaAberto j WHERE j.idTitulo = :idTitulo"),
    @NamedQuery(name = "JsysAvistaAberto.findByIdReceberGeral", query = "SELECT j FROM JsysAvistaAberto j WHERE j.idReceberGeral = :idReceberGeral"),
    @NamedQuery(name = "JsysAvistaAberto.findByDataEmissao", query = "SELECT j FROM JsysAvistaAberto j WHERE j.dataEmissao = :dataEmissao"),
    @NamedQuery(name = "JsysAvistaAberto.findByDataVencimento", query = "SELECT j FROM JsysAvistaAberto j WHERE j.dataVencimento = :dataVencimento"),
    @NamedQuery(name = "JsysAvistaAberto.findByDataPagamento", query = "SELECT j FROM JsysAvistaAberto j WHERE j.dataPagamento = :dataPagamento"),
    @NamedQuery(name = "JsysAvistaAberto.findByIdFuncionario", query = "SELECT j FROM JsysAvistaAberto j WHERE j.idFuncionario = :idFuncionario"),
    @NamedQuery(name = "JsysAvistaAberto.findByNomeVendedor", query = "SELECT j FROM JsysAvistaAberto j WHERE j.nomeVendedor = :nomeVendedor"),
    @NamedQuery(name = "JsysAvistaAberto.findByNomeCorentista", query = "SELECT j FROM JsysAvistaAberto j WHERE j.nomeCorentista = :nomeCorentista"),
    @NamedQuery(name = "JsysAvistaAberto.findByValor", query = "SELECT j FROM JsysAvistaAberto j WHERE j.valor = :valor"),
    @NamedQuery(name = "JsysAvistaAberto.findByValorPago", query = "SELECT j FROM JsysAvistaAberto j WHERE j.valorPago = :valorPago"),
    @NamedQuery(name = "JsysAvistaAberto.findByAtz", query = "SELECT j FROM JsysAvistaAberto j WHERE j.atz = :atz"),
    @NamedQuery(name = "JsysAvistaAberto.findByHora", query = "SELECT j FROM JsysAvistaAberto j WHERE j.hora = :hora"),
    @NamedQuery(name = "JsysAvistaAberto.findByFicha", query = "SELECT j FROM JsysAvistaAberto j WHERE j.ficha = :ficha"),
    @NamedQuery(name = "JsysAvistaAberto.findByReceber", query = "SELECT j FROM JsysAvistaAberto j WHERE j.receber = :receber")})
public class JsysAvistaAberto implements Serializable {
    @Basic(optional = false)
    @Column(name = "idReceber")
    private Integer idReceber;
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "seqReceber")
    private int seqReceber;
    @Basic(optional = false)
    @Column(name = "idTitulo")
    private String idTitulo;
    @Id
    @Basic(optional = false)
    @Column(name = "idReceberGeral")
    private String idReceberGeral;
    @Column(name = "dataEmissao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEmissao;
    @Column(name = "dataVencimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVencimento;
    @Column(name = "dataPagamento")
    private String dataPagamento;
    @Basic(optional = false)
    @Column(name = "idFuncionario")
    private int idFuncionario;
    @Column(name = "nomeVendedor")
    private String nomeVendedor;
    @Column(name = "nomeCorentista")
    private String nomeCorentista;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    
    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "valorPago")
    private BigDecimal valorPago;
    @Column(name = "ATZ")
    private Integer atz;
    @Column(name = "hora")
    private String hora;
    @Column(name = "ficha")
    private Integer ficha;
    @Basic(optional = false)
    @Column(name = "receber")
    private int receber;

    public JsysAvistaAberto() {
    }


    public int getSeqReceber() {
        return seqReceber;
    }

    public void setSeqReceber(int seqReceber) {
        this.seqReceber = seqReceber;
    }

    public String getIdTitulo() {
        return idTitulo;
    }

    public void setIdTitulo(String idTitulo) {
        this.idTitulo = idTitulo;
    }

    public String getIdReceberGeral() {
        return idReceberGeral;
    }

    public void setIdReceberGeral(String idReceberGeral) {
        this.idReceberGeral = idReceberGeral;
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

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public String getNomeCorentista() {
        return nomeCorentista;
    }

    public void setNomeCorentista(String nomeCorentista) {
        this.nomeCorentista = nomeCorentista;
    }

    public BigDecimal getValor() {
        return valor.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_VENDA, RoundingMode.HALF_UP);
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public Integer getAtz() {
        return atz;
    }

    public void setAtz(Integer atz) {
        this.atz = atz;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Integer getFicha() {
        return ficha;
    }

    public void setFicha(Integer ficha) {
        this.ficha = ficha;
    }

    public int getReceber() {
        return receber;
    }

    public void setReceber(int receber) {
        this.receber = receber;
    }

    public Integer getIdReceber() {
        return idReceber;
    }

    public void setIdReceber(Integer idReceber) {
        this.idReceber = idReceber;
    }
    
}
