package de.pascalschwab.opengl;

import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

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

    public void bind(MemoryStack stack){
        if(floatData != null){
            FloatBuffer buffer = stack.callocFloat(floatData.length);
            buffer.put(0, floatData);
            glBindBuffer(GL_ARRAY_BUFFER, this.id);
            glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        }
        else if(intData != null){
            IntBuffer buffer = stack.callocInt(intData.length);
            buffer.put(0, intData);
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.id);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        }
    }

    public void unbind(){
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public int getSingleDataSize(){
        return this.singleDataSize;
    }
}
