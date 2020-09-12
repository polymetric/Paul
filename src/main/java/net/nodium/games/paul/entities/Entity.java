package net.nodium.games.paul.entities;

import net.nodium.games.paul.gl.models.GLModel;
import net.nodium.games.paul.math.MathUtils;
import org.joml.Vector3f;

import java.util.Random;

public class Entity {
    public GLModel model;

    public Vector3f pos;
    public Vector3f posPrev;
    public Vector3f posVel = new Vector3f(0, 0, 0);

    public Vector3f rot;
    public Vector3f rotPrev;
    public Vector3f rotVel = new Vector3f(0, 0, 0);

    public float scale;

    public float brrAmount = 0.001F;

    public Entity(GLModel model, Vector3f pos, Vector3f rot, float scale) {
        this.model = model;
        this.pos = pos;
        this.rot = rot;
        this.scale = scale;

        posPrev = this.pos;
        rotPrev = this.rot;

        this.init();
    }

    public Entity(GLModel model, Vector3f pos) {
        this(model, pos, new Vector3f(1.0F, 0.0F, 0.0F), 1.0F);
    }

    private void init() {
//        posVel.x = 0.001f;
//        rotVel.y = 10f;
//        rotVel.x = 10f;
//        posVel.z = 0.01f;
    }

    public void tick() {
        brrAmount *= 1.1;

        pos.x += temp();
        pos.y += temp();

        if (brrAmount >= 0.1F) {
            brrAmount = 0.1F;
        }

//        tickGravity();
//        tickAirResistance();
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

    public void render() {
        model.setTransformationMatrix(MathUtils.createTransformationMatrix(pos, rot, scale));
        model.render();
    }

    private float temp() {
        Random rand = new Random();
        return (rand.nextFloat() * brrAmount - rand.nextFloat() * brrAmount);
    }
}
