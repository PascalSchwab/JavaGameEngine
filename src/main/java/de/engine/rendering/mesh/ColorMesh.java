package de.engine.rendering.mesh;

import de.engine.rendering.opengl.VertexArrayObject;
import de.engine.rendering.opengl.VertexBufferObject;

import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public final class ColorMesh extends Mesh {
    public ColorMesh(float[] positions, float[] colors, int[] indices){
        this(positions, colors, indices, 1);
    }
    public ColorMesh(float[] positions, float[] colors, int[] indices, int instanceCount) {
        this.verticeCount = indices.length;
        this.instanceCount = instanceCount;

        vertexBufferObjects = new VertexBufferObject[3];
        vertexBufferObjects[0] = new VertexBufferObject(positions, 3);
        vertexBufferObjects[1] = new VertexBufferObject(colors, 3);
        vertexBufferObjects[2] = new VertexBufferObject(indices);

        vertexArrayObject = new VertexArrayObject(vertexBufferObjects);
    }
}
