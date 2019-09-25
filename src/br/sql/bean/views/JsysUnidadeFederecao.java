package br.sql.bean.views;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysUnidadeFederecao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysUnidadeFederecao.findAll", query = "SELECT j FROM JsysUnidadeFederecao j"),
    @NamedQuery(name = "JsysUnidadeFederecao.findByIdUf", query = "SELECT j FROM JsysUnidadeFederecao j WHERE j.idUf = :idUf"),
    @NamedQuery(name = "JsysUnidadeFederecao.findByUf", query = "SELECT j FROM JsysUnidadeFederecao j WHERE j.uf = :uf")})
public class JsysUnidadeFederecao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idUf")
    private String idUf;
    @Basic(optional = false)
    @Column(name = "uf")
    private String uf;

    public JsysUnidadeFederecao() {
    }

    public String getIdUf() {
        return idUf;
    }

    public void setIdUf(String idUf) {
        this.idUf = idUf;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
    
}
