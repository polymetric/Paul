package net.nodium.games.paul.entities.models;


import net.nodium.games.paul.AssetLoader;
import net.nodium.games.paul.gl.shaders.GLShaderBase;
import net.nodium.games.paul.gl.textures.Texture;

public class ModelPlaneHorizontal extends ModelEntity {
    public ModelPlaneHorizontal(AssetLoader assetLoader, GLShaderBase shader, Texture texture, float width, float depth, boolean centered) {
        super(assetLoader);

        float[] vertices = {
              //width, height, depth,
                width, 0,      0,
                0,     0,      0,
                0,     0,      depth,
                width, 0,      depth,
        };

        if (centered) {
            for (int i = 0; i < vertices.length; i++) {
                switch (i % 3) {
                    case 0:
                        vertices[i] -= width / 2.0f;
                        break;
                    case 2:
                        vertices[i] -= depth / 2.0f;
                        break;
                    default:
                }
            }
        }

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

        super.model = assetLoader.loadToVAO(vertices, indices, textureCoords, texture, shader);
    }
}
