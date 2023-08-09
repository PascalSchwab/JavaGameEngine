package de.pascalschwab.gameobjects;

import de.pascalschwab.standard.interfaces.IRenderable;
import de.pascalschwab.standard.math.Vector2;
import de.pascalschwab.window.Window;

public class Sprite extends GameObject implements IRenderable {

    public Sprite(Window window, GameObject parent, Vector2 position, Vector2 size, int zIndex, String spritePath) {
        super(window, parent, position, size, zIndex);
    }

    @Override
    public void draw() {

    }
}
