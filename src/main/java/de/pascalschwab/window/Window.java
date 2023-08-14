package de.pascalschwab.window;

import de.pascalschwab.gameobjects.GameObject;
import de.pascalschwab.gameobjects.RenderObject;
import de.pascalschwab.managers.WindowManager;
import de.pascalschwab.projection.Camera;
import de.pascalschwab.projection.Projection;
import de.pascalschwab.rendering.shader.ShaderCache;
import de.pascalschwab.rendering.texture.TextureCache;
import de.pascalschwab.standard.enums.Colour;
import de.pascalschwab.standard.interfaces.IUpdatable;
import de.pascalschwab.standard.lists.LayerBasedList;
import org.joml.Vector2f;

import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;

public abstract class Window implements Runnable {
    public final List<GameObject> gameObjects = new LayerBasedList<>();
    private final Display display;
    private final Vector2f unit;    // Pixel size
    private final Projection projection;
    private final TextureCache textureCache;
    private final ShaderCache shaderCache;
    private Camera camera = new Camera();
    private Colour backgroundColour = Colour.WHITE;

    public Window(int width, int height, String title) {
        this.display = new Display(new Vector2f(width, height), title);
        this.projection = new Projection(width, height);
        this.textureCache = new TextureCache();
        this.shaderCache = new ShaderCache();
        // Calculate Pixel size
        unit = new Vector2f(1f / (width / 2f), 1f / (height / 2f));

        WindowManager.setWindow(this);
    }

    @Override
    public final void run() {
        init();
        setup();
        loop();
        dispose();
    }

    /**
     * Initialize Window
     */
    private void init() {
        // End window when escape pressed
        glfwSetKeyCallback(this.display.getId(), (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true);
        });
/*        // Setup resize window
        glfwSetFramebufferSizeCallback(this.display.getId(), (window, w, h) -> resize(w, h));*/
    }

    /**
     * Framework (60 FPS Limit)
     */
    private void loop() {
        // Time calculation and game loop
        float deltaTime = 0;
        long lastTime = System.nanoTime();
        while (isRunning()) {
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

    /**
     * Function, that will be called every 60 Frames
     */
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
            if (object instanceof IUpdatable) {
                ((IUpdatable) object).update(deltaTime);
            }
        }
        // Render objects
        render();

        glfwSwapBuffers(this.display.getId());
        glfwPollEvents();
    }

/*    private void resize(int width, int height) {
        projection.updateProjMatrix(width, height);
    }*/

    /**
     * Render function
     */
    private void render() {
        for (GameObject object : gameObjects) {
            if (object instanceof RenderObject) {
                ((RenderObject) object).render();
            }
        }
    }

    /**
     * Checks if specific key is pressed
     */

    private void dispose() {
        this.display.dispose();
        this.textureCache.dispose();
    }

    protected abstract void setup();

    protected abstract void update(float deltaTime);

    /**
     * Checks if window is running
     */
    public final boolean isRunning() {
        return !glfwWindowShouldClose(this.display.getId());
    }

    public Vector2f getUnit() {
        return unit;
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

    /**
     * Sets the new main camera (will be changed in the future)
     */
    public void setMainCamera(Camera camera) {
        this.camera = camera;
    }

    public TextureCache getTextureCache() {
        return this.textureCache;
    }

    public ShaderCache getShaderCache() {
        return shaderCache;
    }

    public Display getDisplay() {
        return display;
    }
}