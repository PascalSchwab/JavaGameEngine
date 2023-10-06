package de.pascalschwab.standard;

import org.joml.Vector2f;

public final class Converter {
    public static float[] Vector21fv(Vector2f vector){
        return new float[]{vector.x, vector.y};
    }
}
