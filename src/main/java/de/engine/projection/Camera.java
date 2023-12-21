package de.engine.projection;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {
    private Vector3f position = new Vector3f();
    private Vector3f right = new Vector3f();
    private Vector3f up = new Vector3f();
    private Matrix4f viewMatrix;

    public Camera() {
        this.viewMatrix = new Matrix4f().identity();
    }

    public Vector3f getPosition() {
        return this.position;
    }

    public Matrix4f getViewMatrix() {
        return this.viewMatrix;
    }

    public void moveDown(float increment) {
        viewMatrix.positiveY(up).mul(increment);
        position.add(up);
        recalculate();
    }

    public void moveLeft(float increment) {
        viewMatrix.positiveX(right).mul(increment);
        position.add(right);
        recalculate();
    }

    public void moveRight(float increment) {
        viewMatrix.positiveX(right).negate().mul(increment);
        position.add(right);
        recalculate();
    }

    public void moveUp(float increment) {
        viewMatrix.positiveY(up).negate().mul(increment);
        position.add(up);
        recalculate();
    }

    private void recalculate() {
        viewMatrix.identity().translate(-position.x, -position.y, -position.z);
    }

    public void setPosition(float x, float y, float z) {
        position.set(x, y, z);
        recalculate();
    }

    public boolean isInViewport(Vector3f position) {
        return false;
    }
}
