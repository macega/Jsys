package br.sql.bean.views;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "jsysRebebimentoAgrupado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysRebebimentoAgrupado.findAll", query = "SELECT j FROM JsysRebebimentoAgrupado j")
    ,
    @NamedQuery(name = "JsysRebebimentoAgrupado.findById", query = "SELECT j FROM JsysRebebimentoAgrupado j WHERE j.id = :id")
    ,
    @NamedQuery(name = "JsysRebebimentoAgrupado.findByIdReceber", query = "SELECT j FROM JsysRebebimentoAgrupado j WHERE j.idReceber = :idReceber")
    ,
    @NamedQuery(name = "JsysRebebimentoAgrupado.findByTotalLiqudo", query = "SELECT j FROM JsysRebebimentoAgrupado j WHERE j.totalLiqudo = :totalLiqudo")
    ,
    @NamedQuery(name = "JsysRebebimentoAgrupado.findByIdTitulo", query = "SELECT j FROM JsysRebebimentoAgrupado j WHERE j.idTitulo = :idTitulo")
    ,
    @NamedQuery(name = "JsysRebebimentoAgrupado.findByTipoPagamento", query = "SELECT j FROM JsysRebebimentoAgrupado j WHERE j.tipoPagamento = :tipoPagamento")
    ,
    @NamedQuery(name = "JsysRebebimentoAgrupado.findByCard", query = "SELECT j FROM JsysRebebimentoAgrupado j WHERE j.card = :card")
    ,
    @NamedQuery(name = "JsysRebebimentoAgrupado.findByCnpjCredenciadora", query = "SELECT j FROM JsysRebebimentoAgrupado j WHERE j.cnpjCredenciadora = :cnpjCredenciadora")
    ,
    @NamedQuery(name = "JsysRebebimentoAgrupado.findByTipoBandeira", query = "SELECT j FROM JsysRebebimentoAgrupado j WHERE j.tipoBandeira = :tipoBandeira")})
public class JsysRebebimentoAgrupado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Column(name = "idReceber")
    private Integer idReceber;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalLiqudo")
    private BigDecimal totalLiqudo;
    @Basic(optional = false)
    @Column(name = "idTitulo")
    private String idTitulo;
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
    @Column(name = "indPag")
    private String indPag;

    public JsysRebebimentoAgrupado() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdReceber() {
        return idReceber;
    }

    public void setIdReceber(Integer idReceber) {
        this.idReceber = idReceber;
    }

    public BigDecimal getTotalLiqudo() {
        return totalLiqudo;
    }

    public void setTotalLiqudo(BigDecimal totalLiqudo) {
        this.totalLiqudo = totalLiqudo;
    }

    public String getIdTitulo() {
        return idTitulo;
    }

    public void setIdTitulo(String idTitulo) {
        this.idTitulo = idTitulo;
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

    public String getIndPag() {
        return indPag;
    }

    public void setIndPag(String indPag) {
        this.indPag = indPag;
    }

}
