import de.pascalschwab.managers.DevTools;
import de.pascalschwab.managers.InputManager;
import de.pascalschwab.standard.enums.Key;
import de.pascalschwab.tilemap.TileMap;
import de.pascalschwab.window.Window;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Game extends Window {
    private TileMap tileMap;
    private Player player;
    private Deer deer;

    public Game(int width, int height, String title) {
        super(width, height, title);
    }

    @Override
    public void setup() {
        tileMap = new TileMap("res/jsons/tilemap.json", new Vector2f(64,64));
        player = new Player(new Vector3f(300, 300, 0), new Vector2f(48, 96));
        deer = new Deer(new Vector3f(100, 100, 0), new Vector2f(96, 96));
    }

    @Override
    public void update(float deltaTime) {
        if (InputManager.isKeyTapped(Key.T)) {
            DevTools.setActive(!DevTools.isActive());
        }
    }
}
