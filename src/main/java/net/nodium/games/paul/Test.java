package net.nodium.games.paul;

public class Test {
    public static void main(String[] args) throws Exception {
        float ang = 0;
        float inc = 10;

        int last = 0;
        int current = 0;
        int occurrences = 0;

        while(true) {
            current = (int) quantizeAngle(ang);
            if (last != current) {
                occurrences = 0;
            }
            System.out.printf("%12.3f %12d %12d\n", ang, current, (occurrences += 1));
//            System.out.printf("%12.3f %12d\n", ang, current);
            ang += inc;
            if (ang > 360 * 2) return;
            last = current;
        }
    }

    public static int quantizeAngle(float ang) {
        return (int) (normalizeAngle(ang + 45) + 180) / 90;
    }

    static float normalizeAngle(float yaw) {
        return ((yaw + 180) % 360) - 180;
    }
}
