package de.pascalschwab.opengl;

import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;

public final class VertexBufferObject extends OpenGLObject{
    private final float[] floatData;
    private final int[] intData;
    private final int singleDataSize;
    public VertexBufferObject(int[] data){
        this(data, 0);
    }
    public VertexBufferObject(float[] data){
        this(data, 0);
    }
    public VertexBufferObject(int[] data, int singleDataSize) {
        super(glGenBuffers());
        this.intData = data;
        this.floatData = null;
        this.singleDataSize = singleDataSize;
    }

    public VertexBufferObject(float[] data, int singleDataSize) {
        super(glGenBuffers());
        this.floatData = data;
        this.intData = null;
        this.singleDataSize = singleDataSize;
    }

    public void setBufferData(MemoryStack stack){
        if(floatData != null){
            FloatBuffer buffer = stack.callocFloat(floatData.length);
            buffer.put(0, floatData);
            bind();
            glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
            unbind(GL_ARRAY_BUFFER);
        }
        else if(intData != null){
            IntBuffer buffer = stack.callocInt(intData.length);
            buffer.put(0, intData);
            bind(GL_ELEMENT_ARRAY_BUFFER);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
            unbind(GL_ELEMENT_ARRAY_BUFFER);
        }
    }

    @Override
    public void bind() {
        bind(GL_ARRAY_BUFFER);
    }

    public void bind(int bufferType){
        glBindBuffer(bufferType, this.id);
    }

    @Override
    public void unbind(){
        unbind(GL_ARRAY_BUFFER);
    }

    public void unbind(int bufferType){
        glBindBuffer(bufferType, this.id);
    }

    public int getSingleDataSize(){
        return this.singleDataSize;
    }

    @Override
    public void dispose() {
        glDeleteBuffers(this.id);
    }
}
