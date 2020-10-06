package net.nodium.games.paul.entities;

import net.nodium.games.paul.EntityHandler;
import net.nodium.games.paul.input.MouseHandler;
import net.nodium.games.paul.sound.SoundListener;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {
    private MouseHandler mouseHandler;
    private SoundListener soundListener;
    public EntityCamera entityCamera;

    public Vector3f pos;
    public float pitch = 0;
    public float yaw = 0;
    public float roll = 0;

    public float pitchVel = 0;
    public float yawVel = 0;
    public float rollVel = 0;

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

//        this.yawVel = 1.5f;
//        this.pitchVel = 1.5f;
//        this.rollVel = 1.5f;

//        entityCamera.enableAirResistance = false;
//        entityCamera.setPosVel(5, 0, 0);
    }

    public void tick() {
        entityCamera.tick();
        tickVelocity();
        handleKeyMove();
    }

    public void tickVelocity() {
        this.pitch += pitchVel * entityCamera.getLogicDelta();
        this.yaw += yawVel * entityCamera.getLogicDelta();
        this.roll += rollVel * entityCamera.getLogicDelta();
    }

    public void render() {
        handleMouseLook();
    }

    private void handleMouseLook() {
        pitch += mouseHandler.deltaY * 0.15;
        yaw += mouseHandler.deltaX * 0.15;

        if (pitch > 90.0F) {
            pitch = 90.0F;
        }
        if (pitch < -90.0F) {
            pitch = -90.0F;
        }
    }

    private void handleKeyMove() {
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
            entityCamera.posVel.y += entityCamera.speed * entityCamera.getLogicDelta();
        }
        if (isMovingDown) {
            entityCamera.posVel.y -= entityCamera.speed * entityCamera.getLogicDelta();
        }
    }
}
