package net.nodium.games.paul.gl.shaders;

import net.nodium.games.paul.entities.Camera;
import net.nodium.games.paul.math.MathUtils;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class GLShaderBase extends GLShader {
    private static final String VSH_PATH = "/shaders/base.vsh";
    private static final String FSH_PATH = "/shaders/base.fsh";

    private int locTransMatrix;
    private int locProjMatrix;
    private int locViewMatrix;
    private int locColor;
    private int locIsLit;
    private int locIsSolidColor;

    public GLShaderBase() {
        super(VSH_PATH, FSH_PATH);
    }

    @Override
    protected void getAllUniformLocations() {
        locTransMatrix = super.getUniformLocation("transMatrix");
        locProjMatrix = super.getUniformLocation("projMatrix");
        locViewMatrix = super.getUniformLocation("viewMatrix");
        locColor = super.getUniformLocation("passColor");
        locIsLit = super.getUniformLocation("isLit");
        locIsSolidColor = super.getUniformLocation("isSolidColor");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "pos");
        super.bindAttribute(1, "textureCoords");
        super.bindAttribute(2, "normal");
    }

    public void loadTransMatrix(Matrix4f matrix) {
        super.loadMatrix4f(locTransMatrix, matrix);
    }

    public void loadProjMatrix(Matrix4f matrix) {
        super.loadMatrix4f(locProjMatrix, matrix);
    }

    public void loadViewMatrix(Matrix4f matrix) { super.loadMatrix4f(locViewMatrix, matrix); }

    public void loadIsLit(boolean isLit) { super.loadBoolean(locIsLit, isLit); }

    public void loadIsSolidColor(boolean isSolidColor) { super.loadBoolean(locIsSolidColor, isSolidColor); }

    public void loadColor(Vector3f color) { super.loadVector3f(locColor, color); }
}
