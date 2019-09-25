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
 * @author Juliano Alves Medina  
 */
@Entity
@Table(name = "jsysFuncionarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysFuncionarios.findAll", query = "SELECT j FROM JsysFuncionarios j"),
    @NamedQuery(name = "JsysFuncionarios.findByIdFuncionario", query = "SELECT j FROM JsysFuncionarios j WHERE j.idFuncionario = :idFuncionario"),
    @NamedQuery(name = "JsysFuncionarios.findByNomeCliente", query = "SELECT j FROM JsysFuncionarios j WHERE j.nomeCliente = :nomeCliente"),
    @NamedQuery(name = "JsysFuncionarios.findByTurno", query = "SELECT j FROM JsysFuncionarios j WHERE j.turno = :turno"),
    @NamedQuery(name = "JsysFuncionarios.findByCategoria", query = "SELECT j FROM JsysFuncionarios j WHERE j.categoria = :categoria")})
public class JsysFuncionarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idFuncionario")
    private int idFuncionario;
    @Basic(optional = false)
    @Column(name = "nomeCliente")
    private String nomeCliente;
    @Basic(optional = false)
    @Column(name = "turno")
    private String turno;
    @Basic(optional = false)
    @Column(name = "categoria")
    private String categoria;

    public JsysFuncionarios() {
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
