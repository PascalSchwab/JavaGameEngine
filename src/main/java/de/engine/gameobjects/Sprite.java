package de.engine.gameobjects;

import de.engine.geometry.TextureRectangle;
import org.joml.Vector2f;
import org.joml.Vector3f;

public final class Sprite extends TextureRectangle {
    public Sprite(GameObject parent, Vector3f position, Vector2f size, String texturePath, Vector2f frameSize) {
        super(parent, position, size, texturePath, frameSize);
    }

    public Sprite(GameObject parent, Vector3f position, Vector2f size, String texturePath, Vector2f frameSize, Vector2f offset) {
        super(parent, position, size, texturePath, frameSize, offset);
    }
}
