package net.nodium.games.paul.entities;

public class EntityCamera extends Entity {
    public EntityCamera(float x, float y, float z) {
        super(x, y, z);

        enableGravity = false;
        airResistanceDecel = 10F;
//        enableAirResistance = false;
    }
}
