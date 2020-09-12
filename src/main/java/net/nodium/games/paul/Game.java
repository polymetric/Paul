package net.nodium.games.paul;

import net.nodium.games.paul.entities.Entity;
import net.nodium.games.paul.gl.Display;
import net.nodium.games.paul.gl.models.GLModel;
import net.nodium.games.paul.gl.shaders.GLShaderBase;
import net.nodium.games.paul.gl.textures.Texture;
import org.joml.Vector3f;

public class Game {
    public GameLoop gameLoop;
    public Display display;
    public GLShaderBase shader;
    public Renderer renderer;
    public EntityHandler entityHandler;
    public AssetLoader assetLoader;


    public Game(String[] args) {
        display = new Display(800, 800, "Paul");
        display.createWindow();

        assetLoader = new AssetLoader();
        shader = new GLShaderBase();
        renderer = new Renderer(this, display, shader);

        gameLoop = new GameLoop(this);
        entityHandler = new EntityHandler();
    }

    public void start() {
        init();
        loop();
        cleanup();
    }

    private void init() {
        renderer.init();

        // test stuff

//        shader = new GLShaderBase();

        float[] vertices = {
                -0.5f, 0.5f, 0f,//v0
                -0.5f, -0.5f, 0f,//v1
                0.5f, -0.5f, 0f,//v2
                0.5f, 0.5f, 0f,//v3
        };

        int[] indices = {
                0,1,3,//top left triangle (v0, v1, v3)
                3,1,2//bottom right triangle (v3, v1, v2)
        };

        float[] textureCoords = {
                0,0,
                0,1,
                1,1,
                1,0
        };

        Texture textureTest = new Texture("src/main/resources/textures/face.png");
        GLModel modelTest = assetLoader.loadToVAO(vertices, indices, textureCoords, textureTest, shader);
        Entity entityTest = new Entity(modelTest, new Vector3f(0.0F, 0.0F, 0.0F));

        entityHandler.entities.add(entityTest);
    }

    private void loop() {
        gameLoop.loop();
    }

    public void tick() {
        entityHandler.tick();
    }

    public void render() {
        renderer.render();
    }

    private void cleanup() {
        renderer.cleanup();
    }
}
