package net.nodium.games.paul.entities.models;


import net.nodium.games.paul.entities.renderers.RenderEntity;
import net.nodium.games.paul.gl.textures.Texture;

public class ModelCube extends ModelEntity {
    public ModelCube(RenderEntity entityRenderer, float width, float height, float depth) {
        super(entityRenderer);

        float[] vertices = {
              //width, height, depth,
                // face Xa
                width, 0,      0,
                0,     0,      0,
                0,     height, 0,
                width, height, 0,

                // face Za
                0,     0,      0,
                0,     0,      depth,
                0,     height, depth,
                0,     height, 0,

                // face Xb
                0,     0,      depth,
                width, 0,      depth,
                width, height, depth,
                0,     height, depth,

                // face Zb
                width, 0,      depth,
                width, 0,      0,
                width, height, 0,
                width, height, depth,

                // face Ya
                width, 0,      depth,
                0,     0,      depth,
                0,     0,      0,
                width, 0,      0,

                // face Yb
                width, height, 0,
                0,     height, 0,
                0,     height, depth,
                width, height, depth,
        };

        int[] indices = {
                // face Xa
                0,  1,  3,
                1,  2,  3,

                // face Za
                4,  5,  7,
                5,  6,  7,

                // face Xb
                8,  9, 11,
                9, 10, 11,

                // face Zb
                12, 13, 15,
                13, 14, 15,

                // face Ya
                16, 17, 19,
                17, 18, 19,

                // face Yb
                20, 21, 23,
                21, 22, 23,
        };

        float[] textureCoords = {
                // face Xa
                0, 1,
                1, 1,
                1, 0,
                0, 0,

                // face Za
                0, 1,
                1, 1,
                1, 0,
                0, 0,

                // face Xb
                0, 1,
                1, 1,
                1, 0,
                0, 0,

                // face Zb
                0, 1,
                1, 1,
                1, 0,
                0, 0,

                // face Ya
                0, 1,
                1, 1,
                1, 0,
                0, 0,

                // face Yb
                0, 1,
                1, 1,
                1, 0,
                0, 0,
        };

        Texture textureTest = new Texture("/textures/face.png");

        super.model = entityRenderer.assetLoader.loadToVAO(vertices, indices, textureCoords, textureTest, entityRenderer.shader);
    }
}
