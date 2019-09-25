package br.sql.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;
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
import javax.persistence.Transient;

/**
 *
 * @author Juliano Alves Medina
 */
@Entity
@Table(name = "UsuariosGrupo")
@NamedQueries({
    @NamedQuery(name = "UsuariosGrupo.findAll", query = "SELECT u FROM UsuariosGrupo u"),
    @NamedQuery(name = "UsuariosGrupo.findByIdGrupo", query = "SELECT u FROM UsuariosGrupo u WHERE u.idGrupo = :idGrupo"),
    @NamedQuery(name = "UsuariosGrupo.findByGrupo", query = "SELECT u FROM UsuariosGrupo u WHERE u.grupo = :grupo")})
public class UsuariosGrupo implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGrupo")
    private Integer idGrupo;
    @Basic(optional = false)
    @Column(name = "Grupo")
    private String grupo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupo")
    private List<Usuarios> usuariosList;
    @Basic(optional = false)
    @Column(name = "Caixa")
    private boolean Caixa;
    @Basic(optional = false)
    @Column(name = "vendas")
    private boolean vendas;
    @Basic(optional = false)
    @Column(name = "Gerencia")
    private boolean Gerencia;
    @Basic(optional = false)
    @Column(name = "Financeiro")
    private boolean Financeiro;
    @Basic(optional = false)
    @Column(name = "Relatorios")
    private boolean Relatorios;
    @Basic(optional = false)
    @Column(name = "Lojas")
    private boolean Lojas;
    @Basic(optional = false)
    @Column(name = "ponto")
    private boolean ponto;
    @Basic(optional = false)
    @Column(name = "MenuCadastros")
    private boolean MenuCadastros;
    @Basic(optional = false)
    @Column(name = "MenuCaixa")
    private boolean MenuCaixa;
    @Basic(optional = false)
    @Column(name = "MenuMovimento")
    private boolean MenuMovimento;
    @Basic(optional = false)
    @Column(name = "MenuPagar")
    private boolean MenuPagar;
    @Basic(optional = false)
    @Column(name = "MenuPonto")
    private boolean MenuPonto;
    @Basic(optional = false)
    @Column(name = "MenuRelatorios")
    private boolean MenuRelatorios;
    @Basic(optional = false)
    @Column(name = "MenuUtilitarios")
    private boolean MenuUtilitarios;
    @Basic(optional = false)
    @Column(name = "MenuFiscal")
    private boolean MenuFiscal;
    @Basic(optional = false)
    @Column(name = "MenuDeposito")
    private boolean MenuDeposito;
    @Basic(optional = false)
    @Column(name = "MenuFinanceiro")
    private boolean MenuFinanceiro;
    @Basic(optional = false)
    @Column(name = "MenuCRM")
    private boolean MenuCRM;
    @Basic(optional = false)
    @Column(name = "MenuAjuda")
    private boolean MenuAjuda;
    @Basic(optional = false)
    @Column(name = "RelVendas")
    private boolean RelVendas;
    @Basic(optional = false)
    @Column(name = "RelCaixa")
    private boolean RelCaixa;
    @Basic(optional = false)
    @Column(name = "RelGerencial")
    private boolean RelGerencial;
    @Basic(optional = false)
    @Column(name = "fechaAuto")
    private boolean fechaAuto;
    @Basic(optional = false)
    @Column(name = "cancelarDia")
    private boolean cancelarDia;
    @Basic(optional = false)
    @Column(name = "administracao")
    private boolean administracao;
    @Basic(optional = false)
    @Column(name = "movAjusteEstoque")
    private boolean movAjusteEstoque;
    
    public UsuariosGrupo() {
    }

    public UsuariosGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public UsuariosGrupo(Integer idGrupo, String grupo, boolean caixa, boolean Vendas, boolean gerencia, boolean financeiro, boolean relatorios, boolean lojas, boolean Ponto, boolean menuCadastros, boolean menuCaixa, boolean menuMovimento, boolean menuPagar, boolean menuPonto, boolean menuRelatroios, boolean menuUtilitarios) {
        this.idGrupo = idGrupo;
        this.grupo = grupo;
        this.Caixa = caixa;
        this.vendas = Vendas;
        this.Gerencia = gerencia;
        this.Financeiro = financeiro;
        this.Relatorios = relatorios;
        this.Lojas = lojas;
        this.ponto = Ponto;
        this.MenuCadastros = menuCadastros;
        this.MenuCaixa = menuCaixa;
        this.MenuMovimento = menuMovimento;
        this.MenuPagar = menuPagar;
        this.MenuPonto = menuPonto;
        this.MenuRelatorios = menuRelatroios;
        this.MenuUtilitarios = menuUtilitarios;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        Integer oldIdGrupo = this.idGrupo;
        this.idGrupo = idGrupo;
        changeSupport.firePropertyChange("idGrupo", oldIdGrupo, idGrupo);
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        String oldGrupo = this.grupo;
        this.grupo = grupo;
        changeSupport.firePropertyChange("grupo", oldGrupo, grupo);
    }

    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    public boolean isCaixa() {
        return Caixa;
    }

    public void setCaixa(boolean Caixa) {
        this.Caixa = Caixa;
    }

    public boolean isVendas() {
        return vendas;
    }

    public void setVendas(boolean vendas) {
        this.vendas = vendas;
    }

    public boolean isGerencia() {
        return Gerencia;
    }

    public void setGerencia(boolean Gerencia) {
        this.Gerencia = Gerencia;
    }

    public boolean isFinanceiro() {
        return Financeiro;
    }

    public void setFinanceiro(boolean Financeiro) {
        this.Financeiro = Financeiro;
    }

    public boolean isRelatorios() {
        return Relatorios;
    }

    public void setRelatorios(boolean Relatorios) {
        this.Relatorios = Relatorios;
    }

    public boolean isLojas() {
        return Lojas;
    }

    public void setLojas(boolean Lojas) {
        this.Lojas = Lojas;
    }

    public boolean isPonto() {
        return ponto;
    }

    public void setPonto(boolean ponto) {
        this.ponto = ponto;
    }

    public boolean isMenuCadastros() {
        return MenuCadastros;
    }

    public void setMenuCadastros(boolean MenuCadastros) {
        this.MenuCadastros = MenuCadastros;
    }

    public boolean isMenuCaixa() {
        return MenuCaixa;
    }

    public void setMenuCaixa(boolean MenuCaixa) {
        this.MenuCaixa = MenuCaixa;
    }

    public boolean isMenuMovimento() {
        return MenuMovimento;
    }

    public void setMenuMovimento(boolean MenuMovimento) {
        this.MenuMovimento = MenuMovimento;
    }

    public boolean isMenuPagar() {
        return MenuPagar;
    }

    public void setMenuPagar(boolean MenuPagar) {
        this.MenuPagar = MenuPagar;
    }

    public boolean isMenuPonto() {
        return MenuPonto;
    }

    public void setMenuPonto(boolean MenuPonto) {
        this.MenuPonto = MenuPonto;
    }

    public boolean isMenuRelatorios() {
        return MenuRelatorios;
    }

    public void setMenuRelatorios(boolean MenuRelatorios) {
        this.MenuRelatorios = MenuRelatorios;
    }

    public boolean isMenuUtilitarios() {
        return MenuUtilitarios;
    }

    public void setMenuUtilitarios(boolean MenuUtilitarios) {
        this.MenuUtilitarios = MenuUtilitarios;
    }

    public boolean isMenuFiscal() {
        return MenuFiscal;
    }

    public void setMenuFiscal(boolean MenuFiscal) {
        this.MenuFiscal = MenuFiscal;
    }

    public boolean isMenuDeposito() {
        return MenuDeposito;
    }

    public void setMenuDeposito(boolean MenuDeposito) {
        this.MenuDeposito = MenuDeposito;
    }

    public boolean isMenuFinanceiro() {
        return MenuFinanceiro;
    }

    public void setMenuFinanceiro(boolean MenuFinanceiro) {
        this.MenuFinanceiro = MenuFinanceiro;
    }

    public boolean isMenuCRM() {
        return MenuCRM;
    }

    public void setMenuCRM(boolean MenuCRM) {
        this.MenuCRM = MenuCRM;
    }

    public boolean isMenuAjuda() {
        return MenuAjuda;
    }

    public void setMenuAjuda(boolean MenuAjuda) {
        this.MenuAjuda = MenuAjuda;
    }

    public boolean isRelVendas() {
        return RelVendas;
    }

    public void setRelVendas(boolean RelVendas) {
        this.RelVendas = RelVendas;
    }

    public boolean isRelCaixa() {
        return RelCaixa;
    }

    public void setRelCaixa(boolean RelCaixa) {
        this.RelCaixa = RelCaixa;
    }

    public boolean isRelGerencial() {
        return RelGerencial;
    }

    public void setRelGerencial(boolean RelGerencial) {
        this.RelGerencial = RelGerencial;
    }

    public boolean isFechaAuto() {
        return fechaAuto;
    }

    public void setFechaAuto(boolean fechaAuto) {
        this.fechaAuto = fechaAuto;
    }

    public boolean isCancelarDia() {
        return cancelarDia;
    }

    public void setCancelarDia(boolean cancelarDia) {
        this.cancelarDia = cancelarDia;
    }

    public boolean isAdministracao() {
        return administracao;
    }

    public void setAdministracao(boolean administracao) {
        this.administracao = administracao;
    }

    public boolean isMovAjusteEstoque() {
        return movAjusteEstoque;
    }

    public void setMovAjusteEstoque(boolean movAjusteEstoque) {
        this.movAjusteEstoque = movAjusteEstoque;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupo != null ? idGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariosGrupo)) {
            return false;
        }
        UsuariosGrupo other = (UsuariosGrupo) object;
        return !((this.idGrupo == null && other.idGrupo != null) 
                || (this.idGrupo != null && !this.idGrupo.equals(other.idGrupo)));
    }

    @Override
    public String toString() {
        return "br.sql.bean.UsuariosGrupo[ idGrupo=" + idGrupo + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
