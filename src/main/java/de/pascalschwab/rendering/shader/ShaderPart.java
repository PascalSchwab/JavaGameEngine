package de.pascalschwab.rendering.shader;

import de.pascalschwab.managers.FileManager;

import java.io.IOException;

import static org.lwjgl.opengl.GL30.*;

public final class ShaderPart {
    private final int id;

    public ShaderPart(String path, ShaderType type) {
        this.id = glCreateShader(type.getCode());
        if (this.id == 0) {
            throw new RuntimeException("Can't create shader part from type: " + type);
        }
        try {
            glShaderSource(this.id, FileManager.getTextFromFile(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        glCompileShader(this.id);
        if (glGetShaderi(this.id, GL_COMPILE_STATUS) == 0) {
            throw new RuntimeException("Error compiling shader part code: " + glGetShaderInfoLog(this.id, 1024));
        }
    }

    public int getId() {
        return id;
    }

    public void dispose() {
        glDeleteShader(this.id);
    }
}
