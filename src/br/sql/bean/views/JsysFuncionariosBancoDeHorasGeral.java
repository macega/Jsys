/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
@Table(name = "jsysFuncionariosBancoDeHorasGeral")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysFuncionariosBancoDeHorasGeral.findAll", query = "SELECT j FROM JsysFuncionariosBancoDeHorasGeral j"),
    @NamedQuery(name = "JsysFuncionariosBancoDeHorasGeral.findByIdFuncionario", query = "SELECT j FROM JsysFuncionariosBancoDeHorasGeral j WHERE j.idFuncionario = :idFuncionario"),
    @NamedQuery(name = "JsysFuncionariosBancoDeHorasGeral.findByFuncionario", query = "SELECT j FROM JsysFuncionariosBancoDeHorasGeral j WHERE j.funcionario = :funcionario"),
    @NamedQuery(name = "JsysFuncionariosBancoDeHorasGeral.findByTotalMinutosExtra", query = "SELECT j FROM JsysFuncionariosBancoDeHorasGeral j WHERE j.totalMinutosExtra = :totalMinutosExtra")})
public class JsysFuncionariosBancoDeHorasGeral implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idFuncionario")
    private int idFuncionario;
    @Basic(optional = false)
    @Column(name = "Funcionario")
    private String funcionario;
    @Column(name = "totalMinutosExtra")
    private Integer totalMinutosExtra;

    public JsysFuncionariosBancoDeHorasGeral() {
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public Integer getTotalMinutosExtra() {
        return totalMinutosExtra;
    }

    public void setTotalMinutosExtra(Integer totalMinutosExtra) {
        this.totalMinutosExtra = totalMinutosExtra;
    }
    
}
