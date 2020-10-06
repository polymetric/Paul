package net.nodium.games.paul.gl.shaders;

import org.joml.Matrix4f;
import org.joml.Vector2f;

public class GLShaderGuiString extends GLShader {
    private static final String VERTEX_FILE = "/shaders/guistring.vsh";
    private static final String FRAGMENT_FILE = "/shaders/guistring.fsh";

    private int locTransMatrix;
    private int locTextureSize;
    private int locSpriteSize;
    private int locSpriteOffset;

    public GLShaderGuiString() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    public void loadTransMatrix(Matrix4f matrix) {
        super.loadMatrix4f(locTransMatrix, matrix);
    }

    public void loadTextureSize(float textureSize) {
        super.loadFloat(locTextureSize, textureSize);
    }

    public void loadSpriteSize(float spriteSize) {
        super.loadFloat(locSpriteSize, spriteSize);
    }

    public void loadSpriteOffset(Vector2f spriteOffset) {
        super.loadVector2f(locSpriteOffset, spriteOffset);
    }

    @Override
    protected void getAllUniformLocations() {
        locTransMatrix = super.getUniformLocation("transMatrix");
        locTextureSize = super.getUniformLocation("textureSize");
        locSpriteSize = super.getUniformLocation("spriteSize");
        locSpriteOffset = super.getUniformLocation("spriteOffset");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }
}
