package br.sql.janelas.movimento;

import br.rel.filtros.FiltroVendedor;
import br.sql.acesso.SQLDatabaseConnection;
import br.sql.bean.JsysClientes;
import br.sql.bean.JsysClientesIds;
import br.sql.bean.JsysOrcamento;
import br.sql.bean.JsysParametros;
import br.sql.bean.views.JsysFuncionarios;
import br.sql.buscas.ConsultaProdutos;
import br.sql.cadastros.ClientesJanelaEdit;
import br.sql.janelas.liberacoes.LiberacaoGeralJanelaJDialog;
import br.sql.janelas.liberacoes.VendasReaberturaCancelamento;
import br.sql.janelas.ponto.PontoIntervalo15Janela;
import br.sql.janelas.ponto.PontoJanela;
import br.sql.log.Log;
import br.sql.util.ManagerDecimal;
import br.sql.util.Retorna;
import br.sql.util.Validar;
import br.sql.util.timerKeys;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import jsys.sql.Menu;

/**
 *
 * @author Juliano Alves Medina
 */
public class VendasJanelaAbertura extends javax.swing.JFrame {

    private final timerKeys tk = new timerKeys();
    private final JsysParametros par = Retorna.JsysParametros();
    private Integer ultimaVenda;
    private static final SQLDatabaseConnection DADOS = new SQLDatabaseConnection();

    public Integer getUltimaVenda() {
        return ultimaVenda;
    }

    public void setUltimaVenda(Integer ultimaVenda) {
        this.ultimaVenda = ultimaVenda;
        jLabelVenda.setText(this.ultimaVenda.toString());
    }

    private Double ultimaVendaValor;

    public Double getUltimaVendaValor() {
        return ultimaVendaValor;
    }

    public void setUltimaVendaValor(Double ultimaVendaValor) {
        this.ultimaVendaValor = ultimaVendaValor;
        jLabelValor.setText(ManagerDecimal.converter(this.ultimaVendaValor));
    }

    private String ultimaVendaFormaPgto;

    public String getUltimaVendaFormaPgto() {
        return ultimaVendaFormaPgto;
    }

    public void setUltimaVendaFormaPgto(String ultimaVendaFormaPgto) {
        this.ultimaVendaFormaPgto = ultimaVendaFormaPgto;
        jLabelTitulo.setText("Titulo: " + this.ultimaVendaFormaPgto);
    }

    public VendasJanelaAbertura() {
        initComponents();
        relatorioButton.setVisible(!par.getBloquearRankVendedor());
        VendedorUJComboBox.setVisible(!par.isCartaoVendedor());
        //jPanel3.setVisible(!par.isCartaoVendedor());
        vendedorJLabel.setVisible(!par.isCartaoVendedor());
        if (!par.isCartaoVendedor()) {
            for (Object O : Retorna.findList("JsysClientes.findAllColaborador")) {
                JsysClientes vendedor = (JsysClientes) O;
                VendedorUJComboBox.addItem(vendedor.getNomeCorentista() + " " + vendedor.getIdCliente(), vendedor);
            }
        }
    }

