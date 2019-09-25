/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
@Table(name = "fechamentoCaixa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FechamentoCaixa.findAll", query = "SELECT f FROM FechamentoCaixa f"),
    @NamedQuery(name = "FechamentoCaixa.findByIdFecamentoCaixa", query = "SELECT f FROM FechamentoCaixa f WHERE f.idFecamentoCaixa = :idFecamentoCaixa"),
    @NamedQuery(name = "FechamentoCaixa.findByIdCaixa", query = "SELECT f FROM FechamentoCaixa f WHERE f.idCaixa = :idCaixa"),
    @NamedQuery(name = "FechamentoCaixa.findByUsuario", query = "SELECT f FROM FechamentoCaixa f WHERE f.usuario = :usuario"),
    @NamedQuery(name = "FechamentoCaixa.findByData", query = "SELECT f FROM FechamentoCaixa f WHERE f.data = :data"),
    @NamedQuery(name = "FechamentoCaixa.findByIdTitulo", query = "SELECT f FROM FechamentoCaixa f WHERE f.idTitulo = :idTitulo"),
    @NamedQuery(name = "FechamentoCaixa.findByValorConferido", query = "SELECT f FROM FechamentoCaixa f WHERE f.valorConferido = :valorConferido"),
    @NamedQuery(name = "FechamentoCaixa.findByValorDifereca", query = "SELECT f FROM FechamentoCaixa f WHERE f.valorDifereca = :valorDifereca"),
    @NamedQuery(name = "FechamentoCaixa.findByValorSaldo", query = "SELECT f FROM FechamentoCaixa f WHERE f.valorSaldo = :valorSaldo")})
public class FechamentoCaixa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idFecamentoCaixa", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFecamentoCaixa;
    @Basic(optional = false)
    @Column(name = "idCaixa", nullable = false)
    private int idCaixa;
    @Basic(optional = false)
    @Column(name = "usuario", nullable = false, length = 25)
    private String usuario;
    @Basic(optional = false)
    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @Column(name = "idTitulo", nullable = false, length = 4)
    private String idTitulo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valorConferido", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorConferido;
    @Basic(optional = false)
    @Column(name = "valorDifereca", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorDifereca;
    @Basic(optional = false)
    @Column(name = "valorSaldo", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorSaldo;

    public FechamentoCaixa() {
    }

    public FechamentoCaixa(Integer idFecamentoCaixa) {
        this.idFecamentoCaixa = idFecamentoCaixa;
    }

    public FechamentoCaixa(Integer idFecamentoCaixa, int idCaixa, String usuario, Date data, String idTitulo, BigDecimal valorConferido, BigDecimal valorDifereca, BigDecimal valorSaldo) {
        this.idFecamentoCaixa = idFecamentoCaixa;
        this.idCaixa = idCaixa;
        this.usuario = usuario;
        this.data = data;
        this.idTitulo = idTitulo;
        this.valorConferido = valorConferido;
        this.valorDifereca = valorDifereca;
        this.valorSaldo = valorSaldo;
    }

    public Integer getIdFecamentoCaixa() {
        return idFecamentoCaixa;
    }

    public void setIdFecamentoCaixa(Integer idFecamentoCaixa) {
        this.idFecamentoCaixa = idFecamentoCaixa;
    }

    public int getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(int idCaixa) {
        this.idCaixa = idCaixa;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getIdTitulo() {
        return idTitulo;
    }

    public void setIdTitulo(String idTitulo) {
        this.idTitulo = idTitulo;
    }

    public BigDecimal getValorConferido() {
        return valorConferido;
    }

    public void setValorConferido(BigDecimal valorConferido) {
        this.valorConferido = valorConferido;
    }

    public BigDecimal getValorDifereca() {
        return valorDifereca;
    }

    public void setValorDifereca(BigDecimal valorDifereca) {
        this.valorDifereca = valorDifereca;
    }

    public BigDecimal getValorSaldo() {
        return valorSaldo;
    }

    public void setValorSaldo(BigDecimal valorSaldo) {
        this.valorSaldo = valorSaldo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFecamentoCaixa != null ? idFecamentoCaixa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FechamentoCaixa)) {
            return false;
        }
        FechamentoCaixa other = (FechamentoCaixa) object;
        if ((this.idFecamentoCaixa == null && other.idFecamentoCaixa != null) || (this.idFecamentoCaixa != null && !this.idFecamentoCaixa.equals(other.idFecamentoCaixa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.sql.bean.FechamentoCaixa[ idFecamentoCaixa=" + idFecamentoCaixa + " ]";
    }
    
}
