package net.nodium.games.paul.entities.models;

import net.nodium.games.paul.AssetLoader;
import net.nodium.games.paul.entities.renderers.RenderEntity;
import net.nodium.games.paul.gl.models.GLModelTextured;

public class ModelEntity {
    public AssetLoader assetLoader;
    public GLModelTextured model;

    public ModelEntity(AssetLoader assetLoader) {
        this.assetLoader = assetLoader;
    }
}
