package net.nodium.games.paul;

import net.nodium.games.paul.entities.Entity;
import net.nodium.games.paul.gl.*;
import net.nodium.games.paul.gl.shaders.GLShaderBase;
import org.joml.Matrix4f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

public class Renderer {
    private Game game;
    private Display display;

    private Matrix4f projMatrix;
    private final float FOV = 70;
    private final float NEAR_PLANE = 0.001f;
    private final float FAR_PLANE = 1000f;

    public Renderer(Game game, Display display, GLShaderBase shader) {
        this.game = game;
        this.display = display;
//        createProjMatrix();
//        shader.start();
//        shader.loadProjMatrix(projMatrix);
//        shader.stop();
    }

    public void init() {
        // set the clear color
        glClearColor(1.0f, 0.0f, 1.0f, 0.0f);
    }

    public void render() {
        // clear the framebuffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        // render stuff here
        for (Entity e : game.entityHandler.entities) {
            e.render();
        }

        // swap the color buffers
        glfwSwapBuffers(display.getWindowID());

        // poll for window events; the key callback above will only be invoked during this call
        glfwPollEvents();
    }

    public void cleanup() {
        game.assetLoader.cleanup();
    }

    private void createProjMatrix() {
        float aspectRatio = (float) display.getWidth() / (float) display.getHeight();
        float yScale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float xScale = yScale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;

        projMatrix = new Matrix4f();
        projMatrix.m00(xScale);
        projMatrix.m11(yScale);
        projMatrix.m22(-((FAR_PLANE + NEAR_PLANE) / frustum_length));
        projMatrix.m23(-1);
        projMatrix.m32(-((2 * NEAR_PLANE * FAR_PLANE) / frustum_length));
        projMatrix.m33(0);
    }
}
