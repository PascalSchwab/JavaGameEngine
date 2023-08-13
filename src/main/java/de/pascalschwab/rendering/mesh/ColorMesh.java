package de.pascalschwab.rendering.mesh;

import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public final class ColorMesh extends Mesh {
    public ColorMesh(float[] positions, float[] colors, int[] indices) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            this.verticeCount = indices.length;

            vertexArrayObject = glGenVertexArrays();
            glBindVertexArray(vertexArrayObject);

            int vertexBufferObject;
            // Positions VBO
            vertexBufferObject = glGenBuffers();
            vertexBufferObjects.add(vertexBufferObject);
            FloatBuffer positionsBuffer = stack.callocFloat(positions.length);
            positionsBuffer.put(0, positions);
            glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
            glBufferData(GL_ARRAY_BUFFER, positionsBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(0);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

            // Color VBO
            vertexBufferObject = glGenBuffers();
            vertexBufferObjects.add(vertexBufferObject);
            FloatBuffer colorsBuffer = stack.callocFloat(colors.length);
            colorsBuffer.put(0, colors);
            glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
            glBufferData(GL_ARRAY_BUFFER, colorsBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(1);
            glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);

            // Index VBO
            vertexBufferObject = glGenBuffers();
            vertexBufferObjects.add(vertexBufferObject);
            IntBuffer indicesBuffer = stack.callocInt(indices.length);
            indicesBuffer.put(0, indices);
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vertexBufferObject);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        }
    }
}
