package de.engine.rendering.shader;

import de.engine.standard.exceptions.EngineRuntimeException;

/**
 * Exception for shader errors
 */
public class ShaderException extends EngineRuntimeException {
    public ShaderException(String message) {
        super(message);
    }
}
