package de.pascalschwab.standard;

import de.pascalschwab.standard.math.Vector;
import de.pascalschwab.window.Window;

public abstract class KinematicObject<VectorType extends Vector<VectorType>> extends GameObject<VectorType> {
    public KinematicObject(Window<VectorType> window, GameObject<VectorType> parent, VectorType position, VectorType size, int zIndex) {
        super(window, parent, position, size, zIndex);
    }

    public abstract void tick();
}
