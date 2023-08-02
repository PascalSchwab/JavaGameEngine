package de.pascalschwab.standard.enums;

import de.pascalschwab.standard.math.Vector4;

public enum Colour {
    RED(1, 0, 0, 0), GREEN(0, 1, 0, 0), WHITE(1, 1, 1, 0), BLACK(0, 0, 0, 0), BLUE(0, 0, 1, 0);
    public final Vector4 colour;
    public final double r;
    public final double g;
    public final double b;
    public final double a;

    Colour(double r, double g, double b, double a) {
        this.colour = new Vector4(r, g, b, a);
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
}
