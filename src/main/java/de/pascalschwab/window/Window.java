package de.pascalschwab.window;

import de.pascalschwab.standard.GameObject;
import de.pascalschwab.standard.KinematicObject;
import de.pascalschwab.standard.enums.Colour;
import de.pascalschwab.standard.lists.LayerBasedList;
import de.pascalschwab.standard.math.Vector;
import de.pascalschwab.standard.math.Vector2;

import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public abstract class Window<VectorType extends Vector<VectorType>> implements Runnable {
    private final Vector2 size;
    private final String title;
    private final Display display;
    public List<GameObject<VectorType>> gameObjects = new LayerBasedList<>();
    private Colour backgroundColour = Colour.WHITE;

    public Window(int width, int height, String title) {
        size = new Vector2(width, height);
        this.title = title;
        this.display = new Display(size, title);
    }

    @Override
    public final void run() {
        init();
        try {
            setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loop();
        dispose();
    }

    private void init() {
        // Setup a key callback
        /*glfwSetKeyCallback(this.display.getId(), (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true);
        });*/
    }

    private void loop() {
        // Time calculation and game loop
        double delta = 0;
        long lastTime = System.nanoTime();
        while (!glfwWindowShouldClose(this.display.getId())) {
            long now = System.nanoTime();
            double tickDuration = (double) 1000000000 / 60;
            delta += (now - lastTime) / tickDuration;
            lastTime = now;

            if (delta >= 1) {
                tick();
                delta--;
            }
        }
    }

    private void tick() {
        // Set the clear color
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor((float) this.backgroundColour.r, (float) this.backgroundColour.g,
                (float) this.backgroundColour.b, (float) this.backgroundColour.a);

        // Update variables
        update();
        for (GameObject<VectorType> object : gameObjects) {
            if (object instanceof KinematicObject) {
                ((KinematicObject<VectorType>) object).tick();
            }
        }
        // Render
        render();

        glfwSwapBuffers(this.display.getId());
        glfwPollEvents();
    }

    private void render() {
    }

    private void dispose() {
        this.display.dispose();
    }

    protected abstract void setup() throws Exception;

    protected abstract void update();

    public final boolean isRunning() {
        return !glfwWindowShouldClose(this.display.getId());
    }

    public Vector2 getSize() {
        return size;
    }

    public String getTitle() {
        return this.title;
    }

    public Colour getBackgroundColour() {
        return backgroundColour;
    }

    public void setBackgroundColour(Colour colour) {
        this.backgroundColour = colour;
    }
}
