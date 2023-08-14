package de.pascalschwab.gameobjects;

import de.pascalschwab.managers.WindowManager;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public abstract class GameObject {
    protected final Vector3f position;
    protected final Vector2f size;
    private final List<GameObject> children = new ArrayList<>();
    private final int id;
    private final GameObject parent;

    public GameObject(GameObject parent, Vector3f position, Vector2f size) {
        this.id = WindowManager.getWindow().gameObjects.size();
        this.parent = parent;
        this.position = position;
        this.size = size;

        // Add gameobject to world
        WindowManager.getWindow().gameObjects.add(this);
        // Add parent/child relation
        if (this.parent != null) {
            this.parent.children.add(this);
        }

        setup();
    }

    public GameObject(Vector3f position, Vector2f size) {
        this(null, position, size);
    }

    public abstract void setup();

    protected Matrix4f getTransformMatrix() {
        Vector3f screenPosition = new Vector3f((position.x * WindowManager.getWindow().getUnit().x) - 1f,
                (-position.y * WindowManager.getWindow().getUnit().y) + 1f, position.z);
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

    public int getId() {
        return id;
    }

    protected void addChild(GameObject object) {
        this.children.add(object);
    }

    public List<GameObject> getChildren() {
        return this.children;
    }

    public GameObject getParent() {
        return parent;
    }

    public Vector2f getSize() {
        return size;
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getZIndex() {
        return this.position.z;
    }
}
