package net.nodium.games.paul.guis;

import net.nodium.games.paul.AssetLoader;
import net.nodium.games.paul.Game;
import net.nodium.games.paul.TextRenderer;
import net.nodium.games.paul.entities.Camera;
import net.nodium.games.paul.gl.Display;
import net.nodium.games.paul.gl.textures.Texture;

import java.util.ArrayList;

public class GuiManager {
    public Game game;
    private AssetLoader assetLoader;
    private RenderGui guiRenderer;
    private TextRenderer textRenderer;
    public ArrayList<GuiTexture> guis = new ArrayList<GuiTexture>();
    public ArrayList<GuiString> guiStrings = new ArrayList<GuiString>();

    public GuiDeathScreen deathScreen;
    public GuiTimer timer;
    public GuiString killCounter;
    public GuiString groundHealth;

    public GuiManager(Game game, AssetLoader assetLoader, Display display){
        this.game = game;
        this.assetLoader = assetLoader;
        textRenderer = new TextRenderer();
        guiRenderer = new RenderGui(assetLoader, display);

        init();
    }

    private void init() {
//        GuiTexture guiTexture = new GuiTexture(this)
//                .setTexture(new Texture("/textures/face.png"))
//                .setPos(-.75f, .75f)
//                .setScale(0.25f);
//        new GuiString(this, textRenderer).setText("p a u l").setScale(16).setPos(-.15f, 0);
//        new GuiString(this, textRenderer).setText(" _");
//        new GuiTexture(this).setTexture(new Texture("/textures/gui/font.png")).setPos(0, 0);
        deathScreen = new GuiDeathScreen(this, textRenderer);
        timer = new GuiTimer(this, textRenderer);

        killCounter = new GuiString(this, textRenderer);
        killCounter.setScale(4);
        killCounter.setPos(-0.9f, 0.8f);
//        killCounter.setText("kills: 0");

        groundHealth = new GuiString(this, textRenderer);
        groundHealth.setScale(4);
        groundHealth.setPos(-0.9f, 0.7f);
//        groundHealth.setText("health: 100");
    }

    public void tick() {
        for (GuiTexture gui : guis) {
            if (gui.isActive()) {
                gui.tick();
            }
            if (gui.shouldBeDeleted()) { guis.remove(gui); }
        }
        for (GuiString gui : guiStrings) {
            if (gui.isActive()) {
                gui.tick();
            }
            if (gui.shouldBeDeleted()) { guis.remove(gui); }
        }
    }

    public void render() {
        guiRenderer.renderGuis(guis);
        guiRenderer.renderStrings(guiStrings, textRenderer);
    }

    public void add(GuiTexture gui) {
        if (gui instanceof GuiString) {
            guiStrings.add((GuiString) gui);
        } else {
            guis.add(gui);
        }
    }

    public void displayDeathScreen() {
        deathScreen.enable();
    }
    
    public void updateKills(int kills, int totalSpawned) {
        killCounter.setText(String.format("kills:  %4d / %4d", kills, totalSpawned));
    }

    public void updateHealth(int health) {
        groundHealth.setText(String.format("health: %4d", health));
    }
}
