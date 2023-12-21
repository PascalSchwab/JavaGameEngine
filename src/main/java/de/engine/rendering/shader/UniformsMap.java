package de.engine.rendering.shader;

import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

/**
 * Every shader has a uniform map, where all uniforms are saved
 */
public final class UniformsMap {
    private final int shaderId;
    private final Map<String, Integer> uniforms = new HashMap<>();

    public UniformsMap(int shaderId) {
        this.shaderId = shaderId;
    }

    public void createUniform(String uniformName) {
        int uniformLocation = glGetUniformLocation(shaderId, uniformName);
        if (uniformLocation < 0) {
            throw new ShaderException("Could not find uniform [" + uniformName + "] in shader program [" +
                    shaderId + "]");
        }
        uniforms.put(uniformName, uniformLocation);
    }

    public void setUniform(String uniformName, Matrix4f value) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            glUniformMatrix4fv(getUniformLocation(uniformName), false, value.get(stack.mallocFloat(16)));
        }
        catch (Exception e){
            throw new ShaderException("Can't create uniform: " + uniformName);
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
            throw new ShaderException("Could not find uniform [" + uniformName + "]");
        }
        return location;
    }

    public void setUniform(String uniformName, float[] values) {
        glUniform1fv(getUniformLocation(uniformName), values);
    }
}