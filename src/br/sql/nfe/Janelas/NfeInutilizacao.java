package br.sql.nfe.Janelas;

import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TInutNFe;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TRetInutNFe;
import br.com.swconsultoria.nfe.util.InutilizacaoUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;
import br.sql.acesso.SQLDatabaseConnection;
import br.sql.bean.JsysNFeInut;
import br.sql.bean.JsysParametros;
import br.sql.log.Log;
import br.sql.nfe.links.ConstantesFiscal;
import br.sql.util.GravaNoArquivo;
import br.sql.util.ManagerString;
import br.sql.util.Retorna;
import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author julia
 */
public class NfeInutilizacao extends javax.swing.JDialog {

    private final String serie;
    private static final SQLDatabaseConnection DADOS = new SQLDatabaseConnection();
    private final JsysParametros par = Retorna.JsysParametros();
    private final JsysNFeInut jsysNFeInut = new JsysNFeInut();

    /**
     * Creates new form NfeInutilizacao
     *
     * @param parent
     * @param modal
     */
    public NfeInutilizacao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        serie = br.JavaApplicationJsys.INI.getString("FISCAL", "SERIE");
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
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inutilizacaojTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        xJustificativajTextArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("NF-e Inutilização");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("NF-e Inutilização");

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

        jLabel2.setText("Intervalo NF-e");

        inutilizacaojTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Inicio", "Fim", "mod", "Serie"
            }
        ));
        jScrollPane1.setViewportView(inutilizacaojTable);

        jLabel3.setText("Justificativa");

        xJustificativajTextArea.setColumns(20);
        xJustificativajTextArea.setRows(5);
        jScrollPane2.setViewportView(xJustificativajTextArea);

        jButton1.setText("Lista NF-e");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Lista NFC-e");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Fechar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Inutilizar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
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
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)))
                .addGap(24, 24, 24))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        listaIntervaloNfe(ConstantesFiscal.NF_E);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        listaIntervaloNfe(ConstantesFiscal.NFC_E);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (validar()) {
            for (int i : inutilizacaojTable.getSelectedRows()) {
                try {
                    // Inicia As Configurações
                    ConfiguracoesNfe configuracoesNfe = br.JavaApplicationJsys.iniciaConfigurações(par);
                    String cnpj = ManagerString.RemoveFormatoCpfCnpj(par.getCnpj());
                    int seriee = Integer.parseInt(inutilizacaojTable.getValueAt(i, 3).toString());
                    int numeroInicial = Integer.parseInt(inutilizacaojTable.getValueAt(i, 0).toString());
                    int numeroFinal = Integer.parseInt(inutilizacaojTable.getValueAt(i, 1).toString());
                    String justificativa = xJustificativajTextArea.getText().trim();
                    LocalDateTime dataCancelamento = LocalDateTime.now();
                    DocumentoEnum documentoEnum = DocumentoEnum.getByModelo(String.valueOf(inutilizacaojTable.getValueAt(i, 2)));
                    //Monta Inutilização
                    TInutNFe inutNFe = InutilizacaoUtil.montaInutilizacao(documentoEnum,
                            cnpj,
                            seriee,
                            numeroInicial,
                            numeroFinal,
                            justificativa,
                            dataCancelamento,
                            configuracoesNfe);
                    jsysNFeInut.setTpAmb(inutNFe.getInfInut().getTpAmb());
                    jsysNFeInut.setXServ(inutNFe.getInfInut().getXServ());
                    jsysNFeInut.setCUF(inutNFe.getInfInut().getCUF());
                    jsysNFeInut.setAno(inutNFe.getInfInut().getAno());
                    jsysNFeInut.setCnpj(inutNFe.getInfInut().getCNPJ());
                    jsysNFeInut.setMod(inutNFe.getInfInut().getMod());
                    jsysNFeInut.setSerie(inutNFe.getInfInut().getSerie());
                    jsysNFeInut.setNNFIni(inutNFe.getInfInut().getNNFIni());
                    jsysNFeInut.setNNFFin(inutNFe.getInfInut().getNNFFin());
                    jsysNFeInut.setXJust(inutNFe.getInfInut().getXServ());
                    StringBuilder id = new StringBuilder();
                    id.append("ID")
                            .append(jsysNFeInut.getCUF())
                            .append(jsysNFeInut.getAno())
                            .append(jsysNFeInut.getCnpj())
                            .append(jsysNFeInut.getMod())
                            .append(ManagerString.zeros(jsysNFeInut.getSerie(), 3))
                            .append(ManagerString.zeros(jsysNFeInut.getNNFIni(), 9))
                            .append(ManagerString.zeros(jsysNFeInut.getNNFFin(), 9));
                    jsysNFeInut.setIdInut(id.toString());
                    jsysNFeInut.setXmlInutNFe(strValueOf(inutNFe));
                    GravaNoArquivo gravador = new GravaNoArquivo();
                    gravador.salvarArquivo(jsysNFeInut.getXmlInutNFe(), br.JavaApplicationJsys.PASTA_XML_INUT_NFE, jsysNFeInut.getIdInut(), "xml");
                    br.sql.acesso.ConnectionFactory.insert(jsysNFeInut);
//                  Envia Inutilização
                    TRetInutNFe retorno = Nfe.inutilizacao(configuracoesNfe, inutNFe, documentoEnum, true);
                    gravador.salvarArquivo(InutilizacaoUtil.criaProcInutilizacao(configuracoesNfe, inutNFe, retorno),
                            br.JavaApplicationJsys.PASTA_XML_PROC_INUT_NFE,
                            jsysNFeInut.getIdInut(),
                            "xml");
//                    //Resultado
//                    System.out.println();
//                    System.out.println("# Status: " + retorno.getInfInut().getCStat() + " - " + retorno.getInfInut().getXMotivo());
//                    System.out.println("# Protocolo: " + retorno.getInfInut().getNProt());
//                    //Cria ProcEvento da Inutilização
//                    String proc = InutilizacaoUtil.criaProcInutilizacao(configuracoesNfe, inutNFe, retorno);
//                    System.out.println();
//                    System.out.println("# ProcInutilizacao : " + proc);
                    RetornoUtil.validaInutilizacao(retorno);
                } catch (FileNotFoundException | CertificadoException | JAXBException ex) {
                    Log.registraErro(this.getClass().getName(), "jButton4ActionPerformed", ex);
                } catch (NfeException ex) {
                    Log.registraErro(this.getClass().getName(), "jButton4ActionPerformed", ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                    xJustificativajTextArea.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable inutilizacaojTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea xJustificativajTextArea;
    // End of variables declaration//GEN-END:variables

    public void listaIntervaloNfe(int mod) {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT inicio, fim, mod, serie FROM intervaloNFeIDs ")
                    .append("WHERE (serie = '").append(serie).append("') and (")
                    .append("mod = '").append(mod).append("')");
            System.out.println(sql.toString());
            ResultSet x = DADOS.execSQL(sql.toString());
            String[] tableColumnsName = {"Inicio", "Fim", "mod", "Serie"};
            DefaultTableModel aModel = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            aModel.setColumnIdentifiers(tableColumnsName);
            java.sql.ResultSetMetaData rsmd = x.getMetaData();
            int colNo = rsmd.getColumnCount();
            while (x.next()) {
                Object[] objects = new Object[colNo];
                for (int i = 0; i < colNo; i++) {
                    objects[i] = x.getObject(i + 1);
                }
                aModel.addRow(objects);
            }
            inutilizacaojTable.setModel(aModel);
            inutilizacaojTable.setDefaultRenderer(Object.class, new br.sql.defaultTableCellRenderer.ZebradoLocation());
        } catch (SQLException e) {
            Log.registraErro(this.getClass().getName(), "listaIntervaloNfe", e);
        }
    }

    private boolean validar() {
        if (xJustificativajTextArea.getText().trim().length() < 15) {
            JOptionPane.showMessageDialog(this, "Informar a justificativa do pedido de inutilização com no mínimo 15 dígitos.");
            xJustificativajTextArea.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * Método que Converte o Objeto em String.
     *
     * @param tInutNFe
     * @return
     * @throws JAXBException
     */
    private static String strValueOf(TInutNFe tInutNFe) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TInutNFe.class);
        Marshaller marshaller = context.createMarshaller();
        JAXBElement<TInutNFe> element = new br.com.swconsultoria.nfe.schema_4.inutNFe.ObjectFactory().createInutNFe(tInutNFe);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        marshaller.marshal(element, sw);

        String xml = sw.toString();
        xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
        xml = xml.replaceAll("<NFe>", "<NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\">");

        return xml;
    }
}
