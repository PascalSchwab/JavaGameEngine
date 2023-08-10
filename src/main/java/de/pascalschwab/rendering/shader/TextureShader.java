package de.pascalschwab.rendering.shader;

public class TextureShader extends ShaderProgram {
    public TextureShader() {
        super("res/shaders/texture.vs", "res/shaders/texture.fs");
    }
}
