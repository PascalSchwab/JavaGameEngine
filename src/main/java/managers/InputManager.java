package managers;

import de.pascalschwab.standard.enums.Key;
import de.pascalschwab.window.Window;

import static org.lwjgl.glfw.GLFW.*;

public class InputManager {
    private static Window window = null;

    /**
     * Checks if specific key is pressed
     */
    public static boolean isKeyPressed(Key key) {
        return glfwGetKey(window.getDisplay().getId(), key.keyCode) == GLFW_PRESS;
    }

    /**
     * Checks if specific key is released
     */
    public static boolean isKeyReleased(Key key) {
        return glfwGetKey(window.getDisplay().getId(), key.keyCode) == GLFW_RELEASE;
    }

    public static void setWindow(Window window) {
        InputManager.window = window;
    }
}
