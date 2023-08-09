import de.pascalschwab.gameobjects.Sprite;
import de.pascalschwab.rendering.mesh.Mesh;
import de.pascalschwab.rendering.mesh.TextureMesh;
import de.pascalschwab.standard.math.Vector2;
import de.pascalschwab.window.Window;

public class Game extends Window {
    public Game(int width, int height, String title) {
        super(width, height, title);
    }

    @Override
    public void setup() throws Exception {
        //Texture texture = this.getTextureCache().createTexture("res/Test.png");
        float[] colors = new float[]{
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
        };
        float[] positions = new float[]{
                -0.5f, 0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                0.5f, 0.5f, 0.0f,
        };
        float[] textCoords = new float[]{
                0.0f, 0.0f,
                0.0f, 0.1f,
                0.1f, 0.1f,
                0.1f, 0.0f
        };
        int[] indices = new int[]{
                0, 1, 3, 3, 1, 2,
        };
        Mesh mesh = new TextureMesh(positions, textCoords, indices);
        Sprite sprite = new Sprite(this, null, new Vector2(1, 1), new Vector2(1, 1), 0, mesh, "res/Player.png");
    }

    @Override
    public void update() {

    }
}
