import de.pascalschwab.window.Window;
import org.joml.Vector2f;

public class Game extends Window {
    private Player player;
    private Deer deer;

    public Game(int width, int height, String title) {
        super(width, height, title);
    }

    @Override
    public void setup() {
        player = new Player(this, new Vector2f(0, 0), new Vector2f(48, 96), 0f);
        deer = new Deer(this, new Vector2f(100, 100), new Vector2f(96, 96), 0f);
    }

    @Override
    public void update(float deltaTime) {
    }
}
