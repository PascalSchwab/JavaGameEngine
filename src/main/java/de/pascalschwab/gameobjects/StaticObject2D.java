package de.pascalschwab.gameobjects;

import de.pascalschwab.window.Window;
import org.joml.Vector2f;

public class StaticObject2D extends StaticObject {
    public StaticObject2D(Window window, GameObject parent, Vector2f position, Vector2f size, int zIndex) {
        super(window, parent, position, size, zIndex);
    }
}
