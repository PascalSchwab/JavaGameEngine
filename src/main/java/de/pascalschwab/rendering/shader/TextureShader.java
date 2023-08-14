package de.pascalschwab.rendering.shader;

public final class TextureShader extends Shader {
    public TextureShader() {
        super("res/shaders/texture.vs", "res/shaders/texture.fs");
    }
}
