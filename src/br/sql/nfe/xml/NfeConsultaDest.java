package br.sql.nfe.xml;

import br.sql.bean.JsysParametros;
import br.sql.util.Retorna;

/**
 *
 * @author Juliano Alves Medina
 */
public class NfeConsultaDest {

    private final JsysParametros par = Retorna.JsysParametros();
    private String Xml;
    private String Id;
    private final String indNFe;
    private final String indEmi;
    private final String ultNSU;

//    /**
//     * 
//     * @param indNFe
//     * 0 = Todas as NF-e; 
//     * 1 = Somente as NF-e que ainda não tiveram manifestação do destinatário (Desconhecimento da operação, Operação não Realizada ou Confirmação da Operação); 
//     * 2 = Idem anterior, incluindo as NF-e que também não tiveram a Ciência da Operação.
//     * @param indEmi
//     * 0 = Todos os Emitentes / Remetentes;
//     * 1 = Somente as NF-e emitidas por emissores / remetentes que não tenham o mesmo CNPJ-Base do destinatário (para excluir as notas fiscais de transferência entre filiais).
//     * @param ultNSU 
//     * Último NSU recebido pela Empresa. Caso seja informado com zero,
//     * ou com um NSU muito antigo, a consulta retornará unicamente as
//     * notas fiscais que tenham sido recepcionadas nos últimos 15 dias.
//     */
//    public NfeConsultaDest(String indNFe, String indEmi, String ultNSU) {
//        this.indNFe = indNFe;
//        this.indEmi = indEmi;
//        this.ultNSU = ultNSU;
//    }
//
//    @Override
//    public boolean gerar() {
//        try {
//            Xml = getXml();
//            GravaNoArquivo gravador = new GravaNoArquivo();
//            gravador.salvarArquivo(Xml, Constantes.PASTA_XML_CONS_NFE_DEST, Id, "xml");
//            //event.setEnvEventoCancNFe(XmlEvento);
//            return true;// GerenteEntidade.insert(event, "");
//        } catch (Exception e) {
//            salvaLog.registraErro(this.getClass().getName(), "gerar", e);
//            return false;
//        }
//    }
//
//    private String getXml() {
//        try {
//            TConsNFeDest tConsNFeDest = new TConsNFeDest();
//            tConsNFeDest.setVersao("1.01");
//            tConsNFeDest.setTpAmb(Constantes.tpAmb);
//            tConsNFeDest.setXServ("CONSULTAR NFE DEST");
//            tConsNFeDest.setCNPJ(ManagerString.RemoveFormatoCpfCnpj(par.getCnpj()));
//            /**
//             * Indicador de NF-e consultada: 
//             * 0=Todas as NF-e; 
//             * 1=Somente as NF-e que ainda não tiveram manifestação do destinatário (Desconhecimento da operação, Operação não Realizada ou Confirmação da Operação); 
//             * 2=Idem anterior, incluindo as NF-e que também não tiveram a Ciência da Operação.
//             */
//            tConsNFeDest.setIndNFe(indNFe);
//            /**
//             * Indicador do Emissor da NF-e: 
//             * 0=Todos os Emitentes / Remetentes;
//             * 1=Somente as NF-e emitidas por emissores / remetentes que não tenham o mesmo CNPJ-Base do destinatário (para excluir as notas fiscais de transferência entre filiais).
//             */
//            tConsNFeDest.setIndEmi(indEmi);
//            /**
//             * Último NSU recebido pela Empresa. Caso seja informado com zero,
//             * ou com um NSU muito antigo, a consulta retornará unicamente as
//             * notas fiscais que tenham sido recepcionadas nos últimos 15 dias.
//             */
//            tConsNFeDest.setUltNSU(ultNSU);
//            return strValueOf(tConsNFeDest);
//        } catch (Exception e) {
//            salvaLog.registraErro(this.getClass().getName(), "getXml", e);
//            return null;
//        }
//    }
//
//    private String strValueOf(TConsNFeDest tConsNFeDest) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    public NfeConsultaDest(String Xml, String Id, String indNFe, String indEmi, String ultNSU) {
        this.Xml = Xml;
        this.Id = Id;
        this.indNFe = indNFe;
        this.indEmi = indEmi;
        this.ultNSU = ultNSU;
    }

}
