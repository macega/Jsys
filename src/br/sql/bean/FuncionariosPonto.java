package br.sql.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "funcionariosPonto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FuncionariosPonto.findByIdFuncionarioData", query = "SELECT f FROM FuncionariosPonto f WHERE (f.idFuncionario = :idFuncionario) AND (f.entrada BETWEEN :inicio AND :fim) ORDER BY f.idFuncionario, f.entrada"),
    @NamedQuery(name = "FuncionariosPonto.findByData", query = "SELECT f FROM FuncionariosPonto f WHERE f.entrada BETWEEN :inicio AND :fim ORDER BY f.idFuncionario, f.entrada"),
    @NamedQuery(name = "FuncionariosPonto.findByIdandEntrada", query = "SELECT f FROM FuncionariosPonto f WHERE f.id = :id and f.entrada >= :entrada ORDER BY f.idFuncionario, f.entrada"),
    @NamedQuery(name = "FuncionariosPonto.findAll", query = "SELECT f FROM FuncionariosPonto f"),
    @NamedQuery(name = "FuncionariosPonto.findById", query = "SELECT f FROM FuncionariosPonto f WHERE f.id = :id"),
    @NamedQuery(name = "FuncionariosPonto.findByCnpjCpf", query = "SELECT f FROM FuncionariosPonto f WHERE f.funcionariosPontoPK.cnpjCpf = :cnpjCpf"),
    @NamedQuery(name = "FuncionariosPonto.findByIdFuncionario", query = "SELECT f FROM FuncionariosPonto f WHERE f.idFuncionario = :idFuncionario"),
    @NamedQuery(name = "FuncionariosPonto.findByEntrada", query = "SELECT f FROM FuncionariosPonto f WHERE f.entrada = :entrada"),
    @NamedQuery(name = "FuncionariosPonto.findByIntervaloInicio", query = "SELECT f FROM FuncionariosPonto f WHERE f.intervaloInicio = :intervaloInicio"),
    @NamedQuery(name = "FuncionariosPonto.findByIntervaloFim", query = "SELECT f FROM FuncionariosPonto f WHERE f.intervaloFim = :intervaloFim"),
    @NamedQuery(name = "FuncionariosPonto.findByIntervalo15Inicio", query = "SELECT f FROM FuncionariosPonto f WHERE f.intervalo15Inicio = :intervalo15Inicio"),
    @NamedQuery(name = "FuncionariosPonto.findByIntervalo15Fim", query = "SELECT f FROM FuncionariosPonto f WHERE f.intervalo15Fim = :intervalo15Fim"),
    @NamedQuery(name = "FuncionariosPonto.findBySaida", query = "SELECT f FROM FuncionariosPonto f WHERE f.saida = :saida")})
public class FuncionariosPonto implements Serializable {

    @Transient
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FuncionariosPontoPK funcionariosPontoPK;
    @Basic(optional = false)
    @Column(name = "id")
    private long id;
    @Basic(optional = false)
    @Column(name = "idFuncionario")
    private int idFuncionario;

