package de.pascalschwab.rendering;

import de.pascalschwab.geometry.Rectangle;
import de.pascalschwab.geometry.TextureRectangle;
import de.pascalschwab.opengl.FrameBuffer;
import de.pascalschwab.opengl.VertexArrayObject;
import de.pascalschwab.rendering.mesh.TextureMesh;
import de.pascalschwab.rendering.shader.Shader;
import org.joml.Vector2f;
import org.joml.Vector3f;

public final class Surface extends Rectangle {
    private final FrameBuffer frameBuffer;
    private final Shader shader;
    private float[] UVS = new float[0];
    public Surface(String shaderPath, int width, int height){
        super(null, new Vector3f(0, 0, 0), new Vector2f(width, height), shaderPath);
        this.frameBuffer = new FrameBuffer(width, height);
        this.shader = new Shader(shaderPath);

        updateUVS();
    }

    public FrameBuffer getFrameBuffer() {
        return frameBuffer;
    }

    public Shader getShader() {
        return shader;
    }

    @Override
    protected void setUniforms() {
        this.getUniformsMap().setUniform("txtSampler", 0);
    }

    @Override
    protected void createUniforms() {
        super.getUniformsMap().createUniform("txtSampler");
    }

    public void updateUVS() {
        UVS = new float[]{
                0, 1,
                0, 0,
                1, 0,
                1, 1
        };
        this.setMesh(new TextureMesh(VERTICES, UVS, INDICES));
    }
}
