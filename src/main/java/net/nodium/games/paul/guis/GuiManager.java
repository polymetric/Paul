package net.nodium.games.paul.guis;

import net.nodium.games.paul.AssetLoader;
import net.nodium.games.paul.TextRenderer;
import net.nodium.games.paul.gl.textures.Texture;
import org.joml.Vector2f;

import java.util.ArrayList;

public class GuiManager {
    private AssetLoader assetLoader;
    private RenderGui guiRenderer;
    private TextRenderer textRenderer;
    public ArrayList<GuiTexture> guis = new ArrayList<GuiTexture>();

    public GuiManager(AssetLoader assetLoader) {
        this.assetLoader = assetLoader;
        textRenderer = new TextRenderer();
        guiRenderer = new RenderGui(assetLoader);

        init();
    }

    private void init() {
//        GuiTexture guiTexture = new GuiTexture(this)
//                .setTexture(new Texture("/textures/face.png"))
//                .setPos(-.75f, .75f)
//                .setScale(0.25f);
        new GuiText(this, textRenderer).setText("bruh");
    }

    public void render() {
        guiRenderer.render(guis);
    }
}
