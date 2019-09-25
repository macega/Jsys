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
@Table(name = "RETIRADAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Retiradas.findByDataBetween", query = "SELECT r FROM Retiradas r WHERE (r.data BETWEEN :dataInicial AND :dataFinal) ORDER BY r.data"),
    @NamedQuery(name = "Retiradas.findByDataBetweenRetirado", query = "SELECT r FROM Retiradas r WHERE (r.data BETWEEN :dataInicial AND :dataFinal) AND (r.retirado = :retirado) ORDER BY r.data"),
    @NamedQuery(name = "Retiradas.findAllPendentes", query = "SELECT r FROM Retiradas r WHERE r.retirado = false ORDER BY r.data"),
    @NamedQuery(name = "Retiradas.findAll", query = "SELECT r FROM Retiradas r"),
    @NamedQuery(name = "Retiradas.findByIdTransferencia", query = "SELECT r FROM Retiradas r WHERE r.idTransferencia = :idTransferencia"),
    @NamedQuery(name = "Retiradas.findByIdGeral", query = "SELECT r FROM Retiradas r WHERE r.idGeral = :idGeral"),
    @NamedQuery(name = "Retiradas.findByValor", query = "SELECT r FROM Retiradas r WHERE r.valor = :valor"),
    @NamedQuery(name = "Retiradas.findByData", query = "SELECT r FROM Retiradas r WHERE r.data = :data"),
    @NamedQuery(name = "Retiradas.findByDescricao", query = "SELECT r FROM Retiradas r WHERE r.descricao = :descricao"),
    @NamedQuery(name = "Retiradas.findByDocumento", query = "SELECT r FROM Retiradas r WHERE r.documento = :documento"),
    @NamedQuery(name = "Retiradas.findByNomeOrigem", query = "SELECT r FROM Retiradas r WHERE r.nomeOrigem = :nomeOrigem"),
    @NamedQuery(name = "Retiradas.findByIdBancoOrigem", query = "SELECT r FROM Retiradas r WHERE r.idBancoOrigem = :idBancoOrigem"),
    @NamedQuery(name = "Retiradas.findByIdBancoDestino", query = "SELECT r FROM Retiradas r WHERE r.idBancoDestino = :idBancoDestino"),
    @NamedQuery(name = "Retiradas.findByCancelada", query = "SELECT r FROM Retiradas r WHERE r.cancelada = :cancelada"),
    @NamedQuery(name = "Retiradas.findByIdTitulo", query = "SELECT r FROM Retiradas r WHERE r.idTitulo = :idTitulo"),
    @NamedQuery(name = "Retiradas.findByHora", query = "SELECT r FROM Retiradas r WHERE r.hora = :hora"),
    @NamedQuery(name = "Retiradas.findByUsuarioInclusao", query = "SELECT r FROM Retiradas r WHERE r.usuarioInclusao = :usuarioInclusao"),
    @NamedQuery(name = "Retiradas.findByUsuarioAlteracao", query = "SELECT r FROM Retiradas r WHERE r.usuarioAlteracao = :usuarioAlteracao"),
    @NamedQuery(name = "Retiradas.findByRetirado", query = "SELECT r FROM Retiradas r WHERE r.retirado = :retirado"),
    @NamedQuery(name = "Retiradas.findByDescRetirado", query = "SELECT r FROM Retiradas r WHERE r.descRetirado = :descRetirado"),
    @NamedQuery(name = "Retiradas.findByDataRetirado", query = "SELECT r FROM Retiradas r WHERE r.dataRetirado = :dataRetirado")})
public class Retiradas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idTransferencia")
    private long idTransferencia;
    @Basic(optional = false)
    @Column(name = "idGeral")
    private String idGeral;
    // @Max(value=?)  @Min(value=?)
    // if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "documento")
    private String documento;
    @Basic(optional = false)
    @Column(name = "nomeOrigem")
    private String nomeOrigem;
    @Basic(optional = false)
    @Column(name = "idBancoOrigem")
    private int idBancoOrigem;
    @Basic(optional = false)
    @Column(name = "idBancoDestino")
    private int idBancoDestino;
    @Basic(optional = false)
    @Column(name = "cancelada")
    private boolean cancelada;
    @Basic(optional = false)
    @Column(name = "idTitulo")
    private String idTitulo;
    @Basic(optional = false)
    @Column(name = "hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;
    @Basic(optional = false)
    @Column(name = "usuarioInclusao")
    private String usuarioInclusao;
    @Column(name = "usuarioAlteracao")
    private String usuarioAlteracao;
    @Basic(optional = false)
    @Column(name = "retirado")
    private boolean retirado;
    @Column(name = "descRetirado")
    private String descRetirado;
    @Column(name = "dataRetirado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRetirado;

    public Retiradas() {
    }

    public long getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(long idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public String getIdGeral() {
        return idGeral;
    }

    public void setIdGeral(String idGeral) {
        this.idGeral = idGeral;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNomeOrigem() {
        return nomeOrigem;
    }

    public void setNomeOrigem(String nomeOrigem) {
        this.nomeOrigem = nomeOrigem;
    }

    public int getIdBancoOrigem() {
        return idBancoOrigem;
    }

    public void setIdBancoOrigem(int idBancoOrigem) {
        this.idBancoOrigem = idBancoOrigem;
    }

    public int getIdBancoDestino() {
        return idBancoDestino;
    }

    public void setIdBancoDestino(int idBancoDestino) {
        this.idBancoDestino = idBancoDestino;
    }

    public boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    public String getIdTitulo() {
        return idTitulo;
    }

    public void setIdTitulo(String idTitulo) {
        this.idTitulo = idTitulo;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getUsuarioInclusao() {
        return usuarioInclusao;
    }

    public void setUsuarioInclusao(String usuarioInclusao) {
        this.usuarioInclusao = usuarioInclusao;
    }

    public String getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(String usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (int) (this.idTransferencia ^ (this.idTransferencia >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Retiradas other = (Retiradas) obj;
        return this.idTransferencia == other.idTransferencia;
    }

}
