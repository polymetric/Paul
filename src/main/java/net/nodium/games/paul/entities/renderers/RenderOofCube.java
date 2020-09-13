package net.nodium.games.paul.entities.renderers;

import net.nodium.games.paul.AssetLoader;
import net.nodium.games.paul.entities.models.ModelOofCube;
import net.nodium.games.paul.gl.models.GLModel;
import net.nodium.games.paul.gl.shaders.GLShader;
import net.nodium.games.paul.gl.shaders.GLShaderBase;
import net.nodium.games.paul.gl.textures.Texture;

public class RenderOofCube extends RenderEntity {
    private Texture texture;

    public RenderOofCube(AssetLoader assetLoader, GLShaderBase shader) {
        super(assetLoader, shader);

        modelEntity = new ModelOofCube(this);
    }
}
