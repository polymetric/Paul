package net.nodium.games.paul.input;

import net.nodium.games.paul.Game;
import org.lwjgl.glfw.GLFWKeyCallbackI;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

public class KeyHandler implements GLFWKeyCallbackI {
    private Game game;

    public KeyHandler(Game game) {
        this.game = game;
    }

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) { glfwSetWindowShouldClose(window, true); }
        if (key == GLFW_KEY_W) { game.camera.isMovingFwd = action == GLFW_RELEASE ? false : true; }
        if (key == GLFW_KEY_S) { game.camera.isMovingBwd = action == GLFW_RELEASE ? false : true; }
        if (key == GLFW_KEY_A) { game.camera.isMovingLeft = action == GLFW_RELEASE ? false : true; }
        if (key == GLFW_KEY_D) { game.camera.isMovingRight = action == GLFW_RELEASE ? false : true; }
        if (key == GLFW_KEY_SPACE) { game.camera.isMovingUp = action == GLFW_RELEASE ? false : true; }
        if (key == GLFW_KEY_LEFT_SHIFT) { game.camera.isMovingDown = action == GLFW_RELEASE ? false : true; }
        if (key == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS ) { game.camera.entityCamera.isFiringLazor = true; }
    }
}
