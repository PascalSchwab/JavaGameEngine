import de.pascalschwab.ui.FontType;
import de.pascalschwab.ui.Hud;
import de.pascalschwab.ui.TextItem;
import org.joml.Vector2f;

public final class GameHud extends Hud {
    private final TextItem textItem;

    public GameHud() {
        textItem = new TextItem(this, new Vector2f(270, 100), "hello", FontType.Arial, 0.8f);
    }
}
