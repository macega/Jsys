package br.rel.filtros;

import br.sql.bean.JsysClientes;
import br.sql.util.ManagerData;
import br.sql.util.ReportUtils;
import br.sql.util.Retorna;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Juliano Alves Medina
 */
public final class FiltroDataCheques extends javax.swing.JDialog {

    /**
     * Creates new form FiltroDataCheques
     *
     * @param parent
     * @param modal
     * @param args
     */
    public FiltroDataCheques(java.awt.Frame parent, boolean modal, String[] args) {
        super(parent, modal);
        initComponents();
        setRelatorio(args);
        setFiltroData();
        caregarDados();
        //dataInicialjDateChooser.setDate(ManagerData.getPrimeiroDiaDoMes(ManagerData.getDataAtualTypeDate()));
        //dataFinaljDateChooser.setDate(ManagerData.setHoraFimDia(ManagerData.getDataAtualTypeDate()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        dataInicialjDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        dataFinaljDateChooser = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        pagosCB = new javax.swing.JCheckBox();
        emitidosCB = new javax.swing.JCheckBox();
        forncedorCB = new componentes.UJComboBox();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        fornecedoresTA = new javax.swing.JTextArea();
        jCheckBoxDevolvidos = new javax.swing.JCheckBox();
        jCheckBoxCancelado = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Filtro Relatório");
        setResizable(false);

        jLabel1.setText("Data Inicial");

        jLabel2.setText("Data Final");

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

        pagosCB.setText("Pagos");
        pagosCB.setToolTipText("");

        emitidosCB.setSelected(true);
        emitidosCB.setText("Emitidos");

        forncedorCB.setAutocompletar(true);

        jLabel3.setText("Fornecedor");
        jLabel3.setToolTipText("");

        jButton3.setText("Adicionar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        fornecedoresTA.setColumns(20);
        fornecedoresTA.setRows(5);
        jScrollPane1.setViewportView(fornecedoresTA);

        jCheckBoxDevolvidos.setText("Devolvidos");
        jCheckBoxDevolvidos.setToolTipText("");

        jCheckBoxCancelado.setText("Cancelado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(399, 399, 399)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(forncedorCB, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dataInicialjDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(dataFinaljDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(emitidosCB)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pagosCB)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBoxDevolvidos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBoxCancelado)))
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(forncedorCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataInicialjDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataFinaljDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emitidosCB)
                            .addComponent(pagosCB)
                            .addComponent(jCheckBoxDevolvidos)
                            .addComponent(jCheckBoxCancelado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Map<Object, Object> parametros = new HashMap<>();
        parametros.put("dataInicial", ManagerData.setHoraInicioDia(dataInicialjDateChooser.getDate()));
        parametros.put("dataFinal", ManagerData.setHoraFimDia(dataFinaljDateChooser.getDate()));
        parametros.put("emitidos", emitidosCB.isSelected());
        parametros.put("quitados", pagosCB.isSelected());
        parametros.put("devolvidos", jCheckBoxDevolvidos.isSelected());
        parametros.put("cancelado", jCheckBoxCancelado.isSelected());
        parametros.put("idFornecedor", fornecedoresTA.getText());
        ReportUtils.openReport("Relação de Cheques", getRelatorio()[0], parametros);
        //this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        fornecedoresTA.setText(fornecedoresTA.getText() + "," + getFornecedor().getIdCliente());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(final String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FiltroDataCheques dialog = new FiltroDataCheques(new javax.swing.JFrame(), false, args);
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dataFinaljDateChooser;
    private com.toedter.calendar.JDateChooser dataInicialjDateChooser;
    private javax.swing.JCheckBox emitidosCB;
    private componentes.UJComboBox forncedorCB;
    private javax.swing.JTextArea fornecedoresTA;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBoxCancelado;
    private javax.swing.JCheckBox jCheckBoxDevolvidos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox pagosCB;
    // End of variables declaration//GEN-END:variables

    private String[] relatorio;

    public String[] getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(String relatorio[]) {
        this.relatorio = relatorio;
    }

    public void setFiltroData() {
        if (this.relatorio[1].equals("DIA")) {
            dataInicialjDateChooser.setDate(ManagerData.setHoraInicioDia(ManagerData.getDate()));
            dataFinaljDateChooser.setDate(ManagerData.setHoraFimDia(ManagerData.getDate()));
        }
        if (this.relatorio[1].equals("MES")) {
            dataInicialjDateChooser.setDate(ManagerData.getPrimeiroDiaDoMes(ManagerData.getDate()));
            dataFinaljDateChooser.setDate(ManagerData.setHoraFimDia(ManagerData.getDate()));
        }
        if (this.relatorio[1].equals("ANO")) {
            dataInicialjDateChooser.setDate(ManagerData.getPrimeiroDiaDoAno(ManagerData.getDate()));
            dataFinaljDateChooser.setDate(ManagerData.setHoraFimDia(ManagerData.getDate()));
        }
    }

    private void caregarDados() {
        for (Object O : Retorna.findList("JsysClientes.findAllAtivos")) {
            JsysClientes f = (JsysClientes) O;
            forncedorCB.addItem(f.getNomeCorentista() + " - " + f.getIdCliente(), f);
        }
    }

    private JsysClientes getFornecedor() {
        try {
            return (JsysClientes) forncedorCB.getSelectedObject();
        } catch (Exception e) {
            return null;
        }
    }
}
