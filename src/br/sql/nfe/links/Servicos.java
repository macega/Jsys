package br.sql.nfe.links;

/**
 *
 * @author Juliano Alves Medina
 */
public interface Servicos {
    
    //Versao

    public String getRecepcaoEvento();
    //public String getVersaoRecepcaoEvento();

    public String getNfeRecepcao();
    //public String getVersaoNfeRecepcao();

    public String getNfeRetRecepcao();
    //public String getVersaoNfeRetRecepcao();

    public String getNfeInutilizacao();
    //public String getVersaoNfeInutilizacao();

    public String getNfeConsultaProtocolo();
    //public String getVersaoNfeConsultaProtocolo();

    public String getNfeStatusServico();
    //public String getVersaoNfeStatusServico();

    public String getNfeConsultaCadastro();
    //public String getVersaoNfeConsultaCadastro();

    public String getNFeAutorizacao();
    //public String getVersaoNFeAutorizacao();

    public String getNFeRetAutorizacao();
    //public String getVersaoNFeRetAutorizacao();

    public String getNFCeConsulta();
    //public String getVersaoNFCeConsulta();

    public String getNFCeConsultaQRCode();
    //public String getVersaoNFCeConsultaQRCode();

//    public void proximoServico(Servicos proximoServico);
//
//    public Servicos procurarServicos(String uf);
}
