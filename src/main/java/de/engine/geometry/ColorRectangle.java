package de.engine.geometry;

import de.engine.gameobjects.GameObject;
import de.engine.rendering.mesh.ColorMesh;
import de.engine.standard.enums.Colour;
import org.joml.Vector2f;
import org.joml.Vector3f;

public abstract class ColorRectangle extends Rectangle {
    private float[] COLORS = new float[0];
    private Colour currentColour = Colour.WHITE;

    public ColorRectangle(GameObject parent, Vector3f position, Vector2f size, String shaderPath, boolean visible) {
        super(parent, position, size, shaderPath, visible);
        updateColors();
    }

    public ColorRectangle(GameObject parent, Vector3f position, Vector2f size, String shaderPath) {
        this(parent, position, size, shaderPath, true);
    }

    protected final void updateColors() {
        COLORS = new float[]{
                currentColour.r, currentColour.g, currentColour.b,
                currentColour.r, currentColour.g, currentColour.b,
                currentColour.r, currentColour.g, currentColour.b,
                currentColour.r, currentColour.g, currentColour.b,
        };
        this.setMesh(new ColorMesh(VERTICES, COLORS, INDICES));
    }

    protected Colour getCurrentColour() {
        return this.currentColour;
    }

    protected void setCurrentColour(Colour colour) {
        if (this.currentColour != colour) {
            this.currentColour = colour;
            updateColors();
        }
    }
}
