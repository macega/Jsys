package br.sql.bean;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Juliano Alves Medina
 */
@Embeddable
public class JsysReceberPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idReceber")
    private Integer idReceber;
    @Basic(optional = false)
    @Column(name = "seqReceber")
    private int seqReceber;

    public JsysReceberPK() {
    }

    public JsysReceberPK(Integer idReceber, int seqReceber) {
        this.idReceber = idReceber;
        this.seqReceber = seqReceber;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idReceber;
        hash += (int) seqReceber;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysReceberPK)) {
            return false;
        }
        JsysReceberPK other = (JsysReceberPK) object;
        if (!Objects.equals(this.idReceber, other.idReceber)) {
            return false;
        }
        return this.seqReceber == other.seqReceber;
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysReceberPK[ idReceber=" + idReceber + ", seqReceber=" + seqReceber + " ]";
    }
    
}
