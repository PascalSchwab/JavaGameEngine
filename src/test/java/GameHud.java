import de.engine.ui.FontType;
import de.engine.ui.Hud;
import de.engine.ui.TextItem;
import org.joml.Vector2f;

public final class GameHud extends Hud {
    private final TextItem textItem;

    public GameHud() {
        textItem = new TextItem(this, new Vector2f(270, 100), "hello", FontType.Arial, 0.8f);
    }
}
