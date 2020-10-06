package net.nodium.games.paul.guis;

import net.nodium.games.paul.AssetLoader;
import net.nodium.games.paul.TextRenderer;
import net.nodium.games.paul.gl.Display;
import net.nodium.games.paul.gl.textures.Texture;

import java.util.ArrayList;

public class GuiManager {
    private AssetLoader assetLoader;
    private RenderGui guiRenderer;
    private TextRenderer textRenderer;
    public ArrayList<GuiTexture> guis = new ArrayList<GuiTexture>();
    public ArrayList<GuiString> guiStrings = new ArrayList<GuiString>();

    public GuiManager(AssetLoader assetLoader, Display display) {
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
}
