package de.engine.managers;

import de.engine.window.Window;

public final class WindowManager {
    private static Window window = null;

    public static Window getWindow() {
        return WindowManager.window;
    }

    public static void setWindow(Window window) {
        WindowManager.window = window;
    }
}
