/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.adp.util;

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

    public Progress() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        setModal(true);
        setUndecorated(true);
        JProgressBar progress = new JProgressBar();
        progress.setSize(400, 20);
        progress.setIndeterminate(true);
        progress.setStringPainted(true);
        progress.setString("Aguarde...");
        getContentPane().add(progress);
        pack();
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((tela.width - this.getSize().width) / 2,
                (tela.height - this.getSize().height) / 2);
    }

    @Override
    public void finalize() {
        try {
            super.finalize();
            setCursor(null);
        } catch (Throwable e) {
        }
    }
}
