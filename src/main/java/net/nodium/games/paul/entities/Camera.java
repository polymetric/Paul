package net.nodium.games.paul.entities;

import net.nodium.games.paul.Launcher;
import net.nodium.games.paul.input.MouseHandler;
import net.nodium.games.paul.math.MathUtils;
import org.joml.Vector3f;

import java.util.Vector;

public class Camera {
    private MouseHandler mouseHandler;

    public Vector3f pos;
    public float pitch;
    public float yaw;

    public Vector3f posPrev;
    public float pitchPrev;
    public float yawPrev;

    public Vector3f posVis;
    public float pitchVis;
    public float yawVis;

    public float speed = 0.06F;

    public boolean isMovingFwd = false;
    public boolean isMovingBwd = false;
    public boolean isMovingLeft = false;
    public boolean isMovingRight = false;
    public boolean isMovingUp = false;
    public boolean isMovingDown = false;

    public Camera(MouseHandler mouseHandler) {
        this(mouseHandler, 0, 0, 0);
    }

    public Camera(MouseHandler mouseHandler, float x, float y, float z) {
        this(mouseHandler, x, y, z, 0, 0);
    }

    public Camera(MouseHandler mouseHandler, float x, float y, float z, float pitch, float yaw) {
        this.mouseHandler = mouseHandler;

        pos = new Vector3f(x, y, z);
        this.pitch = pitch;
        this.yaw = yaw;

        posPrev = new Vector3f(x, y, z);
        pitchPrev = pitch;
        yawPrev = yaw;

        posVis = new Vector3f(0, 0, 0);
        pitchVis = 0;
        yawVis = 0;
    }

    public void tick() {
        pitchPrev = pitch;
        yawPrev = yaw;

        handleMouse();
        move();
    }

    public void render(float timeTillNextTick) {
        pitchVis = MathUtils.lerp(timeTillNextTick, 0, 1, pitchPrev, pitch);
        yawVis = MathUtils.lerp(timeTillNextTick, 0, 1, yawPrev, yaw);
    }

    private void handleMouse() {
        pitch += mouseHandler.deltaY * 0.15;
        yaw += mouseHandler.deltaX * 0.15;

        if (pitch > 90.0F) {
            pitch = 90.0F;
        }
        if (pitch < -90.0F) {
            pitch = -90.0F;
        }
    }

    private void move() {
        if (isMovingFwd) {
            moveHorizAngle(yaw);
        }
        if (isMovingBwd) {
            moveHorizAngle(yaw + 180);
        }
        if (isMovingRight) {
            moveHorizAngle(yaw + 90);
        }
        if (isMovingLeft) {
            moveHorizAngle(yaw - 90);
        }
        if (isMovingUp) {
            pos.y += speed;
        }
        if (isMovingDown) {
            pos.y -= speed;
        }
    }

    private void moveHorizAngle(double angle) {
        angle += -90;
        pos.x += speed * Math.cos(Math.toRadians(angle));
        pos.z += speed * Math.sin(Math.toRadians(angle));
    }
}
