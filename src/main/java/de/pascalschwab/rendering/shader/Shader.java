package de.pascalschwab.rendering.shader;

import de.pascalschwab.managers.FileManager;

import java.io.IOException;

import static org.lwjgl.opengl.GL30.*;

public final class Shader {
    private final int id;
    private final ShaderType type;
    private final String path;

    public Shader(String path, ShaderType type) {
        this.type = type;
        this.path = path;
        this.id = glCreateShader(type.getCode());
        if (this.id == 0) {
            throw new RuntimeException("Can't create shader from type: " + type);
        }
        try {
            glShaderSource(this.id, FileManager.getTextFromFile(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        glCompileShader(this.id);
        if (glGetShaderi(this.id, GL_COMPILE_STATUS) == 0) {
            throw new RuntimeException("Error compiling shader code: " + glGetShaderInfoLog(this.id, 1024));
        }
    }

    public ShaderType getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }
}
