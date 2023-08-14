package de.pascalschwab.managers;

import de.pascalschwab.standard.enums.Key;
import de.pascalschwab.window.Window;

import static org.lwjgl.glfw.GLFW.*;

public class InputManager {
    private static Window window = WindowManager.getWindow();

    public static boolean isKeyTapped(Key key) {
        return false;
    }

    /**
     * Checks if specific key is pressed
     *
     * @return int
     */
    public static int isKeyPressedInt(Key key) {
        return glfwGetKey(window.getDisplay().getId(), key.keyCode) == GLFW_PRESS ? 1 : 0;
    }

    /**
     * Checks if specific key is released
     *
     * @return int
     */
    public static int isKeyReleasedInt(Key key) {
        return glfwGetKey(window.getDisplay().getId(), key.keyCode) == GLFW_RELEASE ? 1 : 0;
    }

    /**
     * Checks if specific key is pressed
     *
     * @return boolean
     */
    public static boolean isKeyPressed(Key key) {
        return glfwGetKey(window.getDisplay().getId(), key.keyCode) == GLFW_PRESS;
    }

    /**
     * Checks if specific key is released
     *
     * @return boolean
     */
    public static boolean isKeyReleased(Key key) {
        return glfwGetKey(window.getDisplay().getId(), key.keyCode) == GLFW_RELEASE;
    }

    public static void setWindow(Window window) {
        InputManager.window = window;
    }
}
