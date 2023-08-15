#version 450

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 textureCoord;

uniform mat4 viewMatrix;
uniform mat4 transformMatrix;

out vec2 outVertexTextureCoord;

void main()
{
    gl_Position =  transformMatrix * viewMatrix * vec4(position, 1.0);
    outVertexTextureCoord = textureCoord;
}