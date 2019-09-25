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
public class LigacaoImpostos {
    
    public void ligacaoImpostos(String cst) {
        Imposto icms00 = new ICMS00();
        Imposto icms10 = new ICMS10();
        Imposto icms20 = new ICMS20();
        
        icms00.proximoImposto(icms10);
        icms10.proximoImposto(icms20);
        
        icms00.calculaImposto(cst);
    }
}
