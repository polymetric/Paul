package net.nodium.games.paul.guis;

import net.nodium.games.paul.TextRenderer;

public class GuiTimer extends GuiString {
    public int time = 0;

    public GuiTimer(GuiManager guiManager, TextRenderer textRenderer) {
        super(guiManager, textRenderer);

        setScale(4);
        setPos(-.9f, 0);

        disable();
    }

    public void tick() {
        time++;
    }
}
