#version 450

layout (triangles) in;
layout (triangle_strip, max_vertices = 4) out;

in  vec3 outVertexColour[];
out vec3 outGeoColour;

void main() {
    for (int i = 0; i < gl_in.length(); i++){
        gl_Position = gl_in[i].gl_Position;
        outGeoColour = outVertexColour[i];
        EmitVertex();
    }
    EndPrimitive();
}