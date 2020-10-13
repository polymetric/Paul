package net.nodium.games.paul.entities.renderers;

import net.nodium.games.paul.AssetLoader;
import net.nodium.games.paul.entities.Entity;
import net.nodium.games.paul.entities.models.ModelCube;
import net.nodium.games.paul.entities.models.ModelEntity;
import net.nodium.games.paul.gl.shaders.GLShaderBase;
import net.nodium.games.paul.gl.textures.Texture;

public class RenderOofCube extends RenderEntity {
    public RenderOofCube(AssetLoader assetLoader, GLShaderBase shader) {
        super(assetLoader, shader);

//        modelEntity = new ModelOofCube(this);
        modelEntity = new ModelCube(assetLoader, shader, new Texture("/textures/face.png"), 1, 1, 1, true);
    }

    @Override
    public void render(Entity entity, ModelEntity modelEntity) {
        modelEntity.model.setIsLit(true);
        super.render(entity, modelEntity);
    }
}
