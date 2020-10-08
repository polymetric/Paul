package net.nodium.games.paul.guis;

import net.nodium.games.paul.gl.textures.Texture;
import org.joml.Vector2f;

public class GuiTexture {
    private GuiManager guiManager;

    public Texture texture;
    public Vector2f pos = new Vector2f(0, 0);
    public float scale = 1;

    public GuiTexture(GuiManager guiManager) {
        this.guiManager = guiManager;

        guiManager.add(this);
    }

    private boolean shouldBeDeleted = false;
    public void delete() {
        shouldBeDeleted = true;
    }
    public boolean shouldBeDeleted() { return shouldBeDeleted; }

    public void tick() {

    }

    // setters

    public GuiTexture setTexture(Texture texture) {
        this.texture = texture;
        return this;
    }

    public GuiTexture setPos(float x, float y) {
        this.pos.x = x;
        this.pos.y = y;
        return this;
    }

    public GuiTexture setScale(float scale) {
        this.scale = scale;
        return this;
    }

    private boolean active = true;
    public void enable() { active = true; }
    public void disable() { active = false; }
    public boolean isActive() { return active; }

    public double getLogicDelta() {
        return guiManager.game.gameLoop.getLogicDelta();
    }
}
