package de.pascalschwab.tilemap;

import de.pascalschwab.geometry.Rectangle;
import de.pascalschwab.managers.WindowManager;
import de.pascalschwab.rendering.mesh.TileMapMesh;
import de.pascalschwab.rendering.texture.Texture;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class TileMap extends Rectangle {
    public static final int TILE_SIZE = 64;
    public final int[] tiles = new int[]{1, 2, 1, 2, 1, 2, 3, 3, 3};
    private final Texture texture;
    private float[] UVS = new float[0];

    public TileMap(Vector3f position, Vector2f size) {
        super(null, position, size, "res/shaders/tilemap");
        //tiles = new int[(int) (size.x / TILE_SIZE * size.y / TILE_SIZE)];
        texture = WindowManager.getWindow().getTextureCache().getTexture("res/tilemap.png", new Vector2f(16, 16));
        updateUVS(new Vector2f(1, 0));
    }

    @Override
    public void bind() {
        super.bind();
        glActiveTexture(GL_TEXTURE0);
        texture.bind();
    }

    @Override
    protected void setUniforms() {
        super.setUniforms();
        this.getUniformsMap().setUniform("txtSampler", 0);
    }

    @Override
    protected void createUniforms() {
        super.createUniforms();
        super.getUniformsMap().createUniform("txtSampler");
    }

    protected final void updateUVS(Vector2f offset) {
        Vector2f topLeft = new Vector2f((texture.getFrameSize().x * texture.getUnits().x) * offset.x,
                (texture.getFrameSize().y * texture.getUnits().y) * offset.y);
        UVS = new float[]{
                topLeft.x, topLeft.y,
                topLeft.x, topLeft.y + (texture.getFrameSize().y * texture.getUnits().y),
                topLeft.x + (texture.getFrameSize().x * texture.getUnits().x), topLeft.y + (texture.getFrameSize().y * texture.getUnits().y),
                topLeft.x + (texture.getFrameSize().x * texture.getUnits().x), topLeft.y
        };
        this.setMesh(new TileMapMesh(VERTICES, UVS, INDICES, tiles));
    }
}
