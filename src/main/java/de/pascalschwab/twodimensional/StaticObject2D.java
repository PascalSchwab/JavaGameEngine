package de.pascalschwab.twodimensional;

import de.pascalschwab.standard.GameObject;
import de.pascalschwab.standard.StaticObject;
import de.pascalschwab.standard.math.Vector2;
import de.pascalschwab.window.Window;

public class StaticObject2D extends StaticObject<Vector2> {
    public StaticObject2D(Window<Vector2> window, GameObject<Vector2> parent, Vector2 position, Vector2 size, int zIndex) {
        super(window, parent, position, size, zIndex);
    }
}
