/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sql.nfe.imposto;

/**
 *
 * @author Juliano Alves Medina
 */
public class ICMS20 implements Imposto {

    private Imposto proximoImposto;

    @Override
    public void calculaImposto(String cst) {
        if (cst.equals("20")) {
            System.out.println("Imposto ICMS20");
        } else {
            System.out.println("cheguei ao fim ");
            // NAO SETA PROXIMO IMPOSTO, LANCE UMA EXCESSAO SE QUISER
        }
    }

    @Override
    public void proximoImposto(Imposto proximoImposto) {
        // NAO FAZ NADA
    }
}
