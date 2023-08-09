package de.pascalschwab.rendering.shader;

public class TextureShader extends ShaderProgram {
    public TextureShader() throws Exception {
        super("res/shaders/texture.vs", "res/shaders/texture.fs");
    }
}
