/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.util;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JProgressBar;

/**
 *
 * @author Juliano Alves Medina
 */
public class Progress extends JDialog {

    private final JProgressBar progress;

    public Progress() {
        progress = new JProgressBar();
        inicializa("Aguarde...", true, true);
    }

    public Progress(String mensagen) {
        progress = new JProgressBar();
        inicializa(mensagen, true, true);
    }

    public Progress(String mensagen, boolean modal) {
        progress = new JProgressBar();
        inicializa(mensagen, modal, true);
    }

    public Progress(String mensagen, boolean modal, boolean indeterminate) {
        progress = new JProgressBar();
        inicializa(mensagen, modal, indeterminate);
    }

    public Progress(boolean modal) {
        progress = new JProgressBar();
        inicializa("Aguarde...", modal, true);
    }

    private void inicializa(String mensagen, boolean modal, boolean indeterminate) {
        setModal(modal);
        setUndecorated(true);
        progress.setSize(5000, 100);
        progress.setIndeterminate(indeterminate);
        progress.setStringPainted(true);
        setMensagen(mensagen);
        getContentPane().add(progress);
        pack();
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((tela.width - this.getSize().width) / 2,
                (tela.height - this.getSize().height) / 2);
    }

    public void setMensagen(String mensagen) {
        progress.setString(mensagen);
    }

    public void setValue(Integer value) {
        progress.setValue(value);
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (b) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        } else {
            setCursor(null);
        }
    }

    @Override
    public void dispose() {
        setCursor(null);
        super.dispose();
    }
}
