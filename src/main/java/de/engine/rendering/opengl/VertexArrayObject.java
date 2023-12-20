package de.engine.rendering.opengl;

import org.lwjgl.system.MemoryStack;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

public final class VertexArrayObject extends OpenGLObject {
    public VertexArrayObject(VertexBufferObject[] vertexBufferObjects) {
        super(glGenVertexArrays());

        try(MemoryStack stack = MemoryStack.stackPush()){
            bind();

            for(int i = 0; i < vertexBufferObjects.length; i++){
                vertexBufferObjects[i].bind();

                vertexBufferObjects[i].setBufferData(stack);
                glVertexAttribPointer(i, vertexBufferObjects[i].getSingleDataSize(), GL_FLOAT, false, 0, 0);
                glEnableVertexAttribArray(i);

                if(vertexBufferObjects[i].getBufferType() != GLBufferType.ELEMENT){
                    vertexBufferObjects[i].unbind();
                }
            }

            unbind();
            for(VertexBufferObject vbo : vertexBufferObjects){
                if(vbo.getBufferType() == GLBufferType.ELEMENT){
                    vbo.unbind();
                }
            }
        }
    }

    @Override
    public void bind(){
        glBindVertexArray(this.getId());
    }

    @Override
    public void unbind(){
        glBindVertexArray(0);
    }

    @Override
    public void dispose() {
        glDeleteVertexArrays(this.getId());
    }
}
