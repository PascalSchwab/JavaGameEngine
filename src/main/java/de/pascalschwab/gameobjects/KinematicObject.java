package de.pascalschwab.gameobjects;

import de.pascalschwab.standard.interfaces.IUpdatable;
import de.pascalschwab.window.Window;
import org.joml.Vector2f;
import org.joml.Vector3f;

public abstract class KinematicObject extends GameObject implements IUpdatable {
    public KinematicObject(Window window, GameObject parent, Vector3f position, Vector2f size) {
        super(window, parent, position, size);
    }

    public final void tick(float deltaTime) {
        update(deltaTime);
    }

    protected abstract void update(float deltaTime);
}
