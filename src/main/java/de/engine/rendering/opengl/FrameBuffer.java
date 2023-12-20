package de.engine.rendering.opengl;

import static org.lwjgl.opengl.GL30.*;

public final class FrameBuffer extends OpenGLObject {
    private final GLTexture texture;
    private final int width;
    private final int height;

    public FrameBuffer(int width, int height) {
        super(glGenFramebuffers());
        this.width = width;
        this.height = height;
        bind();
        this.texture = new GLTexture();
        this.texture.create2DTexture(width, height);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, this.texture.getId(), 0);
        if (glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE) {
            throw new IllegalStateException("Framebuffer is not complete!");
        }
        unbind();
    }

    @Override
    public void bind() {
        glBindFramebuffer(GL_FRAMEBUFFER, this.getId());
    }

    @Override
    public void unbind() {
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

    @Override
    public void dispose() {
        glDeleteFramebuffers(this.getId());
    }

    public GLTexture getTexture() {
        return texture;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
