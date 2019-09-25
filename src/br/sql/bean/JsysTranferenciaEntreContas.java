
package br.sql.bean;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysTranferenciaEntreContas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysTranferenciaEntreContas.findByAberturaDodia", query = "SELECT j FROM JsysTranferenciaEntreContas j WHERE (j.data = :data) AND (j.idBancoDestino = :idBancoDestino) and (j.idGeral = :idGeral)"),
    @NamedQuery(name = "JsysTranferenciaEntreContas.findByIdandIdGeral", query = "SELECT j FROM JsysTranferenciaEntreContas j WHERE j.id = :id AND j.idGeral = :idGeral"),
    @NamedQuery(name = "JsysTranferenciaEntreContas.findAll", query = "SELECT j FROM JsysTranferenciaEntreContas j"),
    @NamedQuery(name = "JsysTranferenciaEntreContas.findById", query = "SELECT j FROM JsysTranferenciaEntreContas j WHERE j.id = :id"),
    @NamedQuery(name = "JsysTranferenciaEntreContas.findByIdGeral", query = "SELECT j FROM JsysTranferenciaEntreContas j WHERE j.idGeral = :idGeral"),
    @NamedQuery(name = "JsysTranferenciaEntreContas.findByValor", query = "SELECT j FROM JsysTranferenciaEntreContas j WHERE j.valor = :valor"),
    @NamedQuery(name = "JsysTranferenciaEntreContas.findByData", query = "SELECT j FROM JsysTranferenciaEntreContas j WHERE j.data = :data"),
    @NamedQuery(name = "JsysTranferenciaEntreContas.findByDescricao", query = "SELECT j FROM JsysTranferenciaEntreContas j WHERE j.descricao = :descricao"),
    @NamedQuery(name = "JsysTranferenciaEntreContas.findByIdBancoOrigem", query = "SELECT j FROM JsysTranferenciaEntreContas j WHERE j.idBancoOrigem = :idBancoOrigem"),
    @NamedQuery(name = "JsysTranferenciaEntreContas.findByIdBancoDestino", query = "SELECT j FROM JsysTranferenciaEntreContas j WHERE j.idBancoDestino = :idBancoDestino"),
    @NamedQuery(name = "JsysTranferenciaEntreContas.findByCancelada", query = "SELECT j FROM JsysTranferenciaEntreContas j WHERE j.cancelada = :cancelada"),
    @NamedQuery(name = "JsysTranferenciaEntreContas.findByIdTitulo", query = "SELECT j FROM JsysTranferenciaEntreContas j WHERE j.idTitulo = :idTitulo"),
    @NamedQuery(name = "JsysTranferenciaEntreContas.findByDataInclusao", query = "SELECT j FROM JsysTranferenciaEntreContas j WHERE j.dataInclusao = :dataInclusao"),
    @NamedQuery(name = "JsysTranferenciaEntreContas.findByUsuarioInclusao", query = "SELECT j FROM JsysTranferenciaEntreContas j WHERE j.usuarioInclusao = :usuarioInclusao"),
    @NamedQuery(name = "JsysTranferenciaEntreContas.findByDataAlteracao", query = "SELECT j FROM JsysTranferenciaEntreContas j WHERE j.dataAlteracao = :dataAlteracao"),
    @NamedQuery(name = "JsysTranferenciaEntreContas.findByUsuarioAlteracao", query = "SELECT j FROM JsysTranferenciaEntreContas j WHERE j.usuarioAlteracao = :usuarioAlteracao"),
    @NamedQuery(name = "JsysTranferenciaEntreContas.findByRetirado", query = "SELECT j FROM JsysTranferenciaEntreContas j WHERE j.retirado = :retirado"),
    @NamedQuery(name = "JsysTranferenciaEntreContas.findByDescRetirado", query = "SELECT j FROM JsysTranferenciaEntreContas j WHERE j.descRetirado = :descRetirado"),
    @NamedQuery(name = "JsysTranferenciaEntreContas.findByDataRetirado", query = "SELECT j FROM JsysTranferenciaEntreContas j WHERE j.dataRetirado = :dataRetirado")})
public class JsysTranferenciaEntreContas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "idGeral")
    private String idGeral;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Column(name = "descricao")
    private String descricao;
    
    @JoinColumn(name = "idBancoOrigem", referencedColumnName = "idCliente") //, insertable = true, updatable = true)
    @OneToOne(optional = false)
    private JsysClientes idBancoOrigem;
    
    @JoinColumn(name = "idBancoDestino", referencedColumnName = "idCliente") //, insertable = true, updatable = true)
    @OneToOne(optional = false)
    private JsysClientes idBancoDestino;
    
    @Basic(optional = false)
    @Column(name = "cancelada")
    private boolean cancelada;
    @Basic(optional = false)
    @Column(name = "idTitulo")
    private String idTitulo;
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
    @Column(name = "retirado")
    private boolean retirado;
    @Column(name = "descRetirado")
    private String descRetirado;
    @Column(name = "dataRetirado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRetirado;

    public JsysTranferenciaEntreContas() {
    }

    public JsysTranferenciaEntreContas(Long id) {
        this.id = id;
    }

    public JsysTranferenciaEntreContas(Long id, String idGeral, BigDecimal valor, Date data, JsysClientes idBancoOrigem, JsysClientes idBancoDestino, boolean cancelada, String idTitulo, Date dataInclusao, String usuarioInclusao, boolean retirado) {
        this.id = id;
        this.idGeral = idGeral;
        this.valor = valor;
        this.data = data;
        this.idBancoOrigem = idBancoOrigem;
        this.idBancoDestino = idBancoDestino;
        this.cancelada = cancelada;
        this.idTitulo = idTitulo;
        this.dataInclusao = dataInclusao;
        this.usuarioInclusao = usuarioInclusao;
        this.retirado = retirado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public JsysClientes getIdBancoOrigem() {
        return idBancoOrigem;
    }

    public void setIdBancoOrigem(JsysClientes idBancoOrigem) {
        this.idBancoOrigem = idBancoOrigem;
    }

    public JsysClientes getIdBancoDestino() {
        return idBancoDestino;
    }

    public void setIdBancoDestino(JsysClientes idBancoDestino) {
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
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysTranferenciaEntreContas)) {
            return false;
        }
        JsysTranferenciaEntreContas other = (JsysTranferenciaEntreContas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysTranferenciaEntreContas[ id=" + id + " ]";
    }
    
}
