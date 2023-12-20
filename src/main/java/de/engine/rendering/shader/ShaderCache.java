package de.engine.rendering.shader;

import java.util.HashMap;
import java.util.Map;

public final class ShaderCache {
    public static final String DEFAULT_SHADER = "res/shaders/color";
    private final Map<String, Shader> shaders = new HashMap<>();

    public ShaderCache() {
        shaders.put(DEFAULT_SHADER, new Shader(DEFAULT_SHADER));
    }

    public void dispose() {
        shaders.values().forEach(Shader::dispose);
    }

    private Shader createShader(String path) {
        Shader shader = shaders.get(path);
        if (shader == null) {
            shader = new Shader(path);
            shaders.put(path, shader);
        }
        return shader;
    }

    public Shader getShader(String path) {
        Shader shader = null;
        if (path != null) {
            shader = createShader(path);
        }
        if (shader == null) {
            shader = shaders.get(DEFAULT_SHADER);
        }
        return shader;
    }
}
