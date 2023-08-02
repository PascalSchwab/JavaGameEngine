package de.pascalschwab.managers;

import javax.swing.*;

public final class KeyManager {
    private final ActionMap actionMap;
    private final InputMap inputMap;

    public KeyManager(JFrame frame) {
        actionMap = frame.getRootPane().getActionMap();
        inputMap = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    }
}
