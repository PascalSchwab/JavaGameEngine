package de.pascalschwab.opengl;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GLTexture extends OpenGLObject{
    public GLTexture() {
        super(glGenTextures());
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
        glBindTexture(GL_TEXTURE_2D, this.id);
    }

    @Override
    public void unbind(){
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    @Override
    public void dispose(){
        glDeleteTextures(this.id);
    }
}
