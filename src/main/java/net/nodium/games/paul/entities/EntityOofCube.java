package net.nodium.games.paul.entities;

import net.nodium.games.paul.EntityHandler;
import net.nodium.games.paul.Launcher;

import java.util.Random;

public class EntityOofCube extends Entity {
    private Random rand;

    public float brrPosAmount = 0.0F;
    public float brrRotAmount = 0.0F;

    public boolean isBrring = false;

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
//
//        posVel.x = (rand.nextFloat() - rand.nextFloat()) * 5;
//        posVel.y = (rand.nextFloat() - rand.nextFloat()) * 5;
//        posVel.z = (rand.nextFloat() - rand.nextFloat()) * 5;
    }

    @Override
    public void tick() {
        if (this.isWithinRadiusOf(entityHandler.game.camera.entityCamera, 4)) {
//            isBrring = true;
        } else {
            isBrring = false;
        }

//        if (isBrring) {
//            brrPosAmount += 0.1F * getLogicDelta();
//            brrRotAmount += 0.1F * getLogicDelta();
//
//            if (brrPosAmount >= 0.1F) {
//                brrPosAmount = 0.1F;
//            }
//
//           posVel.x += rand.nextFloat() * brrPosAmount - rand.nextFloat() * brrPosAmount;
//           posVel.y += rand.nextFloat() * brrPosAmount - rand.nextFloat() * brrPosAmount;
//           posVel.z += rand.nextFloat() * brrPosAmount - rand.nextFloat() * brrPosAmount;
//
//            rot.x += rand.nextFloat() * brrRotAmount - rand.nextFloat() * brrRotAmount;
//            rot.y += rand.nextFloat() * brrRotAmount - rand.nextFloat() * brrRotAmount;
//            rot.z += rand.nextFloat() * brrRotAmount - rand.nextFloat() * brrRotAmount;
//        } else {
//            brrPosAmount -= brrPosAmount / getLogicDelta();
//            brrRotAmount -= brrRotAmount / getLogicDelta();
//        }

        super.tick();

        if (pos.y < -100) {
//            this.setDead();
        }
    }
}
