package de.pascalschwab.gameobjects;

import de.pascalschwab.standard.math.Vector2;
import de.pascalschwab.window.Window;

public abstract class KinematicObject extends GameObject {
    public KinematicObject(Window window, GameObject parent, Vector2 position, Vector2 size, int zIndex) {
        super(window, parent, position, size, zIndex);
    }

    public abstract void tick();
}
