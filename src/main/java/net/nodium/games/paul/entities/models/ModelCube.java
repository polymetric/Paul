package net.nodium.games.paul.entities.models;


import net.nodium.games.paul.AssetLoader;
import net.nodium.games.paul.gl.shaders.GLShader;
import net.nodium.games.paul.gl.shaders.GLShaderBase;
import net.nodium.games.paul.gl.textures.Texture;

public class ModelCube extends ModelEntity {
    public ModelCube(
            AssetLoader assetLoader,
            GLShaderBase shader,
            Texture texture,
            float width,
            float height,
            float depth,
            boolean centered
    ) {
        super(assetLoader);

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

        if (centered) {
            for (int i = 0; i < vertices.length; i++) {
                switch (i % 3) {
                    case 0:
                        vertices[i] -= width / 2.0f;
                        break;
                    case 1:
                        vertices[i] -= height / 2.0f;
                        break;
                    case 2:
                        vertices[i] -= depth / 2.0f;
                        break;
                    default:
                }
            }
        }

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

        float[] normals = {
                // face Xa
                0, 0, -1,
                0, 0, -1,
                0, 0, -1,
                0, 0, -1,

                // face Za
                -1, 0, 0,
                -1, 0, 0,
                -1, 0, 0,
                -1, 0, 0,

                // face Xb
                0, 0, 1,
                0, 0, 1,
                0, 0, 1,
                0, 0, 1,

                // face Zb
                1, 0, 0,
                1, 0, 0,
                1, 0, 0,
                1, 0, 0,

                // face Ya
                0, -1, 0,
                0, -1, 0,
                0, -1, 0,
                0, -1, 0,

                // face Yb
                0, 1, 0,
                0, 1, 0,
                0, 1, 0,
                0, 1, 0,
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

        super.model = assetLoader.loadToVAO(vertices, textureCoords, normals, indices,  texture, shader);
    }
}
