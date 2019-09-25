package br.sql.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Juliano Alves Medina
 */
@Embeddable
public class JsysReceberBaixaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idReceber")
    private Integer idReceber;
    @Basic(optional = false)
    @Column(name = "seqReceber")
    private int seqReceber;
    @Basic(optional = false)
    @Column(name = "id")
    private int id;

    public JsysReceberBaixaPK() {
    }

    public JsysReceberBaixaPK(Integer idReceber, int seqReceber, int id) {
        this.idReceber = idReceber;
        this.seqReceber = seqReceber;
        this.id = id;
    }

    public Integer getIdReceber() {
        return idReceber;
    }

    public void setIdReceber(Integer idReceber) {
        this.idReceber = idReceber;
    }

    public int getSeqReceber() {
        return seqReceber;
    }

    public void setSeqReceber(int seqReceber) {
        this.seqReceber = seqReceber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idReceber;
        hash += (int) seqReceber;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysReceberBaixaPK)) {
            return false;
        }
        JsysReceberBaixaPK other = (JsysReceberBaixaPK) object;
        if (this.idReceber != other.idReceber) {
            return false;
        }
        if (this.seqReceber != other.seqReceber) {
            return false;
        }
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysReceberBaixaPK[ idReceber=" + idReceber + ", seqReceber=" + seqReceber + ", id=" + id + " ]";
    }
    
}
