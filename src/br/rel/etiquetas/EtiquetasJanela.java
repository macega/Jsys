package br.rel.etiquetas;

import br.sql.bean.JsysLojas;
import br.sql.bean.JsysProdutosTPrecos;
import br.sql.bean.ProdutosEtiquetas;
import br.sql.bean.views.JsysIdProdutos;
import br.sql.log.Log;
import br.sql.util.ReportUtils;
import br.sql.util.Retorna;
import br.sql.util.Validar;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Juliano Alves Medina
 */
public class EtiquetasJanela extends JPanel {

    private JsysProdutosTPrecos produtoAtual;

    public EtiquetasJanela() {
        initComponents();
        prencheCombox();
        // permite parar e tirar a seleção de uma célula quando clicar em  
        // qualquer outro componente da janela
        masterTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        // ************ faz a tecla ENTER se comportar como a tecla TAB  
        masterTable.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "selectNextColumnCell");
        // ******* permite mudar para a próxima célula depois de editar a corrente  
        // masterTable.setSurrendersFocusOnKeystroke(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        query = java.beans.Beans.isDesignTime() ? null : br.sql.acesso.ConnectionFactory.getEntityManagerNew().createQuery("SELECT p FROM ProdutosEtiquetas p");
        list = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(query.getResultList());
        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        codigoLabel = new javax.swing.JLabel();
        descricaoLabel = new javax.swing.JLabel();
        quantidadeLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        codigoTF = new javax.swing.JTextField();
        quantidadeTF = new javax.swing.JTextField();
        jTextFieldDescricao = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        uJComboBoxLojas = new componentes.UJComboBox();

        FormListener formListener = new FormListener();

        masterTable.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        masterTable.setRowHeight(30);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, list, masterTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${codigo}"));
        columnBinding.setColumnName("Codigo");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${descricao}"));
        columnBinding.setColumnName("Descricao");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${quantidade}"));
        columnBinding.setColumnName("Quantidade");
        columnBinding.setColumnClass(Integer.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        masterScrollPane.setViewportView(masterTable);
        if (masterTable.getColumnModel().getColumnCount() > 0) {
            masterTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        }

        codigoLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        codigoLabel.setText("Codigo:");

        descricaoLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        descricaoLabel.setText("Descricao:");

        quantidadeLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        quantidadeLabel.setText("Quantidade:");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Etiquetas ");
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

        codigoTF.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        codigoTF.addFocusListener(formListener);
        codigoTF.addKeyListener(formListener);

        quantidadeTF.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        quantidadeTF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        quantidadeTF.setText("1");
        quantidadeTF.setToolTipText("");
        quantidadeTF.addFocusListener(formListener);
        quantidadeTF.addKeyListener(formListener);

        jTextFieldDescricao.setBackground(new java.awt.Color(204, 255, 204));
        jTextFieldDescricao.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton1.setText("Etiquetas");
        jButton1.addActionListener(formListener);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton2.setText("Limpar");
        jButton2.addActionListener(formListener);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton3.setText("Espelhos");
        jButton3.addActionListener(formListener);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton4.setText("Deletar");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jButton4, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton4.addActionListener(formListener);

        uJComboBoxLojas.setAutocompletar(true);
        uJComboBoxLojas.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(masterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(descricaoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDescricao))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(codigoLabel)
                        .addGap(16, 16, 16)
                        .addComponent(codigoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(quantidadeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(quantidadeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(uJComboBoxLojas, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoLabel)
                    .addComponent(quantidadeLabel)
                    .addComponent(codigoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantidadeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descricaoLabel)
                    .addComponent(jTextFieldDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(masterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(uJComboBoxLojas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap())
        );

        bindingGroup.bind();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.FocusListener, java.awt.event.KeyListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jButton1) {
                EtiquetasJanela.this.jButton1ActionPerformed(evt);
            }
            else if (evt.getSource() == jButton2) {
                EtiquetasJanela.this.jButton2ActionPerformed(evt);
            }
            else if (evt.getSource() == jButton3) {
                EtiquetasJanela.this.jButton3ActionPerformed(evt);
            }
            else if (evt.getSource() == jButton4) {
                EtiquetasJanela.this.jButton4ActionPerformed(evt);
            }
        }

        public void focusGained(java.awt.event.FocusEvent evt) {
            if (evt.getSource() == codigoTF) {
                EtiquetasJanela.this.codigoTFFocusGained(evt);
            }
            else if (evt.getSource() == quantidadeTF) {
                EtiquetasJanela.this.quantidadeTFFocusGained(evt);
            }
        }

        public void focusLost(java.awt.event.FocusEvent evt) {
        }

        public void keyPressed(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == codigoTF) {
                EtiquetasJanela.this.codigoTFKeyPressed(evt);
            }
            else if (evt.getSource() == quantidadeTF) {
                EtiquetasJanela.this.quantidadeTFKeyPressed(evt);
            }
        }

        public void keyReleased(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == codigoTF) {
                EtiquetasJanela.this.codigoTFKeyReleased(evt);
            }
        }

        public void keyTyped(java.awt.event.KeyEvent evt) {
        }
    }// </editor-fold>//GEN-END:initComponents

