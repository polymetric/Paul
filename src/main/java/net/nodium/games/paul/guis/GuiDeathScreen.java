package net.nodium.games.paul.guis;

import net.nodium.games.paul.TextRenderer;

public class GuiDeathScreen extends GuiString {
    private GuiManager guiManager;

    private int age = 0;
    private int lifespan;

    public GuiDeathScreen(GuiManager guiManager, TextRenderer textRenderer) {
        super(guiManager, textRenderer);
        this.guiManager = guiManager;

        setText("you now have The dead");
        setScale(4);
        setPos(-.9f, 0);
        lifespan = 60;

        disable();
    }

    public void playerDied() {
        enable();
        age = 0;
    }

    public void tick() {
        System.out.println(age);
        if (age > lifespan) {
            this.disable();
        }
        age++;
    }
}
