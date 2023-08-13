import de.pascalschwab.projection.camera.Camera;
import de.pascalschwab.standard.enums.Key;
import de.pascalschwab.window.Window;
import managers.InputManager;
import org.joml.Vector2f;

public class Game extends Window {
    private final float MOVEMENT_SPEED = 0.005f;
    private Camera camera;
    private Player player;
    /*private Deer deer;*/

    public Game(int width, int height, String title) {
        super(width, height, title);
    }

    @Override
    public void setup() {
        camera = new Camera();
        this.setMainCamera(camera);

        player = new Player(this, new Vector2f(0, 0), new Vector2f(48, 96), 0f);
        /*deer = new Deer(this, new Vector2f(0, 0), new Vector2f(96, 96), 0f);*/
    }

    @Override
    public void update(float deltaTime) {
        float move = deltaTime * MOVEMENT_SPEED;
        if (InputManager.isKeyPressed(Key.W)) {
            camera.moveUp(move);
        } else if (InputManager.isKeyPressed(Key.S)) {
            camera.moveDown(move);
        }
        if (InputManager.isKeyPressed(Key.A)) {
            camera.moveLeft(move);
        } else if (InputManager.isKeyPressed(Key.D)) {
            camera.moveRight(move);
        }
    }
}
