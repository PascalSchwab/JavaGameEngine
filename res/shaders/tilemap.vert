#version 450

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 textureCoord;

uniform mat4 viewMatrix;
uniform mat4 transformMatrix;

uniform float[2] positionUSize;
uniform float[400] tilePositions;

uniform float[2] textureUSize;
uniform float[2] textureSize;
uniform float[200] tileIDs;

out vec2 outTextureCoord;

struct Tile{
    float id;
    vec2 position;
};

void main()
{
    Tile tile = Tile(tileIDs[gl_InstanceID], vec2(tilePositions[gl_InstanceID*2], tilePositions[gl_InstanceID*2+1]));

    vec3 offset = vec3(tile.position.x, tile.position.y, 0);
    gl_Position = transformMatrix * viewMatrix * vec4(position + offset * vec3(positionUSize[0], -positionUSize[1], 0), 1.0);

    vec2 textureOffset = vec2(mod(tile.id, textureSize[0]), floor(tile.id/textureSize[0]));
    vec2 tileSize = vec2(textureUSize[0], textureUSize[1]);

    outTextureCoord = textureCoord + textureOffset * tileSize;
}