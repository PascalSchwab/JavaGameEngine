package de.engine.ui;

import de.engine.gameobjects.GameObject;
import de.engine.geometry.TextureRectangle;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class CharacterItem extends TextureRectangle {
    public CharacterItem(GameObject parent, Vector3f position, Vector2f offset, FontType font, float fontScale) {
        super(parent, position, new Vector2f(font.getFrameSize().x, font.getFrameSize().y).mul(fontScale),
                font.getTexturePath(), font.getFrameSize(), offset);
    }
}
