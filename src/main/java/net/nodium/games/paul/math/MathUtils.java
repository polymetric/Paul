package net.nodium.games.paul.math;

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
}
