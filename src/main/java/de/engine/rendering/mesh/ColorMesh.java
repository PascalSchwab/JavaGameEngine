package de.engine.rendering.mesh;

import de.engine.rendering.opengl.GLBufferType;
import de.engine.rendering.opengl.GLDrawType;
import de.engine.rendering.opengl.VertexArrayObject;
import de.engine.rendering.opengl.VertexBufferObject;
import org.apache.commons.lang3.ArrayUtils;

import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

/**
 * Mesh with position, colors and indices data
 */
public final class ColorMesh extends Mesh {
    public ColorMesh(float[] positions, float[] colors, int[] indices){
        this(positions, colors, indices, 1);
    }
    public ColorMesh(float[] positions, float[] colors, int[] indices, int instanceCount) {
        this.verticeCount = indices.length;
        this.instanceCount = instanceCount;

        VertexBufferObject[] vertexBufferObjects = new VertexBufferObject[]{
                new VertexBufferObject<>(ArrayUtils.toObject(positions), 3, GLBufferType.ARRAY, GLDrawType.DYNAMIC),
                new VertexBufferObject<>(ArrayUtils.toObject(colors), 3, GLBufferType.ARRAY, GLDrawType.STATIC),
                new VertexBufferObject<>(ArrayUtils.toObject(indices), 1, GLBufferType.ELEMENT, GLDrawType.STATIC)
        };

        vertexArrayObject = new VertexArrayObject(vertexBufferObjects);
    }
}
