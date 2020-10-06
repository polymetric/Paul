package net.nodium.games.paul.entities;

import net.nodium.games.paul.EntityHandler;

public class EntityGround extends Entity {
    public EntityGround(EntityHandler entityHandler) {
        super(entityHandler);

        this.enableGravity = false;
        this.enableAirResistance = false;
        this.enableVelocity = false;
    }
}
