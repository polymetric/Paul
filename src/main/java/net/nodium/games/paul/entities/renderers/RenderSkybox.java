package net.nodium.games.paul.entities.renderers;

import net.nodium.games.paul.AssetLoader;
import net.nodium.games.paul.entities.models.ModelCube;
import net.nodium.games.paul.gl.shaders.GLShaderBase;
import net.nodium.games.paul.gl.textures.Texture;

public class RenderSkybox extends RenderEntity {
    public RenderSkybox(AssetLoader assetLoader, GLShaderBase shader) {
        super(assetLoader, shader);

        modelEntity = new ModelCube(assetLoader, shader, new Texture("/textures/face.png"), 1, 1, 1, true);
    }
}
