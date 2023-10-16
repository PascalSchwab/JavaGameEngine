#version 450

in  vec2 outTextureCoord;

out vec4 fragColor;

uniform sampler2D txtSampler;

void main()
{
    fragColor = texture(txtSampler, outTextureCoord) * vec4(0.5f, 0.2f, 0.1f, 0.5f);
}