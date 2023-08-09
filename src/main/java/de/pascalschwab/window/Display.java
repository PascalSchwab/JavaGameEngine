package de.pascalschwab.window;

import org.joml.Vector2f;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.Objects;
import java.util.concurrent.Callable;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public final class Display {
    private final String title;
    private Vector2f size;
    private long id;
    private Callable<Void> resizeFunc;

    public Display(Vector2f size, String title, Callable<Void> resizeFunc) {
        this.resizeFunc = resizeFunc;
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
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        // Create the window
        this.id = glfwCreateWindow((int) this.size.x, (int) this.size.y, this.title, NULL, NULL);
        if (id == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Resize
        glfwSetFramebufferSizeCallback(this.id, (window, w, h) -> resize(w, h));

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

    private void resize(int width, int height) {
        this.size = new Vector2f(width, height);
        try {
            resizeFunc.call();
        } catch (Exception e) {
            throw new RuntimeException("Failed to resize window");
        }
    }

    public long getId() {
        return id;
    }

    public Vector2f getSize() {
        return size;
    }
}
