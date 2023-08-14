#version 330

layout (location=0) in vec3 inPosition;
layout (location=1) in vec3 inColour;

uniform mat4 viewMatrix;
uniform mat4 transformation;

out vec3 exColour;

void main()
{
    gl_Position = transformation * viewMatrix * vec4(inPosition, 1.0);
    exColour = inColour;
}