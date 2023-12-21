package de.engine.ui;

import de.engine.gameobjects.GameObject;
import de.engine.managers.WindowManager;
import org.joml.Vector3f;

public abstract class Hud extends GameObject {
    public Hud() {
        super(new Vector3f(0, 0, 0.5f), WindowManager.getWindow().getWindowSize());
    }
}
