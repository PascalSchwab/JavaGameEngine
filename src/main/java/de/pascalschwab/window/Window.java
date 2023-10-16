package de.pascalschwab.window;

import de.pascalschwab.gameobjects.GameObject;
import de.pascalschwab.gameobjects.RenderObject;
import de.pascalschwab.managers.DevTools;
import de.pascalschwab.managers.InputManager;
import de.pascalschwab.managers.SoundManager;
import de.pascalschwab.managers.WindowManager;
import de.pascalschwab.projection.Camera;
import de.pascalschwab.projection.Projection;
import de.pascalschwab.rendering.Surface;
import de.pascalschwab.rendering.shader.ShaderCache;
import de.pascalschwab.rendering.texture.TextureCache;
import de.pascalschwab.standard.enums.Colour;
import de.pascalschwab.standard.interfaces.IUpdatable;
import de.pascalschwab.standard.lists.LayerBasedList;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;

public abstract class Window implements Runnable, IUpdatable {
    public final List<GameObject> gameObjects = new LayerBasedList<>();
    public final List<GameObject> collisionObjects = new ArrayList<>();
    private final Display display;
    private final Vector2f unit;    // Pixel size
    private final Projection projection;
    private final TextureCache textureCache;
    private final ShaderCache shaderCache;
    private final SoundManager soundManager;
    private Camera camera = new Camera();
    private Colour backgroundColour = Colour.WHITE;
    private Surface surface;

    public Window(int width, int height, String title) {
        this.display = new Display(new Vector2f(width, height), title);
        this.projection = new Projection(width, height);
        this.textureCache = new TextureCache();
        this.shaderCache = new ShaderCache();
        this.soundManager = new SoundManager();
        // Calculate Pixel size
        unit = new Vector2f(1f / (width / 2f), 1f / (height / 2f));

        WindowManager.setWindow(this);

        this.surface = new Surface("res/shaders/frame", width, height);
    }

    @Override
    public final void run() {
        init();
        setup();
        for (int i = 0; i < gameObjects.size(); i++) {
            if (gameObjects.get(i) instanceof IUpdatable) {
                ((IUpdatable) gameObjects.get(i)).setup();
            }
        }
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
            InputManager.addKey(key, action);
        });
    }

    /**
     * Framework (60 FPS Limit)
     */
    private void loop() {
        // Time calculation and game loop
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

        render();
    }

/*    private void resize(int width, int height) {
        projection.updateProjMatrix(width, height);
    }*/

    /**
     * Render function
     */
    private void render() {
        this.surface.begin();
        // Set the clear color
        glClearColor(this.backgroundColour.r, this.backgroundColour.g,
                this.backgroundColour.b, this.backgroundColour.a);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        for (GameObject object : gameObjects) {
            if (object instanceof RenderObject) {
                ((RenderObject) object).render();
            }
        }

        this.surface.end();

        glfwPollEvents();
        glfwSwapBuffers(this.display.getId());
    }

    /**
     * Checks if specific key is pressed
     */

    private void dispose() {
        this.display.dispose();
        this.textureCache.dispose();
    }

    private void showDevTools() {
        glfwSetWindowTitle(this.getDisplay().getId(), "Current FPS: " + DevTools.fps);
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

    public final Colour getBackgroundColour() {
        return backgroundColour;
    }

    public final void setBackgroundColour(Colour colour) {
        this.backgroundColour = colour;
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

    public final Display getDisplay() {
        return display;
    }
}