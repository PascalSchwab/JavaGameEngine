package de.pascalschwab.geometry;

public class Triangle {
/*    private final ShaderProgram shaderProgram;
    // Positions, Colors, Indices for now legit
    float[] colors = new float[]{
            0.5f, 0.0f, 0.0f,
            0.0f, 0.5f, 0.0f,
            0.0f, 0.0f, 0.5f,
    };
    int[] indices = new int[]{
            0, 1, 3,
    };
    private float[] positions = {
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0f, 0.5f, 0f};
    private Mesh mesh = new Mesh(positions, colors, indices);

    public Triangle(Window window, GameObject parent, Vector2 position, Vector2 size, int zIndex, ShaderProgram shaderProgram) {
        super(window, parent, position, size, zIndex);
        this.shaderProgram = shaderProgram;
    }

    @Override
    public void draw() {
        shaderProgram.bind();

        glBindVertexArray(mesh.getVertexArrayObjectId());
        glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);

        glBindVertexArray(0);
        shaderProgram.unbind();
    }*/
}
