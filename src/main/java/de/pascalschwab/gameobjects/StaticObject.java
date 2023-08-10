package de.pascalschwab.gameobjects;

import de.pascalschwab.window.Window;
import org.joml.Vector2f;

public abstract class StaticObject extends GameObject {
    public StaticObject(Window window, GameObject parent, Vector2f position, Vector2f size, float zIndex) {
        super(window, parent, position, size, zIndex);
    }
}
