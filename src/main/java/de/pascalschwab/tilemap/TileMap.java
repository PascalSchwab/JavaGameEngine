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
import org.joml.Vector2i;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public final class TileMap extends Rectangle {
    private final List<Layer> layers = new ArrayList<>();
    private final Vector2f tileUnit;
    private Texture texture;
    private float[] UVS = new float[0];
    private List<Tile> visibleTiles = new ArrayList<>();

    public TileMap(Vector2f tileSize) {
        super(null, new Vector3f(0, 0, 0), tileSize, "res/shaders/tilemap");
        this.tileUnit = new Vector2f(tileSize.x * WindowManager.getWindow().getUnit().x,
                tileSize.y * WindowManager.getWindow().getUnit().y);
    }

    @Override
    public void bind() {
        super.bind();
        glActiveTexture(GL_TEXTURE0);
        texture.bind();

        // Calculate visible tiles
        this.visibleTiles = getVisibleTiles();
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
        this.setMesh(new TextureMesh(VERTICES, UVS, INDICES, this.visibleTiles.size()));
    }

    private float[] getTilePositions() {
        float[] positions = new float[this.visibleTiles.size() * 2];
        for (int i = 0; i < this.visibleTiles.size(); i++) {
            positions[i * 2] = this.visibleTiles.get(i).getPosition().x;
            positions[i * 2 + 1] = this.visibleTiles.get(i).getPosition().y;
        }
        return positions;
    }

    private float[] getTileIDs() {
        float[] ids = new float[this.visibleTiles.size()];
        for (int i = 0; i < this.visibleTiles.size(); i++) {
            ids[i] = this.visibleTiles.get(i).getTileID();
        }
        return ids;
    }

    private List<Tile> getVisibleTiles() {
        List<Tile> tiles = new ArrayList<>();
        Vector2f start = layers.get(0).getTilePositionFromWorldPosition(WindowManager.getWindow().getCamera().getPosition());
        Vector2f end = new Vector2f(start.x + 15, start.y + 8);
        for (int y = (int) start.y; y <= end.y; y++) {
            for (int x = (int) start.x; x <= end.x; x++) {
                if (layers.get(0).getTiles().containsKey(y) && layers.get(0).getTiles().get(y).containsKey(x)) {
                    tiles.add(layers.get(0).getTiles().get(y).get(x));
                }
            }
        }
        return tiles;
    }

    public TileMap loadTiledFile(String path) {
        try {
            String jsonText = FileManager.getTextFromFile(path);
            JsonObject jsonTilemap = JsonParser.parseString(jsonText).getAsJsonObject();
            {
                // Get Texture from Json
                JsonObject jsonTileset;
                {
                    // Get Tileset
                    JsonArray jsonTilesetArray = jsonTilemap.get("tilesets").getAsJsonArray();
                    jsonTileset = jsonTilesetArray.get(0).getAsJsonObject();
                }
                Vector2f textureTileSize = new Vector2f(jsonTileset.get("tilewidth").getAsInt(),
                        jsonTileset.get("tileheight").getAsInt());
                String texturePath = "res/tilemap.png";
                this.texture = WindowManager.getWindow().getTextureCache().getTexture(texturePath, textureTileSize);
            }

            {
                // Get Layers from Json
                JsonArray jsonLayers = jsonTilemap.get("layers").getAsJsonArray();
                jsonLayers.forEach((jsonLayerObject) -> {
                    JsonObject jsonLayer = jsonLayerObject.getAsJsonObject();
                    Vector2f layerSize = new Vector2f(jsonLayer.get("width").getAsInt(), jsonLayer.get("height").getAsInt());
                    Map<Integer, Map<Integer, Tile>> tiles = new HashMap<>();
                    {
                        // Get Tiles
                        JsonArray jsonTiles = jsonLayer.get("data").getAsJsonArray();
                        for (int y = 0; y < layerSize.y; y++) {
                            for (int x = 0; x < layerSize.x; x++) {
                                int tileId = jsonTiles.get((int) (x + y * layerSize.x)).getAsInt() - 1;
                                if (tileId == 0) {
                                    continue;
                                }
                                if (!tiles.containsKey(y)) {
                                    tiles.put(y, new HashMap<>());
                                }
                                tiles.get(y).put(x, new Tile(new Vector2f(x, y), tileId));
                            }
                        }
                    }
                    this.layers.add(new Layer(this, jsonLayer.get("id").getAsInt(), tiles));
                });
            }
        } catch (Exception e) {
            throw new RuntimeException("Json File from the path: " + path + " cannot been loaded.");
        }

        this.visibleTiles = getVisibleTiles();
        updateUVS();
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

            {
                // Get Layers from Json
                JsonArray jsonLayers = jsonTilemap.get("layers").getAsJsonArray();
                jsonLayers.forEach((jsonLayerObject) -> {
                    JsonObject jsonLayer = jsonLayerObject.getAsJsonObject();
                    Map<Integer, Map<Integer, Tile>> tiles = new HashMap<>();
                    {
                        // Get Tiles
                        JsonArray jsonTiles = jsonLayer.get("data").getAsJsonArray();
                        jsonTiles.forEach((jsonTileObject) -> {
                            JsonObject jsonTile = jsonTileObject.getAsJsonObject();
                            Vector2i tilePosition = new Vector2i(jsonTile.get("x").getAsInt(),
                                    jsonTile.get("y").getAsInt());
                            if (!tiles.containsKey(tilePosition.y)) {
                                tiles.put(tilePosition.y, new HashMap<>());
                            }
                            tiles.get(tilePosition.y).put(tilePosition.x, new Tile(new Vector2f(tilePosition),
                                    jsonTile.get("id").getAsInt()));
                        });
                    }
                    this.layers.add(new Layer(this, jsonLayer.get("id").getAsInt(), tiles));
                });
            }
        } catch (Exception e) {
            throw new RuntimeException("Json File from the path: " + path + " cannot been loaded.");
        }

        this.visibleTiles = getVisibleTiles();
        updateUVS();
        return this;
    }

    public Vector2f getTileUnit() {
        return tileUnit;
    }
}
