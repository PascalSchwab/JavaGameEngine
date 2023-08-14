package de.pascalschwab.geometry;

import de.pascalschwab.gameobjects.GameObject;
import de.pascalschwab.gameobjects.RenderObject;
import de.pascalschwab.rendering.shader.ShaderProgram;
import de.pascalschwab.window.Window;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Rectangle extends RenderObject {
    protected final int[] INDICES = new int[]{
            0, 1, 3, 3, 1, 2,
    };

    public Rectangle(Window window, GameObject parent, Vector3f position, Vector2f size, ShaderProgram shaderProgram) {
        super(window, parent, position, size, shaderProgram);
    }

    @Override
    protected void setup() {

    }

    @Override
    public void draw() {
    }

    @Override
    protected void createUniforms() {
    }

    @Override
    protected void setUniforms() {
    }
}
