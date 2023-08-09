#version 330

layout (location=0) in vec3 inPosition;

uniform mat4 projectionMatrix;

void main()
{
    gl_Position = projectionMatrix * vec4(inPosition, 1.0);
}