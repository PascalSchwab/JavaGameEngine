package de.pascalschwab.gameobjects;

import de.pascalschwab.window.Window;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public abstract class GameObject {
    protected final float[] VERTICES;
    private final int id;
    private final Vector3f position;
    private final Vector2f size;
    public List<GameObject> children = new ArrayList<>();
    protected Window window;
    protected GameObject parent;

    public GameObject(Window window, GameObject parent, Vector3f position, Vector2f size) {
        this.id = window.gameObjects.size();
        this.position = position;
        this.size = size;
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
                0, 0, this.position.z,
                0, -1 * unitSize.y, this.position.z,
                unitSize.x, -1 * unitSize.y, this.position.z,
                unitSize.x, 0, this.position.z
        };

        setup();
    }

    public GameObject(Window window, Vector3f position, Vector2f size) {
        this(window, null, position, size);
    }

    protected abstract void setup();

    public Vector2f getSize() {
        return this.size;
    }

    public int getId() {
        return this.id;
    }

    public Vector3f getPosition() {
        return this.position;
    }

    public float getZIndex() {
        return this.position.z;
    }

    protected Matrix4f getTransformationMatrix() {
        Vector3f screenPosition = new Vector3f((position.x * window.getUnit().x) - 1f,
                (-position.y * window.getUnit().y) + 1f, position.z);
        return new Matrix4f().identity().translateLocal(screenPosition);
    }

    public void translate(float x, float y) {
        for (GameObject object : children) {
            if (object.position != this.position) {
                object.position.add(new Vector3f(x, y, 0));
            }
        }
        this.position.add(new Vector3f(x, y, 0));
    }
}
