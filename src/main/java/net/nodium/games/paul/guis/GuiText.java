package net.nodium.games.paul.guis;

import net.nodium.games.paul.TextRenderer;
import net.nodium.games.paul.gl.textures.Texture;
import org.joml.Vector2f;

import javax.xml.soap.Text;

public class GuiText {
    private GuiManager guiManager;
    private TextRenderer textRenderer;

    private String text;
    public GuiTextChar[] charQuads;

    public Vector2f pos;
    public float scale;

    public GuiText(GuiManager guiManager, TextRenderer textRenderer) {
        this.guiManager = guiManager;
        this.textRenderer = textRenderer;
    }

    public GuiText setText(String text) {
        this.text = text;
        createQuadsFromString(text);
        return this;
    }

    private void createQuadsFromString(String text) {
        GuiTextChar[] charQuads = new GuiTextChar[text.length()];

        for (int i = 0; i < text.length(); i++) {
//            charQuads[i] = new GuiTextChar(guiManager, textRenderer).setChar(text.charAt(i));
        }

        this.charQuads = charQuads;
    }

    public GuiText setPos(float x, float y) {
        this.pos.x = x;
        this.pos.y = y;
        return this;
    }
}
