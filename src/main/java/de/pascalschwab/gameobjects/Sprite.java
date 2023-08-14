package de.pascalschwab.gameobjects;

import de.pascalschwab.geometry.Rectangle;
import de.pascalschwab.rendering.mesh.TextureMesh;
import de.pascalschwab.rendering.shader.TextureShader;
import de.pascalschwab.rendering.texture.Texture;
import de.pascalschwab.window.Window;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class Sprite extends Rectangle {
    protected final Texture texture;
    private float[] UVS = new float[0];

    public Sprite(Window window, GameObject parent, Vector3f position, Vector2f size, String texturePath, Vector2f frameSize) {
        this(window, parent, position, size, texturePath, frameSize, new Vector2f(0, 0));
    }

    public Sprite(Window window, GameObject parent, Vector3f position, Vector2f size,
                  String texturePath, Vector2f frameSize, Vector2f offset) {
        super(window, parent, position, size, new TextureShader());
        texture = window.getTextureCache().getTexture(texturePath, frameSize);
        updateUVS(offset);
    }

    @Override
    public void draw() {
        super.draw();
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

    protected void updateUVS(Vector2f offset) {
        Vector2f topLeft = new Vector2f((texture.getFrameSize().x * texture.getUnits().x) * offset.x,
                (texture.getFrameSize().y * texture.getUnits().y) * offset.y);
        UVS = new float[]{
                topLeft.x, topLeft.y,
                topLeft.x, topLeft.y + (texture.getFrameSize().y * texture.getUnits().y),
                topLeft.x + (texture.getFrameSize().x * texture.getUnits().x), topLeft.y + (texture.getFrameSize().y * texture.getUnits().y),
                topLeft.x + (texture.getFrameSize().x * texture.getUnits().x), topLeft.y
        };
        this.setMesh(new TextureMesh(VERTICES, UVS, INDICES));
    }
}
