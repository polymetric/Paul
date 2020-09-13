package net.nodium.games.paul.entities.models;

import net.nodium.games.paul.entities.renderers.RenderEntity;
import net.nodium.games.paul.gl.models.GLModelTextured;

public class ModelEntity {
    public RenderEntity entityRenderer;
    public GLModelTextured model;

    public ModelEntity(RenderEntity entityRenderer) {
        this.entityRenderer = entityRenderer;
    }
}
