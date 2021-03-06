/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.janelas.ponto;

import br.sql.bean.JsysClientesIds;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Juliano Alves Medina
 */
public class PontoJanelaConsulta extends javax.swing.JDialog {

    /**
     * Creates new form PontoJanelaConsulta
     *
     * @param parent
     * @param modal
     */
    public PontoJanelaConsulta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
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
        jTextFieldCartao = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFocusable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Passe o Cartão");
        jLabel1.setToolTipText("");
        jLabel1.setFocusable(false);

        jTextFieldCartao.setToolTipText("");
        jTextFieldCartao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCartaoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldCartao)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldCartao, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldCartaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCartaoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            setCorentista();
        }
    }//GEN-LAST:event_jTextFieldCartaoKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PontoJanelaConsulta dialog = new PontoJanelaConsulta(new javax.swing.JFrame(), true);
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextFieldCartao;
    // End of variables declaration//GEN-END:variables

    protected JsysClientesIds corentista;

    public JsysClientesIds getCorentista() {
        return corentista;
    }

    public void setCorentista() {
        Map<Object, Object> filtro = new HashMap<>();
        filtro.put("id", jTextFieldCartao.getText().trim());
        br.sql.bean.JsysClientesIds c = (br.sql.bean.JsysClientesIds) br.sql.util.Retorna.findOneResult("JsysClientesIds.findById", filtro);
        if (c != null) {
            this.corentista = c;
        } else {
            this.corentista = null;
        }
        this.dispose();
    }

}
