package de.engine.rendering.opengl;

/**
 * OpenGLObject is the base of all objects, which handles with OpenGL.
 */
public abstract class OpenGLObject {
    private final int id;
    public OpenGLObject(int id) {
        this.id = id;
        if(this.id == 0){
            throw new OpenGLException("Can't create OpenGLObject");
        }
    }
    public int getId() {
        return id;
    }
    public abstract void bind();
    public abstract void unbind();
    public abstract void dispose();
}
