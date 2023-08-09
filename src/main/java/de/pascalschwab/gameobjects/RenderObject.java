package de.pascalschwab.gameobjects;

import de.pascalschwab.rendering.mesh.Mesh;
import de.pascalschwab.rendering.shader.ShaderProgram;
import de.pascalschwab.rendering.shader.UniformsMap;
import de.pascalschwab.standard.math.Vector2;
import de.pascalschwab.window.Window;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public abstract class RenderObject extends GameObject {
    private final ShaderProgram shaderProgram;
    private UniformsMap uniformsMap;
    private Mesh mesh;

    public RenderObject(Window window, GameObject parent, Vector2 position, Vector2 size, int zIndex, ShaderProgram shaderProgram, Mesh mesh) {
        super(window, parent, position, size, zIndex);
        this.shaderProgram = shaderProgram;
        this.mesh = mesh;

        uniformsMap = new UniformsMap(shaderProgram.getId());
        createUniforms();
        uniformsMap.createUniform("projectionMatrix");
    }

    public final void render() {
        shaderProgram.bind();
        setUniforms();
        uniformsMap.setUniform("projectionMatrix", window.getProjection().getProjMatrix());

        draw();

        glBindVertexArray(mesh.getVertexArrayObjectId());
        glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);

        glBindVertexArray(0);
        shaderProgram.unbind();
    }

    protected abstract void draw();

    protected abstract void createUniforms();

    protected abstract void setUniforms();

    protected UniformsMap getUniformsMap() {
        return this.uniformsMap;
    }

    protected Mesh getMesh() {
        return this.mesh;
    }
}
