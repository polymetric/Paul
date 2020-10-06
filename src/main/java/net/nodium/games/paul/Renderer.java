package net.nodium.games.paul;

import net.nodium.games.paul.entities.Camera;
import net.nodium.games.paul.entities.Entity;
import net.nodium.games.paul.entities.EntityGround;
import net.nodium.games.paul.entities.EntityOofCube;
import net.nodium.games.paul.entities.renderers.RenderEntity;
import net.nodium.games.paul.entities.renderers.RenderGround;
import net.nodium.games.paul.entities.renderers.RenderOofCube;
import net.nodium.games.paul.gl.*;
import net.nodium.games.paul.gl.shaders.GLShaderBase;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

public class Renderer {
    private Game game;
    private Display display;
    private GLShaderBase shader = new GLShaderBase();
    private Camera camera;

    private HashMap<Class, RenderEntity> entityRendererMap;

    private Matrix4f projMatrix;
    private final float FOV = 90;
    private final float NEAR_PLANE = 0.001f;
    private final float FAR_PLANE = 1000f;

    private Vector3f bg = new Vector3f(0, 0, 0);

    public Renderer(Game game, Display display) {
        this.game = game;
        this.display = display;

        entityRendererMap = new HashMap();
        initEntityRenderMap();

        createProjMatrix();
        shader.start();
        shader.loadProjMatrix(projMatrix);
        shader.stop();
    }

    public void init(Display display, Camera camera) {
        this.display = display;
        this.camera = camera;
    }

    public void render() {
        GL11.glEnable(GL_DEPTH_TEST);

        // set the clear color
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        double mult = 5D;
//        bg.x = (float) (Math.sin(mult * Math.toRadians(game.gameLoop.ticks % 360)) + 1) / 2;
//        bg.y = (float) (Math.sin(mult * Math.toRadians(game.gameLoop.ticks % 360) + (Math.PI * 2 / 3)) + 1) / 2;
//        bg.z = (float) (Math.sin(mult * Math.toRadians(game.gameLoop.ticks % 360) + (Math.PI * 4 / 3)) + 1) / 2;
        glClearColor(bg.x, bg.y, bg.z, 0.0f);

        // clear the framebuffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        camera.render();

        shader.start();
        shader.loadViewMatrix(camera);
        shader.stop();

        // render stuff here
        for (Entity e : game.entityHandler.entities) {
            if (e.isRenderable()) {
                RenderEntity renderEntity = getEntityRenderer(e);
                renderEntity.render(e, renderEntity.modelEntity);
            }
        }

        game.guiManager.render();

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
        entityRendererMap.put(EntityGround.class, new RenderGround(game.assetLoader, shader));
    }

    private RenderEntity getEntityRenderer(Entity entity) {
        return entityRendererMap.get(entity.getClass());
    }
}
