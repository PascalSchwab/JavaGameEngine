package de.pascalschwab.gameobjects;

import de.pascalschwab.window.Window;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

// Remove zIndex (only for RenderObjects)
public abstract class GameObject {
    protected final float[] VERTICES;
    private final int id;
    public List<GameObject> children = new ArrayList<>();
    protected Window window;
    protected GameObject parent;
    protected float zIndex;
    private Vector2f position, size;

    public GameObject(Window window, GameObject parent, Vector2f position, Vector2f size, float zIndex) {
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

        Vector2f unitSize = new Vector2f(size.x * window.getUnit().x, size.y * window.getUnit().y);
        VERTICES = new float[]{
                0, 0, zIndex,
                0, -1 * unitSize.y, zIndex,
                unitSize.x, -1 * unitSize.y, zIndex,
                unitSize.x, 0, zIndex
        };

        setup();
    }

    public GameObject(Window window, Vector2f position, Vector2f size, int zIndex) {
        this(window, null, position, size, zIndex);
    }

    protected abstract void setup();

    public Vector2f getSize() {
        return this.size;
    }

    public int getId() {
        return this.id;
    }

    public float getZIndex() {
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

    protected Matrix4f getTransformationMatrix() {
        Vector3f screenPosition = new Vector3f((position.x * window.getUnit().x) - 1f,
                (-position.y * window.getUnit().y) + 1f, zIndex);
        return new Matrix4f().identity().translateLocal(screenPosition);
    }
}
