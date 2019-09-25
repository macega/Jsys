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
@Table(name = "listaLiberacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListaLiberacao.findAll", query = "SELECT l FROM ListaLiberacao l"),
    @NamedQuery(name = "ListaLiberacao.findByVendedor", query = "SELECT l FROM ListaLiberacao l WHERE l.vendedor = :vendedor"),
    @NamedQuery(name = "ListaLiberacao.findByCliente", query = "SELECT l FROM ListaLiberacao l WHERE l.cliente = :cliente")})
public class ListaLiberacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Lob
    @Column(name = "OBS")
    private String obs;
    @Column(name = "VENDEDOR")
    private String vendedor;
    @Column(name = "CLIENTE")
    private String cliente;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "tabelaOrigem")
    private String tabelaOrigem;
    @Basic(optional = false)
    @Column(name = "idOrigem")
    private String idOrigem;
    @Basic(optional = false)
    @Lob
    @Column(name = "motivo")
    private String motivo;
    @Basic(optional = false)
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Column(name = "hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "usuarioLogado")
    private String usuarioLogado;

    public ListaLiberacao() {
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTabelaOrigem() {
        return tabelaOrigem;
    }

    public void setTabelaOrigem(String tabelaOrigem) {
        this.tabelaOrigem = tabelaOrigem;
    }

    public String getIdOrigem() {
        return idOrigem;
    }

    public void setIdOrigem(String idOrigem) {
        this.idOrigem = idOrigem;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(String usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}
