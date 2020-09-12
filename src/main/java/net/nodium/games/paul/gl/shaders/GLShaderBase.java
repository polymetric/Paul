package net.nodium.games.paul.gl.shaders;

import org.joml.Matrix4f;

public class GLShaderBase extends GLShader {
    private static final String VSH_PATH = "src/main/java/net/nodium/games/paul/gl/shaders/base.vsh";
    private static final String FSH_PATH = "src/main/java/net/nodium/games/paul/gl/shaders/base.fsh";

    private int locTransMatrix;
    private int locProjMatrix;
    private int locColor;

    public GLShaderBase() {
        super(VSH_PATH, FSH_PATH);
    }

    @Override
    protected void getAllUniformLocations() {
        locTransMatrix = super.getUniformLocation("transMatrix");
        locProjMatrix = super.getUniformLocation("projMatrix");
        locColor = super.getUniformLocation("passColor");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "pos");
        super.bindAttribute(1, "textureCoords");
    }

    public void loadTransMatrix(Matrix4f matrix) {
        super.loadMatrix4f(locTransMatrix, matrix);
    }

    public void loadProjMatrix(Matrix4f matrix) {
        super.loadMatrix4f(locProjMatrix, matrix);
    }
}
