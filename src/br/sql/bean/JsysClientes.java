package br.sql.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "jsysClientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JsysClientes.findAllClientes", query = "SELECT j FROM JsysClientes j WHERE J.inativo = false AND J.cliente = true"),
    @NamedQuery(name = "JsysClientes.findAllFornecedore", query = "SELECT j FROM JsysClientes j WHERE J.inativo = false AND J.fornecedor = true"),
    @NamedQuery(name = "JsysClientes.findAllColaborador", query = "SELECT j FROM JsysClientes j WHERE J.inativo = false AND J.colaborador = true"),
    @NamedQuery(name = "JsysClientes.findAllFilial", query = "SELECT j FROM JsysClientes j WHERE J.inativo = false AND J.filial = true"),
    @NamedQuery(name = "JsysClientes.findAllAtivos", query = "SELECT j FROM JsysClientes j WHERE J.inativo = false"),
    @NamedQuery(name = "JsysClientes.findAll", query = "SELECT j FROM JsysClientes j"),
    @NamedQuery(name = "JsysClientes.findByIdCliente", query = "SELECT j FROM JsysClientes j WHERE j.idCliente = :idCliente"),
    @NamedQuery(name = "JsysClientes.findByIdClienteIsAtivo", query = "SELECT j FROM JsysClientes j WHERE j.idCliente = :idCliente AND J.inativo = false AND J.cliente = true"),
    @NamedQuery(name = "JsysClientes.findByNomeCorentista", query = "SELECT j FROM JsysClientes j WHERE j.nomeCorentista = :nomeCorentista"),
    @NamedQuery(name = "JsysClientes.findByFantasia", query = "SELECT j FROM JsysClientes j WHERE j.fantasia = :fantasia"),
    @NamedQuery(name = "JsysClientes.findByTipo", query = "SELECT j FROM JsysClientes j WHERE j.tipo = :tipo"),
    @NamedQuery(name = "JsysClientes.findBySexo", query = "SELECT j FROM JsysClientes j WHERE j.sexo = :sexo"),
    @NamedQuery(name = "JsysClientes.findByEndereco", query = "SELECT j FROM JsysClientes j WHERE j.endereco = :endereco"),
    @NamedQuery(name = "JsysClientes.findByComplemento", query = "SELECT j FROM JsysClientes j WHERE j.complemento = :complemento"),
    @NamedQuery(name = "JsysClientes.findByBairro", query = "SELECT j FROM JsysClientes j WHERE j.bairro = :bairro"),
    @NamedQuery(name = "JsysClientes.findByCidade", query = "SELECT j FROM JsysClientes j WHERE j.cidade = :cidade"),
    @NamedQuery(name = "JsysClientes.findByCodMunicipio", query = "SELECT j FROM JsysClientes j WHERE j.codMunicipio = :codMunicipio"),
    @NamedQuery(name = "JsysClientes.findByEstado", query = "SELECT j FROM JsysClientes j WHERE j.estado = :estado"),
    @NamedQuery(name = "JsysClientes.findByCep", query = "SELECT j FROM JsysClientes j WHERE j.cep = :cep"),
    @NamedQuery(name = "JsysClientes.findByNumero", query = "SELECT j FROM JsysClientes j WHERE j.numero = :numero"),
    @NamedQuery(name = "JsysClientes.findByPais", query = "SELECT j FROM JsysClientes j WHERE j.pais = :pais"),
    @NamedQuery(name = "JsysClientes.findByCodPais", query = "SELECT j FROM JsysClientes j WHERE j.codPais = :codPais"),
    @NamedQuery(name = "JsysClientes.findByFone", query = "SELECT j FROM JsysClientes j WHERE j.fone = :fone"),
    @NamedQuery(name = "JsysClientes.findByCelular", query = "SELECT j FROM JsysClientes j WHERE j.celular = :celular"),
    @NamedQuery(name = "JsysClientes.findByFax", query = "SELECT j FROM JsysClientes j WHERE j.fax = :fax"),
    @NamedQuery(name = "JsysClientes.findByEmail", query = "SELECT j FROM JsysClientes j WHERE j.email = :email"),
    @NamedQuery(name = "JsysClientes.findByNatural", query = "SELECT j FROM JsysClientes j WHERE j.natural = :natural"),
    @NamedQuery(name = "JsysClientes.findByCnpjCpf", query = "SELECT j FROM JsysClientes j WHERE j.cnpjCpf = :cnpjCpf"),
    @NamedQuery(name = "JsysClientes.findByIeRg", query = "SELECT j FROM JsysClientes j WHERE j.ieRg = :ieRg"),
    @NamedQuery(name = "JsysClientes.findByIeRgData", query = "SELECT j FROM JsysClientes j WHERE j.ieRgData = :ieRgData"),
    @NamedQuery(name = "JsysClientes.findByCtps", query = "SELECT j FROM JsysClientes j WHERE j.ctps = :ctps"),
    @NamedQuery(name = "JsysClientes.findByCtpsSerie", query = "SELECT j FROM JsysClientes j WHERE j.ctpsSerie = :ctpsSerie"),
    @NamedQuery(name = "JsysClientes.findByCtpsdata", query = "SELECT j FROM JsysClientes j WHERE j.ctpsdata = :ctpsdata"),
    @NamedQuery(name = "JsysClientes.findByReservista", query = "SELECT j FROM JsysClientes j WHERE j.reservista = :reservista"),
    @NamedQuery(name = "JsysClientes.findByReservistaCategoria", query = "SELECT j FROM JsysClientes j WHERE j.reservistaCategoria = :reservistaCategoria"),
    @NamedQuery(name = "JsysClientes.findByCnh", query = "SELECT j FROM JsysClientes j WHERE j.cnh = :cnh"),
    @NamedQuery(name = "JsysClientes.findByCnhTipo", query = "SELECT j FROM JsysClientes j WHERE j.cnhTipo = :cnhTipo"),
    @NamedQuery(name = "JsysClientes.findByNacionalidade", query = "SELECT j FROM JsysClientes j WHERE j.nacionalidade = :nacionalidade"),
    @NamedQuery(name = "JsysClientes.findByPis", query = "SELECT j FROM JsysClientes j WHERE j.pis = :pis"),
    @NamedQuery(name = "JsysClientes.findByPisdata", query = "SELECT j FROM JsysClientes j WHERE j.pisdata = :pisdata"),
    @NamedQuery(name = "JsysClientes.findByEstadoCivil", query = "SELECT j FROM JsysClientes j WHERE j.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "JsysClientes.findByConjuge", query = "SELECT j FROM JsysClientes j WHERE j.conjuge = :conjuge"),
    @NamedQuery(name = "JsysClientes.findByFilhos", query = "SELECT j FROM JsysClientes j WHERE j.filhos = :filhos"),
    @NamedQuery(name = "JsysClientes.findByNomesDatas", query = "SELECT j FROM JsysClientes j WHERE j.nomesDatas = :nomesDatas"),
    @NamedQuery(name = "JsysClientes.findBySuframa", query = "SELECT j FROM JsysClientes j WHERE j.suframa = :suframa"),
    @NamedQuery(name = "JsysClientes.findByDataNacimento", query = "SELECT j FROM JsysClientes j WHERE j.dataNacimento = :dataNacimento"),
    @NamedQuery(name = "JsysClientes.findByPai", query = "SELECT j FROM JsysClientes j WHERE j.pai = :pai"),
    @NamedQuery(name = "JsysClientes.findByMae", query = "SELECT j FROM JsysClientes j WHERE j.mae = :mae"),
    @NamedQuery(name = "JsysClientes.findByCargo", query = "SELECT j FROM JsysClientes j WHERE j.cargo = :cargo"),
    @NamedQuery(name = "JsysClientes.findBySalario", query = "SELECT j FROM JsysClientes j WHERE j.salario = :salario"),
    @NamedQuery(name = "JsysClientes.findByFoneconjugue", query = "SELECT j FROM JsysClientes j WHERE j.foneconjugue = :foneconjugue"),
    @NamedQuery(name = "JsysClientes.findByContato", query = "SELECT j FROM JsysClientes j WHERE j.contato = :contato"),
    @NamedQuery(name = "JsysClientes.findByDataInclusao", query = "SELECT j FROM JsysClientes j WHERE j.dataInclusao = :dataInclusao"),
    @NamedQuery(name = "JsysClientes.findByUsuarioInclusao", query = "SELECT j FROM JsysClientes j WHERE j.usuarioInclusao = :usuarioInclusao"),
    @NamedQuery(name = "JsysClientes.findByDataalteracao", query = "SELECT j FROM JsysClientes j WHERE j.dataalteracao = :dataalteracao"),
    @NamedQuery(name = "JsysClientes.findByUsuarioalteracao", query = "SELECT j FROM JsysClientes j WHERE j.usuarioalteracao = :usuarioalteracao"),
    @NamedQuery(name = "JsysClientes.findByInativo", query = "SELECT j FROM JsysClientes j WHERE j.inativo = :inativo"),
    @NamedQuery(name = "JsysClientes.findByComissaoVista", query = "SELECT j FROM JsysClientes j WHERE j.comissaoVista = :comissaoVista"),
    @NamedQuery(name = "JsysClientes.findByComissaoPrazo", query = "SELECT j FROM JsysClientes j WHERE j.comissaoPrazo = :comissaoPrazo"),
    @NamedQuery(name = "JsysClientes.findByAdmissao", query = "SELECT j FROM JsysClientes j WHERE j.admissao = :admissao"),
    @NamedQuery(name = "JsysClientes.findByDemissao", query = "SELECT j FROM JsysClientes j WHERE j.demissao = :demissao"),
    @NamedQuery(name = "JsysClientes.findByCliente", query = "SELECT j FROM JsysClientes j WHERE j.cliente = :cliente"),
    @NamedQuery(name = "JsysClientes.findByFornecedor", query = "SELECT j FROM JsysClientes j WHERE j.fornecedor = :fornecedor"),
    @NamedQuery(name = "JsysClientes.findByColaborador", query = "SELECT j FROM JsysClientes j WHERE j.colaborador = :colaborador"),
    @NamedQuery(name = "JsysClientes.findByMalaDireta", query = "SELECT j FROM JsysClientes j WHERE j.malaDireta = :malaDireta"),
    @NamedQuery(name = "JsysClientes.findByVip", query = "SELECT j FROM JsysClientes j WHERE j.vip = :vip")})
