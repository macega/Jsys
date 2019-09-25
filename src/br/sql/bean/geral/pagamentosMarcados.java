package br.sql.bean.geral;

import br.sql.bean.Pagamentos;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Juliano Alves Medina
 */
public class pagamentosMarcados implements Serializable {

    private int id;
    private String seq;
    private Pagamentos pag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public Pagamentos getPag() {
        return pag;
    }

    public void setPag(Pagamentos pag) {
        this.pag = pag;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.seq);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final pagamentosMarcados other = (pagamentosMarcados) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.seq, other.seq);
    }

    @Override
    public String toString() {
        return "pagamentosMarcados{" + "id=" + id + ", seq=" + seq + '}';
    }
}
