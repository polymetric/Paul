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
import net.nodium.games.paul.gl.shaders.GLShaderDebug;
import net.nodium.games.paul.math.MathUtils;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL46;

import java.nio.FloatBuffer;
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

    public Matrix4f projMatrix;
    public final float FOV = 90;
    public final float NEAR_PLANE = 0.001f;
    public final float FAR_PLANE = 1000f;

    public Vector3f bg = new Vector3f(0, 0, 0);

    public Renderer(Game game, Display display) {
        this.game = game;
        this.display = display;

        entityRendererMap = new HashMap();
        initEntityRenderMap();

        projMatrix = MathUtils.createProjMatrix(display, FOV, NEAR_PLANE, FAR_PLANE);

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

//         render stuff here
        for (Entity e : game.entityHandler.entities) {
            if (e.isRenderable()) {
                RenderEntity renderEntity = getEntityRenderer(e);
                renderEntity.render(e, renderEntity.modelEntity);
                if (e.hitbox != null) {
                    e.hitbox.render(null, projMatrix, camera);
                }
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

    private void initEntityRenderMap() {
        entityRendererMap.put(EntityOofCube.class, new RenderOofCube(game.assetLoader, shader));
        entityRendererMap.put(EntityGround.class, new RenderGround(game.assetLoader, shader));
    }

    private RenderEntity getEntityRenderer(Entity entity) {
        return entityRendererMap.get(entity.getClass());
    }
}
