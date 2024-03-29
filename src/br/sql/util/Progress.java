package br.sql.util;

import java.awt.Cursor;

/**
 *
 * @author julia
 */
public class Progress extends javax.swing.JDialog {

    private static final long serialVersionUID = 3977298828280279606L;
    private final boolean indeterminate;
    private final String mensagem;
    private final int value;
    private final boolean stringPainted;

    public static final String MENSAGEM = "Aguarde...";
    public static final Boolean INDETERMINATE = true;
    public static final int VALUE = 0;
    public static final Boolean STRING_PAINTED = true;
    public static final Boolean MODAL = true;

    public Progress() {
        this(STRING_PAINTED, VALUE, MENSAGEM, MODAL, INDETERMINATE);
    }

    public Progress(String mensagen) {
        this(STRING_PAINTED, VALUE, mensagen, MODAL, INDETERMINATE);
    }

    public Progress(boolean modal) {
        this(STRING_PAINTED, VALUE, MENSAGEM, modal, INDETERMINATE);
    }

    public Progress(String mensagen, boolean modal) {
        this(STRING_PAINTED, VALUE, mensagen, modal, INDETERMINATE);
    }

    public Progress(String mensagen, boolean modal, boolean indeterminate) {
        this(STRING_PAINTED, VALUE, mensagen, modal, indeterminate);
    }

    public Progress(boolean stringPainted,
            int value,
            String mensagem,
            boolean modal,
            boolean indeterminate) {
        super(new javax.swing.JFrame(), modal);
        this.stringPainted = stringPainted;
        this.value = value;
        this.mensagem = mensagem;
        this.indeterminate = indeterminate;

        initComponents();
    }

    public void setMensagen(String mensagen) {
        pbar.setString(mensagen);
    }

    public void setValue(Integer value) {
        pbar.setValue(value);
    }

    @Override
    public void setVisible(boolean b) {
        if (b) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        } else {
            this.setCursor(null);
        }
        super.setVisible(b);
    }

    @Override
    public void dispose() {
        this.setCursor(null);
        super.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pbar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        pbar.setValue(value);
        pbar.setIndeterminate(indeterminate);
        pbar.setString(mensagem);
        pbar.setStringPainted(stringPainted);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pbar, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pbar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar pbar;
    // End of variables declaration//GEN-END:variables
}
