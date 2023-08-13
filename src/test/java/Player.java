import de.pascalschwab.gameobjects.AnimatedSprite;
import de.pascalschwab.gameobjects.KinematicObject;
import de.pascalschwab.standard.enums.Key;
import de.pascalschwab.window.Window;
import managers.InputManager;
import org.joml.Vector2f;

public class Player extends KinematicObject {
    AnimatedSprite sprite;
    LookDirection lastLookDirection = LookDirection.DOWN;

    public Player(Window window, Vector2f position, Vector2f size, float zIndex) {
        super(window, null, position, size, zIndex);
    }

    @Override
    protected void setup() {
        sprite = new AnimatedSprite(window, this, this.getPosition(), this.getSize(), zIndex,
                "res/Player.png", new Vector2f(16, 32));
        sprite.addAnimationsFromJson("res/jsons/playerAnimations.json");
        sprite.setCurrentAnimation("idle");
    }

    @Override
    protected void update(float deltaTime) {
        if (InputManager.isKeyPressed(Key.A)) {
            lastLookDirection = LookDirection.LEFT;
            sprite.playAnimation("walk-left", deltaTime);
        } else if (InputManager.isKeyPressed(Key.W)) {
            lastLookDirection = LookDirection.UP;
            sprite.playAnimation("walk-up", deltaTime);
        } else if (InputManager.isKeyPressed(Key.D)) {
            lastLookDirection = LookDirection.RIGHT;
            sprite.playAnimation("walk-right", deltaTime);
        } else if (InputManager.isKeyPressed(Key.S)) {
            lastLookDirection = LookDirection.DOWN;
            sprite.playAnimation("walk-down", deltaTime);
        } else {
            sprite.setCurrentAnimationPosition("idle", lastLookDirection.value);
        }
    }
}
