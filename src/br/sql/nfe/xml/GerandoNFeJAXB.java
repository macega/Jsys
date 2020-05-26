package br.sql.nfe.xml;

import br.JavaApplicationJsys;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.util.NFCeUtil;
import br.com.swconsultoria.nfe.util.WebServiceUtil;
import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnderEmi;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEndereco;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Dest;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Prod;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Emit;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Ide;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.InfAdic;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ICMSTot;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp.Transporta;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp.Vol;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe;
//import br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TUf;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TUfEmi;
//import br.com.swconsultoria.nfe.schema_4.retEnviNFe.TRetEnviNFe;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.sql.acesso.SQLDatabaseConnection;
import br.sql.bean.JsysNFe;
import br.sql.bean.JsysNFeLote;
import br.sql.bean.JsysNFeReferencias;
import br.sql.bean.JsysNFeTransportadoras;
import br.sql.bean.JsysNFeVolumes;
import br.sql.bean.JsysOrcamento;
import br.sql.bean.JsysOrcamentoItens;
import br.sql.bean.JsysParametros;
import br.sql.bean.Ncm;
import br.sql.bean.views.JsysRebebimentoAgrupado;
import br.sql.cadastros.TitulosJanela;
import br.sql.nfe.links.ConstantesFiscal;
import br.sql.janelas.utilitarios.ParametrosJanelas;
import br.sql.log.Log;
import br.sql.util.GravaNoArquivo;
import br.sql.util.ManagerData;
import br.sql.util.ManagerDecimal;
import br.sql.util.ManagerString;
import br.sql.util.Retorna;
import br.sql.util.Validar;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBException;
import org.brazilutils.br.cpfcnpj.CpfCnpj;
import javax.swing.JFrame;

/**
 * Geração do XML da NF-e (Versão 3.10).
 *
 * @author Juliano Alves Medina
 *
 */
public class GerandoNFeJAXB {

    private static final SQLDatabaseConnection DADOS = new SQLDatabaseConnection();
    private TEnviNFe enviNFe;
    private final JsysNFe jsysNFe;
    private final String chaveAcesso;
    private final JsysParametros par = Retorna.JsysParametros();
    private final String cpfCnpj;
    private final JsysOrcamento venda;
    private final TNFe nFe;
    private final InfNFe infNFe;
    private final Date data;
    //private String xmlNfe;
    private BigDecimal totalImpostoGeral;
    private double valorProd;
    private double valorDesc;
    private double baseICMS;
    private double baseST;
    private double valorICMS;
    private double valorST;
    private double valorIpi;
    private double valorPis;
    private double valorCofins;
    private Map<String, String> infos;
    private String mensagem = new String();
    private String CStat;

    /**
     *
     * @param jsysNFe
     * @param cpfCnpj
     * @param numeroNfe informar "0" para pegar sequencia do banco de dados
     */
    public GerandoNFeJAXB(JsysNFe jsysNFe, String cpfCnpj, String numeroNfe) {
        if (jsysNFe.getNfeId() == null) {
            this.jsysNFe = (JsysNFe) br.sql.acesso.ConnectionFactory.insert(jsysNFe);
        } else {
            this.jsysNFe = (JsysNFe) br.sql.acesso.ConnectionFactory.update(jsysNFe);
        }
        this.CStat = "999";
        this.nFe = new TNFe();
        this.infNFe = new InfNFe();
        this.data = ManagerData.getDate();
        this.jsysNFe.setDhEmi(this.data);
        this.jsysNFe.setDhSaiEnt(this.data);
        StringBuilder ch = new StringBuilder();
        ch.append(this.jsysNFe.getSerie());
        ch.append("1"); // emitida
        ch.append(this.jsysNFe.getMod());
        ch.append(this.jsysNFe.getTpAmb());
        if ("0".equals(numeroNfe)) {
            try {
                numeroNfe = String.valueOf(DADOS.sequenciaTabela("jsysNFe", "nNF", "concat(right(serie, 3), emitida, mod, tpAmb)", ch.toString()));
            } catch (Exception e) {
                Log.registraErro("GerandoNFeJAXB", "GerandoNFeJAXB", e);
            }
        }
        this.jsysNFe.setNNF(numeroNfe);
        ChaveAcessoNFe chave = new ChaveAcessoNFe(this.data, String.valueOf(this.jsysNFe.getMod()), this.jsysNFe.getSerie(), this.jsysNFe.getTpEmis(), this.jsysNFe.getNNF());
        this.chaveAcesso = chave.getChaveAcessoNFe();
        this.jsysNFe.setChaveAcesso(this.chaveAcesso);
        this.jsysNFe.setCDV(ManagerString.Right(this.chaveAcesso, 1).trim());
        this.jsysNFe.setCNF(ManagerString.Mid(this.chaveAcesso, 38, 46));
        Map<Object, Object> filtro = new HashMap<>();
        filtro.put("idOrcamento", this.jsysNFe.getVenda());
        this.venda = (JsysOrcamento) Retorna.findOneResult("JsysOrcamento.findByIdOrcamento", filtro);
        if (Validar.isNotNullOrWhite(cpfCnpj)) {
            this.cpfCnpj = ManagerString.RemoveFormatoCpfCnpj(cpfCnpj);
        } else {
            this.cpfCnpj = ManagerString.RemoveFormatoCpfCnpj(this.venda.getIdCliente().getCnpjCpf());
        }
        //operacaoDentroEstado = venda.getIdCliente().getEstado().equals(par.getUf());
        this.totalImpostoGeral = BigDecimal.ZERO;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem += this.mensagem.isEmpty() ? mensagem : System.lineSeparator() + mensagem;
    }

    public JsysNFe getJsysNfe() {
        return jsysNFe;
    }

    public JsysOrcamento getVenda() {
        return venda;
    }

    public TNFe getnFe() {
        return nFe;
    }

//    public String getXmlNfe() {
//        return xmlNfe;
//    }
    public String getChaveAcesso() {
        return chaveAcesso;
    }

