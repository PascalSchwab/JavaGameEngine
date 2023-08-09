package de.pascalschwab.rendering.shader;

public final class StandardShader extends ShaderProgram {

    public StandardShader() throws Exception {
        super("res/shaders/vertex.vs", "res/shaders/fragment.fs");
    }
}
