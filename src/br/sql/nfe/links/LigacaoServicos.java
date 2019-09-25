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
public class LigacaoServicos {

    public Servicos ligacaoServicos(String uf, String tpAmb, int mod) throws IllegalArgumentException {
        Servicos ser = null;
        uf = uf.toUpperCase();
        switch (uf) {
            case "RO":
                ser = new ServicosSVRS(tpAmb, mod);
                break;
            case "SP":
                ser = new ServicosSP(tpAmb, mod);
                break;
            case "RS":
                ser = new ServicosRS(tpAmb, mod);
                break;
            default:
                throw new IllegalArgumentException("Estado Invalido: " + uf);
        }
        return ser;
    }
}
