package de.engine.rendering.opengl;

import static org.lwjgl.opengl.GL15.*;

public enum GLDrawType {
    DYNAMIC(GL_DYNAMIC_DRAW), STATIC(GL_STATIC_DRAW), STREAM(GL_STREAM_DRAW);

    private final int type;

    GLDrawType(int type){
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
