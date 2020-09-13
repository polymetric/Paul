package net.nodium.games.paul.guis;

import net.nodium.games.paul.gl.textures.Texture;
import org.joml.Vector2f;

public class GuiTexture {
    public Texture texture;
    public Vector2f pos;
    public float scale;

    public GuiTexture(Texture texture, Vector2f pos, float scale) {
        this.texture = texture;
        this.pos = pos;
        this.scale = scale;
    }
}
