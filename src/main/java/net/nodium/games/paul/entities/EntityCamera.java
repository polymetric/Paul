package net.nodium.games.paul.entities;

import net.nodium.games.paul.EntityHandler;

public class EntityCamera extends Entity {
    public boolean isFiringLazor = false;

    public EntityCamera(EntityHandler entityHandler) {
        super(entityHandler);
    }

    @Override
    public void init() {
        enableGravity = false;

        airResistanceDecel = 5F;
        speed = 30F;
    }

    @Override
    public void tick() {
        if (isFiringLazor) {

        }

        super.tick();
    }

    @Override
    public boolean isRenderable() {
        return false;
    }
}
