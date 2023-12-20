import de.pascalschwab.gameobjects.AnimatedSprite;
import de.pascalschwab.gameobjects.CollisionBox;
import de.pascalschwab.gameobjects.KinematicObject;
import de.pascalschwab.sound.SoundSource;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Deer extends KinematicObject {
    AnimatedSprite sprite;

    CollisionBox collisionBox;

    SoundSource soundSource;

    public Deer(Vector3f position, Vector2f size) {
        super(null, position, size);
        sprite = new AnimatedSprite(getParent(), this.getPosition(), this.getSize(),
                "res/Reh.png", new Vector2f(32, 32));
        sprite.addAnimationsFromJson("res/jsons/rehAnimations.json");
        sprite.setCurrentAnimation("idle");

        collisionBox = new CollisionBox(this, this.getPosition(), new Vector2f(100, 100));

        soundSource = new SoundSource(this);
    }

    @Override
    public void update(float deltaTime) {
    }
}
