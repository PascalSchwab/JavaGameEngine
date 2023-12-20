import de.pascalschwab.managers.DevTools;
import de.pascalschwab.managers.InputManager;
import de.pascalschwab.networking.messages.ClientDisconnectMessage;
import de.pascalschwab.standard.enums.Key;
import de.pascalschwab.window.Window;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Game extends Window {
    private Player player;
    public Game(int width, int height, String title) {
        super(width, height, title);
        player = new Player(new Vector3f(300, 300, 0), new Vector2f(48, 96));
    }

    @Override
    public void update(float deltaTime) {
        if (InputManager.isKeyTapped(Key.T)) {
            DevTools.setActive(!DevTools.isActive());
        }
        else if(InputManager.isKeyTapped(Key.N)){
        }
    }
}
