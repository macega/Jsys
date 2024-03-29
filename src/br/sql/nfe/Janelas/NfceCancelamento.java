package br.sql.nfe.Janelas;

import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EventosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEvento;
import br.com.swconsultoria.nfe.util.CancelamentoUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.sql.acesso.ConnectionFactory;
import br.sql.acesso.SQLDatabaseConnection;
import br.sql.bean.JsysNFe;
import br.sql.bean.JsysNFeEvento;
import br.sql.bean.JsysParametros;
import br.sql.nfe.links.ConstantesFiscal;
import br.sql.log.Log;
import br.sql.util.GravaNoArquivo;
import br.sql.util.ManagerData;
import br.sql.util.ManagerString;
import br.sql.util.Retorna;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Juliano Alves Medina
 */
public class NfceCancelamento extends javax.swing.JDialog implements
        PropertyChangeListener {

    private JsysNFe nfe;
    private JsysNFeEvento jsysNFeEvento;
    private final SQLDatabaseConnection dados;
    JsysParametros jsysParametros;
    private Task task;

    /**
     * Creates new form NfceCancelamento
     *
     * @param parent
     * @param modal
     * @param nfe
     */
    public NfceCancelamento(java.awt.Frame parent, boolean modal, JsysNFe nfe) {
        super(parent, modal);
        this.nfe = nfe;
        initComponents();
        dados = new SQLDatabaseConnection();
        caregardados();
        StringBuilder status = new StringBuilder();
        jsysParametros = Retorna.JsysParametros();
        status.append("Servidor NFC-e: ");
        status.append(ConstantesFiscal.statusSefaz(jsysParametros.getcStat65()));
        status.append(" | Servidor NF-e: ");
        status.append(ConstantesFiscal.statusSefaz(jsysParametros.getcStat55()));
        jLabelStatus.setText(status.toString());
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
        jLabelStatus = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldVenda = new javax.swing.JTextField();
        jTextFieldChaveAcesso = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taskOutput = new javax.swing.JTextArea();
        startButton = new javax.swing.JButton();
        jTextFieldMotivo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cancelar NF-e & NFC-e");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Cancelar NF-e & NFC-e");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
                    .addComponent(jLabelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel3.setText("Venda");
        jLabel3.setToolTipText("");

        jTextFieldVenda.setDocument(new br.sql.plainDocument.IntegerDocument(10));
        jTextFieldVenda.setToolTipText("");
        jTextFieldVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldVendaKeyReleased(evt);
            }
        });

        jTextFieldChaveAcesso.setEditable(false);
        jTextFieldChaveAcesso.setBackground(new java.awt.Color(255, 255, 102));
        jTextFieldChaveAcesso.setToolTipText("");

        jLabel4.setText("Chave de Acesso");
        jLabel4.setToolTipText("");

        taskOutput.setBackground(new java.awt.Color(204, 255, 255));
        taskOutput.setColumns(20);
        taskOutput.setRows(5);
        jScrollPane1.setViewportView(taskOutput);

        startButton.setText("Cancelar NF-e ou NFC-e");
        startButton.setToolTipText("");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        jTextFieldMotivo.setDocument(new br.sql.plainDocument.JTextFieldTamanhoMaxDocument(255, true)
        );
        jTextFieldMotivo.setToolTipText("");

        jLabel5.setText("Motivo Cancelamento");
        jLabel5.setToolTipText("");

        jLabel2.setText("Avisos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldChaveAcesso)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextFieldMotivo)))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(startButton))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldChaveAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(startButton)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        if (validar()) {
            startButton.setEnabled(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            /**
             * Instances of javax.swing.SwingWorker are not reusuable, so we
             * create new instances as needed.
             */
            task = new Task();
            task.addPropertyChangeListener(this);
            task.execute();
        }
    }//GEN-LAST:event_startButtonActionPerformed

    private void jTextFieldVendaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldVendaKeyReleased
        try {
            jTextFieldChaveAcesso.setText("");
            nfe = null;
            Integer venda = Integer.valueOf(jTextFieldVenda.getText());
            Map<Object, Object> filtro = new HashMap<>();
            filtro.put("venda", venda);
            JsysNFe jsysNfe = (JsysNFe) Retorna.findOneResult("JsysNFe.findByACancelar", filtro);
            if (jsysNfe != null) {
                nfe = jsysNfe;
                jTextFieldChaveAcesso.setText(nfe.getChaveAcesso());
            }
        } catch (NumberFormatException ignore) {
        }
    }//GEN-LAST:event_jTextFieldVendaKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldChaveAcesso;
    private javax.swing.JTextField jTextFieldMotivo;
    private javax.swing.JTextField jTextFieldVenda;
    private javax.swing.JButton startButton;
    private javax.swing.JTextArea taskOutput;
    // End of variables declaration//GEN-END:variables

    private void caregardados() {
        if (nfe != null) {
            jTextFieldVenda.setText(String.valueOf(nfe.getVenda()));
            jTextFieldChaveAcesso.setText(nfe.getChaveAcesso());
        }
    }

    private boolean validar() {
        if (jTextFieldMotivo.getText().length() < 16) {
            JOptionPane.showMessageDialog(this, "Justificavida de Cancelamento ", "ERRO", JOptionPane.ERROR_MESSAGE);
            jTextFieldMotivo.requestFocus();
            jTextFieldMotivo.selectAll();
            return false;
        } else if (nfe == null) {
            JOptionPane.showMessageDialog(this, "Verifique o Numero da Venda", "ERRO", JOptionPane.ERROR_MESSAGE);
            jTextFieldVenda.requestFocus();
            jTextFieldVenda.selectAll();
            return false;
        }
        return true;
    }

    private class Task extends SwingWorker<Boolean, String> {

        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Boolean doInBackground() {
            Boolean erro = true;
            setProgress(0);
            try {
                jsysNFeEvento = new JsysNFeEvento();
                GravaNoArquivo gravador = new GravaNoArquivo();
                ConfiguracoesNfe config = br.JavaApplicationJsys.iniciaConfigurações(jsysParametros);

                br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe retEnviNFe;
                retEnviNFe = br.com.swconsultoria.nfe.util.XmlNfeUtil.xmlToObject(
                        nfe.getRetConsReciNFe(),
                        br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe.class);
                //Agora o evento pode aceitar uma lista de cancelaemntos para envio em Lote.
                Evento cancela = new Evento();
                //Informe a chave da Nota a ser Cancelada
                cancela.setChave(ManagerString.RemoveFormato(nfe.getChaveAcesso()));
                //Informe o protocolo da Nota a ser Cancelada
                cancela.setProtocolo(retEnviNFe.getProtNFe().getInfProt().getNProt());
                //Informe o CNPJ do emitente
                cancela.setCnpj(ManagerString.RemoveFormatoCpfCnpj(jsysParametros.getCnpj()));
                //Informe o Motivo do Cancelamento
                cancela.setMotivo(jTextFieldMotivo.getText().trim());
                //Informe a data do Cancelamento
                //cancela.setDataEvento(LocalDateTime.now());
                cancela.setDataEvento(LocalDateTime.now());

                //Monta o Evento de Cancelamento
                TEnvEvento enviEvento
                        = CancelamentoUtil.montaCancelamento(cancela,
                                config,
                                ZoneId.of(ManagerData.TIME_ZONE_ID));
                TEvento tEvento = enviEvento.getEvento().get(0);
                jsysNFeEvento.setCOrgao(tEvento.getInfEvento().getCOrgao());
                jsysNFeEvento.setChNFe(tEvento.getInfEvento().getChNFe());
                jsysNFeEvento.setTpAmb(config.getAmbiente().getCodigo());
                jsysNFeEvento.setDhEvento(
                        ManagerData.formataData(
                                tEvento.getInfEvento().getDhEvento(),
                                ManagerData.FORMATO_NFE
                        )
                );
                jsysNFeEvento.setTpEvento(EventosEnum.CANCELAMENTO.getCodigo());
                jsysNFeEvento.setNSeqEvento(1);
                jsysNFeEvento.setDescEvento("Cancelamento");
                jsysNFeEvento.setNProt(tEvento.getInfEvento().getDetEvento().getNProt());
                jsysNFeEvento.setXJust(tEvento.getInfEvento().getDetEvento().getXJust());
                jsysNFeEvento.setEmitida(false);
                jsysNFeEvento.setIdEvento(tEvento.getInfEvento().getId());
                publish("Cancelamento Criado");
                publish("Cancelamento Assinado");
                setProgress(33);
                if (jsysNFeEvento.getIdEvento() != null) {
                    String XmlEvento = br.com.swconsultoria.nfe.util.XmlNfeUtil.objectToXml(enviEvento);
                    gravador.salvarArquivo(XmlEvento, br.JavaApplicationJsys.PASTA_XML_EVENTO, jsysNFeEvento.getIdEvento(), "xml");
                    jsysNFeEvento.setEnvEventoCancNFe(XmlEvento);
                    jsysNFeEvento = (JsysNFeEvento) ConnectionFactory.insert(jsysNFeEvento);
                }
                // Envia o Evento de Cancelamento
                publish("Envia o Evento de Cancelamento");
                TRetEnvEvento tRetEnvEvento = Nfe.cancelarNfe(
                        config,
                        enviEvento,
                        true,
                        DocumentoEnum.getByModelo(Integer.toString(nfe.getMod())));
                //Valida o Retorno do Cancelamento
                setProgress(66);
                publish("Salvando Cancelamento");
                String xmlProcEvento = XmlNfeUtil.objectToXml(tRetEnvEvento);
                gravador.salvarArquivo(
                        xmlProcEvento,
                        br.JavaApplicationJsys.PASTA_XML_RET_EVENTO,
                        jsysNFeEvento.getIdEvento(),
                        "xml");
                //Resultado
                if ("128".equals(tRetEnvEvento.getCStat())) {
                    for (TRetEvento tRetEvento : tRetEnvEvento.getRetEvento()) {
                        if ("135".equals(tRetEvento.getInfEvento().getCStat())) {
                            publish(tRetEvento.getInfEvento().getXMotivo());
                            java.util.Map<Object, Object> filtro = new java.util.HashMap<>();
                            filtro.put("chNFe", tRetEvento.getInfEvento().getChNFe());
                            JsysNFeEvento evento = (JsysNFeEvento) Retorna.findOneResult("JsysNFeEvento.findByChNFe", filtro);
                            if (evento != null) {
                                StringBuilder sql = new StringBuilder();
                                sql.append("UPDATE jsysNFeEvento SET emitida = 1, retEnvEventoCancNFe = '");
                                sql.append(xmlProcEvento);
                                sql.append("' WHERE chNFe = '").append(tRetEvento.getInfEvento().getChNFe()).append("'");
                                StringBuilder sql2 = new StringBuilder();
                                sql2.append("UPDATE jsysNFe SET cancelada = 1");
                                sql2.append("WHERE chaveAcesso = 'NFe");
                                sql2.append(tRetEvento.getInfEvento().getChNFe());
                                sql2.append("'");
                                if (dados.execSQLUpdate(sql) == 0) {
                                    Log.registraErro(this.getClass().getName(), "Task.doInBackground", new Exception("erro ao tentar executar o Update na tabela jsysNFeEvento"));
                                } else if (dados.execSQLUpdate(sql2) == 0) {
                                    Log.registraErro(this.getClass().getName(), "Task.doInBackground", new Exception("erro ao tentar executar o Update na tabela jsysNFe"));
                                } else {
                                    erro = false;
                                }
                            }
                        } else {
                            publish("Chave: " + tRetEvento.getInfEvento().getChNFe());
                            publish("Status: "
                                    + tRetEvento.getInfEvento().getCStat()
                                    + " - "
                                    + tRetEvento.getInfEvento().getXMotivo());
                        }
                    }
                    publish("Valida o Retorno do Cancelamento");
                    RetornoUtil.validaCancelamento(tRetEnvEvento);
                } else {
                    publish(tRetEnvEvento.getCStat() + " - " + tRetEnvEvento.getXMotivo());
                }
                setProgress(100);
            } catch (FileNotFoundException | CertificadoException | JAXBException | NfeException ex) {
                setProgress(100);
                publish("Erro ao Transmitir Evento Cancelamento");
                publish(ex.getMessage());
                Log.registraErro(NfceCancelamento.class, "Task.doInBackground", ex);
                return erro;
            }
            return erro;
        }

        @Override
        protected void process(List<String> chunks) {
            super.process(chunks);
            chunks.stream().forEach((s) -> {
                taskOutput.append(String.format(s + System.getProperty("line.separator")));
            });
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
            try {
                Toolkit.getDefaultToolkit().beep();
                startButton.setEnabled(true);
                setCursor(null); //turn off the wait cursor
                if (get()) {
                    JOptionPane.showMessageDialog(null, "ERRO", "ERRO", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Cancelamento efetuado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } catch (InterruptedException | ExecutionException e) {
                Log.registraErro(this.getClass().getName(), "Task", e);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress".equals(evt.getPropertyName())) {
            int progress = (Integer) evt.getNewValue();
            jProgressBar1.setValue(progress);
        }
    }
}
