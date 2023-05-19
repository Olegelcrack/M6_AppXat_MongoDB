/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author marpo
 */
import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.awt.Color;
import javax.swing.JPanel;

public class bean extends JPanel {
    private boolean modoNocturno;
    
    public boolean isModoNocturno() {
        return modoNocturno;
    }

    public void setModoNocturno(boolean modoNocturno) {
        this.modoNocturno = modoNocturno;
        actualizarApariencia();
    }

    private void actualizarApariencia() {
        if (modoNocturno) {
            setBackground(Color.BLACK);
            // Realiza otros cambios de apariencia para el modo nocturno
        } else {
            setBackground(Color.WHITE);
            // Realiza otros cambios de apariencia para el modo diurno
        }
        repaint(); // Vuelve a pintar el componente para reflejar los cambios
    }
}
