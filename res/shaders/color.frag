#version 450

in  vec3 outColour;

out vec4 fragColor;

void main()
{
    fragColor = vec4(outColour, 0.2);
}