package net.nodium.games.paul;

import net.nodium.games.paul.entities.Entity;

import java.util.ArrayList;

public class EntityHandler {
    public ArrayList<Entity> entities = new ArrayList<Entity>();

    public void tick() {
        for (Entity e : entities) {
            e.tick();
        }
    }
}
