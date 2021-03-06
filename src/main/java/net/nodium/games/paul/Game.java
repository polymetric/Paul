package net.nodium.games.paul;

import net.nodium.games.paul.entities.Camera;
import net.nodium.games.paul.gl.Display;
import net.nodium.games.paul.guis.GuiManager;
import net.nodium.games.paul.input.KeyHandler;
import net.nodium.games.paul.input.MouseHandler;

public class Game {
    public GameLoop gameLoop;
    public KeyHandler keyHandler;
    public MouseHandler mouseHandler;
    public Display display;
    public Renderer renderer;
    public EntityHandler entityHandler;
    public AssetLoader assetLoader;
    public Camera camera;
    public GuiManager guiManager;

    private boolean isPaused = false;

    public Game(String[] args) {
        keyHandler = new KeyHandler(this);

        display = new Display(this, 1600, 864, "Paul");
        display.setKeyHandler(keyHandler);
        display.createWindow();
        mouseHandler = new MouseHandler(this, display);

        assetLoader = new AssetLoader();
        renderer = new Renderer(this, display);

        gameLoop = new GameLoop(this);
        entityHandler = new EntityHandler(this);
        guiManager = new GuiManager(this, assetLoader, display);

        camera = new Camera(entityHandler, mouseHandler);
    }

    public void start() {
        init();
        loop();
        cleanup();
    }

    private void init() {
        renderer.init(display, camera);
        entityHandler.init();

        // test stuff

//        shader = new GLShaderBase();
    }

    private void loop() {
        gameLoop.loop();
    }

    public void tick() {
        if (isPaused()) {
            display.enableCursor();
            return;
        }

        if (!camera.boundEntity.isDead()) {
            display.disableCursor();

            mouseHandler.tick();
        }

        camera.tick();
        guiManager.tick();
        entityHandler.tick();
    }

    public void render() {
        renderer.render();
    }

    public void togglePause() {
        isPaused = !isPaused;
    }

    public boolean isPaused() {
        return isPaused;
    }

    private void cleanup() {
        renderer.cleanup();
    }
}
