package de.engine.rendering.opengl;

import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;

/**
 * In the buffer are vertices saved. It will be sent to the GPU.
 */
public final class VertexBufferObject<T extends Number> extends OpenGLObject{
    private final T[] data;
    private final int singleDataSize;
    private final GLBufferType bufferType;
    private final GLDrawType drawType;

    public VertexBufferObject(T[] data, int singleDataSize, GLBufferType bufferType, GLDrawType drawType) {
        super(glGenBuffers());
        this.data = data;
        this.singleDataSize = singleDataSize;
        this.bufferType = bufferType;
        this.drawType = drawType;
    }

    public void setBufferData(MemoryStack stack){
        if(data instanceof Float[]){
            FloatBuffer buffer = stack.callocFloat(data.length);
            buffer.put(ArrayUtils.toPrimitive((Float[]) data)).flip();
            glBufferData(this.bufferType.getType(), buffer, this.drawType.getType());
        }
        else if(data instanceof Integer[]){
            IntBuffer buffer = stack.callocInt(data.length);
            buffer.put(ArrayUtils.toPrimitive((Integer[]) data)).flip();
            glBufferData(this.bufferType.getType(), buffer, this.drawType.getType());
        }
        else{
            throw new OpenGLException("There can only be Integer or Float data in an OpenGL buffer");
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

    @Override
    public void dispose() {
        glDeleteBuffers(this.getId());
    }
}
