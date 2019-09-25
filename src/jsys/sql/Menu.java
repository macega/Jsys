package jsys.sql;

import br.rel.etiquetas.EtiquetasJanela;
import br.rel.filtros.FiltroData;
import br.rel.filtros.FiltroDataCheques;
import br.rel.filtros.FiltroDataInicialFinal;
import br.rel.filtros.FiltroDataListaDeProdutos;
import br.rel.filtros.FiltroDataListaFornecedor;
import br.rel.filtros.FiltroDatasCaixa;
import br.rel.filtros.FiltroIdPagamento;
import br.sql.acesso.ConnectionFactory;
import br.sql.acesso.SQLDatabaseConnection;
import br.sql.bean.JsysOrcamento;
import br.sql.bean.Usuarios;
import br.sql.bean.views.JsysFuncionarios;
import br.sql.buscas.ConsultaProdutos;
import br.sql.cadastros.ClientesJanela;
import br.sql.cadastros.FeriadosJanela;
import br.sql.cadastros.LojasJanela;
import br.sql.cadastros.MetasJanela;
import br.sql.cadastros.TitulosJanela;
import br.sql.cadastros.TransportadorasJanela;
import br.sql.cadastros.UsuarioJanela;
import br.sql.cadastros.produtos.FabricantesJanela;
import br.sql.cadastros.produtos.FamiliasJanela;
import br.sql.cadastros.produtos.GruposJanela;
import br.sql.cadastros.produtos.ProdutosJanela;
import br.sql.cadastros.produtos.TributosJanela;
import br.sql.dialogo.RecontarEstoque;
import br.sql.nfe.Janelas.NfceCancelamento;
import br.sql.nfe.Janelas.NfeConsulta;
import br.sql.nfe.Janelas.NfeEmissao;
import br.sql.nfe.Janelas.NfeInutilizacaoJanela;
import br.sql.janelas.caixa.AberturaCaixaJanela;
import br.sql.janelas.caixa.ConsultaReceber;
import br.sql.janelas.caixa.FechamentoCaixa;
import br.sql.janelas.caixa.PagamentosCaixa;
import br.sql.janelas.caixa.RecebimentoCaixa;
import br.sql.janelas.caixa.ReciboAvulso;
import br.sql.janelas.caixa.ReimpressaoVendas;
import br.sql.janelas.caixa.RetiradasTransportarJanela;
import br.sql.janelas.caixa.enviarRelatorioCaixa;
import br.sql.janelas.crm.AgendaTelefonicaJanela;
import br.sql.janelas.crm.helpDesk;
import br.sql.janelas.crm.helpDeskLista;
import br.sql.janelas.crm.helpDeskTipos;
import br.sql.janelas.deposito.TransferenciaDeposito;
import br.sql.janelas.deposito.compras;
import br.sql.janelas.deposito.listaPedidos;
import br.sql.janelas.financeiro.CadastroBancos;
import br.sql.janelas.financeiro.CadastroCheques;
import br.sql.janelas.financeiro.CadastroContas;
import br.sql.janelas.financeiro.CadastroContasSub;
import br.sql.janelas.financeiro.ConsultaPagamentos;
import br.sql.janelas.liberacoes.CancelamentoPagamentos;
import br.sql.janelas.liberacoes.LiberacaoGeralJanelaJDialog;
import br.sql.janelas.liberacoes.RetiradasCancelamento;
import br.sql.janelas.liberacoes.VendasReaberturaCancelamento;
import br.sql.janelas.liberacoes.recebimentoCancelamento;
import br.sql.janelas.movimento.AjusteEstoqueJanela;
import br.sql.janelas.movimento.PedidosJanela;
import br.sql.janelas.movimento.VendasJanela;
import br.sql.janelas.movimento.VendasJanelaAbertura;
import br.sql.janelas.pacote.PacoteJanela;
import br.sql.janelas.ponto.PontoAjusteJanela;
import br.sql.janelas.ponto.PontoControleCompensacao;
import br.sql.janelas.ponto.PontoIntervalo15Janela;
import br.sql.janelas.ponto.PontoJanela;
import br.sql.janelas.utilitarios.CertificadoA1Informacoes;
import br.sql.janelas.utilitarios.ImportadorXML;
import br.sql.janelas.utilitarios.ParametrosJanelas;
import br.sql.janelas.utilitarios.ParametrosLocaisJanelas;
import br.sql.janelas.utilitarios.TransmitirDadosFiscais;
import br.sql.log.Log;
import br.sql.nfe.Janelas.NfceTransmitirContingencia;
import br.sql.nfe.Janelas.NfeStatus;
import br.sql.util.Calculadora;
import br.sql.util.ManagerData;
import br.sql.util.ManagerString;
import br.sql.util.ReportUtils;
import br.sql.util.Retorna;
import br.sql.util.Validar;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
//import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Juliano Alves Medina
 */
public final class Menu extends javax.swing.JFrame {

    private static Usuarios usuario;
    private List<Status> status = new ArrayList<>();
    private static final SQLDatabaseConnection DADOS = new SQLDatabaseConnection();

