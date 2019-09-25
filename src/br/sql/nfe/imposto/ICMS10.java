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
public class ICMS10 implements Imposto{

    private Imposto proximoImposto;

    @Override
    public void calculaImposto(String cst) {
        if (cst.equals("10")) {
            System.out.println("Imposto ICMS10");
        } else {
            System.out.println("fui para o proximo");
            proximoImposto.calculaImposto(cst);
        }
    }

    @Override
    public void proximoImposto(Imposto proximoImposto) {
        this.proximoImposto = proximoImposto;
    }
}
