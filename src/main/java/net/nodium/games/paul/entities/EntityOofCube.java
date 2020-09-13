package net.nodium.games.paul.entities;

import net.nodium.games.paul.EntityHandler;
import net.nodium.games.paul.entities.renderers.RenderOofCube;

import java.util.Random;

public class EntityOofCube extends Entity {
    public float brrAmount = 0.0F;
    private Random rand = new Random();

    public EntityOofCube(float x, float y, float z) {
        super(x, y, z);
    }

    public EntityOofCube(float x, float y, float z, float rotX, float rotY, float rotZ) {
        super(x, y, z, rotX, rotY, rotZ);
    }

    public void tick() {
        brrAmount += 0.01F;

        pos.x += temp();
        pos.y += temp();
        pos.z += temp();

        if (brrAmount >= 0.1F) {
            brrAmount = 0.1F;
        }

        super.tick();
    }

    private float temp() {
        return (rand.nextFloat() - rand.nextFloat()) * brrAmount;
    }
}
