package de.pascalschwab.tilemap;

import org.joml.Vector2f;

public final class Tile {
    private final Vector2f position;
    private final int tileID;

    public Tile(Vector2f position, int tileID){
        this.position = position;
        this.tileID = tileID;
    }

    public int getTileID() {
        return tileID;
    }

    public Vector2f getPosition() {
        return position;
    }
}
