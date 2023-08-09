import de.pascalschwab.geometry.Triangle;
import de.pascalschwab.rendering.shader.StandardShader;
import de.pascalschwab.standard.math.Vector2;
import de.pascalschwab.window.Window;

public class Game extends Window {
    public Game(int width, int height, String title) {
        super(width, height, title);
    }

    @Override
    public void setup() throws Exception {
        Triangle triangle = new Triangle(this, null, new Vector2(10, 10), new Vector2(20, 20), 0, new StandardShader());
    }

    @Override
    public void update() {

    }
}
