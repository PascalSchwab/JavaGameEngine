package de.pascalschwab.gameobjects;

import de.pascalschwab.window.Window;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

// Remove zIndex (only for RenderObjects)
public abstract class GameObject {
    private final int id;
    public List<GameObject> children = new ArrayList<>();
    protected Window window;
    protected GameObject parent;
    protected int zIndex;
    private Vector2f position, size;

    public GameObject(Window window, GameObject parent, Vector2f position, Vector2f size, int zIndex) {
        this.id = window.gameObjects.size();
        this.position = position;
        this.size = size;
        this.zIndex = zIndex;
        this.window = window;
        this.parent = parent;

        // Add gameobject to world
        this.window.gameObjects.add(this);
        // Add parent/child relation
        if (this.parent != null) {
            this.parent.children.add(this);
        }
    }

    public GameObject(Window window, Vector2f position, Vector2f size, int zIndex) {
        this(window, null, position, size, zIndex);
    }

    public Vector2f getSize() {
        return this.size;
    }

    public int getId() {
        return this.id;
    }

    public int getZIndex() {
        return this.zIndex;
    }

    public Vector2f getPosition() {
        return this.position;
    }

    public void setPosition(Vector2f position) {
        for (GameObject object : children) {
            object.position = position;
        }
        this.position = position;
    }
}
