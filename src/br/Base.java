package br;

/**
 *
 * @author juliano
 */
public class Base {

    public Base() {
    }

    public Base(String Nome) {
        this.Nome = Nome;
    }

    private String Nome;

    public String getNome() {
        if (Nome != null) {
            return Nome;
        } else {
            String bases[] = JavaApplicationJsys.INI.getString("LISTA BASE", "LISTA").split(",");
            return bases[0];
        }

    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }
}
