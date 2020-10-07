package net.nodium.games.paul.entities.renderers;

import net.nodium.games.paul.AssetLoader;
import net.nodium.games.paul.entities.models.ModelCube;
import net.nodium.games.paul.gl.shaders.GLShaderBase;

public class RenderOofCube extends RenderEntity {
    public RenderOofCube(AssetLoader assetLoader, GLShaderBase shader) {
        super(assetLoader, shader);

//        modelEntity = new ModelOofCube(this);
        modelEntity = new ModelCube(this, 1, 1, 1, true);
    }
}
