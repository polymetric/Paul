package net.nodium.games.paul.guis;

import net.nodium.games.paul.TextRenderer;

public class GuiDeathScreen extends GuiInfo {
    public GuiDeathScreen(GuiManager guiManager, TextRenderer textRenderer) {
        super(guiManager, textRenderer);

        setText("you now have the dead");
    }
}
