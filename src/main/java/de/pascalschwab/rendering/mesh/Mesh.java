package de.pascalschwab.rendering.mesh;

import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

public abstract class Mesh {
    protected int vertexArrayObject;
    protected int verticeCount;
    protected List<Integer> vertexBufferObjects = new ArrayList<>();

    public void dispose() {
        vertexBufferObjects.forEach(GL30::glDeleteBuffers);
        glDeleteVertexArrays(vertexArrayObject);
    }

    public int getVertexArrayObjectId() {
        return vertexArrayObject;
    }

    public int getVertexCount() {
        return verticeCount;
    }
}
