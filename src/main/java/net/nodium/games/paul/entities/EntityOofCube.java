package net.nodium.games.paul.entities;

import net.nodium.games.paul.EntityHandler;
import net.nodium.games.paul.entities.renderers.RenderOofCube;

import java.util.Random;

public class EntityOofCube extends Entity {
    public float brrAmount = 0.0F;
    private Random rand;

    public EntityOofCube(float x, float y, float z) {
        super(x, y, z);
    }

    public EntityOofCube(float x, float y, float z, float rotX, float rotY, float rotZ) {
        super(x, y, z, rotX, rotY, rotZ);
    }

    @Override
    public void init() {
//        enableGravity = false;

        rand = new Random();
        rotVel.y = temp() * 100;
        rotVel.x = temp() * 40;
        rotVel.z = temp() * 40;

        posVel.x = temp() * 100;
        posVel.y = temp() * 100;
        posVel.z = temp() * 100;
    }

    public void tick() {
        brrAmount += 0.01F * getDeltaTime();

        if (brrAmount >= 0.1F) {
            brrAmount = 0.1F;
        }

        super.tick();

        if (pos.y < -100) {
//            this.setDead();
        }
    }

    private float temp() {
        return (rand.nextFloat() - .5F) * 2F;
    }
}
