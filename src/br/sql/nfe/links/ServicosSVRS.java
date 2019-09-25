package br.sql.nfe.links;

/**
 *
 * @author Juliano Alves Medina
 */
public class ServicosSVRS implements Servicos {

    private final String tpAmb;
    private final int mod;
    private Servicos proximoServico;

    public ServicosSVRS(String tpAmb, int mod) {
        this.tpAmb = tpAmb;
        this.mod = mod;
    }

    @Override
    public String getRecepcaoEvento() {
        if (tpAmb.equals(ConstantesFiscal.AMBIENTE.PRODUCAO)) {
            if (mod == ConstantesFiscal.NFC_E) {
                return "https://nfce.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx";
            } else {
                return "https://nfe.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx";
            }
        } else {
            if (mod == ConstantesFiscal.NFC_E) {
                return "https://nfce-homologacao.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx";
            } else {
                return "https://nfe-homologacao.svrs.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx";
            }
        }
    }

    @Override
    public String getNfeRecepcao() {
        if (tpAmb.equals(ConstantesFiscal.AMBIENTE.PRODUCAO)) {
            if (mod== ConstantesFiscal.NFC_E) {
                return null;
            } else {
                return null;
            }
        } else {
            if (mod== ConstantesFiscal.NFC_E) {
                return null;
            } else {
                return null;
            }
        }
    }

    @Override
    public String getNfeRetRecepcao() {
        if (tpAmb.equals(ConstantesFiscal.AMBIENTE.PRODUCAO)) {
            if (mod== ConstantesFiscal.NFC_E) {
                return null;
            } else {
                return null;
            }
        } else {
            if (mod== ConstantesFiscal.NFC_E) {
                return null;
            } else {
                return null;
            }
        }
    }

    @Override
    public String getNfeInutilizacao() {
        if (tpAmb.equals(ConstantesFiscal.AMBIENTE.PRODUCAO)) {
            if (mod== ConstantesFiscal.NFC_E) {
                return "https://nfce.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx";
            } else {
                return "https://nfe.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx";
            }
        } else {
            if (mod== ConstantesFiscal.NFC_E) {
                return "https://nfce-homologacao.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx";
            } else {
                return "https://nfe-homologacao.svrs.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx";
            }
        }
    }

    @Override
    public String getNfeConsultaProtocolo() {
        if (tpAmb.equals(ConstantesFiscal.AMBIENTE.PRODUCAO)) {
            if (mod== ConstantesFiscal.NFC_E) {
                return "https://nfce.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx";
            } else {
                return "https://nfe.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx";
            }
        } else {
            if (mod== ConstantesFiscal.NFC_E) {
                return "https://nfce-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx";
            } else {
                return "https://nfe-homologacao.svrs.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx";
            }
        }
    }

    @Override
    public String getNfeStatusServico() {
        if (tpAmb.equals(ConstantesFiscal.AMBIENTE.PRODUCAO)) {
            if (mod== ConstantesFiscal.NFC_E) {
                return "https://nfce.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx";
            } else {
                return "https://nfe.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx";
            }
        } else {
            if (mod== ConstantesFiscal.NFC_E) {
                return "https://nfce-homologacao.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx";
            } else {
                return "https://nfe-homologacao.svrs.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx";
            }
        }
    }

    @Override
    public String getNfeConsultaCadastro() {
        if (tpAmb.equals(ConstantesFiscal.AMBIENTE.PRODUCAO)) {
            if (mod== ConstantesFiscal.NFC_E) {
                return null;
            } else {
                return "https://cad.svrs.rs.gov.br/ws/cadconsultacadastro/cadconsultacadastro2.asmx";
            }
        } else {
            if (mod== ConstantesFiscal.NFC_E) {
                return null;
            } else {
                return "https://cad-homologacao.svrs.rs.gov.br/ws/cadconsultacadastro/cadconsultacadastro2.asmx";
            }
        }
    }

    @Override
    public String getNFeAutorizacao() {
        if (tpAmb.equals(ConstantesFiscal.AMBIENTE.PRODUCAO)) {
            if (mod== ConstantesFiscal.NFC_E) {
                return "https://nfce.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx";
            } else {
                return "https://nfe.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx";
            }
        } else {
            if (mod== ConstantesFiscal.NFC_E) {
                return "https://nfce-homologacao.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx";
            } else {
                return "https://nfe-homologacao.svrs.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx";
            }
        }
    }

    @Override
    public String getNFeRetAutorizacao() {
        if (tpAmb.equals(ConstantesFiscal.AMBIENTE.PRODUCAO)) {
            if (mod== ConstantesFiscal.NFC_E) {
                return "https://nfce.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx";
            } else {
                return "https://nfe.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx";
            }
        } else {
            if (mod== ConstantesFiscal.NFC_E) {
                return "https://nfce-homologacao.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx";
            } else {
                return "https://nfe-homologacao.svrs.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx";
            }
        }
    }

    @Override
    public String getNFCeConsulta() {
        if (tpAmb.equals(ConstantesFiscal.AMBIENTE.PRODUCAO)) {
            if (mod== ConstantesFiscal.NFC_E) {
                return "http://www.nfce.sefin.ro.gov.br";
            } else {
                return null;
            }
        } else {
            if (mod== ConstantesFiscal.NFC_E) {
                return "http://www.nfce.sefin.ro.gov.br";
            } else {
                return null;
            }
        }
    }

    @Override
    public String getNFCeConsultaQRCode() {
        if (tpAmb.equals(ConstantesFiscal.AMBIENTE.PRODUCAO)) {
            if (mod== ConstantesFiscal.NFC_E) {
                return "http://www.nfce.sefin.ro.gov.br/consultanfce/consulta.jsp";
            } else {
                return null;
            }
        } else {
            if (mod== ConstantesFiscal.NFC_E) {
                return "http://www.nfce.sefin.ro.gov.br/consultanfce/consulta.jsp"; //"http://www.nfce.sefin.ro.gov.br/consultaAmbHomologacao.jsp";
            } else {
                return null;
            }
        }
    }

//    @Override
//    public void proximoServico(Servicos proximoServico) {
//        // nao fas nada
//        System.out.println("Chegou o Fim dos serviços");
//    }
//
//    @Override
//    public Servicos procurarServicos(String uf) {
//        if ("RO".equals(uf)) {
//            return this;
//        } else {
//            // chega ou fim dos serviços
//            System.out.println("Servico nao encontrado");
//            return null;
//        }
//    }

}
