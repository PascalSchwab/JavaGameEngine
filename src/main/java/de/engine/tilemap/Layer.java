package de.engine.tilemap;

import de.engine.managers.WindowManager;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.Map;

public final class Layer {
    private final Map<Integer, Map<Integer, Tile>> tiles;
    private final int id;
    private final TileMap tileMap;

    public Layer(TileMap tilemap, int id, Map<Integer, Map<Integer, Tile>> tiles) {
        this.id = id;
        this.tiles = tiles;
        this.tileMap = tilemap;
    }

    public int getId() {
        return id;
    }

    public Map<Integer, Map<Integer, Tile>> getTiles() {
        return tiles;
    }

    public int getTileIdFromWorldPosition(Vector3f position) {
        return 0;
    }

    public Vector2f getTilePositionFromWorldPosition(Vector3f position) {
        return new Vector2f((int) Math.floor(position.x * WindowManager.getWindow().getUnit().x / tileMap.getTileUnit().x),
                (int) Math.floor(position.y * WindowManager.getWindow().getUnit().y / tileMap.getTileUnit().y));
    }
}
