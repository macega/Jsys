package br.sql.acesso;

import br.sql.bean.Usuarios;
import br.sql.acesso.ini.IniFiles;
import br.sql.janelas.caixa.RetiradasJanela;
import br.sql.janelas.movimento.VendasJanelaAbertura;
import br.sql.janelas.pacote.PacoteJanela;
import br.sql.log.Log;
import br.sql.util.Criptografia.Security;
import br.sql.util.Validar;
import java.awt.HeadlessException;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.PersistenceException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import jsys.sql.Menu;

/**
 *
 * @author Juliano Alves Medina
 */
public final class LoginTela extends javax.swing.JFrame {

    private String bases[];
    private Integer err = 0;
    private final RuntimeMXBean rt = ManagementFactory.getRuntimeMXBean();
    private final int runtimePid = Integer.parseInt(rt.getName().substring(0, rt.getName().indexOf("@")));
    private final MatrizDinamica<String> matrizDinamica = new MatrizDinamica<>();

    /**
     * Creates new form LoginTela
     *
     */
    public LoginTela() {
        System.setProperty("user.timezone", "GMT-4:00");
        initException();
        monitoredVMs();
        criaMatrisINI();
        if (caregaBases()) {
            initComponents();
            setTela();
            try {
                Updates.verificaVersao();
            } catch (SQLException e) {
                Log.registraErro("LoginTela", "LoginTela", e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        usuarioTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        senhaPF = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        bancoDadosJC = new javax.swing.JComboBox(bases);
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jsys - Login");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Controle de Acesso"));

        jLabel2.setText("Usúario");

        usuarioTF.setDocument(new br.sql.plainDocument.JTextFieldTamanhoMaxDocument(true)
        );
        usuarioTF.setToolTipText("Digite o seu Usuario de acesso ao Sistema");
        usuarioTF.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        usuarioTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usuarioTFKeyReleased(evt);
            }
        });

        jLabel3.setText("Senha");

        senhaPF.setDocument(new br.sql.plainDocument.JTextFieldTamanhoMaxDocument(true));
        senhaPF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                senhaPFFocusGained(evt);
            }
        });
        senhaPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                senhaPFKeyPressed(evt);
            }
        });

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Ret");
        jButton4.setFocusable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Venda");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton3.setText("Novo");
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        bancoDadosJC.setFocusable(false);

        jLabel5.setText("Banco de Dados");
        jLabel5.setToolTipText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(bancoDadosJC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addComponent(senhaPF)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(usuarioTF)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bancoDadosJC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuarioTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(senhaPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton4)
                    .addComponent(jButton5)))
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/sql/imagens/secrecy-icon (Custom).png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(br.JavaApplicationJsys.VERSAO_STRING);
        jLabel4.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Login Sistema");
        jLabel6.setToolTipText("");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(441, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        openVendas();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        verificaSenha(1);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        verificaSenha(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void senhaPFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_senhaPFKeyPressed
        if (evt.getKeyCode() == 10) {
            verificaSenha(0);
        }
    }//GEN-LAST:event_senhaPFKeyPressed

    private void senhaPFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_senhaPFFocusGained
        senhaPF.selectAll();
    }//GEN-LAST:event_senhaPFFocusGained

    private void usuarioTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioTFKeyReleased
        if (evt.getKeyCode() == 10) {
            senhaPF.requestFocus();
        }
    }//GEN-LAST:event_usuarioTFKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        editIni();
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox bancoDadosJC;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField senhaPF;
    private javax.swing.JTextField usuarioTF;
    // End of variables declaration//GEN-END:variables

