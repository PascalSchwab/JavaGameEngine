package de.engine.rendering.opengl;

/**
 * OpenGLObject is the base of all objects, which handle with OpenGL
 */
abstract class OpenGLObject {
    private final int id;

    public OpenGLObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public abstract void bind();
    public abstract void unbind();
    public abstract void dispose();
}
