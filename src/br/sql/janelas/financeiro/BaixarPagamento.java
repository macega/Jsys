package br.sql.janelas.financeiro;

import br.sql.acesso.ConnectionFactory;
import br.sql.bean.JsysTitulos;
import br.sql.bean.Pagamentos;
import br.sql.bean.geral.pagamentosMarcados;
import br.sql.log.Log;
import br.sql.util.ManagerData;
import br.sql.util.ManagerDecimal;
import br.sql.util.Retorna;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.RollbackException;
import jsys.sql.Menu;

/**
 *
 * @author Juliano Alves Medina
 */
public final class BaixarPagamento extends javax.swing.JDialog {

    /**
     * Creates new form BaixarPagamento
     *
     * @param parent
     * @param modal
     */
    public BaixarPagamento(java.awt.Frame parent, boolean modal) {
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
        jLabel1 = new javax.swing.JLabel();
        jTextFieldTotalTitulos = new javax.swing.JTextField();
        jTextFieldRestante = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldValorPago = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jDateChooserData = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        uJComboBoxTitulo = new componentes.UJComboBox();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("Cancelar");
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

        jLabel1.setText("Total Titulos");
        jLabel1.setToolTipText("");

        jTextFieldTotalTitulos.setBackground(new java.awt.Color(255, 255, 102));
        jTextFieldTotalTitulos.setDocument(new br.sql.plainDocument.MoedaDocument(16)
        );
        jTextFieldTotalTitulos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jTextFieldRestante.setBackground(new java.awt.Color(255, 255, 102));
        jTextFieldRestante.setDocument(new br.sql.plainDocument.MoedaDocument(16));
        jTextFieldRestante.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel2.setText("Total Restante");
        jLabel2.setToolTipText("");

        jTextFieldValorPago.setDocument(new br.sql.plainDocument.MoedaDocument(16));
        jTextFieldValorPago.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel3.setText("Valor Pago");

        jLabel4.setText("Data Baixa");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Efetuar Pagamento");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jLabel6.setText("Titulo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(uJComboBoxTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jDateChooserData, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 23, Short.MAX_VALUE))
                            .addComponent(jTextFieldRestante, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(jTextFieldTotalTitulos)
                            .addComponent(jTextFieldValorPago, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooserData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uJComboBoxTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTotalTitulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRestante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
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
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        BigDecimal saldo = ManagerDecimal.StringToBigDecimal(jTextFieldValorPago.getText());
        for (pagamentosMarcados pm : listPagamentosMarcados) {
            Pagamentos p = pm.getPag();
            BigDecimal valorPago = p.getValorTitulo();
            saldo = saldo.subtract(p.getValorTitulo());
            BigDecimal restante = valorPago.subtract(p.getValorTitulo());
            try {
                if (saldo.signum() != -1) {
                    p.setValorPago(valorPago);
                    p.setRestante(restante);
                    p.setIdTitulo(((JsysTitulos) uJComboBoxTitulo.getSelectedObject()).getIdTitulo());
                    p.setDataPagamento(jDateChooserData.getDate());
                    p.setDataUltimoPgto(jDateChooserData.getDate());
                    p.setUsuarioAlteracao(Menu.getNomeUsuario());
                    p.setDataAlteracao(ManagerData.getDate());
                    p.setIdTituloBaixa(((JsysTitulos) uJComboBoxTitulo.getSelectedObject()).getIdTitulo());
                    p.setIdBanco(Menu.getIdBanco());
                    p.setQuitado(true);
                    ConnectionFactory.update(p);
                }
            } catch (RollbackException e) {
                saldo = new BigDecimal(-1);
                Log.registraErro(this, "jButton2ActionPerformed", e);
            }
        }
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooserData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldRestante;
    private javax.swing.JTextField jTextFieldTotalTitulos;
    private javax.swing.JTextField jTextFieldValorPago;
    private componentes.UJComboBox uJComboBoxTitulo;
    // End of variables declaration//GEN-END:variables

    private List<pagamentosMarcados> listPagamentosMarcados = new ArrayList<>();

    public List<pagamentosMarcados> getListPagamentosMarcados() {
        return listPagamentosMarcados;
    }

    public void setListPagamentosMarcados(List<pagamentosMarcados> listPagamentosMarcados) {
        this.listPagamentosMarcados = listPagamentosMarcados;
        List<Object> O = Retorna.findList("JsysTitulos.findAllAtivos");
        for (Object o : O) {
            JsysTitulos t = (JsysTitulos) o;
            uJComboBoxTitulo.addItem(t.getIdTitulo(), t);
        }
        jDateChooserData.setDate(ManagerData.getDate());
        BigDecimal totalTitulos = BigDecimal.ZERO;
        BigDecimal totalRestante = BigDecimal.ZERO;
        for (pagamentosMarcados p : listPagamentosMarcados) {
            totalTitulos = totalTitulos.add(p.getPag().getValorTitulo());
            totalRestante = totalRestante.add(p.getPag().getRestante());
        }
        jTextFieldTotalTitulos.setText(ManagerDecimal.converter(totalTitulos, "#,##0.00").replace(",", "").replace(".", ""));
        jTextFieldRestante.setText(ManagerDecimal.converter(totalRestante, "#,##0.00").replace(",", "").replace(".", ""));
        jTextFieldValorPago.setText(ManagerDecimal.converter(totalRestante, "#,##0.00").replace(",", "").replace(".", ""));
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
