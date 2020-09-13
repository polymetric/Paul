package net.nodium.games.paul;

import static org.lwjgl.glfw.GLFW.*;

public class GameLoop {
    public Game game;

    // game logic
    public static final int TARGET_TPS = 60;
    public double tps;
    private long lastTick = 0;
    private long logicDelta = 0;
    public long ticks;

    // renderer
    public static final int TARGET_FPS = 60;
    public double fps;
    private long lastFrame;
    private long renderDelta;
    public long frames;
    public float timeTillNextTick;

    public static final double NANOS_PER_SECOND = 1e9D;

    public GameLoop(Game game) {
        this.game = game;
    }

    public void loop() {
        while(!glfwWindowShouldClose(game.display.getWindowID())) {
            long now = System.nanoTime();
            logicDelta = now - lastTick;
            renderDelta = now - lastFrame;

            if (logicDelta >= NANOS_PER_SECOND / TARGET_TPS) {
                tps = NANOS_PER_SECOND / logicDelta;
//                System.out.println(tps);
                game.tick();
                lastTick = System.nanoTime();
                ticks++;
//                System.out.println("tick");
            }

            if (renderDelta >= NANOS_PER_SECOND / TARGET_FPS) {
                fps = (int) Math.ceil(NANOS_PER_SECOND / renderDelta);
//                System.out.println(fps);
                game.render();
                lastFrame = System.nanoTime();
                frames++;
                timeTillNextTick = logicDelta / (float) (NANOS_PER_SECOND / (float) TARGET_TPS);
//                System.out.println(timeTillNextTick);
            }
        }
    }
}
