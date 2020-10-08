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

        hitbox = new Hitbox(pos, 10, 1e-3f, 10);
    }

    @Override
    public void onCollide(Entity other) {
        other.posVel.y = 0;
    }
}
