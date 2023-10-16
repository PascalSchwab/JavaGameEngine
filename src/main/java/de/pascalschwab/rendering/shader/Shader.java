package de.pascalschwab.rendering.shader;

import static org.lwjgl.opengl.GL30.*;

public final class Shader {
    private final int id;
    private final ShaderPart vertexShaderPart;
    private final ShaderPart fragmentShaderPart;
    private final UniformsMap uniformsMap;

    public Shader(String shaderPath) {
        this.id = glCreateProgram();
        if (this.id == 0) {
            throw new RuntimeException("Could not create shader program");
        }
        vertexShaderPart = new ShaderPart(shaderPath + ".vert", ShaderType.VERTEX);
        fragmentShaderPart = new ShaderPart(shaderPath + ".frag", ShaderType.FRAGMENT);

        glAttachShader(this.id, this.vertexShaderPart.getId());
        glAttachShader(this.id, this.fragmentShaderPart.getId());

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

        glValidateProgram(this.id);
        if (glGetProgrami(this.id, GL_VALIDATE_STATUS) == 0) {
            System.err.println("Warning validating Shader code: " + glGetProgramInfoLog(this.id, 1024));
        }

        this.uniformsMap = new UniformsMap(this.id);
    }

    public void bind() {
        glUseProgram(this.id);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void dispose() {
        glDeleteProgram(this.id);
        this.vertexShaderPart.dispose();
        this.fragmentShaderPart.dispose();
    }

    public int getId() {
        return id;
    }

    public UniformsMap getUniformsMap() {
        return uniformsMap;
    }
}
