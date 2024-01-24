package de.engine.gameobjects;

import de.engine.standard.interfaces.IUpdatable;
import org.joml.Vector2f;
import org.joml.Vector3f;

public abstract class KinematicObject extends GameObject implements IUpdatable {
    public KinematicObject(Vector3f position, Vector2f size){
        super(position, size);
    }
    public KinematicObject(GameObject parent, Vector3f position, Vector2f size) {
        super(parent, position, size);
    }
}