    /**
     *
     * emitente do Documento Fiscal
     *
     * @return Identificação da Nota Fiscal eletrônica
     */
    private TNFe.InfNFe.Ide dadosDeIdentificacao() {
        Ide ide = new Ide();
        ide.setCUF(jsysNFe.getCUF());
        ide.setCNF(jsysNFe.getCNF());
        /*
         Informar a natureza da operação de que decorrer a saída ou a entrada, 
         tais como: venda, compra, transferência, devolução, importação, consignação, remessa 
         (para fins de demonstração, de industrialização ou outra), 
         conforme previsto na alínea 'i', inciso I, art. 19 do CONVÊNIO S/Nº, de 15 de dezembro de 1970.
         */
        ide.setNatOp(jsysNFe.getNatOp().trim());
        /*
         55=NF-e emitida em substituição ao modelo 1 ou 1A; 
         65=NFC-e, utilizada nas operações de venda no varejo (a critério da UF aceitar este modelo de documento).
         */
        ide.setMod(String.valueOf(jsysNFe.getMod()));
        /*
         Série do Documento Fiscal, preencher com zeros na hipótese
         de a NF-e não possuir série. (v2.0)
         Série 890-899: uso exclusivo para emissão de NF-e avulsa, 
         pelo contribuinte com seu certificado digital, 
         através do site do Fisco (procEmi=2). (v2.0)
         Serie 900-999: uso exclusivo de NF-e emitidas no SCAN. (v2.0)
         */
        ide.setSerie(jsysNFe.getSerie());
        /*
         Número do Documento Fiscal.
         */
        ide.setNNF(jsysNFe.getNNF());
        /*
         Data e hora no formato UTC (Universal Coordinated Time): AAAA-MM-DDThh:mm:ssTZD
         */
        ide.setDhEmi(ManagerData.convertDate(data, ManagerData.FORMATO_NFE)); //ManagerData.convertDate(n.getDataSaidaEntrada(), "yyyy-MM-dd'T'HH:mm:ssXXX")
        /*
         Data e hora no formato UTC (Universal Coordinated Time): AAAA-MM-DDThh:mm:ssTZD.
         Não informar este campo para a NFC-e.
         */
        if (jsysNFe.getMod() != ConstantesFiscal.NFC_E) {
            ide.setDhSaiEnt(ManagerData.convertDate(jsysNFe.getDhSaiEnt(), ManagerData.FORMATO_NFE));
        }
        
        if (jsysNFe.getTpNF()) { // 0=Entrada; 1=Saída
            ide.setTpNF("1");
        } else {
            ide.setTpNF("0");
        }
        ide.setIdDest(jsysNFe.getIdDest()); // 1=Operação interna; 2=Operação interestadual; 3=Operação com exterior.
        /*
         Informar o município de ocorrência do fato gerador do ICMS. 
         Utilizar a Tabela do IBGE (Anexo IX - Tabela de UF, Município e País)
         */
        ide.setCMunFG(jsysNFe.getCMunFG());
        /*
         setTpImp
         0=Sem geração de DANFE;
         1=DANFE normal, Retrato;
         2=DANFE normal, Paisagem;
         3=DANFE Simplificado;
         4=DANFE NFC-e;
         5=DANFE NFC-e em mensagem eletrônica (o envio de mensagem eletrônica pode ser feita 
         de forma simultânea com a impressão do DANFE; usar o tpImp=5 quando esta for a única 
         forma de disponibilização do DANFE).
         */
        ide.setTpImp(jsysNFe.getTpImp());
        /*
         1=Emissão normal (não em contingência);
         2=Contingência FS-IA, com impressão do DANFE em formulário de segurança;
         3=Contingência SCAN (Sistema de Contingência do Ambiente Nacional);
         4=Contingência DPEC (Declaração Prévia da Emissão em Contingência);
         5=Contingência FS-DA, com impressão do DANFE em formulário de segurança;
         6=Contingência SVC-AN (SEFAZ Virtual de Contingência do AN);
         7=Contingência SVC-RS (SEFAZ Virtual de Contingência do RS);
         9=Contingência off-line da NFC-e (as demais opções de contingência são válidas também para a NFC-e).
         Para a NFC-e somente estão disponíveis e são válidas as opções de contingência 5 e 9.
         */
        ide.setTpEmis(jsysNFe.getTpEmis());
        ide.setCDV(jsysNFe.getCDV()); // Informar o DV da Chave de Acesso da NF-e,
        ide.setTpAmb(jsysNFe.getTpAmb()); // 1=Produção/2=Homologação
        /*
         1=NF-e normal;
         2=NF-e complementar;
         3=NF-e de ajuste;
         4=Devolução de mercadoria.
         */
        ide.setFinNFe(jsysNFe.getFinNFe());
        ide.setIndFinal(jsysNFe.getIndFinal()); // 0=Normal; 1=Consumidor final;

        /*
            0=Não se aplica (por exemplo, Nota Fiscal complementar ou de ajuste);
            1=Operação presencial;
            2=Operação não presencial, pela Internet;
            3=Operação não presencial, Teleatendimento;
            4=NFC-e em operação com entrega a domicílio;
            5=Operação presencial, fora do estabelecimento;
            9=Operação não presencial, outros.

         */
        ide.setIndPres(jsysNFe.getIndPres());
        /*
         0= Emissão de NF-e com aplicativo do contribuinte;
         1= Emissão de NF-e avulsa pelo Fisco;
         2= Emissão de NF-e avulsa, pelo contribuinte com seu certificado digital, através do site do Fisco;
         3= Emissão NF-e pelo contribuinte com aplicativo fornecido pelo Fisco.
         */
        ide.setProcEmi("0");
        ide.setVerProc(br.JavaApplicationJsys.VERSAO_STRING);
        if (!jsysNFe.getTpEmis().equals("1")) {
            ide.setDhCont(ManagerData.convertDate(par.getDhCont(), ManagerData.FORMATO_NFE));
            ide.setXJust(ManagerString.removeAcentos(par.getxJust()));
        }
        for (JsysNFeReferencias ref : jsysNFe.getJsysNFeReferenciasCollection()) {
            Ide.NFref nFRef = new Ide.NFref();
            if (ref.getNFeRef()) {
                nFRef.setRefNFe(ref.getRefNFe());
            } else if (ref.getCTeRef()) {
                nFRef.setRefCTe(ref.getRefCTe());
            } else if (ref.getNfRef()) {
                Ide.NFref.RefNF nf = new Ide.NFref.RefNF();
                nf.setCUF(ref.getCUF());
                nf.setAAMM(ref.getAamm());
                nf.setCNPJ(ref.getCnpj());
                nf.setMod(ref.getModelo());
                nf.setSerie(ref.getSerie());
                nf.setNNF(ref.getNNF());
                nFRef.setRefNF(nf);
            }
            ide.getNFref().add(nFRef);
        }
        return ide;
    }

    /**
     *
     * @return Identificação do Emitente da Nota Fiscal eletrônica
     */
    private Emit dadosDoEmitente() {
        Emit emit = new Emit();
        emit.setCNPJ(ManagerString.RemoveFormatoCpfCnpj(par.getCnpj()));
        emit.setXNome(par.getRazaoSocial().trim());
        emit.setXFant(par.getFantasia().trim());

        TEnderEmi enderEmit = new TEnderEmi();
        enderEmit.setXLgr(par.getEndereco().trim());
        enderEmit.setNro(par.getNumero());
        String xCpl = par.getComplemento();
        if (!"".equals(xCpl) & xCpl != null) {
            enderEmit.setXCpl(xCpl.trim());
        }
        enderEmit.setXBairro(par.getBairro().trim());
        enderEmit.setCMun(String.valueOf(par.getCodMunicipio()));
        enderEmit.setXMun(par.getCidade().trim());
        enderEmit.setUF(TUfEmi.valueOf(par.getUf()));
        enderEmit.setCEP(ManagerString.RemoveFormatoCep(par.getCep()));
        enderEmit.setCPais(String.valueOf(par.getCodPais()));
        enderEmit.setXPais(par.getPais().trim());
        enderEmit.setFone(ManagerString.Right(ManagerString.RemoveFormato(par.getFone()), 10).trim());

        emit.setEnderEmit(enderEmit);

        emit.setIE(par.getInscricao());
        /**
         * IE do Substituto Tributário da UF de destino da mercadoria, quando
         * houver a retenção do ICMS ST para a UF de destino. A empresa ao
         * praticar operações interestaduais com outros Estados de mercadorias
         * com substituições tributária é obrigada a recolher o ICMS-ST(
         * antecipado), por operação. Para algumas empresas, em função do volume
         * de operações, a operação fica trabalhosa, burocrática. Imagine, emito
         * a nota, preencho a GNRE e pago no banco, pego uma via, anexo à nota
         * fiscal, e, libero o caminhão. Para evitar este trabalho, a empresa
         * abre uma inscrição de substituto tributário nos Estados onde ela
         * pratica estas operações, e fica dispensada deste recolhimento, porém,
         * no final do mês preenche a GIA-ST e recolhe o imposto. Fiz muitas
         * operações deste tipo quando atuava numa empresa de alimentos sob ST -
         * azeite, farinha de trigo, etc -,e acontecia do caminhão seguir sem a
         * guia, porque, era sexta-feira o banco já tinha fechado, porque o
         * caminhoneiro esqueceu, e quando não, a logística recolhia para o
         * Estado errado.
         *
         * Resumindo, este é o motivo da Inscrição ST.
         */
        //emit.setIEST("");

        String im = par.getIm();
        if (!im.equals("")) {
            emit.setIM(im);
            String cnae = par.getCnae();
            if (!cnae.equals("")) {
                emit.setCNAE(cnae);
            }
        }
        /*
         Código de Regime Tributário
         1=Simples Nacional;
         2=Simples Nacional, excesso sublimite de receita bruta;
         3=Regime Normal. (v2.0).
         */
        emit.setCRT(par.getCrt());
        return emit;
    }

