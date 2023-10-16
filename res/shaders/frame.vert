#version 450

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 textureCoord;

out vec2 outTextureCoord;

uniform mat4 viewMatrix;
uniform mat4 transformMatrix;

void main()
{
    gl_Position =  transformMatrix * viewMatrix * vec4(position, 1.0);
    outTextureCoord = textureCoord;
}