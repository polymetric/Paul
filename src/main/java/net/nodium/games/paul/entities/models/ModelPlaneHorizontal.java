package net.nodium.games.paul.entities.models;


import net.nodium.games.paul.entities.renderers.RenderEntity;
import net.nodium.games.paul.gl.textures.Texture;

public class ModelPlaneHorizontal extends ModelEntity {
    public ModelPlaneHorizontal(RenderEntity entityRenderer, float width, float depth, boolean centered) {
        super(entityRenderer);

        float[] vertices = {
              //width, height, depth,
                width, 0,      0,
                0,     0,      0,
                0,     0,      depth,
                width, 0,      depth,
        };

        for (int i = 0; i < vertices.length; i += 3) {
            System.out.printf("%6.3f %6.3f %6.3f\n", vertices[i], vertices[i+1], vertices[i+2]);
        }

        System.out.println();

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

        for (int i = 0; i < vertices.length; i += 3) {
            System.out.printf("%6.3f %6.3f %6.3f\n", vertices[i], vertices[i+1], vertices[i+2]);
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

        Texture textureTest = new Texture("/textures/face.png");

        super.model = entityRenderer.assetLoader.loadToVAO(vertices, indices, textureCoords, textureTest, entityRenderer.shader);
    }
}
