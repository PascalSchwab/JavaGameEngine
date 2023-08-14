package de.pascalschwab.gameobjects;

import de.pascalschwab.managers.WindowManager;
import de.pascalschwab.rendering.mesh.Mesh;
import de.pascalschwab.rendering.shader.Shader;
import de.pascalschwab.rendering.shader.UniformsMap;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public abstract class RenderObject extends GameObject {
    private final Shader shader;
    private final UniformsMap uniformsMap;
    private boolean visible;
    private Mesh mesh;

    public RenderObject(GameObject parent, Vector3f position, Vector2f size, String shaderPath, boolean visible) {
        super(parent, position, size);
        this.shader = WindowManager.getWindow().getShaderCache().getShader(shaderPath);
        this.visible = visible;

        this.uniformsMap = new UniformsMap(this.shader.getId());
        createUniforms();
    }

    public RenderObject(GameObject parent, Vector3f position, Vector2f size, String shaderPath) {
        this(parent, position, size, shaderPath, true);
    }

    public final void render() {
        if (this.visible) {
            bind();
            setUniforms();
            draw();
            unbind();
        }
    }

    protected void draw() {
        glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);
    }

    protected void createUniforms() {
        uniformsMap.createUniform("viewMatrix");
        uniformsMap.createUniform("transformMatrix");
    }

    protected void setUniforms() {
        uniformsMap.setUniform("viewMatrix", WindowManager.getWindow().getCamera().getViewMatrix());
        uniformsMap.setUniform("transformMatrix", getTransformMatrix());
    }

    protected void bind() {
        this.shader.bind();
        glBindVertexArray(mesh.getVertexArrayObjectId());
    }

    protected void unbind() {
        glBindVertexArray(0);
        this.shader.unbind();
    }

    protected void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    protected UniformsMap getUniformsMap() {
        return this.uniformsMap;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
