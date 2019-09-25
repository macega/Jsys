package br.sql.janelas.caixa;

import br.sql.acesso.SQLDatabaseConnection;
import br.sql.bean.JsysClientes;
import br.sql.bean.JsysOrcamento;
import br.sql.buscas.ConsultaClientes;
import br.sql.log.Log;
import br.sql.util.ManagerData;
import br.sql.util.Retorna;
import br.sql.util.Validar;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import jsys.sql.Menu;

/**
 *
 * @author Juliano Alves Medina
 */
public class AlterarClienteVenda extends javax.swing.JDialog {
    
    private static final SQLDatabaseConnection DADOS = new SQLDatabaseConnection();

    /**
     * Creates new form AlterarClienteVenda
     *
     * @param parent
     * @param modal
     */
    public AlterarClienteVenda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldVenda = new javax.swing.JTextField();
        jTextFieldDetalhesVenda = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldCliente = new javax.swing.JTextField();
        jTextFieldDetalhesCliente = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("Cancelar");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Alterar");
        jButton2.setToolTipText("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Alterar Cliente da Venda");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(378, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jLabel2.setText("Venda");

        jTextFieldVenda.setToolTipText("");
        jTextFieldVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldVendaKeyReleased(evt);
            }
        });

        jTextFieldDetalhesVenda.setEditable(false);
        jTextFieldDetalhesVenda.setBackground(new java.awt.Color(255, 255, 204));

        jLabel3.setText("Cliente");

        jTextFieldCliente.setToolTipText("Pressione a tecla F10 para abrir a consulta");
        jTextFieldCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldClienteKeyReleased(evt);
            }
        });

        jTextFieldDetalhesCliente.setEditable(false);
        jTextFieldDetalhesCliente.setBackground(new java.awt.Color(255, 255, 204));
        jTextFieldDetalhesCliente.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldVenda)
                            .addComponent(jTextFieldCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldDetalhesVenda)
                            .addComponent(jTextFieldDetalhesCliente))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDetalhesVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDetalhesCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldVendaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldVendaKeyReleased
        consultaVenda();
    }//GEN-LAST:event_jTextFieldVendaKeyReleased

    private void jTextFieldClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldClienteKeyReleased
        consultaCliente();
    }//GEN-LAST:event_jTextFieldClienteKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (venda != null & cliente != null) {
            StringBuilder sql = new StringBuilder();
            sql.append("AjusteVendaCliente(");
            sql.append(cliente.getIdCliente()).append(", ");
            sql.append(venda.getIdOrcamento()).append(", ");
            sql.append(Menu.getNomeUsuario()).append("");
            sql.append(")");
            if (DADOS.execProcudureRetInt(sql) == 1) {
                JOptionPane.showMessageDialog(this, "Alteração Finalizada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Verifique os dados na Janela  ", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextFieldClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F10) {
            ConsultaClientes cc = new ConsultaClientes(null, true, jTextFieldCliente.getText());
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            cc.setBounds(0, 0, screenSize.width, screenSize.height);
            cc.setVisible(true);
            setTextCliente(cc.getRetorno());
        }
    }//GEN-LAST:event_jTextFieldClienteKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldCliente;
    private javax.swing.JTextField jTextFieldDetalhesCliente;
    private javax.swing.JTextField jTextFieldDetalhesVenda;
    private javax.swing.JTextField jTextFieldVenda;
    // End of variables declaration//GEN-END:variables

    private JsysOrcamento venda;
    private JsysClientes cliente;

    public void setTextVenda(String venda) {
        jTextFieldVenda.setText(venda);
        consultaVenda();
    }

    public void setTextCliente(String cliente) {
        jTextFieldCliente.setText(cliente);
        consultaCliente();
    }

    private void consultaVenda() {
        if (Validar.isNotNullOrWhite(jTextFieldVenda.getText())) {
            consultaVenda(Integer.parseInt(jTextFieldVenda.getText().trim()));
        }
    }

    private void consultaVenda(Integer idOrcamento) {
        try {
            Map<Object, Object> filtro = new HashMap<>();
            filtro.put("idOrcamento", idOrcamento);
            venda = (JsysOrcamento) Retorna.findOneResult("JsysOrcamento.findByFechado", filtro);
            if (venda != null) {
                StringBuilder str = new StringBuilder();
                str.append("Data: ").append(ManagerData.convertBrDate(venda.getData())).append("; ");
                str.append("Valor: ").append(venda.getValorLiquido().setScale(2)).append("; ");
                str.append("Vendedor: ").append(venda.getIdFuncionario().getNomeCorentista()).append(".");
                jTextFieldDetalhesVenda.setText(str.toString());
            } else {
                jTextFieldDetalhesVenda.setText("Venda não encontrada");
            }
        } catch (Exception e) {
            Log.registraErro(this.getClass().getName(), "consultaVenda", e);
        }
    }

    private void consultaCliente() {
        if (Validar.isNotNullOrWhite(jTextFieldCliente.getText())) {
            consultaCliente(Integer.parseInt(jTextFieldCliente.getText().trim()));
        }
    }

    private void consultaCliente(Integer idCliente) {
        try {
            Map<Object, Object> filtro = new HashMap<>();
            filtro.put("idCliente", idCliente);
            cliente = (JsysClientes) Retorna.findOneResult("JsysClientes.findByIdClienteIsAtivo", filtro);
            if (cliente != null) {
                StringBuilder str = new StringBuilder();
                str.append(cliente.getNomeCorentista()).append("; ");
                str.append("CPF/CNPJ: ").append(cliente.getCnpjCpf()).append(".");
                jTextFieldDetalhesCliente.setText(str.toString());
            } else {
                jTextFieldDetalhesCliente.setText("Cliente não encontrado");
            }
        } catch (Exception e) {
            Log.registraErro(this.getClass().getName(), "consultaVenda", e);
        }
    }
}
