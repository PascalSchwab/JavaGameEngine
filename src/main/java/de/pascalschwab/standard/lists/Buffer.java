package de.pascalschwab.standard.lists;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Buffer {
    public static FloatBuffer createFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static IntBuffer createIntBuffer(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
}
