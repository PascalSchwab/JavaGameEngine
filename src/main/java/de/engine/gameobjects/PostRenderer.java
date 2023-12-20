package de.engine.gameobjects;

import de.engine.managers.WindowManager;
import de.engine.rendering.opengl.FrameBuffer;
import de.engine.rendering.mesh.TextureMesh;
import de.engine.rendering.shader.Shader;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL31.glDrawElementsInstanced;

public final class PostRenderer extends GameObject {
    private final static int[] INDICES = new int[]{
            0, 1, 3, 3, 1, 2,
    };
    private final static float[] UVS = new float[]{
            0, 1,
            0, 0,
            1, 0,
            1, 1
    };
    private final float[] VERTICES;
    private final Shader shader;
    private final FrameBuffer frameBuffer;
    private final TextureMesh mesh;

    public PostRenderer(String shaderPath) {
        super(new Vector3f(0, 0, 0), WindowManager.getWindow().getWindowSize());
        this.frameBuffer = new FrameBuffer((int) size.x, (int) size.y);
        this.shader = WindowManager.getWindow().getShaderCache().getShader(shaderPath);

        Vector2f unitSize = new Vector2f(size.x * WindowManager.getWindow().getUnit().x, size.y * WindowManager.getWindow().getUnit().y);
        VERTICES = new float[]{
                0, 0, this.position.z,
                0, -1 * unitSize.y, this.position.z,
                unitSize.x, -1 * unitSize.y, this.position.z,
                unitSize.x, 0, this.position.z
        };
        this.mesh = new TextureMesh(VERTICES, UVS, INDICES);

        createUniforms();
    }

    public void begin() {
        this.frameBuffer.bind();
    }

    public void end() {
        this.frameBuffer.unbind();
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        this.shader.bind();
        setUniforms();
        this.mesh.getVertexArrayObject().bind();
        glBindTexture(GL_TEXTURE_2D, this.frameBuffer.getTexture().getId());
        glDrawElementsInstanced(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0, mesh.getInstanceCount());
        this.mesh.getVertexArrayObject().unbind();
        this.shader.unbind();
    }

    public void setUniforms() {
        this.shader.getUniformsMap().setUniform("viewMatrix", WindowManager.getWindow().getCamera().getViewMatrix());
        this.shader.getUniformsMap().setUniform("transformMatrix", getTransformMatrix());
        this.shader.getUniformsMap().setUniform("txtSampler", 0);
    }

    public void createUniforms() {
        this.shader.getUniformsMap().createUniform("viewMatrix");
        this.shader.getUniformsMap().createUniform("transformMatrix");
        this.shader.getUniformsMap().createUniform("txtSampler");
    }

    @Override
    public void dispose() {
        this.shader.dispose();
        this.mesh.dispose();
        this.frameBuffer.dispose();
    }
}
