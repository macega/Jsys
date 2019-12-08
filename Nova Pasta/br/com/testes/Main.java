/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.testes;

import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;

/**
 *
 * @author julia
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(EstadosEnum.valueOf("RO").getCodigoUF());
        
    }

}
