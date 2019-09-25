package br.sql.bean;

import br.sql.util.ManagerString;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysNFe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysNFe.findByACancelar", query = "SELECT j FROM JsysNFe j WHERE (j.emitida = true) AND (j.cancelada = false) AND (j.venda = :venda)"),
    @NamedQuery(name = "JsysNFe.findNFeByVendaEmitida", query = "SELECT j FROM JsysNFe j WHERE j.venda = :venda AND j.emitida = TRUE AND j.cancelada = FALSE"),
    @NamedQuery(name = "JsysNFe.findByDhEmiEmitida", query = "SELECT j FROM JsysNFe j WHERE (j.dhEmi BETWEEN :dhEmiInicial and :dhEmiFinal) AND (j.emitida = TRUE)"),
    //@NamedQuery(name = "JsysNFe.findByChaveAcessoAndErro", query = "SELECT j FROM JsysNFe j WHERE (j.chaveAcesso = :chaveAcesso) AND (j.retConsReciNFe = :retConsReciNFe)"),
    @NamedQuery(name = "JsysNFe.findXml", query = "SELECT j FROM JsysNFe j WHERE (j.dhEmi BETWEEN :dhEmiInicial and :dhEmiFinal) AND (j.emitida = :emitida) AND (j.mod = :mod)"),
    @NamedQuery(name = "JsysNFe.findByinChaveAcesso", query = "SELECT j FROM JsysNFe j WHERE j.chaveAcesso IN :chavesAcesso"),
    @NamedQuery(name = "JsysNFe.findAll", query = "SELECT j FROM JsysNFe j"),
    @NamedQuery(name = "JsysNFe.findByNfeId", query = "SELECT j FROM JsysNFe j WHERE j.nfeId = :nfeId"),
    @NamedQuery(name = "JsysNFe.findByVenda", query = "SELECT j FROM JsysNFe j WHERE j.venda = :venda"),
    @NamedQuery(name = "JsysNFe.findByChaveAcesso", query = "SELECT j FROM JsysNFe j WHERE j.chaveAcesso = :chaveAcesso"),
    @NamedQuery(name = "JsysNFe.findByIndSinc", query = "SELECT j FROM JsysNFe j WHERE j.indSinc = :indSinc"),
    @NamedQuery(name = "JsysNFe.findByCUF", query = "SELECT j FROM JsysNFe j WHERE j.cUF = :cUF"),
    @NamedQuery(name = "JsysNFe.findByCNF", query = "SELECT j FROM JsysNFe j WHERE j.cNF = :cNF"),
    @NamedQuery(name = "JsysNFe.findByNatOp", query = "SELECT j FROM JsysNFe j WHERE j.natOp = :natOp"),
    @NamedQuery(name = "JsysNFe.findByIndPag", query = "SELECT j FROM JsysNFe j WHERE j.indPag = :indPag"),
    @NamedQuery(name = "JsysNFe.findBySerie", query = "SELECT j FROM JsysNFe j WHERE j.serie = :serie"),
    @NamedQuery(name = "JsysNFe.findByNNF", query = "SELECT j FROM JsysNFe j WHERE j.nNF = :nNF"),
    @NamedQuery(name = "JsysNFe.findByDhEmi", query = "SELECT j FROM JsysNFe j WHERE j.dhEmi = :dhEmi"),
    @NamedQuery(name = "JsysNFe.findByDhSaiEnt", query = "SELECT j FROM JsysNFe j WHERE j.dhSaiEnt = :dhSaiEnt"),
    @NamedQuery(name = "JsysNFe.findByTpNF", query = "SELECT j FROM JsysNFe j WHERE j.tpNF = :tpNF"),
    @NamedQuery(name = "JsysNFe.findByIdDest", query = "SELECT j FROM JsysNFe j WHERE j.idDest = :idDest"),
    @NamedQuery(name = "JsysNFe.findByCMunFG", query = "SELECT j FROM JsysNFe j WHERE j.cMunFG = :cMunFG"),
    @NamedQuery(name = "JsysNFe.findByTpImp", query = "SELECT j FROM JsysNFe j WHERE j.tpImp = :tpImp"),
    @NamedQuery(name = "JsysNFe.findByTpEmis", query = "SELECT j FROM JsysNFe j WHERE j.tpEmis = :tpEmis"),
    @NamedQuery(name = "JsysNFe.findByCDV", query = "SELECT j FROM JsysNFe j WHERE j.cDV = :cDV"),
    @NamedQuery(name = "JsysNFe.findByTpAmb", query = "SELECT j FROM JsysNFe j WHERE j.tpAmb = :tpAmb"),
    @NamedQuery(name = "JsysNFe.findByFinNFe", query = "SELECT j FROM JsysNFe j WHERE j.finNFe = :finNFe"),
    @NamedQuery(name = "JsysNFe.findByIndFinal", query = "SELECT j FROM JsysNFe j WHERE j.indFinal = :indFinal"),
    @NamedQuery(name = "JsysNFe.findByIndPres", query = "SELECT j FROM JsysNFe j WHERE j.indPres = :indPres"),
    @NamedQuery(name = "JsysNFe.findByMod", query = "SELECT j FROM JsysNFe j WHERE j.mod = :mod"),
    @NamedQuery(name = "JsysNFe.findByEmitida", query = "SELECT j FROM JsysNFe j WHERE j.emitida = :emitida"),
    @NamedQuery(name = "JsysNFe.findByCancelada", query = "SELECT j FROM JsysNFe j WHERE j.cancelada = :cancelada")})
