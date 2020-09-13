package net.nodium.games.paul.gl.models;

import net.nodium.games.paul.gl.shaders.GLShader;
import net.nodium.games.paul.gl.shaders.GLShaderBase;
import net.nodium.games.paul.gl.textures.Texture;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class GLModelTextured extends GLModelRaw {
    private Texture texture;
    public GLShaderBase shader;
    private Matrix4f transformationMatrix;

    public GLModelTextured(int vaoID, int vertexCount, Texture texture, GLShaderBase shader) {
        super(vaoID, vertexCount);
        this.texture = texture;
        this.shader = shader;
    }

    public void render() {
        shader.start();

        GL30.glBindVertexArray(this.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());

        shader.loadTransMatrix(transformationMatrix);

        GL11.glDrawElements(GL11.GL_TRIANGLES, this.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);

        shader.stop();
    }

    public void setTransformationMatrix(Matrix4f matrix) {
        this.transformationMatrix = matrix;
    }
}
