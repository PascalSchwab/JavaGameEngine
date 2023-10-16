package de.pascalschwab.gameobjects;

import de.pascalschwab.managers.WindowManager;
import de.pascalschwab.rendering.mesh.Mesh;
import de.pascalschwab.rendering.shader.Shader;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL31.glDrawElementsInstanced;

public abstract class RenderObject extends GameObject {
    private final Shader shader;
    private boolean visible;
    private Mesh mesh;

    public RenderObject(GameObject parent, Vector3f position, Vector2f size, String shaderPath, boolean visible) {
        super(parent, position, size);
        this.shader = WindowManager.getWindow().getShaderCache().getShader(shaderPath);
        this.visible = visible;

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
        glDrawElementsInstanced(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0, mesh.getInstanceCount());
    }

    protected void createUniforms() {
        this.shader.getUniformsMap().createUniform("viewMatrix");
        this.shader.getUniformsMap().createUniform("transformMatrix");
    }

    protected void setUniforms() {
        this.shader.getUniformsMap().setUniform("viewMatrix", WindowManager.getWindow().getCamera().getViewMatrix());
        this.shader.getUniformsMap().setUniform("transformMatrix", getTransformMatrix());
    }

    protected void bind() {
        this.shader.bind();
        this.mesh.getVertexArrayObject().bind();
    }

    protected void unbind() {
        this.mesh.getVertexArrayObject().unbind();
        this.shader.unbind();
    }

    protected final void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public final boolean isVisible() {
        return visible;
    }

    public final void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void dispose() {
        this.shader.dispose();
        this.mesh.dispose();
    }

    public Shader getShader() {
        return shader;
    }
}
