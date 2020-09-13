package net.nodium.games.paul.math;

import net.nodium.games.paul.entities.Camera;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class MathUtils {
    public static Matrix4f createTransformationMatrix(Vector3f translation, Vector3f rotation, float scale) {
        Matrix4f matrix = new Matrix4f();
        matrix.translate(translation);
        matrix.rotate((float) Math.toRadians(rotation.x), new Vector3f(1, 0, 0));
        matrix.rotate((float) Math.toRadians(rotation.y), new Vector3f(0, 1, 0));
        matrix.rotate((float) Math.toRadians(rotation.z), new Vector3f(0, 0, 1));
        matrix.scale(scale);

        return matrix;
    }

    public static Matrix4f createViewMatrix(Camera camera) {
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.rotate((float) Math.toRadians(camera.pitch), new Vector3f(1, 0, 0));
        viewMatrix.rotate((float) Math.toRadians(camera.yaw), new Vector3f(0, 1, 0));
        viewMatrix.translate(new Vector3f(-camera.pos.x, -camera.pos.y, -camera.pos.z));
        return viewMatrix;
    }

    public static float lerp(float x, float x0, float x1, float y0, float y1) {
        return y0 + (x - x0) * ((y1 - y0) / (x1 - x0));
    }
}
