package br.sql.bean;

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
@Table(name = "jsysTitulos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysTitulos.findAll", query = "SELECT j FROM JsysTitulos j ORDER BY j.idcupom"),
    @NamedQuery(name = "JsysTitulos.findAllAtivos", query = "SELECT j FROM JsysTitulos j WHERE j.ativo = TRUE ORDER BY j.idcupom"),
    @NamedQuery(name = "JsysTitulos.findByIdTitulo", query = "SELECT j FROM JsysTitulos j WHERE j.idTitulo = :idTitulo ORDER BY j.idcupom"),
    @NamedQuery(name = "JsysTitulos.findByTipoFatura", query = "SELECT j FROM JsysTitulos j WHERE j.tipoFatura = :tipoFatura ORDER BY j.idcupom"),
    @NamedQuery(name = "JsysTitulos.findByIdcupom", query = "SELECT j FROM JsysTitulos j WHERE j.idcupom = :idcupom ORDER BY j.idcupom"),
    @NamedQuery(name = "JsysTitulos.findByAtivo", query = "SELECT j FROM JsysTitulos j WHERE j.ativo = :ativo ORDER BY j.idcupom")})
public class JsysTitulos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idTitulo")
    private String idTitulo;
    @Basic(optional = false)
    @Column(name = "tipoFatura")
    private String tipoFatura;
    @Basic(optional = false)
    @Column(name = "idcupom")
    private String idcupom;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @Column(name = "tipoPagamento")
    private String tipoPagamento;
    @Column(name = "card")
    private Boolean card;
    @Column(name = "cnpjCredenciadora")
    private String cnpjCredenciadora;
    @Column(name = "tipoBandeira")
    private String tipoBandeira;
    @Column(name = "tipoIntegracao")
    private String tipoIntegracao;
    @Column(name = "baixaReceber")
    private Boolean baixaReceber;

    public JsysTitulos() {
        this.ativo = false;
        this.card = false;
        this.baixaReceber = false;
    }

    public String getIdTitulo() {
        return idTitulo;
    }

    public void setIdTitulo(String idTitulo) {
        this.idTitulo = idTitulo;
    }

    public String getTipoFatura() {
        return tipoFatura;
    }

    public void setTipoFatura(String tipoFatura) {
        this.tipoFatura = tipoFatura;
    }

    public String getIdcupom() {
        return idcupom;
    }

    public void setIdcupom(String idcupom) {
        this.idcupom = idcupom;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Boolean getCard() {
        return card;
    }

    public void setCard(Boolean card) {
        this.card = card;
    }

    public String getCnpjCredenciadora() {
        return cnpjCredenciadora;
    }

    public void setCnpjCredenciadora(String cnpjCredenciadora) {
        this.cnpjCredenciadora = cnpjCredenciadora;
    }

    public String getTipoBandeira() {
        return tipoBandeira;
    }

    public void setTipoBandeira(String tipoBandeira) {
        this.tipoBandeira = tipoBandeira;
    }

    public String getTipoIntegracao() {
        return tipoIntegracao;
    }

    public void setTipoIntegracao(String tipoIntegracao) {
        this.tipoIntegracao = tipoIntegracao;
    }

    public Boolean getBaixaReceber() {
        return baixaReceber;
    }

    public void setBaixaReceber(Boolean baixaReceber) {
        this.baixaReceber = baixaReceber;
    }

}
