/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.bean.views;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "funcionariosPontoMesAtual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FuncionariosPontoMesAtual.findAll", query = "SELECT f FROM FuncionariosPontoMesAtual f"),
    @NamedQuery(name = "FuncionariosPontoMesAtual.findById", query = "SELECT f FROM FuncionariosPontoMesAtual f WHERE f.id = :id"),
    @NamedQuery(name = "FuncionariosPontoMesAtual.findByEntrada", query = "SELECT f FROM FuncionariosPontoMesAtual f WHERE f.entrada = :entrada"),
    @NamedQuery(name = "FuncionariosPontoMesAtual.findByIntervaloInicio", query = "SELECT f FROM FuncionariosPontoMesAtual f WHERE f.intervaloInicio = :intervaloInicio"),
    @NamedQuery(name = "FuncionariosPontoMesAtual.findByIntervaloFim", query = "SELECT f FROM FuncionariosPontoMesAtual f WHERE f.intervaloFim = :intervaloFim"),
    @NamedQuery(name = "FuncionariosPontoMesAtual.findByIntervalo15Inicio", query = "SELECT f FROM FuncionariosPontoMesAtual f WHERE f.intervalo15Inicio = :intervalo15Inicio"),
    @NamedQuery(name = "FuncionariosPontoMesAtual.findByIntervalo15Fim", query = "SELECT f FROM FuncionariosPontoMesAtual f WHERE f.intervalo15Fim = :intervalo15Fim"),
    @NamedQuery(name = "FuncionariosPontoMesAtual.findBySaida", query = "SELECT f FROM FuncionariosPontoMesAtual f WHERE f.saida = :saida"),
    @NamedQuery(name = "FuncionariosPontoMesAtual.findByIdFuncionario", query = "SELECT f FROM FuncionariosPontoMesAtual f WHERE f.idFuncionario = :idFuncionario order by f.entrada")})
public class FuncionariosPontoMesAtual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entrada;
    @Column(name = "intervaloInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date intervaloInicio;
    @Column(name = "intervaloFim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date intervaloFim;
    @Column(name = "intervalo15Inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date intervalo15Inicio;
    @Column(name = "intervalo15Fim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date intervalo15Fim;
    @Column(name = "Saida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saida;
    //@JoinColumn(name = "idFuncionario", referencedColumnName = "idFuncionario")
    //@ManyToOne
    @Column(name = "idFuncionario")
    @Basic(optional = false)
    private int idFuncionario;
    @Column(name = "Funcionario")
    @Basic(optional = false)
    private String Funcionario;
    @Lob
    @Column(name = "obs")
    private String obs;

    public FuncionariosPontoMesAtual() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getIntervaloInicio() {
        return intervaloInicio;
    }

    public void setIntervaloInicio(Date intervaloInicio) {
        this.intervaloInicio = intervaloInicio;
    }

    public Date getIntervaloFim() {
        return intervaloFim;
    }

    public void setIntervaloFim(Date intervaloFim) {
        this.intervaloFim = intervaloFim;
    }

    public Date getIntervalo15Inicio() {
        return intervalo15Inicio;
    }

    public void setIntervalo15Inicio(Date intervalo15Inicio) {
        this.intervalo15Inicio = intervalo15Inicio;
    }

    public Date getIntervalo15Fim() {
        return intervalo15Fim;
    }

    public void setIntervalo15Fim(Date intervalo15Fim) {
        this.intervalo15Fim = intervalo15Fim;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getFuncionario() {
        return Funcionario;
    }

    public void setFuncionario(String Funcionario) {
        this.Funcionario = Funcionario;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
