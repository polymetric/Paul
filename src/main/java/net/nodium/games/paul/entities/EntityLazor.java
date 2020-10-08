package net.nodium.games.paul.entities;

import net.nodium.games.paul.EntityHandler;
import net.nodium.games.paul.Launcher;
import net.nodium.games.paul.math.MathUtils;
import net.nodium.games.paul.phys.CardinalDirection;
import net.nodium.games.paul.phys.Hitbox;
import org.joml.Vector2f;

public class EntityLazor extends Entity {
    private Entity sender;

    private float lazorSpeed = 100F;
    private int age = 0;
    private float lifespan = 1;

    public EntityLazor(EntityHandler entityHandler, Camera camera, Entity sender) {
        super(entityHandler);

        this.sender = sender;

        hitbox = new Hitbox(pos, .1f, .1f, .1f);
        enableGravity = false;

        setPos(
                camera.pos.x + camera.offset.x,
                camera.pos.y + camera.offset.y,
                camera.pos.z + camera.offset.z
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
        posVel.add(camera.boundEntity.posVel);
    }

    @Override
    public void onCollide(Entity other) {
        if (other.equals(sender)) { return; }

        switch (MathUtils.getCardinalDir(other.getDirectionRelativeTo(this))) {

        }

        other.hurt(this, 10);
    }

    @Override
    public void tick() {
        age++;

//        Vector2f ang = MathUtils.getAngleBetweenPoints(sender.pos, this.pos);
//        System.out.printf("%38s %12.3f %12.3f\n", "", ang.x, ang.y);

//        System.out.println(age);
        if (age > lifespan / getLogicDelta()) {
            this.kill();
        }

        super.tick();
    }
}
