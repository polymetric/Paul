package net.nodium.games.paul.entities;

import net.nodium.games.paul.Launcher;
import net.nodium.games.paul.input.MouseHandler;
import net.nodium.games.paul.sound.SoundListener;
import org.joml.Vector3f;

public class Camera {
    private MouseHandler mouseHandler;
    private SoundListener soundListener;
    private EntityCamera entityCamera;

    public Vector3f pos;
    public float pitch;
    public float yaw;

    public float cameraAccel = 100F;

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
//        this.soundListener = new SoundListener();

        this.entityCamera = new EntityCamera(x, y, z);
        this.pos = entityCamera.pos;

        this.pitch = pitch;
        this.yaw = yaw;
    }

    public void tick() {
        entityCamera.tick();
        move();
    }

    public void render() {
        handleMouse();
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
//            pos.y += speed;
            entityCamera.posVel.y += cameraAccel * getLogicDelta();
        }
        if (isMovingDown) {
//            pos.y -= speed;
            entityCamera.posVel.y -= cameraAccel * getLogicDelta();
        }
    }

    private void moveHorizAngle(double angle) {
        angle = angle - 90;
//        pos.x += speed * Math.cos(Math.toRadians(angle));
//        pos.z += speed * Math.sin(Math.toRadians(angle));
        entityCamera.posVel.x += cameraAccel * Math.cos(Math.toRadians(angle)) * getLogicDelta();
        entityCamera.posVel.z += cameraAccel * Math.sin(Math.toRadians(angle)) * getLogicDelta();
    }

    public double getLogicDelta() {
        return Launcher.game.gameLoop.getLogicDelta();
    }
}
