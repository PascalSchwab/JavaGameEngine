package de.pascalschwab.standard;

import de.pascalschwab.standard.math.Vector;
import de.pascalschwab.window.Window;

import java.util.ArrayList;
import java.util.List;

public abstract class GameObject<VectorType extends Vector<VectorType>> {
    private final int id;
    public List<GameObject<VectorType>> children = new ArrayList<>();
    protected Window<VectorType> window;
    protected GameObject<VectorType> parent;
    protected int zIndex;
    private VectorType position, size;

    public GameObject(Window<VectorType> window, GameObject<VectorType> parent, VectorType position, VectorType size, int zIndex) {
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

    public GameObject(Window<VectorType> window, VectorType position, VectorType size, int zIndex) {
        this(window, null, position, size, zIndex);
    }

    public VectorType getSize() {
        return this.size;
    }

    public int getId() {
        return this.id;
    }

    public int getZIndex() {
        return this.zIndex;
    }

    public VectorType getPosition() {
        return this.position;
    }

    public void setPosition(VectorType position) {
        for (GameObject<VectorType> object : children) {
            object.position = position;
        }
        this.position = position;
    }
}
