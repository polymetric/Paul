package net.nodium.games.paul;

import net.nodium.games.paul.entities.Entity;
import net.nodium.games.paul.entities.EntityGround;
import net.nodium.games.paul.entities.EntityOofCube;

import java.util.ArrayList;
import java.util.Random;

public class EntityHandler {
    public Game game;
    private Random rand = new Random();

    public ArrayList<Entity> entities = new ArrayList<Entity>();

    public EntityHandler(Game game) {
        this.game = game;
    }

    public void init() {
        new EntityGround(this).setPos(0, 0, 0);

        new EntityOofCube(this).setPos(0, 20, 0);

//        for (int i = 0; i < 10000; i++) {
//            new EntityOofCube(this)
//                    .setPos(
//                            (rand.nextFloat() - rand.nextFloat()) * 1000,
//                            (rand.nextFloat() - rand.nextFloat()) * 1000,
//                            (rand.nextFloat() - rand.nextFloat()) * 1000
//                    );
//        }
    }

    public void tick() {
//        if (game.gameLoop.ticks % 1 == 0) {
//            for (int i = 0; i < 10; i++) {
//                new EntityOofCube(this)
//                        .setPos(
//                                (rand.nextFloat() - rand.nextFloat()) * 1000,
//                                (rand.nextFloat() - rand.nextFloat()) * 1000,
//                                (rand.nextFloat() - rand.nextFloat()) * 1000
//                        );
//            }
//        }

        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.tick();

            if (e.isDead()) {
                entities.remove(e);
            }
        }
    }

    public float temp() {
        Random rand = new Random();
        return rand.nextFloat() * 100F;
    }
}
