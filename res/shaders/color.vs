#version 330

layout (location=0) in vec3 inPosition;

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

void main()
{
    gl_Position = viewMatrix * projectionMatrix * vec4(inPosition, 1.0);
}