package net.nodium.games.paul.entities.renderers;

import net.nodium.games.paul.AssetLoader;
import net.nodium.games.paul.entities.Entity;
import net.nodium.games.paul.entities.models.ModelEntity;
import net.nodium.games.paul.gl.shaders.GLShaderBase;
import net.nodium.games.paul.math.MathUtils;

public class RenderEntity {
    public AssetLoader assetLoader;
    public ModelEntity modelEntity;
    public GLShaderBase shader;

    public RenderEntity(AssetLoader assetLoader, GLShaderBase shader) {
        this.assetLoader = assetLoader;
        this.shader = shader;
    }

    public void render(Entity entity, ModelEntity modelEntity) {
        modelEntity.model.setTransformationMatrix(MathUtils.createTransformationMatrix(entity.posVis, entity.rotVis, entity.scale));
        modelEntity.model.render();
    }
}
