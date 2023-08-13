import de.pascalschwab.gameobjects.AnimatedSprite;
import de.pascalschwab.gameobjects.KinematicObject;
import de.pascalschwab.window.Window;
import org.joml.Vector2f;

public class Deer extends KinematicObject {
    AnimatedSprite sprite;

    public Deer(Window window, Vector2f position, Vector2f size, float zIndex) {
        super(window, null, position, size, zIndex);
    }

    @Override
    protected void setup() {
        sprite = new AnimatedSprite(window, parent, this.getPosition(), this.getSize(), zIndex,
                "res/Reh.png", new Vector2f(32, 32));
        sprite.addAnimationsFromJson("res/jsons/rehAnimations.json");
        sprite.setCurrentAnimation("idle");
    }

    @Override
    protected void update(float deltaTime) {
    }
}
