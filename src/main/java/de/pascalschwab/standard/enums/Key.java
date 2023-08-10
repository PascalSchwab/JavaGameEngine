package de.pascalschwab.standard.enums;

import static org.lwjgl.glfw.GLFW.*;

public enum Key {
    W(GLFW_KEY_W), A(GLFW_KEY_A), S(GLFW_KEY_S), D(GLFW_KEY_D);
    public final int keyCode;

    Key(int keyCode) {
        this.keyCode = keyCode;
    }
}
