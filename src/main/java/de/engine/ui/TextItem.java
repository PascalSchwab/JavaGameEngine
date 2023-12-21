package de.engine.ui;

import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class TextItem extends HudObject {
    private final List<CharacterItem> characterList = new ArrayList<>();
    private final String text;

    public TextItem(Hud hud, Vector2f position, String text, FontType fontType, float fontScale) {
        super(hud, new Vector3f(position.x, position.y, hud.getPosition().z), null);
        this.text = text;
        setCharacterList(fontType, fontScale);
    }

    public String getText() {
        return text;
    }

    private void setCharacterList(FontType fontType, float fontScale) {
        char[] chars = this.text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            characterList.add(new CharacterItem(this, new Vector3f(position.x + 25 * fontScale * i, position.y, position.z),
                    fontType.getData().get(String.valueOf(chars[i])),
                    fontType, fontScale));
        }
    }
}
