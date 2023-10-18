package de.pascalschwab.tilemap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.pascalschwab.geometry.Rectangle;
import de.pascalschwab.managers.FileManager;
import de.pascalschwab.managers.WindowManager;
import de.pascalschwab.rendering.mesh.TextureMesh;
import de.pascalschwab.rendering.texture.Texture;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public final class TileMap extends Rectangle {
    private final List<Layer> layers = new ArrayList<>();
    private Texture texture;
    private float[] UVS = new float[0];

    public TileMap(Vector2f tileSize) {
        super(null, new Vector3f(0, 0, 0), tileSize, "res/shaders/tilemap");
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
        this.getShader().getUniformsMap().setUniform("txtSampler", 0);
        this.getShader().getUniformsMap().setUniform("positionUSize", new float[]{this.size.x * WindowManager.getWindow().getUnit().x,
                this.size.y * WindowManager.getWindow().getUnit().y});
        this.getShader().getUniformsMap().setUniform("tilePositions", getTilePositions());
        this.getShader().getUniformsMap().setUniform("textureUSize", new float[]{texture.getFrameSize().x * texture.getUnits().x,
                texture.getFrameSize().y * texture.getUnits().y});
        this.getShader().getUniformsMap().setUniform("textureSize", new float[]{texture.getSize().x, texture.getSize().y});
        this.getShader().getUniformsMap().setUniform("tileIDs", getTileIDs());
    }

    @Override
    protected void createUniforms() {
        super.createUniforms();
        this.getShader().getUniformsMap().createUniform("txtSampler");
        this.getShader().getUniformsMap().createUniform("tilePositions");
        this.getShader().getUniformsMap().createUniform("positionUSize");
        this.getShader().getUniformsMap().createUniform("textureUSize");
        this.getShader().getUniformsMap().createUniform("textureSize");
        this.getShader().getUniformsMap().createUniform("tileIDs");
    }

    private void updateUVS() {
        UVS = new float[]{
                0, 0,
                0, texture.getFrameSize().y * texture.getUnits().y,
                texture.getFrameSize().x * texture.getUnits().x, texture.getFrameSize().y * texture.getUnits().y,
                texture.getFrameSize().x * texture.getUnits().x, 0
        };
        this.setMesh(new TextureMesh(VERTICES, UVS, INDICES, tiles.size()));
    }

    private float[] getTilePositions() {
        float[] positions = new float[tiles.size() * 2];
        for (int i = 0; i < tiles.size(); i++) {
            positions[i * 2] = tiles.get(i).getPosition().x;
            positions[i * 2 + 1] = tiles.get(i).getPosition().y;
        }
        return positions;
    }

    private float[] getTileIDs() {
        float[] ids = new float[tiles.size()];
        for (int i = 0; i < tiles.size(); i++) {
            ids[i] = tiles.get(i).getTileID();
        }
        return ids;
    }

    public TileMap loadTiledFile(String path) {
        try {
            String jsonText = FileManager.getTextFromFile(path);
            JsonObject jsonTilemap = JsonParser.parseString(jsonText).getAsJsonObject();
            System.out.println(jsonTilemap);
        } catch (Exception e) {
            throw new RuntimeException("Json File from the path: " + path + " cannot been loaded.");
        }
        /*        updateUVS();*/
        return this;
    }

    public TileMap loadJson(String path) {
        try {
            String jsonText = FileManager.getTextFromFile(path);
            JsonObject jsonTilemap = JsonParser.parseString(jsonText).getAsJsonObject();

            {
                // Get Texture from Json
                JsonObject jsonTileSize = jsonTilemap.get("tileSize").getAsJsonObject();
                Vector2f textureTileSize = new Vector2f(jsonTileSize.get("x").getAsInt(), jsonTileSize.get("y").getAsInt());
                String texturePath = jsonTilemap.get("texturePath").getAsString();
                this.texture = WindowManager.getWindow().getTextureCache().getTexture(texturePath, textureTileSize);
            }

            // Get Tiles
            JsonArray jsonTiles = jsonTilemap.get("tiles").getAsJsonArray();
            jsonTiles.forEach((object) -> {
                JsonObject jsonTile = object.getAsJsonObject();
                Vector2f tilePosition = new Vector2f(jsonTile.get("x").getAsInt(), jsonTile.get("y").getAsInt());
                this.tiles.add(new Tile(tilePosition, jsonTile.get("id").getAsInt()));
            });
        } catch (Exception e) {
            throw new RuntimeException("Json File from the path: " + path + " cannot been loaded.");
        }

        updateUVS();
        return this;
    }
}
