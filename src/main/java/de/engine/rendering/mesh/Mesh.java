package de.engine.rendering.mesh;

import de.engine.rendering.opengl.VertexArrayObject;

public abstract class Mesh {
    protected VertexArrayObject vertexArrayObject;
    protected int verticeCount;
    protected int instanceCount;

    public void dispose() {
        vertexArrayObject.dispose();
    }

    public final int getVertexCount() {
        return verticeCount;
    }

    public final int getInstanceCount() {
        return instanceCount;
    }

    public final VertexArrayObject getVertexArrayObject() {
        return this.vertexArrayObject;
    }
}
