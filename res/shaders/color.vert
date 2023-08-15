#version 330

layout (location=0) in vec3 inPosition;
layout (location=1) in vec3 inColour;

uniform mat4 viewMatrix;
uniform mat4 transformMatrix;

out vec3 exColour;

void main()
{
    gl_Position = transformMatrix * viewMatrix * vec4(inPosition, 1.0);
    exColour = inColour;
}