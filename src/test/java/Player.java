import de.pascalschwab.gameobjects.KinematicObject2D;
import de.pascalschwab.gameobjects.Sprite;
import de.pascalschwab.rendering.mesh.Mesh;
import de.pascalschwab.rendering.mesh.TextureMesh;
import de.pascalschwab.standard.math.Vector2;
import de.pascalschwab.window.Window;

public class Player extends KinematicObject2D {
    public Player(Window window, Vector2 position, Vector2 size, int zIndex) throws Exception {
        super(window, position, size, zIndex);

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
        float[] colors = new float[]{
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
        };
        int[] indices = new int[]{
                0, 1, 3, 3, 1, 2,
        };
        Mesh mesh = new TextureMesh(positions, textCoords, indices);
        Sprite sprite = new Sprite(window, this, position, size, zIndex, mesh, "res/Player.png");
    }

    @Override
    protected void update() {
    }
}
