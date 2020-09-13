package net.nodium.games.paul.guis;

import net.nodium.games.paul.AssetLoader;
import net.nodium.games.paul.gl.models.GLModelRaw;
import net.nodium.games.paul.gl.shaders.GLShaderGui;
import net.nodium.games.paul.gl.textures.Texture;
import net.nodium.games.paul.math.MathUtils;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;

public class RenderGui {
    private final GLModelRaw quad;
    private GLShaderGui shader;

    public RenderGui(AssetLoader assetLoader) {
        float[] positions = { -1, 1, -1, -1, 1, 1, 1, -1 };
        quad = assetLoader.loadToVAO(positions);
        shader = new GLShaderGui();
    }

    public void render(ArrayList<GuiTexture> guis) {
        shader.start();
        GL30.glBindVertexArray(quad.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        for (GuiTexture gui : guis) {
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL13.glBindTexture(GL13.GL_TEXTURE_2D, gui.texture.getTextureID());

            Matrix4f matrix = MathUtils.createTransformationMatrix(gui.pos, gui.scale);
            shader.loadTransMatrix(matrix);

            GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());
        }

        GL11.glDisable(GL11.GL_BLEND);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        shader.stop();
    }

    public void cleanup() {
        shader.cleanup();
    }
}
