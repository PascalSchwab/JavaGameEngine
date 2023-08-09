package de.pascalschwab.window;

import de.pascalschwab.gameobjects.GameObject;
import de.pascalschwab.gameobjects.KinematicObject;
import de.pascalschwab.gameobjects.RenderObject;
import de.pascalschwab.projection.Projection;
import de.pascalschwab.projection.camera.Camera;
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
    private Camera camera = new Camera();
    //private TextureCache textureCache = new TextureCache();

    public Window(int width, int height, String title) {
        size = new Vector2f(width, height);
        this.title = title;
        this.display = new Display(size, title);
        this.projection = new Projection(width, height);
        this.camera.setPosition(0.5f, 0.5f, 0.5f);
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
        // End window when escape pressed
        glfwSetKeyCallback(this.display.getId(), (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true);
        });
        // Setup resize window
        glfwSetFramebufferSizeCallback(this.display.getId(), (window, w, h) -> resize(w, h));
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
                tick(delta);
                delta--;
            }
        }
    }

    private void tick(double diffMilliTime) {
        // Set the clear color
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor((float) this.backgroundColour.r, (float) this.backgroundColour.g,
                (float) this.backgroundColour.b, (float) this.backgroundColour.a);

        // Update variables
        input(diffMilliTime);
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

    private void resize(int width, int height) {
        projection.updateProjMatrix(width, height);
    }

    public boolean isKeyPressed(int keyCode) {
        return glfwGetKey(this.display.getId(), keyCode) == GLFW_PRESS;
    }

    private void input(double diffTimeMillis) {
        final float MOUSE_SENSITIVITY = 0.1f;
        final float MOVEMENT_SPEED = 0.005f;

        float move = (float) (diffTimeMillis * MOVEMENT_SPEED);
        if (isKeyPressed(GLFW_KEY_W)) {
            camera.moveUp(move);
        } else if (isKeyPressed(GLFW_KEY_S)) {
            camera.moveDown(move);
        }
        if (isKeyPressed(GLFW_KEY_A)) {
            camera.moveLeft(move);
        } else if (isKeyPressed(GLFW_KEY_D)) {
            camera.moveRight(move);
        }
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

    public Camera getCamera() {
        return camera;
    }

    /*    public TextureCache getTextureCache() {
        return this.textureCache;
    }*/
}