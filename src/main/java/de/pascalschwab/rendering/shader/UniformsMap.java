package de.pascalschwab.rendering.shader;

import org.joml.Matrix4d;
import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

public final class UniformsMap {
    private final int shaderProgramId;
    private final Map<String, Integer> uniforms = new HashMap<>();

    public UniformsMap(int shaderProgramId) {
        this.shaderProgramId = shaderProgramId;
    }

    public void createUniform(String uniformName) {
        int uniformLocation = glGetUniformLocation(shaderProgramId, uniformName);
        if (uniformLocation < 0) {
            throw new RuntimeException("Could not find uniform [" + uniformName + "] in shader program [" +
                    shaderProgramId + "]");
        }
        uniforms.put(uniformName, uniformLocation);
    }

    public void setUniform(String uniformName, Matrix4f value) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            glUniformMatrix4fv(getUniformLocation(uniformName), false, value.get(stack.mallocFloat(16)));
        }
    }

    public void setUniform(String uniformName, float value) {
        glUniform1f(getUniformLocation(uniformName), value);
    }

    public void setUniform(String uniformName, int value) {
        glUniform1i(getUniformLocation(uniformName), value);
    }

    private int getUniformLocation(String uniformName) {
        Integer location = uniforms.get(uniformName);
        if (location == null) {
            throw new RuntimeException("Could not find uniform [" + uniformName + "]");
        }
        return location;
    }

    public void setUniform(String uniformName, float[] values){
        glUniform1fv(getUniformLocation(uniformName), values);
    }
}