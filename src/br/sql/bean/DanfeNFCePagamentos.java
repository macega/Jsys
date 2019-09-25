/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.bean;

import br.sql.util.Retorna;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 *
 * @author Juliano Alves Medina
 */
public class DanfeNFCePagamentos {

    private String formaPagamento;
    private BigDecimal vTotalPago;

    /**
     * FORMA PAGAMENTO = forma na qual o pagamento da NFC-e foi efetuado (podem
     * ocorrer mais de uma forma de pagamento, devendo neste caso ser indicado o
     * montante parcial do pagamento para a respectiva forma. Exemplo: em
     * dinheiro, em cheque, etc.
     *
     * @return
     */
    public String getFormaPagamento() {
        return formaPagamento;
    }

    /**
     * FORMA PAGAMENTO = forma na qual o pagamento da NFC-e foi efetuado (podem
     * ocorrer mais de uma forma de pagamento, devendo neste caso ser indicado o
     * montante parcial do pagamento para a respectiva forma. Exemplo: em
     * dinheiro, em cheque, etc.
     *
     * @param formaPagamento
     */
    public void setFormaPagamento(String formaPagamento) {
        java.util.Map<Object, Object> filtro = new HashMap<>();
        filtro.put("idcupom", formaPagamento);
        JsysTitulos titulo = (JsysTitulos) Retorna.findOneResult("JsysTitulos.findByIdcupom", filtro);
        if (titulo != null) {
            this.formaPagamento = titulo.getTipoPagamento().substring(3);
        } else {
            this.formaPagamento = formaPagamento;
        }
    }

    /**
     * VALOR PAGO = valor pago efetivamente na forma de pagamento identificada
     * imediatamente acima.
     *
     * @return
     */
    public BigDecimal getvTotalPago() {
        return vTotalPago;
    }

    /**
     * VALOR PAGO = valor pago efetivamente na forma de pagamento identificada
     * imediatamente acima.
     *
     * @param vTotalPago
     */
    public void setvTotalPago(String vTotalPago) {
        this.vTotalPago = new BigDecimal(vTotalPago);
    }
}
