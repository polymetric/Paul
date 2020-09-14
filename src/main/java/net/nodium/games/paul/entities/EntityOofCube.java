package net.nodium.games.paul.entities;

import net.nodium.games.paul.EntityHandler;

import java.util.Random;

public class EntityOofCube extends Entity {
    private Random rand;

    public float brrPosAmount = 0.0F;
    public float brrRotAmount = 0.0F;

    public boolean 

    public EntityOofCube(EntityHandler entityHandler) {
        super(entityHandler);
    }

    @Override
    public void init() {
        enableGravity = false;

        rand = new Random();
//        rotVel.y = (rand.nextFloat() - rand.nextFloat()) * 100;
//        rotVel.x = (rand.nextFloat() - rand.nextFloat()) * 40;
//        rotVel.z = (rand.nextFloat() - rand.nextFloat()) * 40;

//        posVel.x = (rand.nextFloat() - rand.nextFloat()) * 10;
//        posVel.y = (rand.nextFloat() - rand.nextFloat()) * 10;
//        posVel.z = (rand.nextFloat() - rand.nextFloat()) * 10;
    }

    @Override
    public void tick() {
        brrPosAmount += 0.0001F * getLogicDelta();
        brrRotAmount += 0.0001F * getLogicDelta();

        if (brrPosAmount >= 0.1F) {
            brrPosAmount = 0.1F;
        }

//        pos.x += rand.nextFloat() * brrAmount - rand.nextFloat() * brrAmount;
//        pos.y += rand.nextFloat() * brrAmount - rand.nextFloat() * brrAmount;
//        pos.z += rand.nextFloat() * brrAmount - rand.nextFloat() * brrAmount;

        rot.x += rand.nextFloat() * brrRotAmount - rand.nextFloat() * brrRotAmount;
        rot.y += rand.nextFloat() * brrRotAmount - rand.nextFloat() * brrRotAmount;
        rot.z += rand.nextFloat() * brrRotAmount - rand.nextFloat() * brrRotAmount;

        super.tick();

        if (pos.y < -100) {
            this.setDead();
        }
    }
}
