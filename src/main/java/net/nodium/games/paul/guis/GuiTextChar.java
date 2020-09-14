package net.nodium.games.paul.guis;

import net.nodium.games.paul.TextRenderer;

public class GuiTextChar extends GuiTexture {
    private TextRenderer textRenderer;
    public char character;

    public GuiTextChar(GuiManager guiManager, TextRenderer textRenderer, char character) {
        super(guiManager);
        this.textRenderer = textRenderer;
        this.character = character;
    }


}
