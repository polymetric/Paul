
package net.nodium.games.paul;

import net.nodium.games.paul.gl.models.GLModelRaw;
import net.nodium.games.paul.gl.models.GLModelTextured;
import net.nodium.games.paul.gl.GLUtils;
import net.nodium.games.paul.gl.shaders.GLShader;
import net.nodium.games.paul.gl.shaders.GLShaderBase;
import net.nodium.games.paul.gl.textures.Texture;
import org.lwjgl.opengl.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

public class AssetLoader {
    private ArrayList<Integer> vaos = new ArrayList<Integer>();
    private ArrayList<Integer> vbos = new ArrayList<Integer>();
    private ArrayList<Integer> textures = new ArrayList<Integer>();

    public GLModelTextured loadToVAO(float[] positions, float[] textureCoords, float[] normals, int[] indices, Texture texture, GLShaderBase shader) {
        int vaoID = createVAO();

        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, 3, positions);
        storeDataInAttributeList(1, 2, textureCoords);
        storeDataInAttributeList(2, 3, normals);

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
            GL46.glDeleteVertexArrays(vaoID);
        }

        for (int vboID : vbos) {
            GL46.glDeleteBuffers(vboID);
        }

        for (int textureID : textures) {
            GL46.glDeleteTextures(textureID);
        }
    }

    private int createVAO() {
        int vaoID = GL46.glGenVertexArrays();
        vaos.add(vaoID);

        GL46.glBindVertexArray(vaoID);

        return vaoID;
    }

    private void unbindVAO() {
        GL46.glBindVertexArray(0);
    }

    private int createVBO(int target) {
        int vboID = GL46.glGenBuffers();
        vbos.add(vboID);

        GL46.glBindBuffer(target, vboID);

        return vboID;
    }

    private void unbindVBO(int target) {
        GL46.glBindBuffer(target, 0);
    }

    private void storeDataInAttributeList(int attributeNumber, int dimensions, float[] data) {
        int vboID = createVBO(GL46.GL_ARRAY_BUFFER);
        FloatBuffer buffer = GLUtils.storeDataInFloatBuffer(data);

        GL46.glBufferData(GL46.GL_ARRAY_BUFFER, buffer, GL46.GL_STATIC_DRAW);
        GL46.glVertexAttribPointer(attributeNumber, dimensions, GL46.GL_FLOAT, false, 0, 0);

        unbindVBO(GL46.GL_ARRAY_BUFFER);
    }

    private void bindIndicesBuffer(int[] indices) {
        int vboID = createVBO(GL46.GL_ELEMENT_ARRAY_BUFFER);
        IntBuffer buffer = GLUtils.storeDataInIntBuffer(indices);

        GL46.glBufferData(GL46.GL_ELEMENT_ARRAY_BUFFER, buffer, GL46.GL_STATIC_DRAW);
    }
}
