import de.pascalschwab.gameobjects.AnimatedSprite;
import de.pascalschwab.gameobjects.KinematicObject;
import de.pascalschwab.rendering.texture.Animation;
import de.pascalschwab.standard.enums.Key;
import de.pascalschwab.window.Window;
import org.joml.Vector2f;

public class Player extends KinematicObject {
    AnimatedSprite sprite;

    public Player(Window window, Vector2f position, Vector2f size, float zIndex) {
        super(window, null, position, size, zIndex);
    }

    @Override
    protected void setup() {
        sprite = new AnimatedSprite(window, this, this.getPosition(), this.getSize(), zIndex, "res/Player.png");
        sprite.addAnimation(new Animation("idle", new Vector2f[]{new Vector2f(0, 0),
                new Vector2f(1, 0), new Vector2f(2, 0), new Vector2f(3, 0)}, 15));
        sprite.addAnimation(new Animation("run-left", new Vector2f[]{new Vector2f(0, 1),
                new Vector2f(1, 1), new Vector2f(2, 1), new Vector2f(3, 1)}, 15));
        sprite.setCurrentAnimation("idle");
    }

    @Override
    protected void update(float deltaTime) {
        if (window.isKeyPressed(Key.A)) {
            sprite.setCurrentAnimation("run-left");
            sprite.playCurrentAnimation(deltaTime);
        } else if (window.isKeyPressed(Key.W)) {

        } else if (window.isKeyPressed(Key.D)) {

        } else if (window.isKeyPressed(Key.S)) {

        } else {
            sprite.setCurrentAnimation("idle");
            sprite.getCurrentAnimation().setCurrentPosition(1);
        }
        //sprite.playCurrentAnimation(deltaTime);
    }
}
