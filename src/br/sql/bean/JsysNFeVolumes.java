package br.sql.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysNFeVolumes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysNFeVolumes.findAll", query = "SELECT j FROM JsysNFeVolumes j"),
    @NamedQuery(name = "JsysNFeVolumes.findById", query = "SELECT j FROM JsysNFeVolumes j WHERE j.id = :id"),
    @NamedQuery(name = "JsysNFeVolumes.findByQVol", query = "SELECT j FROM JsysNFeVolumes j WHERE j.qVol = :qVol"),
    @NamedQuery(name = "JsysNFeVolumes.findByEsp", query = "SELECT j FROM JsysNFeVolumes j WHERE j.esp = :esp"),
    @NamedQuery(name = "JsysNFeVolumes.findByMarca", query = "SELECT j FROM JsysNFeVolumes j WHERE j.marca = :marca"),
    @NamedQuery(name = "JsysNFeVolumes.findByNVol", query = "SELECT j FROM JsysNFeVolumes j WHERE j.nVol = :nVol"),
    @NamedQuery(name = "JsysNFeVolumes.findByPesoL", query = "SELECT j FROM JsysNFeVolumes j WHERE j.pesoL = :pesoL"),
    @NamedQuery(name = "JsysNFeVolumes.findByPesoB", query = "SELECT j FROM JsysNFeVolumes j WHERE j.pesoB = :pesoB")})
public class JsysNFeVolumes implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "qVol")
    private Long qVol;
    @Column(name = "esp")
    private String esp;
    @Column(name = "marca")
    private String marca;
    @Column(name = "nVol")
    private String nVol;
    // @Max(value=?)  @Min(value=?)
    //if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pesoL")
    private BigDecimal pesoL;
    @Column(name = "pesoB")
    private BigDecimal pesoB;
    @JoinColumn(name = "nfe_id", referencedColumnName = "nfe_id")
    @ManyToOne(optional = false)
    private JsysNFe nfeId;

    public JsysNFeVolumes() {
    }

    public JsysNFeVolumes(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        Long oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public Long getQVol() {
        return qVol;
    }

    public void setQVol(Long qVol) {
        Long oldQVol = this.qVol;
        this.qVol = qVol;
        changeSupport.firePropertyChange("QVol", oldQVol, qVol);
    }

    public String getEsp() {
        return esp;
    }

    public void setEsp(String esp) {
        String oldEsp = this.esp;
        this.esp = esp;
        changeSupport.firePropertyChange("esp", oldEsp, esp);
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        String oldMarca = this.marca;
        this.marca = marca;
        changeSupport.firePropertyChange("marca", oldMarca, marca);
    }

    public String getNVol() {
        return nVol;
    }

    public void setNVol(String nVol) {
        String oldNVol = this.nVol;
        this.nVol = nVol;
        changeSupport.firePropertyChange("NVol", oldNVol, nVol);
    }

    public BigDecimal getPesoL() {
        return pesoL;
    }

    public void setPesoL(BigDecimal pesoL) {
        BigDecimal oldPesoL = this.pesoL;
        this.pesoL = pesoL;
        changeSupport.firePropertyChange("pesoL", oldPesoL, pesoL);
    }

    public BigDecimal getPesoB() {
        return pesoB;
    }

    public void setPesoB(BigDecimal pesoB) {
        BigDecimal oldPesoB = this.pesoB;
        this.pesoB = pesoB;
        changeSupport.firePropertyChange("pesoB", oldPesoB, pesoB);
    }

    public JsysNFe getNfeId() {
        return nfeId;
    }

    public void setNfeId(JsysNFe nfeId) {
        JsysNFe oldNfeId = this.nfeId;
        this.nfeId = nfeId;
        changeSupport.firePropertyChange("nfeId", oldNfeId, nfeId);
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
        if (!(object instanceof JsysNFeVolumes)) {
            return false;
        }
        JsysNFeVolumes other = (JsysNFeVolumes) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysNFeVolumes[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
