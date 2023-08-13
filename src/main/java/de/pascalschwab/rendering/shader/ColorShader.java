package de.pascalschwab.rendering.shader;

public final class ColorShader extends ShaderProgram {
    public ColorShader() {
        super("res/shaders/color.vs", "res/shaders/color.fs");
    }
}
