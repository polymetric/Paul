package net.nodium.games.paul.entities.renderers;

import net.nodium.games.paul.AssetLoader;
import net.nodium.games.paul.entities.Entity;
import net.nodium.games.paul.entities.models.ModelEntity;
import net.nodium.games.paul.gl.shaders.GLShader;
import net.nodium.games.paul.gl.shaders.GLShaderBase;
import net.nodium.games.paul.math.MathUtils;
import org.joml.Vector3f;

public class RenderEntity {
    public AssetLoader assetLoader;
    public ModelEntity modelEntity;
    public GLShaderBase shader;

    public RenderEntity(AssetLoader assetLoader, GLShaderBase shader) {
        this.assetLoader = assetLoader;
        this.shader = shader;
    }

    public void render(Entity entity, ModelEntity modelEntity) {
        Vector3f posVis = new Vector3f();
        Vector3f rotVis = new Vector3f();

        modelEntity.model.setTransformationMatrix(MathUtils.createTransformationMatrix(entity.pos, entity.rot, 1.0F));
        modelEntity.model.render();
    }
}
