package net.nodium.games.paul.guis;

import net.nodium.games.paul.AssetLoader;
import net.nodium.games.paul.TextRenderer;
import net.nodium.games.paul.gl.Display;
import net.nodium.games.paul.gl.models.GLModelRaw;
import net.nodium.games.paul.gl.shaders.GLShaderGui;
import net.nodium.games.paul.gl.shaders.GLShaderGuiString;
import net.nodium.games.paul.math.MathUtils;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.Vector;

public class RenderGui {
    private Display display;
    private final GLModelRaw quad;
    private GLShaderGui shaderGui = new GLShaderGui();
    private GLShaderGuiString shaderGuiString = new GLShaderGuiString();

    public RenderGui(AssetLoader assetLoader, Display display) {
        this.display = display;
        float[] positions = {-1, 1, -1, -1, 1, 1, 1, -1};
        quad = assetLoader.loadToVAO(positions);
    }

    public void renderGuis(ArrayList<GuiTexture> guis) {
        if (guis.size() < 1) {
            return;
        }

        shaderGui.start();

        preFrame();

        for (GuiTexture gui : guis) {
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL13.glBindTexture(GL13.GL_TEXTURE_2D, gui.texture.getTextureID());

            Matrix4f matrix = MathUtils.createGuiTransformationMatrix(display, gui.texture, gui.pos, gui.scale);
            shaderGui.loadTransMatrix(matrix);

            GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());
        }

        postFrame();

        shaderGui.stop();
    }

    public void renderStrings(ArrayList<GuiString> guiStrings, TextRenderer textRenderer) {
        if (guiStrings.size() < 1) {
            return;
        }

        shaderGuiString.start();

        preFrame();

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, textRenderer.texture.getTextureID());

        for (GuiString gui : guiStrings) {
            String text = gui.text;

            shaderGuiString.loadTextureSize(textRenderer.imgWidth);
            shaderGuiString.loadSpriteSize(textRenderer.charWidth);

            for (int i = 0; i < text.length(); i++) {
                Matrix4f matrix = MathUtils.createStringTransformationMatrix(display, textRenderer.texture, textRenderer.charWidth, gui.pos, gui.scale);
                matrix.translate(new Vector3f(i/4f, 0, 0));
                matrix.scale(1f / textRenderer.charWidth);
                shaderGuiString.loadTransMatrix(matrix);

                Vector2i spriteOffset = textRenderer.getCharOffset(text.charAt(i));
                shaderGuiString.loadSpriteOffset(new Vector2f(spriteOffset.x / 64F, spriteOffset.y / 64F));

//                System.out.println(new Vector2f(textureCoords.x / 64F, textureCoords.y / 64F));

                GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());
            }
        }

        postFrame();

        shaderGuiString.stop();
    }

    public void preFrame() {
        GL30.glBindVertexArray(quad.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
    }

    public void postFrame() {
        GL11.glDisable(GL11.GL_BLEND);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }

    public void cleanup() {
        shaderGui.cleanup();
        shaderGuiString.cleanup();
    }
}
