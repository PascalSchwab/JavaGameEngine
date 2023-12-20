package de.engine.rendering.opengl;

import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;

public final class VertexBufferObject extends OpenGLObject{
    private final float[] floatData;
    private final int[] intData;
    private final int singleDataSize;
    private final GLBufferType bufferType;
    private final GLDrawType drawType;

    public VertexBufferObject(int[] data){
        this(data, 1, GLBufferType.ELEMENT, GLDrawType.STATIC);
    }

    public VertexBufferObject(int[] data, int singleDataSize, GLBufferType bufferType, GLDrawType drawType) {
        super(glGenBuffers());
        this.intData = data;
        this.floatData = null;
        this.singleDataSize = singleDataSize;
        this.bufferType = bufferType;
        this.drawType = drawType;
    }

    public VertexBufferObject(float[] data, int singleDataSize, GLBufferType bufferType, GLDrawType drawType) {
        super(glGenBuffers());
        this.floatData = data;
        this.intData = null;
        this.singleDataSize = singleDataSize;
        this.bufferType = bufferType;
        this.drawType = drawType;
    }

    public void setBufferData(MemoryStack stack){
        if(floatData != null){
            FloatBuffer buffer = stack.callocFloat(floatData.length);
            buffer.put(floatData).flip();
            glBufferData(this.bufferType.getType(), buffer, this.drawType.getType());
        }
        else if(intData != null){
            IntBuffer buffer = stack.callocInt(intData.length);
            buffer.put(intData).flip();
            glBufferData(this.bufferType.getType(), buffer, this.drawType.getType());
        }
    }

    @Override
    public void bind(){
        glBindBuffer(this.bufferType.getType(), this.getId());
    }

    @Override
    public void unbind(){
        glBindBuffer(this.bufferType.getType(), 0);
    }

    public int getSingleDataSize(){
        return this.singleDataSize;
    }

    public GLBufferType getBufferType() {
        return bufferType;
    }

    public GLDrawType getDrawType() {
        return drawType;
    }

    @Override
    public void dispose() {
        glDeleteBuffers(this.getId());
    }
}
