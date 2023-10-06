#version 450

layout (location = 0) in vec3 position;
layout (location = 1) in vec3 colour;

uniform mat4 viewMatrix;
uniform mat4 transformMatrix;

out vec3 outColour;

void main()
{
    gl_Position = transformMatrix * viewMatrix * vec4(position, 1.0);
    outColour = colour;
}