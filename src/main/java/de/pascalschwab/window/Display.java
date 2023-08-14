package de.pascalschwab.window;

import org.joml.Vector2f;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public final class Display {
    private final long id;

    public Display(Vector2f size, String title) {

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
        this.id = glfwCreateWindow((int) size.x, (int) size.y, title, NULL, NULL);
        if (id == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Center window
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        assert vidmode != null;
        glfwSetWindowPos(this.id, (int) ((vidmode.width() - size.x) / 2), (int) ((vidmode.height() - size.y) / 2));

        // Activate context
        glfwMakeContextCurrent(id);
        GL.createCapabilities();
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(id);

        // Don't render transparency
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
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
}
