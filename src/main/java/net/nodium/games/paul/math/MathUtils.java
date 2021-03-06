package net.nodium.games.paul.math;

import net.nodium.games.paul.Launcher;
import net.nodium.games.paul.entities.Camera;
import net.nodium.games.paul.gl.Display;
import net.nodium.games.paul.gl.textures.Texture;
import net.nodium.games.paul.phys.CardinalDirection;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.system.CallbackI;

import java.util.Vector;

public class MathUtils {
    public static Matrix4f createTransformationMatrix(Vector3f pos, Vector3f rot, float scale) {
        Matrix4f matrix = new Matrix4f();

        matrix.translate(pos);
        matrix.rotate((float) Math.toRadians(rot.y), 0, 1, 0);
        matrix.rotate((float) Math.toRadians(rot.x), 1, 0, 0);
        matrix.rotate((float) Math.toRadians(rot.z), 0, 0, 1);
        matrix.scale(scale);

        return matrix;
    }

    public static Matrix4f createGuiTransformationMatrix(Display display, Texture texture, Vector2f pos, float scale) {
        Matrix4f matrix = new Matrix4f();

        matrix.translate(new Vector3f(pos.x, pos.y, 0));

//        matrix.scale(new Vector3f(texture.getWidth() / (float) display.getWidth(), texture.getHeight() / (float) display.getHeight(), 1));
        matrix.scale(new Vector3f(scale, scale, 1));

        return matrix;
    }

    public static Matrix4f createStringTransformationMatrix(Display display, Texture texture, float charSize, Vector2f pos, float scale) {
        Matrix4f matrix = new Matrix4f();

        matrix.translate(new Vector3f(pos.x, pos.y, 0));

//        matrix.scale(new Vector3f(texture.getWidth() / (float) display.getWidth(), texture.getHeight() / (float) display.getHeight(), 1));
        matrix.scale(new Vector3f(1f/display.getWidth(), 1f/display.getHeight(), 1));
        matrix.scale(new Vector3f(texture.getWidth(), texture.getHeight(), 1));
        matrix.scale(new Vector3f(scale, scale, 1));

        return matrix;
    }

    public static Matrix4f createViewMatrix(Camera camera) {
        Matrix4f viewMatrix = new Matrix4f();

        viewMatrix.rotate((float) Math.toRadians(camera.roll), 0, 0, 1);
        viewMatrix.rotate((float) Math.toRadians(camera.pitch), 1, 0, 0);
        viewMatrix.rotate((float) Math.toRadians(camera.yaw), 0, 1, 0);
        viewMatrix.translate(new Vector3f(-camera.pos.x, -camera.pos.y, -camera.pos.z));
        viewMatrix.translate(new Vector3f(-camera.offset.x, -camera.offset.y, -camera.offset.z));

        return viewMatrix;
    }

    public static Matrix4f createViewMatrixNoPos(Camera camera) {
        Matrix4f viewMatrix = new Matrix4f();

        viewMatrix.rotate((float) Math.toRadians(camera.roll), 0, 0, 1);
        viewMatrix.rotate((float) Math.toRadians(camera.pitch), 1, 0, 0);
        viewMatrix.rotate((float) Math.toRadians(camera.yaw), 0, 1, 0);
//        viewMatrix.translate(new Vector3f(-camera.pos.x, -camera.pos.y, -camera.pos.z));
//        viewMatrix.translate(new Vector3f(-camera.offset.x, -camera.offset.y, -camera.offset.z));

        return viewMatrix;
    }

    public static Matrix4f createProjMatrix(Display display, float fov, float nearPlane, float farPlane) {
        Matrix4f projMatrix = new Matrix4f();

        float aspectRatio = (float) display.getWidth() / (float) display.getHeight();
        float yScale = (float) ((1f / Math.tan(Math.toRadians(fov / 2f))) * aspectRatio);
        float xScale = yScale / aspectRatio;
        float frustum_length = farPlane - nearPlane;

        projMatrix.m00(xScale);
        projMatrix.m11(yScale);
        projMatrix.m22(-((farPlane + nearPlane) / frustum_length));
        projMatrix.m23(-1);
        projMatrix.m32(-((2 * nearPlane * farPlane) / frustum_length));
        projMatrix.m33(0);

        return projMatrix;
    }

    public static float lerp(float x, float x0, float x1, float y0, float y1) {
        return y0 + (x - x0) * ((y1 - y0) / (x1 - x0));
    }

    public static float xyzDistance(Vector3f a, Vector3f b) {
        return (float) Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2) + Math.pow(b.z - a.z, 2));
    }

    // returns an alpha and beta angle between two points in 3d space
    public static Vector2f getAngleBetweenPoints(Vector3f a, Vector3f b) {
        return new Vector2f(
                (float) Math.toDegrees(Math.atan2(a.z - b.z, a.y - b.y)),
                (float) Math.toDegrees(Math.atan2(a.x - b.x, a.z - b.z))
        );
    }

    // this returns the facing direction, including the possibility of up and down
    public static CardinalDirection getCardinalDir(Vector2f dir) {
        if (dir.x < -45) { return CardinalDirection.POS_Y; }
        if (dir.x > 45) { return CardinalDirection.NEG_Y; }

        return getCardinalYaw(dir.y);
    }

    // this returns the facing direction of just a yaw angle
    public static CardinalDirection getCardinalYaw(float yaw) {
        switch (quantizeAngle(yaw))  {
            case 0: return CardinalDirection.NEG_Z;
            case 1: return CardinalDirection.POS_X;
            case 2: return CardinalDirection.POS_Z;
            case 3: return CardinalDirection.NEG_X;
            default: throw new AssertionError();
        }
    }

    public static int quantizeAngle(float ang) {
        return (int) (normalizeAngle(ang + 45) + 180) / 90;
    }

    static float normalizeAngle(float yaw) {
        yaw = ((yaw + 180) % 360) - 180;
        if (yaw < -180) yaw += 360;
        return yaw;
    }

    public static Vector3f vecFromAngle(Vector2f ang) {
        return new Vector3f(
                (float) (Math.cos(Math.toRadians(ang.y - 90)) * Math.cos(Math.toRadians(ang.x))),
                (float) (Math.sin(Math.toRadians(ang.x - 180))),
                (float) (Math.sin(Math.toRadians(ang.x - 90)) * Math.cos(Math.toRadians(ang.x)))
        );
    }
}
