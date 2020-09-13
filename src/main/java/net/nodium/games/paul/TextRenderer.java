package net.nodium.games.paul;

import org.joml.Vector2i;
import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

public class TextRenderer {
    private BufferedImage charTable;
    private final int charStart = 32;
    private final int charWidth = 8;
    private final int charHeight = 8;
    private int imgWidth = 64;
    private int imgHeight = 64;

    public void renderText() {
        byte[] text = getCharImage('b');
        ByteBuffer buffer = BufferUtils.createByteBuffer(text.length);
        buffer.put(text);
        buffer.rewind();
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, 8, 8, 0, GL_RGBA, GL_BYTE, buffer);
    }

    public byte[] getCharImage(char c) {
        c = String.valueOf(c).toUpperCase().charAt(0);
        Vector2i topLeft = getCharOffset(c);
        try {
            charTable = ImageIO.read(new FileInputStream("C:\\Users\\polymetric\\Documents\\ExportedFont.png"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        int[] pixels = new int[0];

        return ((DataBufferByte) charTable.getRaster().getDataBuffer()).getData();
    }

    private Vector2i getCharOffset(char c) {
        return new Vector2i((c - charStart) * charWidth % imgWidth, ((c - charStart) / charHeight) * charHeight);
    }

    public static void main(String[] args) throws Exception {
        TextRenderer tr = new TextRenderer();

//        System.out.println(tr.getCharOffset('b').x + " " + tr.getCharOffset('b').y);

        BufferedImage image = ImageIO.read(new FileInputStream("C:\\Users\\polymetric\\Documents\\ExportedFont - Copy.png"));
        byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();

        JFrame frame = new JFrame();
//        frame.setContentPane(new BufferedImage(pixels));
    }
}
