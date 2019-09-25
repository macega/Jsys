/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.adp.geral;

/**
 *
 * @author Juliano Alves Medina
 */
public class Importador {

    private String origem;
    private String destino;
    private String script;

    public Importador(String origem, String destino, String script) {
        this.origem = origem;
        this.destino = destino;
        this.script = script;
    }
    
    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(this.origem).append(";").append(this.destino).append(";").append(this.script).toString();
    }
    
    public StringBuilder toStringBuilder() {
        return new StringBuilder().append(this.origem).append(";").append(this.destino).append(";").append(this.script);
    }
}
