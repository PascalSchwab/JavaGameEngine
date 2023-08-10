package de.pascalschwab.rendering.texture;

import java.util.HashMap;
import java.util.Map;

public class TextureCache {
    public static final String DEFAULT_TEXTURE = "res/Default_Texture.png";
    private Map<String, Texture> textures = new HashMap<>();

    public TextureCache() {
        textures.put(DEFAULT_TEXTURE, new Texture(DEFAULT_TEXTURE));
    }

    public void dispose() {
        textures.values().forEach(Texture::dispose);
    }

    public Texture createTexture(String path) {
        return textures.computeIfAbsent(path, Texture::new);
    }

    public Texture getTexture(String path) {
        Texture texture = null;
        if (path != null) {
            texture = textures.get(path);
        }
        if (texture == null) {
            texture = textures.get(DEFAULT_TEXTURE);
        }
        return texture;
    }
}
