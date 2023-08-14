package de.pascalschwab.managers;

import de.pascalschwab.standard.enums.Key;
import de.pascalschwab.window.Window;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public class InputManager {
    private static final Window window = WindowManager.getWindow();
    private static final Map<Integer, InputData> activeKeys = new HashMap<>();

    /**
     * Checks if specific key is tapped
     *
     * @return boolean
     */
    public static boolean isKeyTapped(Key key) {
        if (activeKeys.containsKey(key.keyCode)) {
            InputData data = activeKeys.get(key.keyCode);
            if (!data.triggered && data.action == GLFW_PRESS) {
                data.triggered = true;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if specific key is tapped
     *
     * @return int
     */
    public static int isKeyTappedInt(Key key) {
        if (activeKeys.containsKey(key.keyCode)) {
            InputData data = activeKeys.get(key.keyCode);
            if (!data.triggered && data.action == GLFW_PRESS) {
                data.triggered = true;
                return 1;
            }
        }
        return 0;
    }

    /**
     * Checks if specific key is pressed
     *
     * @return int
     */
    public static int isKeyPressedInt(Key key) {
        if (activeKeys.containsKey(key.keyCode))
            return activeKeys.get(key.keyCode).action == GLFW_REPEAT ? 1 : 0;
        return 0;
    }

    /**
     * Checks if specific key is released
     *
     * @return int
     */
    public static int isKeyReleasedInt(Key key) {
        if (activeKeys.containsKey(key.keyCode))
            return activeKeys.get(key.keyCode).action == GLFW_RELEASE ? 1 : 0;
        return 0;
    }

    /**
     * Checks if specific key is pressed
     *
     * @return boolean
     */
    public static boolean isKeyPressed(Key key) {
        if (activeKeys.containsKey(key.keyCode))
            return activeKeys.get(key.keyCode).action == GLFW_REPEAT || activeKeys.get(key.keyCode).action == GLFW_PRESS;
        return false;
    }

    /**
     * Checks if specific key is released
     *
     * @return boolean
     */
    public static boolean isKeyReleased(Key key) {
        if (activeKeys.containsKey(key.keyCode))
            return activeKeys.get(key.keyCode).action == GLFW_RELEASE;
        return false;
    }

    /**
     * Adds the key to the active keys
     */
    public static void addKey(Integer key, Integer action) {
        if (!activeKeys.containsKey(key)) {
            activeKeys.put(key, new InputData(action, false));
        } else {
            activeKeys.replace(key, new InputData(action, false));
        }
    }
}

class InputData {
    int action;
    boolean triggered;

    public InputData(int action, boolean triggered) {
        this.action = action;
        this.triggered = triggered;
    }
}
