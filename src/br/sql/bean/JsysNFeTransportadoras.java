package br.sql.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "JsysNFeTransportadoras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysNFeTransportadoras.findAll", query = "SELECT j FROM JsysNFeTransportadoras j"),
    @NamedQuery(name = "JsysNFeTransportadoras.findById", query = "SELECT j FROM JsysNFeTransportadoras j WHERE j.id = :id"),
    @NamedQuery(name = "JsysNFeTransportadoras.findByCnpjCpf", query = "SELECT j FROM JsysNFeTransportadoras j WHERE j.cnpjCpf = :cnpjCpf"),
    @NamedQuery(name = "JsysNFeTransportadoras.findByXNome", query = "SELECT j FROM JsysNFeTransportadoras j WHERE j.xNome = :xNome"),
    @NamedQuery(name = "JsysNFeTransportadoras.findByIeRg", query = "SELECT j FROM JsysNFeTransportadoras j WHERE j.ieRg = :ieRg"),
    @NamedQuery(name = "JsysNFeTransportadoras.findByXEnder", query = "SELECT j FROM JsysNFeTransportadoras j WHERE j.xEnder = :xEnder"),
    @NamedQuery(name = "JsysNFeTransportadoras.findByXMun", query = "SELECT j FROM JsysNFeTransportadoras j WHERE j.xMun = :xMun"),
    @NamedQuery(name = "JsysNFeTransportadoras.findByUf", query = "SELECT j FROM JsysNFeTransportadoras j WHERE j.uf = :uf"),
    @NamedQuery(name = "JsysNFeTransportadoras.findByFone", query = "SELECT j FROM JsysNFeTransportadoras j WHERE j.fone = :fone"),
    @NamedQuery(name = "JsysNFeTransportadoras.findByEmail", query = "SELECT j FROM JsysNFeTransportadoras j WHERE j.email = :email")})
public class JsysNFeTransportadoras implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id 
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "CnpjCpf")
    private String cnpjCpf;
    @Basic(optional = false)
    @Column(name = "xNome")
    private String xNome;
    @Basic(optional = false)
    @Column(name = "ieRg")
    private String ieRg;
    @Column(name = "xEnder")
    private String xEnder;
    @Column(name = "xMun")
    private String xMun;
    @Column(name = "UF")
    private String uf;
    @Lob
    @Column(name = "obs")
    private String obs;
    @Column(name = "fone")
    private String fone;
    @Column(name = "email")
    private String email;

    public JsysNFeTransportadoras() {
    }

    public JsysNFeTransportadoras(Integer id) {
        this.id = id;
    }

    public JsysNFeTransportadoras(Integer id, String cnpjCpf, String xNome, String ieRg) {
        this.id = id;
        this.cnpjCpf = cnpjCpf;
        this.xNome = xNome;
        this.ieRg = ieRg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        String oldCnpjCpf = this.cnpjCpf;
        this.cnpjCpf = cnpjCpf;
        changeSupport.firePropertyChange("cnpjCpf", oldCnpjCpf, cnpjCpf);
    }

    public String getXNome() {
        return xNome;
    }

    public void setXNome(String xNome) {
        String oldXNome = this.xNome;
        this.xNome = xNome;
        changeSupport.firePropertyChange("XNome", oldXNome, xNome);
    }

    public String getIeRg() {
        return ieRg;
    }

    public void setIeRg(String ieRg) {
        String oldIeRg = this.ieRg;
        this.ieRg = ieRg;
        changeSupport.firePropertyChange("ieRg", oldIeRg, ieRg);
    }

    public String getXEnder() {
        return xEnder;
    }

    public void setXEnder(String xEnder) {
        String oldXEnder = this.xEnder;
        this.xEnder = xEnder;
        changeSupport.firePropertyChange("XEnder", oldXEnder, xEnder);
    }

    public String getXMun() {
        return xMun;
    }

    public void setXMun(String xMun) {
        String oldXMun = this.xMun;
        this.xMun = xMun;
        changeSupport.firePropertyChange("XMun", oldXMun, xMun);
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        String oldUf = this.uf;
        this.uf = uf;
        changeSupport.firePropertyChange("uf", oldUf, uf);
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        String oldObs = this.obs;
        this.obs = obs;
        changeSupport.firePropertyChange("obs", oldObs, obs);
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        String oldFone = this.fone;
        this.fone = fone;
        changeSupport.firePropertyChange("fone", oldFone, fone);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String oldEmail = this.email;
        this.email = email;
        changeSupport.firePropertyChange("email", oldEmail, email);
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
        if (!(object instanceof JsysNFeTransportadoras)) {
            return false;
        }
        JsysNFeTransportadoras other = (JsysNFeTransportadoras) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysNFeTransportadoras[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
