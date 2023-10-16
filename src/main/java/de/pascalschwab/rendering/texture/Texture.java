package de.pascalschwab.rendering.texture;

import de.pascalschwab.opengl.GLTexture;
import org.joml.Vector2f;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Objects;

import static org.lwjgl.stb.STBImage.*;

public final class Texture {
    private final GLTexture glTexture;
    private final Vector2f units;
    private final Vector2f frameSize;
    private final Vector2f size;

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
            this.size = new Vector2f(width / this.frameSize.x, height / this.frameSize.y);
            this.units = new Vector2f(1f / width, 1f / height);
            this.glTexture = new GLTexture();

            glTexture.create2DTextureFromBuffer(width, height, buffer);

            stbi_image_free(buffer);
        }
    }

    public Texture(Vector2f frameSize) {
        this.size = new Vector2f(1, 1);
        this.units = new Vector2f(1f / frameSize.x, 1f / frameSize.y);
        this.frameSize = frameSize;
        this.glTexture = new GLTexture();
        this.glTexture.create2DTexture((int) frameSize.x, (int) frameSize.y);
    }

    public void bind() {
        this.glTexture.bind();
    }

    public void unbind() {
        this.glTexture.unbind();
    }

    public void dispose() {
        this.glTexture.dispose();
    }

    public Vector2f getUnits() {
        return units;
    }

    public Vector2f getFrameSize() {
        return frameSize;
    }

    public Vector2f getSize() {
        return size;
    }
}
