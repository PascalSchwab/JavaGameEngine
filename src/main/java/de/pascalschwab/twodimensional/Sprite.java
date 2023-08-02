package de.pascalschwab.twodimensional;

import de.pascalschwab.standard.GameObject;
import de.pascalschwab.standard.interfaces.IRenderable;
import de.pascalschwab.standard.math.Vector2;
import de.pascalschwab.window.Window;

public class Sprite extends GameObject<Vector2> implements IRenderable {

    public Sprite(Window<Vector2> window, GameObject<Vector2> parent, Vector2 position, Vector2 size, int zIndex, String spritePath) {
        super(window, parent, position, size, zIndex);
    }

    @Override
    public void draw() {

    }
}
