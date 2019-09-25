package br.sql.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysParametros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysParametros.findAll", query = "SELECT j FROM JsysParametros j")
    ,@NamedQuery(name = "JsysParametros.findByFantasia", query = "SELECT j FROM JsysParametros j WHERE j.fantasia = :fantasia")
    ,@NamedQuery(name = "JsysParametros.findByRazaoSocial", query = "SELECT j FROM JsysParametros j WHERE j.razaoSocial = :razaoSocial")
    ,@NamedQuery(name = "JsysParametros.findByCnpj", query = "SELECT j FROM JsysParametros j WHERE j.cnpj = :cnpj")
    ,@NamedQuery(name = "JsysParametros.findByInscricao", query = "SELECT j FROM JsysParametros j WHERE j.inscricao = :inscricao")
    ,@NamedQuery(name = "JsysParametros.findBySuframa", query = "SELECT j FROM JsysParametros j WHERE j.suframa = :suframa")
    ,@NamedQuery(name = "JsysParametros.findByEndereco", query = "SELECT j FROM JsysParametros j WHERE j.endereco = :endereco")
    ,@NamedQuery(name = "JsysParametros.findByNumero", query = "SELECT j FROM JsysParametros j WHERE j.numero = :numero")
    ,@NamedQuery(name = "JsysParametros.findByComplemento", query = "SELECT j FROM JsysParametros j WHERE j.complemento = :complemento")
    ,@NamedQuery(name = "JsysParametros.findByBairro", query = "SELECT j FROM JsysParametros j WHERE j.bairro = :bairro")
    ,@NamedQuery(name = "JsysParametros.findByCidade", query = "SELECT j FROM JsysParametros j WHERE j.cidade = :cidade")
    ,@NamedQuery(name = "JsysParametros.findByCodMunicipio", query = "SELECT j FROM JsysParametros j WHERE j.codMunicipio = :codMunicipio")
    ,@NamedQuery(name = "JsysParametros.findByUf", query = "SELECT j FROM JsysParametros j WHERE j.uf = :uf")
    ,@NamedQuery(name = "JsysParametros.findByCep", query = "SELECT j FROM JsysParametros j WHERE j.cep = :cep")
    ,@NamedQuery(name = "JsysParametros.findByPais", query = "SELECT j FROM JsysParametros j WHERE j.pais = :pais")
    ,@NamedQuery(name = "JsysParametros.findByCodPais", query = "SELECT j FROM JsysParametros j WHERE j.codPais = :codPais")
    ,@NamedQuery(name = "JsysParametros.findByFone", query = "SELECT j FROM JsysParametros j WHERE j.fone = :fone")
    ,@NamedQuery(name = "JsysParametros.findByFax", query = "SELECT j FROM JsysParametros j WHERE j.fax = :fax")
    ,@NamedQuery(name = "JsysParametros.findByEmail", query = "SELECT j FROM JsysParametros j WHERE j.email = :email")
    ,@NamedQuery(name = "JsysParametros.findByMensagem", query = "SELECT j FROM JsysParametros j WHERE j.mensagem = :mensagem")
    ,@NamedQuery(name = "JsysParametros.findByJuros", query = "SELECT j FROM JsysParametros j WHERE j.juros = :juros")
    ,@NamedQuery(name = "JsysParametros.findBySelecionarEstoque", query = "SELECT j FROM JsysParametros j WHERE j.selecionarEstoque = :selecionarEstoque")
    ,@NamedQuery(name = "JsysParametros.findByIdForncedor", query = "SELECT j FROM JsysParametros j WHERE j.idForncedor = :idForncedor")
    ,@NamedQuery(name = "JsysParametros.findBySqlProduto", query = "SELECT j FROM JsysParametros j WHERE j.sqlProduto = :sqlProduto")
    ,@NamedQuery(name = "JsysParametros.findBySqlCliente", query = "SELECT j FROM JsysParametros j WHERE j.sqlCliente = :sqlCliente")
    ,@NamedQuery(name = "JsysParametros.findByCartaoVendas", query = "SELECT j FROM JsysParametros j WHERE j.cartaoVendas = :cartaoVendas")
    ,@NamedQuery(name = "JsysParametros.findByCartaoVendedor", query = "SELECT j FROM JsysParametros j WHERE j.cartaoVendedor = :cartaoVendedor")
    ,@NamedQuery(name = "JsysParametros.findByCfop", query = "SELECT j FROM JsysParametros j WHERE j.cfop = :cfop")
    ,@NamedQuery(name = "JsysParametros.findByCfopSub", query = "SELECT j FROM JsysParametros j WHERE j.cfopSub = :cfopSub")
    ,@NamedQuery(name = "JsysParametros.findByCnae", query = "SELECT j FROM JsysParametros j WHERE j.cnae = :cnae")
    ,@NamedQuery(name = "JsysParametros.findByIm", query = "SELECT j FROM JsysParametros j WHERE j.im = :im")
    ,@NamedQuery(name = "JsysParametros.findByNNFeserie", query = "SELECT j FROM JsysParametros j WHERE j.nNFeserie = :nNFeserie")
    ,@NamedQuery(name = "JsysParametros.findByVercaoSystema", query = "SELECT j FROM JsysParametros j WHERE j.vercaoSystema = :vercaoSystema")
    ,@NamedQuery(name = "JsysParametros.findByVercaoDB", query = "SELECT j FROM JsysParametros j WHERE j.vercaoDB = :vercaoDB")
    ,@NamedQuery(name = "JsysParametros.findByDeposito", query = "SELECT j FROM JsysParametros j WHERE j.deposito = :deposito")
    ,@NamedQuery(name = "JsysParametros.findByIdDeposito", query = "SELECT j FROM JsysParametros j WHERE j.idDeposito = :idDeposito")
    ,@NamedQuery(name = "JsysParametros.findByIdGeralCheque", query = "SELECT j FROM JsysParametros j WHERE j.idGeralCheque = :idGeralCheque")
    ,@NamedQuery(name = "JsysParametros.findByIdGeralAberturaCaixa", query = "SELECT j FROM JsysParametros j WHERE j.idGeralAberturaCaixa = :idGeralAberturaCaixa")
    ,@NamedQuery(name = "JsysParametros.findByIdGeralRetiradas", query = "SELECT j FROM JsysParametros j WHERE j.idGeralRetiradas = :idGeralRetiradas")
    ,@NamedQuery(name = "JsysParametros.findByIdGeralSobraCaixa", query = "SELECT j FROM JsysParametros j WHERE j.idGeralSobraCaixa = :idGeralSobraCaixa")
    ,@NamedQuery(name = "JsysParametros.findByIdGeralFaltaCaixa", query = "SELECT j FROM JsysParametros j WHERE j.idGeralFaltaCaixa = :idGeralFaltaCaixa")
    ,@NamedQuery(name = "JsysParametros.findByIdGeralPagamento", query = "SELECT j FROM JsysParametros j WHERE j.idGeralPagamento = :idGeralPagamento")
    ,@NamedQuery(name = "JsysParametros.findByIdTituloDinhero", query = "SELECT j FROM JsysParametros j WHERE j.idTituloDinhero = :idTituloDinhero")})
