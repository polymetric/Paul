package net.nodium.games.paul.entities;

import net.nodium.games.paul.EntityHandler;
import net.nodium.games.paul.input.MouseHandler;
import net.nodium.games.paul.math.MathUtils;
import net.nodium.games.paul.sound.SoundListener;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {
    private MouseHandler mouseHandler;
    private SoundListener soundListener;
    // TODO change this to entity so it can be bound to any entity
    public EntityCamera boundEntity;

    public Vector3f pos;
    public Vector3f offset = new Vector3f(0, 1, 0);
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

        this.boundEntity = new EntityCamera(entityHandler, this);
        this.pos = boundEntity.pos;

//        this.yawVel = 1.5f;
//        this.pitchVel = 1.5f;
//        this.rollVel = 1.5f;

//        entityCamera.enableAirResistance = false;
//        entityCamera.setPosVel(5, 0, 0);
    }

    public void tick() {
        handleKeyMove();
//        tickVelocity();
    }

    public void tickVelocity() {
        this.pitch += pitchVel * boundEntity.getLogicDelta();
        this.yaw += yawVel * boundEntity.getLogicDelta();
        this.roll += rollVel * boundEntity.getLogicDelta();
    }

    public void render() {
        handleMouseLook();
    }

    private void handleMouseLook() {
        // TODO bind rotation to boundEntity
        System.out.printf("%12.0f %12.0f %12.0f %12.3f %12.3f %s\n", pos.x, pos.y, pos.z, pitch, yaw, MathUtils.getCardinalDir(new Vector2f(pitch, yaw)));

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
        if (!boundEntity.onGround()) return;
        if (isMovingFwd) {
            boundEntity.moveHorizAngle(yaw);
        }
        if (isMovingBwd) {
            boundEntity.moveHorizAngle(yaw + 180);
        }
        if (isMovingRight) {
            boundEntity.moveHorizAngle(yaw + 90);
        }
        if (isMovingLeft) {
            boundEntity.moveHorizAngle(yaw - 90);
        }
//        if (isMovingUp) {
//            entityCamera.posVel.y += entityCamera.speed * entityCamera.getLogicDelta();
//        }
//        if (isMovingDown) {
//            entityCamera.posVel.y -= entityCamera.speed * entityCamera.getLogicDelta();
//        }
    }

    // TODO make this able to bind to any entity
    public void bindToEntity(EntityCamera entity) {
        boundEntity = entity;
    }
}
