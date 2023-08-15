#version 450

in  vec3 outGeoColour;

out vec4 fragColor;

void main()
{
    fragColor = vec4(outGeoColour, 0.2);
}