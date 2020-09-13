
package net.nodium.games.paul;

import net.nodium.games.paul.gl.models.GLModelRaw;
import net.nodium.games.paul.gl.models.GLModelTextured;
import net.nodium.games.paul.gl.GLUtils;
import net.nodium.games.paul.gl.shaders.GLShader;
import net.nodium.games.paul.gl.shaders.GLShaderBase;
import net.nodium.games.paul.gl.textures.Texture;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

public class AssetLoader {
    private ArrayList<Integer> vaos = new ArrayList<Integer>();
    private ArrayList<Integer> vbos = new ArrayList<Integer>();
    private ArrayList<Integer> textures = new ArrayList<Integer>();

    public GLModelTextured loadToVAO(float[] positions, int[] indices, float[] textureCoords, Texture texture, GLShaderBase shader) {
        int vaoID = createVAO();

        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, 3, positions);
        storeDataInAttributeList(1, 2, textureCoords);

        unbindVAO();

        return new GLModelTextured(vaoID, indices.length, texture, shader);
    }

    public GLModelRaw loadToVAO(float[] positions) {
        int vaoID = createVAO();

        storeDataInAttributeList(0, 2, positions);

        unbindVAO();

        return new GLModelRaw(vaoID, positions.length / 2);
    }

    public void cleanup() {
        for (int vaoID : vaos) {
            GL30.glDeleteVertexArrays(vaoID);
        }

        for (int vboID : vbos) {
            GL15.glDeleteBuffers(vboID);
        }

        for (int textureID : textures) {
            GL11.glDeleteTextures(textureID);
        }
    }

    private int createVAO() {
        int vaoID = GL30.glGenVertexArrays();
        vaos.add(vaoID);

        GL30.glBindVertexArray(vaoID);

        return vaoID;
    }

    private void unbindVAO() {
        GL30.glBindVertexArray(0);
    }

    private int createVBO(int target) {
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);

        GL15.glBindBuffer(target, vboID);

        return vboID;
    }

    private void unbindVBO(int target) {
        GL15.glBindBuffer(target, 0);
    }

    private void storeDataInAttributeList(int attributeNumber, int dimensions, float[] data) {
        int vboID = createVBO(GL15.GL_ARRAY_BUFFER);
        FloatBuffer buffer = GLUtils.storeDataInFloatBuffer(data);

        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, dimensions, GL11.GL_FLOAT, false, 0, 0);

        unbindVBO(GL15.GL_ARRAY_BUFFER);
    }

    private void bindIndicesBuffer(int[] indices) {
        int vboID = createVBO(GL15.GL_ELEMENT_ARRAY_BUFFER);
        IntBuffer buffer = GLUtils.storeDataInIntBuffer(indices);

        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }
}