//    /**
//     * @author Juliano Alves Medina
//     *
//     * @param args
//     */
//    public static void main(final String args[]) {
//        boolean adapta = false;
//        for (String s : args) {
//            if ("adapta".equals(s)) {
//                adapta = true;
//                break;
//            }
//        }
//        if (adapta) {
//            br.adp.geral.Geral.main(args);
//        } else {
//            java.awt.EventQueue.invokeLater(() -> {
//                LoginTela loginTela = new LoginTela();
//                loginTela.setVisible(true);
//                loginTela.autoLogin(args);
//            });
//        }
//    }
    private void setFocus() {
        enabledItens(true);
        usuarioTF.requestFocus();
        usuarioTF.selectAll();
    }

    private void setTela() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
            this.pack();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            Log.registraErro(this.getClass().getName(), "setTela", e);
        }
    }

    /**
     * o parametro "tipo" e para qual janela vai ser aberta
     *
     * @param tipo 0:menu 1:liberacaoJanela 2:retiradasJanela
     * @throws HeadlessException
     */
    private void verificaSenha(Integer tipo) {
        enabledItens(false);
        if (bases != null) {
            br.JavaApplicationJsys.base.setNome(bases[bancoDadosJC.getSelectedIndex()]);
            if (tipo == 2) {
                usuarioTF.setText("SUPERVISOR");
                senhaPF.setText("AE3ILM");
                tipo = 0;
            }
            if (tipo == 3) {
                usuarioTF.setText("SUPERVISOR");
                senhaPF.setText("AE3ILM");
                tipo = 1;
            }
            Security s = new Security();
            String password;
            Usuarios usuario = null;
            try {
                password = s.getHexadecimal(String.valueOf(senhaPF.getPassword()), "SHA-256", "UTF-8");
                Map<Object, Object> filtro = new HashMap<>();
                filtro.put("usuario", usuarioTF.getText());
                filtro.put("password", password);
                filtro.put("bloqueado", false);
                usuario = (Usuarios) br.sql.util.Retorna.findOneResult("Usuarios.findByAcesso", filtro);
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                Log.registraErro(LoginTela.class.getName(), "verificaSenha", e);
            }
            if (usuario != null) {
                switch (tipo) {
                    case 0:
                        new Menu(usuario).setVisible(true);
                        Log.registraUsuario(usuarioTF.getText());
                        closed();
                        break;
                    case 1:
                        if (usuario.getIdGrupo().isFinanceiro()) {
                            JFrame frame = new JFrame();
                            frame.setContentPane(new RetiradasJanela());
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.setTitle("Confirmar Retiradas");
                            frame.pack();
                            frame.setLocationRelativeTo(null);
                            frame.setVisible(true);
                            Log.registraUsuario(usuarioTF.getText());
                            closed();
                        } else {
                            JOptionPane.showMessageDialog(null, "Usuario sem Permissão!!");
                            setFocus();
                        }
                        break;
                    default:
                        break;
                }
            } else {
                err++;
                if (err.equals(4)) {
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Usúario/Senha incorretos");
                    setFocus();
                }
            }
        }
    }

    /**
     *
     * isso vai gravar no log dos erro nao encontrados
     */
    private void initException() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                Log.registraErro(t.getClass().getName(), "uncaughtException", e);
            }
        });
    }

    /**
     *
     * se existe outra instância, mostra a mensagem e finaliza a instância
     * atual. Caso contrário inicia a aplicação.
     */
    private void monitoredVMs() throws HeadlessException {
        if (!MonitoredVMs.getMonitoredVMs(runtimePid)) {
            JOptionPane.showMessageDialog(null, "O Programa já Está Aberto.", "ERRO", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    private boolean caregaBases() {
        IniFiles ini;
        if (!new File("jsys.ini").exists()) {
            editIni();
            return false;
        } else {
            ini = new IniFiles("jsys.ini");
            int erro = 0;
            for (int i = 0; i < matrizDinamica.getLengthLinha(); i++) {
                String[] c = matrizDinamica.get(i, 0).split(",");
                String coluna = c[0];
                for (int j = 1; j < matrizDinamica.getLengthColuna(i); j++) {
                    String[] element = matrizDinamica.get(i, j).split(","); // obtendo o elementos                     
                    if (ini.getString(coluna, element[0]) == null | "".equals(ini.getString(coluna, element[0]))) {
                        erro += 1;
                        if ("LISTA BASE".equals(coluna) & "LISTA".equals(element[0])) {
                            editIni();
                            return false;
                        } else {
                            String valor = JOptionPane.showInputDialog(null,
                                    "Parâmetros Faltando."
                                    + System.lineSeparator()
                                    + "[" + coluna + "]"
                                    + System.lineSeparator()
                                    + element[0] + "=", element[1]);
                            ini.setString(coluna, element[0], valor);
                        }
                    }
                }
            }
            if (erro == 0) {
                bases = ini.getString("LISTA BASE", "LISTA").split(",");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Parâmetros Alterados\nO sistema será Finalizado.", "ERRO", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
                return false;
            }
        }
    }

    private void editIni() {
        CadastroEmpJanela ce = new CadastroEmpJanela();
        ce.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        ce.setTitle("Jsys");
        ce.setLocationRelativeTo(null);
        ce.setVisible(true);
    }

    private void openVendas() {
        try {
            if (bases != null) {
                //args[0] = "Vendas - Balcão";
                br.JavaApplicationJsys.base.setNome(bases[bancoDadosJC.getSelectedIndex()]);
                //jsys.sql.Menu menu = new jsys.sql.Menu();
                //br.sql.janelas.movimento.VendasJanelaAbertura.main(args, true);

                java.awt.EventQueue.invokeLater(() -> {
                    br.JavaApplicationJsys.setFechaSystemaVendas(true);
                    VendasJanelaAbertura f = new VendasJanelaAbertura();
                    f.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    f.setVisible(true);
                });
                Log.registraUsuario("Balcao");
                closed();
            }
        } catch (PersistenceException e) {
            Log.registraErro(this.getClass().getName(), "jButton5ActionPerformed", e);
        }
    }

    private void closed() {
        this.setVisible(false);
        this.dispose();
    }

    public void autoLogin(String[] args) throws HeadlessException {
        for (String arg : args) {
            if (Validar.isNotNullOrWhite(arg)) {
                switch (arg) {
                    case "vendas":
                        openVendas();
                        break;
                    case "supervisor":
                        verificaSenha(2);
                        break;
                    case "pacote":
                        openPacote();
                        break;
                    case "supervisorRet":
                        verificaSenha(3);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void openPacote() {
        try {
            if (bases != null) {
                br.JavaApplicationJsys.base.setNome(bases[bancoDadosJC.getSelectedIndex()]);
                new PacoteJanela().setVisible(true);
                Log.registraUsuario("Pacote");
                closed();
            }
        } catch (PersistenceException e) {
            Log.registraErro(this.getClass().getName(), "jButton5ActionPerformed", e);
        }
    }

    private void criaMatrisINI() {
        matrizDinamica.set(0, matrizDinamica.getLengthColuna(0), "LISTA BASE,lista base");
        matrizDinamica.set(0, matrizDinamica.getLengthColuna(0), "LISTA,lista");

        matrizDinamica.set(1, matrizDinamica.getLengthColuna(1), "EMAIL,email@email.com");
        matrizDinamica.set(1, matrizDinamica.getLengthColuna(1), "USER,user email");
        matrizDinamica.set(1, matrizDinamica.getLengthColuna(1), "SENHA,senha email");
        matrizDinamica.set(1, matrizDinamica.getLengthColuna(1), "DESTINO_CAIXA,email@email.com");
        matrizDinamica.set(1, matrizDinamica.getLengthColuna(1), "HOST,smtp.gmail.com");
        matrizDinamica.set(1, matrizDinamica.getLengthColuna(1), "PORTA,587");
        matrizDinamica.set(1, matrizDinamica.getLengthColuna(1), "AUTH,true");
        matrizDinamica.set(1, matrizDinamica.getLengthColuna(1), "STARTTLS,true");
        matrizDinamica.set(1, matrizDinamica.getLengthColuna(1), "DESTINO_CONTADOR,email@email.com");
        matrizDinamica.set(1, matrizDinamica.getLengthColuna(1), "NOME_EMAIL,email");
        matrizDinamica.set(1, matrizDinamica.getLengthColuna(1), "NOME_DESTINO_CAIXA,caixa");
        matrizDinamica.set(1, matrizDinamica.getLengthColuna(1), "NOME_DESTINO_CONTADOR,contador");

        matrizDinamica.set(2, matrizDinamica.getLengthColuna(2), "LISTA IMP,");
        matrizDinamica.set(2, matrizDinamica.getLengthColuna(2), "IMP1,A");
        matrizDinamica.set(2, matrizDinamica.getLengthColuna(2), "IMP2,B");

        matrizDinamica.set(3, matrizDinamica.getLengthColuna(3), "FISCAL,fiscal");
        matrizDinamica.set(3, matrizDinamica.getLengthColuna(3), "SERIE,1");
        matrizDinamica.set(3, matrizDinamica.getLengthColuna(3), "CONSULTA,false");
        matrizDinamica.set(3, matrizDinamica.getLengthColuna(3), "CONSULTA_TEMPO,00:00:00");
        matrizDinamica.set(3, matrizDinamica.getLengthColuna(3), "TP_AMB,2 - Homologação");

        matrizDinamica.set(4, matrizDinamica.getLengthColuna(4), "REPLICADOR,replicador");
        matrizDinamica.set(4, matrizDinamica.getLengthColuna(4), "ATIVO,false");
        matrizDinamica.set(4, matrizDinamica.getLengthColuna(4), "TIME,01:00:00");
    }

    private void enabledItens(boolean enabled) {
        bancoDadosJC.setEnabled(enabled);
        jButton3.setEnabled(enabled);
        usuarioTF.setEnabled(enabled);
        senhaPF.setEnabled(enabled);
        jButton4.setEnabled(enabled);
        jButton5.setEnabled(enabled);
        jButton1.setEnabled(enabled);
    }
}
