/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Administrador
 */
@Entity
@Table(name = "jsysPlanoPagto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysPlanoPagto.findAll", query = "SELECT j FROM JsysPlanoPagto j"),
    @NamedQuery(name = "JsysPlanoPagto.findByIdPlano", query = "SELECT j FROM JsysPlanoPagto j WHERE j.idPlano = :idPlano"),
    @NamedQuery(name = "JsysPlanoPagto.findByDescPlano", query = "SELECT j FROM JsysPlanoPagto j WHERE j.descPlano = :descPlano"),
    @NamedQuery(name = "JsysPlanoPagto.findByFormaPagto", query = "SELECT j FROM JsysPlanoPagto j WHERE j.formaPagto = :formaPagto"),
    @NamedQuery(name = "JsysPlanoPagto.findByDocEntrada", query = "SELECT j FROM JsysPlanoPagto j WHERE j.docEntrada = :docEntrada"),
    @NamedQuery(name = "JsysPlanoPagto.findByDocRestante", query = "SELECT j FROM JsysPlanoPagto j WHERE j.docRestante = :docRestante"),
    @NamedQuery(name = "JsysPlanoPagto.findByQantParcelas", query = "SELECT j FROM JsysPlanoPagto j WHERE j.qantParcelas = :qantParcelas"),
    @NamedQuery(name = "JsysPlanoPagto.findByTemEntrada", query = "SELECT j FROM JsysPlanoPagto j WHERE j.temEntrada = :temEntrada"),
    @NamedQuery(name = "JsysPlanoPagto.findByIntervalo", query = "SELECT j FROM JsysPlanoPagto j WHERE j.intervalo = :intervalo")})
public class JsysPlanoPagto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idPlano")
    private String idPlano;
    @Basic(optional = false)
    @Column(name = "descPlano")
    private String descPlano;
    @Basic(optional = false)
    @Column(name = "formaPagto")
    private String formaPagto;
    @Basic(optional = false)
    @Column(name = "docEntrada")
    private String docEntrada;
    @Basic(optional = false)
    @Column(name = "docRestante")
    private String docRestante;
    @Basic(optional = false)
    @Column(name = "QantParcelas")
    private String qantParcelas;
    @Basic(optional = false)
    @Column(name = "temEntrada")
    private boolean temEntrada;
    @Basic(optional = false)
    @Column(name = "intervalo")
    private short intervalo;

    public JsysPlanoPagto() {
    }

    public String getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(String idPlano) {
        this.idPlano = idPlano;
    }

    public String getDescPlano() {
        return descPlano;
    }

    public void setDescPlano(String descPlano) {
        this.descPlano = descPlano;
    }

    public String getFormaPagto() {
        return formaPagto;
    }

    public void setFormaPagto(String formaPagto) {
        this.formaPagto = formaPagto;
    }

    public String getDocEntrada() {
        return docEntrada;
    }

    public void setDocEntrada(String docEntrada) {
        this.docEntrada = docEntrada;
    }

    public String getDocRestante() {
        return docRestante;
    }

    public void setDocRestante(String docRestante) {
        this.docRestante = docRestante;
    }

    public String getQantParcelas() {
        return qantParcelas;
    }

    public void setQantParcelas(String qantParcelas) {
        this.qantParcelas = qantParcelas;
    }

    public boolean getTemEntrada() {
        return temEntrada;
    }

    public void setTemEntrada(boolean temEntrada) {
        this.temEntrada = temEntrada;
    }

    public short getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(short intervalo) {
        this.intervalo = intervalo;
    }
    
}
