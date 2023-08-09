package de.pascalschwab.rendering.shader;

import de.pascalschwab.managers.FileManager;

import static org.lwjgl.opengl.GL30.*;

public final class Shader {
    private final int id;
    private final ShaderType type;
    private final String path;

    public Shader(String path, ShaderType type) throws Exception {
        this.type = type;
        this.path = path;
        this.id = glCreateShader(type.getCode());
        if (this.id == 0) {
            throw new Exception("Can't create shader from type: " + type);
        }
        glShaderSource(this.id, FileManager.getTextFromFile(path));
        glCompileShader(this.id);
        if (glGetShaderi(this.id, GL_COMPILE_STATUS) == 0) {
            throw new Exception("Error compiling shader code: " + glGetShaderInfoLog(this.id, 1024));
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