public class JsysParametros implements Serializable {

    @Transient
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;

    @OneToOne(optional = false)
    @JoinColumn(name = "idTributacao", referencedColumnName = "idTributacao")
    private JsysProdutosTTributacao idTributacao;

    @Basic(optional = false)
    @Column(name = "fantasia")
    private String fantasia;

    @Basic(optional = false)
    @Column(name = "razaoSocial")
    private String razaoSocial;

    @Id
    @Basic(optional = false)
    @Column(name = "cnpj")
    private String cnpj;

    @Basic(optional = false)
    @Column(name = "inscricao")
    private String inscricao;

    @Basic(optional = false)
    @Column(name = "suframa")
    private String suframa;

    @Basic(optional = false)
    @Column(name = "endereco")
    private String endereco;

    @Basic(optional = false)
    @Column(name = "numero")
    private String numero;

    @Basic(optional = false)
    @Column(name = "complemento")
    private String complemento;

    @Basic(optional = false)
    @Column(name = "bairro")
    private String bairro;

    @Basic(optional = false)
    @Column(name = "cidade")
    private String cidade;

    @Basic(optional = false)
    @Column(name = "codMunicipio")
    private int codMunicipio;

    @Basic(optional = false)
    @Column(name = "uf")
    private String uf;

    @Basic(optional = false)
    @Column(name = "cep")
    private String cep;

    @Basic(optional = false)
    @Column(name = "pais")
    private String pais;

    @Basic(optional = false)
    @Column(name = "codPais")
    private int codPais;

