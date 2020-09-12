package net.nodium.games.paul;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class GameLoop {
    public Game game;

    // game logic
    public static final int TARGET_TPS = 240;
    public double tps;
    private long lastTick;
    private long logicDelta;
    public long ticks;

    // renderer
    public static final int TARGET_FPS = 240;
    public double fps;
    private long lastRender;
    private long renderDelta;
    public long frames;

    public static final double NANOS_PER_SECOND = 1e9D;

    public GameLoop(Game game) {
        this.game = game;
    }

    public void loop() {
        while(!glfwWindowShouldClose(game.display.getWindowID())) {
            long now = System.nanoTime();
            logicDelta = now - lastTick;
            renderDelta = now - lastRender;

            if (logicDelta >= NANOS_PER_SECOND / TARGET_TPS) {
                tps = NANOS_PER_SECOND / logicDelta;
//                System.out.println(tps);
                game.tick();
                lastTick = System.nanoTime();
                ticks++;
            }

            if (renderDelta >= NANOS_PER_SECOND / TARGET_FPS) {
                fps = (int) Math.ceil(NANOS_PER_SECOND / renderDelta);
//                System.out.println(fps);
                game.render();
                lastRender = System.nanoTime();
                frames++;
            }
        }
    }
}
