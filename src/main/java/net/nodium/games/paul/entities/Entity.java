package net.nodium.games.paul.entities;

import net.nodium.games.paul.Launcher;
import org.joml.Vector3f;

public class Entity {
    // positions are in meters, velocities are in m/s, rotation velocities are in degrees/s
    // velocities are tickrate agnostic

    public Vector3f pos = new Vector3f();
    public Vector3f posPrev;
    public Vector3f posVel = new Vector3f(0, 0, 0);
    public Vector3f posVis;

    public Vector3f rot = new Vector3f();
    public Vector3f rotPrev;
    public Vector3f rotVel = new Vector3f(0, 0, 0);
    public Vector3f rotVis;

    public float gravityAccel = 9.8F;
    public float airResistanceDecel = 0.80F;
    public float posVelMax = 10F;
    public float rotVelMax = 360F;

    public boolean enableGravity = true;
    public boolean enableVelocity = true;
    public boolean enableAirResistance = true;

    private boolean isDead = false;

    public Entity(float x, float y, float z) {
        this(x, y, z, 0.0F, 0.0F, 0.0F);
    }

    public Entity(float x, float y, float z, float rotX, float rotY, float rotZ) {
        pos.x = x;
        pos.y = y;
        pos.z = z;
        rot.x = rotX;
        rot.y = rotY;
        rot.z = rotZ;

        posPrev = this.pos;
        rotPrev = this.rot;

        posVis = this.pos;
        rotVis = this.rot;

        this.init();
    }

    public void init() {}

    public void tick() {
        tickVelocity();
        tickGravity();
        tickAirResistance();
    }

    private void tickVelocity() {
        if (!enableVelocity) return;

        posVel.x = posVel.x > posVelMax ? posVelMax : posVel.x;
        posVel.y = posVel.y > posVelMax ? posVelMax : posVel.y;
        posVel.z = posVel.z > posVelMax ? posVelMax : posVel.z;

        pos.x += posVel.x * getDeltaTime();
        pos.y += posVel.y * getDeltaTime();
        pos.z += posVel.z * getDeltaTime();

        rot.x += rotVel.x * getDeltaTime();
        rot.y += rotVel.y * getDeltaTime();
        rot.z += rotVel.z * getDeltaTime();
    }

    private void tickGravity() {
        if (!enableGravity) return;

        posVel.y -= gravityAccel / Launcher.game.gameLoop.getTps();
    }

    private void tickAirResistance() {
        if (!enableAirResistance) return;

        double delta = Launcher.game.gameLoop.getLogicDelta();

        posVel.x -= posVel.x * airResistanceDecel * delta;
        posVel.y -= posVel.y * airResistanceDecel * delta;
        posVel.z -= posVel.z * airResistanceDecel * delta;
    }

    public void setDead() {
        isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }

    public double getDeltaTime() {
        return Launcher.game.gameLoop.getLogicDelta();
    }
}
