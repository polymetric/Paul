package net.nodium.games.paul.entities;

import net.nodium.games.paul.EntityHandler;
import net.nodium.games.paul.phys.Hitbox;

public class EntityGround extends Entity {
    public EntityGround(EntityHandler entityHandler) {
        super(entityHandler);
    }

    public void init() {
        enableGravity = false;
        enableAirResistance = false;
        enableVelocity = false;

        health = 100;

//        invulnerable = true;

        hitbox = new Hitbox(pos, 40, 2, 40).setOffset(0, -1, 0);
    }

    @Override
    public void tick() {
        entityHandler.updateHealth(health);
        super.tick();
    }

    @Override
    public void onCollide(Entity other) {
        other.posVel.y = Math.max(other.posVel.y, 0);
    }
}