    /**
     *
     *
     * @return E - Identificação do Destinatário da Nota Fiscal eletrônica Grupo
     * obrigatório para a NF-e (modelo 55)
     */
    private Dest dadosDoDestinatario() {
        Dest dest = new Dest();
        CpfCnpj validate = new CpfCnpj(cpfCnpj);
        if (validate.isCnpj()) {
            dest.setCNPJ(cpfCnpj);
        } else if (validate.isCpf()) {
            dest.setCPF(cpfCnpj);
        } else {
            // o sistema ainda nao aceita id Estrangeiro
            return null;
        }
        if (!venda.getIdCliente().getIdCliente().equals(par.getIdConsumidorFinal())) {
            dest.setXNome(jsysNFe.getTpAmb().equals("2") ? "NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL" : venda.getIdCliente().getNomeCorentista().trim());
            if (Validar.isNotNullOrWhite(venda.getIdCliente().getEndereco().trim(),
                    venda.getIdCliente().getNumero().trim(),
                    venda.getIdCliente().getBairro().trim(),
                    venda.getIdCliente().getCodMunicipio(),
                    venda.getIdCliente().getCidade().trim(),
                    venda.getIdCliente().getEstado().trim(),
                    venda.getIdCliente().getCep(),
                    venda.getIdCliente().getCodPais().trim(),
                    venda.getIdCliente().getPais().trim())) {
                TEndereco enderDest = new TEndereco();
                enderDest.setXLgr(venda.getIdCliente().getEndereco().trim());
                enderDest.setNro(venda.getIdCliente().getNumero().trim());
                enderDest.setXBairro(venda.getIdCliente().getBairro().trim());

                if (Validar.isNotNullOrWhite(venda.getIdCliente().getComplemento())) {
                    enderDest.setXCpl(venda.getIdCliente().getComplemento().trim());
                }
                /*
                 Utilizar a Tabela do IBGE (Anexo IX - Tabela de UF, Município e País).
                 Informar ‘9999999 ‘para operações com o exterior.
                 */
                enderDest.setCMun(venda.getIdCliente().getCodMunicipio());
                /*
                 Informar ‘EXTERIOR ‘para operações com o exterior.
                 */
                enderDest.setXMun(venda.getIdCliente().getCidade().trim());
                /*
                 Informar ‘EX’ para operações com o exterior.
                 */
                enderDest.setUF(TUf.valueOf(venda.getIdCliente().getEstado()));
                /*
                 Informar os zeros não significativos.
                 */
                enderDest.setCEP(ManagerString.RemoveFormatoCep(venda.getIdCliente().getCep()));
                /*
                 Utilizar a Tabela do BACEN (Anexo IX - Tabela de UF, Município e País).
                 */
                enderDest.setCPais(venda.getIdCliente().getCodPais());
                enderDest.setXPais(venda.getIdCliente().getPais().trim());
                /*
                 Preencher com o Código DDD + número do telefone. 
                 Nas operações com exterior é permitido informar 
                 o código do país + código da localidade + número do telefone (v2.0)
                 */
                enderDest.setFone(ManagerString.Right(ManagerString.RemoveFormato(venda.getIdCliente().getFone()), 10).trim());
                dest.setEnderDest(enderDest);
            }
        }
        if (jsysNFe.getMod() == ConstantesFiscal.NFC_E) {
            /*
             1=Contribuinte ICMS (informar a IE do destinatário);
             2=Contribuinte isento de Inscrição no cadastro de Contribuintes do ICMS;
             9=Não Contribuinte, que pode ou não possuir Inscrição Estadual no Cadastro de Contribuintes do ICMS.
             Nota 1: No caso de NFC-e informar indIEDest=9 e não informar a tag IE do destinatário;
             Nota 2: No caso de operação com o Exterior informar indIEDest=9 e não informar a tag IE do destinatário;
             Nota 3: No caso de Contribuinte Isento de Inscrição (indIEDest=2), não informar a tag IE do destinatário.
             */
            dest.setIndIEDest("9");
        } else if (jsysNFe.getMod() == ConstantesFiscal.NF_E) {
            /*
             1=Contribuinte ICMS (informar a IE do destinatário);
             2=Contribuinte isento de Inscrição no cadastro de Contribuintes do ICMS;
             9=Não Contribuinte, que pode ou não possuir Inscrição Estadual no Cadastro de Contribuintes do ICMS.
             Nota 1: No caso de NFC-e informar indIEDest=9 e não informar a tag IE do destinatário;
             Nota 2: No caso de operação com o Exterior informar indIEDest=9 e não informar a tag IE do destinatário;
             Nota 3: No caso de Contribuinte Isento de Inscrição (indIEDest=2), não informar a tag IE do destinatário.
             */
            if (venda.getIdCliente().getIeRg().equals("ISENTO")) {
                // gabiarra para emitir nota para o estado no amazonas sem inscricao estadual
                // Os Estados que não permitem que os destinatário tenham indicação 
                // como Contribuinte Isento são: AM, BA, CE, GO, MG, MS, MT, PA, PE, RN, SE, SP.
                if (Validar.estadosNaoContribuintes(venda.getIdCliente().getEstado())) {
                    dest.setIndIEDest("9");
                } else {
                    dest.setIndIEDest("2");
                }
            } else {
                dest.setIndIEDest("1");
                /*
                 Campo opcional. Informar somente os algarismos, sem os caracteres de formatação (ponto, barra, hífen, etc.).
                 */
                dest.setIE(ManagerString.RemoveFormato(venda.getIdCliente().getIeRg()));
            }

            /*
             Obrigatório, nas operações que se beneficiam de incentivos fiscais existentes nas áreas sob controle da SUFRAMA. 
             A omissão desta informação impede o processamento da operação pelo Sistema de 
             Mercadoria Nacional da SUFRAMA e a liberação da Declaração de Ingresso, 
             prejudicando a comprovação do ingresso / internamento da mercadoria nestas áreas. (v2.0)
             */
            if (Validar.isNotNullOrWhite(venda.getIdCliente().getSuframa())) {
                dest.setISUF(ManagerString.RemoveFormato(venda.getIdCliente().getSuframa()));
            }

            /*
             ainda nao tenho nota de serviço no sistema 
             Campo opcional, pode ser informado na NF-e conjugada, 
             com itens de produtos sujeitos ao ICMS e itens de serviços sujeitos ao ISSQN.
             */
            //dest.setIM(null);
        }
        /*
         Campo pode ser utilizado para informar o e-mail de recepção da NF-e indicada pelo destinatário (v2.0)
         */
        String email = venda.getIdCliente().getEmail();
        if (Validar.isNotNullOrWhite(email)) {
            dest.setEmail(email.toLowerCase());
        }
        return dest;
    }

