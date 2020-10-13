#version 460 core

in vec3 pos;
in vec2 textureCoords;
in vec3 normal;

out vec2 pass_textureCoords;
out vec3 surfaceNormal;
out vec3 toLightVector;

uniform mat4 transMatrix;
uniform mat4 projMatrix;
uniform mat4 viewMatrix;

void main() {
    vec4 worldPos = transMatrix * vec4(pos, 1.0);

    gl_Position = projMatrix * viewMatrix * worldPos;
    pass_textureCoords = textureCoords;

    surfaceNormal = (transMatrix * vec4(normal, 0.0)).xyz;
    toLightVector = vec3(10000, 0, 0) - worldPos.xyz;
}
