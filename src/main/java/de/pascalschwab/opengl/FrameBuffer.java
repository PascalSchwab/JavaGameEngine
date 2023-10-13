package de.pascalschwab.opengl;

import static org.lwjgl.opengl.GL30.*;

public class FrameBuffer extends OpenGLObject {
    private final GLTexture texture;
    private final RenderBufferObject renderBufferObject;
    public FrameBuffer(int width, int height) {
        super(glGenFramebuffers());
        bind();
        this.texture = new GLTexture();
        this.texture.create2DTexture(width, height);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, this.texture.getId(), 0);
        this.renderBufferObject = new RenderBufferObject();
        this.renderBufferObject.createDepthStencilBuffer(width, height);
        glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_STENCIL_ATTACHMENT, GL_RENDERBUFFER, this.renderBufferObject.getId());
        if(glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE){
            throw new IllegalStateException("Framebuffer is not complete!");
        }
        unbind();
    }

    @Override
    public void bind(){
        glBindFramebuffer(GL_FRAMEBUFFER, this.id);
    }

    @Override
    public void unbind(){
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

    @Override
    public void dispose(){
        glDeleteFramebuffers(this.id);
    }
}
