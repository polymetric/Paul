package net.nodium.games.paul;

import net.nodium.games.paul.entities.Camera;
import net.nodium.games.paul.gl.Display;
import net.nodium.games.paul.gl.shaders.GLShaderBase;
import net.nodium.games.paul.guis.GuiManager;
import net.nodium.games.paul.input.KeyHandler;
import net.nodium.games.paul.input.MouseHandler;

public class Game {
    public GameLoop gameLoop;
    public KeyHandler keyHandler;
    public MouseHandler mouseHandler;
    public Display display;
    public GLShaderBase shader;
    public Renderer renderer;
    public EntityHandler entityHandler;
    public AssetLoader assetLoader;
    public Camera camera;
    public GuiManager guiManager;

    public Game(String[] args) {
        keyHandler = new KeyHandler(this);

        display = new Display(800, 800, "Paul");
        display.setKeyHandler(keyHandler);
        display.createWindow();
        mouseHandler = new MouseHandler(this, display);

        assetLoader = new AssetLoader();
        shader = new GLShaderBase();
        camera = new Camera(mouseHandler);
        renderer = new Renderer(this, display, shader, camera);

        gameLoop = new GameLoop(this);
        entityHandler = new EntityHandler(this);
        guiManager = new GuiManager(assetLoader);
    }

    public void start() {
        init();
        loop();
        cleanup();
    }

    private void init() {
        renderer.init();
        entityHandler.init();

        // test stuff

//        shader = new GLShaderBase();
    }

    private void loop() {
        gameLoop.loop();
    }

    public void tick() {
        mouseHandler.tick();
        camera.tick();
        entityHandler.tick();
    }

    public void render() {
        renderer.render();
    }

    private void cleanup() {
        renderer.cleanup();
    }
}
