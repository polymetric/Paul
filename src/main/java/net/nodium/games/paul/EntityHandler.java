package net.nodium.games.paul;

import net.nodium.games.paul.entities.Entity;
import net.nodium.games.paul.entities.EntityOofCube;

import java.util.ArrayList;
import java.util.Random;

public class EntityHandler {
    public Game game;

    public ArrayList<Entity> entities = new ArrayList<Entity>();

    public EntityHandler(Game game) {
        this.game = game;
    }

    public void init() {
//        entities.add(new EntityOofCube(1, 0, 1));

//        for (int i = 0; i < 1000; i++) {
//            entities.add(new EntityOofCube(temp(), 0, temp()));
//        }
    }

    public void tick() {
        if (game.gameLoop.ticks % 1 == 0) {
            for (int i = 0; i < 2; i++) {
                new EntityOofCube(this);
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

    public float temp() {
        Random rand = new Random();
        return rand.nextFloat() * 100F;
    }
}
