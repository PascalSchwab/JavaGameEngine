package de.engine.rendering.mesh;

import de.engine.rendering.opengl.GLBufferType;
import de.engine.rendering.opengl.GLDrawType;
import de.engine.rendering.opengl.VertexArrayObject;
import de.engine.rendering.opengl.VertexBufferObject;
import de.engine.rendering.texture.Texture;

import java.nio.charset.StandardCharsets;

public class TextMesh extends Mesh {
    private static final int VERTICES_PER_QUAD = 4;

    public TextMesh(String text, Texture texture, int numCols, int numRows) {
        byte[] chars = text.getBytes(StandardCharsets.ISO_8859_1);
        int numChars = chars.length;

        float[] positions = new float[VERTICES_PER_QUAD * 3 * numChars];
        float[] textCoords = new float[VERTICES_PER_QUAD * 2 * numChars];
        int[] indices = new int[VERTICES_PER_QUAD * numChars];

        float tileWidth = texture.getSize().x / (float) numCols;
        float tileHeight = texture.getSize().y / (float) numRows;

        for (int i = 0; i < numChars; i++) {
            byte currChar = chars[i];
            int col = currChar % numCols;
            int row = currChar / numCols;

            // Build a character tile composed by two triangles

            // Left Top vertex
            positions[i * 3] = (float) i * tileWidth;
            positions[i * 3 + 1] = 0.0f;
            positions[i * 3 + 2] = 0.0f;
            textCoords[i * 2] = (float) col / (float) numCols;
            textCoords[i * 2 + 1] = (float) row / (float) numRows;
            indices[i] = i * VERTICES_PER_QUAD;

            // Left Bottom vertex
            positions[i * 3] = (float) i * tileWidth;
            positions[i * 3 + 1] = tileHeight;
            positions[i * 3 + 2] = 0.0f;
            textCoords[i * 2] = (float) col / (float) numCols;
            textCoords[i * 2 + 1] = (float) row + 1 / (float) numRows;
            indices[i] = i * VERTICES_PER_QUAD + 1;

            // Right Bottom vertex
            positions[i * 3] = (float) i * tileWidth + tileWidth;
            positions[i * 3 + 1] = tileHeight;
            positions[i * 3 + 2] = 0.0f;
            textCoords[i * 2] = (float) col + 1 / (float) numCols;
            textCoords[i * 2 + 1] = (float) row + 1 / (float) numRows;
            indices[i] = i * VERTICES_PER_QUAD + 2;

            // Right Top vertex
            positions[i * 3] = (float) i * tileWidth + tileWidth;
            positions[i * 3 + 1] = 0.0f;
            positions[i * 3 + 2] = 0.0f;
            textCoords[i * 2] = (float) col + 1 / (float) numCols;
            textCoords[i * 2 + 1] = (float) row / (float) numRows;
            indices[i] = i * VERTICES_PER_QUAD + 3;

            // Add indices por left top and bottom right vertices
            indices[i] = i * VERTICES_PER_QUAD;
            indices[i] = i * VERTICES_PER_QUAD + 2;
        }

        vertexBufferObjects = new VertexBufferObject[3];
        vertexBufferObjects[0] = new VertexBufferObject(positions, 3, GLBufferType.ARRAY, GLDrawType.STATIC);
        vertexBufferObjects[1] = new VertexBufferObject(textCoords, 2, GLBufferType.ARRAY, GLDrawType.STATIC);
        vertexBufferObjects[2] = new VertexBufferObject(indices);

        vertexArrayObject = new VertexArrayObject(vertexBufferObjects);
    }
}
