package de.pascalschwab.rendering;

import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

public class Mesh {
    private int vertexArrayObject;
    private int verticeCount;
    private List<Integer> vertexBufferObjects = new ArrayList<>();

    public Mesh(float[] positions, int verticeCount) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            this.verticeCount = verticeCount;

            vertexArrayObject = glGenVertexArrays();
            glBindVertexArray(vertexArrayObject);

            int vertexBufferObject = glGenBuffers();
            vertexBufferObjects.add(vertexBufferObject);
            FloatBuffer positionsBuffer = stack.callocFloat(positions.length);
            positionsBuffer.put(0, positions);
            glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
            glBufferData(GL_ARRAY_BUFFER, positionsBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(0);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        }
    }

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
