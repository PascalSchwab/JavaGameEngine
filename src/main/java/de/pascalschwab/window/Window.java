package de.pascalschwab.window;

import de.pascalschwab.gameobjects.GameObject;
import de.pascalschwab.gameobjects.KinematicObject;
import de.pascalschwab.gameobjects.RenderObject;
import de.pascalschwab.projection.Projection;
import de.pascalschwab.standard.enums.Colour;
import de.pascalschwab.standard.lists.LayerBasedList;
import org.joml.Vector2f;

import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;

public abstract class Window implements Runnable {
    private final Vector2f size;
    private final String title;
    private final Display display;
    public List<GameObject> gameObjects = new LayerBasedList<>();
    private Colour backgroundColour = Colour.WHITE;
    private Projection projection;
    //private TextureCache textureCache = new TextureCache();

    public Window(int width, int height, String title) {
        size = new Vector2f(width, height);
        this.title = title;
        this.display = new Display(size, title, () -> {
            resize();
            return null;
        });
        this.projection = new Projection(width, height);
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
        for (GameObject object : gameObjects) {
            if (object instanceof KinematicObject) {
                ((KinematicObject) object).tick();
            }
        }
        // Render
        render();

        glfwSwapBuffers(this.display.getId());
        glfwPollEvents();
    }

    private void render() {
        for (GameObject object : gameObjects) {
            if (object instanceof RenderObject) {
                ((RenderObject) object).render();
            }
        }
    }

    public void resize() {
        projection.updateProjMatrix((int) this.display.getSize().x, (int) this.display.getSize().y);
    }

    private void dispose() {
        this.display.dispose();
    }

    protected abstract void setup() throws Exception;

    protected abstract void update();

    public final boolean isRunning() {
        return !glfwWindowShouldClose(this.display.getId());
    }

    public Vector2f getSize() {
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

    public Projection getProjection() {
        return projection;
    }

    /*    public TextureCache getTextureCache() {
        return this.textureCache;
    }*/
}
