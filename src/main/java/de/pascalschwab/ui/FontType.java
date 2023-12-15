package de.pascalschwab.ui;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.pascalschwab.managers.FileManager;
import org.joml.Vector2f;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public enum FontType {
    Arial("res/fonts/standard.png", new Vector2f(32, 64), "res/fonts/standard.json");
    private final String texturePath;
    private final Vector2f frameSize;
    private final Map<String, Vector2f> data;

    FontType(String texturePath, Vector2f frameSize, String jsonPath) {
        this.texturePath = texturePath;
        this.frameSize = frameSize;
        this.data = getFontData(jsonPath);
    }

    public String getTexturePath() {
        return texturePath;
    }

    public Vector2f getFrameSize() {
        return frameSize;
    }

    public Map<String, Vector2f> getData() {
        return data;
    }

    private Map<String, Vector2f> getFontData(String jsonPath) {
        Map<String, Vector2f> data = new HashMap<>();

        try {
            String jsonText = FileManager.getTextFromFile(jsonPath);
            JsonArray jsonArray = JsonParser.parseString(jsonText).getAsJsonArray();
            jsonArray.forEach((object) -> {
                JsonObject jsonCharacter = object.getAsJsonObject();
                String character = jsonCharacter.get("char").getAsString();
                Vector2f offset = new Vector2f(jsonCharacter.get("x").getAsInt(), jsonCharacter.get("y").getAsInt());
                data.put(character, offset);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }
}