    private void Vendas(List<Object> o, JsysClientesIds jsysClientesIds, JsysFuncionarios jsysFuncionarios) {
        VendasJanela v = new VendasJanela(o.isEmpty() ? 0 : (new VendasJanelaAbertas(jsysClientesIds.getJsysClientes().getIdCliente())).getOrcamento(), jsysFuncionarios);
        v.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (br.JavaApplicationJsys.isFechaSystemaVendas()) {
                    java.awt.EventQueue.invokeLater(() -> {
                        VendasJanelaAbertura f = new VendasJanelaAbertura();
                        f.setVisible(true);
                    });
                }
            }
        });
        if (jsysFuncionarios != null) {
            v.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Id Vendedor Não Encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
            v.dispose();
            VendasJanelaAbertura f = new VendasJanelaAbertura();
            f.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        idVendedorJTF = new javax.swing.JTextField();
        VendedorUJComboBox = new componentes.UJComboBox();
        vendedorJLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabelVenda = new javax.swing.JLabel();
        jLabelValor = new javax.swing.JLabel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        consultaProdutosButton = new javax.swing.JButton();
        reabriVendabutton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();
        relatorioButton = new javax.swing.JButton();
        potonButon = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vendas - Jsys");
        setFocusable(false);
        setUndecorated(true);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setFocusable(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Vendas");
        jLabel3.setToolTipText("");
        jLabel3.setFocusable(false);

        jButton1.setText("Fechar");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addComponent(jButton1))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setFocusable(false);

        jPanel1.setFocusable(false);

        idVendedorJTF.setDocument(new br.sql.plainDocument.IntegerDocument());
        idVendedorJTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        idVendedorJTF.setToolTipText("");
        idVendedorJTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                idVendedorJTFFocusGained(evt);
            }
        });
        idVendedorJTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idVendedorJTFKeyPressed(evt);
            }
        });

        VendedorUJComboBox.setAutocompletar(true);
        VendedorUJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VendedorUJComboBoxActionPerformed(evt);
            }
        });

        vendedorJLabel.setText("Vendedor:");
        vendedorJLabel.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(idVendedorJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(vendedorJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(VendedorUJComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(VendedorUJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vendedorJLabel)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(idVendedorJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Ultima Venda"));
        jPanel3.setFocusable(false);

        jLabelVenda.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelVenda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelVenda.setText("Código");
        jLabelVenda.setToolTipText("");
        jLabelVenda.setFocusable(false);

        jLabelValor.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelValor.setText("Valor");
        jLabelValor.setFocusable(false);

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTitulo.setText("Título");
        jLabelTitulo.setToolTipText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelVenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTitulo)
                .addGap(90, 90, 90)
                .addComponent(jLabelValor)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitulo)
                    .addComponent(jLabelVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PDV Livre");
        jLabel2.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Passe o Cartão");
        jLabel1.setToolTipText("");
        jLabel1.setFocusable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setFocusable(false);

        consultaProdutosButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        consultaProdutosButton.setText("Consulta Produtos");
        consultaProdutosButton.setToolTipText("");
        consultaProdutosButton.setFocusable(false);
        consultaProdutosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaProdutosButtonActionPerformed(evt);
            }
        });

        reabriVendabutton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        reabriVendabutton.setForeground(new java.awt.Color(204, 0, 0));
        reabriVendabutton.setText("Reabrir Venda");
        reabriVendabutton.setToolTipText("");
        reabriVendabutton.setFocusable(false);
        reabriVendabutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reabriVendabuttonActionPerformed(evt);
            }
        });

        cancelarButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cancelarButton.setForeground(new java.awt.Color(204, 0, 0));
        cancelarButton.setText("Cancelar Venda");
        cancelarButton.setToolTipText("");
        cancelarButton.setFocusable(false);
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        relatorioButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        relatorioButton.setForeground(new java.awt.Color(0, 0, 255));
        relatorioButton.setText("Rank Vendas");
        relatorioButton.setFocusable(false);
        relatorioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatorioButtonActionPerformed(evt);
            }
        });

        potonButon.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        potonButon.setForeground(new java.awt.Color(0, 0, 255));
        potonButon.setText("Ponto");
        potonButon.setFocusable(false);
        potonButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                potonButonActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 153, 102));
        jButton2.setText("Cadastro Clientes");
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 153, 102));
        jButton3.setText("Consulta Clientes");
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton4.setText("Intervalo");
        jButton4.setFocusable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cancelarButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(reabriVendabutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(consultaProdutosButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(relatorioButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(potonButon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(relatorioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(potonButon)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reabriVendabutton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(consultaProdutosButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void idVendedorJTFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idVendedorJTFFocusGained
        idVendedorJTF.selectAll();
    }//GEN-LAST:event_idVendedorJTFFocusGained

    private void idVendedorJTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idVendedorJTFKeyPressed
        tk.ativar();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String idVendedor = idVendedorJTF.getText();
            idVendedorJTF.setText("");
            if (tk.isCartao()) {
                Map<Object, Object> filtro = new HashMap<>();
                filtro.put("id", idVendedor);
                JsysClientesIds jsysClientesIds = (JsysClientesIds) Retorna.findOneResult("JsysClientesIds.findById", filtro);
                if (jsysClientesIds != null) {
                    filtro.clear();
                    filtro.put("idFuncionario", jsysClientesIds.getJsysClientes());
                    filtro.put("vendido", false);
                    List<Object> o = Retorna.findList("JsysOrcamento.findByVendasAbertasVendedor", filtro);
                    filtro.clear();
                    filtro.put("idFuncionario", jsysClientesIds.getJsysClientes().getIdCliente());
                    JsysFuncionarios jsysFuncionarios = (JsysFuncionarios) Retorna.findOneResult("JsysFuncionarios.findByIdFuncionario", filtro);
                    Vendas(o, jsysClientesIds, jsysFuncionarios);
                    dispose();
                } else {
                    erro();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Falar com A Gerência.");
            }
        }
    }//GEN-LAST:event_idVendedorJTFKeyPressed

    private void reabriVendabuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reabriVendabuttonActionPerformed
        VendasReaberturaCancelamento vr = new VendasReaberturaCancelamento(null, true);
        vr.setVisible(true);
        if (Validar.Venda(vr.getRetornoId())) {
            LiberacaoGeralJanelaJDialog lg = new LiberacaoGeralJanelaJDialog(null, true);
            lg.setId(vr.getRetornoId());
            lg.setTipo(1);
            lg.setVisible(true);
            if (lg.isConfirma()) {
                try {
                    String[] args = new String[1];
                    args[0] = "Vendas - Usuario: " + Menu.getNomeUsuario();
                    Map<Object, Object> filtro = new HashMap<>();
                    filtro.put("idOrcamento", vr.getRetornoId());
                    JsysOrcamento o = (JsysOrcamento) Retorna.findOneResult("JsysOrcamento.findByIdOrcamento", filtro);

                    filtro.clear();
                    filtro.put("idFuncionario", o.getIdFuncionario().getIdCliente());
                    JsysFuncionarios funcionario = (JsysFuncionarios) Retorna.findOneResult("JsysFuncionarios.findByIdFuncionario", filtro);

                    VendasJanela v = new VendasJanela(vr.getRetornoId(), funcionario);
                    v.setVisible(true);
                    //VendasJanela.main(args, vr.getRetornoId(), funcionario);

                    dispose();
                } catch (Exception e) {
                    Log.registraErro(this.getClass().getName(), "reabriVendabuttonActionPerformed", e);
                    JOptionPane.showMessageDialog(null, "ERRO NA EXECUÇÃO", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Venda nâo Encontrada", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_reabriVendabuttonActionPerformed

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
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
    }//GEN-LAST:event_cancelarButtonActionPerformed

    private void potonButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_potonButonActionPerformed
        PontoJanela.main(new String[0]);
    }//GEN-LAST:event_potonButonActionPerformed

    private void consultaProdutosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaProdutosButtonActionPerformed
        new ConsultaProdutos(new javax.swing.JFrame(), true, new javax.swing.JTextField()).setVisible(true);
    }//GEN-LAST:event_consultaProdutosButtonActionPerformed

    private void relatorioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatorioButtonActionPerformed
//        GregorianCalendar calendarInicial = ManagerData.getGregorianCalendar();
//        int DIA_INICIO_FOLHA = Integer.parseInt(par.getDataInicioFolhaPagamento());
//        DIA_INICIO_FOLHA = DIA_INICIO_FOLHA == 0 ? 1 : DIA_INICIO_FOLHA;
//        if (calendarInicial.get(Calendar.DAY_OF_MONTH) < DIA_INICIO_FOLHA) {
//            calendarInicial.add(Calendar.MONTH, -1);
//        }
//        calendarInicial.set(Calendar.DAY_OF_MONTH, DIA_INICIO_FOLHA);
//        calendarInicial.set(Calendar.HOUR_OF_DAY, 0);
//        calendarInicial.set(Calendar.MINUTE, 0);
//        calendarInicial.set(Calendar.SECOND, 0);
//        calendarInicial.set(Calendar.MILLISECOND, 0);
//        GregorianCalendar calendarFinal = ManagerData.getGregorianCalendar();
//        if (calendarFinal.get(Calendar.DAY_OF_MONTH) > DIA_INICIO_FOLHA - 1) {
//            calendarFinal.add(Calendar.MONTH, 1);
//        }
//        calendarFinal.set(Calendar.DAY_OF_MONTH, DIA_INICIO_FOLHA - 1);
//        calendarFinal.set(Calendar.HOUR_OF_DAY, 0);
//        calendarFinal.set(Calendar.MINUTE, 0);
//        calendarFinal.set(Calendar.SECOND, 0);
//        calendarFinal.set(Calendar.MILLISECOND, 0);
//
//        Map<Object, Object> filtro = new HashMap<>();
//        filtro.put("dataInicial", calendarInicial.getTime());
//        filtro.put("dataFinal", calendarFinal.getTime());
//        List<Object> O = Retorna.findList("JsysMetas.findByMes", filtro);
        if (DADOS.execProcudureRetInt("JsysMetasFindByMes") == 0) {
            JOptionPane.showMessageDialog(this, "Não foi encontrada a meta dos vendedores!"
                    + System.lineSeparator()
                    + "Falar com a Gerencia", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {
            FiltroVendedor.main(new String[]{"/br/rel/vendedores/RankVendas.jasper", "DIA"});
        }
    }//GEN-LAST:event_relatorioButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (br.JavaApplicationJsys.isFechaSystemaVendas()) {
            System.exit(0);
        } else {
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ClientesJanelaEdit cadCliente = new ClientesJanelaEdit(new javax.swing.JFrame(), true, new JsysClientes(), true);
        cadCliente.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ConsultaClientesVendas dialog = new ConsultaClientesVendas(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        PontoIntervalo15Janela.main(new String[]{});
    }//GEN-LAST:event_jButton4ActionPerformed

    private void VendedorUJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VendedorUJComboBoxActionPerformed
        
        JOptionPane.showMessageDialog(null, "Erro", "Opçao Desativada !!!", JOptionPane.ERROR_MESSAGE);
        
//        if ("comboBoxEdited".equals(evt.getActionCommand())) {
//            JsysClientes vendedor = (JsysClientes) VendedorUJComboBox.getSelectedObject();
//            if (vendedor != null) {
//                Map<Object, Object> filtro = new HashMap<>();
//                filtro.put("idFuncionario", vendedor);
//                filtro.put("vendido", false);
//                List<Object> o = Retorna.findList("JsysOrcamento.findByVendasAbertasVendedor", filtro);
//
//                filtro.clear();
//                filtro.put("idFuncionario", vendedor.getIdCliente());
//                JsysFuncionarios funcionario = (JsysFuncionarios) Retorna.findOneResult("JsysFuncionarios.findByIdFuncionario", filtro);
//
////                VendasJanela.main(new String[1],
////                        o.isEmpty() ? 0
////                        : (new VendasJanelaAbertas(vendedor.getIdCliente())).getOrcamento(),
////                        funcionario);
//                dispose();
//            } else {
//                erro();
//            }
//        }
    }//GEN-LAST:event_VendedorUJComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.UJComboBox VendedorUJComboBox;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JButton consultaProdutosButton;
    private javax.swing.JTextField idVendedorJTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelValor;
    private javax.swing.JLabel jLabelVenda;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton potonButon;
    private javax.swing.JButton reabriVendabutton;
    private javax.swing.JButton relatorioButton;
    private javax.swing.JLabel vendedorJLabel;
    // End of variables declaration//GEN-END:variables

    private void erro() throws HeadlessException {
        JOptionPane.showMessageDialog(null, "Id Vendedor Não Encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
        //idVendedorJTF.setText("");
        idVendedorJTF.requestFocus();
    }
}
