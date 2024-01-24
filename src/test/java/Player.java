import de.engine.gameobjects.GameObject;
import de.engine.gameobjects.KinematicObject;
import de.engine.managers.InputManager;
import de.engine.sound.SoundSource;
import de.engine.standard.enums.Key;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Player extends KinematicObject {
    SoundSource source;
    public Player(Vector3f position, Vector2f size) {
        super(position, size);
        source = new SoundSource(this);
    }

    @Override
    public void update(float delta) {
    }
}
