package br.sql.bean.views;

import java.util.Objects;

/**
 *
 * @author Juliano Alves Medina
 */
public class ufIBGE {

    private int idUF;
    private String nomeUF;
    private String UF;
    private Double area;

    public ufIBGE(int idUF, String nomeUF, String UF, Double area) {
        this.idUF = idUF;
        this.nomeUF = nomeUF;
        this.UF = UF;
        this.area = area;
    }

    public ufIBGE() {
    }

    public int getIdUF() {
        return idUF;
    }

    public void setIdUF(int idUF) {
        this.idUF = idUF;
    }

    public String getNomeUF() {
        return nomeUF;
    }

    public void setNomeUF(String nomeUF) {
        this.nomeUF = nomeUF;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idUF;
        hash = 67 * hash + Objects.hashCode(this.UF);
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
        final ufIBGE other = (ufIBGE) obj;
        if (this.idUF != other.idUF) {
            return false;
        }
        if (!Objects.equals(this.UF, other.UF)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ufIBGE{" + "idUF=" + idUF + ", nomeUF=" + nomeUF + ", UF=" + UF + ", area=" + area + '}';
    }

}
