package net.nodium.games.paul.entities.renderers;

import net.nodium.games.paul.AssetLoader;
import net.nodium.games.paul.entities.models.ModelCube;
import net.nodium.games.paul.entities.models.ModelPlaneHorizontal;
import net.nodium.games.paul.gl.shaders.GLShaderBase;
import net.nodium.games.paul.gl.textures.Texture;

public class RenderGround extends RenderEntity {
    public RenderGround(AssetLoader assetLoader, GLShaderBase shader) {
        super(assetLoader, shader);

        modelEntity = new ModelPlaneHorizontal(this, new Texture("/textures/resumejack.png"), 10, 10, true);
    }
}
