package de.pascalschwab.managers;

import de.pascalschwab.window.Window;

public final class WindowManager {
    private static Window window = null;

    public static Window getWindow() {
        return WindowManager.window;
    }

    public static void setWindow(Window window) {
        WindowManager.window = window;
    }
}