    private void codigoTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoTFKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Validar.Produto(codigoTF.getText())) {
                quantidadeTF.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Produto Não Encontrado", "ERRO", JOptionPane.ERROR_MESSAGE);
                codigoTF.requestFocus();
                codigoTF.selectAll();
            }
        }
    }//GEN-LAST:event_codigoTFKeyPressed

    private void codigoTFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_codigoTFFocusGained
        codigoTF.selectAll();
    }//GEN-LAST:event_codigoTFFocusGained

    private void codigoTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoTFKeyReleased
        setProdutoAtual(null);
        Map<Object, Object> filtro = new HashMap<>();
        filtro.put("codigoBarra", codigoTF.getText());
        JsysIdProdutos p = (JsysIdProdutos) Retorna.findOneResult("JsysIdProdutos.findByCodigoBarra", filtro);
        if (p != null) {
            filtro.clear();
            filtro.put("idProduto", p.getIdProduto().getIdProduto());
            filtro.put("idloja", Retorna.getLoja());
            setProdutoAtual((JsysProdutosTPrecos) Retorna.findOneResult("JsysProdutosTPrecos.findByIdProdutoAndIdloja", filtro));
        }
    }//GEN-LAST:event_codigoTFKeyReleased

    private void quantidadeTFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quantidadeTFFocusGained
        quantidadeTF.selectAll();
    }//GEN-LAST:event_quantidadeTFFocusGained

    private void quantidadeTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantidadeTFKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Validar.Produto(codigoTF.getText()) & Validar.isNumber(quantidadeTF.getText())) {
                try {
                    Map<Object, Object> filtro = new HashMap<>();
                    filtro.put("codigoBarra", codigoTF.getText().trim());
                    JsysIdProdutos idp = (JsysIdProdutos) Retorna.findOneResult("JsysIdProdutos.findByCodigoBarra", filtro);
                    ProdutosEtiquetas p = new ProdutosEtiquetas(idp.getIdProduto().getIdProduto().toString(), idp.getIdProduto().getNomeProduto(), Integer.parseInt(quantidadeTF.getText().trim()));
                    if (!list.contains(p)) {
                        br.sql.acesso.ConnectionFactory.insert(p);
                        list.add(p);
                        int row = list.size() - 1;
                        masterTable.setRowSelectionInterval(row, row);
                        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
                    }
                } catch (Exception e) {
                    Log.registraErro(this.getClass().toString(), this.getName(), e);
                } finally {
                    limparCampos();
                    codigoTF.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Produto ou Quantidade Invalida", "ERRO", JOptionPane.ERROR_MESSAGE);
                codigoTF.requestFocus();
                codigoTF.selectAll();
            }
        }
    }//GEN-LAST:event_quantidadeTFKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        imprimir("/br/rel/etiquetas/Etiquetas3ColunasDS.jasper");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            List<ProdutosEtiquetas> toRemove = new ArrayList<>();
            for (int idx = 0; idx < list.size(); idx++) {
                ProdutosEtiquetas p = list.get(idx);
                toRemove.add(p);
                br.sql.acesso.ConnectionFactory.delete(p);
            }
            list.removeAll(toRemove);
        } catch (Exception e) {
            Log.registraErro(this.getClass().toString(), this.getName(), e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        imprimir("/br/rel/etiquetas/espelhosDeposito.jasper");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int[] selected = masterTable.getSelectedRows();
        List<ProdutosEtiquetas> toRemove = new ArrayList<>(selected.length);
        for (int idx = 0; idx < selected.length; idx++) {
            ProdutosEtiquetas p = list.get(masterTable.convertRowIndexToModel(selected[idx]));
            toRemove.add(p);
            br.sql.acesso.ConnectionFactory.delete(p);
        }
        list.removeAll(toRemove);
    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel codigoLabel;
    private javax.swing.JTextField codigoTF;
    private javax.swing.JLabel descricaoLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldDescricao;
    private java.util.List<br.sql.bean.ProdutosEtiquetas> list;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private javax.swing.JLabel quantidadeLabel;
    private javax.swing.JTextField quantidadeTF;
    private javax.persistence.Query query;
    private componentes.UJComboBox uJComboBoxLojas;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    public void setProdutoAtual(JsysProdutosTPrecos produtoAtual) {
        this.produtoAtual = produtoAtual;
        if (this.produtoAtual != null) {
            jTextFieldDescricao.setText(this.produtoAtual.getJsysProdutosT().getNomeProduto());
        } else {
            jTextFieldDescricao.setText("Digite o codigo do Produto!!");
        }
    }

    private void prencheCombox() {
        try {
            Map<Object, Object> filtro = new HashMap<>();
            for (Object L : Retorna.findList("JsysLojas.findAll", filtro)) {
                JsysLojas l = (JsysLojas) L;
                uJComboBoxLojas.addItem(l.getNomeLoja() + " - " + l.getIdloja(), l);
            }
            filtro.clear();
            filtro.put("ativo", true);
            JsysLojas jL = ((JsysLojas) Retorna.findOneResult("JsysLojas.findByAtivo", filtro));
            uJComboBoxLojas.setSelectedItem(jL.getNomeLoja() + " - " + jL.getIdloja());
        } catch (Exception e) {
            Log.registraErro(this.getClass().toString(), this.getName(), e);
        }
    }

    /**
     * @return JsysProdutosTGrupos selecionado
     */
    private JsysLojas getuJComboBoxGrupo() {
        try {
            if (uJComboBoxLojas.getSelectedItem() != null) {
                return (JsysLojas) uJComboBoxLojas.getSelectedObject();
            } else {
                return null;
            }
        } catch (Exception ignore) {
            return null;
        }
    }

    private void limparCampos() {
        codigoTF.setText("");
        quantidadeTF.setText("");
        jTextFieldDescricao.setText("");
    }

    private void imprimir(String relatorio) throws NumberFormatException, HeadlessException {
        if (!list.isEmpty()) {
            ArrayList<EtiquetasBean> dados = new ArrayList<>();
            for (int idx = 0; idx < list.size(); idx++) {
                ProdutosEtiquetas p = list.get(idx);
                Map<Object, Object> filtro = new HashMap<>();
                filtro.put("idProduto", Integer.parseInt(p.getCodigo()));
                filtro.put("idloja", getuJComboBoxGrupo().getIdloja());
                JsysProdutosTPrecos prod = (JsysProdutosTPrecos) Retorna.findOneResult("JsysProdutosTPrecos.findByIdProdutoAndIdloja", filtro);
                if (prod != null) {
                    for (int i = 1; i <= p.getQuantidade(); i++) {
                        EtiquetasBean e = new EtiquetasBean(Integer.parseInt(String.valueOf(idx) + String.valueOf(i)), p.getCodigo(), p.getDescricao(), prod.getPrecoVenda(), prod.getJsysProdutosT().getMarca());
                        dados.add(e);
                    }
                }
            }
            ReportUtils.openReport("Etiquetas", relatorio, new JRBeanCollectionDataSource(dados));
        } else {
            JOptionPane.showMessageDialog(null, "Não á produtos na lista ", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
}
