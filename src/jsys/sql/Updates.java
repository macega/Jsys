package jsys.sql;

import br.JavaApplicationJsys;
import br.adp.util.ExecutaQuery;
import br.sql.log.Log;
import br.sql.util.EXEFileInfo;
import br.sql.util.FileEx;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Juliano Alves Medina
 */
public class Updates extends javax.swing.JDialog {

    public static String localInstalador;

    /**
     * Creates new form Updates
     *
     * @param parent
     * @param modal
     */
    public Updates(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        caregaDados();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Informações e Atualizações");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Informações e Atualizações");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(658, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jButton1.setText("Fechar");
        jButton1.setFocusable(false);
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
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    private void caregaDados() {
        FileEx file = new FileEx("updates.txt");
        try {
            jTextArea1.setText(file.stringRead());
        } catch (FileNotFoundException e) {
            Log.registraErro(this.getClass().getName(), "caregaDados", e);
        }
    }

    public static void verificaVersao() {
        try {
            ResultSet rs = ExecutaQuery.executesqlScriptRS(
                    "select localinstalador from jsysparametros",
                    JavaApplicationJsys.getBaseDefault()
            );
            while (rs.next()) {
                localInstalador = rs.getString(1);
                if (EXEFileInfo.getMajorVersionOfProgram(localInstalador) != JavaApplicationJsys.MAJOR
                        | EXEFileInfo.getMinorVersionOfProgram(localInstalador) != JavaApplicationJsys.MINOR
                        | EXEFileInfo.getBuildOfProgram(localInstalador) != JavaApplicationJsys.BUILD
                        | EXEFileInfo.getRevisionOfProgram(localInstalador) != JavaApplicationJsys.REVISION) {
                    instalar();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            Log.registraErro("Updates", "verificaVersao", e);
        }
    }

    public static void instalar() {
        try {
            if (!"".equals(localInstalador)) {
                /**
                 * /SILENT	Runs the installer in silent mode (The progress
                 * window is displayed) /VERYSILENT Very silent mode No windows
                 * are displayed /SUPPRESSMSGBOXES Suppress message boxes Only
                 * has an effect when combined with '/SILENT' and '/VERYSILENT'
                 * /NOCANCEL Disables cancelling the installation process
                 * /NORESTART Prevents installer from restarting the system even
                 * if it's necessary /DIR="x:\dirname" Overrides the default
                 * install directory.
                 */
                Process p = Runtime.getRuntime().exec(localInstalador + " /SILENT");
                if (p.isAlive()) {
                    System.exit(0);
                }
            }
        } catch (IOException ignore) {
        }
    }
}
