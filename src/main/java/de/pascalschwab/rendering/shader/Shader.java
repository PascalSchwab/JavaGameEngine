package de.pascalschwab.rendering.shader;

import static org.lwjgl.opengl.GL30.*;

public final class Shader {
    private final int id;
    private final ShaderPart vertexShaderPart;
    private final ShaderPart fragmentShaderPart;
    private final ShaderPart geometryShaderPart;

    public Shader(String vertexShaderPath, String fragmentShaderPath) {
        this(vertexShaderPath, fragmentShaderPath, null);
    }

    public Shader(String vertexShaderPath, String fragmentShaderPath, String geometryShaderPath) {
        this.id = glCreateProgram();
        if (this.id == 0) {
            throw new RuntimeException("Could not create shader program");
        }
        vertexShaderPart = new ShaderPart(vertexShaderPath, ShaderType.VERTEX);
        fragmentShaderPart = new ShaderPart(fragmentShaderPath, ShaderType.FRAGMENT);
        if (geometryShaderPath != null) {
            geometryShaderPart = new ShaderPart(geometryShaderPath, ShaderType.GEOMETRY);
        } else {
            geometryShaderPart = null;
        }
        glAttachShader(this.id, this.vertexShaderPart.getId());
        glAttachShader(this.id, this.fragmentShaderPart.getId());
        if (geometryShaderPart != null) {
            glAttachShader(this.id, this.geometryShaderPart.getId());
        }

        // Link shader program
        glLinkProgram(this.id);
        if (glGetProgrami(this.id, GL_LINK_STATUS) == 0) {
            throw new RuntimeException("Error linking Shader code: " + glGetProgramInfoLog(this.id, 1024));
        }

        if (this.vertexShaderPart.getId() != 0) {
            glDetachShader(this.id, this.vertexShaderPart.getId());
        }
        if (this.fragmentShaderPart.getId() != 0) {
            glDetachShader(this.id, this.fragmentShaderPart.getId());
        }
        if (this.geometryShaderPart != null && this.geometryShaderPart.getId() != 0) {
            glDetachShader(this.id, this.geometryShaderPart.getId());
        }

        glValidateProgram(this.id);
        if (glGetProgrami(this.id, GL_VALIDATE_STATUS) == 0) {
            System.err.println("Warning validating Shader code: " + glGetProgramInfoLog(this.id, 1024));
        }
    }

    public void bind() {
        glUseProgram(this.id);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void dispose() {
        unbind();
        if (this.id != 0) {
            glDeleteProgram(this.id);
        }
    }

    public int getId() {
        return id;
    }
}
