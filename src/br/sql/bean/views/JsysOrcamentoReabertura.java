/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.bean.views;

import br.sql.bean.JsysOrcamentoItens;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysOrcamentoReabertura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysOrcamentoReabertura.findAll", query = "SELECT j FROM JsysOrcamentoReabertura j"),
    @NamedQuery(name = "JsysOrcamentoReabertura.findById", query = "SELECT j FROM JsysOrcamentoReabertura j WHERE j.id = :id"),
    @NamedQuery(name = "JsysOrcamentoReabertura.findByData", query = "SELECT j FROM JsysOrcamentoReabertura j WHERE j.data = :data"),
    @NamedQuery(name = "JsysOrcamentoReabertura.findByIdVendedor", query = "SELECT j FROM JsysOrcamentoReabertura j WHERE j.idVendedor = :idVendedor"),
    @NamedQuery(name = "JsysOrcamentoReabertura.findByVendedor", query = "SELECT j FROM JsysOrcamentoReabertura j WHERE j.vendedor = :vendedor"),
    @NamedQuery(name = "JsysOrcamentoReabertura.findByValorLiquido", query = "SELECT j FROM JsysOrcamentoReabertura j WHERE j.valorLiquido = :valorLiquido"),
    @NamedQuery(name = "JsysOrcamentoReabertura.findByIdFarura", query = "SELECT j FROM JsysOrcamentoReabertura j WHERE j.idFarura = :idFarura")})
public class JsysOrcamentoReabertura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @Column(name = "idVendedor")
    private String idVendedor;
    @Basic(optional = false)
    @Column(name = "vendedor")
    private String vendedor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valorLiquido")
    private BigDecimal valorLiquido;
    @Basic(optional = false)
    @Column(name = "idFarura")
    private String idFarura;
    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "JsysOrcamentoReabertura")
//    private Collection<JsysOrcamentoItens> jsysOrcamentoItensCollection;

    public JsysOrcamentoReabertura() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public BigDecimal getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(BigDecimal valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public String getIdFarura() {
        return idFarura;
    }

    public void setIdFarura(String idFarura) {
        this.idFarura = idFarura;
    }

//    public Collection<JsysOrcamentoItens> getJsysOrcamentoItensCollection() {
//        return jsysOrcamentoItensCollection;
//    }
//
//    public void setJsysOrcamentoItensCollection(Collection<JsysOrcamentoItens> jsysOrcamentoItensCollection) {
//        this.jsysOrcamentoItensCollection = jsysOrcamentoItensCollection;
//    }
    
}
