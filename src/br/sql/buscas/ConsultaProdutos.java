package br.sql.buscas;

import br.sql.acesso.SQLDatabaseConnection;
import br.sql.log.Log;
import br.sql.defaultTableCellRenderer.Zebrado;
import br.sql.util.ManagerSQL;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juliano Alves Medina
 */
public class ConsultaProdutos extends javax.swing.JDialog {

    private final JTextField field;
    private static final SQLDatabaseConnection DADOS = new SQLDatabaseConnection();
    //private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Creates new form ConsultaProdutos
     *
     * @param parent
     * @param modal
     * @param field
     */
    public ConsultaProdutos(java.awt.Frame parent, boolean modal, JTextField field) {
        super(parent, modal);
        initComponents();
        this.field = field;
        consultaTF.setText(this.field.getText());
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);
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

        consultaTF = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setModal(true);
        setResizable(false);

        consultaTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                consultaTFKeyPressed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setRowHeight(25);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(consultaTF)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(consultaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void consultaTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_consultaTFKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            selecionaTabela();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            actionConsulta();
        }
    }//GEN-LAST:event_consultaTFKeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() > 1) {
            setValue();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            setValue();
        }
    }//GEN-LAST:event_jTable1KeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField consultaTF;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void actionConsulta() {
        try {
            String campos[] = {"jsysProdutosT.nomeProduto", "jsysProdutosT.idProduto", "jsysLojas.nomeLoja", "jsysProdutosTPrecos.precoVenda", "jsysProdutosT.estoqueGeral", "jsysProdutosT.marca", "jsysIdProdutos.codigoBarra"};
            String order[] = {"jsysProdutosT.idProduto"};
            Map<Object, Object> onde = new HashMap<>();
            onde.put("jsysProdutosT.inativo", "= 0");
            onde.put("jsysLojas.ativo", "= 1");
            ManagerSQL sql = new ManagerSQL();
            sql.setTabela("jsysProdutosT "
                    + "INNER JOIN jsysProdutosTPrecos ON jsysProdutosT.idProduto = jsysProdutosTPrecos.idProduto "
                    + "INNER JOIN jsysLojas ON jsysProdutosTPrecos.idloja = jsysLojas.idloja "
                    + "LEFT JOIN jsysIdProdutos ON jsysProdutosT.idProduto = jsysIdProdutos.idProduto ");
            sql.setCampos(campos);
            sql.setOrder(order);
            sql.setOnde(onde);
            ResultSet x = DADOS.execSQL(sql.getSQL(consultaTF.getText()));
            // Definição do TableModel
            DefaultTableModel aModel = new DefaultTableModel(new Object[][]{}, new String[]{"NOME PRODUTO", "CODIGO", "LOJA", "PREÇO DE VENDA", "ESTOQUE", "MARCA", "REFERENCIA"}) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            java.sql.ResultSetMetaData rsmd = x.getMetaData();
            int colNo = rsmd.getColumnCount();
            while (x.next()) {
                Object[] objects = new Object[colNo];
                for (int i = 0; i < colNo; i++) {
                    objects[i] = x.getObject(i + 1);
                }
                aModel.addRow(objects);
            }
            jTable1.setModel(aModel);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(450);
            jTable1.setDefaultRenderer(Object.class, new Zebrado());
        } catch (SQLException e) {
            Log.registraErro(this, "falha ao acionar a consulta", e);
        }
    }

    private void selecionaTabela() {
        try {
            jTable1.requestFocus();
            jTable1.setRowSelectionInterval(0, 0);
        } catch (Exception ignore) {
        }
    }

    private void setValue() {
        this.field.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1)));
        this.dispose();
    }
}