    /**
     *
     *
     * @return H - Detalhamento de Produtos e Serviços da NF-e I - Produtos e
     * Serviços da NF-e M - Tributos incidentes no Produto ou Serviço V -
     * Informações adicionais
     */
    private Det dadosDoProduto(JsysOrcamentoItens iten, Integer nItem) throws NfeException {
        Det det = new Det();
        det.setNItem(nItem.toString());
        // Dados do Produro
        Prod prod = new Prod();
        // Código do produto ou serviço
        String codigo = iten.getIdProdutoNfe() != null && !iten.getIdProdutoNfe().toString().isEmpty()
                ? iten.getIdProdutoNfe().toString()
                : iten.getJsysProdutosT().getIdProduto().toString();
        prod.setCProd(codigo);
        /*
            Preencher com o código GTIN-8, GTIN-12, GTIN-13
            ou GTIN-14 (antigos códigos EAN, UPC e DUN-14).
            Para produtos que não possuem código de
            barras com GTIN, deve ser informado o literal
            “SEM GTIN”;
         */
        if (!iten.getIdbarra().equals(iten.getJsysProdutosT().getIdProduto().toString())
                & Validar.isValidCodigoBarra(iten.getIdbarra())) {
            prod.setCEAN(iten.getIdbarra());
            prod.setCEANTrib(iten.getIdbarra());
        } else {
            prod.setCEAN("SEM GTIN");
            prod.setCEANTrib("SEM GTIN");
        }
        if (jsysNFe.getTpAmb().equals("2") & nItem == 1) {
            prod.setXProd("NOTA FISCAL EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        } else {
            prod.setXProd(ManagerString.Left(iten.getJsysProdutosT().getNomeProduto(), 120).trim());
        }

        /*
         Obrigatória informação do NCM completo (8 dígitos).
         Nota: Em caso de item de serviço ou item que não tenham produto 
         (ex. transferência de crédito, crédito do ativo imobilizado, etc.), 
         informar o valor 00 (dois zeros). (NT 2014/004)
         */
        prod.setNCM(iten.getJsysProdutosT().getNCM());

        prod.setCEST("0000000"); // Código Especificador da Substituição Tributária – CEST
        /**
         * Indicador de Produção em escala relevante, conforme Cláusula 23 do
         * Convenio ICMS 52/2017: S - Produzido em Escala Relevante; N –
         * Produzido em Escala NÃO Relevante. Nota: preenchimento obrigatório
         * para produtos com NCM relacionado no Anexo XXVII do Convenio 52/2017
         * produto em escala NÃO relevante.
         *
         */
        //prod.setIndEscala("N");
        /**
         * Código de Benefício Fiscal utilizado pela UF, aplicado ao item. Obs.:
         * Deve ser utilizado o mesmo código adotado na EFD e outras
         * declarações, nas UF que o exigem.
         */
        //prod.setCBenef("");


        /*
         * resolver depois 
         */
        //prod.setEXTIPI(null);
        if (iten.getCfop() == null || "".equals(iten.getCfop())) {
            String cfop = venda.getIdCliente().getEstado().equals(par.getUf())
                    ? par.getCfop() : par.getCfopInterestadual();
            prod.setCFOP(ManagerString.RemoveFormato(cfop));
        } else {
            prod.setCFOP(ManagerString.RemoveFormato(iten.getCfop()));
        }
        prod.setUCom(iten.getUnidadeVenda());
        prod.setQCom(ManagerDecimal.converterXmlNFe(iten.getQuantidade()));
        prod.setVUnCom(ManagerDecimal.converterXmlNFe(iten.getPrecoVenda()));
        prod.setVProd(ManagerDecimal.converterXmlNFe(iten.getTotalProduto()));
        String strProd = getValorNfe(iten.getTotalProduto());
        prod.setVProd(strProd);
        valorProd += Double.valueOf(strProd);
        prod.setUTrib(iten.getUnidadeVenda());
        prod.setQTrib(ManagerDecimal.converterXmlNFe(iten.getQuantidade()));
        prod.setVUnTrib(ManagerDecimal.converterXmlNFe(iten.getPrecoVenda()));
        //prod.setVFrete("0.00");
        //prod.setVSeg("0.00");
        if (iten.getDesconto().doubleValue() > 0.0) {
            String strProdDesc = getValorNfe(iten.getDesconto());
            prod.setVDesc(strProdDesc);
            valorDesc += Double.valueOf(strProdDesc);
        }
        if (iten.getDesconto().doubleValue() < 0.0) {
            prod.setVOutro(ManagerDecimal.converterXmlNFe(iten.getDesconto().multiply(new BigDecimal(-1))));
        }
        /*
         0=Valor do item (vProd) não compõe o valor total da NF-e
         1=Valor do item (vProd) compõe o valor total da NF-e (vProd) (v2.0)
         */
        prod.setIndTot("1");

//        /**
//         * Detalhamento de produto sujeito a rastreabilidade
//         */
//        Prod.Rastro rastro = new Prod.Rastro();
//        rastro.setNLote("1"); // Número do Lote do produto 
//        rastro.setQLote("0.000"); // Quantidade de produto no Lote 
//        rastro.setDFab("2018-01-01"); // Formato: “AAAA-MM-DD” 
//        rastro.setDVal("2020-01-01"); // Formato: “AAAA-MM-DD” Informar o último dia do mês caso a validade não especifique o dia.
//        rastro.setCAgreg("0"); // Código de Agregação 
//        prod.getRastro().add(rastro);
//        /**
//         * Detalhamento de Medicamentos e de matérias-primas farmacêuticas
//         */
//        Prod.Med med = new Prod.Med();
//        med.setCProdANVISA("0000000000000"); //Utilizar o número do registro ANVISA
//        med.setVPMC("0.00"); // Preço máximo consumidor
//        prod.setMed(med);
        det.setProd(prod);
        det.setImposto(getImposto(iten));

        /**
         * Informações adicionais do Produto.
         */
        //det.setInfAdProd("Norma referenciada, informações complementares, etc.");
        return det;
    }

    /**
     * Impostos da NF-e
     */
    private Imposto getImposto(JsysOrcamentoItens iten) throws NfeException {
        Imposto imposto = new Imposto();
        BigDecimal totalImposto = BigDecimal.ZERO;
        Map<Object, Object> filtro = new HashMap<>();
        filtro.put("codigo", iten.getJsysProdutosT().getNCM());
        Ncm ncm = (Ncm) Retorna.findOneResult("Ncm.findByCodigo", filtro);
        String origenFiscal = iten.getJsysProdutosT().getOrigemFiscal();
        BigDecimal valorLiquidoProduto = iten.getTotalProduto().subtract(iten.getDesconto());
        if (origenFiscal.equals("0")
                | origenFiscal.equals("3")
                | origenFiscal.equals("4")
                | origenFiscal.equals("5")) {
            totalImposto = totalImposto.add(getValorPercentual(valorLiquidoProduto, ncm.getNacionalfederal()));
        } else {
            totalImposto = totalImposto.add(getValorPercentual(valorLiquidoProduto, ncm.getImportadosfederal()));
        }
        totalImposto = totalImposto.add(getValorPercentual(valorLiquidoProduto, ncm.getEstadual()));
        totalImposto = totalImposto.add(getValorPercentual(valorLiquidoProduto, ncm.getMunicipal()));
        totalImpostoGeral = totalImpostoGeral.add(totalImposto);
        imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoVTotTrib(ManagerDecimal.converterXmlNFe(totalImposto)));

        // icms
        if (par.getCrt().equals("1")) {
            imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(getSimples(iten)));
            //imposto.setICMS(getSimples(iten));
        } else {
            imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(getNormal(iten)));
            //imposto.setICMS(getNormal(iten));
        }
        if (jsysNFe.getMod() == ConstantesFiscal.NF_E) {
            // ipi
            //imposto.setIPI(getIPI(iten));
            //imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoIPI(getIPI(iten)));
            // pis
            imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoPIS(getPIS(iten)));
            // confins
            imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoCOFINS(getCOFINS(iten)));
        }
        return imposto;
    }

    public ICMS getSimples(JsysOrcamentoItens iten) throws NfeException {
        ICMS icms = new ICMS();
        if (par.getIdTributacao() != null) {
            String cson = iten.getIdTributacao() == null
                    ? iten.getJsysProdutosT().getIdTributacao() == null ? par.getIdTributacao().getCson() : iten.getJsysProdutosT().getIdTributacao().getCson()
                    : iten.getIdTributacao().getCson();
            //                vp.getComVendaProdutoIcmsCst().equals("") ?
//                        vp.getProdProduto().getProdIcms().getProdIcmsCson() : 
//                        vp.getComVendaProdutoIcmsCst();
            String origem = iten.getJsysProdutosT().getOrigemFiscal().substring(0, 1);// String.valueOf(vp.getProdProduto().getProdOrigem().getProdOrigemValor());
            switch (cson) {
//            ICMS.ICMSSN101 icmssn101 = new ICMS.ICMSSN101();
//            icmssn101.setOrig(origem);
//            icmssn101.setCSOSN(cson);
//            // porcentagem icms
//            double porcento = 0.00;
//            if (comNatureza.getComNaturezaIcms()) {
//                porcento = vp.getComVendaProdutoIcms() == 0.00 ? Double.valueOf(auth.getConf().get("nfe.cson")) : vp.getComVendaProdutoIcms();
//            }
//            icmssn101.setPCredSN(getValorNfe(porcento));
//            // valor da base de calculo
//            String strBase = porcento == 0.00 ? "0.00" : getValorNfe(vp.getComVendaProdutoTotalLiquido());
//            double base = Double.valueOf(strBase);
//            // valor icms
//            String strValor = getValorNfe(base * porcento / 100);
//            double valor = Double.valueOf(strValor);
//            icmssn101.setVCredICMSSN(strValor);
//            icms.setICMSSN101(icmssn101);
//            // executa a soma dos impostos
//            baseICMS += base;
//            valorICMS += valor;
                case "101":
                    break;
                case "102":
                    ICMS.ICMSSN102 icmssn102 = new ICMS.ICMSSN102();
                    icmssn102.setOrig(origem);
                    icmssn102.setCSOSN(cson);
                    icms.setICMSSN102(icmssn102);

//        } else if (cson.equals("201")) {
//            ICMS.ICMSSN201 icmssn201 = new ICMS.ICMSSN201();
//            icmssn201.setOrig(origem);
//            icmssn201.setCSOSN(cson);
//            icmssn201.setModBCST(auth.getConf().get("nfe.modocalcst"));
//            icmssn201.setVBCST("0.00");
//            icmssn201.setPICMSST("0.00");
//            icmssn201.setVICMSST("0.00");
//            icmssn201.setPCredSN("0.00");
//            icmssn201.setVCredICMSSN("0.00");
//            icms.setICMSSN201(icmssn201);
//        } else if (cson.equals("202")) {
//            ICMS.ICMSSN202 icmssn202 = new ICMS.ICMSSN202();
//            icmssn202.setOrig(origem);
//            icmssn202.setCSOSN(cson);
//            icmssn202.setModBCST(auth.getConf().get("nfe.modocalcst"));
//            icmssn202.setVBCST("0.00");
//            icmssn202.setPICMSST("0.00");
//            icmssn202.setVICMSST("0.00");
//            icms.setICMSSN202(icmssn202);
                    break;
                case "500":
                    ICMS.ICMSSN500 icmssn500 = new ICMS.ICMSSN500();
                    icmssn500.setOrig(origem);
                    icmssn500.setCSOSN(cson);
                    icmssn500.setVBCSTRet("0.00");
                    icmssn500.setVICMSSTRet("0.00");
                    icms.setICMSSN500(icmssn500);
                    break;
                default:
                    ICMS.ICMSSN900 icmssn900 = new ICMS.ICMSSN900();
                    icmssn900.setOrig(origem);
                    icmssn900.setCSOSN(cson);
                    icms.setICMSSN900(icmssn900);
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "situaçao tributária nao cadastrada", "ERRO", JOptionPane.ERROR_MESSAGE);
            new ParametrosJanelas().setVisible(true);
            throw new NfeException("situaçao tributária nao cadastrada");
        }
        return icms;
    }

    public ICMS getNormal(JsysOrcamentoItens iten) {
        ICMS icms = new ICMS();
        String cst = iten.getIdTributacao() == null
                ? iten.getJsysProdutosT().getIdTributacao() == null ? par.getIdTributacao().getCst() : iten.getJsysProdutosT().getIdTributacao().getCst()
                : iten.getIdTributacao().getCst();
        //vp.getComVendaProdutoIcmsCst().equals("") ? vp.getProdProduto().getProdIcms().getProdIcmsCst() : vp.getComVendaProdutoIcmsCst();
        String origem = iten.getJsysProdutosT().getOrigemFiscal().substring(0, 1);

        // se é 10 e muda pra 60
        if (cst.equals("10")) {
            cst = "60";
        }// se for 41 muda pra 40
        else if (cst.equals("41")) {
            cst = "40";
        }

        // modo base calculo normal
        switch (cst) {
//            ICMS.ICMS00 icms00 = new ICMS.ICMS00();
//            icms00.setOrig(origem);
//            icms00.setCST(cst);
//            icms00.setModBC(auth.getConf().get("nfe.modocalc"));
//            // porcentagem icms
//            double porcento = 0.00;
//            if (comNatureza.getComNaturezaIcms()) {
//                if (vp.getComVendaProdutoIcms() > 0) {
//                    porcento = vp.getComVendaProdutoIcms();
//                } else {
//                    porcento = dentro ? vp.getProdProduto().getProdIcms().getProdIcmsDentro() : vp.getProdProduto().getProdIcms().getProdIcmsFora();
//                }
//            }
//            // valor da base de calculo
//            String strBase = porcento == 0.00 ? "0.00" : getValorNfe(vp.getComVendaProdutoTotalLiquido());
//            double base = Double.valueOf(strBase);
//            icms00.setVBC(strBase);
//            icms00.setPICMS(getValorNfe(porcento));
//            // valor icms
//            String strValor = getValorNfe(base * porcento / 100);
//            double valor = Double.valueOf(strValor);
//            icms00.setVICMS(strValor);
//            icms.setICMS00(icms00);
//            // executa a soma dos impostos
//            baseICMS += base;
//            valorICMS += valor;
//        } else if (cst.equals("30")) {
//            ICMS.ICMS30 icms30 = new ICMS.ICMS30();
//            icms30.setOrig(origem);
//            icms30.setCST(cst);
//            icms30.setModBCST(auth.getConf().get("nfe.modocalcst"));
//            icms30.setVBCST("0.00");
//            icms30.setPICMSST("0.00");
//            icms30.setVICMSST("0.00");
//            icms.setICMS30(icms30);
            case "00":
                break;
            case "40":
                ICMS.ICMS40 icms40 = new ICMS.ICMS40();
                icms40.setOrig(origem);
                icms40.setCST(cst);
                icms.setICMS40(icms40);
                break;
            case "60":
                ICMS.ICMS60 icms60 = new ICMS.ICMS60();
                icms60.setOrig(origem);
                icms60.setCST(cst);
                icms60.setVBCSTRet("0.00");
                icms60.setVICMSSTRet("0.00");
                icms.setICMS60(icms60);
                break;
            default:
                ICMS.ICMS90 icms90 = new ICMS.ICMS90();
                icms90.setOrig(origem);
                icms90.setCST(cst);
                icms.setICMS90(icms90);
                break;
        }

        return icms;
    }

    /**
     * 03.17 Grupo de Tributação: PIS e COFINS A. Grupo PISNT Incluída a
     * possibilidade de informação do CST=05 - Operação Tributável, Substituição
     * Tributária (tag:PISNT/CST). B. Grupo COFINSNT Incluída a possibilidade de
     * informação do CST=05 - Operação Tributável, Substituição Tributária
     * (tag:COFINSNT/CST). C. PIS / COFINS para a NFC-e (Nota Fiscal de
     * Consumidor Eletrônica) No caso da NFC-e, o grupo de tributação do PIS e o
     * grupo de tributação da COFINS são opcionais. Para a NF-e se mantém
     * obrigatória a informação destes grupos, controladas por regras de
     * validação efetuadas pela SEFAZ.
     *
     * @param iten
     * @return
     */
    public PIS getPIS(JsysOrcamentoItens iten) {
        PIS pis = new PIS();
        if (!par.getCrt().equals("1")) {
            String cst = iten.getIdTributacao() == null
                    ? iten.getJsysProdutosT().getIdTributacao() == null
                    ? par.getIdTributacao().getCst()
                    : iten.getJsysProdutosT().getIdTributacao().getCst()
                    : iten.getIdTributacao().getCst();
            // identifica pela natureza se cobra PIS
            double porcento = 0.00;
//            if (comNatureza.getComNaturezaPis()) {
//                if (vp.getComVendaProdutoPis() > 0) {
//                    porcento = vp.getComVendaProdutoPis();
//                } else {
//                    porcento = vp.getProdProduto().getProdPis().getProdPisAliquota();
//                }
//            }

            // faz o calculo do valor e define
            double valorLiquido = iten.getTotalProduto().subtract(iten.getDesconto()).doubleValue();
            double valor = valorLiquido * porcento / 100;
            String strValor = getValorNfe(valor);
            valorPis += Double.valueOf(strValor);

            // isento
            if (porcento == 0.00) {
                PIS.PISOutr outr = new PIS.PISOutr();
                outr.setCST("49");
                outr.setVBC("0.00");
                outr.setPPIS("0.00");
                outr.setVPIS("0.00");
                pis.setPISOutr(outr);
            } else if (cst.equals("49")) {
                PIS.PISOutr outr = new PIS.PISOutr();
                outr.setCST(cst);
                outr.setVBC(getValorNfe(valorLiquido));
                outr.setPPIS(getValorNfe(porcento));
                outr.setVPIS(strValor);
                pis.setPISOutr(outr);
            } else {
                PIS.PISAliq aliq = new PIS.PISAliq();
                aliq.setCST(cst);
                aliq.setVBC(getValorNfe(valorLiquido));
                aliq.setPPIS(getValorNfe(porcento));
                aliq.setVPIS(strValor);
                pis.setPISAliq(aliq);
            }
        } else {
            // simples nacinal
            PIS.PISOutr pisOutr = new PIS.PISOutr();
            pisOutr.setCST("99");
            pisOutr.setVBC("0.00");
            pisOutr.setPPIS("0.00");
            pisOutr.setVPIS("0.00");
            pis.setPISOutr(pisOutr);
        }
        return pis;
    }

    public COFINS getCOFINS(JsysOrcamentoItens iten) {
        COFINS cofins = new COFINS();
        if (!par.getCrt().equals("1")) {
            String cst = iten.getIdTributacao() == null
                    ? iten.getJsysProdutosT().getIdTributacao() == null
                    ? par.getIdTributacao().getCst()
                    : iten.getJsysProdutosT().getIdTributacao().getCst()
                    : iten.getIdTributacao().getCst();

            // identifica pela natureza se cobra COFINS
            double porcento = 0.00;
//            if (comNatureza.getComNaturezaCofins()) {
//                if (vp.getComVendaProdutoCofins() > 0) {
//                    porcento = vp.getComVendaProdutoCofins();
//                } else {
//                    porcento = vp.getProdProduto().getProdCofins().getProdCofinsAliquota();
//                }
//            }

            // faz o calculo do valor e define
            double valorLiquido = iten.getTotalProduto().subtract(iten.getDesconto()).doubleValue();
            double valor = valorLiquido * porcento / 100;
            String strValor = getValorNfe(valor);
            valorCofins += Double.valueOf(strValor);

            // isento ou simples nacional
            if (porcento == 0.00) {
                COFINS.COFINSOutr outr = new COFINS.COFINSOutr();
                outr.setCST("49");
                outr.setVBC("0.00");
                outr.setPCOFINS("0.00");
                outr.setVCOFINS("0.00");
                cofins.setCOFINSOutr(outr);
            } else if (cst.equals("49")) {
                COFINS.COFINSOutr outr = new COFINS.COFINSOutr();
                outr.setCST(cst);
                outr.setVBC(getValorNfe(valorLiquido));
                outr.setPCOFINS(getValorNfe(porcento));
                outr.setVCOFINS(strValor);
                cofins.setCOFINSOutr(outr);
            } else {
                COFINS.COFINSAliq aliq = new COFINS.COFINSAliq();
                aliq.setCST(cst);
                aliq.setVBC(getValorNfe(valorLiquido));
                aliq.setPCOFINS(getValorNfe(porcento));
                aliq.setVCOFINS(strValor);
                cofins.setCOFINSAliq(aliq);
            }
        } else {
            COFINS.COFINSOutr cofinsOutros = new COFINS.COFINSOutr();
            cofinsOutros.setCST("99");
            cofinsOutros.setVBC("0.00");
            cofinsOutros.setPCOFINS("0.00");
            cofinsOutros.setVCOFINS("0.00");
            cofins.setCOFINSOutr(cofinsOutros);
        }
        return cofins;
    }

    /**
     *
     *
     * @return W - Valores Totais da NF-e
     */
    private Total totaisDaNFe() {
        Total total = new Total();
        ICMSTot icmstot = new ICMSTot();
//        BigDecimal valorBruto = BigDecimal.ZERO;
//        BigDecimal valorDesconto = BigDecimal.ZERO;
//        for (JsysOrcamentoItens iten : venda.getJsysOrcamentoItensCollection()) {
//            valorBruto = valorBruto.add(iten.getTotalProduto());
//            valorDesconto = valorDesconto.add(iten.getDesconto());
//        }
//        BigDecimal valorLiquido = valorBruto.subtract(valorDesconto);
        if (par.getCrt().equals("1")) {
            icmstot.setVBC("0.00");
            icmstot.setVICMS("0.00");
            if (baseICMS > 0) {
                infos.put("icms", "Base de Calculo do ICMS = " + ManagerDecimal.formataNumero(baseICMS, 1, 2, true) + " com valor de ICMS = " + ManagerDecimal.formataNumero(valorICMS, 1, 2, true));
            }
        } else {
            icmstot.setVBC(getValorNfe(baseICMS));
            icmstot.setVICMS(getValorNfe(valorICMS));
        }
        icmstot.setVICMSDeson("0.00");
        icmstot.setVFCP("0.00");
        icmstot.setVFCPST("0.00");
        icmstot.setVFCPSTRet("0.00");
        icmstot.setVBCST(getValorNfe(baseST));
        icmstot.setVST(getValorNfe(valorST));
        icmstot.setVProd(getValorNfe(valorProd));
        icmstot.setVFrete("0.00");
        icmstot.setVSeg("0.00");
        icmstot.setVDesc(getValorNfe(valorDesc));
        icmstot.setVII("0.00");
        icmstot.setVIPI(getValorNfe(valorIpi));
        icmstot.setVIPIDevol("0.00");
        icmstot.setVPIS(getValorNfe(valorPis));
        icmstot.setVCOFINS(getValorNfe(valorCofins));
        if (valorDesc < 0.0) {
            icmstot.setVOutro(getValorNfe(valorDesc * -1));
        } else {
            icmstot.setVOutro("0.00");
        }
        icmstot.setVNF(getValorNfe((valorProd + valorIpi) - valorDesc));
        icmstot.setVTotTrib(getValorNfe(totalImpostoGeral));
        total.setICMSTot(icmstot);
        return total;
    }

    private InfNFe.Pag.DetPag dadosPag(JsysRebebimentoAgrupado rec) {
        InfNFe.Pag.DetPag p = new InfNFe.Pag.DetPag();
        try {
            /**
             * 0= Pagamento à Vista 1= Pagamento à Prazo
             */
            p.setIndPag(rec.getIndPag());
            /**
             * 01=Dinheiro 02=Cheque 03=Cartão de Crédito 04=Cartão de Débito
             * 05=Crédito Loja 10=Vale Alimentação 11=Vale Refeição 12=Vale
             * Presente 13=Vale Combustível 15=Boleto Bancário 90= Sem pagamento
             * 99=Outros
             *
             *
             */
            p.setTPag(rec.getTipoPagamento().substring(0, 2));
            p.setVPag(ManagerDecimal.converterXmlNFe(rec.getTotalLiqudo()));
        } catch (Exception e) {
            Log.registraErro(this.getClass().getName(), "dadosPag", e);
            JOptionPane.showMessageDialog(null, "Verifique os Cadastro de Titulos");
            JFrame frame = new JFrame();
            frame.setContentPane(new TitulosJanela());
            frame.setTitle("Cadastro de Títulos");
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);
        }

        // Nota Técnica 2013/005 
        // Os campos de informação do Grupo de Cartões são de preenchimento opcional.
        if (rec.getCard()) {
            TNFe.InfNFe.Pag.DetPag.Card c = new TNFe.InfNFe.Pag.DetPag.Card();
            if (rec.getTipoIntegracao() != null) {
                String tipoIntegracao = rec.getTipoIntegracao().substring(0, 1);
                c.setTpIntegra(tipoIntegracao);
                if ("1".equals(tipoIntegracao)) {
                    c.setCNPJ(ManagerString.RemoveFormatoCpfCnpj(rec.getCnpjCredenciadora())); //Informar o CNPJ da Credenciadora de cartão de crédito / débito
                    c.setTBand(rec.getTipoBandeira().substring(0, 2)); // selcionar a bandeira da forma de pagamento
                    c.setCAut("0"); //Identifica o número da autorização da transação da operação com cartão de crédito e/ou débito
                }
                p.setCard(c);
            } else {
                JOptionPane.showMessageDialog(null, "O Título \""
                        + rec.getIdTitulo()
                        + "\" está cadastrado incorretamente!"
                        + System.lineSeparator()
                        + "Conserte o cadastro e Tente Emitir novamente a NFC-e.", "ERRO", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        return p;
    }

    /**
     *
     * @param modFrete 0=Por conta do emitente; 1=Por conta do
     * destinatário/remetente; 2=Por conta de terceiros; 9=Sem frete. (V2.0)
     * @return X - Informações do Transporte da NF-e
     */
    private Transp dadosDoTransporte(String modFrete) {
        Transp transp = new Transp();
        /*
         0=Por conta do emitente;
         1=Por conta do destinatário/remetente;
         2=Por conta de terceiros;
         9=Sem frete. (V2.0)
         */
        transp.setModFrete(modFrete);

        /**
         * Dados da Transportadora. fatal implementar
         */
        if (!modFrete.equals("9")) {
            Map<Object, Object> filtro = new HashMap<>();
            filtro.put("id", this.jsysNFe.getIdTrasportadora());
            JsysNFeTransportadoras transportadora = (JsysNFeTransportadoras) Retorna.findOneResult("JsysNFeTransportadoras.findById", filtro);
            Transporta t = new Transporta();
            String cnpjCpf = ManagerString.RemoveFormatoCpfCnpj(transportadora.getCnpjCpf()).trim();
            if (cnpjCpf.length() == 14) {
                t.setCNPJ(cnpjCpf);
            } else if (cnpjCpf.length() == 11) {
                t.setCPF(cnpjCpf);
            }
            String xNome = transportadora.getXNome().trim();
            if (xNome.length() > 0) {
                t.setXNome(xNome);
            }
            String IE = ManagerString.RemoveFormatoCpfCnpj(transportadora.getIeRg()).trim();
            if (IE.length() > 0) {
                t.setIE(IE);
            }
            String xEnder = transportadora.getXEnder().trim();
            if (xEnder.length() > 0) {
                t.setXEnder(xEnder);
            }
            String xMun = transportadora.getXMun().trim();
            if (xMun.length() > 0) {
                t.setXMun(xMun);
            }
            String UF = transportadora.getUf().trim();
            if (UF.length() > 0) {
                t.setUF(TUf.valueOf(UF));
            }
            transp.setTransporta(t);
            /**
             * Dados de Volumes.
             */
            for (JsysNFeVolumes volumes : jsysNFe.getJsysNFeVolumesCollection()) {
                Vol vol = new Vol();
                if (!volumes.getQVol().toString().isEmpty()) {
                    vol.setQVol(volumes.getQVol().toString());
                }
                if (!volumes.getEsp().isEmpty()) {
                    vol.setEsp(volumes.getEsp());
                }
                if (!volumes.getMarca().isEmpty()) {
                    vol.setMarca(volumes.getMarca());
                }
                if (!volumes.getNVol().isEmpty()) {
                    vol.setNVol(volumes.getNVol());
                }
                if (!volumes.getPesoL().toString().isEmpty()) {
                    vol.setPesoL(volumes.getPesoL().toString());
                }
                if (!volumes.getPesoB().toString().isEmpty()) {
                    vol.setPesoB(volumes.getPesoB().toString());
                }
                transp.getVol().add(vol);
            }
        }
        return transp;
    }

    /**
     *
     *
     * @return Z - Informações Adicionais da NF-e
     */
    private InfAdic informacoesAdicionais() {
        StringBuilder sb = new StringBuilder();
        // adiciona o decreto da natureza
//        if (!comNatureza.getComNaturezaDecreto().equals("")) {
//            sb.append(comNatureza.getComNaturezaDecreto() + "#");
//        }
        // adiciona os decretos para os tributos
//        for (Map.Entry<String, String> info : infos.entrySet()) {
//            sb.append(info.getValue()).append("#");
//        }
        // uma mensagem padrao se precisar
//        if (auth.getConf().get("nfe.info") != null) {
//            sb.append("#" + auth.getConf().get("nfe.info"));
//        }
//        // caso a opcao de mostrar os valores de impostos esteja ativa
//        if (auth.getConf().get("nfe.ibpt").equalsIgnoreCase("SIM")) {
//            double impostos = 0.00;
//            for (ComVendaProduto vp : produtos) {
//                FiltroTexto ft = new FiltroTexto("ibptCodigo", ECompara.IGUAL, vp.getProdProduto().getProdProdutoNcm());
//                FiltroNumero fn = new FiltroNumero("ibptTabela", ECompara.IGUAL, vp.getProdProduto().getProdTipo().getProdTipoValor().equals("09") ? 1 : 0);
//                GrupoFiltro gf = new GrupoFiltro(EJuncao.E, new IFiltro[]{ft, fn});
//                try {
//                    Ibpt ibpt = (Ibpt) servico.selecionar(new Ibpt(), gf, false);
//                    int ori = vp.getProdProduto().getProdOrigem().getProdOrigemValor();
//                    double taxa = (ori == 0 || ori == 3 || ori == 4 || ori == 5) ? ibpt.getIbptAliqNac() : ibpt.getIbptAliqImp();
//                    impostos += vp.getComVendaProdutoTotalLiquido() * taxa / 100;
//                } catch (Exception ex) {
//                    continue;
//                }
//            }
//            double porcentagem = impostos / valorProd * 100;
//            sb.append("#Valor Aproximado dos Tributos em Reais ");
//            sb.append(UtilServer.formataNumero(impostos, 1, 2, false)).append(" [");
//            sb.append(UtilServer.formataNumero(porcentagem, 1, 2, false)).append(" porcentos] Fonte: IBPT");
//        }

        // adiciona o pedido da venda
        sb.append("#Venda ").append(ManagerDecimal.formataNumero(venda.getIdOrcamento(), 9, 0, false));

        // case tenha alguma observacao
        if (!this.jsysNFe.getInfCpl().trim().equals("")) {
            sb.append(" ").append(this.jsysNFe.getInfCpl().trim());
        }
        InfAdic inf = new InfAdic();
        inf.setInfCpl(sb.toString().trim());
        return inf;
    }

    public boolean gerar() throws JAXBException, SQLException, FileNotFoundException, CertificadoException, NfeException, NoSuchAlgorithmException {
        ConfiguracoesNfe config = br.JavaApplicationJsys.iniciaConfigurações(par);
        if (jsysNFe.getMod() == ConstantesFiscal.NF_E & !Validar.cpfCnpj(venda.getIdCliente().getCnpjCpf())) {
            return false;
        }
        infNFe.setId(chaveAcesso);
        infNFe.setVersao(ConstantesFiscal.VERSAO.NFE);
        infNFe.setIde(dadosDeIdentificacao());
        infNFe.setEmit(dadosDoEmitente());
        Dest destinatario = dadosDoDestinatario();
        if (destinatario != null) {
            infNFe.setDest(destinatario);
        }
        Integer nItem = 1;
        for (JsysOrcamentoItens iten : venda.getJsysOrcamentoItensCollection()) {
            infNFe.getDet().add(dadosDoProduto(iten, nItem));
            nItem++;
        }
        infNFe.setTotal(totaisDaNFe());
        infNFe.setTransp(dadosDoTransporte(this.jsysNFe.getModFrete()));
        infNFe.setInfAdic(informacoesAdicionais());
        infNFe.setPag(getPag());
        //infNFe.setCobr(getCobr());

        nFe.setInfNFe(infNFe);
        // Monta EnviNfe
        enviNFe = new TEnviNFe();
        enviNFe.setVersao(ConstantesFiscal.VERSAO.NFE);
        JsysNFeLote lote = new JsysNFeLote();
        lote.setIdLote(String.valueOf(DADOS.sequenciaTabela("jsysNFeLote", "idLote")));
        br.sql.acesso.ConnectionFactory.insert(lote);
        enviNFe.setIdLote(lote.getIdLote());
        enviNFe.setIndSinc("1");
        enviNFe.getNFe().add(nFe);
        // Monta e Assina o XML
        enviNFe = Nfe.montaNfe(config, enviNFe, true);
        if (jsysNFe.getMod() == ConstantesFiscal.NFC_E) {
//            //QRCODE
//            String cDest = null;
//            if (enviNFe.getNFe().get(0).getInfNFe().getDest() != null) {
//                if (Validar.isNotNullOrWhite(enviNFe.getNFe().get(0).getInfNFe().getDest().getCNPJ())) {
//                    cDest = enviNFe.getNFe().get(0).getInfNFe().getDest().getCNPJ();
//                } else if (Validar.isNotNullOrWhite(enviNFe.getNFe().get(0).getInfNFe().getDest().getCPF())) {
//                    cDest = enviNFe.getNFe().get(0).getInfNFe().getDest().getCPF();
//                } else if (Validar.isNotNullOrWhite(enviNFe.getNFe().get(0).getInfNFe().getDest().getIdEstrangeiro())) {
//                    cDest = enviNFe.getNFe().get(0).getInfNFe().getDest().getIdEstrangeiro();
//                }
//            }
            TNFe.InfNFeSupl infNFeSupl = new TNFe.InfNFeSupl();
            infNFeSupl.setQrCode(
                    preencheQRCode(enviNFe, config, par.getcIdToken(), par.getCSC(), par.getContingencia())
            );
            infNFeSupl.setUrlChave(
                    WebServiceUtil.getUrl(
                            config,
                            DocumentoEnum.NFCE,
                            ServicosEnum.URL_CONSULTANFCE
                    )
            );
            enviNFe.getNFe().get(0).setInfNFeSupl(infNFeSupl);
        }
        String xml = XmlNfeUtil.objectToXml(enviNFe);
        GravaNoArquivo gravador = new GravaNoArquivo();
        gravador.salvarArquivo(xml, br.JavaApplicationJsys.PASTA_XML_ENVI_NFE, chaveAcesso, "xml");
        jsysNFe.setEnviNFe(xml);
        return br.sql.acesso.ConnectionFactory.update(jsysNFe) instanceof JsysNFe;
    }

    /**
     * Envia a Nfe para a Sefaz
     *
     * @param tipoDocumento se e uma NF-e ou NFC-e
     * @return
     */
    public boolean enviarNfe(DocumentoEnum tipoDocumento) {
        try {
            ConfiguracoesNfe config = br.JavaApplicationJsys.iniciaConfigurações(par);
            TRetEnviNFe tRetEnviNFe = Nfe.enviarNfe(config, enviNFe, tipoDocumento);
            String xmlTRetEnviNFe = XmlNfeUtil.objectToXml(tRetEnviNFe);
            GravaNoArquivo gravador = new GravaNoArquivo();
            gravador.salvarArquivo(xmlTRetEnviNFe, br.JavaApplicationJsys.PASTA_XML_RET_CONS_RECI_NFE, chaveAcesso, "xml");
            jsysNFe.setRetConsReciNFe(xmlTRetEnviNFe);
            jsysNFe.setProcNFe(XmlNfeUtil.criaNfeProc(enviNFe, tRetEnviNFe.getProtNFe()));
            if (Validar.verificaCsStat(tRetEnviNFe.getCStat())) {
                this.CStat = tRetEnviNFe.getProtNFe().getInfProt().getCStat();
                if (Validar.verificaCsStat(this.CStat)) {
                    setMensagem(tRetEnviNFe.getProtNFe().getInfProt().getXMotivo());
                    jsysNFe.setEmitida(true);
                } else {
                    setMensagem(this.CStat + " - " + tRetEnviNFe.getProtNFe().getInfProt().getXMotivo());
                    jsysNFe.setEmitida(false);
                }
            } else {
                setMensagem(tRetEnviNFe.getCStat() + " - " + tRetEnviNFe.getXMotivo());
                jsysNFe.setEmitida(false);
            }
            return br.sql.acesso.ConnectionFactory.update(jsysNFe) instanceof JsysNFe;
        } catch (JAXBException | FileNotFoundException | CertificadoException | NfeException ex) {
            Log.registraErro(GerandoNFeJAXB.class.getName(), "enviarNfe", ex);
        }
        return false;
    }

    private BigDecimal getValorPercentual(BigDecimal valor, BigDecimal porcentagem) {
        BigDecimal fator = porcentagem.divide(new BigDecimal(100));
        return valor.multiply(fator).setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.DOWN);
    }

//    private TIpi getIPI(JsysOrcamentoItens iten) {
//        IPI ipi = new IPI();
//        String cst = vp.getComVendaProdutoIpiCst().equals("") ? vp.getProdProduto().getProdIpi().getProdIpiCstSaida() : vp.getComVendaProdutoIpiCst();
//
//        // identifica pela natureza se cobra IPI
//        double porcento = 0.00;
//        if (comNatureza.getComNaturezaIpi()) {
//            if (vp.getComVendaProdutoIpi() > 0) {
//                porcento = vp.getComVendaProdutoIpi();
//            } else {
//                porcento = vp.getProdProduto().getProdIpi().getProdIpiAliquota();
//            }
//        }
//
//        // faz o calculo do valor e seta o tipo do cst
//        double valor = vp.getComVendaProdutoTotalLiquido() * porcento / 100;
//        String strValor = getValorNfe(valor);
//        valorIpi += Double.valueOf(strValor);
//
//        IPITrib trib = new IPITrib();
//        if (porcento == 0.00) {
//            trib.setCST("99");
//            trib.setVBC("0.00");
//            trib.setPIPI("0.00");
//            trib.setVIPI("0.00");
//        } else {
//            trib.setCST(cst);
//            trib.setVBC(getValorNfe(vp.getComVendaProdutoTotalLiquido()));
//            trib.setPIPI(getValorNfe(porcento));
//            trib.setVIPI(strValor);
//        }
//
//        ipi.setIPITrib(trib);
//        ipi.setCEnq(vp.getProdProduto().getProdIpi().getProdIpiEnq());
//        return ipi;
//        return null;
//    }
    private String getValorNfe(double valor) {
        return ManagerDecimal.formataNumero(valor, 1, 2, false).replace(",", ".");
    }

    private String getValorNfe(BigDecimal valor) {
        return getValorNfe(valor.doubleValue());
    }

    private InfNFe.Cobr getCobr() {
        InfNFe.Cobr corb = new InfNFe.Cobr();
        return null;
    }

    private InfNFe.Pag getPag() {
        Map<Object, Object> filtro = new HashMap<>();
        filtro.put("idReceber", venda.getIdOrcamento());
        List<Object> O = Retorna.findList("JsysRebebimentoAgrupado.findByIdReceber", filtro);
        InfNFe.Pag pag = new InfNFe.Pag();
        for (Object o : O) {
            JsysRebebimentoAgrupado rec = (JsysRebebimentoAgrupado) o;
            pag.getDetPag().add(dadosPag(rec));
        }
        pag.setVTroco("0.00");
        return pag.getDetPag().isEmpty() ? null : pag;
    }

    public String getCStat() {
        return CStat;
    }

    /**
     * Preenche QRCode 
     * 
     * Monta QRCode
     *
     * @param enviNFe
     * @param config
     * @param idToken
     * @param csc
     * @return
     * @throws NfeException
     * @throws NoSuchAlgorithmException
     */
    private static String preencheQRCode(TEnviNFe enviNFe, ConfiguracoesNfe config, String idToken, String csc, boolean contingencia) throws NfeException, NoSuchAlgorithmException {

        //System.out.println(csc);
        //csc = csc.replaceAll("[^0-9^A-Z^a-z]", "");
        //System.out.println(csc);
        if (contingencia) {
            // QRCODE EMISSAO OFFLINE
            return NFCeUtil.getCodeQRCodeContingencia(
                    enviNFe.getNFe().get(0).getInfNFe().getId().substring(3),
                    config.getAmbiente().getCodigo(),
                    enviNFe.getNFe().get(0).getInfNFe().getIde().getDhEmi(),
                    enviNFe.getNFe().get(0).getInfNFe().getTotal().getICMSTot().getVNF(),
                    Base64.getEncoder().encodeToString(enviNFe.getNFe().get(0).getSignature().getSignedInfo().getReference().getDigestValue()),
                    idToken,
                    csc,
                    WebServiceUtil.getUrl(config, DocumentoEnum.NFCE, ServicosEnum.URL_QRCODE));
        } else {
            //QRCODE EMISAO ONLINE
            return NFCeUtil.getCodeQRCode(
                    enviNFe.getNFe().get(0).getInfNFe().getId().substring(3),
                    config.getAmbiente().getCodigo(),
                    idToken,
                    csc,
                    WebServiceUtil.getUrl(config, DocumentoEnum.NFCE, ServicosEnum.URL_QRCODE));
        }

    }
}
