/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "jsysSubConta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysSubConta.findAll", query = "SELECT j FROM JsysSubConta j"),
    @NamedQuery(name = "JsysSubConta.findByIdConta", query = "SELECT j FROM JsysSubConta j WHERE j.jsysSubContaPK.idConta = :idConta"),
    @NamedQuery(name = "JsysSubConta.findByIdSubConta", query = "SELECT j FROM JsysSubConta j WHERE j.jsysSubContaPK.idSubConta = :idSubConta"),
    @NamedQuery(name = "JsysSubConta.findByIdGeral", query = "SELECT j FROM JsysSubConta j WHERE j.idGeral = :idGeral"),
    @NamedQuery(name = "JsysSubConta.findByDescricao", query = "SELECT j FROM JsysSubConta j WHERE j.descricao = :descricao"),
    @NamedQuery(name = "JsysSubConta.findByIdGrupo", query = "SELECT j FROM JsysSubConta j WHERE j.idGrupo = :idGrupo"),
    @NamedQuery(name = "JsysSubConta.findBySaiBanco", query = "SELECT j FROM JsysSubConta j WHERE j.saiBanco = :saiBanco"),
    @NamedQuery(name = "JsysSubConta.findByTipoConta", query = "SELECT j FROM JsysSubConta j WHERE j.tipoConta = :tipoConta")})
public class JsysSubConta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JsysSubContaPK jsysSubContaPK;
    @Basic(optional = false)
    @Column(name = "idGeral")
    private String idGeral;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "idGrupo")
    private String idGrupo;
    @Basic(optional = false)
    @Column(name = "saiBanco")
    private boolean saiBanco;
    @Basic(optional = false)
    @Column(name = "tipoConta")
    private short tipoConta;
    @Column(name = "dataInclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;
    @Basic(optional = false)
    @Column(name = "usuarioInclusao")
    private String usuarioInclusao;
    @Column(name = "dataAlteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @Basic(optional = true)
    @Column(name = "usuarioAlteracao")
    private String usuarioAlteracao;

    public JsysSubConta() {
    }

    public JsysSubConta(JsysSubContaPK jsysSubContaPK) {
        this.jsysSubContaPK = jsysSubContaPK;
    }

    public JsysSubConta(JsysSubContaPK jsysSubContaPK, String idGeral, String descricao, String idGrupo, boolean saiBanco, short tipoConta) {
        this.jsysSubContaPK = jsysSubContaPK;
        this.idGeral = idGeral;
        this.descricao = descricao;
        this.idGrupo = idGrupo;
        this.saiBanco = saiBanco;
        this.tipoConta = tipoConta;
    }

    public JsysSubConta(String idConta, String idSubConta) {
        this.jsysSubContaPK = new JsysSubContaPK(idConta, idSubConta);
    }

    public JsysSubContaPK getJsysSubContaPK() {
        return jsysSubContaPK;
    }

    public void setJsysSubContaPK(JsysSubContaPK jsysSubContaPK) {
        this.jsysSubContaPK = jsysSubContaPK;
    }

    public String getIdGeral() {
        return idGeral;
    }

    public void setIdGeral(String idGeral) {
        this.idGeral = idGeral;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public boolean getSaiBanco() {
        return saiBanco;
    }

    public void setSaiBanco(boolean saiBanco) {
        this.saiBanco = saiBanco;
    }

    public short getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(short tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public String getUsuarioInclusao() {
        return usuarioInclusao;
    }

    public void setUsuarioInclusao(String usuarioInclusao) {
        this.usuarioInclusao = usuarioInclusao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(String usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jsysSubContaPK != null ? jsysSubContaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysSubConta)) {
            return false;
        }
        JsysSubConta other = (JsysSubConta) object;
        if ((this.jsysSubContaPK == null && other.jsysSubContaPK != null) || (this.jsysSubContaPK != null && !this.jsysSubContaPK.equals(other.jsysSubContaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysSubConta_1[ jsysSubContaPK=" + jsysSubContaPK + " ]";
    }

}
