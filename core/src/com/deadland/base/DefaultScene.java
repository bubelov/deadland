package com.deadland.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deadland.base.model.BaseEntity;

import java.util.ArrayList;

/**
 * @author Alexey Nevinsky
 * @version 20.08.14 22:38
 */
public class DefaultScene extends GameScene {

    public DefaultScene() {
        entities = new ArrayList<BaseEntity>();
    }

    @Override
    public void render(SpriteBatch batch) {
        for (BaseEntity entity : entities) {
            entity.render(batch);
        }
    }

    @Override
    public void update(float delta) {
        for (BaseEntity entity : entities) {
            entity.update(delta);
        }
    }

    public void add(BaseEntity entity) {
        entities.add(entity);
    }
}
