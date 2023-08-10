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
    private final float[] UVS;
    private final Texture texture;

    public Sprite(Window window, GameObject parent, Vector2f position, Vector2f size, float zIndex, String texturePath) {
        this(window, parent, position, size, zIndex, texturePath, new Vector2f(0, 0));
    }

    public Sprite(Window window, GameObject parent, Vector2f position, Vector2f size, float zIndex, String texturePath, Vector2f offset) {
        super(window, parent, position, size, zIndex, new TextureShader());
        texture = window.getTextureCache().getTexture(texturePath);

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
}
