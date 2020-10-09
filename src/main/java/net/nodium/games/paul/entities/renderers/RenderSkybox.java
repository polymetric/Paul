package net.nodium.games.paul.entities.renderers;

import net.nodium.games.paul.AssetLoader;
import net.nodium.games.paul.entities.Entity;
import net.nodium.games.paul.entities.models.ModelCube;
import net.nodium.games.paul.entities.models.ModelEntity;
import net.nodium.games.paul.gl.shaders.GLShader;
import net.nodium.games.paul.gl.shaders.GLShaderBase;
import net.nodium.games.paul.gl.textures.Texture;
import net.nodium.games.paul.math.MathUtils;
import org.joml.Matrix4f;

public class RenderSkybox extends RenderEntity {
    public RenderSkybox(AssetLoader assetLoader, GLShaderBase shader) {
        super(assetLoader, shader);

        modelEntity = new ModelCube(assetLoader, shader, new Texture("/textures/ns.PNG"), 1, 1, 1, true);
    }

    @Override
    public void render(Entity entity, ModelEntity modelEntity) {
        modelEntity.model.setTransformationMatrix(new Matrix4f());
        modelEntity.model.render();
    }
}
