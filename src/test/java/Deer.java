import de.pascalschwab.gameobjects.AnimatedSprite;
import de.pascalschwab.gameobjects.CollisionBox;
import de.pascalschwab.gameobjects.KinematicObject;
import de.pascalschwab.window.Window;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Deer extends KinematicObject {
    AnimatedSprite sprite;

    CollisionBox collisionBox;

    public Deer(Window window, Vector3f position, Vector2f size) {
        super(window, null, position, size);
    }

    @Override
    protected void setup() {
        sprite = new AnimatedSprite(window, parent, this.getPosition(), this.getSize(),
                "res/Reh.png", new Vector2f(32, 32));
        sprite.addAnimationsFromJson("res/jsons/rehAnimations.json");
        sprite.setCurrentAnimation("idle");

        collisionBox = new CollisionBox(window, this, this.getPosition(), new Vector2f(100, 100));
    }

    @Override
    protected void update(float deltaTime) {
    }
}
