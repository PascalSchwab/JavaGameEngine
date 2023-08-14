import de.pascalschwab.window.Window;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Game extends Window {
    private Player player;
    private Deer deer;

    public Game(int width, int height, String title) {
        super(width, height, title);
    }

    @Override
    public void setup() {
        player = new Player(new Vector3f(300, 300, 0), new Vector2f(48, 96));
        deer = new Deer(new Vector3f(100, 100, 0), new Vector2f(96, 96));
    }

    @Override
    public void update(float deltaTime) {
    }
}
