package net.nodium.games.paul;

import net.nodium.games.paul.gl.textures.Texture;
import org.joml.Vector2i;
import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

public class TextRenderer {
    public static final String LOC_FONT = "/textures/gui/font.png";
    public Texture texture = new Texture(LOC_FONT);
    //    private BufferedImage charTable;
    public final int charStart = 32;
    public final int charWidth = 8;
    public final int charHeight = 8;
    public final int imgWidth = 64;
    public final int imgHeight = 64;
    public final boolean isTableUppercaseOnly = true;

    public Vector2i getCharOffset(char c) {
        if (isTableUppercaseOnly) {
            c = String.valueOf(c).toUpperCase().charAt(0);
        }
        return new Vector2i((c - charStart) * charWidth % imgWidth, ((c - charStart) / charHeight) * charHeight);
    }
}
