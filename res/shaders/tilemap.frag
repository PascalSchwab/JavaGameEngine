#version 450

in  vec2 outGeoTextureCoord;

out vec4 fragColor;

uniform sampler2D txtSampler;

void main()
{
    fragColor = texture(txtSampler, outGeoTextureCoord);
}