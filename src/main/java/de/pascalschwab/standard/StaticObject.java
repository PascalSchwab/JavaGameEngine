package de.pascalschwab.standard;

import de.pascalschwab.standard.math.Vector;
import de.pascalschwab.window.Window;

public abstract class StaticObject<VectorType extends Vector<VectorType>> extends GameObject<VectorType> {
    public StaticObject(Window<VectorType> window, GameObject<VectorType> parent, VectorType position, VectorType size, int zIndex) {
        super(window, parent, position, size, zIndex);
    }
}
