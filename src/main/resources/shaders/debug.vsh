#version 460 core

in vec3 pos;

out vec4 pass_Color;

uniform mat4 transMatrix;
uniform mat4 projMatrix;
uniform mat4 viewMatrix;
uniform vec4 in_Color;

void main() {
    gl_Position = projMatrix * viewMatrix * transMatrix * vec4(pos, 1.0);
//    gl_Position = vec4(pos, 1.0);
    pass_Color = in_Color;
}