public class JsysNFe implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nfeId")
    private Collection<JsysNFeReferencias> jsysNFeReferenciasCollection;
    @Column(name = "infCpl")
    private String infCpl;
    @Column(name = "cancelada")
    private Boolean cancelada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nfeId")
    private Collection<JsysNFeVolumes> jsysNFeVolumesCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nfe_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nfeId;
    @Basic(optional = false)
    @Column(name = "venda")
    private int venda;
    @Basic(optional = false)
    @Column(name = "indSinc")
    private boolean indSinc;
    @Basic(optional = false)
    @Column(name = "cUF")
    private String cUF;
    @Basic(optional = false)
    @Column(name = "cNF")
    private String cNF;
    @Basic(optional = false)
    @Column(name = "natOp")
    private String natOp;
    @Basic(optional = false)
    @Column(name = "indPag")
    private String indPag;
    @Basic(optional = false)
    @Column(name = "serie")
    private String serie;
    @Basic(optional = false)
    @Column(name = "nNF")
    private String nNF;
    @Basic(optional = false)
    @Column(name = "dhEmi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dhEmi;
    @Basic(optional = false)
    @Column(name = "dhSaiEnt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dhSaiEnt;
    @Basic(optional = false)
    @Column(name = "tpNF")
    private boolean tpNF;
    @Basic(optional = false)
    @Column(name = "idDest")
    private String idDest;
    @Basic(optional = false)
    @Column(name = "cMunFG")
    private String cMunFG;
    @Basic(optional = false)
    @Column(name = "tpImp")
    private String tpImp;
    @Basic(optional = false)
    @Column(name = "tpEmis")
    private String tpEmis;
    @Basic(optional = false)
    @Column(name = "cDV")
    private String cDV;
    @Basic(optional = false)
    @Column(name = "tpAmb")
    private String tpAmb;
    @Basic(optional = false)
    @Column(name = "finNFe")
    private String finNFe;
    @Basic(optional = false)
    @Column(name = "indFinal")
    private String indFinal;
    @Basic(optional = false)
    @Column(name = "indPres")
    private String indPres;
    @Basic(optional = false)
    @Column(name = "mod")
    private int mod;
    @Basic(optional = false)
    @Column(name = "emitida")
    private boolean emitida;
    @Basic(optional = false)
    @Column(name = "chaveAcesso")
    private String chaveAcesso;
    @Column(name = "enviNFe")
    private String enviNFe;
    @Column(name = "retConsReciNFe")
    private String retConsReciNFe;
    @Basic(optional = true)
    @Column(name = "procNFe")
    private String procNFe;
    @Basic(optional = false)
    @Column(name = "modFrete")
    private String modFrete;
    @Basic(optional = true)
    @Column(name = "idTrasportadora")
    private Integer idTrasportadora;

    /**
     *
     */
    public JsysNFe() {
        this.emitida = false;
        this.cancelada = false;
        this.venda = 0;
        this.chaveAcesso = "";
        this.indSinc = false;
        this.cUF = "";
        this.cNF = "";
        this.natOp = "";
        this.indPag = "";
        this.serie = "";
        this.nNF = "";
        this.dhEmi = new Date();
        this.dhSaiEnt = new Date();
        this.tpNF = false;
        this.idDest = "";
        this.cMunFG = "";
        this.tpImp = "";
        this.tpEmis = "";
        this.cDV = "";
        this.tpAmb = "";
        this.finNFe = "";
        this.indFinal = "";
        this.indPres = "";
        this.mod = 0;
    }

    /**
     *
     * @param nfeId
     */
    public JsysNFe(Long nfeId) {
        this.nfeId = nfeId;
    }

    /**
     *
     * @param nfeId
     * @param venda
     * @param indSinc
     * @param cUF
     * @param cNF
     * @param natOp
     * @param indPag
     * @param serie
     * @param nNF
     * @param dhEmi
     * @param dhSaiEnt
     * @param tpNF
     * @param idDest
     * @param cMunFG
     * @param tpImp
     * @param tpEmis
     * @param cDV
     * @param tpAmb
     * @param finNFe
     * @param indFinal
     * @param indPres
     * @param mod
     * @param emitida
     */
    public JsysNFe(Long nfeId, int venda, boolean indSinc, String cUF, String cNF, String natOp, String indPag, String serie, String nNF, Date dhEmi, Date dhSaiEnt, boolean tpNF, String idDest, String cMunFG, String tpImp, String tpEmis, String cDV, String tpAmb, String finNFe, String indFinal, String indPres, int mod, boolean emitida) {
        this.nfeId = nfeId;
        this.venda = venda;
        this.indSinc = indSinc;
        this.cUF = cUF;
        this.cNF = cNF;
        this.natOp = natOp;
        this.indPag = indPag;
        this.serie = serie;
        this.nNF = nNF;
        this.dhEmi = dhEmi;
        this.dhSaiEnt = dhSaiEnt;
        this.tpNF = tpNF;
        this.idDest = idDest;
        this.cMunFG = cMunFG;
        this.tpImp = tpImp;
        this.tpEmis = tpEmis;
        this.cDV = cDV;
        this.tpAmb = tpAmb;
        this.finNFe = finNFe;
        this.indFinal = indFinal;
        this.indPres = indPres;
        this.mod = mod;
        this.emitida = emitida;
    }

    /**
     *
     * @return primary key tabela
     */
    public Long getNfeId() {
        return nfeId;
    }

    /**
     *
     * @param nfeId primary key tabela
     */
    public void setNfeId(Long nfeId) {
        this.nfeId = nfeId;
    }

    /**
     *
     * @return codigo do orcamento do sistema
     */
    public int getVenda() {
        return venda;
    }

    /**
     *
     * @param venda codigo do orcamento do sistema
     */
    public void setVenda(int venda) {
        this.venda = venda;
    }

    /**
     *
     * @return 0=Não. 1=Empresa solicita processamento síncrono do Lote de NF-e
     * (sem a geração de Recibo para consulta futura); Nota: O processamento
     * síncrono do Lote corresponde a entrega da resposta do processamento das
     * NF-e do Lote, sem a geração de um Recibo de Lote para consulta futura. A
     * resposta de forma síncrona pela SEFAZ Autorizadora só ocorrerá se: - a
     * empresa solicitar e constar unicamente uma NF-e no Lote; - a SEFAZ
     * Autorizadora implementar o processamento síncrono para a resposta do Lote
     * de NF-e.
     */
    public boolean getIndSinc() {
        return indSinc;
    }

    /**
     *
     * @param indSinc 0=Não. 1=Empresa solicita processamento síncrono do Lote
     * de NF-e (sem a geração de Recibo para consulta futura); Nota: O
     * processamento síncrono do Lote corresponde a entrega da resposta do
     * processamento das NF-e do Lote, sem a geração de um Recibo de Lote para
     * consulta futura. A resposta de forma síncrona pela SEFAZ Autorizadora só
     * ocorrerá se: - a empresa solicitar e constar unicamente uma NF-e no Lote;
     * - a SEFAZ Autorizadora implementar o processamento síncrono para a
     * resposta do Lote de NF-e.
     */
    public void setIndSinc(boolean indSinc) {
        this.indSinc = indSinc;
    }

    /**
     *
     * @return Código da UF do emitente do Documento Fiscal.
     */
    public String getCUF() {
        return cUF;
    }

    /**
     *
     * @param cUF Código da UF do emitente do Documento Fiscal. Utilizar a
     * Tabela do IBGE de código de unidades da federação (Anexo IX - Tabela de
     * UF, Município e País).
     */
    public void setCUF(String cUF) {
        this.cUF = cUF;
    }

    /**
     *
     * @return Código numérico que compõe a Chave de Acesso.
     */
    public String getCNF() {
        return cNF;
    }

    /**
     *
     * @param cNF Código numérico que compõe a Chave de Acesso. Número aleatório
     * gerado pelo emitente para cada NF-e para evitar acessos indevidos da
     * NF-e. (v2.0)
     */
    public void setCNF(String cNF) {
        this.cNF = cNF;
    }

    /**
     *
     * @return a natureza da operação de que decorrer a saída ou a entrada,
     */
    public String getNatOp() {
        return natOp;
    }

    /**
     *
     * @param natOp Informar a natureza da operação de que decorrer a saída ou a
     * entrada, tais como: venda, compra, transferência, devolução, importação,
     * consignação, remessa (para fins de demonstração, de industrialização ou
     * outra), conforme previsto na alínea 'i', inciso I, art. 19 do CONVÊNIO
     * S/Nº, de 15 de dezembro de 1970.
     */
    public void setNatOp(String natOp) {
        this.natOp = natOp;
    }

    /**
     *
     * @return 0=Pagamento à vista; 1=Pagamento a prazo; 2=Outros.
     */
    public String getIndPag() {
        return indPag;
    }

    /**
     *
     * @param indPag 0=Pagamento à vista; 1=Pagamento a prazo; 2=Outros.
     */
    public void setIndPag(String indPag) {
        this.indPag = indPag;
    }

    /**
     *
     * @return Série do Documento Fiscal,
     */
    public String getSerie() {
        return serie;
    }

    /**
     *
     * @param serie Série do Documento Fiscal, preencher com zeros na hipótese
     * de a NF-e não possuir série. (v2.0) Série 890-899: uso exclusivo para
     * emissão de NF-e avulsa, pelo contribuinte com seu certificado digital,
     * através do site do Fisco (procEmi=2). (v2.0) Serie 900-999: uso exclusivo
     * de NF-e emitidas no SCAN. (v2.0)
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     *
     * @return Número do Documento Fiscal.
     */
    public String getNNF() {
        return nNF;
    }

    /**
     *
     * @param nNF Número do Documento Fiscal.
     */
    public void setNNF(String nNF) {
        this.nNF = nNF;
    }

    /**
     *
     * @return Data e hora no formato UTC (Universal Coordinated Time):
     * AAAA-MM-DDThh:mm:ssTZD
     */
    public Date getDhEmi() {
        return dhEmi;
    }

    /**
     *
     * @param dhEmi Data e hora no formato UTC (Universal Coordinated Time):
     * AAAA-MM-DDThh:mm:ssTZD
     */
    public void setDhEmi(Date dhEmi) {
        this.dhEmi = dhEmi;
    }

    /**
     *
     * @return Data e hora no formato UTC
     */
    public Date getDhSaiEnt() {
        return dhSaiEnt;
    }

    /**
     *
     * @param dhSaiEnt Data e hora no formato UTC (Universal Coordinated Time):
     * AAAA-MM-DDThh:mm:ssTZD. Não informar este campo para a NFC-e.
     */
    public void setDhSaiEnt(Date dhSaiEnt) {
        this.dhSaiEnt = dhSaiEnt;
    }

    /**
     *
     * @return 0=Entrada; 1=Saída
     */
    public boolean getTpNF() {
        return tpNF;
    }

    /**
     *
     * @param tpNF 0=Entrada; 1=Saída
     */
    public void setTpNF(boolean tpNF) {
        this.tpNF = tpNF;
    }

    /**
     *
     * @return 1=Operação interna; 2=Operação interestadual; 3=Operação com
     * exterior.
     */
    public String getIdDest() {
        return idDest;
    }

    /**
     *
     * @param idDest 1=Operação interna; 2=Operação interestadual; 3=Operação
     * com exterior.
     */
    public void setIdDest(String idDest) {
        this.idDest = idDest;
    }

    /**
     *
     * @return o município de ocorrência do fato gerador do ICMS.
     */
    public String getCMunFG() {
        return cMunFG;
    }

    /**
     *
     * @param cMunFG Informar o município de ocorrência do fato gerador do ICMS.
     * Utilizar a Tabela do IBGE (Anexo IX - Tabela de UF, Município e País)
     */
    public void setCMunFG(String cMunFG) {
        this.cMunFG = cMunFG;
    }

    /**
     *
     * @return 0=Sem geração de DANFE; 1=DANFE normal, Retrato; 2=DANFE normal,
     * Paisagem; 3=DANFE Simplificado; 4=DANFE NFC-e; 5=DANFE NFC-e em mensagem
     * eletrônica (o envio de mensagem eletrônica pode ser feita de forma
     * simultânea com a impressão do DANFE; usar o tpImp=5 quando esta for a
     * única forma de disponibilização do DANFE).
     */
    public String getTpImp() {
        return tpImp;
    }

    /**
     *
     * @param tpImp 0=Sem geração de DANFE; 1=DANFE normal, Retrato; 2=DANFE
     * normal, Paisagem; 3=DANFE Simplificado; 4=DANFE NFC-e; 5=DANFE NFC-e em
     * mensagem eletrônica (o envio de mensagem eletrônica pode ser feita de
     * forma simultânea com a impressão do DANFE; usar o tpImp=5 quando esta for
     * a única forma de disponibilização do DANFE).
     */
    public void setTpImp(String tpImp) {
        this.tpImp = tpImp;
    }

    /**
     *
     * @return 1=Emissão normal (não em contingência); 2=Contingência FS-IA, com
     * impressão do DANFE em formulário de segurança; 3=Contingência SCAN
     * (Sistema de Contingência do Ambiente Nacional); 4=Contingência DPEC
     * (Declaração Prévia da Emissão em Contingência); 5=Contingência FS-DA, com
     * impressão do DANFE em formulário de segurança; 6=Contingência SVC-AN
     * (SEFAZ Virtual de Contingência do AN); 7=Contingência SVC-RS (SEFAZ
     * Virtual de Contingência do RS); 9=Contingência off-line da NFC-e (as
     * demais opções de contingência são válidas também para a NFC-e). Para a
     * NFC-e somente estão disponíveis e são válidas as opções de contingência 5
     * e 9.
     */
    public String getTpEmis() {
        return tpEmis;
    }

    /**
     *
     * @param tpEmis 1=Emissão normal (não em contingência); 2=Contingência
     * FS-IA, com impressão do DANFE em formulário de segurança; 3=Contingência
     * SCAN (Sistema de Contingência do Ambiente Nacional); 4=Contingência DPEC
     * (Declaração Prévia da Emissão em Contingência); 5=Contingência FS-DA, com
     * impressão do DANFE em formulário de segurança; 6=Contingência SVC-AN
     * (SEFAZ Virtual de Contingência do AN); 7=Contingência SVC-RS (SEFAZ
     * Virtual de Contingência do RS); 9=Contingência off-line da NFC-e (as
     * demais opções de contingência são válidas também para a NFC-e). Para a
     * NFC-e somente estão disponíveis e são válidas as opções de contingência 5
     * e 9.
     */
    public void setTpEmis(String tpEmis) {
        this.tpEmis = tpEmis;
    }

    /**
     *
     * @return o DV da Chave de Acesso da NF-e,
     */
    public String getCDV() {
        return cDV;
    }

    /**
     *
     * @param cDV Informar o DV da Chave de Acesso da NF-e, o DV será calculado
     * com a aplicação do algoritmo módulo 11 (base 2,9) da Chave de Acesso.
     * (vide item 5 do Manual de Orientação)
     */
    public void setCDV(String cDV) {
        this.cDV = cDV;
    }

    /**
     *
     * @return 1=Produção/2=Homologação
     */
    public String getTpAmb() {
        return tpAmb;
    }

    /**
     *
     * @param tpAmb 1=Produção/2=Homologação
     */
    public void setTpAmb(String tpAmb) {
        this.tpAmb = tpAmb;
    }

    /**
     *
     * @return 1=NF-e normal; 2=NF-e complementar; 3=NF-e de ajuste; 4=Devolução
     * de mercadoria.
     */
    public String getFinNFe() {
        return finNFe;
    }

    /**
     *
     * @param finNFe 1=NF-e normal; 2=NF-e complementar; 3=NF-e de ajuste;
     * 4=Devolução de mercadoria.
     */
    public void setFinNFe(String finNFe) {
        this.finNFe = finNFe;
    }

    /**
     *
     * @return 0=Normal; 1=Consumidor final;
     */
    public String getIndFinal() {
        return indFinal;
    }

    /**
     *
     * @param indFinal 0=Normal; 1=Consumidor final;
     */
    public void setIndFinal(String indFinal) {
        this.indFinal = indFinal;
    }

    /**
     *
     * @return 0=Não se aplica (por exemplo, Nota Fiscal complementar ou de
     * ajuste); 1=Operação presencial; 2=Operação não presencial, pela Internet;
     * 3=Operação não presencial, Teleatendimento; 4=NFC-e em operação com
     * entrega a domicílio; 9=Operação não presencial, outros.
     */
    public String getIndPres() {
        return indPres;
    }

    /**
     *
     * @param indPres 0=Não se aplica (por exemplo, Nota Fiscal complementar ou
     * de ajuste); 1=Operação presencial; 2=Operação não presencial, pela
     * Internet; 3=Operação não presencial, Teleatendimento; 4=NFC-e em operação
     * com entrega a domicílio; 9=Operação não presencial, outros.
     */
    public void setIndPres(String indPres) {
        this.indPres = indPres;
    }

    /**
     *
     * @return 55=NF-e; 65=NFC-e,
     */
    public int getMod() {
        return mod;
    }

    /**
     *
     * @param mod 55=NF-e emitida em substituição ao modelo 1 ou 1A; 65=NFC-e,
     * utilizada nas operações de venda no varejo (a critério da UF aceitar este
     * modelo de documento).
     */
    public void setMod(int mod) {
        this.mod = mod;
    }

    /**
     *
     * @return se a NF-e ja foi transmitida
     */
    public boolean getEmitida() {
        return emitida;
    }

    /**
     *
     * @param emitida marcar verdadeiro depois de transmitida a NF-e
     */
    public void setEmitida(boolean emitida) {
        this.emitida = emitida;
    }

    public String getEnviNFe() {
        return enviNFe;
    }

    public void setEnviNFe(String enviNFe) {
        this.enviNFe = enviNFe.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
    }

    public String getRetConsReciNFe() {
        return ManagerString.removeAcentos(retConsReciNFe);
    }

    public void setRetConsReciNFe(String retConsReciNFe) {
        this.retConsReciNFe = retConsReciNFe.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }


    public String getProcNFe() {
        return procNFe;
    }

    public void setProcNFe(String procNFe) {
        this.procNFe = procNFe;
    }

    public Integer getIdTrasportadora() {
        return idTrasportadora;
    }

    public void setIdTrasportadora(Integer idTrasportadora) {
        this.idTrasportadora = idTrasportadora;
    }

    public String getModFrete() {
        return modFrete;
    }

    public void setModFrete(String modFrete) {
        this.modFrete = modFrete;
    }

    public String getcDV() {
        return cDV;
    }

    public String getcMunFG() {
        return cMunFG;
    }

    public String getcNF() {
        return cNF;
    }

    public String getcUF() {
        return cUF;
    }

    public boolean isEmitida() {
        return emitida;
    }

    public String getnNF() {
        return nNF;
    }

    public boolean isIndSinc() {
        return indSinc;
    }

    public void setcDV(String cDV) {
        this.cDV = cDV;
    }

    public boolean isTpNF() {
        return tpNF;
    }

    public void setcMunFG(String cMunFG) {
        this.cMunFG = cMunFG;
    }

    public void setcNF(String cNF) {
        this.cNF = cNF;
    }

    public void setcUF(String cUF) {
        this.cUF = cUF;
    }

    public void setnNF(String nNF) {
        this.nNF = nNF;
    }

    public String getInfCpl() {
        return infCpl;
    }

    public void setInfCpl(String infCpl) {
        this.infCpl = infCpl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nfeId != null ? nfeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysNFe)) {
            return false;
        }
        JsysNFe other = (JsysNFe) object;
        return (this.nfeId != null || other.nfeId == null) && (this.nfeId == null || this.nfeId.equals(other.nfeId));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysNFe[ nfeId=" + nfeId + " ]";
    }

    public Boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(Boolean cancelada) {
        this.cancelada = cancelada;
    }

    @XmlTransient
    public Collection<JsysNFeVolumes> getJsysNFeVolumesCollection() {
        return jsysNFeVolumesCollection;
    }

    public void setJsysNFeVolumesCollection(Collection<JsysNFeVolumes> jsysNFeVolumesCollection) {
        this.jsysNFeVolumesCollection = jsysNFeVolumesCollection;
    }

    @XmlTransient
    public Collection<JsysNFeReferencias> getJsysNFeReferenciasCollection() {
        return jsysNFeReferenciasCollection;
    }

    public void setJsysNFeReferenciasCollection(Collection<JsysNFeReferencias> jsysNFeReferenciasCollection) {
        this.jsysNFeReferenciasCollection = jsysNFeReferenciasCollection;
    }
}
