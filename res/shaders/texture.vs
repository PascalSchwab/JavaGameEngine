#version 330

layout (location=0) in vec3 position;
layout (location=1) in vec2 texCoord;

out vec2 outTextCoord;

//uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

void main()
{
    //gl_Position = viewMatrix * projectionMatrix * vec4(position, 1.0);
    gl_Position = viewMatrix * vec4(position, 1.0);
    outTextCoord = texCoord;
}