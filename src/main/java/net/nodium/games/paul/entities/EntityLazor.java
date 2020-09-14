package net.nodium.games.paul.entities;

import net.nodium.games.paul.EntityHandler;

public class EntityLazor extends Entity {
    public EntityLazor(EntityHandler entityHandler) {
        super(entityHandler);
    }

    @Override
    public void init() {
        enableGravity = false;

    }
}
