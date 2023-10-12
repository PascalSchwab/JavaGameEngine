package de.pascalschwab.rendering.mesh;

import de.pascalschwab.opengl.VertexArrayObject;
import de.pascalschwab.opengl.VertexBufferObject;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public final class TextureMesh extends Mesh {
    private VertexArrayObject vAO;
    private VertexBufferObject[] vBOs;
    public TextureMesh(float[] positions, float[] textureCoords, int[] indices){
        this(positions, textureCoords, indices, 1);
    }
    public TextureMesh(float[] positions, float[] textureCoords, int[] indices, int instanceCount) {
        this.verticeCount = indices.length;
        this.instanceCount = instanceCount;

        vBOs = new VertexBufferObject[3];
        vBOs[0] = new VertexBufferObject(positions, 3);
        vBOs[1] = new VertexBufferObject(textureCoords, 2);
        vBOs[2] = new VertexBufferObject(indices);

        vertexArrayObject = glGenVertexArrays();
        glBindVertexArray(vertexArrayObject);

        vBOs[0].bind();
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        vBOs[1].bind();
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

        vBOs[2].bind();

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

        /*try (MemoryStack stack = MemoryStack.stackPush()) {
            this.verticeCount = indices.length;
            this.instanceCount = instanceCount;

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

            // Texture VBO
            vertexBufferObject = glGenBuffers();
            vertexBufferObjects.add(vertexBufferObject);
            FloatBuffer textureCoordsBuffer = MemoryUtil.memAllocFloat(textureCoords.length);
            textureCoordsBuffer.put(0, textureCoords);
            glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
            glBufferData(GL_ARRAY_BUFFER, textureCoordsBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(1);
            glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

            // Index VBO
            vertexBufferObject = glGenBuffers();
            vertexBufferObjects.add(vertexBufferObject);
            IntBuffer indicesBuffer = stack.callocInt(indices.length);
            indicesBuffer.put(0, indices);
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vertexBufferObject);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        }*/
    }
}
