package de.pascalschwab.gameobjects;

import de.pascalschwab.geometry.Rectangle;
import de.pascalschwab.managers.WindowManager;
import de.pascalschwab.rendering.mesh.ColorMesh;
import de.pascalschwab.standard.enums.Alignment;
import de.pascalschwab.standard.enums.Colour;
import de.pascalschwab.standard.interfaces.IUpdatable;
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
    private Colour currentColour = NON_COLLISION_COLOUR;

    public CollisionBox(GameObject parent, Vector3f position, Vector2f size) {
        this(parent, position, size, Alignment.CENTER);
    }

    public CollisionBox(GameObject parent, Vector3f position, Vector2f size, Alignment alignment) {
        super(parent, new Vector3f(position), size, "res/shaders/color");
        setAlignment(alignment);
        updateColors();
        // Add Collision Object
        WindowManager.getWindow().collisionObjects.add(this);
    }

    @Override
    public void setup() {

    }

    @Override
    public void update(float delta) {
        collisions.clear();

        for (GameObject object : WindowManager.getWindow().collisionObjects) {
            CollisionBox box = (CollisionBox) object;
            if (box != this) {
                if (isColliding(box)) {
                    collisions.add(box);
                }
            }
        }
        if (collisions.isEmpty()) {
            setCurrentColour(NON_COLLISION_COLOUR);
        } else {
            setCurrentColour(COLLISION_COLOUR);
        }
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

    public final Alignment getAlignment() {
        return alignment;
    }

    public final void setAlignment(Alignment alignment) {
        this.alignment = alignment;
        if (alignment == Alignment.CENTER) {
            this.getPosition().sub(getSize().x / 2, getSize().y / 2, 0)
                    .add(getParent().getSize().x / 2, getParent().getSize().y / 2, 0);
        }
    }

    public boolean isColliding(CollisionBox box) {
        if (this.position.x + this.getSize().x > box.getPosition().x && this.position.x < box.getPosition().x + box.getSize().x) {
            if (this.position.y + this.getSize().y > box.getPosition().y && this.position.y < box.getPosition().y + box.getSize().y) {
                return true;
            }
        }
        return false;
    }

    private void setCurrentColour(Colour colour) {
        if (this.currentColour != colour) {
            this.currentColour = colour;
            updateColors();
        }
    }
}
