/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.nfe.links;

/**
 *
 * @author Juliano Alves Medina
 */
public class TestServicos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Servicos ser = new LigacaoServicos().ligacaoServicos("ba", "1", ConstantesFiscal.NFC_E);
        if (ser != null) {
            System.out.print("status: ");
            System.out.print("inutilizacao: ");
        }
    }

}
