package net.nodium.games.paul.entities;

import net.nodium.games.paul.EntityHandler;
import net.nodium.games.paul.Launcher;

public class EntityLazor extends Entity {
    private float lazorSpeed = 100F;
    private int age = 0;

    public EntityLazor(EntityHandler entityHandler, Camera camera) {
        super(entityHandler);

        enableGravity = false;

        setPos(
                camera.pos.x,
                camera.pos.y,
                camera.pos.z
        );

        setRot(
                -camera.pitch,
                -camera.yaw,
                0
        );
        setPosVel(
                lazorSpeed * (float) (Math.cos(Math.toRadians(camera.yaw - 90)) * Math.cos(Math.toRadians(camera.pitch))),
                lazorSpeed * (float) (Math.sin(Math.toRadians(camera.pitch - 180))),
                lazorSpeed * (float) (Math.sin(Math.toRadians(camera.yaw - 90)) * Math.cos(Math.toRadians(camera.pitch)))
        );
    }

    @Override
    public void tick() {
        age++;

        if (age > .5 / getLogicDelta()) {
            System.out.printf("deleted lazor after %d ticks\n", age);
            this.kill();
        }

        super.tick();
    }
}
