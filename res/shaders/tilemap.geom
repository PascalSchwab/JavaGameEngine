#version 450

layout (triangles) in;
layout (triangle_strip, max_vertices = 4) out;
layout (location = 2) in uint tileIDs[];

in  vec2 outVertexTextureCoord[];
out vec2 outGeoTextureCoord;

void main() {
    for (int i = 0; i < gl_in.length(); i++){
        gl_Position = gl_in[i].gl_Position;
        outGeoTextureCoord = outVertexTextureCoord[i];
        EmitVertex();
    }
    EndPrimitive();
}
