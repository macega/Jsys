package br.sql.nfe.links;

/**
 *
 * @author Juliano Alves Medina
 */
public class ServicosRS implements Servicos {

    private final String tpAmb;
    private final int mod;
    private Servicos proximoServico;

    public ServicosRS(String tpAmb, int mod) {
        this.tpAmb = tpAmb;
        this.mod = mod;
    }

    @Override
    public String getRecepcaoEvento() {
        return "servico RS";
    }

    @Override
    public String getNfeRecepcao() {
        return "servico RS";
    }

    @Override
    public String getNfeRetRecepcao() {
        return "servico RS";
    }

    @Override
    public String getNfeInutilizacao() {
        return "servico RS";
    }

    @Override
    public String getNfeConsultaProtocolo() {
        return "servico RS";
    }

    @Override
    public String getNfeStatusServico() {
        return "servico RS";
    }

    @Override
    public String getNfeConsultaCadastro() {
        return "servico RS";
    }

    @Override
    public String getNFeAutorizacao() {
        return "servico RS";
    }

    @Override
    public String getNFeRetAutorizacao() {
        return "servico RS";
    }

    @Override
    public String getNFCeConsulta() {
        return "servico RS";
    }

    @Override
    public String getNFCeConsultaQRCode() {
        return "servico RS";
    }

//    @Override
//    public void proximoServico(Servicos proximoServico) {
//        this.proximoServico = proximoServico;
//    }
//
//    @Override
//    public Servicos procurarServicos(String uf) {
//        if ("RS".equals(uf)) {
//            return this;
//        } else {
//            return this.proximoServico.procurarServicos(uf);
//        }
//    }

}
