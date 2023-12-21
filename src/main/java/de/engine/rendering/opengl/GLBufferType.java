package de.engine.rendering.opengl;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;

/**
 * Buffer Type
 */
public enum GLBufferType {
    ARRAY(GL_ARRAY_BUFFER), ELEMENT(GL_ELEMENT_ARRAY_BUFFER);
    private final int type;
    GLBufferType(int type){
        this.type = type;
    }
    public int getType() {
        return type;
    }
}
