package net.nodium.games.paul.entities;

import net.nodium.games.paul.EntityHandler;

import java.util.Random;

public class EntityOofCube extends Entity {
    public float brrAmount = 0.0F;
    private Random rand;

    public EntityOofCube(EntityHandler entityHandler) {
        super(entityHandler);
    }

    @Override
    public void init() {
//        enableGravity = false;

        rand = new Random();
        rotVel.y = (rand.nextFloat() - rand.nextFloat()) * 100;
        rotVel.x = (rand.nextFloat() - rand.nextFloat()) * 40;
        rotVel.z = (rand.nextFloat() - rand.nextFloat()) * 40;

        posVel.x = (rand.nextFloat() - rand.nextFloat()) * 10;
        posVel.y = (rand.nextFloat() - rand.nextFloat()) * 10;
        posVel.z = (rand.nextFloat() - rand.nextFloat()) * 10;
    }

    public void tick() {
        brrAmount += 0.01F * getLogicDelta();

        if (brrAmount >= 0.1F) {
            brrAmount = 0.1F;
        }

        super.tick();

        if (pos.y < -100) {
            this.setDead();
        }
    }
}
