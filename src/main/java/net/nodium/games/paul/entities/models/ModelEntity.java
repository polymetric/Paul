package net.nodium.games.paul.entities.models;

import net.nodium.games.paul.entities.Entity;
import net.nodium.games.paul.entities.renderers.RenderEntity;
import net.nodium.games.paul.gl.models.GLModel;

public class ModelEntity {
    public RenderEntity entityRenderer;
    public GLModel model;

    public ModelEntity(RenderEntity entityRenderer) {
        this.entityRenderer = entityRenderer;
    }
}
