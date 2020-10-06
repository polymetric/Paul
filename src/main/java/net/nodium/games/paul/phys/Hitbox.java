package net.nodium.games.paul.phys;

import org.joml.Vector3f;

public class Hitbox {
    public Vector3f pos = new Vector3f();
    public Vector3f size = new Vector3f();
    public Bounds bounds = new Bounds();

    public Hitbox() {

    }

    public void calcBounds() {
        bounds.min.x = pos.x - size.x / 2;
        bounds.min.y = pos.y - size.y / 2;
        bounds.min.z = pos.z - size.z / 2;

        bounds.max.x = pos.x + size.x / 2;
        bounds.max.y = pos.y + size.y / 2;
        bounds.max.z = pos.z + size.z / 2;
    }

    public boolean intersectsWith(Hitbox other) {
        this.calcBounds();
        other.calcBounds();

        // check X
        if (bounds.max.x > other.bounds.min.x) return true;
        if (bounds.min.x < other.bounds.max.x) return true;

        return false;
    }

    public class Bounds {
        Vector3f min = new Vector3f();
        Vector3f max = new Vector3f();

        public Bounds(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
            min.x = minX;
            min.y = minY;
            min.z = minZ;

            max.x = maxX;
            max.y = maxY;
            max.z = maxZ;
        }

        public Bounds() {

        }
    }
}
