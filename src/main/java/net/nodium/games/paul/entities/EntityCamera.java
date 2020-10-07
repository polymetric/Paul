package net.nodium.games.paul.entities;

import net.nodium.games.paul.EntityHandler;
import net.nodium.games.paul.phys.Hitbox;

public class EntityCamera extends Entity {
    public boolean isFiringLazor = false;

    public EntityCamera(EntityHandler entityHandler) {
        super(entityHandler);
    }

    @Override
    public void init() {
//        enableGravity = false;
        hitbox = new Hitbox(pos, 1, 2, 1);

//        airResistanceDecel = 5F;
        speed = 30F;
    }

    @Override
    public void tick() {
        if (isFiringLazor) {
            isFiringLazor = false;
            System.out.println("lazor");
        }

        super.tick();
    }

    @Override
    public boolean isRenderable() {
        return false;
    }
}
