package net.nodium.games.paul.entities;

import net.nodium.games.paul.EntityHandler;
import net.nodium.games.paul.Launcher;
import net.nodium.games.paul.input.MouseHandler;
import net.nodium.games.paul.sound.SoundListener;
import org.joml.Vector3f;

public class Camera {
    private MouseHandler mouseHandler;
    private SoundListener soundListener;
    public EntityCamera entityCamera;

    public Vector3f pos;
    public float pitch = 0;
    public float yaw = 0;

    public boolean isMovingFwd = false;
    public boolean isMovingBwd = false;
    public boolean isMovingLeft = false;
    public boolean isMovingRight = false;
    public boolean isMovingUp = false;
    public boolean isMovingDown = false;

    public Camera(EntityHandler entityHandler, MouseHandler mouseHandler) {
        this.mouseHandler = mouseHandler;
//        this.soundListener = new SoundListener();

        this.entityCamera = new EntityCamera(entityHandler);
        this.pos = entityCamera.pos;
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
            entityCamera.moveHorizAngle(yaw);
        }
        if (isMovingBwd) {
            entityCamera.moveHorizAngle(yaw + 180);
        }
        if (isMovingRight) {
            entityCamera.moveHorizAngle(yaw + 90);
        }
        if (isMovingLeft) {
            entityCamera.moveHorizAngle(yaw - 90);
        }
        if (isMovingUp) {
            entityCamera.posVel.y += entityCamera.accel * entityCamera.getLogicDelta();
        }
        if (isMovingDown) {
            entityCamera.posVel.y -= entityCamera.accel * entityCamera.getLogicDelta();
        }
    }
}
