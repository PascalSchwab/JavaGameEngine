package de.engine.rendering.opengl;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.system.MemoryUtil.NULL;

public final class GLTexture extends OpenGLObject{
    public GLTexture() {
        super(glGenTextures());
    }

    public void create2DTextureFromBuffer(int width, int height, ByteBuffer buffer){
        bind();
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
        glGenerateMipmap(GL_TEXTURE_2D);
        unbind();
    }

    public void create2DTexture(int width, int height){
        bind();
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height,
                0, GL_RGB, GL_UNSIGNED_BYTE, NULL);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        unbind();
    }

    @Override
    public void bind(){
        glBindTexture(GL_TEXTURE_2D, this.getId());
    }

    @Override
    public void unbind(){
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    @Override
    public void dispose(){
        glDeleteTextures(this.getId());
    }
}
