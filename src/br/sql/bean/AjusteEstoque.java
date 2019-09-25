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
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "AjusteEstoque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AjusteEstoque.findAll", query = "SELECT a FROM AjusteEstoque a"),
    @NamedQuery(name = "AjusteEstoque.findByFechadoAndData", query = "SELECT a FROM AjusteEstoque a WHERE a.fechado = :fechado AND a.data BETWEEN :dataInicial AND :dataFinal"),
    @NamedQuery(name = "AjusteEstoque.findByIdAjusteEstoque", query = "SELECT a FROM AjusteEstoque a WHERE a.idAjusteEstoque = :idAjusteEstoque"),
    @NamedQuery(name = "AjusteEstoque.findByData", query = "SELECT a FROM AjusteEstoque a WHERE a.data BETWEEN :dataInicial AND :dataFinal"),
    @NamedQuery(name = "AjusteEstoque.findByIdUsuario", query = "SELECT a FROM AjusteEstoque a WHERE a.idUsuario = :idUsuario"),
    @NamedQuery(name = "AjusteEstoque.findByUsuario", query = "SELECT a FROM AjusteEstoque a WHERE a.usuario = :usuario"),
    @NamedQuery(name = "AjusteEstoque.findByTotalCompra", query = "SELECT a FROM AjusteEstoque a WHERE a.totalCompra = :totalCompra"),
    @NamedQuery(name = "AjusteEstoque.findByTotalVenda", query = "SELECT a FROM AjusteEstoque a WHERE a.totalVenda = :totalVenda"),
    @NamedQuery(name = "AjusteEstoque.findByFechado", query = "SELECT a FROM AjusteEstoque a WHERE a.fechado = :fechado"),
    @NamedQuery(name = "AjusteEstoque.findByCancelada", query = "SELECT a FROM AjusteEstoque a WHERE a.cancelada = :cancelada"),
    @NamedQuery(name = "AjusteEstoque.findByIdUsuarioCancelamento", query = "SELECT a FROM AjusteEstoque a WHERE a.idUsuarioCancelamento = :idUsuarioCancelamento")})
public class AjusteEstoque implements Serializable {
    @Transient
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAjusteEstoque")
    private Integer idAjusteEstoque;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private int idUsuario;
    @Basic(optional = false)
    @Column(name = "usuario")
    private String usuario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "totalCompra")
    private BigDecimal totalCompra;
    @Basic(optional = false)
    @Column(name = "totalVenda")
    private BigDecimal totalVenda;
    @Lob
    @Column(name = "obs")
    private String obs;
    @Basic(optional = false)
    @Column(name = "fechado")
    private boolean fechado;
    @Basic(optional = false)
    @Column(name = "cancelada")
    private boolean cancelada;
    @Column(name = "idUsuarioCancelamento")
    private Integer idUsuarioCancelamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ajusteEstoque")
    private Collection<AjusteEstoqueItens> ajusteEstoqueItensCollection;

    public AjusteEstoque() {
    }

    public AjusteEstoque(Integer idAjusteEstoque) {
        this.idAjusteEstoque = idAjusteEstoque;
    }

    /**
     *
     * @param idAjusteEstoque
     * @param data
     * @param idUsuario
     * @param usuario
     * @param totalCompra
     * @param totalVenda
     * @param fechado
     * @param cancelada
     */
    public AjusteEstoque(Integer idAjusteEstoque, Date data, int idUsuario, String usuario, BigDecimal totalCompra, BigDecimal totalVenda, boolean fechado, boolean cancelada) {
        this.idAjusteEstoque = idAjusteEstoque;
        this.data = data;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.totalCompra = totalCompra;
        this.totalVenda = totalVenda;
        this.fechado = fechado;
        this.cancelada = cancelada;
    }

    public Integer getIdAjusteEstoque() {
        return idAjusteEstoque;
    }

    public void setIdAjusteEstoque(Integer idAjusteEstoque) {
        Integer oldIdAjusteEstoque = this.idAjusteEstoque;
        this.idAjusteEstoque = idAjusteEstoque;
        changeSupport.firePropertyChange("idAjusteEstoque", oldIdAjusteEstoque, idAjusteEstoque);
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        Date oldData = this.data;
        this.data = data;
        changeSupport.firePropertyChange("data", oldData, data);
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        int oldIdUsuario = this.idUsuario;
        this.idUsuario = idUsuario;
        changeSupport.firePropertyChange("idUsuario", oldIdUsuario, idUsuario);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        String oldUsuario = this.usuario;
        this.usuario = usuario;
        changeSupport.firePropertyChange("usuario", oldUsuario, usuario);
    }

    public BigDecimal getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(BigDecimal totalCompra) {
        BigDecimal oldTotalCompra = this.totalCompra;
        this.totalCompra = totalCompra;
        changeSupport.firePropertyChange("totalCompra", oldTotalCompra, totalCompra);
    }

    public BigDecimal getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(BigDecimal totalVenda) {
        BigDecimal oldTotalVenda = this.totalVenda;
        this.totalVenda = totalVenda;
        changeSupport.firePropertyChange("totalVenda", oldTotalVenda, totalVenda);
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        String oldObs = this.obs;
        this.obs = obs;
        changeSupport.firePropertyChange("obs", oldObs, obs);
    }

    public boolean isFechado() {
        return fechado;
    }

    public void setFechado(boolean fechado) {
        boolean oldFechado = this.fechado;
        this.fechado = fechado;
        changeSupport.firePropertyChange("fechado", oldFechado, fechado);
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        boolean oldCancelada = this.cancelada;
        this.cancelada = cancelada;
        changeSupport.firePropertyChange("cancelada", oldCancelada, cancelada);
    }

    public Integer getIdUsuarioCancelamento() {
        return idUsuarioCancelamento;
    }

    public void setIdUsuarioCancelamento(Integer idUsuarioCancelamento) {
        Integer oldIdUsuarioCancelamento = this.idUsuarioCancelamento;
        this.idUsuarioCancelamento = idUsuarioCancelamento;
        changeSupport.firePropertyChange("idUsuarioCancelamento", oldIdUsuarioCancelamento, idUsuarioCancelamento);
    }

    @XmlTransient
    public Collection<AjusteEstoqueItens> getAjusteEstoqueItensCollection() {
        return ajusteEstoqueItensCollection;
    }

    public void setAjusteEstoqueItensCollection(Collection<AjusteEstoqueItens> ajusteEstoqueItensCollection) {
        this.ajusteEstoqueItensCollection = ajusteEstoqueItensCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAjusteEstoque != null ? idAjusteEstoque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AjusteEstoque)) {
            return false;
        }
        AjusteEstoque other = (AjusteEstoque) object;
        return (this.idAjusteEstoque != null || other.idAjusteEstoque == null) && (this.idAjusteEstoque == null || this.idAjusteEstoque.equals(other.idAjusteEstoque));
    }

    @Override
    public String toString() {
        return "br.sql.bean.AjusteEstoque[ idAjusteEstoque=" + idAjusteEstoque + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
