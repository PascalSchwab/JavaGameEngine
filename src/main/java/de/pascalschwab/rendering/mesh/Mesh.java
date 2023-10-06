package de.pascalschwab.rendering.mesh;

import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

public abstract class Mesh {
    protected int vertexArrayObject;
    protected int verticeCount;
    protected int instanceCount;
    protected List<Integer> vertexBufferObjects = new ArrayList<>();

    public void dispose() {
        vertexBufferObjects.forEach(GL30::glDeleteBuffers);
        glDeleteVertexArrays(vertexArrayObject);
    }

    public final int getVertexArrayObjectId() {
        return vertexArrayObject;
    }

    public final int getVertexCount() {
        return verticeCount;
    }

    public final int getInstanceCount(){return instanceCount;}
}
