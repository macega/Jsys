package br.sql.nfe.transmisor;

/**
 *
 * @author Juliano Alves Medina
 */
public interface transmitirInterface {

    public String getMensagem();

    public void setMensagem(String mensagem);

    public Boolean transmitir(String chaveAcesso, String tpAmb, int mod) throws Exception;

}
