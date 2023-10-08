package de.pascalschwab.gameobjects;

import de.pascalschwab.managers.WindowManager;
import de.pascalschwab.sound.SoundSource;
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

        // Add Game Object
        WindowManager.getWindow().gameObjects.add(this);
        // Add parent/child relation
        if (this.parent != null) {
            this.parent.children.add(this);
        }
    }

    public GameObject(Vector3f position, Vector2f size) {
        this(null, position, size);
    }

    protected final Matrix4f getTransformMatrix() {
        Vector3f screenPosition = new Vector3f((position.x * WindowManager.getWindow().getUnit().x) - 1f,
                (-position.y * WindowManager.getWindow().getUnit().y) + 1f, position.z);
        return new Matrix4f().identity().translateLocal(screenPosition);
    }

    public final void translate(float x, float y) {
        for (GameObject object : children) {
            if (object.position != this.position) {
                object.addPosition(new Vector3f(x, y, 0));
            }
        }
        this.addPosition(new Vector3f(x, y, 0));
    }

    public final int getId() {
        return id;
    }

    protected final void addChild(GameObject object) {
        this.children.add(object);
    }

    public final List<GameObject> getChildren() {
        return this.children;
    }

    public final GameObject getParent() {
        return parent;
    }

    public final Vector2f getSize() {
        return size;
    }

    public final Vector3f getPosition() {
        return position;
    }
    public void addPosition(Vector3f position){
        this.position.add(position);
    }

    public final float getZIndex() {
        return this.position.z;
    }
}
