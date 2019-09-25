/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sql.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "natureza")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Natureza.findAll", query = "SELECT n FROM Natureza n"),
    @NamedQuery(name = "Natureza.findByTipo", query = "SELECT n FROM Natureza n WHERE n.tipo = :tipo"),
    @NamedQuery(name = "Natureza.findByEstadual", query = "SELECT n FROM Natureza n WHERE n.estadual = :estadual"),
    @NamedQuery(name = "Natureza.findByCfop", query = "SELECT n FROM Natureza n WHERE n.cfop = :cfop")})
public class Natureza implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Lob
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "estadual")
    private boolean estadual;
    @Id
    @Basic(optional = false)
    @Column(name = "cfop")
    private String cfop;

    public Natureza() {
    }

    public Natureza(String cfop) {
        this.cfop = cfop;
    }

    public Natureza(String cfop, String nome, String tipo, boolean estadual) {
        this.cfop = cfop;
        this.nome = nome;
        this.tipo = tipo;
        this.estadual = estadual;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean getEstadual() {
        return estadual;
    }

    public void setEstadual(boolean estadual) {
        this.estadual = estadual;
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cfop != null ? cfop.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Natureza)) {
            return false;
        }
        Natureza other = (Natureza) object;
        return (this.cfop != null || other.cfop == null) && (this.cfop == null || this.cfop.equals(other.cfop));
    }

    @Override
    public String toString() {
        return "br.sql.bean.Natureza[ cfop=" + cfop + " ]";
    }
    
}
