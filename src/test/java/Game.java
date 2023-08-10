import de.pascalschwab.projection.camera.Camera;
import de.pascalschwab.standard.enums.Key;
import de.pascalschwab.window.Window;
import org.joml.Vector2f;

public class Game extends Window {
    private final float MOVEMENT_SPEED = 0.005f;
    private Camera camera;
    private Player player;

    public Game(int width, int height, String title) {
        super(width, height, title);
    }

    @Override
    public void setup() {
        camera = new Camera();
        this.setMainCamera(camera);

        player = new Player(this, new Vector2f(1, 1), new Vector2f(1, 1), 1);
    }

    @Override
    public void update(float deltaTime) {
        float move = deltaTime * MOVEMENT_SPEED;
        if (isKeyPressed(Key.W)) {
            camera.moveUp(move);
        } else if (isKeyPressed(Key.S)) {
            camera.moveDown(move);
        }
        if (isKeyPressed(Key.A)) {
            camera.moveLeft(move);
        } else if (isKeyPressed(Key.D)) {
            camera.moveRight(move);
        }
    }
}
