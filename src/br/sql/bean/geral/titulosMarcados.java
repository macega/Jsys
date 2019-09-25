package br.sql.bean.geral;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Juliano Alves Medina
 */
public class titulosMarcados {

    private Integer idReceber;
    private Integer seqReceber;
    private BigDecimal valor;
    private Date data;
    private int receber;

    public Integer getIdReceber() {
        return idReceber;
    }

    public void setIdReceber(Integer idReceber) {
        this.idReceber = idReceber;
    }

    public Integer getSeqReceber() {
        return seqReceber;
    }

    public void setSeqReceber(Integer seqReceber) {
        this.seqReceber = seqReceber;
    }

    
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getReceber() {
        return receber;
    }

    public void setReceber(int receber) {
        this.receber = receber;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public titulosMarcados() {
    }

    public titulosMarcados(Integer idReceber, Integer seqReceber, BigDecimal valor, int receber) {
        this.idReceber = idReceber;
        this.seqReceber = seqReceber;
        this.valor = valor;
        this.receber = receber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idReceber);
        hash = 97 * hash + Objects.hashCode(this.seqReceber);
        hash = 97 * hash + Objects.hashCode(this.valor);
        hash = 97 * hash + this.receber;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final titulosMarcados other = (titulosMarcados) obj;
        if (!Objects.equals(this.idReceber, other.idReceber)) {
            return false;
        }
        if (!Objects.equals(this.seqReceber, other.seqReceber)) {
            return false;
        }
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        return this.receber == other.receber;
    }

    @Override
    public String toString() {
        return "titulosMarcados{idReceber=" + idReceber + ", seqReceber=" + seqReceber + ", valor=" + valor + ", receber=" + receber + '}';
    }
}