    /**
     *
     * @param usuario recebe o usuario logado no sistema
     */
    public Menu(Usuarios usuario) {
        initComponents();
        Menu.usuario = usuario;
        if (Menu.usuario.getIdUsuario() != null || Menu.usuario.getIdUsuario() != 0) {
            UsuarioJLabel.setText(Menu.usuario.getIdUsuario().toString() + " - " + Menu.usuario.getUsuario());
            menuCadastro.setVisible(Menu.usuario.getIdGrupo().isMenuCadastros());
            menuCaixa.setVisible(Menu.usuario.getIdGrupo().isMenuCaixa());
            menuFiscal.setVisible(Menu.usuario.getIdGrupo().isMenuFiscal());
            menuMovimento.setVisible(Menu.usuario.getIdGrupo().isMenuMovimento());
            menuPonto.setVisible(Menu.usuario.getIdGrupo().isMenuPonto());
            menuRelatorios.setVisible(Menu.usuario.getIdGrupo().isMenuRelatorios());
            menuDeposito.setVisible(Menu.usuario.getIdGrupo().isMenuDeposito());
            menuFinanceiro.setVisible(Menu.usuario.getIdGrupo().isMenuFinanceiro());
            menuCRM.setVisible(Menu.usuario.getIdGrupo().isMenuCRM());
            menuUtilitarios.setVisible(Menu.usuario.getIdGrupo().isMenuUtilitarios());
            menuAjuda.setVisible(Menu.usuario.getIdGrupo().isMenuAjuda());
            relLojas.setVisible(Menu.usuario.getIdGrupo().isRelGerencial());
            jMenuItemAjusteEstoque.setVisible(Menu.usuario.getIdGrupo().isMovAjusteEstoque());
        }
        setTela("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        UsuarioJLabel.requestFocus();
        
        boolean replicador = "true".equals(ManagerString.noNull(br.JavaApplicationJsys.INI.getString("REPLICADOR", "ATIVO").toLowerCase(), "false"));
        TaskTimer taskTimer = new TaskTimer();
        taskTimer.execute();
        TaskStatus taskStatus = new TaskStatus(replicador);
        taskStatus.execute();
        TaskStatusMostrar taskStatusMostrar = new TaskStatusMostrar(replicador);
        taskStatusMostrar.execute();
        //new TaskConsultaSefaz("true".equals(br.JavaApplicationJsys.INI.getString("FISCAL", "CONSULTA").toLowerCase()), br.JavaApplicationJsys.INI.getString("FISCAL", "CONSULTA_TEMPO"));
        TaskReplicador taskReplicador = new TaskReplicador(replicador, ManagerString.noNull(br.JavaApplicationJsys.INI.getString("REPLICADOR", "TIME"), "23:00:00"));
        taskReplicador.execute();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButtonCorentista = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabelStatus = new javax.swing.JLabel();
        jLabelReplicador = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        ImagenCentro = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        mostraHora = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        UsuarioJLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuCadastro = new javax.swing.JMenu();
        corentista = new javax.swing.JMenu();
        corentistasItens = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        titulos = new javax.swing.JMenuItem();
        jMenuItem52 = new javax.swing.JMenuItem();
        jMenu18 = new javax.swing.JMenu();
        jMenuItem59 = new javax.swing.JMenuItem();
        jMenuItem61 = new javax.swing.JMenuItem();
        jMenuItem84 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        produtos = new javax.swing.JMenu();
        produto = new javax.swing.JMenuItem();
        subGrupo = new javax.swing.JMenuItem();
        grupo = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        menuCaixa = new javax.swing.JMenu();
        recebimento = new javax.swing.JMenuItem();
        jMenuItem74 = new javax.swing.JMenuItem();
        jMenuItem75 = new javax.swing.JMenuItem();
        retiradas = new javax.swing.JMenuItem();
        Abertura = new javax.swing.JMenuItem();
        recebimentosConvinios = new javax.swing.JMenuItem();
        RelatoriosCaixa = new javax.swing.JMenu();
        relCaixa = new javax.swing.JMenuItem();
        relContasPagas = new javax.swing.JMenuItem();
        relMovCaixa = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem73 = new javax.swing.JMenuItem();
        jMenuItem77 = new javax.swing.JMenuItem();
        jMenuItem79 = new javax.swing.JMenuItem();
        jMenuItem76 = new javax.swing.JMenuItem();
        retiradasEnvio = new javax.swing.JMenuItem();
        jMenuGerencial = new javax.swing.JMenu();
        jMenuItem90 = new javax.swing.JMenuItem();
        jMenuItem89 = new javax.swing.JMenuItem();
        jMenuItem1555 = new javax.swing.JMenuItem();
        jMenuItem1556 = new javax.swing.JMenuItem();
        jMenuItem1557 = new javax.swing.JMenuItem();
        jMenuItem1558 = new javax.swing.JMenuItem();
        jMenuItem1559 = new javax.swing.JMenuItem();
        jMenu20 = new javax.swing.JMenu();
        envioDecaixa = new javax.swing.JMenuItem();
        jMenuItem39 = new javax.swing.JMenuItem();
        menuFiscal = new javax.swing.JMenu();
        EeportarFescal = new javax.swing.JMenuItem();
        jMenuItem82 = new javax.swing.JMenuItem();
        jMenuItem70 = new javax.swing.JMenuItem();
        jMenuItem67 = new javax.swing.JMenuItem();
        cadastroECF = new javax.swing.JMenuItem();
        jMenuItem83 = new javax.swing.JMenuItem();
        jMenuItemNfeStatus = new javax.swing.JMenuItem();
        jMenuItemDadosFiscaisContador = new javax.swing.JMenuItem();
        menuMovimento = new javax.swing.JMenu();
        vendasMenu = new javax.swing.JMenuItem();
        pedidosMenu = new javax.swing.JMenuItem();
        ListaProdutosMenu = new javax.swing.JMenuItem();
        jMenuItemAjusteEstoque = new javax.swing.JMenuItem();
        gerencialMenu = new javax.swing.JMenu();
        jMenuItemCadastroMetas = new javax.swing.JMenuItem();
        jMenuItemRecontaEstoque = new javax.swing.JMenuItem();
        jMenuItemCancelarRecebimento = new javax.swing.JMenuItem();
        jMenuItemCancelarRetiradas = new javax.swing.JMenuItem();
        jMenuItemCancelarPagamento = new javax.swing.JMenuItem();
        jMenuItemReabrirVenda = new javax.swing.JMenuItem();
        jMenuItemCancelarVenda = new javax.swing.JMenuItem();
        menuPonto = new javax.swing.JMenu();
        jMenuItem32 = new javax.swing.JMenuItem();
        jMenuItem36 = new javax.swing.JMenuItem();
        jMenuItem35 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem57 = new javax.swing.JMenuItem();
        menuRelatorios = new javax.swing.JMenu();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem33 = new javax.swing.JMenuItem();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenuItem38 = new javax.swing.JMenuItem();
        caixaMunu = new javax.swing.JMenu();
        relCaixaMenu = new javax.swing.JMenuItem();
        relContasPagasMenu = new javax.swing.JMenuItem();
        relMovFinaceiraMenu = new javax.swing.JMenuItem();
        jMenuItem62 = new javax.swing.JMenuItem();
        jMenuItem78 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        produtosMenu = new javax.swing.JMenu();
        jMenuItem81 = new javax.swing.JMenuItem();
        relProdutosNegativos = new javax.swing.JMenuItem();
        jMenuItem29 = new javax.swing.JMenuItem();
        etiquetasMenu = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem66 = new javax.swing.JMenuItem();
        jMenuItem72 = new javax.swing.JMenuItem();
        jMenuItem92 = new javax.swing.JMenuItem();
        jMenuItem93 = new javax.swing.JMenuItem();
        RelVendasMenu = new javax.swing.JMenu();
        relFaturamentoVendas = new javax.swing.JMenuItem();
        jMenuItem65 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem40 = new javax.swing.JMenuItem();
        jMenuItem58 = new javax.swing.JMenuItem();
        vendedoresMenu = new javax.swing.JMenu();
        relRankVendasMenu = new javax.swing.JMenuItem();
        relComissaoColaboradorMenu = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenuItem53 = new javax.swing.JMenuItem();
        jMenuItem54 = new javax.swing.JMenuItem();
        relLojas = new javax.swing.JMenu();
        jMenuIten00 = new javax.swing.JMenuItem();
        jMenuItem80 = new javax.swing.JMenuItem();
        menuDeposito = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem71 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        menuFinanceiro = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenu14 = new javax.swing.JMenu();
        jMenuItem60 = new javax.swing.JMenuItem();
        jMenuItem63 = new javax.swing.JMenuItem();
        jMenuItem43 = new javax.swing.JMenuItem();
        jMenuItem44 = new javax.swing.JMenuItem();
        jMenu19 = new javax.swing.JMenu();
        jMenuItem64 = new javax.swing.JMenuItem();
        jMenu16 = new javax.swing.JMenu();
        jMenuItem50 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenuItem55 = new javax.swing.JMenuItem();
        jMenu13 = new javax.swing.JMenu();
        jMenuItem41 = new javax.swing.JMenuItem();
        jMenuItem42 = new javax.swing.JMenuItem();
        jMenuItem51 = new javax.swing.JMenuItem();
        menuCRM = new javax.swing.JMenu();
        jMenuItem49 = new javax.swing.JMenuItem();
        jMenu15 = new javax.swing.JMenu();
        jMenuItem46 = new javax.swing.JMenuItem();
        jMenuItem45 = new javax.swing.JMenuItem();
        jMenuItem48 = new javax.swing.JMenuItem();
        menuUtilitarios = new javax.swing.JMenu();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem37 = new javax.swing.JMenuItem();
        jMenu17 = new javax.swing.JMenu();
        jMenuItem47 = new javax.swing.JMenuItem();
        jMenuItem56 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        menuAjuda = new javax.swing.JMenu();
        jMenuItemInformaAtuali = new javax.swing.JMenuItem();
        jMenuItemServidorVinculado = new javax.swing.JMenuItem();
        jMenuItemSobre = new javax.swing.JMenuItem();
        jMenuItem69 = new javax.swing.JMenuItem();
        jMenuItem95 = new javax.swing.JMenuItem();
        jMenuItem94 = new javax.swing.JMenuItem();
        jMenuItemSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JSYS-SQL");
        setExtendedState(MAXIMIZED_BOTH);
        setIconImages(null);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/shopping.png"))); // NOI18N
        jButton1.setToolTipText("Vendas");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonCorentista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/corents.png"))); // NOI18N
        jButtonCorentista.setToolTipText("Cadastro de Correntistas");
        jButtonCorentista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonCorentista.setFocusPainted(false);
        jButtonCorentista.setFocusable(false);
        jButtonCorentista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCorentistaActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/Dolar.png"))); // NOI18N
        jButton3.setToolTipText("Recebimento de Caixa");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.setFocusPainted(false);
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/full.png"))); // NOI18N
        jButton4.setToolTipText("Consulta de Produtos");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton4.setFocusPainted(false);
        jButton4.setFocusable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/Paste.png"))); // NOI18N
        jButton5.setToolTipText("Conferencia de Pacote");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton5.setFocusPainted(false);
        jButton5.setFocusable(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/calcu.png"))); // NOI18N
        jButton6.setToolTipText("Calculadora");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton6.setFocusPainted(false);
        jButton6.setFocusable(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabelStatus.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelStatus.setForeground(new java.awt.Color(51, 51, 255));
        jLabelStatus.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelStatus.setText("Jsys-SQL");
        jLabelStatus.setToolTipText("");

        jLabelReplicador.setForeground(new java.awt.Color(0, 102, 102));
        jLabelReplicador.setText("Replicador Ligado");
        jLabelReplicador.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCorentista, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelStatus, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelReplicador, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelReplicador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCorentista, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        ImagenCentro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ImagenCentro.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ImagenCentro, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ImagenCentro, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Data:");

        jLabel3.setText(ManagerData.getDataAtualTypeString());

        jLabel4.setText("Loja:");

        jLabel5.setText(ConnectionFactory.getNomeSQLServer());

        jLabel6.setText("Hora:");

        mostraHora.setText("00:00:00");

        jLabel7.setText("Usuario:");
        jLabel7.setToolTipText("");

        UsuarioJLabel.setText("erro");
        UsuarioJLabel.setToolTipText("");

        jLabel9.setText("Versão: " + br.JavaApplicationJsys.VERSAO_STRING);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mostraHora, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(UsuarioJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel7)
                .addComponent(UsuarioJLabel)
                .addComponent(jLabel9))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(mostraHora, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(jLabel3))
        );

        menuCadastro.setMnemonic('c');
        menuCadastro.setText("Cadastros");

        corentista.setMnemonic('c');
        corentista.setText("Correntista");
        corentista.setToolTipText("");

        corentistasItens.setMnemonic('c');
        corentistasItens.setText("Correntistas");
        corentistasItens.setToolTipText("");
        corentistasItens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                corentistasItensActionPerformed(evt);
            }
        });
        corentista.add(corentistasItens);

        menuCadastro.add(corentista);
        menuCadastro.add(jSeparator2);

        titulos.setMnemonic('t');
        titulos.setText("Titulos");
        titulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titulosActionPerformed(evt);
            }
        });
        menuCadastro.add(titulos);

        jMenuItem52.setText("Bancos");
        jMenuItem52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem52ActionPerformed(evt);
            }
        });
        menuCadastro.add(jMenuItem52);

        jMenu18.setText("Contas");
        jMenu18.setToolTipText("");

        jMenuItem59.setText("Contas");
        jMenuItem59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem59ActionPerformed(evt);
            }
        });
        jMenu18.add(jMenuItem59);

        jMenuItem61.setText("Sub Contas");
        jMenuItem61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem61ActionPerformed(evt);
            }
        });
        jMenu18.add(jMenuItem61);

        menuCadastro.add(jMenu18);

        jMenuItem84.setText("Transportadoras");
        jMenuItem84.setToolTipText("");
        jMenuItem84.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem84ActionPerformed(evt);
            }
        });
        menuCadastro.add(jMenuItem84);
        menuCadastro.add(jSeparator3);

        produtos.setMnemonic('p');
        produtos.setText("Produtos");

        produto.setMnemonic('p');
        produto.setText("Produtos");
        produto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produtoActionPerformed(evt);
            }
        });
        produtos.add(produto);

        subGrupo.setMnemonic('F');
        subGrupo.setText("Famílias");
        subGrupo.setToolTipText("");
        subGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subGrupoActionPerformed(evt);
            }
        });
        produtos.add(subGrupo);

        grupo.setMnemonic('g');
        grupo.setText("Grupo");
        grupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grupoActionPerformed(evt);
            }
        });
        produtos.add(grupo);

        jMenuItem6.setText("Fabricantes");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        produtos.add(jMenuItem6);

        jMenuItem4.setText("Tributos");
        jMenuItem4.setToolTipText("");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        produtos.add(jMenuItem4);

        menuCadastro.add(produtos);

        jMenuBar1.add(menuCadastro);

        menuCaixa.setMnemonic('a');
        menuCaixa.setText("Caixa");

        recebimento.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        recebimento.setMnemonic('R');
        recebimento.setText("Recebimento de Caixa");
        recebimento.setToolTipText("");
        recebimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recebimentoActionPerformed(evt);
            }
        });
        menuCaixa.add(recebimento);

        jMenuItem74.setText("Pagamento");
        jMenuItem74.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem74ActionPerformed(evt);
            }
        });
        menuCaixa.add(jMenuItem74);

        jMenuItem75.setText("Impressão Venda");
        jMenuItem75.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem75ActionPerformed(evt);
            }
        });
        menuCaixa.add(jMenuItem75);

        retiradas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK));
        retiradas.setMnemonic('t');
        retiradas.setText("Retiradas");
        retiradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retiradasActionPerformed(evt);
            }
        });
        menuCaixa.add(retiradas);

        Abertura.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        Abertura.setMnemonic('A');
        Abertura.setText("Abertura de Caixa");
        Abertura.setToolTipText("");
        Abertura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AberturaActionPerformed(evt);
            }
        });
        menuCaixa.add(Abertura);

        recebimentosConvinios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        recebimentosConvinios.setMnemonic('C');
        recebimentosConvinios.setText("Recebimentos (Convênios)");
        recebimentosConvinios.setToolTipText("");
        recebimentosConvinios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recebimentosConviniosActionPerformed(evt);
            }
        });
        menuCaixa.add(recebimentosConvinios);

        RelatoriosCaixa.setMnemonic('l');
        RelatoriosCaixa.setText("Relatorios");

        relCaixa.setMnemonic('c');
        relCaixa.setText("Caixa");
        relCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relCaixaActionPerformed(evt);
            }
        });
        RelatoriosCaixa.add(relCaixa);

        relContasPagas.setMnemonic('o');
        relContasPagas.setText("Contas Pagas");
        relContasPagas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relContasPagasActionPerformed(evt);
            }
        });
        RelatoriosCaixa.add(relContasPagas);

        relMovCaixa.setMnemonic('m');
        relMovCaixa.setText("Mov Financeira");
        relMovCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relMovCaixaActionPerformed(evt);
            }
        });
        RelatoriosCaixa.add(relMovCaixa);

        jMenuItem20.setText("Debitos Convenio");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        RelatoriosCaixa.add(jMenuItem20);

        jMenuItem73.setText("NF-e & NFC-e");
        jMenuItem73.setToolTipText("");
        jMenuItem73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem73ActionPerformed(evt);
            }
        });
        RelatoriosCaixa.add(jMenuItem73);

        jMenuItem77.setText("Recibo - Pagamentos");
        jMenuItem77.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem77ActionPerformed(evt);
            }
        });
        RelatoriosCaixa.add(jMenuItem77);

        jMenuItem79.setText("Recibo - Avulso");
        jMenuItem79.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem79ActionPerformed(evt);
            }
        });
        RelatoriosCaixa.add(jMenuItem79);

        menuCaixa.add(RelatoriosCaixa);

        jMenuItem76.setText("Fechamento");
        jMenuItem76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem76ActionPerformed(evt);
            }
        });
        menuCaixa.add(jMenuItem76);

        retiradasEnvio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        retiradasEnvio.setMnemonic('i');
        retiradasEnvio.setText("Confirmar Retiradas");
        retiradasEnvio.setToolTipText("");
        retiradasEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retiradasEnvioActionPerformed(evt);
            }
        });
        menuCaixa.add(retiradasEnvio);

        jMenuGerencial.setText("Gerencial");
        jMenuGerencial.setToolTipText("");

        jMenuItem90.setText("Cadastro de Metas");
        jMenuItem90.setToolTipText("");
        jMenuItem90.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem90ActionPerformed(evt);
            }
        });
        jMenuGerencial.add(jMenuItem90);

        jMenuItem89.setText("Recontar Estoque");
        jMenuItem89.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem89ActionPerformed(evt);
            }
        });
        jMenuGerencial.add(jMenuItem89);

        jMenuItem1555.setText("Cancelar Recebimento");
        jMenuItem1555.setToolTipText("");
        jMenuItem1555.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1555ActionPerformed(evt);
            }
        });
        jMenuGerencial.add(jMenuItem1555);

        jMenuItem1556.setText("Cancelar Retirada");
        jMenuItem1556.setToolTipText("");
        jMenuItem1556.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1556ActionPerformed(evt);
            }
        });
        jMenuGerencial.add(jMenuItem1556);

        jMenuItem1557.setText("Cancelar Pagamento");
        jMenuItem1557.setToolTipText("");
        jMenuItem1557.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1557ActionPerformed(evt);
            }
        });
        jMenuGerencial.add(jMenuItem1557);

        jMenuItem1558.setText("Reabrir Venda");
        jMenuItem1558.setToolTipText("");
        jMenuItem1558.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1558ActionPerformed(evt);
            }
        });
        jMenuGerencial.add(jMenuItem1558);

        jMenuItem1559.setText("Cancelar Venda");
        jMenuItem1559.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1559ActionPerformed(evt);
            }
        });
        jMenuGerencial.add(jMenuItem1559);

        menuCaixa.add(jMenuGerencial);

        jMenu20.setText("E-Mail");

        envioDecaixa.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        envioDecaixa.setMnemonic('E');
        envioDecaixa.setText("Relatorio Caixa");
        envioDecaixa.setToolTipText("");
        envioDecaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                envioDecaixaActionPerformed(evt);
            }
        });
        jMenu20.add(envioDecaixa);

        jMenuItem39.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem39.setMnemonic('F');
        jMenuItem39.setText("Dados Fiscais (Contador)");
        jMenuItem39.setToolTipText("");
        jMenuItem39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem39ActionPerformed(evt);
            }
        });
        jMenu20.add(jMenuItem39);

        menuCaixa.add(jMenu20);

        jMenuBar1.add(menuCaixa);

        menuFiscal.setMnemonic('F');
        menuFiscal.setText("Fiscal");

        EeportarFescal.setMnemonic('e');
        EeportarFescal.setText("Emissão NF-e");
        EeportarFescal.setToolTipText("");
        EeportarFescal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EeportarFescalActionPerformed(evt);
            }
        });
        menuFiscal.add(EeportarFescal);

        jMenuItem82.setText("Contingencia Transmissor NFC-e ");
        jMenuItem82.setToolTipText("");
        jMenuItem82.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem82ActionPerformed(evt);
            }
        });
        menuFiscal.add(jMenuItem82);

        jMenuItem70.setText("Cancelamento NF-e & NFC-e");
        jMenuItem70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem70ActionPerformed(evt);
            }
        });
        menuFiscal.add(jMenuItem70);

        jMenuItem67.setText("Inutilização NF-e & NFC-e");
        jMenuItem67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem67ActionPerformed(evt);
            }
        });
        menuFiscal.add(jMenuItem67);

        cadastroECF.setText("Consulta NF-e & NFC-e");
        cadastroECF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastroECFActionPerformed(evt);
            }
        });
        menuFiscal.add(cadastroECF);

        jMenuItem83.setText("Transportadoras");
        jMenuItem83.setToolTipText("");
        jMenuItem83.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem83ActionPerformed(evt);
            }
        });
        menuFiscal.add(jMenuItem83);

        jMenuItemNfeStatus.setText("Status Servico Sefaz");
        jMenuItemNfeStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNfeStatusActionPerformed(evt);
            }
        });
        menuFiscal.add(jMenuItemNfeStatus);

        jMenuItemDadosFiscaisContador.setText("Dados Fiscais (Contador)");
        jMenuItemDadosFiscaisContador.setToolTipText("");
        jMenuItemDadosFiscaisContador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDadosFiscaisContadorActionPerformed(evt);
            }
        });
        menuFiscal.add(jMenuItemDadosFiscaisContador);

        jMenuBar1.add(menuFiscal);

        menuMovimento.setMnemonic('M');
        menuMovimento.setText("Movimento");

        vendasMenu.setMnemonic('v');
        vendasMenu.setText("Venda");
        vendasMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendasMenuActionPerformed(evt);
            }
        });
        menuMovimento.add(vendasMenu);

        pedidosMenu.setText("Pedidos");
        pedidosMenu.setToolTipText("");
        pedidosMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pedidosMenuActionPerformed(evt);
            }
        });
        menuMovimento.add(pedidosMenu);

        ListaProdutosMenu.setMnemonic('l');
        ListaProdutosMenu.setText("Lista de Produtos");
        ListaProdutosMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListaProdutosMenuActionPerformed(evt);
            }
        });
        menuMovimento.add(ListaProdutosMenu);

        jMenuItemAjusteEstoque.setText("Ajuste de Estoque");
        jMenuItemAjusteEstoque.setToolTipText("");
        jMenuItemAjusteEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAjusteEstoqueActionPerformed(evt);
            }
        });
        menuMovimento.add(jMenuItemAjusteEstoque);

        gerencialMenu.setMnemonic('g');
        gerencialMenu.setText("Gerencial");
        gerencialMenu.setToolTipText("");

        jMenuItemCadastroMetas.setText("Cadastro de Metas");
        jMenuItemCadastroMetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastroMetasActionPerformed(evt);
            }
        });
        gerencialMenu.add(jMenuItemCadastroMetas);

        jMenuItemRecontaEstoque.setText("Recontar Estoque");
        jMenuItemRecontaEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRecontaEstoqueActionPerformed(evt);
            }
        });
        gerencialMenu.add(jMenuItemRecontaEstoque);

        jMenuItemCancelarRecebimento.setText("Cancelar Recebimento");
        jMenuItemCancelarRecebimento.setToolTipText("");
        jMenuItemCancelarRecebimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCancelarRecebimentoActionPerformed(evt);
            }
        });
        gerencialMenu.add(jMenuItemCancelarRecebimento);

        jMenuItemCancelarRetiradas.setText("Cancelar Retirada");
        jMenuItemCancelarRetiradas.setToolTipText("");
        jMenuItemCancelarRetiradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCancelarRetiradasActionPerformed(evt);
            }
        });
        gerencialMenu.add(jMenuItemCancelarRetiradas);

        jMenuItemCancelarPagamento.setText("Cancelar Pagamento");
        jMenuItemCancelarPagamento.setToolTipText("");
        jMenuItemCancelarPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCancelarPagamentoActionPerformed(evt);
            }
        });
        gerencialMenu.add(jMenuItemCancelarPagamento);

        jMenuItemReabrirVenda.setText("Reabrir Venda");
        jMenuItemReabrirVenda.setToolTipText("");
        jMenuItemReabrirVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReabrirVendaActionPerformed(evt);
            }
        });
        gerencialMenu.add(jMenuItemReabrirVenda);

        jMenuItemCancelarVenda.setText("Cancelar Venda");
        jMenuItemCancelarVenda.setToolTipText("");
        jMenuItemCancelarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCancelarVendaActionPerformed(evt);
            }
        });
        gerencialMenu.add(jMenuItemCancelarVenda);

        menuMovimento.add(gerencialMenu);

        jMenuBar1.add(menuMovimento);

        menuPonto.setMnemonic('p');
        menuPonto.setText("Ponto");
        menuPonto.setToolTipText("");

        jMenuItem32.setMnemonic('m');
        jMenuItem32.setText("Marcação de Ponto");
        jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem32ActionPerformed(evt);
            }
        });
        menuPonto.add(jMenuItem32);

        jMenuItem36.setMnemonic('c');
        jMenuItem36.setText("Controle de Intervalo");
        jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem36ActionPerformed(evt);
            }
        });
        menuPonto.add(jMenuItem36);

        jMenuItem35.setMnemonic('p');
        jMenuItem35.setText("Ponto Ajuste");
        jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem35ActionPerformed(evt);
            }
        });
        menuPonto.add(jMenuItem35);

        jMenuItem23.setText("Cadastro de Feriados");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        menuPonto.add(jMenuItem23);

        jMenuItem57.setText("Cadastro de Compenção");
        jMenuItem57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem57ActionPerformed(evt);
            }
        });
        menuPonto.add(jMenuItem57);

        jMenuBar1.add(menuPonto);

        menuRelatorios.setMnemonic('r');
        menuRelatorios.setText("Relatorios");

        jMenu12.setText("Clientes");
        jMenu12.setToolTipText("");

        jMenuItem33.setText("Lista de Clientes");
        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem33ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem33);

        jMenuItem34.setText("Lista de Colaboradores");
        jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem34ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem34);

        jMenuItem38.setText("Lista de Fornecedores");
        jMenuItem38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem38ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem38);

        menuRelatorios.add(jMenu12);

        caixaMunu.setMnemonic('c');
        caixaMunu.setText("Caixa");

        relCaixaMenu.setMnemonic('c');
        relCaixaMenu.setText("Caixa");
        relCaixaMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relCaixaMenuActionPerformed(evt);
            }
        });
        caixaMunu.add(relCaixaMenu);

        relContasPagasMenu.setMnemonic('o');
        relContasPagasMenu.setText("Contas Pagas");
        relContasPagasMenu.setToolTipText("");
        relContasPagasMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relContasPagasMenuActionPerformed(evt);
            }
        });
        caixaMunu.add(relContasPagasMenu);

        relMovFinaceiraMenu.setMnemonic('m');
        relMovFinaceiraMenu.setText("Mov Financeira");
        relMovFinaceiraMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relMovFinaceiraMenuActionPerformed(evt);
            }
        });
        caixaMunu.add(relMovFinaceiraMenu);

        jMenuItem62.setText("NF-e & NFC-e");
        jMenuItem62.setToolTipText("");
        jMenuItem62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem62ActionPerformed(evt);
            }
        });
        caixaMunu.add(jMenuItem62);

        jMenuItem78.setText("Recibo - Pagamentos");
        jMenuItem78.setToolTipText("");
        jMenuItem78.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem78ActionPerformed(evt);
            }
        });
        caixaMunu.add(jMenuItem78);

        menuRelatorios.add(caixaMunu);

        jMenu10.setText("Recebimento");

        jMenuItem17.setText("Debitos Convenio");
        jMenuItem17.setToolTipText("");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem17);

        menuRelatorios.add(jMenu10);

        produtosMenu.setMnemonic('p');
        produtosMenu.setText("Produtos");

        jMenuItem81.setText("Tabela de Produtos");
        jMenuItem81.setToolTipText("");
        jMenuItem81.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem81ActionPerformed(evt);
            }
        });
        produtosMenu.add(jMenuItem81);

        relProdutosNegativos.setMnemonic('p');
        relProdutosNegativos.setText("Produtos Negativos");
        relProdutosNegativos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relProdutosNegativosActionPerformed(evt);
            }
        });
        produtosMenu.add(relProdutosNegativos);

        jMenuItem29.setText("Mais Vendidos");
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        produtosMenu.add(jMenuItem29);

        etiquetasMenu.setText("Etiquetas");
        etiquetasMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etiquetasMenuActionPerformed(evt);
            }
        });
        produtosMenu.add(etiquetasMenu);

        jMenuItem3.setText("Kardex");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        produtosMenu.add(jMenuItem3);

        jMenuItem66.setText("Vendas Mes");
        jMenuItem66.setToolTipText("");
        jMenuItem66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem66ActionPerformed(evt);
            }
        });
        produtosMenu.add(jMenuItem66);

        jMenuItem72.setText("Devoluções Deposito");
        jMenuItem72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem72ActionPerformed(evt);
            }
        });
        produtosMenu.add(jMenuItem72);

        jMenuItem92.setText("Lista de Transferências");
        jMenuItem92.setToolTipText("");
        jMenuItem92.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem92ActionPerformed(evt);
            }
        });
        produtosMenu.add(jMenuItem92);

        jMenuItem93.setText("Transferência Detalhado");
        jMenuItem93.setToolTipText("");
        jMenuItem93.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem93ActionPerformed(evt);
            }
        });
        produtosMenu.add(jMenuItem93);

        menuRelatorios.add(produtosMenu);

        RelVendasMenu.setText("Vendas");

        relFaturamentoVendas.setText("Faturamento de Vendas");
        relFaturamentoVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relFaturamentoVendasActionPerformed(evt);
            }
        });
        RelVendasMenu.add(relFaturamentoVendas);

        jMenuItem65.setText("Faturamento de Vendas Mes");
        jMenuItem65.setToolTipText("");
        jMenuItem65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem65ActionPerformed(evt);
            }
        });
        RelVendasMenu.add(jMenuItem65);

        menuRelatorios.add(RelVendasMenu);

        jMenu2.setText("Ponto");

        jMenuItem5.setText("Cartão Ponto");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem40.setText("Relação de Faltas");
        jMenuItem40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem40ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem40);

        jMenuItem58.setText("Lista de Compensação");
        jMenuItem58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem58ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem58);

        menuRelatorios.add(jMenu2);

        vendedoresMenu.setMnemonic('v');
        vendedoresMenu.setText("Vendedores");

        relRankVendasMenu.setMnemonic('r');
        relRankVendasMenu.setText("Rank de Vendas");
        relRankVendasMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relRankVendasMenuActionPerformed(evt);
            }
        });
        vendedoresMenu.add(relRankVendasMenu);

        relComissaoColaboradorMenu.setText("Comissão Colaboradores");
        relComissaoColaboradorMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relComissaoColaboradorMenuActionPerformed(evt);
            }
        });
        vendedoresMenu.add(relComissaoColaboradorMenu);

        jMenuItem31.setText("Vendas por Família");
        jMenuItem31.setToolTipText("");
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        vendedoresMenu.add(jMenuItem31);

        jMenuItem53.setText("Lista de Vendas (Por Vendedor)");
        jMenuItem53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem53ActionPerformed(evt);
            }
        });
        vendedoresMenu.add(jMenuItem53);

        jMenuItem54.setText("Lista de Produtos Vendidos (Por Vendedor)");
        jMenuItem54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem54ActionPerformed(evt);
            }
        });
        vendedoresMenu.add(jMenuItem54);

        menuRelatorios.add(vendedoresMenu);

        relLojas.setText("Lojas");

        jMenuIten00.setText("Vendas Lojas");
        jMenuIten00.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIten00ActionPerformed(evt);
            }
        });
        relLojas.add(jMenuIten00);

        menuRelatorios.add(relLojas);

        jMenuItem80.setText("Recibo - Avulso");
        jMenuItem80.setToolTipText("");
        jMenuItem80.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem80ActionPerformed(evt);
            }
        });
        menuRelatorios.add(jMenuItem80);

        jMenuBar1.add(menuRelatorios);

        menuDeposito.setText("Deposito");

        jMenuItem7.setText("Transferências");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        menuDeposito.add(jMenuItem7);

        jMenuItem21.setText("Compras");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        menuDeposito.add(jMenuItem21);

        jMenuItem28.setText("Ajuste de Estoque");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        menuDeposito.add(jMenuItem28);

        jMenu4.setText("Relatorios");

        jMenuItem1.setText("Pedidos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuItem71.setText("Devoluções");
        jMenuItem71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem71ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem71);
        jMenu4.add(jSeparator1);

        jMenu1.setText("Produtos");

        jMenuItem2.setText("Produtos em Falta (Geral)");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem15.setText("Etiquetas");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem15);

        jMenuItem8.setText("Vendas Mes (Geral)");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenu4.add(jMenu1);

        jMenu3.setText("Transferencias");

        jMenuItem11.setText("Lista de Transferências");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem11);

        jMenuItem12.setText("Transferência Detalhado");
        jMenuItem12.setToolTipText("");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem12);

        jMenu4.add(jMenu3);

        menuDeposito.add(jMenu4);

        jMenuBar1.add(menuDeposito);

        menuFinanceiro.setText("Financeiro");

        jMenu7.setText("Relatorios");

        jMenu14.setText("Contas");

        jMenuItem60.setText("Contas Pagas");
        jMenuItem60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem60ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem60);

        jMenuItem63.setText("Contas a Pagar");
        jMenuItem63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem63ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem63);

        jMenuItem43.setText("Contas Pagas (Geral)");
        jMenuItem43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem43ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem43);

        jMenuItem44.setText("Resumo de Contas Pagas");
        jMenuItem44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem44ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem44);

        jMenu7.add(jMenu14);

        jMenu19.setText("Cheques");

        jMenuItem64.setText("Listar Cheques");
        jMenuItem64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem64ActionPerformed(evt);
            }
        });
        jMenu19.add(jMenuItem64);

        jMenu7.add(jMenu19);

        jMenu16.setText("Compras");

        jMenuItem50.setText("Lista de Compras");
        jMenuItem50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem50ActionPerformed(evt);
            }
        });
        jMenu16.add(jMenuItem50);

        jMenu7.add(jMenu16);

        jMenu6.setText("Vendas");

        jMenuItem13.setText("Vendas Lojas");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem13);

        jMenu7.add(jMenu6);

        jMenu8.setText("Transferencias");

        jMenuItem14.setText("Lista de Transferências");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem14);

        jMenuItem16.setText("Transferência Detalhado");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem16);

        jMenu7.add(jMenu8);

        jMenu5.setText("Vendedores");

        jMenuItem10.setText("Comissão Colaboradores");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem10);

        jMenu7.add(jMenu5);

        jMenu11.setText("Produtos");
        jMenu11.setToolTipText("");

        jMenuItem30.setText("Inventário de Estoque");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem30);

        jMenuItem55.setText("Inventário de Estoque (Data)");
        jMenuItem55.setToolTipText("");
        jMenuItem55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem55ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem55);

        jMenu7.add(jMenu11);

        jMenu13.setText("Ponto");

        jMenuItem41.setText("Relação de Faltas Geral");
        jMenuItem41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem41ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem41);

        jMenu7.add(jMenu13);

        menuFinanceiro.add(jMenu7);

        jMenuItem42.setText("Consulta Pagamentos");
        jMenuItem42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem42ActionPerformed(evt);
            }
        });
        menuFinanceiro.add(jMenuItem42);

        jMenuItem51.setText("Cheques");
        jMenuItem51.setToolTipText("");
        jMenuItem51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem51ActionPerformed(evt);
            }
        });
        menuFinanceiro.add(jMenuItem51);

        jMenuBar1.add(menuFinanceiro);

        menuCRM.setText("CRM");

        jMenuItem49.setText("Agenda Telefônica");
        jMenuItem49.setToolTipText("");
        jMenuItem49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem49ActionPerformed(evt);
            }
        });
        menuCRM.add(jMenuItem49);

        jMenu15.setText("Help Desk");

        jMenuItem46.setText("Atendimento");
        jMenuItem46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem46ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem46);

        jMenuItem45.setText("Tipos");
        jMenuItem45.setToolTipText("");
        jMenuItem45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem45ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem45);

        jMenuItem48.setText("Fechamento");
        jMenuItem48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem48ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem48);

        menuCRM.add(jMenu15);

        jMenuBar1.add(menuCRM);

        menuUtilitarios.setMnemonic('u');
        menuUtilitarios.setText("Utilitarios");
        menuUtilitarios.setToolTipText("");

        jMenuItem24.setMnemonic('c');
        jMenuItem24.setText("Cadastro Lojas");
        jMenuItem24.setToolTipText("");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed1(evt);
            }
        });
        menuUtilitarios.add(jMenuItem24);

        jMenuItem9.setText("Parâmetros Locais");
        jMenuItem9.setToolTipText("");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        menuUtilitarios.add(jMenuItem9);

        jMenuItem18.setMnemonic('p');
        jMenuItem18.setText("Parâmetros");
        jMenuItem18.setToolTipText("");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        menuUtilitarios.add(jMenuItem18);

        jMenu9.setMnemonic('a');
        jMenu9.setText("Aparencia");

        jMenuItem26.setMnemonic('w');
        jMenuItem26.setText("Windows");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem26);

        jMenuItem27.setMnemonic('j');
        jMenuItem27.setText("Java");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem27);

        jMenuItem25.setMnemonic('l');
        jMenuItem25.setText("Linux");
        jMenuItem25.setToolTipText("");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem25);

        menuUtilitarios.add(jMenu9);

        jMenuItem37.setMnemonic('u');
        jMenuItem37.setText("Usuarios");
        jMenuItem37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem37ActionPerformed(evt);
            }
        });
        menuUtilitarios.add(jMenuItem37);

        jMenu17.setText("Help Desk");

        jMenuItem47.setText("Lista Atendimentos");
        jMenuItem47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem47ActionPerformed(evt);
            }
        });
        jMenu17.add(jMenuItem47);

        jMenuItem56.setText("Lista Fechamentos");
        jMenuItem56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem56ActionPerformed(evt);
            }
        });
        jMenu17.add(jMenuItem56);

        menuUtilitarios.add(jMenu17);

        jMenuItem19.setText("Importar XML NF-e");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        menuUtilitarios.add(jMenuItem19);

        jMenuBar1.add(menuUtilitarios);

        menuAjuda.setMnemonic('j');
        menuAjuda.setText("Ajuda");
        menuAjuda.setToolTipText("");

        jMenuItemInformaAtuali.setText("Informações e Atualizações");
        jMenuItemInformaAtuali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInformaAtualiActionPerformed(evt);
            }
        });
        menuAjuda.add(jMenuItemInformaAtuali);

        jMenuItemServidorVinculado.setText("Verificar Servidores Vinculados");
        jMenuItemServidorVinculado.setEnabled(false);
        menuAjuda.add(jMenuItemServidorVinculado);

        jMenuItemSobre.setMnemonic('s');
        jMenuItemSobre.setText("Sobre");
        jMenuItemSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSobreActionPerformed(evt);
            }
        });
        menuAjuda.add(jMenuItemSobre);

        jMenuItem69.setText("Informações de Certificado A1");
        jMenuItem69.setToolTipText("");
        jMenuItem69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem69ActionPerformed(evt);
            }
        });
        menuAjuda.add(jMenuItem69);

        jMenuItem95.setText("Download Software - Jsys");
        jMenuItem95.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem95ActionPerformed(evt);
            }
        });
        menuAjuda.add(jMenuItem95);

        jMenuItem94.setText("Atualizar (Rede)");
        jMenuItem94.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem94ActionPerformed(evt);
            }
        });
        menuAjuda.add(jMenuItem94);

        jMenuItemSair.setText("Sair");
        jMenuItemSair.setToolTipText("");
        jMenuItemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSairActionPerformed(evt);
            }
        });
        menuAjuda.add(jMenuItemSair);

        jMenuBar1.add(menuAjuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(940, 651));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void corentistasItensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_corentistasItensActionPerformed
        JFrame frame = new JFrame();
        frame.setContentPane(new ClientesJanela(""));
        frame.pack();
        frame.setExtendedState(4);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Cadastro de Correntistas");
        frame.setVisible(true);
    }//GEN-LAST:event_corentistasItensActionPerformed

    private void produtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produtoActionPerformed
        ProdutosJanela.main(null);
    }//GEN-LAST:event_produtoActionPerformed

    private void jButtonCorentistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCorentistaActionPerformed
        JFrame frame = new JFrame();
        frame.setContentPane(new ClientesJanela(""));
        frame.pack();
        frame.setExtendedState(4);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Cadastro de Correntistas");
        frame.setVisible(true);
    }//GEN-LAST:event_jButtonCorentistaActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        ParametrosJanelas parametrosJanelas = new ParametrosJanelas();
        parametrosJanelas.setVisible(true);
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void grupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grupoActionPerformed
        OpenJFrame(new JFrame(), new GruposJanela(null), "Cadastro de Grupos");
    }//GEN-LAST:event_grupoActionPerformed

    private void subGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subGrupoActionPerformed
        OpenJFrame(new JFrame(), new FamiliasJanela(null), "Cadastro de Famílias");
    }//GEN-LAST:event_subGrupoActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        setTela("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        setTela("javax.swing.plaf.metal.MetalLookAndFeel");
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        setTela("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void retiradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retiradasActionPerformed
        br.sql.janelas.caixa.LancamentoRetiradas lr = new br.sql.janelas.caixa.LancamentoRetiradas();
        lr.setVisible(true);
    }//GEN-LAST:event_retiradasActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Calculadora.main(new String[]{"Calculadora"});
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
        PontoJanela.main(null);
    }//GEN-LAST:event_jMenuItem32ActionPerformed

    private void jMenuItem24ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed1
        OpenJFrame(new JFrame(), new LojasJanela(), "Cadastro de Lojas");
    }//GEN-LAST:event_jMenuItem24ActionPerformed1

    private void jMenuItemSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSobreActionPerformed
        Sobre.main(null);
    }//GEN-LAST:event_jMenuItemSobreActionPerformed

    private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed
        OpenJFrame(new JFrame(), new PontoAjusteJanela(), "Ponto Ajuste");
    }//GEN-LAST:event_jMenuItem35ActionPerformed

    private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed
        PontoIntervalo15Janela.main(new String[0]);
    }//GEN-LAST:event_jMenuItem36ActionPerformed

    private void vendasMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendasMenuActionPerformed
        br.JavaApplicationJsys.setFechaSystemaVendas(false);
        VendasJanelaAbertura f = new VendasJanelaAbertura();
        f.setVisible(true);
    }//GEN-LAST:event_vendasMenuActionPerformed

    private void titulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titulosActionPerformed
        OpenJFrame(new JFrame(), new TitulosJanela(), "Cadastro de Títulos", false);
    }//GEN-LAST:event_titulosActionPerformed

    private void jMenuItem37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem37ActionPerformed
        OpenJFrame(new JFrame(), new UsuarioJanela(), "Cadastro de Senhas");
    }//GEN-LAST:event_jMenuItem37ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        br.JavaApplicationJsys.setFechaSystemaVendas(false);
        VendasJanelaAbertura f = new VendasJanelaAbertura();
        f.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void envioDecaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_envioDecaixaActionPerformed
        enviarRelatorioCaixa dialog = new enviarRelatorioCaixa(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_envioDecaixaActionPerformed

    private void retiradasEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retiradasEnvioActionPerformed
        OpenJFrame(new JFrame(), new RetiradasTransportarJanela(), "Retiradas Transportar");
    }//GEN-LAST:event_retiradasEnvioActionPerformed

    private void recebimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recebimentoActionPerformed
        new RecebimentoCaixa().setVisible(true);
    }//GEN-LAST:event_recebimentoActionPerformed

    private void EeportarFescalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EeportarFescalActionPerformed
        NfeEmissao frmEmissao = new NfeEmissao(new javax.swing.JFrame(), false, 0);
        frmEmissao.setVisible(true);
    }//GEN-LAST:event_EeportarFescalActionPerformed

    private void cadastroECFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastroECFActionPerformed
        NfeConsulta con = new NfeConsulta(new javax.swing.JFrame(), true);
        con.setVisible(true);
    }//GEN-LAST:event_cadastroECFActionPerformed

    private void AberturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AberturaActionPerformed
        AberturaCaixaJanela dialog = new AberturaCaixaJanela(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_AberturaActionPerformed

    private void relProdutosNegativosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relProdutosNegativosActionPerformed
        ReportUtils.openReport("Lista de Produtos", "/br/rel/produtos/ProdutosNegativos.jasper");
    }//GEN-LAST:event_relProdutosNegativosActionPerformed

    private void relContasPagasMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relContasPagasMenuActionPerformed
        abreContasPagas();
    }//GEN-LAST:event_relContasPagasMenuActionPerformed

    private void relCaixaMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relCaixaMenuActionPerformed
        abreRelCaixa();
    }//GEN-LAST:event_relCaixaMenuActionPerformed

    private void relMovFinaceiraMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relMovFinaceiraMenuActionPerformed
        abreMovimCaixa();
    }//GEN-LAST:event_relMovFinaceiraMenuActionPerformed

    private void relRankVendasMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relRankVendasMenuActionPerformed

