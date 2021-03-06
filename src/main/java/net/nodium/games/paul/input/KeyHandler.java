package net.nodium.games.paul.input;

import net.nodium.games.paul.Game;
import net.nodium.games.paul.entities.Entity;
import net.nodium.games.paul.entities.EntityOofCube;
import org.lwjgl.glfw.GLFWKeyCallbackI;

import static org.lwjgl.glfw.GLFW.*;

public class KeyHandler implements GLFWKeyCallbackI {
    private Game game;

    public KeyHandler(Game game) {
        this.game = game;
    }

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        // pause
        if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) { game.togglePause(); }

        // movement
//        if (key == GLFW_KEY_W) { game.camera.isMovingFwd = action == GLFW_RELEASE ? false : true; }
//        if (key == GLFW_KEY_S) { game.camera.isMovingBwd = action == GLFW_RELEASE ? false : true; }
//        if (key == GLFW_KEY_A) { game.camera.isMovingLeft = action == GLFW_RELEASE ? false : true; }
//        if (key == GLFW_KEY_D) { game.camera.isMovingRight = action == GLFW_RELEASE ? false : true; }
//        if (key == GLFW_KEY_SPACE) { game.camera.isMovingUp = action == GLFW_RELEASE ? false : true; }
//        if (key == GLFW_KEY_LEFT_SHIFT) { game.camera.isMovingDown = action == GLFW_RELEASE ? false : true; }

        if (key == GLFW_KEY_W) { game.camera.isMovingFwd = action == GLFW_RELEASE ? false : true; }
        if (key == GLFW_KEY_S) { game.camera.isMovingBwd = action == GLFW_RELEASE ? false : true; }
        if (key == GLFW_KEY_A) { game.camera.isMovingLeft = action == GLFW_RELEASE ? false : true; }
        if (key == GLFW_KEY_D) { game.camera.isMovingRight = action == GLFW_RELEASE ? false : true; }
        if (key == GLFW_KEY_SPACE) { game.camera.boundEntity.isJumping = action == GLFW_RELEASE ? false : true; }
        if (key == GLFW_KEY_R && action == GLFW_PRESS) { game.camera.boundEntity.setPos(0, 2, 0); }
        if (key == GLFW_KEY_EQUAL && action == GLFW_PRESS) { game.camera.boundEntity.kill(null); }

        // fire lazor
        if (key == GLFW_KEY_F && action == GLFW_PRESS ) { game.camera.boundEntity.fireLazor(); }

        // debug
        if (key == GLFW_KEY_T && action == GLFW_PRESS) {
            for (Entity e : game.entityHandler.entities) {
                if (e instanceof EntityOofCube) {
                    e.jump();
                }
            }
        }
    }
}
