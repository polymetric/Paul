package net.nodium.games.paul;

import net.nodium.games.paul.entities.Entity;
import net.nodium.games.paul.entities.EntityOofCube;

import java.util.ArrayList;

public class EntityHandler {
    private Game game;

    public ArrayList<Entity> entities = new ArrayList<Entity>();

    public void init() {
       entities.add(new EntityOofCube(0, 0, 1));
    }

    public void tick() {
        for (Entity e : entities) {
            e.tick();
        }
    }
}
