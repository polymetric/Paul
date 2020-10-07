package net.nodium.games.paul.entities;

import net.nodium.games.paul.EntityHandler;
import net.nodium.games.paul.Launcher;
import net.nodium.games.paul.math.MathUtils;
import net.nodium.games.paul.phys.Hitbox;
import org.joml.Vector3f;

public abstract class Entity {
    // positions are in meters, velocities are in m/s, rotation velocities are in degrees/s
    // velocities are tickrate agnostic
    public EntityHandler entityHandler;

    public Vector3f pos = new Vector3f(0, 0, 0);
    public Vector3f posPrev = pos;
    public Vector3f posVel = new Vector3f(0, 0, 0);
    public Vector3f posVis = pos;

    public Vector3f rot = new Vector3f(0, 0, 0);
    public Vector3f rotPrev = rot;
    public Vector3f rotVel = new Vector3f(0, 0, 0);
    public Vector3f rotVis = rot;

    public Hitbox hitbox;

    public int health = 1;

    public float gravityAccel = 9.8F;
    public float airResistanceDecel = 0.00F;
    public float posVelMax = 1000F;
    public float rotVelMax = 360F;
    public float speed = 100F;

    public boolean enableGravity = true;
    public boolean enableVelocity = true;
    public boolean enableAirResistance = true;

    private boolean isDead = false;

    public Entity(EntityHandler entityHandler) {
        this.entityHandler = entityHandler;

        entityHandler.entities.add(this);

        this.init();
    }

    public void init() {}

    public void tick() {
        tickVelocity();
        tickGravity();
        tickAirResistance();
        tickCollisions();

        if (health <= 0) {
            setDead();
        }
    }

    private void tickVelocity() {
        if (!enableVelocity) return;

        // velocity limits
        posVel.x = posVel.x > posVelMax ? posVelMax : posVel.x;
        posVel.y = posVel.y > posVelMax ? posVelMax : posVel.y;
        posVel.z = posVel.z > posVelMax ? posVelMax : posVel.z;

        posVel.x = posVel.x < -posVelMax ? -posVelMax : posVel.x;
        posVel.y = posVel.y < -posVelMax ? -posVelMax : posVel.y;
        posVel.z = posVel.z < -posVelMax ? -posVelMax : posVel.z;

        // add velocity to position
        // this is tickrate agnostic, which means the velocity
        // is specified in units per second
        pos.x += posVel.x * getLogicDelta();
        pos.y += posVel.y * getLogicDelta();
        pos.z += posVel.z * getLogicDelta();

        rot.x += rotVel.x * getLogicDelta();
        rot.y += rotVel.y * getLogicDelta();
        rot.z += rotVel.z * getLogicDelta();
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

    private void tickCollisions() {
        for (Entity other : entityHandler.entities) {
            if (other.equals(this)) continue;
            if (this.hitbox == null | other.hitbox == null) continue;

            if (this.hitbox.intersectsWith(other.hitbox).onY) {
                this.posVel.y = 0;
                break;
            }
        }
    }

    public void moveHorizAngle(double angle) {
        angle = angle - 90;
        posVel.x += speed * Math.cos(Math.toRadians(angle)) * getLogicDelta();
        posVel.z += speed * Math.sin(Math.toRadians(angle)) * getLogicDelta();
    }

    // setters

    public void setDead() {
        isDead = true;
    }

    public Entity setPos(float x, float y, float z) {
        this.pos.x = x;
        this.pos.y = y;
        this.pos.z = z;

        return this;
    }

    public Entity setRot(float x, float y, float z) {
        this.rot.x = x;
        this.rot.y = y;
        this.rot.z = z;

        return this;
    }

    public Entity setPosVel(float x, float y, float z) {
        this.posVel.x = x;
        this.posVel.y = y;
        this.posVel.z = z;

        return this;
    }

    public Entity setRotVel(float x, float y, float z) {
        this.rotVel.x = x;
        this.rotVel.y = y;
        this.rotVel.z = z;

        return this;
    }

    // getters

    public boolean isDead() {
        return isDead;
    }

    public double getLogicDelta() {
        return entityHandler.game.gameLoop.getLogicDelta();
    }

    public boolean isRenderable() {
        return true;
    }

    public boolean isWithinRadiusOf(Entity entity, float radius) {
//        System.out.println(MathUtils.xyzDistance(this.pos, entity.pos));
//        System.out.println(radius);
//        System.out.println(MathUtils.xyzDistance(this.pos, entity.pos) < radius);
        return MathUtils.xyzDistance(this.pos, entity.pos) < radius;
    }
}
