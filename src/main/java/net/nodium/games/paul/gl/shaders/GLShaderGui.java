package net.nodium.games.paul.gl.shaders;

import org.joml.Matrix4f;

public class GLShaderGui extends GLShader {
    private static final String VERTEX_FILE = "/shaders/gui.vsh";
    private static final String FRAGMENT_FILE = "/shaders/gui.fsh";

    private int locTransMatrix;

    public GLShaderGui() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    public void loadTransMatrix(Matrix4f matrix) {
        super.loadMatrix4f(locTransMatrix, matrix);
    }

    @Override
    protected void getAllUniformLocations() {
        locTransMatrix = super.getUniformLocation("transMatrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }
}
