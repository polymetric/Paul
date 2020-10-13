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
        game.camera.boundEntity.setPos(0, 2, 0);

        new EntityGround(this).setPos(0, 0, 0);

//        new EntityOofCube(this).setPos(0, 2, 0);
//        new EntityOofCube(this).setPos(10, 10, 10);

//        for (int i = 0; i < 1000; i++) {
//            new EntityOofCube(this)
//                    .setPos(
//                            (rand.nextFloat() - rand.nextFloat()) * 100f,
//                            (rand.nextFloat() - rand.nextFloat()) * 100f,
//                            (rand.nextFloat() - rand.nextFloat()) * 100f
//                    );
//        }
    }

    private float spawnInterval = 3f;
    private long lastSpawned = System.currentTimeMillis();

    private int totalSpawned = 0;

    public void tick() {
        updateKills(game.camera.boundEntity.getKills());

        if (game.camera.boundEntity.isDead()) {
            if (game.gameLoop.ticks % 1 == 0) {
                for (int i = 0; i < 100; i++) {
                    EntityOofCube oofCube = new EntityOofCube(this);
                    oofCube.setPos(
                            (rand.nextFloat() - rand.nextFloat()) * 100,
                            (rand.nextFloat() - rand.nextFloat()) * 50 + 20,
                            (rand.nextFloat() - rand.nextFloat()) * 100
                    );
                    oofCube.isBrring = true;
                    oofCube.brrPosAmount = 2f;
                    oofCube.brrRotAmount = 10;
                }
            }
        } else {
            if ((System.currentTimeMillis() - lastSpawned) / 1000f > spawnInterval) {
                totalSpawned++;
                lastSpawned = System.currentTimeMillis();
                System.out.println(spawnInterval);
                spawnInterval *= 0.99f;
                for (int i = 0; i < 1; i++) {
                    new EntityOofCube(this)
                            .setPos(
                                    (rand.nextFloat() - rand.nextFloat()) * 1,
                                    rand.nextFloat() * 100 + 20,
                                    (rand.nextFloat() - rand.nextFloat()) * 1
                            );
                }
            }
        }

        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.tick();

            if (e.isMarkedForCleanup()) {
                entities.remove(e);
            }
        }
    }

    public void playerDied() {
        game.display.enableCursor();
        game.guiManager.displayDeathScreen();
    }

    public void updateKills(int kills) {
        game.guiManager.updateKills(kills, totalSpawned);
    }

    public void updateHealth(int health) {
        game.guiManager.updateHealth(health);
    }
}
