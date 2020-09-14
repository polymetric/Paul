#version 400 core

in vec2 pass_textureCoords;

out vec4 out_Color;

uniform sampler2D textureSampler;
uniform vec4 passColor;

void main() {
    out_Color = texture(textureSampler, pass_textureCoords);
//    out_Color = passColor;
}