    @Column(name = "fone")
    private String fone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Column(name = "mensagem")
    private String mensagem;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "juros")
    private BigDecimal juros;

    @Basic(optional = false)
    @Column(name = "selecionarEstoque")
    private boolean selecionarEstoque;

    @Basic(optional = false)
    @Column(name = "idForncedor")
    private int idForncedor;

    @Column(name = "sqlProduto")
    private String sqlProduto;

    @Column(name = "sqlCliente")
    private String sqlCliente;

    @Basic(optional = false)
    @Column(name = "cartaoVendas")
    private boolean cartaoVendas;

    @Basic(optional = false)
    @Column(name = "cartaoVendedor")
    private boolean cartaoVendedor;

    @Basic(optional = false)
    @Column(name = "cfop")
    private String cfop;

    @Basic(optional = false)
    @Column(name = "cfopSub")
    private String cfopSub;

    @Basic(optional = false)
    @Column(name = "crt")
    private String crt;

    @Basic(optional = false)
    @Column(name = "CNAE")
    private String cnae;

    @Basic(optional = false)
    @Column(name = "IM")
    private String im;

    @Basic(optional = false)
    @Column(name = "nNFeserie")
    private String nNFeserie;

    @Basic(optional = false)
    @Column(name = "vercaoSystema")
    private long vercaoSystema;

    @Basic(optional = false)
    @Column(name = "vercaoDB")
    private long vercaoDB;

    @Basic(optional = false)
    @Column(name = "deposito")
    private boolean deposito;

    @Basic(optional = false)
    @Column(name = "idDeposito")
    private int idDeposito;

    @Column(name = "idGeralCheque")
    private String idGeralCheque;

    @Column(name = "idGeralAberturaCaixa")
    private String idGeralAberturaCaixa;

    @Column(name = "idGeralRetiradas")
    private String idGeralRetiradas;

    @Column(name = "idGeralSobraCaixa")
    private String idGeralSobraCaixa;

    @Column(name = "idGeralFaltaCaixa")
    private String idGeralFaltaCaixa;

    @Column(name = "idGeralPagamento")
    private String idGeralPagamento;

    @Column(name = "idTituloDinhero")
    private String idTituloDinhero;

    @Column(name = "infCpl")
    private String infCpl;

    @Column(name = "caminhoDoCertificadoDoCliente")
    private String caminhoDoCertificadoDoCliente;

    @Column(name = "senhaDoCertificadoDoCliente")
    private String senhaDoCertificadoDoCliente;

    @Column(name = "cIdToken")
    private String cIdToken;

    @Column(name = "CSC")
    private String CSC;

    @Basic(optional = false)
    @Column(name = "idConsumidorFinal")
    private Integer idConsumidorFinal;

    @Column(name = "logo")
    private String logo;

    @Column(name = "tempoIntervalo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tempoIntervalo;

    @Basic(optional = false)
    @Column(name = "simplesNacinal")
    private boolean simplesNacinal;

    @Column(name = "utilizarNfce")
    private Boolean utilizarNfce;

    @Column(name = "quantIntervalo")
    private Integer quantIntervalo;

    @Column(name = "bloquearRankVendedor")
    private Boolean bloquearRankVendedor;

    @Column(name = "contingencia")
    private Boolean contingencia;

    @Column(name = "tpEmis")
    private Short tpEmis;

    @Column(name = "dhCont")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dhCont;

    @Column(name = "xJust")
    private String xJust;

    @Column(name = "vias")
    private Boolean vias;

    @Basic(optional = false)
    @Column(name = "limiteDesconto")
    private BigDecimal limiteDesconto;

    @Basic(optional = false)
    @Column(name = "cfopInterestadual")
    private String cfopInterestadual;

    @Basic(optional = false)
    @Column(name = "cfopSubcfopInterestadual")
    private String cfopSubcfopInterestadual;

    @Column(name = "CStat65")
    private String cStat65;

    @Column(name = "CStat65Hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cStat65Hora;

    @Column(name = "CStat55")
    private String cStat55;

    @Column(name = "CStat55Hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cStat55Hora;

    @Basic(optional = false)
    @Column(name = "idBancoCofre")
    private Integer idBancoCofre;

    @Basic(optional = false)
    @Column(name = "idBancoAjuste")
    private Integer idBancoAjuste;

    @Basic(optional = false)
    @Column(name = "validarNFCe")
    private Boolean validarNFCe;

    @Basic(optional = false)
    @Column(name = "forcarCodigoBarrasVenda")
    private Boolean forcarCodigoBarrasVenda;

    @Basic(optional = false)
    @Column(name = "localInstalador")
    private String localInstalador;

    @Column(name = "dataInicioFolhaPagamento")
    private String dataInicioFolhaPagamento;

    @Column(name = "dataFimFolhaPagamento")
    private String dataFimFolhaPagamento;

    /**
     *
     */
    public JsysParametros() {
    }

    /**
     *
     * @param cnpj
     */
    public JsysParametros(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     *
     * @return Fantasia da Empresa
     */
    public String getFantasia() {
        return fantasia;
    }

    /**
     *
     * @param fantasia
     */
    public void setFantasia(String fantasia) {
        String oldFantasia = this.fantasia;
        this.fantasia = fantasia;
        changeSupport.firePropertyChange("fantasia", oldFantasia, fantasia);
    }

    /**
     *
     * @return Razao social da empresa
     */
    public String getRazaoSocial() {
        return razaoSocial;
    }

    /**
     *
     * @param razaoSocial
     */
    public void setRazaoSocial(String razaoSocial) {
        String oldRazaoSocial = this.razaoSocial;
        this.razaoSocial = razaoSocial;
        changeSupport.firePropertyChange("razaoSocial", oldRazaoSocial, razaoSocial);
    }

    /**
     *
     * @return cnpj da empresa
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     *
     * @param cnpj
     */
    public void setCnpj(String cnpj) {
        String oldCnpj = this.cnpj;
        this.cnpj = cnpj;
        changeSupport.firePropertyChange("cnpj", oldCnpj, cnpj);
    }

    /**
     *
     * @return
     */
    public String getInscricao() {
        return inscricao;
    }

    /**
     *
     * @param inscricao
     */
    public void setInscricao(String inscricao) {
        String oldInscricao = this.inscricao;
        this.inscricao = inscricao;
        changeSupport.firePropertyChange("inscricao", oldInscricao, inscricao);
    }

    /**
     *
     * @return
     */
    public String getSuframa() {
        return suframa;
    }

    /**
     *
     * @param suframa
     */
    public void setSuframa(String suframa) {
        String oldSuframa = this.suframa;
        this.suframa = suframa;
        changeSupport.firePropertyChange("suframa", oldSuframa, suframa);
    }

    /**
     *
     * @return
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     *
     * @param endereco
     */
    public void setEndereco(String endereco) {
        String oldEndereco = this.endereco;
        this.endereco = endereco;
        changeSupport.firePropertyChange("endereco", oldEndereco, endereco);
    }

    /**
     *
     * @return
     */
    public String getNumero() {
        return numero;
    }

    /**
     *
     * @param numero
     */
    public void setNumero(String numero) {
        String oldNumero = this.numero;
        this.numero = numero;
        changeSupport.firePropertyChange("numero", oldNumero, numero);
    }

    /**
     *
     * @return
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     *
     * @param complemento
     */
    public void setComplemento(String complemento) {
        String oldComplemento = this.complemento;
        this.complemento = complemento;
        changeSupport.firePropertyChange("complemento", oldComplemento, complemento);
    }

    /**
     *
     * @return o bairro da empressa
     */
    public String getBairro() {
        return bairro;
    }

    /**
     *
     * @param bairro
     */
    public void setBairro(String bairro) {
        String oldBairro = this.bairro;
        this.bairro = bairro;
        changeSupport.firePropertyChange("bairro", oldBairro, bairro);
    }

    /**
     *
     * @return
     */
    public String getCidade() {
        return cidade;
    }

    /**
     *
     * @param cidade
     */
    public void setCidade(String cidade) {
        String oldCidade = this.cidade;
        this.cidade = cidade;
        changeSupport.firePropertyChange("cidade", oldCidade, cidade);
    }

    /**
     *
     * @return
     */
    public int getCodMunicipio() {
        return codMunicipio;
    }

    /**
     *
     * @param codMunicipio
     */
    public void setCodMunicipio(int codMunicipio) {
        int oldCodMunicipio = this.codMunicipio;
        this.codMunicipio = codMunicipio;
        changeSupport.firePropertyChange("codMunicipio", oldCodMunicipio, codMunicipio);
    }

    /**
     *
     * @return
     */
    public String getUf() {
        return uf;
    }

    /**
     *
     * @param uf
     */
    public void setUf(String uf) {
        String oldUf = this.uf;
        this.uf = uf;
        changeSupport.firePropertyChange("uf", oldUf, uf);
    }

    /**
     *
     * @return
     */
    public String getCep() {
        return cep;
    }

    /**
     *
     * @param cep
     */
    public void setCep(String cep) {
        String oldCep = this.cep;
        this.cep = cep;
        changeSupport.firePropertyChange("cep", oldCep, cep);
    }

    /**
     *
     * @return
     */
    public String getPais() {
        return pais;
    }

    /**
     *
     * @param pais
     */
    public void setPais(String pais) {
        String oldPais = this.pais;
        this.pais = pais;
        changeSupport.firePropertyChange("pais", oldPais, pais);
    }

    /**
     *
     * @return
     */
    public int getCodPais() {
        return codPais;
    }

    /**
     *
     * @param codPais
     */
    public void setCodPais(int codPais) {
        int oldCodPais = this.codPais;
        this.codPais = codPais;
        changeSupport.firePropertyChange("codPais", oldCodPais, codPais);
    }

    /**
     *
     * @return
     */
    public String getFone() {
        return fone;
    }

    /**
     *
     * @param fone
     */
    public void setFone(String fone) {
        String oldFone = this.fone;
        this.fone = fone;
        changeSupport.firePropertyChange("fone", oldFone, fone);
    }

    /**
     *
     * @return
     */
    public String getFax() {
        return fax;
    }

    /**
     *
     * @param fax
     */
    public void setFax(String fax) {
        String oldFax = this.fax;
        this.fax = fax;
        changeSupport.firePropertyChange("fax", oldFax, fax);
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        String oldEmail = this.email;
        this.email = email;
        changeSupport.firePropertyChange("email", oldEmail, email);
    }

    /**
     *
     * @return
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     *
     * @param mensagem
     */
    public void setMensagem(String mensagem) {
        String oldMensagem = this.mensagem;
        this.mensagem = mensagem;
        changeSupport.firePropertyChange("mensagem", oldMensagem, mensagem);
    }

    /**
     *
     * @return
     */
    public BigDecimal getJuros() {
        return juros;
    }

    /**
     *
     * @param juros
     */
    public void setJuros(BigDecimal juros) {
        BigDecimal oldJuros = this.juros;
        this.juros = juros;
        changeSupport.firePropertyChange("juros", oldJuros, juros);
    }

    /**
     *
     * @return
     */
    public boolean getSelecionarEstoque() {
        return selecionarEstoque;
    }

    /**
     *
     * @param selecionarEstoque
     */
    public void setSelecionarEstoque(boolean selecionarEstoque) {
        boolean oldSelecionarEstoque = this.selecionarEstoque;
        this.selecionarEstoque = selecionarEstoque;
        changeSupport.firePropertyChange("selecionarEstoque", oldSelecionarEstoque, selecionarEstoque);
    }

    /**
     *
     * @return
     */
    public int getIdForncedor() {
        return idForncedor;
    }

    /**
     *
     * @param idForncedor
     */
    public void setIdForncedor(int idForncedor) {
        int oldIdForncedor = this.idForncedor;
        this.idForncedor = idForncedor;
        changeSupport.firePropertyChange("idForncedor", oldIdForncedor, idForncedor);
    }

    /**
     *
     * @return
     */
    public String getSqlProduto() {
        return sqlProduto;
    }

    /**
     *
     * @param sqlProduto
     */
    public void setSqlProduto(String sqlProduto) {
        String oldSqlProduto = this.sqlProduto;
        this.sqlProduto = sqlProduto;
        changeSupport.firePropertyChange("sqlProduto", oldSqlProduto, sqlProduto);
    }

    /**
     *
     * @return
     */
    public String getSqlCliente() {
        return sqlCliente;
    }

    /**
     *
     * @param sqlCliente
     */
    public void setSqlCliente(String sqlCliente) {
        String oldSqlCliente = this.sqlCliente;
        this.sqlCliente = sqlCliente;
        changeSupport.firePropertyChange("sqlCliente", oldSqlCliente, sqlCliente);
    }

    /**
     *
     * @return
     */
    public boolean getCartaoVendas() {
        return cartaoVendas;
    }

    /**
     *
     * @param cartaoVendas
     */
    public void setCartaoVendas(boolean cartaoVendas) {
        boolean oldCartaoVendas = this.cartaoVendas;
        this.cartaoVendas = cartaoVendas;
        changeSupport.firePropertyChange("cartaoVendas", oldCartaoVendas, cartaoVendas);
    }

    /**
     *
     * @return
     */
    public boolean getCartaoVendedor() {
        return cartaoVendedor;
    }

    /**
     *
     * @param cartaoVendedor
     */
    public void setCartaoVendedor(boolean cartaoVendedor) {
        boolean oldCartaoVendedor = this.cartaoVendedor;
        this.cartaoVendedor = cartaoVendedor;
        changeSupport.firePropertyChange("cartaoVendedor", oldCartaoVendedor, cartaoVendedor);
    }

    /**
     *
     * @return
     */
    public String getCfop() {
        return cfop;
    }

    /**
     *
     * @param cfop
     */
    public void setCfop(String cfop) {
        String oldCfop = this.cfop;
        this.cfop = cfop;
        changeSupport.firePropertyChange("cfop", oldCfop, cfop);
    }

    /**
     *
     * @return
     */
    public String getCfopSub() {
        return cfopSub;
    }

    /**
     *
     * @param cfopSub
     */
    public void setCfopSub(String cfopSub) {
        String oldCfopSub = this.cfopSub;
        this.cfopSub = cfopSub;
        changeSupport.firePropertyChange("cfopSub", oldCfopSub, cfopSub);
    }

    /**
     *
     * @return Código de Regime Tributário 1 - Simples Nacional 2 - Simples
     * Nacional - excesso de sublimite da receita bruta 3 - Regime Normal
     */
    public String getCrt() {
        return crt;
    }

    /**
     *
     * @param crt
     */
    public void setCrt(String crt) {
        String oldCrt = this.crt;
        this.crt = crt;
        changeSupport.firePropertyChange("crt", oldCrt, crt);
    }

    /**
     *
     * @return
     */
    public String getCnae() {
        return cnae;
    }

    /**
     *
     * @param cnae
     */
    public void setCnae(String cnae) {
        String oldCnae = this.cnae;
        this.cnae = cnae;
        changeSupport.firePropertyChange("cnae", oldCnae, cnae);
    }

    /**
     *
     * @return inscrição Municipal
     */
    public String getIm() {
        return im;
    }

    /**
     *
     * @param im
     */
    public void setIm(String im) {
        String oldIm = this.im;
        this.im = im;
        changeSupport.firePropertyChange("im", oldIm, im);
    }

    /**
     *
     * @return
     */
    public long getVercaoSystema() {
        return vercaoSystema;
    }

    /**
     *
     * @param vercaoSystema
     */
    public void setVercaoSystema(long vercaoSystema) {
        long oldVercaoSystema = this.vercaoSystema;
        this.vercaoSystema = vercaoSystema;
        changeSupport.firePropertyChange("vercaoSystema", oldVercaoSystema, vercaoSystema);
    }

    /**
     *
     * @return
     */
    public long getVercaoDB() {
        return vercaoDB;
    }

    /**
     *
     * @param vercaoDB
     */
    public void setVercaoDB(long vercaoDB) {
        long oldVercaoDB = this.vercaoDB;
        this.vercaoDB = vercaoDB;
        changeSupport.firePropertyChange("vercaoDB", oldVercaoDB, vercaoDB);
    }

    /**
     *
     * @return
     */
    public boolean getDeposito() {
        return deposito;
    }

    /**
     *
     * @param deposito
     */
    public void setDeposito(boolean deposito) {
        boolean oldDeposito = this.deposito;
        this.deposito = deposito;
        changeSupport.firePropertyChange("deposito", oldDeposito, deposito);
    }

    /**
     *
     * @return
     */
    public int getIdDeposito() {
        return idDeposito;
    }

    /**
     *
     * @param idDeposito
     */
    public void setIdDeposito(int idDeposito) {
        int oldIdDeposito = this.idDeposito;
        this.idDeposito = idDeposito;
        changeSupport.firePropertyChange("idDeposito", oldIdDeposito, idDeposito);
    }

    /**
     *
     * @return
     */
    public String getIdGeralCheque() {
        return idGeralCheque;
    }

    /**
     *
     * @param idGeralCheque
     */
    public void setIdGeralCheque(String idGeralCheque) {
        String oldIdGeralCheque = this.idGeralCheque;
        this.idGeralCheque = idGeralCheque;
        changeSupport.firePropertyChange("idGeralCheque", oldIdGeralCheque, idGeralCheque);
    }

    /**
     *
     * @return
     */
    public String getIdGeralAberturaCaixa() {
        return idGeralAberturaCaixa;
    }

    /**
     *
     * @param idGeralAberturaCaixa
     */
    public void setIdGeralAberturaCaixa(String idGeralAberturaCaixa) {
        String oldIdGeralAberturaCaixa = this.idGeralAberturaCaixa;
        this.idGeralAberturaCaixa = idGeralAberturaCaixa;
        changeSupport.firePropertyChange("idGeralAberturaCaixa", oldIdGeralAberturaCaixa, idGeralAberturaCaixa);
    }

    /**
     *
     * @return
     */
    public String getIdGeralRetiradas() {
        return idGeralRetiradas;
    }

    /**
     *
     * @param idGeralRetiradas
     */
    public void setIdGeralRetiradas(String idGeralRetiradas) {
        String oldIdGeralRetiradas = this.idGeralRetiradas;
        this.idGeralRetiradas = idGeralRetiradas;
        changeSupport.firePropertyChange("idGeralRetiradas", oldIdGeralRetiradas, idGeralRetiradas);
    }

    /**
     *
     * @return
     */
    public String getIdGeralSobraCaixa() {
        return idGeralSobraCaixa;
    }

    /**
     *
     * @param idGeralSobraCaixa
     */
    public void setIdGeralSobraCaixa(String idGeralSobraCaixa) {
        String oldIdGeralSobraCaixa = this.idGeralSobraCaixa;
        this.idGeralSobraCaixa = idGeralSobraCaixa;
        changeSupport.firePropertyChange("idGeralSobraCaixa", oldIdGeralSobraCaixa, idGeralSobraCaixa);
    }

    /**
     *
     * @return
     */
    public String getIdGeralFaltaCaixa() {
        return idGeralFaltaCaixa;
    }

    /**
     *
     * @param idGeralFaltaCaixa
     */
    public void setIdGeralFaltaCaixa(String idGeralFaltaCaixa) {
        String oldIdGeralFaltaCaixa = this.idGeralFaltaCaixa;
        this.idGeralFaltaCaixa = idGeralFaltaCaixa;
        changeSupport.firePropertyChange("idGeralFaltaCaixa", oldIdGeralFaltaCaixa, idGeralFaltaCaixa);
    }

    /**
     *
     * @return
     */
    public String getIdGeralPagamento() {
        return idGeralPagamento;
    }

    /**
     *
     * @param idGeralPagamento
     */
    public void setIdGeralPagamento(String idGeralPagamento) {
        String oldIdGeralPagamento = this.idGeralPagamento;
        this.idGeralPagamento = idGeralPagamento;
        changeSupport.firePropertyChange("idGeralPagamento", oldIdGeralPagamento, idGeralPagamento);
    }

    /**
     *
     * @return
     */
    public String getIdTituloDinhero() {
        return idTituloDinhero;
    }

    /**
     *
     * @param idTituloDinhero
     */
    public void setIdTituloDinhero(String idTituloDinhero) {
        String oldIdTituloDinhero = this.idTituloDinhero;
        this.idTituloDinhero = idTituloDinhero;
        changeSupport.firePropertyChange("idTituloDinhero", oldIdTituloDinhero, idTituloDinhero);
    }

    /**
     *
     * @return
     */
    public String getnNFeserie() {
        return nNFeserie;
    }

    /**
     *
     * @param nNFeserie
     */
    public void setnNFeserie(String nNFeserie) {
        this.nNFeserie = nNFeserie;
    }

    /**
     *
     * @return Retorna Informações Complementares de interesse do Contribuinte
     */
    public String getInfCpl() {
        return infCpl;
    }

    /**
     *
     * @param infCpl
     */
    public void setInfCpl(String infCpl) {
        String oldInfCpl = this.infCpl;
        this.infCpl = infCpl;
        changeSupport.firePropertyChange("infCpl", oldInfCpl, infCpl);
    }

    public String getCaminhoDoCertificadoDoCliente() {
        return caminhoDoCertificadoDoCliente;
    }

    public void setCaminhoDoCertificadoDoCliente(String caminhoDoCertificadoDoCliente) {
        String oldCaminhoDoCertificadoDoCliente = this.caminhoDoCertificadoDoCliente;
        this.caminhoDoCertificadoDoCliente = caminhoDoCertificadoDoCliente;
        changeSupport.firePropertyChange("caminhoDoCertificadoDoCliente", oldCaminhoDoCertificadoDoCliente, caminhoDoCertificadoDoCliente);
    }

    public String getSenhaDoCertificadoDoCliente() {
        return senhaDoCertificadoDoCliente;
    }

    public void setSenhaDoCertificadoDoCliente(String senhaDoCertificadoDoCliente) {
        String oldSenhaDoCertificadoDoCliente = this.senhaDoCertificadoDoCliente;
        this.senhaDoCertificadoDoCliente = senhaDoCertificadoDoCliente;
        changeSupport.firePropertyChange("senhaDoCertificadoDoCliente", oldSenhaDoCertificadoDoCliente, senhaDoCertificadoDoCliente);
    }

    public String getcIdToken() {
        return cIdToken;
    }

    public void setcIdToken(String cIdToken) {
        this.cIdToken = cIdToken;
    }

    public String getCSC() {
        return CSC;
    }

    public void setCSC(String CSC) {
        String oldCSC = this.CSC;
        this.CSC = CSC;
        changeSupport.firePropertyChange("CSC", oldCSC, CSC);
    }

    public Integer getIdConsumidorFinal() {
        return idConsumidorFinal;
    }

    public void setIdConsumidorFinal(Integer idConsumidorFinal) {
        Integer oldIdConsumidorFinal = this.idConsumidorFinal;
        this.idConsumidorFinal = idConsumidorFinal;
        changeSupport.firePropertyChange("idConsumidorFinal", oldIdConsumidorFinal, idConsumidorFinal);
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        String oldLogo = this.logo;
        this.logo = logo;
        changeSupport.firePropertyChange("logo", oldLogo, logo);
    }

    public Date getTempoIntervalo() {
        return tempoIntervalo;
    }

    public void setTempoIntervalo(Date tempoIntervalo) {
        Date oldTempoIntervalo = this.tempoIntervalo;
        this.tempoIntervalo = tempoIntervalo;
        changeSupport.firePropertyChange("tempoIntervalo", oldTempoIntervalo, tempoIntervalo);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cnpj != null ? cnpj.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysParametros)) {
            return false;
        }
        JsysParametros other = (JsysParametros) object;
        return (this.cnpj != null || other.cnpj == null) && (this.cnpj == null || this.cnpj.equals(other.cnpj));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysParametros[ cnpj=" + cnpj + " ]";
    }

    public boolean getSimplesNacinal() {
        return simplesNacinal;
    }

    public void setSimplesNacinal(boolean simplesNacinal) {
        boolean oldSimplesNacinal = this.simplesNacinal;
        this.simplesNacinal = simplesNacinal;
        changeSupport.firePropertyChange("simplesNacinal", oldSimplesNacinal, simplesNacinal);
    }

    public Boolean getUtilizarNfce() {
        return utilizarNfce;
    }

    public void setUtilizarNfce(Boolean utilizarNfce) {
        Boolean oldUtilizarNfce = this.utilizarNfce;
        this.utilizarNfce = utilizarNfce;
        changeSupport.firePropertyChange("utilizarNfce", oldUtilizarNfce, utilizarNfce);
    }

    public Integer getQuantIntervalo() {
        return quantIntervalo;
    }

    public void setQuantIntervalo(Integer quantIntervalo) {
        Integer oldQuantIntervalo = this.quantIntervalo;
        this.quantIntervalo = quantIntervalo;
        changeSupport.firePropertyChange("quantIntervalo", oldQuantIntervalo, quantIntervalo);
    }

    public Boolean getBloquearRankVendedor() {
        return bloquearRankVendedor;
    }

    public void setBloquearRankVendedor(Boolean bloquearRankVendedor) {
        Boolean oldBloquearRankVendedor = this.bloquearRankVendedor;
        this.bloquearRankVendedor = bloquearRankVendedor;
        changeSupport.firePropertyChange("bloquearRankVendedor", oldBloquearRankVendedor, bloquearRankVendedor);
    }

    public Boolean getContingencia() {
        return contingencia;
    }

    public void setContingencia(Boolean contingencia) {
        Boolean oldContingencia = this.contingencia;
        this.contingencia = contingencia;
        changeSupport.firePropertyChange("contingencia", oldContingencia, contingencia);
    }

    public Short getTpEmis() {
        return tpEmis;
    }

    public void setTpEmis(Short tpEmis) {
        Short oldTpEmis = this.tpEmis;
        this.tpEmis = tpEmis;
        changeSupport.firePropertyChange("tpEmis", oldTpEmis, tpEmis);
    }

    public Date getDhCont() {
        return dhCont;
    }

    public void setDhCont(Date dhCont) {
        Date oldDhCont = this.dhCont;
        this.dhCont = dhCont;
        changeSupport.firePropertyChange("dhCont", oldDhCont, dhCont);
    }

    public void setVias(Boolean vias) {
        Boolean oldVias = this.vias;
        this.vias = vias;
        changeSupport.firePropertyChange("vias", oldVias, vias);
    }

    public Boolean getVias() {
        return vias;
    }

    public String getxJust() {
        return xJust;
    }

    public boolean isCartaoVendas() {
        return cartaoVendas;
    }

    public boolean isCartaoVendedor() {
        return cartaoVendedor;
    }

    public boolean isDeposito() {
        return deposito;
    }

    public boolean isSelecionarEstoque() {
        return selecionarEstoque;
    }

    public boolean isSimplesNacinal() {
        return simplesNacinal;
    }

    public void setxJust(String xJust) {
        this.xJust = xJust;
    }

//    public String getCStat65() {
//        return cStat65;
//    }
//
//    public void setCStat65(String cStat65) {
//        this.cStat65 = cStat65;
//    }
//    public Date getCStat65Hora() {
//        return cStat65Hora;
//    }
//
//    public void setCStat65Hora(Date cStat65Hora) {
//        this.cStat65Hora = cStat65Hora;
//    }
//    public String getCStat55() {
//        return cStat55;
//    }
//
//    public void setCStat55(String cStat55) {
//        this.cStat55 = cStat55;
//    }
//    public Date getCStat55Hora() {
//        return cStat55Hora;
//    }
//
//    public void setCStat55Hora(Date cStat55Hora) {
//        this.cStat55Hora = cStat55Hora;
//    }
    public String getCfopInterestadual() {
        return cfopInterestadual;
    }

    public void setCfopInterestadual(String cfopInterestadual) {
        String oldCfopInterestadual = this.cfopInterestadual;
        this.cfopInterestadual = cfopInterestadual;
        changeSupport.firePropertyChange("cfopInterestadual", oldCfopInterestadual, cfopInterestadual);
    }

    public String getCfopSubcfopInterestadual() {
        return cfopSubcfopInterestadual;
    }

    public void setCfopSubcfopInterestadual(String cfopSubcfopInterestadual) {
        String oldCfopSubcfopInterestadual = this.cfopSubcfopInterestadual;
        this.cfopSubcfopInterestadual = cfopSubcfopInterestadual;
        changeSupport.firePropertyChange("cfopSubcfopInterestadual", oldCfopSubcfopInterestadual, cfopSubcfopInterestadual);
    }

    public String getcStat65() {
        return cStat65;
    }

    public void setcStat65(String cStat65) {
        this.cStat65 = cStat65;
    }

    public Date getcStat65Hora() {
        return cStat65Hora;
    }

    public void setcStat65Hora(Date cStat65Hora) {
        this.cStat65Hora = cStat65Hora;
    }

    public String getcStat55() {
        return cStat55;
    }

    public void setcStat55(String cStat55) {
        this.cStat55 = cStat55;
    }

    public Date getcStat55Hora() {
        return cStat55Hora;
    }

    public void setcStat55Hora(Date cStat55Hora) {
        this.cStat55Hora = cStat55Hora;
    }

    public BigDecimal getLimiteDesconto() {
        return limiteDesconto;
    }

    public void setLimiteDesconto(BigDecimal limiteDesconto) {
        BigDecimal oldLimiteDesconto = this.limiteDesconto;
        this.limiteDesconto = limiteDesconto;
        changeSupport.firePropertyChange("limiteDesconto", oldLimiteDesconto, limiteDesconto);
    }

    public Integer getIdBancoCofre() {
        return idBancoCofre;
    }

    public void setIdBancoCofre(Integer idBancoCofre) {
        Integer oldIdBancoCofre = this.idBancoCofre;
        this.idBancoCofre = idBancoCofre;
        changeSupport.firePropertyChange("idBancoCofre", oldIdBancoCofre, idBancoCofre);
    }

    public Integer getIdBancoAjuste() {
        return idBancoAjuste;
    }

    public void setIdBancoAjuste(Integer idBancoAjuste) {
        Integer oldIdBancoAjuste = this.idBancoAjuste;
        this.idBancoAjuste = idBancoAjuste;
        changeSupport.firePropertyChange("idBancoAjuste", oldIdBancoAjuste, idBancoAjuste);
    }

    public Boolean getValidarNFCe() {
        return validarNFCe;
    }

    public void setValidarNFCe(Boolean validarNFCe) {
        Boolean oldValidarNFCe = this.validarNFCe;
        this.validarNFCe = validarNFCe;
        changeSupport.firePropertyChange("validarNFCe", oldValidarNFCe, validarNFCe);
    }

    public Boolean getForcarCodigoBarrasVenda() {
        return forcarCodigoBarrasVenda;
    }

    public void setForcarCodigoBarrasVenda(Boolean forcarCodigoBarrasVenda) {
        Boolean oldForcarCodigoBarrasVenda = this.forcarCodigoBarrasVenda;
        this.forcarCodigoBarrasVenda = forcarCodigoBarrasVenda;
        changeSupport.firePropertyChange("forcarCodigoBarrasVenda", oldForcarCodigoBarrasVenda, forcarCodigoBarrasVenda);
    }

    public JsysProdutosTTributacao getIdTributacao() {
        return idTributacao;
    }

    public void setIdTributacao(JsysProdutosTTributacao idTributacao) {
        this.idTributacao = idTributacao;
    }

    public void setIdTributacao(Object idTributacao) {
        if (idTributacao instanceof JsysProdutosTTributacao) {
            this.idTributacao = (JsysProdutosTTributacao) idTributacao;
        } else {
            this.idTributacao = null;
        }
    }

    public String getLocalInstalador() {
        return localInstalador;
    }

    public void setLocalInstalador(String localInstalador) {
        String oldLocalInstalador = this.localInstalador;
        this.localInstalador = localInstalador;
        changeSupport.firePropertyChange("localInstalador", oldLocalInstalador, localInstalador);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public String getDataInicioFolhaPagamento() {
        return dataInicioFolhaPagamento;
    }

    public void setDataInicioFolhaPagamento(String dataInicioFolhaPagamento) {
        this.dataInicioFolhaPagamento = dataInicioFolhaPagamento;
    }

    public String getDataFimFolhaPagamento() {
        return dataFimFolhaPagamento;
    }

    public void setDataFimFolhaPagamento(String dataFimFolhaPagamento) {
        this.dataFimFolhaPagamento = dataFimFolhaPagamento;
    }

}
