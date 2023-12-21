package de.engine.rendering.shader;

import de.engine.rendering.opengl.OpenGLObject;

import static org.lwjgl.opengl.GL30.*;

/**
 * Shader that will be used to render objects
 * Current no geometry shader implemented
 */
public final class Shader extends OpenGLObject {
    private final ShaderPart vertexShaderPart;
    private final ShaderPart fragmentShaderPart;
    private final UniformsMap uniformsMap;

    public Shader(String shaderPath) {
        super(glCreateProgram());
        vertexShaderPart = new ShaderPart(shaderPath + ".vert", ShaderType.VERTEX);
        fragmentShaderPart = new ShaderPart(shaderPath + ".frag", ShaderType.FRAGMENT);

        glAttachShader(this.getId(), this.vertexShaderPart.getId());
        glAttachShader(this.getId(), this.fragmentShaderPart.getId());

        // Link shader program
        glLinkProgram(this.getId());
        if (glGetProgrami(this.getId(), GL_LINK_STATUS) == 0) {
            throw new ShaderException("Error linking Shader code: " + glGetProgramInfoLog(this.getId(), 1024));
        }

        if (this.vertexShaderPart.getId() != 0) {
            glDetachShader(this.getId(), this.vertexShaderPart.getId());
        }
        if (this.fragmentShaderPart.getId() != 0) {
            glDetachShader(this.getId(), this.fragmentShaderPart.getId());
        }

        glValidateProgram(this.getId());
        if (glGetProgrami(this.getId(), GL_VALIDATE_STATUS) == 0) {
            throw new ShaderException("Warning validating Shader code: " + glGetProgramInfoLog(this.getId(), 1024));
        }

        this.uniformsMap = new UniformsMap(this.getId());
    }

    @Override
    public void bind() {
        glUseProgram(this.getId());
    }

    @Override
    public void unbind() {
        glUseProgram(0);
    }

    @Override
    public void dispose() {
        glDeleteProgram(this.getId());
        this.vertexShaderPart.dispose();
        this.fragmentShaderPart.dispose();
    }

    public UniformsMap getUniformsMap() {
        return uniformsMap;
    }
}
