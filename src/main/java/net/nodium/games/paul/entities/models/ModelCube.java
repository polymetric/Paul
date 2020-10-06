package net.nodium.games.paul.entities.models;


import net.nodium.games.paul.entities.renderers.RenderEntity;
import net.nodium.games.paul.gl.textures.Texture;

public class ModelCube extends ModelEntity {
    public ModelCube(RenderEntity entityRenderer, float width, float height, float depth) {
        super(entityRenderer);

        float[] vertices = {
                0,     0,      0,       // 0
                width, 0,      0,       // 1
                width, depth,  0,       // 2
                0,     depth,  0,       // 3

                0,     0,      height,  // 4
                width, 0,      height,  // 5
                width, depth,  height,  // 6
                0,     depth,  height,  // 7
        };

        float[] textureCoords = {
                0, 1,
                1, 1,
                1, 0,
                0, 0,

                0, 1,
                1, 1,
                1, 0,
                0, 0,
        };

        int[] indices = {
                // face Xa
                  0,   1,   4,
                  1,   4,   5,

                // face Yb
                  1,   2,   5,
                  2,   5,   6,

                // face Xb
                  2,   3,   6,
                  3,   6,   7,

                // face Ya
                  3,   0,   7,
                  0,   7,   4,

                // face Za
                  3,   2,   0,
                  0,   1,   2,

                // face Zb
                  4,   5,   7,
                  5,   6,   7
        };

        Texture textureTest = new Texture("/textures/face.png");

        super.model = entityRenderer.assetLoader.loadToVAO(vertices, indices, textureCoords, textureTest, entityRenderer.shader);
    }
}
