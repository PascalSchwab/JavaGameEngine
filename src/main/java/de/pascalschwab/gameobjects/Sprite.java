package de.pascalschwab.gameobjects;

import de.pascalschwab.geometry.Rectangle;
import de.pascalschwab.rendering.mesh.TextureMesh;
import de.pascalschwab.rendering.shader.TextureShader;
import de.pascalschwab.rendering.texture.Texture;
import de.pascalschwab.window.Window;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class Sprite extends Rectangle {
    private final float[] textCoords = new float[]{
            0.0f, 0.0f,
            0.0f, 0.1f,
            0.1f, 0.1f,
            0.1f, 0.0f
    };
    private final String texturePath;

    public Sprite(Window window, GameObject parent, Vector2f position, Vector2f size, int zIndex, String texturePath) {
        super(window, parent, position, size, zIndex, new TextureShader());
        this.texturePath = texturePath;
        this.setMesh(new TextureMesh(VERTICES, textCoords, INDICES));
    }

    @Override
    public void draw() {
        super.draw();
        Texture texture = window.getTextureCache().getTexture(texturePath);
        glActiveTexture(GL_TEXTURE0);
        texture.bind();
    }

    @Override
    protected void setUniforms() {
        super.setUniforms();
        super.getUniformsMap().setUniform("txtSampler", 0);
    }

    @Override
    protected void createUniforms() {
        super.createUniforms();
        super.getUniformsMap().createUniform("txtSampler");
    }
}
