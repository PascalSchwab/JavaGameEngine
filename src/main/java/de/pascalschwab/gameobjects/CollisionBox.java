package de.pascalschwab.gameobjects;

import de.pascalschwab.geometry.Rectangle;
import de.pascalschwab.rendering.mesh.ColorMesh;
import de.pascalschwab.standard.enums.Alignment;
import de.pascalschwab.standard.enums.Colour;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class CollisionBox extends Rectangle {
    private static final Colour COLLISION_COLOUR = Colour.RED;
    private static final Colour NON_COLLISION_COLOUR = Colour.GREEN;
    private float[] COLORS = new float[0];
    private Alignment alignment;
    private List<CollisionBox> collisions = new ArrayList<>();

    public CollisionBox(GameObject parent, Vector3f position, Vector2f size) {
        this(parent, position, size, Alignment.CENTER);
    }

    public CollisionBox(GameObject parent, Vector3f position, Vector2f size, Alignment alignment) {
        super(parent, new Vector3f(position), size, "res/shaders/color");
        setAlignment(alignment);
        updateColors();
    }

    @Override
    public void setup() {

    }

    protected void updateColors() {
        COLORS = new float[]{
                0.5f, 0.0f, 0.0f,
                0.5f, 0.0f, 0.0f,
                0.5f, 0.0f, 0.0f,
                0.5f, 0.0f, 0.0f,
        };
        this.setMesh(new ColorMesh(VERTICES, COLORS, INDICES));
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
        if (alignment == Alignment.CENTER) {
            this.getPosition().sub(getSize().x / 2, getSize().y / 2, 0)
                    .add(getParent().getSize().x / 2, getParent().getSize().y / 2, 0);
        }
    }
}
