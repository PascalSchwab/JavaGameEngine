package de.engine.rendering.opengl;

import de.engine.standard.exceptions.EngineRuntimeException;

/**
 * Runtime Exception for OpenGL error
 */
public final class OpenGLException extends EngineRuntimeException {
    public OpenGLException(String message) {
        super(message);
    }
}
