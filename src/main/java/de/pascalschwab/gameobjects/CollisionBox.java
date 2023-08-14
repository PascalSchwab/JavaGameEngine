package de.pascalschwab.gameobjects;

import de.pascalschwab.geometry.Rectangle;
import de.pascalschwab.rendering.mesh.ColorMesh;
import de.pascalschwab.rendering.shader.ShaderProgram;
import de.pascalschwab.standard.enums.Alignment;
import de.pascalschwab.standard.enums.Colour;
import de.pascalschwab.standard.interfaces.IUpdatable;
import de.pascalschwab.window.Window;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class CollisionBox extends Rectangle implements IUpdatable {
    private static final Colour COLLISION_COLOUR = Colour.RED;
    private static final Colour NON_COLLISION_COLOUR = Colour.GREEN;
    private float[] COLORS = new float[0];
    private Alignment alignment;
    private List<CollisionBox> collisions = new ArrayList<>();

    public CollisionBox(Window window, GameObject parent, Vector3f position, Vector2f size) {
        this(window, parent, position, size, Alignment.CENTER);
    }

    public CollisionBox(Window window, GameObject parent, Vector3f position, Vector2f size, Alignment alignment) {
        super(window, parent, new Vector3f(position), size,
                new ShaderProgram("res/shaders/color.vs", "res/shaders/color.fs"));
        setAlignment(alignment);
        updateColors();
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
                    .add(parent.getSize().x / 2, parent.getSize().y / 2, 0);
        }
    }
}
