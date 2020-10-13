package net.nodium.games.paul.entities;

import net.nodium.games.paul.EntityHandler;
import net.nodium.games.paul.phys.Hitbox;

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
//        enableGravity = false;

        deathAnimationLength = .05f;
//        deathAnimationLength = 1f;
        hitbox = new Hitbox(pos, 1, 1, 1);

//        rotVel.y = 90;

        rand = new Random();
        rotVel.y = (rand.nextFloat() - rand.nextFloat()) * 100;
        rotVel.x = (rand.nextFloat() - rand.nextFloat()) * 40;
        rotVel.z = (rand.nextFloat() - rand.nextFloat()) * 40;

        posVel.x = (rand.nextFloat() - rand.nextFloat()) * 5;
        posVel.y = (rand.nextFloat() - rand.nextFloat()) * 5;
        posVel.z = (rand.nextFloat() - rand.nextFloat()) * 5;
    }

    @Override
    public void tick() {
        if (this.isWithinRadiusOf(entityHandler.game.camera.boundEntity, 2)) {
//            isBrring = true;
        } else {
//            isBrring = false;
        }

        if (isBrring) {
            brrPosAmount += 0.1F * getLogicDelta();
            brrRotAmount += 0.1F * getLogicDelta();

//            if (brrPosAmount >= .3F) {
//                brrPosAmount = .3F;
//            }

           pos.x += (rand.nextFloat() - rand.nextFloat()) * brrPosAmount;
           pos.y += (rand.nextFloat() - rand.nextFloat()) * brrPosAmount;
           pos.z += (rand.nextFloat() - rand.nextFloat()) * brrPosAmount;

           rot.x += (rand.nextFloat() - rand.nextFloat()) * brrPosAmount;
           rot.y += (rand.nextFloat() - rand.nextFloat()) * brrPosAmount;
           rot.z += (rand.nextFloat() - rand.nextFloat()) * brrPosAmount;
        } else {
            brrPosAmount -= brrPosAmount / getLogicDelta();
            brrRotAmount -= brrRotAmount / getLogicDelta();
        }

        super.tick();
    }

    @Override
    public void onCollide(Entity other) {
        if (other instanceof EntityOofCube) { return; }
        if (isDead()) { return; }
        if (other instanceof EntityGround) {
            this.kill(other);
            ((EntityGround) other).hurt(this, 1);
        }
    }

    @Override
    public void playDeathAnimation() {
        scale = (float) (Math.pow(1 + timeDead * getLogicDelta(), 25));
    }
}
