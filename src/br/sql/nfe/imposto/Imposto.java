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
public interface Imposto {

    public void calculaImposto(String cst);

    public void proximoImposto(Imposto proximoImposto);
}
