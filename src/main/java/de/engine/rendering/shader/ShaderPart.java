package de.engine.rendering.shader;

import de.engine.managers.FileManager;

import java.io.IOException;

import static org.lwjgl.opengl.GL30.*;

/**
 * Reads shader file and compiles this specific shader
 */
public final class ShaderPart {
    private final int id;
    public ShaderPart(String path, ShaderType type) {
        this.id = glCreateShader(type.getCode());
        if (this.id == 0) {
            throw new ShaderException("Can't create shader part from type: " + type);
        }

        // Get shader from file
        try {
            glShaderSource(this.id, FileManager.getTextFromFile(path));
        } catch (IOException e) {
            throw new ShaderException("Can't read shader: " + path);
        }

        // Compile shader
        glCompileShader(this.id);
        if (glGetShaderi(this.id, GL_COMPILE_STATUS) == 0) {
            throw new ShaderException("Error compiling shader part code: " + glGetShaderInfoLog(this.id, 1024));
        }
    }
    public int getId() {
        return id;
    }
    public void dispose() {
        glDeleteShader(this.id);
    }
}
