package de.pascalschwab.rendering.texture;

import org.joml.Vector2f;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Objects;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.stb.STBImage.*;

public final class Texture {
    private final Vector2f units;
    private final Vector2f frameSize;
    private final Vector2f size;
    private int id;

    public Texture(int width, int height, ByteBuffer buffer) {
        units = new Vector2f(1f / (width / 2f), 1f / (height / 2f));
        this.frameSize = new Vector2f(width, height);
        this.size = new Vector2f(width, height);
        generateTexture(width, height, buffer);
    }

    public Texture(String path) {
        this(path, null);
    }

    public Texture(String path, Vector2f frameSize) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            ByteBuffer buffer = stbi_load(path, w, h, channels, 4);
            if (buffer == null) {
                throw new RuntimeException("Image file [" + path + "] not loaded: " + stbi_failure_reason());
            }

            int width = w.get();
            int height = h.get();
            this.frameSize = Objects.requireNonNullElseGet(frameSize, () -> new Vector2f(width, height));
            this.size = new Vector2f(width/this.frameSize.x, height/this.frameSize.y);
            units = new Vector2f(1f / width, 1f / height);

            generateTexture(width, height, buffer);

            stbi_image_free(buffer);
        }
    }

    private void generateTexture(int width, int height, ByteBuffer buffer) {
        id = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, id);
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
        glGenerateMipmap(GL_TEXTURE_2D);
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public void dispose() {
        glDeleteTextures(id);
    }

    public Vector2f getUnits() {
        return units;
    }

    public Vector2f getFrameSize() {
        return frameSize;
    }

    public Vector2f getSize(){return size;}
}
