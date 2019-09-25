/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.bean;

import br.JavaApplicationJsys;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "jsysCompras")
@NamedQueries({
    @NamedQuery(name = "JsysCompras.findAll", query = "SELECT j FROM JsysCompras j"),
    @NamedQuery(name = "JsysCompras.findByIdCompra", query = "SELECT j FROM JsysCompras j WHERE j.idCompra = :idCompra"),
    @NamedQuery(name = "JsysCompras.findByData", query = "SELECT j FROM JsysCompras j WHERE j.data = :data"),
    @NamedQuery(name = "JsysCompras.findByIdCorentista", query = "SELECT j FROM JsysCompras j WHERE j.idCorentista = :idCorentista"),
    
    @NamedQuery(name = "JsysCompras.findByIdCorentistaAndConfirmado", query = "SELECT j FROM JsysCompras j WHERE j.idCorentista.idCliente = :idCorentista AND j.confirmado = :confirmado AND j.data BETWEEN :dataInicial AND :dataFinal"),
    
    @NamedQuery(name = "JsysCompras.findByNNFe", query = "SELECT j FROM JsysCompras j WHERE j.nNFe = :nNFe"),
    @NamedQuery(name = "JsysCompras.findByModNFe", query = "SELECT j FROM JsysCompras j WHERE j.modNFe = :modNFe"),
    @NamedQuery(name = "JsysCompras.findBySerieNFe", query = "SELECT j FROM JsysCompras j WHERE j.serieNFe = :serieNFe"),
    @NamedQuery(name = "JsysCompras.findByCfop", query = "SELECT j FROM JsysCompras j WHERE j.cfop = :cfop"),
    @NamedQuery(name = "JsysCompras.findByCfopSub", query = "SELECT j FROM JsysCompras j WHERE j.cfopSub = :cfopSub"),
    @NamedQuery(name = "JsysCompras.findByDataChegada", query = "SELECT j FROM JsysCompras j WHERE j.dataChegada = :dataChegada"),
    @NamedQuery(name = "JsysCompras.findByValorBC", query = "SELECT j FROM JsysCompras j WHERE j.valorBC = :valorBC"),
    @NamedQuery(name = "JsysCompras.findByValorICMS", query = "SELECT j FROM JsysCompras j WHERE j.valorICMS = :valorICMS"),
    @NamedQuery(name = "JsysCompras.findByValorBCST", query = "SELECT j FROM JsysCompras j WHERE j.valorBCST = :valorBCST"),
    @NamedQuery(name = "JsysCompras.findByValorST", query = "SELECT j FROM JsysCompras j WHERE j.valorST = :valorST"),
    @NamedQuery(name = "JsysCompras.findByValorProd", query = "SELECT j FROM JsysCompras j WHERE j.valorProd = :valorProd"),
    @NamedQuery(name = "JsysCompras.findByValorFrete", query = "SELECT j FROM JsysCompras j WHERE j.valorFrete = :valorFrete"),
    @NamedQuery(name = "JsysCompras.findByValorSeg", query = "SELECT j FROM JsysCompras j WHERE j.valorSeg = :valorSeg"),
    @NamedQuery(name = "JsysCompras.findByValorDesc", query = "SELECT j FROM JsysCompras j WHERE j.valorDesc = :valorDesc"),
    @NamedQuery(name = "JsysCompras.findByValorII", query = "SELECT j FROM JsysCompras j WHERE j.valorII = :valorII"),
    @NamedQuery(name = "JsysCompras.findByValorIPI", query = "SELECT j FROM JsysCompras j WHERE j.valorIPI = :valorIPI"),
    @NamedQuery(name = "JsysCompras.findByValorPIS", query = "SELECT j FROM JsysCompras j WHERE j.valorPIS = :valorPIS"),
    @NamedQuery(name = "JsysCompras.findByValorCOFINS", query = "SELECT j FROM JsysCompras j WHERE j.valorCOFINS = :valorCOFINS"),
    @NamedQuery(name = "JsysCompras.findByValorOutro", query = "SELECT j FROM JsysCompras j WHERE j.valorOutro = :valorOutro"),
    @NamedQuery(name = "JsysCompras.findByValorNF", query = "SELECT j FROM JsysCompras j WHERE j.valorNF = :valorNF"),
    @NamedQuery(name = "JsysCompras.findByValorTotTrib", query = "SELECT j FROM JsysCompras j WHERE j.valorTotTrib = :valorTotTrib"),
    
    @NamedQuery(name = "JsysCompras.findByConfirmado", query = "SELECT j FROM JsysCompras j WHERE j.confirmado = :confirmado AND j.data BETWEEN :dataInicial AND :dataFinal"),
    
    @NamedQuery(name = "JsysCompras.findByCancelado", query = "SELECT j FROM JsysCompras j WHERE j.cancelado = :cancelado"),
    @NamedQuery(name = "JsysCompras.findByDataCancelado", query = "SELECT j FROM JsysCompras j WHERE j.dataCancelado = :dataCancelado"),
    @NamedQuery(name = "JsysCompras.findByDataInclusao", query = "SELECT j FROM JsysCompras j WHERE j.dataInclusao = :dataInclusao"),
    @NamedQuery(name = "JsysCompras.findByUsuarioInclusao", query = "SELECT j FROM JsysCompras j WHERE j.usuarioInclusao = :usuarioInclusao"),
    @NamedQuery(name = "JsysCompras.findByDataAlteracao", query = "SELECT j FROM JsysCompras j WHERE j.dataAlteracao = :dataAlteracao"),
    @NamedQuery(name = "JsysCompras.findByUsuarioAlteracao", query = "SELECT j FROM JsysCompras j WHERE j.usuarioAlteracao = :usuarioAlteracao")})
