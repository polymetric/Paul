package net.nodium.games.paul.entities;

import net.nodium.games.paul.EntityHandler;
import net.nodium.games.paul.gl.models.GLModel;
import net.nodium.games.paul.math.MathUtils;
import org.joml.Vector3f;

import java.util.Random;

public class Entity {
    public Vector3f pos = new Vector3f();
    public Vector3f posPrev;
    public Vector3f posVel = new Vector3f(0, 0, 0);

    public Vector3f rot = new Vector3f();
    public Vector3f rotPrev;
    public Vector3f rotVel = new Vector3f(0, 0, 0);

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

        this.init();
    }

    private void init() {
    }

    public void tick() {
        tickGravity();
        tickAirResistance();
        tickVelocity();
    }

    private void tickVelocity() {
        pos.x += posVel.x;
        pos.y += posVel.y;
        pos.z += posVel.z;

        rot.x += rotVel.x;
        rot.y += rotVel.y;
        rot.z += rotVel.z;
    }

    private void tickGravity() {

    }

    private void tickAirResistance() {

    }
}
