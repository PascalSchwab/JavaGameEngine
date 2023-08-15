package de.pascalschwab.rendering.shader;

import static org.lwjgl.opengl.GL30.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL30.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;

public enum ShaderType {
    VERTEX(GL_VERTEX_SHADER), FRAGMENT(GL_FRAGMENT_SHADER), GEOMETRY(GL_GEOMETRY_SHADER);
    private final int code;

    ShaderType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
