package de.pascalschwab.rendering.texture;

import java.util.HashMap;
import java.util.Map;

public class TextureCache {
    public static final String DEFAULT_TEXTURE = "res/Test.png";
    private Map<String, Texture> textureMap = new HashMap<>();

    public TextureCache() {
        textureMap.put(DEFAULT_TEXTURE, new Texture(DEFAULT_TEXTURE));
    }

    public void dispose() {
        textureMap.values().forEach(Texture::dispose);
    }

    public Texture createTexture(String path) {
        return textureMap.computeIfAbsent(path, Texture::new);
    }

    public Texture getTexture(String path) {
        Texture texture = null;
        if (path != null) {
            texture = textureMap.get(path);
        }
        if (texture == null) {
            texture = textureMap.get(DEFAULT_TEXTURE);
        }
        return texture;
    }
}
