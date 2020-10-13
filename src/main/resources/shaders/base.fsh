#version 400 core

in vec2 pass_textureCoords;
in vec3 surfaceNormal;
in vec3 toLightVector;

out vec4 out_Color;

uniform sampler2D textureSampler;
uniform vec4 passColor;

uniform bool isLit;
uniform bool isSolidColor;

void main() {
    vec3 unitNormal = normalize(surfaceNormal);
    vec3 unitLightVector = normalize(toLightVector);

    float nDot1 = dot(unitNormal, unitLightVector);
    float brightness = max(nDot1, 0.5);
    vec3 diffuse = brightness * vec3(1.0, 1.0, 1.0);

    vec4 textureColor = vec4(0, 0, 0, 0);

    if (isSolidColor) {
        textureColor = passColor;
    } else {
        textureColor = texture(textureSampler, pass_textureCoords);
    }

    if (isLit) {
        out_Color = vec4(diffuse, 1.0) * textureColor;
//        out_Color = vec4(1, 0, 1, 1);
    } else {
        out_Color = textureColor;
    }
//    out_Color = passColor;
}
