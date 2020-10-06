package net.nodium.games.paul.guis;

import net.nodium.games.paul.TextRenderer;
import net.nodium.games.paul.gl.textures.Texture;
import org.joml.Vector2f;

public class GuiString extends GuiTexture {
    private GuiManager guiManager;
    public TextRenderer textRenderer;

    public String text;

    public GuiString(GuiManager guiManager, TextRenderer textRenderer) {
        super(guiManager);
        this.textRenderer = textRenderer;
    }

    public GuiString setText(String text) {
        this.text = text;
        return this;
    }

    public GuiString setPos(float x, float y) {
        this.pos.x = x;
        this.pos.y = y;
        return this;
    }
}
