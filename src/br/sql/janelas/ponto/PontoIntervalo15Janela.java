package br.sql.janelas.ponto;

import br.sql.acesso.SQLDatabaseConnection;
import br.sql.log.Log;
import br.sql.defaultTableCellRenderer.ColorirPonto15;
import br.sql.defaultTableCellRenderer.ColorirPonto15Todos;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juliano Alves Medina
 */
public final class PontoIntervalo15Janela extends JPanel {
    
    private static final SQLDatabaseConnection DADOS = new SQLDatabaseConnection();

    public PontoIntervalo15Janela() {
        initComponents();
        registrarAcoesDoTeclado();
        actionConsultaNoIntervalo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        idTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        FormListener formListener = new FormListener();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setFocusable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Controle de Intervalo");
        jLabel1.setToolTipText("");
        jLabel1.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(422, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel3.setFocusable(false);

        jScrollPane1.setFocusable(false);
        jScrollPane1.setRequestFocusEnabled(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setEnabled(false);
        jTable1.setFocusable(false);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("No Intervalo");
        jButton1.setFocusable(false);
        jButton1.addActionListener(formListener);

        jButton2.setText("Fora do Intervalo");
        jButton2.setFocusable(false);
        jButton2.addActionListener(formListener);

        jButton3.setText("Bloqueados");
        jButton3.setToolTipText("");
        jButton3.setFocusable(false);
        jButton3.addActionListener(formListener);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Passe o cartão do Colaborador");
        jLabel2.setToolTipText("");
        jLabel2.setFocusable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idTF, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(idTF, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jButton1) {
                PontoIntervalo15Janela.this.jButton1ActionPerformed(evt);
            }
            else if (evt.getSource() == jButton2) {
                PontoIntervalo15Janela.this.jButton2ActionPerformed(evt);
            }
            else if (evt.getSource() == jButton3) {
                PontoIntervalo15Janela.this.jButton3ActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        actionConsultaNoIntervalo();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        actionConsultaForaIntervalo();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        actionConsultaBloqueados();
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField idTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    public static void main(final String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setContentPane(new PontoIntervalo15Janela());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setTitle("Controle de Intervalo");
        });
    }

    private void registrarAcoesDoTeclado() {
        ActionMap actionMap = this.getActionMap();
        actionMap.put("botaoENTER", new BotaoAction());
        actionMap.put("botao1", new BotaoAction1());
        this.setActionMap(actionMap);
        InputMap imap = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        imap.put(KeyStroke.getKeyStroke("ENTER"), "botaoENTER");
        imap.put(KeyStroke.getKeyStroke("F5"), "botao1");
    }

    private class BotaoAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            /*
             * -1 bloqueado
             *  0 entrou
             *  1 saiu
             *  2 limite de funcionarios atingido
             *  3 já foi para intervalo
             *  4 colaborador nao encontrador
             */
            if (br.sql.util.Validar.isNotNullOrWhite(idTF.getText().trim())) {
                try {
                    switch (DADOS.execProcudureRetInt("registraPonto15(" + idTF.getText().trim() + ")")) {
                        case -1:
                            JOptionPane.showMessageDialog(null, "Colaborador ultrapassou o limite Permitido,"
                                    + System.lineSeparator()
                                    + "Veja a Tabela.", "Bloqueio", JOptionPane.ERROR_MESSAGE);
                            actionConsultaBloqueados();
                            break;
                        case 0:
                            JOptionPane.showMessageDialog(null, "Colaborador Liberado para o Intervalo.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            actionConsultaNoIntervalo();
                            break;
                        case 1:
                            JOptionPane.showMessageDialog(null, "Intervalo Finalizado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            actionConsultaNoIntervalo();
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(null, "Limite de colaboradores no intervalo já alcançado.", "Bloqueio", JOptionPane.ERROR_MESSAGE);
                            actionConsultaNoIntervalo();
                            break;
                        case 3:
                            JOptionPane.showMessageDialog(null, "Colaborador já foi ao intervalo hoje.", "Bloqueio", JOptionPane.ERROR_MESSAGE);
                            actionConsultaForaIntervalo();
                            break;
                        case 4:
                            JOptionPane.showMessageDialog(null, "Colaborador Não encontrado.", "Bloqueio", JOptionPane.ERROR_MESSAGE);
                            actionConsultaForaIntervalo();
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Colaborador Bloqueado.", "Bloqueio", JOptionPane.ERROR_MESSAGE);
                            actionConsultaBloqueados();
                            break;
                    }
                } catch (Exception ex) {
                    Log.registraErro(this, "BotaoAction", ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Colaborador Não encontrado,\\nPasse novamente o cartão.", "Bloqueio", JOptionPane.ERROR_MESSAGE);
            }
            idTF.setText("");
            idTF.requestFocus();
        }
    }

    private class BotaoAction1 extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            actionConsultaNoIntervalo();
        }
    }

    private void actionConsultaBloqueados() {
        try {
            ResultSet x = DADOS.execSQL("SELECT jsysFuncionarios.nomeCliente "
                    + "	,CONVERT(VARCHAR, funcionariosPonto.data, 105) AS dia "
                    + "	,CONVERT(VARCHAR, isnull((intervalo15Fim - intervalo15Inicio), cast('1900-01-01 00:00:00.000' AS DATETIME)), 108) AS Tempo "
                    + "FROM funcionariosPonto WITH (NOLOCK) "
                    + "INNER JOIN jsysFuncionarios ON jsysFuncionarios.idFuncionario = funcionariosPonto.idFuncionario "
                    + "INNER JOIN ( "
                    + "	SELECT max(funcionariosPonto.data) AS data "
                    + "		,funcionariosPonto.idFuncionario "
                    + "	FROM funcionariosPonto WITH (NOLOCK) "
                    + "	INNER JOIN ( "
                    + "		SELECT max(data) AS data "
                    + "			,funcionariosPonto.idFuncionario "
                    + "		FROM funcionariosPonto WITH (NOLOCK) "
                    + "		INNER JOIN jsysFuncionarios WITH (NOLOCK) ON jsysFuncionarios.idFuncionario = funcionariosPonto.idFuncionario "
                    + "		WHERE dataAdmissaoServico IS NOT NULL "
                    + "		GROUP BY funcionariosPonto.idFuncionario "
                    + "		) AS tb ON funcionariosPonto.data < tb.data "
                    + "		AND tb.idFuncionario = funcionariosPonto.idFuncionario "
                    + "	WHERE (DATEPART(DW, funcionariosPonto.data) <> 1) "
                    + "		AND ( "
                    + "			( "
                    + "				funcionariosPonto.compensacao = 1 "
                    + "				AND funcionariosPonto.intervalo15Inicio IS NOT NULL "
                    + "				AND funcionariosPonto.intervalo15Fim IS NOT NULL "
                    + "				) "
                    + "			OR ( "
                    + "				funcionariosPonto.falta <> 1 "
                    + "				AND funcionariosPonto.feriado <> 1 "
                    + "				AND funcionariosPonto.ferias <> 1 "
                    + "				AND funcionariosPonto.atestado <> 1 "
                    + "				AND funcionariosPonto.compensacao <> 1 "
                    + "				) "
                    + "			) "
                    + "	GROUP BY funcionariosPonto.idFuncionario "
                    + "	) AS tab ON funcionariosPonto.data = tab.data "
                    + "	AND tab.idFuncionario = funcionariosPonto.idFuncionario");

            DefaultTableModel aModel = new DefaultTableModel(new Object[][]{}, new String[]{"Colaborador", "Dia", "Tempo"}) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            java.sql.ResultSetMetaData rsmd = x.getMetaData();
            int colNo = rsmd.getColumnCount();
            while (x.next()) {
                Object[] objects = new Object[colNo];
                for (int i = 0; i < colNo; i++) {
                    objects[i] = x.getObject(i + 1);
                }
                aModel.addRow(objects);
            }
            jTable1.setModel(aModel);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(370);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(75);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(75);
            jTable1.setDefaultRenderer(Object.class, new ColorirPonto15Todos());
        } catch (SQLException e) {
            Log.registraErro(this.getClass().getName(), "actionConsultaBloqueados", e);
        }
    }

    private void actionConsultaNoIntervalo() {
        try {
            ResultSet x = DADOS.execSQL("SELECT nomeCorentista AS colaborador "
                    + "	,CONVERT(VARCHAR, intervalo15Inicio, 108) AS Saida "
                    + "	,CONVERT(VARCHAR, DATEADD(MINUTE, 15, intervalo15Inicio), 108) AS Volta "
                    + "FROM funcionariosPonto "
                    + "INNER JOIN jsysClientes ON jsysClientes.idCliente = funcionariosPonto.idFuncionario "
                    + "WHERE intervalo15Inicio IS NOT NULL AND intervalo15Fim IS NULL");
            carregaTabala(x, new String[]{"Colaborador", "Saida", "Volta"});
        } catch (SQLException e) {
            Log.registraErro(this.getClass().getName(), "actionConsultaNoIntervalo", e);
        }
    }

    private void actionConsultaForaIntervalo() {
        try {
            ResultSet x = DADOS.execSQL("SELECT nomeCorentista AS colaborador "
                    + "	,CONVERT(VARCHAR, ISNULL(intervalo15Inicio, data), 108) AS Saida "
                    + "	,CONVERT(VARCHAR, ISNULL(intervalo15Fim, data), 108) AS Volta "
                    + "FROM funcionariosPonto "
                    + "INNER JOIN jsysClientes ON jsysClientes.idCliente = funcionariosPonto.idFuncionario "
                    + "WHERE data = dbo.setTimeNull(getdate()) "
                    + "	AND ((funcionariosPonto.intervalo15Inicio IS NULL AND funcionariosPonto.intervalo15Fim IS NULL "
                    + "	) OR (funcionariosPonto.intervalo15Inicio IS NOT NULL "
                    + "	AND funcionariosPonto.intervalo15Fim IS NOT NULL)) ");
            carregaTabala(x, new String[]{"Colaborador", "Saida", "Volta"});
        } catch (SQLException e) {
            Log.registraErro(this.getClass().getName(), "actionConsultaTodos", e);
        }
    }

    private void carregaTabala(ResultSet resultSet, String[] args) throws SQLException {
        DefaultTableModel aModel = new DefaultTableModel(new Object[][]{}, args) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        java.sql.ResultSetMetaData rsmd = resultSet.getMetaData();
        int colNo = rsmd.getColumnCount();
        while (resultSet.next()) {
            Object[] objects = new Object[colNo];
            for (int i = 0; i < colNo; i++) {
                objects[i] = resultSet.getObject(i + 1);
            }
            aModel.addRow(objects);
        }
        jTable1.setModel(aModel);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(370);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(75);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(75);
        jTable1.setDefaultRenderer(Object.class, new ColorirPonto15());
    }
}
