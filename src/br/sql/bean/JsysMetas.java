package br.sql.bean;

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

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysMetas")
@NamedQueries({
    @NamedQuery(name = "JsysMetas.findAll", query = "SELECT j FROM JsysMetas j"),
    @NamedQuery(name = "JsysMetas.findByMes", query = "SELECT j FROM JsysMetas j WHERE j.mes BETWEEN :dataInicial AND :dataFinal"),
    @NamedQuery(name = "JsysMetas.findByLoja", query = "SELECT j FROM JsysMetas j WHERE j.loja = :loja"),
    @NamedQuery(name = "JsysMetas.findByVendedor", query = "SELECT j FROM JsysMetas j WHERE j.vendedor = :vendedor")})
public class JsysMetas implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "mes")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mes;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "loja")
    private BigDecimal loja;
    
    @Basic(optional = false)
    @Column(name = "vendedor")
    private BigDecimal vendedor;

    public Date getMes() {
        return mes;
    }

    public void setMes(Date mes) {
        this.mes = mes;
    }

    public BigDecimal getLoja() {
        return loja;
    }

    public void setLoja(BigDecimal loja) {
        this.loja = loja;
    }

    public BigDecimal getVendedor() {
        return vendedor;
    }

    public void setVendedor(BigDecimal vendedor) {
        this.vendedor = vendedor;
    }
}
