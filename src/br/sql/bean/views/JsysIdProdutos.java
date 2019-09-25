/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.bean.views;

import br.sql.bean.JsysProdutosT;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juliano ALves Medina
 */
@Entity
@Table(name = "jsysIdProdutos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysIdProdutos.findAll", query = "SELECT j FROM JsysIdProdutos j"),
    @NamedQuery(name = "JsysIdProdutos.findByCodigoBarra", query = "SELECT j FROM JsysIdProdutos j WHERE j.codigoBarra = :codigoBarra"),
    @NamedQuery(name = "JsysIdProdutos.findByIdProduto", query = "SELECT j FROM JsysIdProdutos j WHERE j.idProduto = :idProduto")})
public class JsysIdProdutos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigoBarra")
    private String codigoBarra;
    @ManyToOne
    @JoinColumn(name = "idProduto", referencedColumnName = "idProduto")
    private JsysProdutosT idProduto;

    public JsysIdProdutos() {
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public JsysProdutosT getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(JsysProdutosT idProduto) {
        this.idProduto = idProduto;
    }
}
