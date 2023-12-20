package de.engine.ui;

import de.engine.gameobjects.GameObject;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class HudObject extends GameObject {
    public HudObject(Hud hud, Vector3f position, Vector2f size) {
        super(hud, position, size);
    }
}
