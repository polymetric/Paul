package net.nodium.games.paul.entities.renderers;

import net.nodium.games.paul.AssetLoader;
import net.nodium.games.paul.entities.models.ModelCube;
import net.nodium.games.paul.entities.models.ModelPlaneHorizontal;
import net.nodium.games.paul.gl.shaders.GLShaderBase;

public class RenderGround extends RenderEntity {
    public RenderGround(AssetLoader assetLoader, GLShaderBase shader) {
        super(assetLoader, shader);

        modelEntity = new ModelCube(this, 0.1f, 1, 0.1f);
        modelEntity = new ModelPlaneHorizontal(this, 10, 10);
    }
}