//        JsysParametros jsysParametros = Retorna.JsysParametros();
//        int DIA_INICIO_FOLHA = Integer.parseInt(ManagerString.noNull(jsysParametros.getDataInicioFolhaPagamento(), "1"));
//        DIA_INICIO_FOLHA = DIA_INICIO_FOLHA == 0 ? 1 : DIA_INICIO_FOLHA;
//        GregorianCalendar calendarInicial = ManagerData.getGregorianCalendar();
//        if (calendarInicial.get(Calendar.DAY_OF_MONTH) < DIA_INICIO_FOLHA) {
//            calendarInicial.add(Calendar.MONTH, -1);
//        }
//        calendarInicial.set(Calendar.DAY_OF_MONTH, DIA_INICIO_FOLHA);
//        calendarInicial.set(Calendar.HOUR_OF_DAY, 0);
//        calendarInicial.set(Calendar.MINUTE, 0);
//        calendarInicial.set(Calendar.SECOND, 0);
//        calendarInicial.set(Calendar.MILLISECOND, 0);
//        
//        
//        GregorianCalendar calendarFinal = ManagerData.getGregorianCalendar();
//        if (calendarFinal.get(Calendar.DAY_OF_MONTH) > DIA_INICIO_FOLHA - 1) {
//            calendarFinal.add(Calendar.MONTH, 1);
//        }
//        calendarFinal.set(Calendar.DAY_OF_MONTH, DIA_INICIO_FOLHA - 1);
//        calendarFinal.set(Calendar.HOUR_OF_DAY, 0);
//        calendarFinal.set(Calendar.MINUTE, 0);
//        calendarFinal.set(Calendar.SECOND, 0);
//        calendarFinal.set(Calendar.MILLISECOND, 0);
//        Map<Object, Object> filtro = new HashMap<>();
//        filtro.put("dataInicial", calendarInicial.getTime());
//        filtro.put("dataFinal", calendarFinal.getTime());
//        List<Object> O = Retorna.findList("JsysMetas.findByMes", filtro);
        if (DADOS.execProcudureRetInt("JsysMetasFindByMes") == 0) {
            JOptionPane.showMessageDialog(this, "Não foi encontrada a meta dos vendedores!"
                    + System.lineSeparator()
                    + "Falar com a Gerencia", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {
            FiltroData dialog = new FiltroData(new javax.swing.JFrame(), false);
            dialog.setRelatorio("/br/rel/vendedores/RankVendas.jasper");
            dialog.setVisible(true);
        }

    }//GEN-LAST:event_relRankVendasMenuActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        RecebimentoCaixa recebimentoCaixa = new RecebimentoCaixa();
        recebimentoCaixa.setVisible(true);
        setCursor(null);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void recebimentosConviniosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recebimentosConviniosActionPerformed
        OpenJFrame(new JFrame(), new ConsultaReceber(), "Consulta Recebimentos");
    }//GEN-LAST:event_recebimentosConviniosActionPerformed

    private void relCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relCaixaActionPerformed
        abreRelCaixa();
    }//GEN-LAST:event_relCaixaActionPerformed

    private void relContasPagasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relContasPagasActionPerformed
        abreContasPagas();
    }//GEN-LAST:event_relContasPagasActionPerformed

    private void relMovCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relMovCaixaActionPerformed
        abreMovimCaixa();
    }//GEN-LAST:event_relMovCaixaActionPerformed

    private void etiquetasMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etiquetasMenuActionPerformed
        OpenJFrame(new JFrame(), new EtiquetasJanela(), "Etiquetas");
    }//GEN-LAST:event_etiquetasMenuActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new ConsultaProdutos(new javax.swing.JFrame(), true, new javax.swing.JTextField()).setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenuItemRecontaEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRecontaEstoqueActionPerformed
        RecontarEstoque dialog = new RecontarEstoque(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_jMenuItemRecontaEstoqueActionPerformed

    private void jMenuItem49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem49ActionPerformed
        OpenJFrame(new JFrame(), new AgendaTelefonicaJanela(), "Agenda Telefônica");
    }//GEN-LAST:event_jMenuItem49ActionPerformed

    private void ListaProdutosMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListaProdutosMenuActionPerformed
        new ConsultaProdutos(new javax.swing.JFrame(), true, new javax.swing.JTextField()).setVisible(true);
    }//GEN-LAST:event_ListaProdutosMenuActionPerformed

    private void pedidosMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pedidosMenuActionPerformed
        OpenJFrame(new JFrame(), new PedidosJanela(), "Pedidos Lojas");
    }//GEN-LAST:event_pedidosMenuActionPerformed

    private void relComissaoColaboradorMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relComissaoColaboradorMenuActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/vendedores/ComissaoVendedor.jasper", FiltroDataInicialFinal.DIA);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_relComissaoColaboradorMenuActionPerformed

    private void relFaturamentoVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relFaturamentoVendasActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/vendas/FaturamentoVendas.jasper", FiltroDataInicialFinal.MES);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_relFaturamentoVendasActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new listaPedidos().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        ReportUtils.openReport("Produtos em Falta (Geral)", "/br/rel/produtos/produtosEmFaltaGeral.jasper");
        //setExtendedState(ICONIFIED);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        br.rel.filtros.FiltroIdProduto.main(new String[]{"/br/rel/produtos/kardex.jasper"});
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItemAjusteEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAjusteEstoqueActionPerformed
        OpenJFrame(new JFrame(), new AjusteEstoqueJanela(), "Ajuste de Estoque");
    }//GEN-LAST:event_jMenuItemAjusteEstoqueActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/ponto/cartaoPonto.jasper", FiltroDataInicialFinal.MES);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        OpenJFrame(new JFrame(), new FabricantesJanela(null), "Cadastro de Fabricantes");
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        JFrame frame = new JFrame();
        frame.setContentPane(new TransferenciaDeposito());
        frame.setTitle("Transferencias");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        br.rel.filtros.FiltroDataListaDeProdutos.main(new String[]{"/br/rel/deposito/vendas/vendasMesGeral.jasper", "ANO"});
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        abrirTransferencia();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        abrirTransferenciaDetalhado();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/deposito/vendas/VendasLojas.jasper", FiltroDataInicialFinal.DIA);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        abrirTransferencia();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        abrirTransferenciaDetalhado();
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        ParametrosLocaisJanelas parametrosLocaisJanela = new ParametrosLocaisJanelas(new javax.swing.JFrame(), true);
        parametrosLocaisJanela.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/vendedores/ComissaoVendedor.jasper", FiltroDataInicialFinal.DIA);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/crediario/convenioDebitos.jasper", FiltroDataInicialFinal.MES);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/crediario/convenioDebitos.jasper", FiltroDataInicialFinal.MES);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        OpenJFrame(new JFrame(), new compras(), "Compras");
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        OpenJFrame(new JFrame(), new EtiquetasJanela(), "Etiquetas");
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItemCadastroMetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastroMetasActionPerformed
        OpenJFrame(new JFrame(), new MetasJanela(), "Cadastros de Metas");
    }//GEN-LAST:event_jMenuItemCadastroMetasActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        OpenJFrame(new JFrame(), new FeriadosJanela(), "Cadastro de Feriados");
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        OpenJFrame(new JFrame(), new AjusteEstoqueJanela(), "Ajuste de Estoque");
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuIten00ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIten00ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/deposito/vendas/VendasLojas.jasper", FiltroDataInicialFinal.DIA);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuIten00ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/produtos/ProdutosMaisVendidos.jasper", FiltroDataInicialFinal.DIA);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        ReportUtils.openReport("Inventário de Estoque", "/br/rel/produtos/InventarioEstoque.jasper");
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        br.rel.filtros.FiltroDataListaFamilias.main(new String[]{"/br/rel/vendedores/RelacaoVendas.jasper", "DIA", "0"});
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed
        ReportUtils.openReport("Lista de Clientes", "/br/rel/clientes/ListaClientes.jasper");
    }//GEN-LAST:event_jMenuItem33ActionPerformed

    private void jMenuItem34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem34ActionPerformed
        ReportUtils.openReport("Lista de Colaboradores", "/br/rel/clientes/ListaColaboradores.jasper");
    }//GEN-LAST:event_jMenuItem34ActionPerformed

    private void jMenuItem38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem38ActionPerformed
        ReportUtils.openReport("Lista de Fornecedores", "/br/rel/clientes/ListaFornecedores.jasper");
    }//GEN-LAST:event_jMenuItem38ActionPerformed

    private void jMenuItemInformaAtualiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInformaAtualiActionPerformed
        Updates dialog = new Updates(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_jMenuItemInformaAtualiActionPerformed

    private void jMenuItem40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem40ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/ponto/FaltasPonto.jasper", FiltroDataInicialFinal.MES);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuItem40ActionPerformed

    private void jMenuItem41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem41ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/ponto/FaltasGeralPonto.jasper", FiltroDataInicialFinal.MES);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuItem41ActionPerformed

    private void jMenuItem42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem42ActionPerformed
        OpenJFrame(new JFrame(), new ConsultaPagamentos(), "Consulta Contas á Pagar");
    }//GEN-LAST:event_jMenuItem42ActionPerformed

    private void jMenuItem43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem43ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/financeiro/ContasPagas.jasper", FiltroDataInicialFinal.MES);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuItem43ActionPerformed

    private void jMenuItem44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem44ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/financeiro/resumoContasPagas.jasper", FiltroDataInicialFinal.MES);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuItem44ActionPerformed

    private void jMenuItem45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem45ActionPerformed
        OpenJFrame(new JFrame(), new helpDeskTipos(), "Help Desk Tipos");
    }//GEN-LAST:event_jMenuItem45ActionPerformed

    private void jMenuItem46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem46ActionPerformed
        helpDesk dialog = new helpDesk(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_jMenuItem46ActionPerformed

    private void jMenuItem47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem47ActionPerformed
        ReportUtils.openReport("Help Desk", "/br/rel/helpDesk/listaAtendimentos.jasper");
    }//GEN-LAST:event_jMenuItem47ActionPerformed

    private void jMenuItem48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem48ActionPerformed
        OpenJFrame(new JFrame(), new helpDeskLista(), "Help Desk Lista");
    }//GEN-LAST:event_jMenuItem48ActionPerformed

    private void jMenuItem50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem50ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/compras/listaCompras.jasper", FiltroDataInicialFinal.MES);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuItem50ActionPerformed

    private void jMenuItem51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem51ActionPerformed
        OpenJFrame(new JFrame(), new CadastroCheques(), "Consulta Cheques");
    }//GEN-LAST:event_jMenuItem51ActionPerformed

    private void jMenuItem52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem52ActionPerformed
        OpenJFrame(new JFrame(), new CadastroBancos(), "Cadastro de Bancos");
    }//GEN-LAST:event_jMenuItem52ActionPerformed

    private void jMenuItem53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem53ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/vendedores/ListaVendasPorVendedor.jasper", FiltroDataInicialFinal.DIA);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuItem53ActionPerformed

    private void jMenuItem54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem54ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/vendedores/ListaProdutosVendidosPorVendedor.jasper", FiltroDataInicialFinal.DIA);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuItem54ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        new PacoteJanela().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem55ActionPerformed
        FiltroData dialog = new FiltroData(new javax.swing.JFrame(), false);
        dialog.setRelatorio("/br/rel/produtos/InventarioEstoqueData.jasper");
        dialog.setVisible(true);
    }//GEN-LAST:event_jMenuItem55ActionPerformed

    private void jMenuItem56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem56ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/helpDesk/listaAtendimentosFinalizados.jasper", FiltroDataInicialFinal.MES);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuItem56ActionPerformed

    private void jMenuItem57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem57ActionPerformed
        new PontoControleCompensacao().setVisible(true);
    }//GEN-LAST:event_jMenuItem57ActionPerformed

    private void jMenuItem58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem58ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/ponto/ListaCompencacao.jasper", FiltroDataInicialFinal.MES);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuItem58ActionPerformed

    private void jMenuItem59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem59ActionPerformed
        OpenJFrame(new JFrame(), new CadastroContas(), "Cadastro de Contas");
    }//GEN-LAST:event_jMenuItem59ActionPerformed

    private void jMenuItem60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem60ActionPerformed
        abreContasPagas();
    }//GEN-LAST:event_jMenuItem60ActionPerformed

    private void jMenuItem61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem61ActionPerformed
        OpenJFrame(new JFrame(), new CadastroContasSub(), "Cadastro Sub Contas");
    }//GEN-LAST:event_jMenuItem61ActionPerformed

    private void jMenuItem63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem63ActionPerformed
        br.rel.filtros.FiltroDataListaFornecedor.main(new String[]{"/br/rel/financeiro/ContasAPagar.jasper", "MES", ""});
    }//GEN-LAST:event_jMenuItem63ActionPerformed

    private void jMenuItem64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem64ActionPerformed
        FiltroDataCheques.main(new String[]{"/br/rel/financeiro/RelacaoCheques.jasper", "MES"});
    }//GEN-LAST:event_jMenuItem64ActionPerformed

    private void jMenuItem65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem65ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/vendas/FaturamentoVendasMes.jasper", FiltroDataInicialFinal.ANO);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuItem65ActionPerformed

    private void jMenuItemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemSairActionPerformed

    private void jMenuItem67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem67ActionPerformed
        NfeInutilizacaoJanela.main(null);
    }//GEN-LAST:event_jMenuItem67ActionPerformed

    private void jMenuItem69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem69ActionPerformed
        CertificadoA1Informacoes.main(null);
    }//GEN-LAST:event_jMenuItem69ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        ImportadorXML dialog = new ImportadorXML(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem39ActionPerformed
        TransmitirDadosFiscais transmitirDadosFiscais = new TransmitirDadosFiscais();
        transmitirDadosFiscais.setVisible(true);
    }//GEN-LAST:event_jMenuItem39ActionPerformed

    private void jMenuItem62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem62ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/fiscal/relatorioNotasFiscais.jasper", FiltroDataInicialFinal.MES);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuItem62ActionPerformed

    private void jMenuItem66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem66ActionPerformed
        FiltroDataListaDeProdutos.main(new String[]{"/br/rel/produtos/VendasMes.jasper", "ANO"});
    }//GEN-LAST:event_jMenuItem66ActionPerformed

    private void jMenuItem70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem70ActionPerformed
        NfceCancelamento cancelar = new NfceCancelamento(null, true, null);
        cancelar.setVisible(true);
    }//GEN-LAST:event_jMenuItem70ActionPerformed

    private void jMenuItem71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem71ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/deposito/DevolucaoLojas.jasper", FiltroDataInicialFinal.MES);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuItem71ActionPerformed

    private void jMenuItem72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem72ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/deposito/DevolucaoLojas.jasper", FiltroDataInicialFinal.MES);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuItem72ActionPerformed

    private void jMenuItem73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem73ActionPerformed
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/fiscal/relatorioNotasFiscais.jasper", FiltroDataInicialFinal.MES);
        filtroDataInicialFinal.setVisible(true);
    }//GEN-LAST:event_jMenuItem73ActionPerformed

    private void jMenuItem74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem74ActionPerformed
        new PagamentosCaixa();
    }//GEN-LAST:event_jMenuItem74ActionPerformed

    private void jMenuItem75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem75ActionPerformed
        new ReimpressaoVendas(new javax.swing.JFrame(), true, "").setVisible(true);
    }//GEN-LAST:event_jMenuItem75ActionPerformed

    private void jMenuItem76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem76ActionPerformed
        FechamentoCaixa fechamentoCaixa = new FechamentoCaixa();
        fechamentoCaixa.setVisible(fechamentoCaixa.getFichas());
    }//GEN-LAST:event_jMenuItem76ActionPerformed

    private void jMenuItem77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem77ActionPerformed
        new FiltroIdPagamento(new javax.swing.JFrame(), false, 0).setVisible(true);
    }//GEN-LAST:event_jMenuItem77ActionPerformed

    private void jMenuItem78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem78ActionPerformed
        new FiltroIdPagamento(new javax.swing.JFrame(), false, 0).setVisible(true);
    }//GEN-LAST:event_jMenuItem78ActionPerformed

    private void jMenuItem79ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem79ActionPerformed
        new ReciboAvulso();
    }//GEN-LAST:event_jMenuItem79ActionPerformed

    private void jMenuItem80ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem80ActionPerformed
        new ReciboAvulso();
    }//GEN-LAST:event_jMenuItem80ActionPerformed

    private void jMenuItem81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem81ActionPerformed
        ReportUtils.openReport("Tabela de Produtos", "/br/rel/produtos/TabelaDeProdutos.jasper");
    }//GEN-LAST:event_jMenuItem81ActionPerformed

    private void jMenuItem82ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem82ActionPerformed
        NfceTransmitirContingencia dialog = new NfceTransmitirContingencia(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_jMenuItem82ActionPerformed

    private void jMenuItem83ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem83ActionPerformed
        new TransportadorasJanela().setVisible(true);
    }//GEN-LAST:event_jMenuItem83ActionPerformed

    private void jMenuItem84ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem84ActionPerformed
        new TransportadorasJanela().setVisible(true);
    }//GEN-LAST:event_jMenuItem84ActionPerformed

    private void jMenuItemCancelarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCancelarVendaActionPerformed
        VendasReaberturaCancelamento vd = new VendasReaberturaCancelamento(null, true);
        vd.setVisible(true);
        if (Validar.Venda(vd.getRetornoId())) {
            LiberacaoGeralJanelaJDialog lg = new LiberacaoGeralJanelaJDialog(null, true);
            lg.setId(vd.getRetornoId());
            lg.setTipo(3);
            lg.setVisible(true);
            if (lg.isConfirma()) {
                JOptionPane.showMessageDialog(null, "Venda Cancelada com Exito");
            } else {
                JOptionPane.showMessageDialog(null, "Não foi posivel Cancelar a venda", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Verificar Codigo da Venda", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemCancelarVendaActionPerformed

    private void jMenuItemReabrirVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReabrirVendaActionPerformed
        VendasReaberturaCancelamento vr = new VendasReaberturaCancelamento(null, true);
        vr.setVisible(true);
        if (Validar.Venda(vr.getRetornoId())) {
            LiberacaoGeralJanelaJDialog lg = new LiberacaoGeralJanelaJDialog(null, true);
            lg.setId(vr.getRetornoId());
            lg.setTipo(1);
            lg.setVisible(true);
            if (lg.isConfirma()) {
                try {
                    Map<Object, Object> filtro = new HashMap<>();
                    filtro.put("idOrcamento", vr.getRetornoId());
                    JsysOrcamento o = (JsysOrcamento) Retorna.findOneResult("JsysOrcamento.findByIdOrcamento", filtro);
                    filtro.clear();
                    filtro.put("idFuncionario", o.getIdFuncionario().getIdCliente());
                    JsysFuncionarios funcionario = (JsysFuncionarios) Retorna.findOneResult("JsysFuncionarios.findByIdFuncionario", filtro);
                    br.JavaApplicationJsys.setFechaSystemaVendas(false);
                    if (funcionario != null) {
                        VendasJanela v = new VendasJanela(vr.getRetornoId(), funcionario);
                        v.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Id Vendedor Não Encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (HeadlessException e) {
                    Log.registraErro(this.getClass().getName(), "reabriVendabuttonActionPerformed", e);
                    JOptionPane.showMessageDialog(this, "ERRO NA EXECUÇÃO", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Venda nâo Encontrada", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemReabrirVendaActionPerformed

    private void jMenuItem1559ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1559ActionPerformed
        jMenuItemCancelarVendaActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem1559ActionPerformed

    private void jMenuItem1558ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1558ActionPerformed
        jMenuItemReabrirVendaActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem1558ActionPerformed

    private void jMenuItem1555ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1555ActionPerformed
        jMenuItemCancelarRecebimentoActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem1555ActionPerformed

    private void jMenuItem92ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem92ActionPerformed
        abrirTransferencia();
    }//GEN-LAST:event_jMenuItem92ActionPerformed

    private void jMenuItem93ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem93ActionPerformed
        abrirTransferenciaDetalhado();
    }//GEN-LAST:event_jMenuItem93ActionPerformed

    private void jMenuItem1556ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1556ActionPerformed
        jMenuItemCancelarRetiradasActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem1556ActionPerformed

    private void jMenuItem1557ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1557ActionPerformed
        jMenuItemCancelarPagamentoActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem1557ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        OpenJFrame(new JFrame(), new TributosJanela(), "Cadastros de Tributos");
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem94ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem94ActionPerformed
        Updates.instalar();
    }//GEN-LAST:event_jMenuItem94ActionPerformed

    private void jMenuItem95ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem95ActionPerformed
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI(br.JavaApplicationJsys.LINK_INSTALADOR));
        } catch (IOException | URISyntaxException e) {
            Log.registraErro("Menu", "jMenuItem95ActionPerformed", e);
        }
    }//GEN-LAST:event_jMenuItem95ActionPerformed

    private void jMenuItemNfeStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNfeStatusActionPerformed
        NfeStatus dialog = new NfeStatus(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_jMenuItemNfeStatusActionPerformed

    private void jMenuItemDadosFiscaisContadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDadosFiscaisContadorActionPerformed
        TransmitirDadosFiscais transmitirDadosFiscais = new TransmitirDadosFiscais();
        transmitirDadosFiscais.setVisible(true);
    }//GEN-LAST:event_jMenuItemDadosFiscaisContadorActionPerformed

    private void jMenuItem90ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem90ActionPerformed
        jMenuItemCadastroMetasActionPerformed(null);
    }//GEN-LAST:event_jMenuItem90ActionPerformed

    private void jMenuItem89ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem89ActionPerformed
        jMenuItemRecontaEstoqueActionPerformed(null);
    }//GEN-LAST:event_jMenuItem89ActionPerformed

    private void jMenuItemCancelarRecebimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCancelarRecebimentoActionPerformed
        recebimentoCancelamento c = new recebimentoCancelamento(new Frame(), true);
        c.setVisible(true);
        if (c.getRetornoId() != null && Validar.Venda(c.getRetornoId())) {
            LiberacaoGeralJanelaJDialog lg = new LiberacaoGeralJanelaJDialog(null, true);
            lg.setId(c.getRetornoId());
            lg.setIdSeq(c.getSeqReceber().toString());
            lg.setTipo(5);
            lg.setVisible(true);
            if (lg.isConfirma()) {
                JOptionPane.showMessageDialog(null, "Recebimento cancelado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível cancelar o Recebimento!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jMenuItemCancelarRecebimentoActionPerformed

    private void jMenuItemCancelarRetiradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCancelarRetiradasActionPerformed
        RetiradasCancelamento dialog = new RetiradasCancelamento(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_jMenuItemCancelarRetiradasActionPerformed

    private void jMenuItemCancelarPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCancelarPagamentoActionPerformed
        CancelamentoPagamentos dialog = new CancelamentoPagamentos(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_jMenuItemCancelarPagamentoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Abertura;
    private javax.swing.JMenuItem EeportarFescal;
    private javax.swing.JLabel ImagenCentro;
    private javax.swing.JMenuItem ListaProdutosMenu;
    private javax.swing.JMenu RelVendasMenu;
    private javax.swing.JMenu RelatoriosCaixa;
    private static javax.swing.JLabel UsuarioJLabel;
    private javax.swing.JMenuItem cadastroECF;
    private javax.swing.JMenu caixaMunu;
    private javax.swing.JMenu corentista;
    private javax.swing.JMenuItem corentistasItens;
    private javax.swing.JMenuItem envioDecaixa;
    private javax.swing.JMenuItem etiquetasMenu;
    private javax.swing.JMenu gerencialMenu;
    private javax.swing.JMenuItem grupo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButtonCorentista;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelReplicador;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenu jMenu17;
    private javax.swing.JMenu jMenu18;
    private javax.swing.JMenu jMenu19;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu20;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuGerencial;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem1555;
    private javax.swing.JMenuItem jMenuItem1556;
    private javax.swing.JMenuItem jMenuItem1557;
    private javax.swing.JMenuItem jMenuItem1558;
    private javax.swing.JMenuItem jMenuItem1559;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem37;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem40;
    private javax.swing.JMenuItem jMenuItem41;
    private javax.swing.JMenuItem jMenuItem42;
    private javax.swing.JMenuItem jMenuItem43;
    private javax.swing.JMenuItem jMenuItem44;
    private javax.swing.JMenuItem jMenuItem45;
    private javax.swing.JMenuItem jMenuItem46;
    private javax.swing.JMenuItem jMenuItem47;
    private javax.swing.JMenuItem jMenuItem48;
    private javax.swing.JMenuItem jMenuItem49;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem50;
    private javax.swing.JMenuItem jMenuItem51;
    private javax.swing.JMenuItem jMenuItem52;
    private javax.swing.JMenuItem jMenuItem53;
    private javax.swing.JMenuItem jMenuItem54;
    private javax.swing.JMenuItem jMenuItem55;
    private javax.swing.JMenuItem jMenuItem56;
    private javax.swing.JMenuItem jMenuItem57;
    private javax.swing.JMenuItem jMenuItem58;
    private javax.swing.JMenuItem jMenuItem59;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem60;
    private javax.swing.JMenuItem jMenuItem61;
    private javax.swing.JMenuItem jMenuItem62;
    private javax.swing.JMenuItem jMenuItem63;
    private javax.swing.JMenuItem jMenuItem64;
    private javax.swing.JMenuItem jMenuItem65;
    private javax.swing.JMenuItem jMenuItem66;
    private javax.swing.JMenuItem jMenuItem67;
    private javax.swing.JMenuItem jMenuItem69;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem70;
    private javax.swing.JMenuItem jMenuItem71;
    private javax.swing.JMenuItem jMenuItem72;
    private javax.swing.JMenuItem jMenuItem73;
    private javax.swing.JMenuItem jMenuItem74;
    private javax.swing.JMenuItem jMenuItem75;
    private javax.swing.JMenuItem jMenuItem76;
    private javax.swing.JMenuItem jMenuItem77;
    private javax.swing.JMenuItem jMenuItem78;
    private javax.swing.JMenuItem jMenuItem79;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem80;
    private javax.swing.JMenuItem jMenuItem81;
    private javax.swing.JMenuItem jMenuItem82;
    private javax.swing.JMenuItem jMenuItem83;
    private javax.swing.JMenuItem jMenuItem84;
    private javax.swing.JMenuItem jMenuItem89;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItem90;
    private javax.swing.JMenuItem jMenuItem92;
    private javax.swing.JMenuItem jMenuItem93;
    private javax.swing.JMenuItem jMenuItem94;
    private javax.swing.JMenuItem jMenuItem95;
    private static javax.swing.JMenuItem jMenuItemAjusteEstoque;
    private javax.swing.JMenuItem jMenuItemCadastroMetas;
    private javax.swing.JMenuItem jMenuItemCancelarPagamento;
    private javax.swing.JMenuItem jMenuItemCancelarRecebimento;
    private javax.swing.JMenuItem jMenuItemCancelarRetiradas;
    private javax.swing.JMenuItem jMenuItemCancelarVenda;
    private javax.swing.JMenuItem jMenuItemDadosFiscaisContador;
    private javax.swing.JMenuItem jMenuItemInformaAtuali;
    private javax.swing.JMenuItem jMenuItemNfeStatus;
    private javax.swing.JMenuItem jMenuItemReabrirVenda;
    private javax.swing.JMenuItem jMenuItemRecontaEstoque;
    private javax.swing.JMenuItem jMenuItemSair;
    private javax.swing.JMenuItem jMenuItemServidorVinculado;
    private javax.swing.JMenuItem jMenuItemSobre;
    private javax.swing.JMenuItem jMenuIten00;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private static javax.swing.JMenu menuAjuda;
    private static javax.swing.JMenu menuCRM;
    private static javax.swing.JMenu menuCadastro;
    private static javax.swing.JMenu menuCaixa;
    private static javax.swing.JMenu menuDeposito;
    private static javax.swing.JMenu menuFinanceiro;
    private static javax.swing.JMenu menuFiscal;
    private static javax.swing.JMenu menuMovimento;
    private static javax.swing.JMenu menuPonto;
    private static javax.swing.JMenu menuRelatorios;
    private static javax.swing.JMenu menuUtilitarios;
    private javax.swing.JLabel mostraHora;
    private javax.swing.JMenuItem pedidosMenu;
    private javax.swing.JMenuItem produto;
    private javax.swing.JMenu produtos;
    private javax.swing.JMenu produtosMenu;
    private javax.swing.JMenuItem recebimento;
    private javax.swing.JMenuItem recebimentosConvinios;
    private javax.swing.JMenuItem relCaixa;
    private javax.swing.JMenuItem relCaixaMenu;
    private javax.swing.JMenuItem relComissaoColaboradorMenu;
    private javax.swing.JMenuItem relContasPagas;
    private javax.swing.JMenuItem relContasPagasMenu;
    private javax.swing.JMenuItem relFaturamentoVendas;
    private static javax.swing.JMenu relLojas;
    private javax.swing.JMenuItem relMovCaixa;
    private javax.swing.JMenuItem relMovFinaceiraMenu;
    private javax.swing.JMenuItem relProdutosNegativos;
    private javax.swing.JMenuItem relRankVendasMenu;
    private javax.swing.JMenuItem retiradas;
    private javax.swing.JMenuItem retiradasEnvio;
    private javax.swing.JMenuItem subGrupo;
    private javax.swing.JMenuItem titulos;
    private javax.swing.JMenuItem vendasMenu;
    private javax.swing.JMenu vendedoresMenu;
    // End of variables declaration//GEN-END:variables

    private void abreContasPagas() {
        FiltroDataListaFornecedor.main(new String[]{"/br/rel/caixa/ContasPagas.jasper", "MES", "0"});
    }

    private void abreMovimCaixa() {
        FiltroDatasCaixa.main(new String[]{"/br/rel/caixa/movimCaixa.jasper", "DIA", "DINH"});
    }

    private void abreRelCaixa() {
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/caixa/caixa.jasper", FiltroDataInicialFinal.DIA);
        filtroDataInicialFinal.setVisible(true);
    }

    private void abrirTransferencia() {
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/transferencia/Tranferencias.jasper", FiltroDataInicialFinal.MES);
        filtroDataInicialFinal.setVisible(true);
    }

    private void abrirTransferenciaDetalhado() {
        FiltroDataInicialFinal filtroDataInicialFinal = new FiltroDataInicialFinal("/br/rel/transferencia/TransferenciasDetalhado.jasper", FiltroDataInicialFinal.MES);
        filtroDataInicialFinal.setVisible(true);
    }

    public static Usuarios getUser() {
        return usuario;
    }

    public static String getNomeBanco() {
        return usuario.getIdCliente().getNomeCorentista() != null ? usuario.getIdCliente().getNomeCorentista() : "BALCAO";
    }

    public static String getNomeUsuario() {
        return usuario != null ? usuario.getUsuario() : "BALCAO";
    }

    public static Integer getIdBanco() {
        return usuario.getIdCliente().getIdCliente() != null ? usuario.getIdCliente().getIdCliente() : 0;
    }

    public static Integer getidUsuario() {
        return usuario.getIdUsuario() != null ? usuario.getIdUsuario() : 0;
    }

    private void setTela(String lookJanela) {
        try {
            UIManager.setLookAndFeel(lookJanela);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            Log.registraErro(this.getClass().getName(), "setTela", e);
        }
    }

    private void OpenJFrame(JFrame frame, Container cntnr, String title) {
        OpenJFrame(frame, cntnr, title, true);
    }

    private void OpenJFrame(JFrame frame, Container cntnr, String title, boolean resizable) {
        frame.setContentPane(cntnr);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(resizable);
        frame.setTitle(title);
        frame.setVisible(true);
    }

    private class TaskTimer extends SwingWorker<Void, Void> {

        public TaskTimer() {
        }

        @Override
        protected Void doInBackground() throws Exception {
            while (true) {
                mostraHora.setText(ManagerData.getHoraAtualTypeString(1));
                Thread.sleep(30000L);
            }
        }
    }

    private class TaskReplicador extends SwingWorker<Void, Void> {

        private final boolean ativo;
        private final Long millis;

        public TaskReplicador(boolean ativo, String millis) {
            this.ativo = ativo;
            jLabelReplicador.setVisible(ativo);
            this.millis = ManagerData.horaToLong(millis);
        }

        @Override
        protected Void doInBackground() throws Exception {
            while (this.ativo) {
                Thread.sleep(this.millis);
                DADOS.execProcedure("Replicar");
            }
            return null;
        }
    }

//    private class TaskConsultaSefaz extends SwingWorker<Void, Void> {
//
//        private final boolean ativo;
//        private final Long millis;
//        private final ConsultaStatusServicoJAXB consulta;
//        private TRetConsStatServ stat;
//
//        private TaskConsultaSefaz(boolean ativo, String millis) {
//            this.ativo = ativo;
//            this.millis = !"00:00:00".equals(millis) ? ManagerData.horaToLong(millis) : 300000L;
//            this.consulta = new ConsultaStatusServicoJAXB();
//            execute();
//        }
//
//        @Override
//        public Void doInBackground() throws NfeException, XMLStreamException, RemoteException {
//            setHoraConsulta(0);
//            while (ativo) {
//                try {
//                    Thread.sleep(millis);
//                    JsysParametros par = Retorna.JsysParametros();
//                    if (ManagerData.dateDiffMinutes(par.getcStat55Hora(), ManagerData.getDate()) > 5) {
//                        stat = consulta.consultar(ConstantesFiscal.NF_E);
//                        par.setcStat55(stat.getCStat());
//                        ConnectionFactory.update(par);
//                        setHoraConsulta(ConstantesFiscal.NF_E);
//                    }
//                    if (ManagerData.dateDiffMinutes(par.getcStat65Hora(), ManagerData.getDate()) > 5) {
//                        stat = consulta.consultar(ConstantesFiscal.NFC_E);
//                        par.setcStat65(stat.getCStat());
//                        ConnectionFactory.update(par);
//                        setHoraConsulta(ConstantesFiscal.NFC_E);
//                    }
//                } catch (InterruptedException ignore) {
//                }
//            }
//            return null;
//        }
//
//        private void setHoraConsulta(int mod) {
//            JsysParametros p = Retorna.JsysParametros();
//            Date d = ManagerData.getDate();
//            switch (mod) {
//                case ConstantesFiscal.NF_E:
//                    p.setcStat55Hora(d);
//                    break;
//                case ConstantesFiscal.NFC_E:
//                    p.setcStat65Hora(d);
//                    break;
//                default:
//                    p.setcStat55Hora(d);
//                    p.setcStat65Hora(d);
//                    break;
//            }
//            ConnectionFactory.update(p);
//        }
//    }
    private class TaskStatus extends SwingWorker<Void, Void> {

        private final Long millis;
        private final boolean ativo;

        public TaskStatus(boolean ativo) {
            this.ativo = ativo;
            this.millis = ManagerData.horaToLong("00:03:00");
        }

        @Override
        protected Void doInBackground() throws Exception {
            while (ativo) {
                ResultSet resultSet = DADOS.execSQL("EXECUTE StatusServidoresVinculados");
                List<Status> S = new ArrayList<>();
                while (resultSet.next()) {
                    Status s = new Status(resultSet.getString(1), resultSet.getInt(2), resultSet.getBoolean(3));
                    S.add(s);
                }
                ResultSet resultSet2 = DADOS.execSQL("SELECT CASE WHEN count(*) = 0 "
                        + " THEN 'Todos os registros enviados.' "
                        + " ELSE CONCAT ('A ',count(*),' registros a serem enviados.') END AS itens "
                        + ",count(*) AS id "
                        + ",CASE WHEN count(*) = 0 THEN 1 ELSE 0 END AS 'online' "
                        + "FROM Replicacao WITH (NOLOCK) "
                        + "WHERE dataProcessado IS NULL");
                while (resultSet2.next()) {
                    Status s = new Status(resultSet2.getString(1), resultSet2.getInt(2), resultSet2.getBoolean(3));
                    S.add(s);
                }
                status = S;
                Thread.sleep(millis);
            }
            return null;
        }
    }

    private class TaskStatusMostrar extends SwingWorker<Void, Void> {

        private final boolean ativo;
        private final Long millis;

        public TaskStatusMostrar(boolean ativo) {
            this.ativo = ativo;
            this.millis = ManagerData.horaToLong("00:00:05");
        }

        @Override
        protected Void doInBackground() throws Exception {
            while (ativo) {
                for (Status statu : status) {
                    jLabelStatus.setText("Aguardando");
                    jLabelStatus.setForeground(Color.black);
                    Thread.sleep(500L);
                    jLabelStatus.setText(statu.getItens());
                    jLabelStatus.setForeground(statu.getOnline() ? new Color(0, 153, 51) : Color.red);
                    Thread.sleep(millis);
                }
            }
            return null;
        }
    }

    private class Status {

        String itens;
        Integer id;
        Boolean online;

        public Status(String itens, Integer id, Boolean online) {
            this.itens = itens;
            this.id = id;
            this.online = online;
        }

        public String getItens() {
            return itens;
        }

        public Integer getId() {
            return id;
        }

        public Boolean getOnline() {
            return online;
        }
    }
}
