package de.engine.standard.enums;

import org.joml.Vector4f;

public enum Colour {
    RED(1, 0, 0, 0), GREEN(0, 1, 0, 0), WHITE(1, 1, 1, 0), BLACK(0, 0, 0, 0), BLUE(0, 0, 1, 0);
    public final Vector4f colour;
    public final float r;
    public final float g;
    public final float b;
    public final float a;

    Colour(float r, float g, float b, float a) {
        this.colour = new Vector4f(r, g, b, a);
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
}
