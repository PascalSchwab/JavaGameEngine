package de.pascalschwab.rendering.mesh;

import de.pascalschwab.opengl.VertexArrayObject;
import de.pascalschwab.opengl.VertexBufferObject;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15C.glDeleteBuffers;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

public abstract class Mesh {
    protected VertexArrayObject vertexArrayObject;
    protected int verticeCount;
    protected int instanceCount;
    protected VertexBufferObject[] vertexBufferObjects;

    public void dispose() {
        for(int i = 0; i < vertexBufferObjects.length; i++){
            glDeleteBuffers(vertexBufferObjects[i].getId());
        }
        glDeleteVertexArrays(vertexArrayObject.getId());
    }

    public final int getVertexCount() {
        return verticeCount;
    }

    public final int getInstanceCount(){return instanceCount;}

    public final VertexArrayObject getVertexArrayObject(){
        return this.vertexArrayObject;
    }
}
