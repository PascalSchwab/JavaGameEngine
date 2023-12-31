package de.engine.geometry;

import de.engine.gameobjects.GameObject;
import de.engine.gameobjects.RenderObject;
import de.engine.managers.WindowManager;
import org.joml.Vector2f;
import org.joml.Vector3f;

public abstract class Rectangle extends RenderObject {
    protected final int[] INDICES = new int[]{
            0, 1, 3, 3, 1, 2,
    };
    protected final float[] VERTICES;

    public Rectangle(GameObject parent, Vector3f position, Vector2f size, String shaderPath, boolean visible) {
        super(parent, position, size, shaderPath, visible);

        Vector2f unitSize = new Vector2f(size.x * WindowManager.getWindow().getUnit().x, size.y * WindowManager.getWindow().getUnit().y);
        VERTICES = new float[]{
                0, 0, this.position.z,
                0, -1 * unitSize.y, this.position.z,
                unitSize.x, -1 * unitSize.y, this.position.z,
                unitSize.x, 0, this.position.z
        };
    }

    public Rectangle(GameObject parent, Vector3f position, Vector2f size, String shaderPath) {
        this(parent, position, size, shaderPath, true);
    }
}