public class JsysClientes implements Serializable {
    @Basic(optional = false)
    @Column(name = "horasExtras")
    private Boolean horasExtras;
    @Basic(optional = false)
    @Column(name = "recebimentoValeTrasnporte")
    private Boolean recebimentoValeTrasnporte;
    @Column(name = "descontosOutros")
    private Boolean descontosOutros;
    @JoinTable(name = "jsysClientesIds", joinColumns = {
        @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")}, inverseJoinColumns = {
        @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")})
    @ManyToMany
    private Collection<JsysClientes> jsysClientesCollection;
    @ManyToMany(mappedBy = "jsysClientesCollection")
    private Collection<JsysClientes> jsysClientesCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente")
    private Collection<JsysReceber> jsysReceberCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCliente")
    private Integer idCliente;

    @Basic(optional = false)
    @Column(name = "nomeCorentista")
    private String nomeCorentista;
    @Basic(optional = false)
    @Column(name = "fantasia")
    private String fantasia;
    @Basic(optional = false)
    @Column(name = "tipo")
    private boolean tipo;
    @Column(name = "sexo")
    private String sexo;
    @Basic(optional = false)
    @Column(name = "endereco")
    private String endereco;
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
    private String codMunicipio;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @Column(name = "cep")
    private String cep;
    @Basic(optional = false)
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
    @Column(name = "pais")
    private String pais;
    @Basic(optional = false)
    @Column(name = "codPais")
    private String codPais;
    @Column(name = "fone")
    private String fone;
    @Column(name = "celular")
    private String celular;
    @Column(name = "fax")
    private String fax;
    @Column(name = "email")
    private String email;
    @Column(name = "natural")
    private String natural;

    @Column(name = "cnpjCpf")
    private String cnpjCpf;

    @Basic(optional = false)
    @Column(name = "ieRg")
    private String ieRg;
    @Column(name = "ieRgData")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ieRgData;
    @Column(name = "ctps")
    private String ctps;
    @Column(name = "ctpsSerie")
    private String ctpsSerie;
    @Column(name = "ctpsdata")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ctpsdata;
    @Column(name = "reservista")
    private String reservista;
    @Column(name = "reservistaCategoria")
    private String reservistaCategoria;
    @Column(name = "cnh")
    private String cnh;
    @Column(name = "cnhTipo")
    private String cnhTipo;
    @Column(name = "nacionalidade")
    private String nacionalidade;
    @Column(name = "pis")
    private String pis;
    @Column(name = "pisdata")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pisdata;
    @Column(name = "estadoCivil")
    private String estadoCivil;
    @Column(name = "conjuge")
    private String conjuge;
    @Basic(optional = false)
    @Column(name = "filhos")
    private boolean filhos;
    @Column(name = "nomesDatas")
    private String nomesDatas;
    @Column(name = "suframa")
    private String suframa;
    @Column(name = "dataNacimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNacimento;
    @Column(name = "pai")
    private String pai;
    @Column(name = "mae")
    private String mae;
    @Column(name = "cargo")
    private String cargo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salario")
    private BigDecimal salario;
    @Column(name = "foneconjugue")
    private String foneconjugue;
    @Column(name = "contato")
    private String contato;
    @Basic(optional = false)
    @Column(name = "dataInclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;
    @Basic(optional = false)
    @Column(name = "usuarioInclusao")
    private String usuarioInclusao;
    @Column(name = "dataalteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataalteracao;
    @Column(name = "usuarioalteracao")
    private String usuarioalteracao;
    @Lob
    @Column(name = "obs")
    private String obs;
//    @Column(name = "carteiraTrabalho")
//    private String carteiraTrabalho;
    @Basic(optional = false)
    @Column(name = "inativo")
    private boolean inativo;
    @Column(name = "comissaoVista")
    private BigDecimal comissaoVista;
    @Column(name = "comissaoPrazo")
    private BigDecimal comissaoPrazo;
    @Column(name = "admissao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date admissao;
    @Column(name = "demissao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date demissao;
    @Basic(optional = false)
    @Column(name = "cliente")
    private boolean cliente;
    @Basic(optional = false)
    @Column(name = "fornecedor")
    private boolean fornecedor;
    @Basic(optional = false)
    @Column(name = "colaborador")
    private boolean colaborador;
    @Basic(optional = false)
    @Column(name = "filial")
    private boolean filial;
    @Basic(optional = false)
    @Column(name = "malaDireta")
    private boolean malaDireta;
    @Basic(optional = false)
    @Column(name = "vip")
    private boolean vip;
    @Basic(optional = false)
    @Column(name = "idLoja")
    private String idLoja;

    @Basic(optional = false)
    @Column(name = "tituloEleitor")
    private String tituloEleitor;
    @Basic(optional = false)
    @Column(name = "zona")
    private String zona;
    @Basic(optional = false)
    @Column(name = "secao")
    private String secao;
    @Basic(optional = false)
    @Column(name = "escolaridade")
    private String escolaridade;
    @Basic(optional = false)
    @Column(name = "cutis")
    private String cutis;

    @Column(name = "cargaHoraria")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cargaHoraria;

    @Basic(optional = false)
    @Column(name = "reposoSemanal")
    private String reposoSemanal;


    @Column(name = "exameAdmissional")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exameAdmissional;

    @Column(name = "avisoDataInicial")
    @Temporal(TemporalType.TIMESTAMP)
    private Date avisoDataInicial;

    @Column(name = "avisoDataFinal")
    @Temporal(TemporalType.TIMESTAMP)
    private Date avisoDataFinal;

    public JsysClientes() {
        this.nomeCorentista = "";
        this.fantasia = "";
        this.tipo = false;
        this.endereco = "";
        this.bairro = "";
        this.cidade = "";
        this.codMunicipio = "";
        this.estado = "RO";
        this.cep = "";
        this.numero = "";
        this.pais = "BRASIL";
        this.codPais = "1058";
        this.inativo = false;
        this.cliente = false;
        this.fornecedor = false;
        this.colaborador = false;
        this.filial = false;
        this.vip = false;
        this.salario = BigDecimal.ZERO;
        this.comissaoVista = BigDecimal.ZERO;
        this.comissaoPrazo = BigDecimal.ZERO;
        this.ieRg = "";
        this.recebimentoValeTrasnporte = false;
    }
    

//    public JsysClientes(Integer idCliente) {
//        this.idCliente = idCliente;
//    }

//    public JsysClientes(Integer idCliente, String nomeCorentista, String fantasia, boolean tipo, String endereco, String bairro, String cidade, String codMunicipio, String estado, String cep, String numero, String pais, String codPais, String ieRg, boolean filhos, Date dataInclusao, String usuarioInclusao, boolean inativo, boolean cliente, boolean fornecedor, boolean colaborador, boolean malaDireta, boolean vip) {
//        this.idCliente = idCliente;
//        this.nomeCorentista = nomeCorentista;
//        this.fantasia = fantasia;
//        this.tipo = tipo;
//        this.endereco = endereco;
//        this.bairro = bairro;
//        this.cidade = cidade;
//        this.codMunicipio = codMunicipio;
//        this.estado = estado;
//        this.cep = cep;
//        this.numero = numero;
//        this.pais = pais;
//        this.codPais = codPais;
//        this.ieRg = ieRg;
//        this.filhos = filhos;
//        this.dataInclusao = dataInclusao;
//        this.usuarioInclusao = usuarioInclusao;
//        this.inativo = inativo;
//        this.cliente = cliente;
//        this.fornecedor = fornecedor;
//        this.colaborador = colaborador;
//        this.malaDireta = malaDireta;
//        this.vip = vip;
//    }
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCorentista() {
        return nomeCorentista;
    }

    public void setNomeCorentista(String nomeCorentista) {
        this.nomeCorentista = nomeCorentista;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public boolean getTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCodMunicipio() {
        return codMunicipio;
    }

    public void setCodMunicipio(String codMunicipio) {
        this.codMunicipio = codMunicipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodPais() {
        return codPais;
    }

    public void setCodPais(String codPais) {
        this.codPais = codPais;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNatural() {
        return natural;
    }

    public void setNatural(String natural) {
        this.natural = natural;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public String getIeRg() {
        return ieRg;
    }

    public void setIeRg(String ieRg) {
        this.ieRg = ieRg;
    }

    public Date getIeRgData() {
        return ieRgData;
    }

    public void setIeRgData(Date ieRgData) {
        this.ieRgData = ieRgData;
    }

    public String getCtps() {
        return ctps;
    }

    public void setCtps(String ctps) {
        this.ctps = ctps;
    }

    public String getCtpsSerie() {
        return ctpsSerie;
    }

    public void setCtpsSerie(String ctpsSerie) {
        this.ctpsSerie = ctpsSerie;
    }

    public Date getCtpsdata() {
        return ctpsdata;
    }

    public void setCtpsdata(Date ctpsdata) {
        this.ctpsdata = ctpsdata;
    }

    public String getReservista() {
        return reservista;
    }

    public void setReservista(String reservista) {
        this.reservista = reservista;
    }

    public String getReservistaCategoria() {
        return reservistaCategoria;
    }

    public void setReservistaCategoria(String reservistaCategoria) {
        this.reservistaCategoria = reservistaCategoria;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getCnhTipo() {
        return cnhTipo;
    }

    public void setCnhTipo(String cnhTipo) {
        this.cnhTipo = cnhTipo;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }

    public Date getPisdata() {
        return pisdata;
    }

    public void setPisdata(Date pisdata) {
        this.pisdata = pisdata;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getConjuge() {
        return conjuge;
    }

    public void setConjuge(String conjuge) {
        this.conjuge = conjuge;
    }

    public boolean getFilhos() {
        return filhos;
    }

    public void setFilhos(boolean filhos) {
        this.filhos = filhos;
    }

    public String getNomesDatas() {
        return nomesDatas;
    }

    public void setNomesDatas(String nomesDatas) {
        this.nomesDatas = nomesDatas;
    }

    public String getSuframa() {
        return suframa;
    }

    public void setSuframa(String suframa) {
        this.suframa = suframa;
    }

    public Date getDataNacimento() {
        return dataNacimento;
    }

    public void setDataNacimento(Date dataNacimento) {
        this.dataNacimento = dataNacimento;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFoneconjugue() {
        return foneconjugue;
    }

    public void setFoneconjugue(String foneconjugue) {
        this.foneconjugue = foneconjugue;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public String getUsuarioInclusao() {
        return usuarioInclusao;
    }

    public void setUsuarioInclusao(String usuarioInclusao) {
        this.usuarioInclusao = usuarioInclusao;
    }

    public Date getDataalteracao() {
        return dataalteracao;
    }

    public void setDataalteracao(Date dataalteracao) {
        this.dataalteracao = dataalteracao;
    }

    public String getUsuarioalteracao() {
        return usuarioalteracao;
    }

    public void setUsuarioalteracao(String usuarioalteracao) {
        this.usuarioalteracao = usuarioalteracao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

//    public String getCarteiraTrabalho() {
//        return carteiraTrabalho;
//    }
//
//    public void setCarteiraTrabalho(String carteiraTrabalho) {
//        this.carteiraTrabalho = carteiraTrabalho;
//    }
    public boolean getInativo() {
        return inativo;
    }

    public void setInativo(boolean inativo) {
        this.inativo = inativo;
    }

    public BigDecimal getComissaoVista() {
        return comissaoVista;
    }

    public void setComissaoVista(BigDecimal comissaoVista) {
        this.comissaoVista = comissaoVista;
    }

    public BigDecimal getComissaoPrazo() {
        return comissaoPrazo;
    }

    public void setComissaoPrazo(BigDecimal comissaoPrazo) {
        this.comissaoPrazo = comissaoPrazo;
    }

    public Date getAdmissao() {
        return admissao;
    }

    public void setAdmissao(Date admissao) {
        this.admissao = admissao;
    }

    public Date getDemissao() {
        return demissao;
    }

    public void setDemissao(Date demissao) {
        this.demissao = demissao;
    }

    public boolean getCliente() {
        return cliente;
    }

    public void setCliente(boolean cliente) {
        this.cliente = cliente;
    }

    public boolean getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(boolean fornecedor) {
        this.fornecedor = fornecedor;
    }

    public boolean getColaborador() {
        return colaborador;
    }

    public void setColaborador(boolean colaborador) {
        this.colaborador = colaborador;
    }

    public boolean getFilial() {
        return filial;
    }

    public void setFilial(boolean filial) {
        this.filial = filial;
    }

    public boolean getMalaDireta() {
        return malaDireta;
    }

    public void setMalaDireta(boolean malaDireta) {
        this.malaDireta = malaDireta;
    }

    public boolean getVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public String getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(String idLoja) {
        this.idLoja = idLoja;
    }

    public String getTituloEleitor() {
        return tituloEleitor;
    }

    public void setTituloEleitor(String tituloEleitor) {
        this.tituloEleitor = tituloEleitor;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getSecao() {
        return secao;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getCutis() {
        return cutis;
    }

    public void setCutis(String cutis) {
        this.cutis = cutis;
    }

    public Date getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Date cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getReposoSemanal() {
        return reposoSemanal;
    }

    public void setReposoSemanal(String reposoSemanal) {
        this.reposoSemanal = reposoSemanal;
    }


    public Date getExameAdmissional() {
        return exameAdmissional;
    }

    public void setExameAdmissional(Date exameAdmissional) {
        this.exameAdmissional = exameAdmissional;
    }

    public Date getAvisoDataInicial() {
        return avisoDataInicial;
    }

    public void setAvisoDataInicial(Date avisoDataInicial) {
        this.avisoDataInicial = avisoDataInicial;
    }

    public Date getAvisoDataFinal() {
        return avisoDataFinal;
    }

    public void setAvisoDataFinal(Date avisoDataFinal) {
        this.avisoDataFinal = avisoDataFinal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysClientes)) {
            return false;
        }
        JsysClientes other = (JsysClientes) object;
        return (this.idCliente != null || other.idCliente == null) && (this.idCliente == null || this.idCliente.equals(other.idCliente));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysClientes[ idCliente=" + idCliente + " ]";
    }

    public Boolean getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(Boolean horasExtras) {
        this.horasExtras = horasExtras;
    }

    public Boolean getRecebimentoValeTrasnporte() {
        return recebimentoValeTrasnporte;
    }

    public void setRecebimentoValeTrasnporte(Boolean recebimentoValeTrasnporte) {
        this.recebimentoValeTrasnporte = recebimentoValeTrasnporte;
    }

    public Boolean getDescontosOutros() {
        return descontosOutros;
    }

    public void setDescontosOutros(Boolean descontosOutros) {
        this.descontosOutros = descontosOutros;
    }

    @XmlTransient
    public Collection<JsysClientes> getJsysClientesCollection() {
        return jsysClientesCollection;
    }

    public void setJsysClientesCollection(Collection<JsysClientes> jsysClientesCollection) {
        this.jsysClientesCollection = jsysClientesCollection;
    }

    @XmlTransient
    public Collection<JsysClientes> getJsysClientesCollection1() {
        return jsysClientesCollection1;
    }

    public void setJsysClientesCollection1(Collection<JsysClientes> jsysClientesCollection1) {
        this.jsysClientesCollection1 = jsysClientesCollection1;
    }

    @XmlTransient
    public Collection<JsysReceber> getJsysReceberCollection() {
        return jsysReceberCollection;
    }

    public void setJsysReceberCollection(Collection<JsysReceber> jsysReceberCollection) {
        this.jsysReceberCollection = jsysReceberCollection;
    }

}
