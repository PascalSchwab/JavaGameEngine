package de.pascalschwab.gameobjects;

import de.pascalschwab.rendering.mesh.Mesh;
import de.pascalschwab.rendering.shader.ShaderProgram;
import de.pascalschwab.rendering.shader.UniformsMap;
import de.pascalschwab.window.Window;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public abstract class RenderObject extends GameObject {
    private final ShaderProgram shaderProgram;
    private UniformsMap uniformsMap;
    private Mesh mesh;

    public RenderObject(Window window, GameObject parent, Vector2f position, Vector2f size, float zIndex, ShaderProgram shaderProgram) {
        super(window, parent, position, size, zIndex);
        this.shaderProgram = shaderProgram;

        uniformsMap = new UniformsMap(shaderProgram.getId());
        createUniforms();
        /*uniformsMap.createUniform("projectionMatrix");*/
        uniformsMap.createUniform("viewMatrix");
        uniformsMap.createUniform("transformation");
    }

    public final void render() {
        shaderProgram.bind();
        /*uniformsMap.setUniform("projectionMatrix", window.getProjection().getProjMatrix());*/
        uniformsMap.setUniform("viewMatrix", window.getCamera().getViewMatrix());
        getUniformsMap().setUniform("transformation", getTransformationMatrix());
        setUniforms();

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

    protected void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }
}
