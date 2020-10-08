package net.nodium.games.paul.input;

import net.nodium.games.paul.Game;
import net.nodium.games.paul.entities.Camera;
import net.nodium.games.paul.gl.Display;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class MouseHandler {
    private Game game;
    private Display display;

    public int mouseX;
    public int mouseY;
    public int prevX;
    public int prevY;
    public int deltaX;
    public int deltaY;

    public MouseHandler(Game game, Display display) {
        this.game = game;
        this.display = display;
    }

    public void tick() {
        if (game.isPaused()) {
            display.enableCursor();
            return;
        }

        display.disableCursor();

        DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer y = BufferUtils.createDoubleBuffer(1);

        glfwGetCursorPos(display.getWindowID(), x, y);
        x.rewind();
        y.rewind();
        mouseX = (int) x.get();
        mouseY = (int) y.get();

        deltaX = mouseX - display.getWidth() / 2;
        deltaY = mouseY - display.getHeight() / 2;

        glfwSetCursorPos(display.getWindowID(), (double) display.getWidth() / 2, (double) display.getHeight() / 2);
    }
}
