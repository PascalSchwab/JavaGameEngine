import de.engine.managers.DevTools;
import de.engine.managers.InputManager;
import de.engine.standard.enums.Key;
import de.engine.window.Window;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Game extends Window {
    public Game(int width, int height, String title) {
        super(width, height, title);
        Player player = new Player(new Vector3f(100, 100, 0), new Vector2f(64, 128));
    }

    @Override
    public void update(float deltaTime) {
    }
}
