package de.engine.rendering.opengl;

import static org.lwjgl.opengl.GL30.*;

/**
 * Not implemented
 */
@Deprecated
public final class RenderBufferObject extends OpenGLObject{
    public RenderBufferObject(){
        super(glGenRenderbuffers());
    }
    public void createDepthStencilBuffer(int width, int height){
        bind();
        glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH24_STENCIL8, width, height);
        unbind();
    }
    @Override
    public void bind() {
        glBindRenderbuffer(GL_RENDERBUFFER, this.getId());
    }

    @Override
    public void unbind() {
        glBindRenderbuffer(GL_RENDERBUFFER, 0);
    }

    @Override
    public void dispose() {
        glDeleteRenderbuffers(this.getId());
    }
}
