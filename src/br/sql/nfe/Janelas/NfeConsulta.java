package br.sql.nfe.Janelas;

import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TProtNFe;
import br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TRetConsSitNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.sql.bean.JsysNFe;
import br.sql.log.Log;
import br.sql.nfe.util.XmlUtil;
import br.sql.util.Retorna;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import javax.swing.SwingWorker;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Juliano Alves Medina
 */
public class NfeConsulta extends javax.swing.JDialog implements PropertyChangeListener {

    private Integer idVenda = 0;

    /**
     * Creates new form nfeConsulta
     *
     * @param parent
     * @param modal
     */
    public NfeConsulta(java.awt.Frame parent, boolean modal) {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldChaveAcesso = new javax.swing.JTextField();
        startButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaObs = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        venda = new javax.swing.JTextField();
        data = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        motivo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        verAplic = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        nProt = new javax.swing.JTextField();
        jButtonImprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta NF-e & NFC-e");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Consulta NF-e & NFC-e");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel2.setText("Chave de Acesso");
        jLabel2.setToolTipText("");

        jTextFieldChaveAcesso.setDocument(new br.sql.plainDocument.IntegerDocument(44)
        );
        jTextFieldChaveAcesso.setToolTipText("");

        startButton.setText("Consultar");
        startButton.setToolTipText("");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        jTextAreaObs.setColumns(20);
        jTextAreaObs.setRows(5);
        jScrollPane1.setViewportView(jTextAreaObs);

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Cupom"));

        jLabel3.setText("Venda");

        venda.setEditable(false);
        venda.setBackground(new java.awt.Color(255, 255, 204));
        venda.setToolTipText("");

        data.setEditable(false);
        data.setBackground(new java.awt.Color(255, 255, 204));
        data.setToolTipText("");

        jLabel4.setText("Data");
        jLabel4.setToolTipText("");

        jLabel5.setText("Motivo");

        motivo.setEditable(false);
        motivo.setBackground(new java.awt.Color(255, 255, 204));
        motivo.setToolTipText("");

        jLabel6.setText("Verçao Aplicaçao");
        jLabel6.setToolTipText("");

        verAplic.setEditable(false);
        verAplic.setBackground(new java.awt.Color(255, 255, 204));
        verAplic.setToolTipText("");

        jLabel7.setText("Número do Protocolo da NF-e");
        jLabel7.setToolTipText("");

