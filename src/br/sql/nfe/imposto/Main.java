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
public class Main {

    public static void main(String[] args) {
        // VOCE CHAMARIA APENAS UMA VEZ O MÉTODO ABAIXO, PASSANDO A CST
        // E AS  PROPRIAS CLASSES IRAO DECIDIR QUAL IMPOSTO UTILIZAR
        // E TAMBEM PASSA AS DEMAIS INFORMACOES DOS IMPOSTOS.....

        // EU SÓ COLOQUEI AS TRES POSSIBILIDADES PRA VOCE VER QUE O CODIGO
        // DECIDE POR ELE MESMO QUAL IMPOSTO UTILIZAR
        new LigacaoImpostos().ligacaoImpostos("10"/*DEMAIS VALORES DO IMPOSTO*/);
        new LigacaoImpostos().ligacaoImpostos("00"/*DEMAIS VALORES DO IMPOSTO*/);
        new LigacaoImpostos().ligacaoImpostos("20"/*DEMAIS VALORES DO IMPOSTO*/);
    }
}
