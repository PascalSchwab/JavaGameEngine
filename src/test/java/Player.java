import de.pascalschwab.gameobjects.KinematicObject;
import de.pascalschwab.gameobjects.Sprite;
import de.pascalschwab.window.Window;
import org.joml.Vector2f;

public class Player extends KinematicObject {
    Sprite sprite;

    public Player(Window window, Vector2f position, Vector2f size, float zIndex) {
        super(window, null, position, size, zIndex);
        sprite = new Sprite(window, this, position, size, zIndex, "res/Player.png", new Vector2f(1, 0));
        /*sprite = new Sprite(window, this, position, size, zIndex, "res/Player.png", 0?, 1?)*/
    }

    @Override
    protected void update() {
    }
}
