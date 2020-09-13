package net.nodium.games.paul;

import net.nodium.games.paul.entities.Camera;
import net.nodium.games.paul.entities.Entity;
import net.nodium.games.paul.entities.EntityOofCube;
import net.nodium.games.paul.entities.renderers.RenderEntity;
import net.nodium.games.paul.entities.renderers.RenderOofCube;
import net.nodium.games.paul.gl.*;
import net.nodium.games.paul.gl.shaders.GLShaderBase;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

public class Renderer {
    private Game game;
    private Display display;
    private GLShaderBase shader;
    private Camera camera;

    private HashMap<Class, RenderEntity> entityRendererMap;

    private Matrix4f projMatrix;
    private final float FOV = 90;
    private final float NEAR_PLANE = 0.001f;
    private final float FAR_PLANE = 1000f;

    public Renderer(Game game, Display display, GLShaderBase shader, Camera camera) {
        this.game = game;
        this.display = display;
        this.shader = shader;
        this.camera = camera;

        entityRendererMap = new HashMap();
        initEntityRenderMap();

        createProjMatrix();
        shader.start();
        shader.loadProjMatrix(projMatrix);
        shader.stop();
    }

    public void init() {
    }

    public void render() {
        GL11.glEnable(GL_DEPTH_TEST);

        // set the clear color
        glClearColor(1.0f, 0.0f, 1.0f, 0.0f);

        // clear the framebuffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

//        camera.render(game.gameLoop.timeTillNextTick);

        shader.start();
        shader.loadViewMatrix(camera);
        shader.stop();

        // render stuff here
        for (Entity e : game.entityHandler.entities) {
            RenderEntity renderEntity = getEntityRenderer(e);
            renderEntity.render(e, renderEntity.modelEntity);
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

    private void initEntityRenderMap() {
        entityRendererMap.put(EntityOofCube.class, new RenderOofCube(game.assetLoader, shader));
    }

    private RenderEntity getEntityRenderer(Entity entity) {
        return entityRendererMap.get(entity.getClass());
    }
}
