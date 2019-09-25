package br.sql.util;

import java.util.Date;

/**
 *
 * @author Juliano Alves Medina
 */
public class timerKeys {

    //private DateTimeZone timeZone = DateTimeZone.forID("GMT"); 
    private Boolean ativado;
    private Date d1;

    public timerKeys() {
        ativado = false;
        d1 = ManagerData.getDate();
    }

    public boolean isAtivado() {
        return ativado;
    }

    private Double getTempo() {
        if (d1 != null) {
            Date d2 = ManagerData.getDate();
            return (double) (d2.getTime() - d1.getTime());
        }
        return 0.0;
    }

    public void ativar() {
        if (!ativado) {
            ativado = true;
            d1 = ManagerData.getDate();
        }
    }

    public void desativar() {
        ativado = false;
        d1 = null;
    }

    /**
     *
     * @return Retorna true caso for um cartão
     */
    public boolean isCartao() {
        return isCartao(200.0);
    }

    /**
     *
     * @param time EX: 250.0
     * @return falso se o tempo digitado for exedido
     */
    public boolean isCartao(Double time) {
        if (getTempo() <= time) {
            desativar();
            return true;
        } else {
            desativar();
            return false;
        }
    }
}