        nProt.setEditable(false);
        nProt.setBackground(new java.awt.Color(255, 255, 204));
        nProt.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(venda, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(motivo)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(verAplic, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 262, Short.MAX_VALUE))
                            .addComponent(nProt))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(venda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(motivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(verAplic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nProt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonImprimir.setText("Imprimir NFC-e");
        jButtonImprimir.setEnabled(false);
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
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
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldChaveAcesso)
                        .addGap(18, 18, 18)
                        .addComponent(startButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonImprimir)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldChaveAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButtonImprimir))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        startButton.setEnabled(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        String s = new String();
        jTextAreaObs.setText(s);
        venda.setText(s);
        data.setText(s);
        motivo.setText(s);
        verAplic.setText(s);
        nProt.setText(s);
        jButtonImprimir.setEnabled(false);
        task = new Task();
        task.addPropertyChangeListener(this);
        task.execute();
    }//GEN-LAST:event_startButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        NfceEmissao nfce = new NfceEmissao(null, true);
        nfce.setRecebeVenda(idVenda);
        nfce.setVisible(true);
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField data;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaObs;
    private javax.swing.JTextField jTextFieldChaveAcesso;
    private javax.swing.JTextField motivo;
    private javax.swing.JTextField nProt;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton startButton;
    private javax.swing.JTextField venda;
    private javax.swing.JTextField verAplic;
    // End of variables declaration//GEN-END:variables

    private Task task;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress".equals(evt.getPropertyName())) {
            progressBar.setValue((Integer) evt.getNewValue());
        }
    }

    private class Task extends SwingWorker<Boolean, String> {

        @Override
        protected Boolean doInBackground() {
            try {
                //br.JavaApplicationJsys.iniciaConfigurações();
                ConfiguracoesNfe config = br.JavaApplicationJsys.iniciaConfigurações(Retorna.JsysParametros());
                publish("Iniciando consulta no Banco de dados da Sefaz");
                setProgress(10);
                String chaveAcesso = jTextFieldChaveAcesso.getText();
                
                System.out.println( DocumentoEnum.getByModelo(chaveAcesso.substring(20, 22)) );
                
                TRetConsSitNFe tRetConsSitNFe = Nfe.consultaXml(
                        config,
                        chaveAcesso,
                        DocumentoEnum.getByModelo(chaveAcesso.substring(20, 22)));

                publish("Analisando dados da Sefaz");
                setProgress(25);
                publish(tRetConsSitNFe.getXMotivo());
                if (tRetConsSitNFe.getProtNFe() != null
                        && XmlUtil.verificaCsStat(tRetConsSitNFe.getProtNFe().getInfProt().getCStat())) {
                    setProgress(40);
                    TRetEnviNFe tRetEnviNFe = new TRetEnviNFe();
                    tRetEnviNFe.setVersao(tRetConsSitNFe.getVersao());
                    tRetEnviNFe.setTpAmb(tRetConsSitNFe.getProtNFe().getInfProt().getTpAmb());
                    tRetEnviNFe.setVerAplic(tRetConsSitNFe.getProtNFe().getInfProt().getVerAplic());
                    tRetEnviNFe.setCStat("104");
                    tRetEnviNFe.setXMotivo("Lote processado");
                    tRetEnviNFe.setCUF(tRetConsSitNFe.getCUF());
                    tRetEnviNFe.setDhRecbto(tRetConsSitNFe.getProtNFe().getInfProt().getDhRecbto().toXMLFormat());
                    br.com.swconsultoria.nfe.schema_4.enviNFe.TProtNFe tProtNFe = new br.com.swconsultoria.nfe.schema_4.enviNFe.TProtNFe();
                    tProtNFe.setVersao(tRetConsSitNFe.getProtNFe().getVersao());
                    TProtNFe.InfProt infProt = new TProtNFe.InfProt();
                    infProt.setTpAmb(tRetConsSitNFe.getProtNFe().getInfProt().getTpAmb());
                    infProt.setVerAplic(tRetConsSitNFe.getProtNFe().getInfProt().getVerAplic());
                    infProt.setChNFe(tRetConsSitNFe.getProtNFe().getInfProt().getChNFe());
                    infProt.setDhRecbto(tRetConsSitNFe.getProtNFe().getInfProt().getDhRecbto().toXMLFormat());
                    infProt.setNProt(tRetConsSitNFe.getProtNFe().getInfProt().getNProt());
                    infProt.setDigVal(tRetConsSitNFe.getProtNFe().getInfProt().getDigVal());
                    infProt.setCStat(tRetConsSitNFe.getProtNFe().getInfProt().getCStat());
                    infProt.setXMotivo(tRetConsSitNFe.getProtNFe().getInfProt().getXMotivo());
                    tProtNFe.setInfProt(infProt);
                    tRetEnviNFe.setProtNFe(tProtNFe);
                    publish("Consultado Bando de dados");
                    setProgress(55);
                    java.util.Map<Object, Object> filtro = new HashMap<>();
                    filtro.put("chaveAcesso", "NFe" + tRetConsSitNFe.getChNFe());
                    JsysNFe nfe = (JsysNFe) Retorna.findOneResult("JsysNFe.findByChaveAcesso", filtro);
                    setProgress(70);
                    if (nfe != null) {
                        try {
                            publish("NF-e Encontrada");
                            setProgress(85);
                            nfe.setEmitida(true);
                            String cStat = tRetConsSitNFe.getCStat();
                            nfe.setCancelada(("101".equals(cStat) | "151".equals(cStat)));
                            nfe.setRetConsReciNFe(XmlUtil.objectToXml(tRetEnviNFe));
                            if (br.sql.acesso.ConnectionFactory.update(nfe) instanceof JsysNFe) {
                                publish("NF-e Registrada na Banco de dados");
                                setProgress(100);
                                venda.setText(String.valueOf(nfe.getVenda()));
                                data.setText(tRetConsSitNFe.getDhRecbto());
                                motivo.setText(tRetConsSitNFe.getProtNFe().getInfProt().getXMotivo());
                                verAplic.setText(tRetConsSitNFe.getVerAplic());
                                nProt.setText(tRetConsSitNFe.getProtNFe().getInfProt().getNProt());
                                idVenda = nfe.getVenda();
                                jButtonImprimir.setEnabled(true);
                                return true;
                            } else {
                                publish("Não foi possível gravar os dados no banco de dados");
                                setProgress(0);
                                return false;
                            }
                        } catch (JAXBException e) {
                            publish("NF-e encontrada");
                            Log.registraErro(this.getClass().getName(), "doInBackground0", e);
                        }
                    }
                } else {
                    publish("Erro encontrado processo finalizado.");
                    publish("CStat: " + tRetConsSitNFe.getCStat());
                    publish("XMotivo: " + tRetConsSitNFe.getXMotivo());
                    publish("DhRecbto: " + tRetConsSitNFe.getDhRecbto());
                    setProgress(100);
                }
                publish("Erro encontrado processo finalizado.");
                setProgress(100);
                return false;
            } catch (CertificadoException | NfeException | FileNotFoundException e) {
                Log.registraErro(this.getClass().getName(), "doInBackground1", e);
                return false;
            }
        }

        @Override
        protected void process(List<String> chunks) {
            super.process(chunks);
            chunks.stream().forEach((s) -> {
                jTextAreaObs.append(String.format(s + System.getProperty("line.separator")));
            });
        }

        @Override
        protected void done() {
            Toolkit.getDefaultToolkit().beep();
            startButton.setEnabled(true);
            setCursor(null); //turn off the wait cursor
        }
    }
}
