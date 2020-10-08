package net.nodium.games.paul;

import net.nodium.games.paul.entities.Entity;
import net.nodium.games.paul.entities.EntityGround;
import net.nodium.games.paul.entities.EntityLazor;
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
        game.camera.boundEntity.setPos(0, 2, 0);

        new EntityGround(this).setPos(0, 0, 0);

        new EntityOofCube(this).setPos(0, 2, 0);

//        for (int i = 0; i < 100; i++) {
//            new EntityOofCube(this)
//                    .setPos(
//                            (rand.nextFloat() - rand.nextFloat()) * 2.5f,
//                            rand.nextFloat() * 10000 + 10,
//                            (rand.nextFloat() - rand.nextFloat()) * 2.5f
//                    );
//        }
    }

    public void tick() {
        if (game.gameLoop.ticks % 240 == 0) {
            for (int i = 0; i < 1; i++) {
                new EntityOofCube(this)
                        .setPos(
                                (rand.nextFloat() - rand.nextFloat()) * 1,
                                rand.nextFloat() * 100 + 20,
                                (rand.nextFloat() - rand.nextFloat()) * 1
                        );
            }
        }

        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.tick();

            if (e.isDead()) {
                entities.remove(e);
            }
        }
    }

    public void playerDied() {
        game.guiManager.deathScreen.playerDied();
    }
}
