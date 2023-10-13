package de.pascalschwab.opengl;

import org.lwjgl.system.MemoryStack;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

public class VertexArrayObject extends OpenGLObject {
    private final VertexBufferObject[] vertexBufferObjects;
    public VertexArrayObject(VertexBufferObject[] vertexBufferObjects) {
        super(glGenVertexArrays());
        this.vertexBufferObjects = vertexBufferObjects;

        try(MemoryStack stack = MemoryStack.stackPush()){
            bind();

            for(int i = 0; i < vertexBufferObjects.length; i++){
                vertexBufferObjects[i].setBufferData(stack);
                if(vertexBufferObjects[0].getSingleDataSize() > 0){
                    glEnableVertexAttribArray(i);
                    glVertexAttribPointer(i, vertexBufferObjects[i].getSingleDataSize(), GL_FLOAT, false, 0, 0);
                }
            }

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            unbind();
        }
    }

    @Override
    public void bind(){
        glBindVertexArray(this.id);
    }

    @Override
    public void unbind(){
        glBindVertexArray(0);
    }

    public VertexBufferObject[] getVertexBufferObjects() {
        return vertexBufferObjects;
    }

    @Override
    public void dispose() {
        glDeleteVertexArrays(this.id);
    }
}
