package net.nodium.games.paul.entities;

import net.nodium.games.paul.EntityHandler;
import net.nodium.games.paul.phys.Hitbox;

public class EntityCamera extends Entity {
    private Camera camera;

    private boolean isFiringLazor = false;

    public EntityCamera(EntityHandler entityHandler, Camera camera) {
        super(entityHandler);
        this.camera = camera;
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
            new EntityLazor(entityHandler, camera, camera.boundEntity);
            isFiringLazor = false;
        }

        super.tick();
    }

    public void fireLazor() {
        isFiringLazor = true;
    }

    @Override
    public boolean isRenderable() {
        return false;
    }

    @Override
    public void kill() {
        respawn();
    }

    public void respawn() {
        this.setPos(0, 2, 0);
    }
}
