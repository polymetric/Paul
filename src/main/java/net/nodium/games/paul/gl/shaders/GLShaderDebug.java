package net.nodium.games.paul.gl.shaders;

import net.nodium.games.paul.entities.Camera;
import net.nodium.games.paul.math.MathUtils;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class GLShaderDebug extends GLShader {
    private static final String VSH_PATH = "/shaders/debug.vsh";
    private static final String FSH_PATH = "/shaders/debug.fsh";

    private int locTransMatrix;
    private int locProjMatrix;
    private int locViewMatrix;
    private int locColor;

    public GLShaderDebug() {
        super(VSH_PATH, FSH_PATH);
    }

    @Override
    protected void getAllUniformLocations() {
        locTransMatrix = super.getUniformLocation("transMatrix");
        locProjMatrix = super.getUniformLocation("projMatrix");
        locViewMatrix = super.getUniformLocation("viewMatrix");
        locColor = super.getUniformLocation("in_Color");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "pos");
    }

    public void loadTransMatrix(Matrix4f matrix) {
        super.loadMatrix4f(locTransMatrix, matrix);
    }

    public void loadProjMatrix(Matrix4f matrix) {
        super.loadMatrix4f(locProjMatrix, matrix);
    }

    public void loadViewMatrix(Camera camera) {
        Matrix4f matrix = MathUtils.createViewMatrix(camera);
        super.loadMatrix4f(locViewMatrix, matrix);
    }

    public void loadColor(Vector4f color) {
        super.loadVector4f(locColor, color);
    }
}
