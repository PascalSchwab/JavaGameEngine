package de.pascalschwab.geometry;

import de.pascalschwab.gameobjects.GameObject;
import de.pascalschwab.rendering.Mesh;
import de.pascalschwab.rendering.shader.ShaderProgram;
import de.pascalschwab.standard.interfaces.IRenderable;
import de.pascalschwab.standard.math.Vector2;
import de.pascalschwab.window.Window;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class Triangle extends GameObject implements IRenderable {
    private final ShaderProgram shaderProgram;
    private float[] vertices = {-0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0f, 0.5f, 0f};
    private Mesh mesh = new Mesh(vertices, 3);

    public Triangle(Window window, GameObject parent, Vector2 position, Vector2 size, int zIndex, ShaderProgram shaderProgram) {
        super(window, parent, position, size, zIndex);
        this.shaderProgram = shaderProgram;
    }

    @Override
    public void draw() {
        shaderProgram.bind();
        GL30.glBindVertexArray(mesh.getVertexArrayObjectId());
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, mesh.getVertexCount());
        GL30.glBindVertexArray(0);
        shaderProgram.unbind();
    }
}
