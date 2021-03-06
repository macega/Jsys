package br.sql.cadastros;

import br.sql.bean.Cidades;
import br.sql.bean.JsysNFeTransportadoras;
import br.sql.log.Log;
import br.sql.util.ManagerString;
import br.sql.util.Retorna;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Juliano Alves Medina
 */
public class TransportadorasJanelaEdit extends javax.swing.JDialog {

    private final JsysNFeTransportadoras t;

    /**
     * Creates new form TransportadorasJanelaEdit
     *
     * @param parent
     * @param modal
     * @param t
     */
    public TransportadorasJanelaEdit(java.awt.Frame parent, boolean modal, JsysNFeTransportadoras t) {
        super(parent, modal);
        initComponents();
        this.confirma = false;
        this.t = t;
        caregarDados();
        jTextFieldNome.requestFocus();
    }

    protected boolean confirma;

    public boolean isConfirma() {
        return confirma;
    }

    public void setConfirma(boolean confirma) {
        this.confirma = confirma;
    }

    public JsysNFeTransportadoras getJsysNFeTransportadoras() {
        return t;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jTextFieldCnpjCpf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldIeRg = new javax.swing.JTextField();
        isentoCB = new javax.swing.JCheckBox();
        jTextFieldLogradouro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxUF = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        uJComboBoxCidade = new componentes.UJComboBox();
        jTextFieldFone = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaObs = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Cadastro de Transportadoras");
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

        jLabel2.setText("Nome");

        jTextFieldNome.setToolTipText("");

        jTextFieldCnpjCpf.setDocument(new br.sql.plainDocument.CpfCnpjDocument());

        jLabel3.setText("CNPJ");
        jLabel3.setToolTipText("");

        jButton1.setText("Cancelar");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Confirmar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("IE");

        jTextFieldIeRg.setToolTipText("");

        isentoCB.setText("Isento");
        isentoCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                isentoCBItemStateChanged(evt);
            }
        });

        jTextFieldLogradouro.setToolTipText("");

        jLabel5.setText("Logradouro");
        jLabel5.setToolTipText("");

        jLabel6.setText("UF");
        jLabel6.setToolTipText("");

        jComboBoxUF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RO", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RR", "SC", "SP", "SE", "TO", "EX" }));
        jComboBoxUF.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxUFItemStateChanged(evt);
            }
        });

        jLabel7.setText("Município");
        jLabel7.setToolTipText("");

        uJComboBoxCidade.setAutocompletar(true);

        jTextFieldFone.setDocument(new br.sql.plainDocument.FoneDocument());
        jTextFieldFone.setToolTipText("");

        jLabel8.setText("Fone");

        jLabel9.setText("Email");
        jLabel9.setToolTipText("");

        jLabel10.setText("Obs");
        jLabel10.setToolTipText("");

        jTextAreaObs.setColumns(20);
        jTextAreaObs.setRows(5);
        jScrollPane1.setViewportView(jTextAreaObs);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNome)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldCnpjCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(isentoCB))
                            .addComponent(jTextFieldIeRg, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldLogradouro)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(uJComboBoxCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldFone, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(0, 214, Short.MAX_VALUE))
                            .addComponent(jTextFieldEmail)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCnpjCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(isentoCB)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldIeRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uJComboBoxCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        confirma = false;
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void isentoCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_isentoCBItemStateChanged
        if (isentoCB.isSelected()) {
            jTextFieldIeRg.setEditable(false);
            jTextFieldIeRg.setText("ISENTO");
        } else {
            jTextFieldIeRg.setEditable(true);
            jTextFieldIeRg.setText("");
        }
    }//GEN-LAST:event_isentoCBItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (t != null) {
            t.setXNome(ManagerString.removeAcentos(jTextFieldNome.getText()) );
            t.setCnpjCpf(jTextFieldCnpjCpf.getText());
            t.setIeRg(jTextFieldIeRg.getText());
            t.setXEnder(ManagerString.removeAcentos(jTextFieldLogradouro.getText()));
            t.setFone(jTextFieldFone.getText());
            t.setEmail(jTextFieldEmail.getText());
            t.setUf(jComboBoxUF.getSelectedItem().toString());
            t.setXMun(((Cidades) uJComboBoxCidade.getSelectedObject()).getMunicipio());
            t.setObs(ManagerString.removeAcentos(jTextAreaObs.getText()));
            confirma = true;
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBoxUFItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxUFItemStateChanged
        setCidade();
    }//GEN-LAST:event_jComboBoxUFItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox isentoCB;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBoxUF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaObs;
    private javax.swing.JTextField jTextFieldCnpjCpf;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldFone;
    private javax.swing.JTextField jTextFieldIeRg;
    private javax.swing.JTextField jTextFieldLogradouro;
    private javax.swing.JTextField jTextFieldNome;
    private componentes.UJComboBox uJComboBoxCidade;
    // End of variables declaration//GEN-END:variables

    private void caregarDados() {
        setCidade();
        if (t != null & t.getId() != null ) {
            jTextFieldNome.setText(t.getXNome());
            jTextFieldCnpjCpf.setText(t.getCnpjCpf());
            jTextFieldIeRg.setText(t.getIeRg());
            jTextFieldLogradouro.setText(t.getXEnder());
            jTextFieldFone.setText(t.getFone());
            jTextFieldEmail.setText(t.getEmail());
            jComboBoxUF.setSelectedItem(t.getUf());
            jTextAreaObs.setText(t.getObs());
        }
    }

    /**
     * carega as cidades do estado selecionado
     *
     * @param uf
     */
    private void setCidade() {
        Map<Object, Object> filtro = new HashMap<>();
        filtro.put("uf", jComboBoxUF.getSelectedItem().toString());
        List<Object> cidadeList = Retorna.findList("Cidades.findByUf", filtro);
        try {
            uJComboBoxCidade.clear();
            for (Object O : cidadeList) {
                Cidades c = (Cidades) O;
                uJComboBoxCidade.addItem(c.getMunicipio(), c);
            }
            if (t.getXMun().equals("")) {
                uJComboBoxCidade.setSelectedIndex(0);
            } else {
                uJComboBoxCidade.setSelectedItem(t.getXMun());
            }
        } catch (Exception e) {
            Log.registraErro(this.getClass().getName(), "setCidade", e);
        }
    }
}
