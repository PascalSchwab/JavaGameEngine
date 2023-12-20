package de.engine.rendering.mesh;

import de.engine.rendering.opengl.VertexArrayObject;
import de.engine.rendering.opengl.VertexBufferObject;

public abstract class Mesh {
    protected VertexArrayObject vertexArrayObject;
    protected int verticeCount;
    protected int instanceCount;
    protected VertexBufferObject[] vertexBufferObjects;

    public void dispose() {
        vertexArrayObject.dispose();
        for (VertexBufferObject vbo : vertexBufferObjects) {
            vbo.dispose();
        }
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
