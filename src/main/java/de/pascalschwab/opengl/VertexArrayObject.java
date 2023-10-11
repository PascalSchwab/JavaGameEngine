package de.pascalschwab.opengl;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class VertexArrayObject extends OpenGLObject{
    private final VertexBufferObject[] vertexBufferObjects;
    public VertexArrayObject(VertexBufferObject[] vertexBufferObjects) {
        super(glGenVertexArrays());
        this.vertexBufferObjects = vertexBufferObjects;

        glBindVertexArray(this.id);

        for(int i = 0; i < vertexBufferObjects.length; i++){
            vertexBufferObjects[i].bind();
            if(vertexBufferObjects[i].getSingleDataSize() != 0){
                glEnableVertexAttribArray(i);
                glVertexAttribPointer(i, vertexBufferObjects[i].getSingleDataSize(),
                        GL_FLOAT, false, 0, 0);
            }
        }

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }
}
