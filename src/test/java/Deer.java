import de.engine.gameobjects.AnimatedSprite;
import de.engine.gameobjects.CollisionBox;
import de.engine.gameobjects.KinematicObject;
import de.engine.sound.SoundSource;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Deer extends KinematicObject {
    AnimatedSprite sprite;

    CollisionBox collisionBox;

    SoundSource soundSource;

    public Deer(Vector3f position, Vector2f size) {
        super(null, position, size);
        sprite = new AnimatedSprite(getParent(), "res/Reh.png",
                new Vector2f(32, 32), "res/jsons/rehAnimations.json");
        sprite.setCurrentAnimation("idle");

        collisionBox = new CollisionBox(this, this.getPosition(), new Vector2f(100, 100));

        soundSource = new SoundSource(this);
    }

    @Override
    public void update(float deltaTime) {
    }
}
