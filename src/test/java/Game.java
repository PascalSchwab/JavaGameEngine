import de.pascalschwab.rendering.ShaderProgram;
import de.pascalschwab.standard.math.Vector2;
import de.pascalschwab.window.Window;

public class Game extends Window<Vector2> {
    public Game(int width, int height, String title) {
        super(width, height, title);
    }

    @Override
    public void setup() throws Exception {
        ShaderProgram shaderProgram = new ShaderProgram("res/shaders/vertex.vs", "res/shaders/fragment.fs");
    }

    @Override
    public void update() {

    }
}
