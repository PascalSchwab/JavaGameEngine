package de.pascalschwab.threedimensional;

import de.pascalschwab.standard.GameObject;
import de.pascalschwab.standard.KinematicObject;
import de.pascalschwab.standard.math.Vector3;
import de.pascalschwab.window.Window;

public class KinematicObject3D extends KinematicObject<Vector3> {
    public KinematicObject3D(Window<Vector3> window, GameObject<Vector3> parent, Vector3 position, Vector3 size, int zIndex) {
        super(window, parent, position, size, zIndex);
    }

    @Override
    public void tick() {

    }
}
