package net.nodium.games.paul.entities;

import net.nodium.games.paul.EntityHandler;
import net.nodium.games.paul.Launcher;
import net.nodium.games.paul.math.MathUtils;
import net.nodium.games.paul.phys.CardinalDirection;
import net.nodium.games.paul.phys.Hitbox;
import org.joml.Vector2f;
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
    public float frictionDecel = 5.00F;
    public float posVelMax = 1000F;
    public float rotVelMax = 360F;
    public float speed = 100F;

    public boolean enableGravity = true;
    public boolean enableVelocity = true;
    public boolean enableAirResistance = true;
    public boolean enableFriction = true;

    private boolean isDead = false;
    private boolean onGround = false;

    public Entity(EntityHandler entityHandler) {
        this.entityHandler = entityHandler;

        entityHandler.entities.add(this);

        this.init();
    }

    public void init() {}

    public void tick() {
        tickCollisions();

        tickVelocity();
        tickGravity();
        tickAirResistance();
        tickFriction();

        if (health <= 0) {
            kill();
        }

        if (pos.y < -100) {
            this.kill();
        }
    }

    private void tickVelocity() {
        if (!enableVelocity) { return; }

        // velocity limits
        posVel.x = Math.min(posVel.x, posVelMax);
        posVel.y = Math.min(posVel.y, posVelMax);
        posVel.z = Math.min(posVel.z, posVelMax);

        posVel.x = Math.max(posVel.x, -posVelMax);
        posVel.y = Math.max(posVel.y, -posVelMax);
        posVel.z = Math.max(posVel.z, -posVelMax);

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
        if (!enableAirResistance) { return; }

        double delta = Launcher.game.gameLoop.getLogicDelta();

        posVel.x -= posVel.x * airResistanceDecel * delta;
        posVel.y -= posVel.y * airResistanceDecel * delta;
        posVel.z -= posVel.z * airResistanceDecel * delta;
    }

    private void tickFriction() {
        if (!enableFriction) { return; }
        if (!onGround()) { return; }

        double delta = getLogicDelta();

        posVel.x -= posVel.x * frictionDecel * delta;
        posVel.y -= posVel.y * frictionDecel * delta;
        posVel.z -= posVel.z * frictionDecel * delta;
    }

    private void tickCollisions() {
        if (hitbox == null) { return; }
        hitbox.tick();
        boolean touchesGroundThisTick = false;

        for (Entity other : entityHandler.entities) {
            if (other.equals(this)) { continue; }
            if (hitbox == null | other.hitbox == null) { continue; }

//            if (other instanceof EntityGround) {
            if (other instanceof Entity) {
                if (hitbox.intersectsWith(other.hitbox)) {
//                    onCollide(other);

//                    this.posVel.x = 0;
//                    this.posVel.y = 0;
//                    this.posVel.z = 0;
                    touchesGroundThisTick = true;
                    onGround = true;
                }
            }
        }

        if (!touchesGroundThisTick) { onGround = false; }
    }

    public void onCollide(Entity other) {
        if (this instanceof EntityGround) return;
        if (other instanceof EntityGround) posVel.y = Math.min(posVel.y, 0);

//        CardinalDirection dirFrom = MathUtils.getCardinalDir(MathUtils.getAngleBetweenPoints(this.pos, other.pos));
        CardinalDirection dirFrom = hitbox.intersectDir(other.hitbox);

        switch (dirFrom) {
            case NEG_Z: posVel.z = Math.max(posVel.z, 0); break;
            case POS_X: posVel.x = Math.min(posVel.x, 0); break;
            case POS_Z: posVel.z = Math.min(posVel.z, 0); break;
            case NEG_X: posVel.x = Math.max(posVel.x, 0); break;
            case POS_Y: posVel.y = Math.min(posVel.y, 0); break;
            case NEG_Y: posVel.y = Math.max(posVel.y, 0); break;
        }

        if (this instanceof EntityCamera) {
            System.out.printf("player collided with %s from %s\n", other, dirFrom);
        }
    }

    public Vector2f getDirectionRelativeTo(Entity other) {
        return MathUtils.getAngleBetweenPoints(this.pos, other.pos);
    }

    public void moveHorizAngle(double angle) {
        angle = angle - 90;
        posVel.x += speed * Math.cos(Math.toRadians(angle)) * getLogicDelta();
        posVel.z += speed * Math.sin(Math.toRadians(angle)) * getLogicDelta();
    }

    public void jump() {
        if (onGround()) {
            this.posVel.y += 5;
        }
    }

    // setters

    public void kill() {
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

    public boolean onGround() { return onGround; }

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