    @JoinColumn(name = "idFuncionario", referencedColumnName = "idCliente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private JsysClientes Funcionario;

    @Basic(optional = false)
    @Column(name = "entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entrada;
    @Column(name = "intervaloInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date intervaloInicio;
    @Column(name = "intervaloFim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date intervaloFim;
    @Column(name = "intervalo15Inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date intervalo15Inicio;
    @Column(name = "intervalo15Fim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date intervalo15Fim;
    @Column(name = "Saida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saida;
    @Lob
    @Column(name = "obs")
    private String obs;
    @Column(name = "feriado")
    private Boolean feriado;
    @Column(name = "atestado")
    private Boolean atestado;
    @Column(name = "ferias")
    private Boolean ferias;
    @Column(name = "outros")
    private Boolean outros;
    @Column(name = "falta")
    private Boolean falta;
    @Column(name = "licencaMaternidade")
    private Boolean licencaMaternidade;
    @Column(name = "avisoPrevio")
    private Boolean avisoPrevio;
    @Column(name = "lactante")
    private Boolean lactante;
    @Column(name = "compensacao")
    private Boolean compensacao;
    @Column(name = "verificar")
    private Boolean verificar;
    @Column(name = "meiaFalta")
    private Boolean meiaFalta;

    public FuncionariosPonto() {
    }

    public FuncionariosPonto(FuncionariosPontoPK funcionariosPontoPK) {
        this.funcionariosPontoPK = funcionariosPontoPK;
    }

    public FuncionariosPonto(FuncionariosPontoPK funcionariosPontoPK, long id, int idFuncionario, Date entrada) {
        this.funcionariosPontoPK = funcionariosPontoPK;
        this.id = id;
        this.idFuncionario = idFuncionario;
        this.entrada = entrada;
    }

    public FuncionariosPonto(Date data, String cnpjCpf) {
        this.funcionariosPontoPK = new FuncionariosPontoPK(data, cnpjCpf);
    }

    public FuncionariosPontoPK getFuncionariosPontoPK() {
        return funcionariosPontoPK;
    }

    public void setFuncionariosPontoPK(FuncionariosPontoPK funcionariosPontoPK) {
        this.funcionariosPontoPK = funcionariosPontoPK;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        long oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        int oldIdFuncionario = this.idFuncionario;
        this.idFuncionario = idFuncionario;
        changeSupport.firePropertyChange("idFuncionario", oldIdFuncionario, idFuncionario);
    }

    public JsysClientes getFuncionario() {
        return Funcionario;
    }

    public void setFuncionario(JsysClientes funcionario) {
        JsysClientes oldFuncionario = this.Funcionario;
        this.Funcionario = funcionario;
        changeSupport.firePropertyChange("funcionario", oldFuncionario, funcionario);
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        Date oldEntrada = this.entrada;
        this.entrada = entrada;
        changeSupport.firePropertyChange("entrada", oldEntrada, entrada);
    }

    public Date getIntervaloInicio() {
        return intervaloInicio;
    }

    public void setIntervaloInicio(Date intervaloInicio) {
        Date oldIntervaloInicio = this.intervaloInicio;
        this.intervaloInicio = intervaloInicio;
        changeSupport.firePropertyChange("intervaloInicio", oldIntervaloInicio, intervaloInicio);
    }

    public Date getIntervaloFim() {
        return intervaloFim;
    }

    public void setIntervaloFim(Date intervaloFim) {
        Date oldIntervaloFim = this.intervaloFim;
        this.intervaloFim = intervaloFim;
        changeSupport.firePropertyChange("intervaloFim", oldIntervaloFim, intervaloFim);
    }

    public Date getIntervalo15Inicio() {
        return intervalo15Inicio;
    }

    public void setIntervalo15Inicio(Date intervalo15Inicio) {
        Date oldIntervalo15Inicio = this.intervalo15Inicio;
        this.intervalo15Inicio = intervalo15Inicio;
        changeSupport.firePropertyChange("intervalo15Inicio", oldIntervalo15Inicio, intervalo15Inicio);
    }

    public Date getIntervalo15Fim() {
        return intervalo15Fim;
    }

    public void setIntervalo15Fim(Date intervalo15Fim) {
        Date oldIntervalo15Fim = this.intervalo15Fim;
        this.intervalo15Fim = intervalo15Fim;
        changeSupport.firePropertyChange("intervalo15Fim", oldIntervalo15Fim, intervalo15Fim);
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        Date oldSaida = this.saida;
        this.saida = saida;
        changeSupport.firePropertyChange("saida", oldSaida, saida);
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        String oldObs = this.obs;
        this.obs = obs;
        changeSupport.firePropertyChange("obs", oldObs, obs);
    }

    public Boolean getFeriado() {
        return feriado;
    }

    public void setFeriado(Boolean feriado) {
        Boolean oldFeriado = this.feriado;
        this.feriado = feriado;
        changeSupport.firePropertyChange("feriado", oldFeriado, feriado);
    }

    public Boolean getAtestado() {
        return atestado;
    }

    public void setAtestado(Boolean atestado) {
        Boolean oldAtestado = this.atestado;
        this.atestado = atestado;
        changeSupport.firePropertyChange("atestado", oldAtestado, atestado);
    }

    public Boolean getFerias() {
        return ferias;
    }

    public void setFerias(Boolean ferias) {
        Boolean oldFerias = this.ferias;
        this.ferias = ferias;
        changeSupport.firePropertyChange("ferias", oldFerias, ferias);
    }

    public Boolean getOutros() {
        return outros;
    }

    public void setOutros(Boolean outros) {
        Boolean oldOutros = this.outros;
        this.outros = outros;
        changeSupport.firePropertyChange("outros", oldOutros, outros);
    }

    public Boolean getFalta() {
        return falta;
    }

    public void setFalta(Boolean falta) {
        Boolean oldFalta = this.falta;
        this.falta = falta;
        changeSupport.firePropertyChange("falta", oldFalta, falta);
    }

    public Boolean getLicencaMaternidade() {
        return licencaMaternidade;
    }

    public void setLicencaMaternidade(Boolean licencaMaternidade) {
        Boolean oldLicencaMaternidade = this.licencaMaternidade;
        this.licencaMaternidade = licencaMaternidade;
        changeSupport.firePropertyChange("licencaMaternidade", oldLicencaMaternidade, licencaMaternidade);
    }

    public Boolean getAvisoPrevio() {
        return avisoPrevio;
    }

    public void setAvisoPrevio(Boolean avisoPrevio) {
        Boolean oldAvisoPrevio = this.avisoPrevio;
        this.avisoPrevio = avisoPrevio;
        changeSupport.firePropertyChange("avisoPrevio", oldAvisoPrevio, avisoPrevio);
    }

    public Boolean getLactante() {
        return lactante;
    }

    public void setLactante(Boolean lactante) {
        Boolean oldLactante = this.lactante;
        this.lactante = lactante;
        changeSupport.firePropertyChange("lactante", oldLactante, lactante);
    }

    public Boolean getCompensacao() {
        return compensacao;
    }

    public void setCompensacao(Boolean compensacao) {
        Boolean oldCompensacao = this.compensacao;
        this.compensacao = compensacao;
        changeSupport.firePropertyChange("compensacao", oldCompensacao, compensacao);
    }

    public Boolean getVerificar() {
        return verificar;
    }

    public void setVerificar(Boolean verificar) {
        Boolean oldVerificar = this.verificar;
        this.verificar = verificar;
        changeSupport.firePropertyChange("verificar", oldVerificar, verificar);
    }

    public Boolean getMeiaFalta() {
        return meiaFalta;
    }

    public void setMeiaFalta(Boolean meiaFalta) {
        Boolean oldMeiaFalta = this.meiaFalta;
        this.meiaFalta = meiaFalta;
        changeSupport.firePropertyChange("meiaFalta", oldMeiaFalta, meiaFalta);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (funcionariosPontoPK != null ? funcionariosPontoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FuncionariosPonto)) {
            return false;
        }
        FuncionariosPonto other = (FuncionariosPonto) object;
        return (this.funcionariosPontoPK != null || other.funcionariosPontoPK == null) && (this.funcionariosPontoPK == null || this.funcionariosPontoPK.equals(other.funcionariosPontoPK));
    }

    @Override
    public String toString() {
        return "br.sql.bean.FuncionariosPonto[ funcionariosPontoPK=" + funcionariosPontoPK + " ]";
    }
}
