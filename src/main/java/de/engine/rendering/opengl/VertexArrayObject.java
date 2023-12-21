package de.engine.rendering.opengl;

import org.lwjgl.system.MemoryStack;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

/**
 * It combines all VBOs and makes pointer on them.
 */
public final class VertexArrayObject extends OpenGLObject {
    private final VertexBufferObject[] vertexBufferObjects;
    public VertexArrayObject(VertexBufferObject[] vertexBufferObjects) {
        super(glGenVertexArrays());
        this.vertexBufferObjects = vertexBufferObjects;

        try(MemoryStack stack = MemoryStack.stackPush()){
            // Bind VAO
            bind();

            for(int i = 0; i < vertexBufferObjects.length; i++){
                // Bind Array Buffer
                vertexBufferObjects[i].bind();

                vertexBufferObjects[i].setBufferData(stack);
                glVertexAttribPointer(i, vertexBufferObjects[i].getSingleDataSize(), GL_FLOAT, false, 0, 0);
                glEnableVertexAttribArray(i);

                // Unbind Array Buffer
                if(vertexBufferObjects[i].getBufferType() != GLBufferType.ELEMENT){
                    vertexBufferObjects[i].unbind();
                }
            }

            // Unbind Element Buffer and VAO
            unbind();
            for(VertexBufferObject vbo : vertexBufferObjects){
                if(vbo.getBufferType() == GLBufferType.ELEMENT){
                    vbo.unbind();
                }
            }
        }
        catch (Exception e){
            throw new OpenGLException("Can't create VAO. Stack problem.");
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
        for(VertexBufferObject vbo : vertexBufferObjects){
            vbo.dispose();
        }
    }
}
