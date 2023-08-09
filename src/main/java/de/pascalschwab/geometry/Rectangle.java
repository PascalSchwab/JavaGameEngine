package de.pascalschwab.geometry;

import de.pascalschwab.gameobjects.GameObject;
import de.pascalschwab.gameobjects.RenderObject;
import de.pascalschwab.rendering.mesh.Mesh;
import de.pascalschwab.rendering.shader.ShaderProgram;
import de.pascalschwab.standard.math.Vector2;
import de.pascalschwab.window.Window;

public class Rectangle extends RenderObject {
    public Rectangle(Window window, GameObject parent, Vector2 position, Vector2 size, int zIndex, ShaderProgram shaderProgram, Mesh mesh) {
        super(window, parent, position, size, zIndex, shaderProgram, mesh);
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
