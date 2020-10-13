package net.nodium.games.paul.guis;

import net.nodium.games.paul.TextRenderer;

public class GuiInfo extends GuiString {
    public int age = 0;
    public float lifespan;

    public GuiInfo(GuiManager guiManager, TextRenderer textRenderer) {
        super(guiManager, textRenderer);

        setScale(4);
        setPos(-.9f, 0);
        lifespan = 3;

        disable();
    }

    public void tick() {
        age++;

        if (age > lifespan / getLogicDelta()) {
            this.disable();
        }
    }
}
