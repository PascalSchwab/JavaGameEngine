import de.pascalschwab.standard.math.Vector2;
import de.pascalschwab.twodimensional.KinematicObject2D;
import de.pascalschwab.twodimensional.Sprite;
import de.pascalschwab.window.Window;

public class Player extends KinematicObject2D {
    public Player(Window<Vector2> window, Vector2 position, Vector2 size, int zIndex) {
        super(window, position, size, zIndex);
        Sprite sprite = new Sprite(window, this, position, size, zIndex, "res/Unbenannt.png");
    }

    @Override
    protected void update() {

    }
}
