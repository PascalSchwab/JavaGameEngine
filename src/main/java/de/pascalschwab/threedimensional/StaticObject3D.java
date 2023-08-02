package de.pascalschwab.threedimensional;

import de.pascalschwab.standard.GameObject;
import de.pascalschwab.standard.StaticObject;
import de.pascalschwab.standard.math.Vector3;
import de.pascalschwab.window.Window;

public class StaticObject3D extends StaticObject<Vector3> {
    public StaticObject3D(Window<Vector3> window, GameObject<Vector3> parent, Vector3 position, Vector3 size, int zIndex) {
        super(window, parent, position, size, zIndex);
    }
}
