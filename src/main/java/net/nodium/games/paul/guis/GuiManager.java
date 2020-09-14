package net.nodium.games.paul.guis;

import net.nodium.games.paul.AssetLoader;
import net.nodium.games.paul.gl.textures.Texture;
import org.joml.Vector2f;

import java.util.ArrayList;

public class GuiManager {
    private AssetLoader assetLoader;
    private RenderGui guiRenderer;
    private ArrayList<GuiTexture> guis = new ArrayList<GuiTexture>();

    public GuiManager(AssetLoader assetLoader) {
        this.assetLoader = assetLoader;
        guiRenderer = new RenderGui(assetLoader);

        init();
    }

    private void init() {
        guis.add(new GuiTexture(new Texture("/textures/face.png"), new Vector2f(-.75f, .75f), .25f));
    }

    public void render() {
        guiRenderer.render(guis);
    }
}
