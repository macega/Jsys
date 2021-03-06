package br.sql.cadastros;

import br.sql.acesso.SQLDatabaseConnection;
import br.sql.bean.JsysNFeTransportadoras;
import br.sql.bean.JsysParametros;
import br.sql.log.Log;
import br.sql.defaultTableCellRenderer.Zebrado;
import br.sql.util.ManagerSQL;
import br.sql.util.Retorna;
import com.sun.glass.events.KeyEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juliano Alves Medina
 */
public class TransportadorasJanela extends javax.swing.JFrame {
    
        private static final SQLDatabaseConnection DADOS = new SQLDatabaseConnection();

    /**
     * Creates new form TransportadorasJanela
     */
    public TransportadorasJanela() {
        initComponents();
        actionConsulta();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldFiltro = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de Transportadoras");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Lista de Transportadoras");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Procurar");
        jLabel2.setToolTipText("");

        jTextFieldFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldFiltroKeyPressed(evt);
            }
        });

        jButton2.setText("Editar");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jButton2, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Nova");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldFiltro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldFiltroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFiltroKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            actionConsulta();
        }
    }//GEN-LAST:event_jTextFieldFiltroKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JsysNFeTransportadoras t =  new JsysNFeTransportadoras();
        JsysParametros par = Retorna.JsysParametros();
        t.setUf(par.getUf());
        t.setXMun(par.getCidade());
        TransportadorasJanelaEdit tje = new TransportadorasJanelaEdit(new javax.swing.JFrame(), true, t);
        tje.setVisible(true);
        if (tje.isConfirma()) {
            br.sql.acesso.ConnectionFactory.insert(tje.getJsysNFeTransportadoras());
            jTextFieldFiltro.setText(tje.getJsysNFeTransportadoras().getXNome());
            actionConsulta();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        java.util.Map<Object, Object> filtro = new java.util.HashMap<>();
        filtro.put("id", Integer.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
        JsysNFeTransportadoras C = (JsysNFeTransportadoras) Retorna.findOneResult("JsysNFeTransportadoras.findById", filtro);
        TransportadorasJanelaEdit tje = new TransportadorasJanelaEdit(new java.awt.Frame(), true, C);
        tje.setVisible(true);
        if (tje.isConfirma()) {
            br.sql.acesso.ConnectionFactory.update(tje.getJsysNFeTransportadoras());
            jTextFieldFiltro.setText(tje.getJsysNFeTransportadoras().getXNome());
            actionConsulta();
        } else {
            actionConsulta();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        actionConsulta();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldFiltro;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private void actionConsulta() {
        try {
            ManagerSQL sql = new ManagerSQL();
            String[] campos;
            String[] nomeCampos;
            nomeCampos = new String[]{"Código", "Transportadora", "CNPJ", "IE", "Logradouro", "Cidade", "UF", "OBS", "Fone", "E-Mail"};
            campos = new String[]{"id", "xNome", "CnpjCpf", "ieRg", "xEnder", "xMun", "UF", "obs", "fone", "email"};
            sql.setTabela("JsysNFeTransportadoras");

            sql.setCampos(campos);
            sql.setOrder(new String[]{"JsysNFeTransportadoras.xNome"});
            ResultSet x = DADOS.execSQL(sql.getSQL(jTextFieldFiltro.getText()));
            // Definição do TableModel
            DefaultTableModel aModel = new DefaultTableModel(new Object[][]{}, nomeCampos) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            ResultSetMetaData rsmd = x.getMetaData();
            int colNo = rsmd.getColumnCount();
            while (x.next()) {
                Object[] objects = new Object[colNo];
                for (int i = 0; i < colNo; i++) {
                    objects[i] = x.getObject(i + 1);
                }
                aModel.addRow(objects);
            }
            jTable1.setModel(aModel);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(53);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(249);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(73);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(128);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(42);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(184);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(83);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(200);
            jTable1.setDefaultRenderer(Object.class, new Zebrado());
        } catch (SQLException e) {
            Log.registraErro(this.getClass().getName(), "actionConsulta", e);
        }
    }
}