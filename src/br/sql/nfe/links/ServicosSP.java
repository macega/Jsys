package br.sql.nfe.links;

/**
 *
 * @author Juliano Alves Medina
 */
public class ServicosSP implements Servicos {
    
    private final String tpAmb;
    private final int mod;
    private Servicos proximoServico;

    public ServicosSP(String tpAmb, int mod) {
        this.tpAmb = tpAmb;
        this.mod = mod;
    }

    @Override
    public String getRecepcaoEvento() {
        return "servico SP";
    }

    @Override
    public String getNfeRecepcao() {
        return "servico SP";
    }

    @Override
    public String getNfeRetRecepcao() {
        return "servico SP";
    }

    @Override
    public String getNfeInutilizacao() {
        return "servico SP";
    }

    @Override
    public String getNfeConsultaProtocolo() {
        return "servico SP";
    }

    @Override
    public String getNfeStatusServico() {
        return "servico SP";
    }

    @Override
    public String getNfeConsultaCadastro() {
        return "servico SP";
    }

    @Override
    public String getNFeAutorizacao() {
        return "servico SP";
    }

    @Override
    public String getNFeRetAutorizacao() {
        return "servico SP";
    }

    @Override
    public String getNFCeConsulta() {
        return "servico SP";
    }

    @Override
    public String getNFCeConsultaQRCode() {
        return "servico SP";
    }

//    @Override
//    public void proximoServico(Servicos proximoServico) {
//        this.proximoServico = proximoServico;
//    }
//    
//    @Override
//    public Servicos procurarServicos(String uf) {
//        if ("SP".equals(uf)) {
//            return this;
//        } else {
//            return this.proximoServico.procurarServicos(uf);
//        }
//    }
}
