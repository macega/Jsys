/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.util;

import java.util.GregorianCalendar;
import javax.swing.SwingWorker;
//import org.joda.time.DateTime;
//import org.joda.time.DateTimeComparator;

/**
 *
 * @author Juliano Alves Medina
 */
public class fechaAuto extends javax.swing.JDialog {

    //private DateTime data1 = null;
    private final SwingWorker w;

    /**
     * Creates new form fechaAuto
     *
     * @param parent
     * @param modal
     */
    public fechaAuto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
        gc.setTime(ManagerData.getDate());
        gc.add(GregorianCalendar.MINUTE, 1);
        //data1 = new DateTime(gc.getTime());

        this.w = new SwingWorker() {
            @Override
            protected Void doInBackground() throws Exception {
//                while (true) {
//                    DateTime data2 = new DateTime(ManagerData.getDate());
//                    StringBuilder tempo = new StringBuilder();
//                    tempo.append(ManagerString.zeros(String.valueOf(org.joda.time.Seconds.secondsBetween(data1, DateTime.now()).getSeconds() / 60), 2))
//                            .append(":")
//                            .append(ManagerString.zeros(String.valueOf(org.joda.time.Seconds.secondsBetween(data1, DateTime.now()).getSeconds() % 60), 2));
//                    timeTF.setText(tempo.toString());
//                    switch (DateTimeComparator.getInstance().compare(data1, data2)) {
//                        case -1: //  "data1 e menor que data2"
//                            System.exit(0);
//                            break;
//                        default:
//                            break;
//                    }
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        br.sql.log.Log.registraErro("fechaAuto", "fechaAuto", e);
//                    }
//                }
                return null;
            }
        };
        initComponents();
        w.execute();
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
        timeTF = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sistema está sendo Finalizado");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sistema está sendo Finalizado");
        jLabel1.setToolTipText("");

        timeTF.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        timeTF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeTF.setText("00:00");
        timeTF.setToolTipText("");

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timeTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeTF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //jsys.sql.Menu.setFechaAuto(getDataAtualTypeDate());
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     *
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                fechaAuto dialog = new fechaAuto(new javax.swing.JFrame(), true);
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel timeTF;
    // End of variables declaration//GEN-END:variables
}
