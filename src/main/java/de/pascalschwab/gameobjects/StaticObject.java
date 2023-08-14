package de.pascalschwab.gameobjects;

import de.pascalschwab.window.Window;
import org.joml.Vector2f;
import org.joml.Vector3f;

public abstract class StaticObject extends GameObject {
    public StaticObject(Window window, GameObject parent, Vector3f position, Vector2f size) {
        super(window, parent, position, size);
    }
}
