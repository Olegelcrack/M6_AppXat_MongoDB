package bean_final;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Bean_prova extends JPanel {
    private JToggleButton toggleButton;
    private Color backgroundColor;
    private Color newColor;
    private Color newColor2;
    private boolean isNightMode;
    private List<ActionListener> modeChangeListeners;

    public Bean_prova() {
        setLayout(new BorderLayout());
        newColor = null;
        toggleButton = new JToggleButton("Mode Nocturn");
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isNightMode = toggleButton.isSelected();
                if (isNightMode) {
                    newColor = new Color(40, 40, 40);
                    newColor2 = Color.WHITE;
                    toggleButton.setText("Mode Diürn");
                    fireModeChangeEvent();
                } else {
                    newColor = null;
                    newColor2 = Color.BLACK;
                    toggleButton.setText("Mode Nocturn");
                    fireModeChangeEvent();
                }
            }
        });

        modeChangeListeners = new ArrayList<>();

        add(toggleButton, BorderLayout.CENTER);
    }

    public Color getMode() {
        return newColor;
    }

    public Color getMode2() {
        return newColor2;
    }

    public void addModeChangeListener(ActionListener listener) {
        modeChangeListeners.add(listener);
    }

    public void removeModeChangeListener(ActionListener listener) {
        modeChangeListeners.remove(listener);
    }

    private void fireModeChangeEvent() {
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "modeChange");
        for (ActionListener listener : modeChangeListeners) {
            listener.actionPerformed(event);
        }
    }
}