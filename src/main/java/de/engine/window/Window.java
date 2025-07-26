package de.engine.window;

import de.engine.gameobjects.GameObject;
import de.engine.gameobjects.RenderObject;
import de.engine.gameobjects.Surface;
import de.engine.managers.*;
import de.engine.projection.Camera;
import de.engine.projection.Projection;
import de.engine.rendering.shader.ShaderCache;
import de.engine.rendering.texture.TextureCache;
import de.engine.sound.SoundManager;
import de.engine.standard.enums.Colour;
import de.engine.standard.interfaces.IUpdatable;
import de.engine.standard.lists.LayerBasedList;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;

public abstract class Window implements Runnable, IUpdatable {
    public final List<Surface> surfaces;
    public final List<GameObject> gameObjects;
    public final List<GameObject> collisionObjects;

    private final Colour backgroundColour;
    // Rendering
    private final Vector2f unit;
    private final Display display;
    private final Projection projection;
    private Camera camera;
    /*private final PostRenderer postRenderer;*/
    // Caches
    private final TextureCache textureCache;
    private final ShaderCache shaderCache;
    // Manager
    private final SoundManager soundManager;

    public Window(int width, int height, String title, Colour backgroundColour) {
        this.display = new Display(new Vector2f(width, height), title);
        this.projection = new Projection(width, height);
        this.textureCache = new TextureCache();
        this.shaderCache = new ShaderCache();
        this.soundManager = new SoundManager();
        this.camera = new Camera();
        this.surfaces = new LayerBasedList<>();
        this.gameObjects = new LayerBasedList<>();
        this.collisionObjects = new ArrayList<>();
        this.backgroundColour = backgroundColour;
        /*this.postRenderer = new PostRenderer("res/shaders/postRenderer");*/
        // Calculate Pixel size
        unit = new Vector2f(1f / (width / 2f), 1f / (height / 2f));

        WindowManager.setWindow(this);
    }

    public Window(int width, int height, String title) {
        this(width, height, title, Colour.BLACK);
    }

    @Override
    public final void run() {
        init();
        // Time calculation and game loop (60fps)
        float deltaTime = 0;
        long lastTime = System.nanoTime();
        while (isRunning()) {
            DevTools.fps++;
            long now = System.nanoTime();
            float tickDuration = (float) 1000000000 / 60;
            deltaTime += (now - lastTime) / tickDuration;
            lastTime = now;
            // Temporary Fix for moving Window
            if (deltaTime >= 2) {
                deltaTime = 0;
            }
            if (deltaTime >= 1) {
                tick(deltaTime);
                deltaTime--;
                DevTools.fps = 0;
            }
        }
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
            InputManager.addKey(key, action);
        });
    }

    /**
     * Function, that will be called every 60 Frames
     */
    private void tick(float deltaTime) {
        if (DevTools.isActive()) {
            showDevTools();
        }

        // Update variables
        update(deltaTime);
        for (GameObject object : gameObjects) {
            if (object instanceof IUpdatable) {
                ((IUpdatable) object).update(deltaTime);
            }
        }

        // Render
        /*postRenderer.begin();*/
        render();
        /*postRenderer.end();*/

        glfwPollEvents();
        glfwSwapBuffers(this.display.getId());
    }

/*    private void resize(int width, int height) {
        projection.updateProjMatrix(width, height);
    }*/

    /**
     * Render function
     */
    private void render() {
        // Set the clear color
        glClearColor(this.backgroundColour.r, this.backgroundColour.g,
                this.backgroundColour.b, this.backgroundColour.a);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        for (GameObject object : gameObjects) {
            if (object instanceof RenderObject && !(object instanceof Surface)) {
                ((RenderObject) object).render();
            }
        }

        // Render surfaces
        for (Surface surface : surfaces) {
            surface.render();
        }
    }

    /**
     * Checks if specific key is pressed
     */

    private void dispose() {
        this.display.dispose();
        this.textureCache.dispose();
        this.shaderCache.dispose();
        this.soundManager.dispose();

        for (GameObject object : gameObjects) {
            object.dispose();
        }
    }

    private void showDevTools() {
        glfwSetWindowTitle(this.display.getId(), "Current FPS: " + DevTools.fps);
    }

    /**
     * Checks if window is running
     */
    public final boolean isRunning() {
        return !glfwWindowShouldClose(this.display.getId());
    }

    public final Vector2f getUnit() {
        return unit;
    }

    public final Projection getProjection() {
        return projection;
    }

    public final Camera getCamera() {
        return this.camera;
    }

    /**
     * Sets the new main camera (will be changed in the future)
     */
    public final void setMainCamera(Camera camera) {
        this.camera = camera;
    }

    public final TextureCache getTextureCache() {
        return this.textureCache;
    }

    public final ShaderCache getShaderCache() {
        return shaderCache;
    }

    public Vector2f getWindowSize() {
        return this.display.getSize();
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }
}