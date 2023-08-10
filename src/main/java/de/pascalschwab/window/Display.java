package de.pascalschwab.window;

import org.joml.Vector2f;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public final class Display {
    private final String title;
    private Vector2f size;
    private long id;

    public Display(Vector2f size, String title) {
        this.size = size;
        this.title = title;

        init();
    }

    private void init() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
        // Initialize GLFW
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        // Create the window
        this.id = glfwCreateWindow((int) this.size.x, (int) this.size.y, this.title, NULL, NULL);
        if (id == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Activate context
        glfwMakeContextCurrent(id);
        GL.createCapabilities();
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(id);
    }

    public void dispose() {
        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(id);
        glfwDestroyWindow(id);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }

    public long getId() {
        return id;
    }

    public Vector2f getSize() {
        return size;
    }
}
