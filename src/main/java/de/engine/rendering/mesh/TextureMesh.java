package de.engine.rendering.mesh;

import de.engine.rendering.opengl.GLBufferType;
import de.engine.rendering.opengl.GLDrawType;
import de.engine.rendering.opengl.VertexArrayObject;
import de.engine.rendering.opengl.VertexBufferObject;
import org.apache.commons.lang3.ArrayUtils;

import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public final class TextureMesh extends Mesh {
    public TextureMesh(float[] positions, float[] textureCoords, int[] indices){
        this(positions, textureCoords, indices, 1);
    }
    public TextureMesh(float[] positions, float[] textureCoords, int[] indices, int instanceCount) {
        this.verticeCount = indices.length;
        this.instanceCount = instanceCount;

        vertexBufferObjects = new VertexBufferObject[3];
        vertexBufferObjects[0] = new VertexBufferObject<>(ArrayUtils.toObject(positions), 3, GLBufferType.ARRAY, GLDrawType.STATIC);
        vertexBufferObjects[1] = new VertexBufferObject<>(ArrayUtils.toObject(textureCoords), 2, GLBufferType.ARRAY, GLDrawType.STATIC);
        vertexBufferObjects[2] = new VertexBufferObject<>(ArrayUtils.toObject(indices), 1, GLBufferType.ELEMENT, GLDrawType.STATIC);

        vertexArrayObject = new VertexArrayObject(vertexBufferObjects);
    }
}