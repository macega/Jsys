/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jsys.sql;

import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.Stack;

/**
 *
 * @author Juliano Alves Medina
 */
public class InactivityListener implements WindowStateListener, MouseListener, KeyListener {

    private static InactivityListener instance;
    private long lastAction = 0;

    public int getInactivitySeconds() {
        return (int) (System.currentTimeMillis() - lastAction) / 1000;
    }

    private void setLastAction() {
        lastAction = System.currentTimeMillis();
    }

    /**
     * add the listeners to the frame and to its childs
     *
     * @param main
     */
    @SuppressWarnings("unchecked")
    public static void addListener(Window main) {

        main.addWindowStateListener(InactivityListener.getInstance());

        Stack stack = new Stack();
        stack.push(main);

        while (!stack.empty()) {
            Container container = (Container) stack.pop();
            container.addKeyListener(InactivityListener.getInstance());
            container.addMouseListener(InactivityListener.getInstance());
            Component[] components = container.getComponents();
            for (Component component : components) {
                if (component instanceof Container) {
                    stack.push(component);
                } else {
                    // non containers
                    component.addKeyListener(InactivityListener.getInstance());
                    component.addMouseListener(InactivityListener.getInstance());
                }
            }
        }

    }

    public static InactivityListener getInstance() {
        if (instance == null) {
            instance = new InactivityListener();
        }
        return instance;
    }

    private InactivityListener() {
        setLastAction();
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        setLastAction();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setLastAction();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        setLastAction();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setLastAction();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setLastAction();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setLastAction();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setLastAction();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        setLastAction();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        setLastAction();
    }

}
