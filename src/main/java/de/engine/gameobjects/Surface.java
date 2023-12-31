package de.engine.gameobjects;

import de.engine.geometry.ColorRectangle;
import de.engine.standard.enums.Colour;
import org.joml.Vector2f;
import org.joml.Vector3f;

public final class Surface extends ColorRectangle {
    public Surface(Vector3f position, Vector2f size, String shaderPath) {
        super(null, position, size, shaderPath);
    }

    public void setCurrentColour(Colour colour) {
        super.setCurrentColour(colour);
    }
}