public class JsysCompras implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCompra")
    private Integer idCompra;
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @ManyToOne
    @JoinColumn(name = "idCorentista", referencedColumnName = "idCliente")
    private JsysClientes idCorentista;
    @Basic(optional = false)
    @Column(name = "nNFe")
    private int nNFe;
    @Basic(optional = false)
    @Column(name = "modNFe")
    private String modNFe;
    @Basic(optional = false)
    @Column(name = "serieNFe")
    private String serieNFe;
    @Basic(optional = false)
    @Column(name = "cfop")
    private String cfop;
    @Basic(optional = false)
    @Column(name = "cfopSub")
    private String cfopSub;
    @Column(name = "dataChegada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataChegada;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valorBC")
    private BigDecimal valorBC;
    @Basic(optional = false)
    @Column(name = "valorICMS")
    private BigDecimal valorICMS;
    @Basic(optional = false)
    @Column(name = "valorBCST")
    private BigDecimal valorBCST;
    @Basic(optional = false)
    @Column(name = "valorST")
    private BigDecimal valorST;
    @Basic(optional = false)
    @Column(name = "valorProd")
    private BigDecimal valorProd;
    @Basic(optional = false)
    @Column(name = "valorFrete")
    private BigDecimal valorFrete;
    @Basic(optional = false)
    @Column(name = "valorSeg")
    private BigDecimal valorSeg;
    @Basic(optional = false)
    @Column(name = "valorDesc")
    private BigDecimal valorDesc;
    @Basic(optional = false)
    @Column(name = "valorII")
    private BigDecimal valorII;
    @Basic(optional = false)
    @Column(name = "valorIPI")
    private BigDecimal valorIPI;
    @Basic(optional = false)
    @Column(name = "valorPIS")
    private BigDecimal valorPIS;
    @Basic(optional = false)
    @Column(name = "valorCOFINS")
    private BigDecimal valorCOFINS;
    @Basic(optional = false)
    @Column(name = "valorOutro")
    private BigDecimal valorOutro;
    @Basic(optional = false)
    @Column(name = "valorNF")
    private BigDecimal valorNF;
    @Basic(optional = false)
    @Column(name = "valorTotTrib")
    private BigDecimal valorTotTrib;
    @Lob
    @Column(name = "obs")
    private String obs;
    @Basic(optional = false)
    @Column(name = "confirmado")
    private boolean confirmado;
    @Basic(optional = false)
    @Column(name = "cancelado")
    private boolean cancelado;
    @Column(name = "dataCancelado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCancelado;
    @Basic(optional = false)
    @Column(name = "dataInclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;
    @Basic(optional = false)
    @Column(name = "usuarioInclusao")
    private String usuarioInclusao;
    @Column(name = "dataAlteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @Column(name = "usuarioAlteracao")
    private String usuarioAlteracao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jsysCompras")
    private List<JsysComprasItens> jsysComprasItensList;
    @ManyToOne
    @JoinColumn(name = "idloja", referencedColumnName = "idloja")
    private JsysLojas idloja;

    @Basic(optional = false)
    @Column(name = "chaveAcesso")
    private String chaveAcesso;

    @Basic(optional = false)
    @Column(name = "xml")
    private String xml;

    public JsysCompras() {
    }

    public JsysCompras(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public JsysCompras(Integer idCompra, JsysClientes idCorentista, int nNFe, String modNFe, String serieNFe, String cfop, String cfopSub, BigDecimal valorBC, BigDecimal valorICMS, BigDecimal valorBCST, BigDecimal valorST, BigDecimal valorProd, BigDecimal valorFrete, BigDecimal valorSeg, BigDecimal valorDesc, BigDecimal valorII, BigDecimal valorIPI, BigDecimal valorPIS, BigDecimal valorCOFINS, BigDecimal valorOutro, BigDecimal valorNF, BigDecimal valorTotTrib, boolean confirmado, boolean cancelado, Date dataInclusao, String usuarioInclusao) {
        this.idCompra = idCompra;
        this.idCorentista = idCorentista;
        this.nNFe = nNFe;
        this.modNFe = modNFe;
        this.serieNFe = serieNFe;
        this.cfop = cfop;
        this.cfopSub = cfopSub;
        this.valorBC = valorBC;
        this.valorICMS = valorICMS;
        this.valorBCST = valorBCST;
        this.valorST = valorST;
        this.valorProd = valorProd;
        this.valorFrete = valorFrete;
        this.valorSeg = valorSeg;
        this.valorDesc = valorDesc;
        this.valorII = valorII;
        this.valorIPI = valorIPI;
        this.valorPIS = valorPIS;
        this.valorCOFINS = valorCOFINS;
        this.valorOutro = valorOutro;
        this.valorNF = valorNF;
        this.valorTotTrib = valorTotTrib;
        this.confirmado = confirmado;
        this.cancelado = cancelado;
        this.dataInclusao = dataInclusao;
        this.usuarioInclusao = usuarioInclusao;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        Integer oldIdCompra = this.idCompra;
        this.idCompra = idCompra;
        changeSupport.firePropertyChange("idCompra", oldIdCompra, idCompra);
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        Date oldData = this.data;
        this.data = data;
        changeSupport.firePropertyChange("data", oldData, data);
    }

    public JsysClientes getIdCorentista() {
        return idCorentista;
    }

    public void setIdCorentista(JsysClientes idCorentista) {
        JsysClientes oldIdCorentista = this.idCorentista;
        this.idCorentista = idCorentista;
        changeSupport.firePropertyChange("idCorentista", oldIdCorentista, idCorentista);
    }

    public int getnNFe() {
        return nNFe;
    }

    public void setnNFe(int nNFe) {
        int oldNNFe = this.nNFe;
        this.nNFe = nNFe;
        changeSupport.firePropertyChange("NNFe", oldNNFe, nNFe);
    }

    public String getModNFe() {
        return modNFe;
    }

    public void setModNFe(String modNFe) {
        String oldModNFe = this.modNFe;
        this.modNFe = modNFe;
        changeSupport.firePropertyChange("modNFe", oldModNFe, modNFe);
    }

    public String getSerieNFe() {
        return serieNFe;
    }

    public void setSerieNFe(String serieNFe) {
        String oldSerieNFe = this.serieNFe;
        this.serieNFe = serieNFe;
        changeSupport.firePropertyChange("serieNFe", oldSerieNFe, serieNFe);
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        String oldCfop = this.cfop;
        this.cfop = cfop;
        changeSupport.firePropertyChange("cfop", oldCfop, cfop);
    }

    public String getCfopSub() {
        return cfopSub;
    }

    public void setCfopSub(String cfopSub) {
        String oldCfopSub = this.cfopSub;
        this.cfopSub = cfopSub;
        changeSupport.firePropertyChange("cfopSub", oldCfopSub, cfopSub);
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        Date oldDataChegada = this.dataChegada;
        this.dataChegada = dataChegada;
        changeSupport.firePropertyChange("dataChegada", oldDataChegada, dataChegada);
    }

    public BigDecimal getValorBC() {
        return valorBC.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setValorBC(BigDecimal valorBC) {
        BigDecimal oldValorBC = this.valorBC;
        this.valorBC = valorBC;
        changeSupport.firePropertyChange("valorBC", oldValorBC, valorBC);
    }

    public BigDecimal getValorICMS() {
        return valorICMS.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setValorICMS(BigDecimal valorICMS) {
        BigDecimal oldValorICMS = this.valorICMS;
        this.valorICMS = valorICMS;
        changeSupport.firePropertyChange("valorICMS", oldValorICMS, valorICMS);
    }

    public BigDecimal getValorBCST() {
        return valorBCST.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setValorBCST(BigDecimal valorBCST) {
        BigDecimal oldValorBCST = this.valorBCST;
        this.valorBCST = valorBCST;
        changeSupport.firePropertyChange("valorBCST", oldValorBCST, valorBCST);
    }

    public BigDecimal getValorST() {
        return valorST.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setValorST(BigDecimal valorST) {
        BigDecimal oldValorST = this.valorST;
        this.valorST = valorST;
        changeSupport.firePropertyChange("valorST", oldValorST, valorST);
    }

    public BigDecimal getValorProd() {
        return valorProd.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setValorProd(BigDecimal valorProd) {
        BigDecimal oldValorProd = this.valorProd;
        this.valorProd = valorProd;
        changeSupport.firePropertyChange("valorProd", oldValorProd, valorProd);
    }

    public BigDecimal getValorFrete() {
        return valorFrete.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setValorFrete(BigDecimal valorFrete) {
        BigDecimal oldValorFrete = this.valorFrete;
        this.valorFrete = valorFrete;
        changeSupport.firePropertyChange("valorFrete", oldValorFrete, valorFrete);
    }

    public BigDecimal getValorSeg() {
        return valorSeg.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setValorSeg(BigDecimal valorSeg) {
        BigDecimal oldValorSeg = this.valorSeg;
        this.valorSeg = valorSeg;
        changeSupport.firePropertyChange("valorSeg", oldValorSeg, valorSeg);
    }

    public BigDecimal getValorDesc() {
        return valorDesc.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setValorDesc(BigDecimal valorDesc) {
        BigDecimal oldValorDesc = this.valorDesc;
        this.valorDesc = valorDesc;
        changeSupport.firePropertyChange("valorDesc", oldValorDesc, valorDesc);
    }

    public BigDecimal getValorII() {
        return valorII.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setValorII(BigDecimal valorII) {
        BigDecimal oldValorII = this.valorII;
        this.valorII = valorII;
        changeSupport.firePropertyChange("valorII", oldValorII, valorII);
    }

    public BigDecimal getValorIPI() {
        return valorIPI.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setValorIPI(BigDecimal valorIPI) {
        BigDecimal oldValorIPI = this.valorIPI;
        this.valorIPI = valorIPI;
        changeSupport.firePropertyChange("valorIPI", oldValorIPI, valorIPI);
    }

    public BigDecimal getValorPIS() {
        return valorPIS.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setValorPIS(BigDecimal valorPIS) {
        BigDecimal oldValorPIS = this.valorPIS;
        this.valorPIS = valorPIS;
        changeSupport.firePropertyChange("valorPIS", oldValorPIS, valorPIS);
    }

    public BigDecimal getValorCOFINS() {
        return valorCOFINS.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setValorCOFINS(BigDecimal valorCOFINS) {
        BigDecimal oldValorCOFINS = this.valorCOFINS;
        this.valorCOFINS = valorCOFINS;
        changeSupport.firePropertyChange("valorCOFINS", oldValorCOFINS, valorCOFINS);
    }

    public BigDecimal getValorOutro() {
        return valorOutro.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setValorOutro(BigDecimal valorOutro) {
        BigDecimal oldValorOutro = this.valorOutro;
        this.valorOutro = valorOutro;
        changeSupport.firePropertyChange("valorOutro", oldValorOutro, valorOutro);
    }

    public BigDecimal getValorNF() {
        return valorNF.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setValorNF(BigDecimal valorNF) {
        BigDecimal oldValorNF = this.valorNF;
        this.valorNF = valorNF;
        changeSupport.firePropertyChange("valorNF", oldValorNF, valorNF);
    }

    public BigDecimal getValorTotTrib() {
        return valorTotTrib.setScale(JavaApplicationJsys.CASA_DECIMAL_VALOR_DEFALT, RoundingMode.HALF_UP);
    }

    public void setValorTotTrib(BigDecimal valorTotTrib) {
        BigDecimal oldValorTotTrib = this.valorTotTrib;
        this.valorTotTrib = valorTotTrib;
        changeSupport.firePropertyChange("valorTotTrib", oldValorTotTrib, valorTotTrib);
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        String oldObs = this.obs;
        this.obs = obs;
        changeSupport.firePropertyChange("obs", oldObs, obs);
    }

    public boolean getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        boolean oldConfirmado = this.confirmado;
        this.confirmado = confirmado;
        changeSupport.firePropertyChange("confirmado", oldConfirmado, confirmado);
    }

    public boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        boolean oldCancelado = this.cancelado;
        this.cancelado = cancelado;
        changeSupport.firePropertyChange("cancelado", oldCancelado, cancelado);
    }

    public Date getDataCancelado() {
        return dataCancelado;
    }

    public void setDataCancelado(Date dataCancelado) {
        Date oldDataCancelado = this.dataCancelado;
        this.dataCancelado = dataCancelado;
        changeSupport.firePropertyChange("dataCancelado", oldDataCancelado, dataCancelado);
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        Date oldDataInclusao = this.dataInclusao;
        this.dataInclusao = dataInclusao;
        changeSupport.firePropertyChange("dataInclusao", oldDataInclusao, dataInclusao);
    }

    public String getUsuarioInclusao() {
        return usuarioInclusao;
    }

    public void setUsuarioInclusao(String usuarioInclusao) {
        String oldUsuarioInclusao = this.usuarioInclusao;
        this.usuarioInclusao = usuarioInclusao;
        changeSupport.firePropertyChange("usuarioInclusao", oldUsuarioInclusao, usuarioInclusao);
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        Date oldDataAlteracao = this.dataAlteracao;
        this.dataAlteracao = dataAlteracao;
        changeSupport.firePropertyChange("dataAlteracao", oldDataAlteracao, dataAlteracao);
    }

    public String getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(String usuarioAlteracao) {
        String oldUsuarioAlteracao = this.usuarioAlteracao;
        this.usuarioAlteracao = usuarioAlteracao;
        changeSupport.firePropertyChange("usuarioAlteracao", oldUsuarioAlteracao, usuarioAlteracao);
    }

    public List<JsysComprasItens> getJsysComprasItensList() {
        return jsysComprasItensList;
    }

    public void setJsysComprasItensList(List<JsysComprasItens> jsysComprasItensList) {
        this.jsysComprasItensList = jsysComprasItensList;
    }

    public JsysLojas getIdloja() {
        return idloja;
    }

    public void setIdloja(JsysLojas idloja) {
        JsysLojas oldIdloja = this.idloja;
        this.idloja = idloja;
        changeSupport.firePropertyChange("idloja", oldIdloja, idloja);
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        String oldChaveAcesso = this.chaveAcesso;
        this.chaveAcesso = chaveAcesso;
        changeSupport.firePropertyChange("chaveAcesso", oldChaveAcesso, chaveAcesso);
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        String oldXml = this.xml;
        this.xml = xml;
        changeSupport.firePropertyChange("xml", oldXml, xml);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompra != null ? idCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JsysCompras)) {
            return false;
        }
        JsysCompras other = (JsysCompras) object;
        return (this.idCompra != null || other.idCompra == null) && (this.idCompra == null || this.idCompra.equals(other.idCompra));
    }

    @Override
    public String toString() {
        return "br.sql.bean.JsysCompras[ idCompra=" + idCompra + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public PropertyChangeSupport getChangeSupport() {
        return changeSupport;
    }

    public void setChangeSupport(PropertyChangeSupport changeSupport) {
        this.changeSupport = changeSupport;
    }
}
