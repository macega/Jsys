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
@Table(name = "jsysPacotefichas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysPacotefichas.findAll", query = "SELECT j FROM JsysPacotefichas j"),
    @NamedQuery(name = "JsysPacotefichas.findByIdOrcamento", query = "SELECT j FROM JsysPacotefichas j WHERE j.idOrcamento = :idOrcamento"),
    @NamedQuery(name = "JsysPacotefichas.findByFicha", query = "SELECT j FROM JsysPacotefichas j WHERE j.ficha = :ficha"),
    @NamedQuery(name = "JsysPacotefichas.findByData", query = "SELECT j FROM JsysPacotefichas j WHERE j.data = :data"),
    @NamedQuery(name = "JsysPacotefichas.findByVendedor", query = "SELECT j FROM JsysPacotefichas j WHERE j.vendedor = :vendedor"),
    @NamedQuery(name = "JsysPacotefichas.findByCliente", query = "SELECT j FROM JsysPacotefichas j WHERE j.cliente = :cliente"),
    @NamedQuery(name = "JsysPacotefichas.findByValorLiquido", query = "SELECT j FROM JsysPacotefichas j WHERE j.valorLiquido = :valorLiquido"),
    @NamedQuery(name = "JsysPacotefichas.findByQuitado", query = "SELECT j FROM JsysPacotefichas j WHERE j.quitado = :quitado"),
    @NamedQuery(name = "JsysPacotefichas.findByFormaPagto", query = "SELECT j FROM JsysPacotefichas j WHERE j.formaPagto = :formaPagto"),
    @NamedQuery(name = "JsysPacotefichas.findByidBanco", query = "SELECT j FROM JsysPacotefichas j WHERE j.idBanco = :idBanco")})
public class JsysPacotefichas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idOrcamento")
    private int idOrcamento;
    @Column(name = "ficha")
    private Integer ficha;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @Column(name = "vendedor")
    private String vendedor;
    @Basic(optional = false)
    @Column(name = "cliente")
    private String cliente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valorLiquido")
    private BigDecimal valorLiquido;
    @Basic(optional = false)
    @Column(name = "Quitado")
    private int quitado;
    @Basic(optional = false)
    @Column(name = "formaPagto")
    private String formaPagto;
    @Column(name = "idBanco")
    private Integer idBanco;
    
    public JsysPacotefichas() {
    }

    public int getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(int idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public Integer getFicha() {
        return ficha;
    }

    public void setFicha(Integer ficha) {
        this.ficha = ficha;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(BigDecimal valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public int getQuitado() {
        return quitado;
    }

    public void setQuitado(int quitado) {
        this.quitado = quitado;
    }

    public String getFormaPagto() {
        return formaPagto;
    }

    public void setFormaPagto(String formaPagto) {
        this.formaPagto = formaPagto;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idOrcamento;
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
        final JsysPacotefichas other = (JsysPacotefichas) obj;
        return this.idOrcamento == other.idOrcamento;
    } 
    
    
}
