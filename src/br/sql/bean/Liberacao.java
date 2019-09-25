/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "Liberacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Liberacao.findAll", query = "SELECT l FROM Liberacao l"),
    @NamedQuery(name = "Liberacao.findById", query = "SELECT l FROM Liberacao l WHERE l.id = :id"),
    @NamedQuery(name = "Liberacao.findByTabelaOrigem", query = "SELECT l FROM Liberacao l WHERE l.tabelaOrigem = :tabelaOrigem"),
    @NamedQuery(name = "Liberacao.findByIdOrigem", query = "SELECT l FROM Liberacao l WHERE l.idOrigem = :idOrigem"),
    @NamedQuery(name = "Liberacao.findByUsuario", query = "SELECT l FROM Liberacao l WHERE l.usuario = :usuario"),
    @NamedQuery(name = "Liberacao.findByData", query = "SELECT l FROM Liberacao l WHERE l.data = :data"),
    @NamedQuery(name = "Liberacao.findByHora", query = "SELECT l FROM Liberacao l WHERE l.hora = :hora"),
    @NamedQuery(name = "Liberacao.findByTipo", query = "SELECT l FROM Liberacao l WHERE l.tipo = :tipo"),
    @NamedQuery(name = "Liberacao.findByUsuarioLogado", query = "SELECT l FROM Liberacao l WHERE l.usuarioLogado = :usuarioLogado")})
public class Liberacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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

    public Liberacao() {
        this.motivo = "";
    }

    public Liberacao(Integer id) {
        this.id = id;
        this.motivo = "";
    }

    public Liberacao(String tabelaOrigem, String idOrigem, String motivo, String usuario, String tipo, String usuarioLogado) {
        this.tabelaOrigem = tabelaOrigem;
        this.idOrigem = idOrigem;
        this.motivo = motivo;
        this.usuario = usuario;
        this.tipo = tipo;
        this.usuarioLogado = usuarioLogado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        this.motivo += motivo;
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

    public void setTipo(int tipo) {
        switch (tipo) {
            case 0:
                this.tipo = "Permissao";
                break;
            case 1:
                this.tipo = "Cancelamento";
                break;
            case 2:
                this.tipo = "Caixa Reabertura";
                break;
            case 3:
                this.tipo = "Liberacao";
                break;
            case 4:
                this.tipo = "Exclusao";
                break;
            case 5:
                this.tipo = "Estorno";
                break;
            case 6:
                this.tipo = "Reimpressao";
                break;
            case 7:
                this.tipo = "Reabertura";
                break;
            case 8:
                this.tipo = "Devolucao";
                break;
            default:
                this.tipo = "Permissao";
                break;
        }
    }

    public String getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(String usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Liberacao)) {
            return false;
        }
        Liberacao other = (Liberacao) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "br.sql.bean.Liberacao[ id=" + id + " ]";
    }
}
