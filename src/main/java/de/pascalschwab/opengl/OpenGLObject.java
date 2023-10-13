package de.pascalschwab.opengl;

public abstract class OpenGLObject {
    protected final int id;

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
