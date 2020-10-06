package net.nodium.games.paul.entities.models;


import net.nodium.games.paul.entities.renderers.RenderEntity;
import net.nodium.games.paul.gl.textures.Texture;

public class ModelPlaneHorizontal extends ModelEntity {
    public ModelPlaneHorizontal(RenderEntity entityRenderer, float width, float depth) {
        super(entityRenderer);

        float[] vertices = {
              //width, height, depth,
                width, 0,      0,
                0,     0,      0,
                0,     0,      depth,
                width, 0,      depth,
        };

        int[] indices = {
                0,  1,  3,
                1,  2,  3,
        };

        float[] textureCoords = {
                0, 1,
                1, 1,
                1, 0,
                0, 0,
        };

        Texture textureTest = new Texture("/textures/face.png");

        super.model = entityRenderer.assetLoader.loadToVAO(vertices, indices, textureCoords, textureTest, entityRenderer.shader);
    }
}
