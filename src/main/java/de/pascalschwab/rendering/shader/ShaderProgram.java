package de.pascalschwab.rendering.shader;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {
    private final int id;
    private final Shader vertexShader;
    private final Shader fragmentShader;

    public ShaderProgram(String vertexShaderPath, String fragmentShaderPath) throws Exception {
        this.id = glCreateProgram();
        if (this.id == 0) {
            throw new Exception("Could not create shader program");
        }
        vertexShader = new Shader(vertexShaderPath, ShaderType.VERTEX);
        fragmentShader = new Shader(fragmentShaderPath, ShaderType.FRAGMENT);
        glAttachShader(this.id, this.vertexShader.getId());
        glAttachShader(this.id, this.fragmentShader.getId());

        // Link shader program
        glLinkProgram(this.id);
        if (glGetProgrami(this.id, GL_LINK_STATUS) == 0) {
            throw new Exception("Error linking Shader code: " + glGetProgramInfoLog(this.id, 1024));
        }

        if (this.vertexShader.getId() != 0) {
            glDetachShader(this.id, this.vertexShader.getId());
        }
        if (this.fragmentShader.getId() != 0) {
            glDetachShader(this.id, this.fragmentShader.getId());
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

    public Shader getFragmentShader() {
        return fragmentShader;
    }

    public Shader getVertexShader() {
        return vertexShader;
    }
}
