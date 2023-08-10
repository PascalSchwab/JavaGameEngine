package de.pascalschwab.window;

import de.pascalschwab.gameobjects.GameObject;
import de.pascalschwab.gameobjects.KinematicObject;
import de.pascalschwab.gameobjects.RenderObject;
import de.pascalschwab.projection.Projection;
import de.pascalschwab.projection.camera.Camera;
import de.pascalschwab.rendering.texture.TextureCache;
import de.pascalschwab.standard.enums.Colour;
import de.pascalschwab.standard.enums.Key;
import de.pascalschwab.standard.lists.LayerBasedList;
import org.joml.Vector2f;

import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;

public abstract class Window implements Runnable {
    private final Vector2f size;
    private final String title;
    private final Display display;
    private final Vector2f units;
    public List<GameObject> gameObjects = new LayerBasedList<>();
    private Camera camera = new Camera();
    private Colour backgroundColour = Colour.WHITE;
    private Projection projection;
    private TextureCache textureCache;

    public Window(int width, int height, String title) {
        size = new Vector2f(width, height);
        units = new Vector2f(1f / (width / 2f), 1f / (height / 2f));
        this.title = title;
        this.display = new Display(size, title);
        this.projection = new Projection(width, height);
        this.textureCache = new TextureCache();
        textureCache.createTexture("res/Player.png", new Vector2f(16, 32));
    }

    @Override
    public final void run() {
        init();
        setup();
        loop();
        dispose();
    }

    private void init() {
        // End window when escape pressed
        glfwSetKeyCallback(this.display.getId(), (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true);
        });
/*        // Setup resize window
        glfwSetFramebufferSizeCallback(this.display.getId(), (window, w, h) -> resize(w, h));*/
    }

    private void loop() {
        // Time calculation and game loop
        float deltaTime = 0;
        long lastTime = System.nanoTime();
        while (!glfwWindowShouldClose(this.display.getId())) {
            long now = System.nanoTime();
            float tickDuration = (float) 1000000000 / 60;
            deltaTime += (now - lastTime) / tickDuration;
            lastTime = now;

            if (deltaTime >= 1) {
                tick(deltaTime);
                deltaTime--;
            }
        }
    }

    private void tick(float deltaTime) {
        // Set the clear color
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(this.backgroundColour.r, this.backgroundColour.g,
                this.backgroundColour.b, this.backgroundColour.a);
        // Don't render transparency
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        // Update variables
        update(deltaTime);
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

/*    private void resize(int width, int height) {
        projection.updateProjMatrix(width, height);
    }*/

    public boolean isKeyPressed(Key key) {
        return glfwGetKey(this.display.getId(), key.keyCode) == GLFW_PRESS;
    }

    private void dispose() {
        this.display.dispose();
    }

    protected abstract void setup();

    protected abstract void update(float deltaTime);

    public final boolean isRunning() {
        return !glfwWindowShouldClose(this.display.getId());
    }

    public Vector2f getSize() {
        return size;
    }

    public Vector2f getUnits() {
        return units;
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
        return this.camera;
    }

    public void setMainCamera(Camera camera) {
        this.camera = camera;
    }

    public TextureCache getTextureCache() {
        return this.textureCache;
    }
}