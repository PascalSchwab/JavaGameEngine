package de.engine.geometry;

import de.engine.gameobjects.GameObject;
import de.engine.gameobjects.RenderObject;
import de.engine.managers.WindowManager;
import org.joml.Vector2f;
import org.joml.Vector3f;

public abstract class Triangle extends RenderObject {
    protected final int[] INDICES = new int[]{
            0, 1, 2
    };
    protected final float[] VERTICES;

    public Triangle(GameObject parent, Vector3f position, Vector2f size, String shaderPath) {
        super(parent, position, size, shaderPath);

        Vector2f unitSize = new Vector2f(size.x * WindowManager.getWindow().getUnit().x, size.y * WindowManager.getWindow().getUnit().y);
        VERTICES = new float[]{
                unitSize.x / 2, 0, this.position.z,
                0, -1 * unitSize.y, this.position.z,
                unitSize.x, -1 * unitSize.y, this.position.z,
        };
    }
}
