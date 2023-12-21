import de.engine.gameobjects.AnimatedSprite;
import de.engine.gameobjects.CollisionBox;
import de.engine.gameobjects.KinematicObject;
import de.engine.managers.InputManager;
import de.engine.managers.WindowManager;
import de.engine.sound.SoundSource;
import de.engine.standard.enums.Key;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Player extends KinematicObject {
    private AnimatedSprite sprite;
    private LookDirection lastLookDirection = LookDirection.DOWN;
    private CollisionBox collisionBox;
    private SoundSource soundSource;

    public Player(Vector3f position, Vector2f size) {
        super(null, position, size);
        sprite = new AnimatedSprite(this, this.getPosition(), this.getSize(),
                "res/Player.png", new Vector2f(16, 32));
        sprite.addAnimationsFromJson("res/jsons/playerAnimations.json");
        sprite.setCurrentAnimation("idle");

        collisionBox = new CollisionBox(this, this.getPosition(), new Vector2f(200, 200));

        soundSource = new SoundSource(this, this.getPosition());
    }

    @Override
    public void update(float deltaTime) {
        float speed = 2f * deltaTime;
        if (InputManager.isKeyPressed(Key.A)) {
            lastLookDirection = LookDirection.LEFT;
            sprite.playAnimation("walk-left", deltaTime);
            this.translate(-speed, 0);
        } else if (InputManager.isKeyPressed(Key.W)) {
            lastLookDirection = LookDirection.UP;
            sprite.playAnimation("walk-up", deltaTime);
            this.translate(0, -speed);
        } else if (InputManager.isKeyPressed(Key.D)) {
            lastLookDirection = LookDirection.RIGHT;
            sprite.playAnimation("walk-right", deltaTime);
            this.translate(speed, 0);
        } else if (InputManager.isKeyPressed(Key.S)) {
            lastLookDirection = LookDirection.DOWN;
            sprite.playAnimation("walk-down", deltaTime);
            this.translate(0, speed);
        } else {
            sprite.setCurrentAnimationPosition("idle", lastLookDirection.value);
        }

        if (InputManager.isKeyTapped(Key.U)) {
            soundSource.play("Roblox");
        }

        /*if (!WindowManager.getWindow().getCamera().isInViewport(this.getPosition())) {
        }*/

    }
}
